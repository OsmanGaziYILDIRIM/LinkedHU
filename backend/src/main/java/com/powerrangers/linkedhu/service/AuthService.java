package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.auth.TokenManager;
import com.powerrangers.linkedhu.dto.LoginRequestDTO;
import com.powerrangers.linkedhu.dto.LoginResponseDTO;
import com.powerrangers.linkedhu.dto.RegisterRequestDTO;
import com.powerrangers.linkedhu.entity.Academician;
import com.powerrangers.linkedhu.entity.Admin;
import com.powerrangers.linkedhu.entity.Graduate;
import com.powerrangers.linkedhu.entity.Student;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.AuthorityRepository;
import com.powerrangers.linkedhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.*;

@Service
public class AuthService {

    @Lazy
    public AuthService(DaoAuthenticationProvider authenticationProvider, AuthorityRepository authorityRepository, CustomUserDetailsManager customUserDetailsManager, UserRepository userRepository, SecurityService securityService) {
        this.authenticationProvider = authenticationProvider;
        this.customUserDetailsManager = customUserDetailsManager;
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.authorityRepository = authorityRepository;
    }

    private final DaoAuthenticationProvider authenticationProvider;
    private final CustomUserDetailsManager customUserDetailsManager;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final AuthorityRepository authorityRepository;

    @Value("${linkedhu.authoraties.max}")
    private Integer MAX_AUTHORATIES;

    @Transactional
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequest){
        /*if (!userRepository.getUserEnableStatu(loginRequest.getUsername())) { return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(null);
        }*/
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        try{
            Authentication userAuth = authenticationProvider.authenticate(authentication);

            String token = TokenManager.generateToken(userAuth);
            User user = userRepository.findByUsername(loginRequest.getUsername());

            user.setLastLogin(new Date());
            userRepository.save(user);

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token, user.getId(), TokenManager.getAuthorities(userAuth), user.getUsertype());
            return new ResponseEntity<LoginResponseDTO>(loginResponseDTO, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED) // 401
                    .body(null);
        }
    }

    public String getAuthenticatedUsername(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
            return username;
        }
        catch (Exception e){
            System.out.println("getAuthenticatedUsername user not found!");
            return null;
        }
    }

    @Transactional
    public ResponseEntity<String> register(RegisterRequestDTO registerRequestDTO){

        Integer requestedUserType = registerRequestDTO.getUsertype();

        if (requestedUserType == null || requestedUserType > MAX_AUTHORATIES || requestedUserType < 1) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 409
                    .body("Requested usertype value is not acceptable.");
        }

        /* Check Needed attributes for usertype is null or not */
        if(customUserDetailsManager.userExistsByUsername(registerRequestDTO.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT) // 409
                    .body("Member with that email already exists!");
        }
        String nameWithUpperCase = registerRequestDTO.getName();
        String surnameWithUpperCase = registerRequestDTO.getSurname();
        nameWithUpperCase = nameWithUpperCase.substring(0, 1).toUpperCase() + nameWithUpperCase.substring(1);
        surnameWithUpperCase = surnameWithUpperCase.substring(0, 1).toUpperCase() + surnameWithUpperCase.substring(1);
        User user = null;
        if (requestedUserType == 1) // REGISTER STUDENT
        {
            if ( registerRequestDTO.getStudentId() == null ) { ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // 409
                    .body("Requested studentId value is not acceptable.");}

           user = new Student(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(),
                    nameWithUpperCase, surnameWithUpperCase, registerRequestDTO.getBirthdate(),
                    registerRequestDTO.getGender(), registerRequestDTO.getPhoneNumber(),
                    registerRequestDTO.getDegree(), registerRequestDTO.getDepartment(), registerRequestDTO.getStudentId());
        }
        else if (requestedUserType == 2) // REGISTER GRADUATE
        {
            user = new Graduate(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(),
                    nameWithUpperCase, surnameWithUpperCase, registerRequestDTO.getBirthdate(),
                    registerRequestDTO.getGender(), registerRequestDTO.getPhoneNumber(),
                    registerRequestDTO.getGraduate_year());
        }
        else if (requestedUserType == 3) // REGISTER ACADEMICIAN
        {
            user = new Academician(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(),
                    nameWithUpperCase, surnameWithUpperCase, registerRequestDTO.getBirthdate(),
                    registerRequestDTO.getGender(), registerRequestDTO.getPhoneNumber());
        }
        else if (requestedUserType == 4) // REGISTER ADMIN
        {
            user = new Admin(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(),
                    nameWithUpperCase, surnameWithUpperCase);
        }
        customUserDetailsManager.createUser(user);
        return  ResponseEntity.ok("You are successfully registered, Wait for the system admins approval.");
    }
}
