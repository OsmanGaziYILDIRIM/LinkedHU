    package com.powerrangers.linkedhu.entity;

    import lombok.Data;
    import java.util.ArrayList;
    import java.util.List;
    import javax.persistence.*;

    @Entity
    @Data
    public class JobPost extends Post{

        private String positionName;
        private String location;
        private int salary;
        private String currency;
        private String workType;
        private String companyName;
        private String jobTitle;

        @ElementCollection
        @CollectionTable(
                name="JobApplications",
                joinColumns=@JoinColumn(name="OWNER_ID")
        )
        private List<Long> userApp;

        @Lob
        private String jobDescription;

        public void addUserApp(Long l) {
            userApp.add(l);
        }
    }
