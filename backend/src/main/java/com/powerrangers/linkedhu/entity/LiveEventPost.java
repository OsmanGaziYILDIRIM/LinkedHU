package com.powerrangers.linkedhu.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Data
public class LiveEventPost extends Post{

    private String liveEventLink;
    private String liveEventDescription;
    private String eventTitle;
    private String eventDate;
    private String location;
}
