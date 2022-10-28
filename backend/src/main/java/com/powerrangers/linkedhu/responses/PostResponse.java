package com.powerrangers.linkedhu.responses;

import com.powerrangers.linkedhu.entity.Post;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class PostResponse {

    Long id;
    Long userId;
    String name;
    Date date;
    String postType;
    String positionName;
    String location;
    String companyName;
    int salary;
    String currency;
    String workType;
    String jobDescription;
    String liveEventLink;
    String liveEventDescription;
    String eventTitle;
    String eventDate;
    String jobTitle;
    String text;
    String title;

    public PostResponse() {


    }
    public void setJPResponse(Long id, Long userId,String name,Date date, String postType,String positionName,String location, String companyName,
                              int salary, String currency, String workType, String jobDescription,String jobTitle) {
        this.id=id;
        this.userId=userId;
        this.name=name;
        this.date=date;
        this.postType=postType;
        this.positionName=positionName;
        this.location=location;
        this.companyName=companyName;
        this.salary=salary;
        this.currency=currency;
        this.workType=workType;
        this.jobDescription=jobDescription;
        this.jobTitle=jobTitle;
    }
    public void setLEMResponse(Long id, Long userId, String name, Date date, String postType, String link, String description, String title, String eventDate,String location) {
        this.id=id;
        this.userId=userId;
        this.name=name;
        this.date=date;
        this.postType=postType;
        this.liveEventLink=link;
        this.liveEventDescription=description;
        this.eventTitle=title;
        this.eventDate=eventDate;
        this.location=location;


    }
    public void setNMResponse(Long id, Long userId, String name, Date date, String postType, String title, String text) {
        this.id=id;
        this.userId=userId;
        this.name=name;
        this.date=date;
        this.postType=postType;
        this.title=title;
        this.text=text;
    }
}