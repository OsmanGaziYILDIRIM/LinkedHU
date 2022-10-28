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
public class Academician extends User implements UserDetails {
    public static Byte ACADEMICIAN_TYPE_INDEX = 3;

    public Academician(String username, String password, String name, String surname,
                       String birthdate, Byte gender, String phoneNumber) {
        this.usertype = ACADEMICIAN_TYPE_INDEX;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

}
