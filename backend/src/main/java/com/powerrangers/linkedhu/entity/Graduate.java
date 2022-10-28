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
@AllArgsConstructor
public class Graduate extends User implements UserDetails {
    public static Byte GRADUATE_TYPE_INDEX = 2;
    protected String graduate_year;

    public Graduate(String username, String password, String name, String surname,
                    String birthdate, Byte gender, String phoneNumber, String graduate_year) {
        this.usertype = GRADUATE_TYPE_INDEX;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.graduate_year = graduate_year;
        System.out.println(this.graduate_year);
    }

}
