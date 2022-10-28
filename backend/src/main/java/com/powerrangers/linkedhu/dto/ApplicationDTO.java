package com.powerrangers.linkedhu.dto;

import lombok.Data;
import com.powerrangers.linkedhu.entity.FileDB;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
public class ApplicationDTO {
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private float gpa;
    @NotEmpty
    private String coverLetter;

    @NotEmpty
    private Long postId;

    private String fileId;

}
