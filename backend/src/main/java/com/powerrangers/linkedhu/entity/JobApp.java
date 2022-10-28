package com.powerrangers.linkedhu.entity;


import com.powerrangers.linkedhu.entity.common.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="JobApplication")
@Data
public class JobApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String  fileId;


    @Temporal(TemporalType.TIMESTAMP)
    private Date LastDate;

    private String fullName;
    private String email;
    private String phoneNumber;
    private float gpa;
    private String coverLetter;

}
