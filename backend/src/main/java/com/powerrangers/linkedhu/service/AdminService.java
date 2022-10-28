package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.dto.UserDTO;
import com.powerrangers.linkedhu.entity.Block;
import com.powerrangers.linkedhu.entity.Student;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.AuthorityRepository;
import com.powerrangers.linkedhu.repository.BlockRepository;
import com.powerrangers.linkedhu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SecurityService securityService;
    private final BlockRepository blockRepository;
    private final AuthorityRepository authorityRepository;

    public ResponseEntity<String> blockUserFromSystem(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) { return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There is no user with this id"); }
        String username = userRepository.getUserNameById(userId);
        if (userService.userIsBlockedBySystem(username)) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("This user is already blocked from the system."); }
        User user = optionalUser.get();
        if (user.isStudentRepresentative()) { user.deleteRepresentativeRole(); }
        blockRepository.save(new Block(securityService.getAuthorizedUsername(), username, "ADMIN PANEL"));
        return ResponseEntity.ok("User is successfully BLOCKED from the system.");
    }

    public ResponseEntity<String> unBlockUserFromSystem(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) { return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There is no user with this username"); }
        String username = userRepository.getUserNameById(userId);
        if (!userService.userIsBlockedBySystem(username)) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("This user has not been blocked before."); }
        blockRepository.deleteBlockByUsername(username);
        return ResponseEntity.ok("User is successfully UNBLOCKED from the system.");
    }

    public ResponseEntity<String> enableSpesificUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) { return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There is no user with this userId: " + String.valueOf(userId)); }
        User user = userOptional.get();
        user.setEnable(true);
        userRepository.save(user);
        return ResponseEntity.ok("User is successfully ENABLED.");
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = userService.listAll();
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (User u : userList) {
            userDTOList.add(
                    new UserDTO( u.getId(),
                            u.getName() + u.getSurname(),
                            u.getAuthorities().toString(),
                            u.getBirthdate(),
                            u.getPhoneNumber(),
                            u.getGender(),
                            u.getText(),
                            u.getDegree(),
                            u.getDepartment(),
                            u.getGraduate_year(),
                            u.getEnable(),
                            userService.userIsBlockedBySystem(u.getUsername())) );
        }
        return userDTOList;
    }

    public List<UserDTO> getAllStudentRepresentatives() {
        List<User> userList = userRepository.getStudentRepresentatives();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User u : userList) {
            if (u.isStudentRepresentative())
                userDTOList.add( new UserDTO( u.getId(),
                        u.getName() + " " +  u.getSurname(),
                        u.getAuthorities().toString(),
                        u.getBirthdate(),
                        u.getPhoneNumber(),
                        u.getGender(),
                        u.getText(),
                        u.getDegree(),
                        u.getDepartment(),
                        u.getGraduate_year(),
                        u.getEnable(),
                        userService.userIsBlockedBySystem(u.getUsername())) );
        }
        return userDTOList;
    }

    public ResponseEntity<String> setStudentRepresentative(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent() || !userOptional.get().isStudent()) { return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There is no student user with this userId: " + String.valueOf(userId)); }
        Student student = (Student)userOptional.get();
        if(student.getDegree() == null) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body("This user's degree value is null."); }
        for (User user : userRepository.getStudentRepresentatives()) {
            if (((Student)user).getDegree() == student.getDegree()) { return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("There is already a student representative on this degree: " + String.valueOf(user.getDegree()));}
        }
        student.setStudentRespentative( authorityRepository.findByAuthority("REPRESENTATIVE") );
        userRepository.save(student);
        return ResponseEntity.ok("Role is successfully assigned to user.");
    }

    public ResponseEntity<String> deleteStudentRepresentative(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent() || !userOptional.get().isStudent()) { return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There is no student user with this userId: " + String.valueOf(userId)); }
        User user = userOptional.get();
        user.deleteRepresentativeRole();
        userRepository.save(user);
        return ResponseEntity.ok("Role is successfully deleted from user.");

    }
}
