package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.entity.Authority;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.AuthorityRepository;
import com.powerrangers.linkedhu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public void createUser(UserDetails user) {
        User currentUser = (User) user;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        currentUser.setPassword(passwordEncoder.encode(currentUser.getPassword()));

        startAuthorities();

        if (currentUser.getUsertype() == 1) {
            currentUser.setEnable(false);
            currentUser.setAuthorities(List.of(authorityRepository.findByAuthority("STUDENT")));
        }
        else if (currentUser.getUsertype() == 2){
            currentUser.setEnable(false);
            currentUser.setAuthorities(List.of(authorityRepository.findByAuthority("GRADUATE")));
        }
        else if (currentUser.getUsertype() == 3) {
            currentUser.setEnable(false);
            currentUser.setAuthorities(List.of(authorityRepository.findByAuthority("ACADEMICIAN")));
        }
        else if (currentUser.getUsertype() == 4) {
            currentUser.setEnable(true);
            currentUser.setAuthorities(List.of(authorityRepository.findByAuthority("ADMIN")));
        }
        userRepository.save(currentUser);
    }

    public void startAuthorities() {
        if (authorityRepository.findByAuthority("STUDENT") == null) authorityRepository.save(new Authority("STUDENT"));
        if (authorityRepository.findByAuthority("GRADUATE") == null) authorityRepository.save(new Authority("GRADUATE"));
        if (authorityRepository.findByAuthority("ACADEMICIAN") == null) authorityRepository.save(new Authority("ACADEMICIAN"));
        if (authorityRepository.findByAuthority("ADMIN") == null) authorityRepository.save(new Authority("ADMIN"));
        if (authorityRepository.findByAuthority("REPRESENTATIVE") == null) authorityRepository.save(new Authority("REPRESENTATIVE"));
    }

    @Override
    public void updateUser(UserDetails member) {
        User oldMember = (User) loadUserByUsername(member.getUsername());
        User newMember = (User) member;
        newMember.setId(oldMember.getId());
        userRepository.save(newMember);
    }

    public boolean userExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

}
