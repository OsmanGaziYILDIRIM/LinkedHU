package com.powerrangers.linkedhu.dto;

import com.powerrangers.linkedhu.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {

    public String token;
    public Long id;
    public List<String> authorities;
    public Byte usertype;
}
