package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserRepository userRepository;

    public String getAuthorizedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }

    public User getAuthorizedMember(){
        String username = getAuthorizedUsername();
        return userRepository.findByUsername(username);
    }

    public boolean hasAuthority(String authority){
        return this.getAuthorizedMember().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(authority));
    }
}
