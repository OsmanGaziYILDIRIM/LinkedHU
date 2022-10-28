package com.powerrangers.linkedhu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@SequenceGenerator(name = "idgen9", sequenceName = "PRIVATEMSG_SEQ", allocationSize = 1)
@Table(name="systemblocks")
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;                     //BıgInt Integer

    @Column(name = "admin_name")
    private String adminname;

    private String username;

    private String reason;

    private Date date;

    public Block(String adminname, String username, String reason) {
        this.date = new Date(); // Rising the message

        this.adminname = adminname;
        this.username = username;
        this.reason = reason;
    }
}
