package com.powerrangers.linkedhu.entity;

import lombok.*;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@SequenceGenerator(name = "idgen9", sequenceName = "PRIVATEMSG_SEQ", allocationSize = 1)
@NoArgsConstructor
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;                     //BıgInt Integer

    private String senderUsername;

    private String receiverUsername;

    private String content;

    private Date date;

    public PrivateMessage(String sender, String receiver, String content) {
        this.date = new Date(); // Rising the message

        this.senderUsername = sender;
        this.receiverUsername = receiver;
        this.content = content;
    }
}
