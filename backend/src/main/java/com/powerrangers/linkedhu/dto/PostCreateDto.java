package com.powerrangers.linkedhu.dto;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class PostCreateDto {

    @NotEmpty
    String postType;
    @NotEmpty
    Long userId;

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
    String jobTitle;
    String eventDate;
    String text;
    String title;

}
