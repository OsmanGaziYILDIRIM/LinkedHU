package com.powerrangers.linkedhu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
public class RegisterRequestDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String birthdate;

    private Byte gender;

    private String phoneNumber;

    private Byte department;

    private String graduate_year;

    private Byte degree;

    private Integer usertype;

    private String studentId;
}