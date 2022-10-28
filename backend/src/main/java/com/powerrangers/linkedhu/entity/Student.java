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
public class Student extends User implements UserDetails {
    public static Byte STUDENT_TYPE_INDEX = 1;
    private Byte department;
    private Byte degree;
    private String studentId;

    public Student(String username, String password, String name, String surname,
                   String birthdate, Byte gender, String phoneNumber, Byte degree, Byte department, String studentId) {
        this.usertype = STUDENT_TYPE_INDEX;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.degree = degree;
        this.department = department;
        this.studentId = studentId;
    }

}
