package com.powerrangers.linkedhu.entity;

import com.powerrangers.linkedhu.entity.common.User;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Admin extends User implements UserDetails {
    public static Byte ADMIN_TYPE_INDEX = 4;

    public Admin(String username, String password, String name, String surname) {
        this.usertype = ADMIN_TYPE_INDEX;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

}
