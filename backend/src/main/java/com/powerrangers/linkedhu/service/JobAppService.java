package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.repository.JobPostRepository;
import org.springframework.stereotype.Service;
import com.powerrangers.linkedhu.entity.JobApp;
import com.powerrangers.linkedhu.entity.JobPost;
import com.powerrangers.linkedhu.repository.JobAppRepository;
import com.powerrangers.linkedhu.dto.ApplicationDTO;
import java.util.Date;
@Service
public class JobAppService {

    private JobAppRepository jArepo;
    private JobPostRepository jPrepo;


    public JobAppService(JobAppRepository jA,JobPostRepository jP) {
        this.jArepo=jA; this.jPrepo=jP;
    }

    public void makeAnApplication(ApplicationDTO appDTO) {
       // try{
            JobApp job=new JobApp();
            job.setEmail(appDTO.getEmail());
            job.setFileId(appDTO.getFileId());
            job.setCoverLetter(appDTO.getCoverLetter());
            job.setLastDate(new Date());
            job.setGpa(appDTO.getGpa());
            job.setFullName(appDTO.getFullName());
            job.setPhoneNumber(appDTO.getPhoneNumber());
            jArepo.save(job);
            JobPost post=new JobPost();
            post=jPrepo.findById(appDTO.getPostId()).get();
            post.addUserApp(job.getId());
            jPrepo.save(post);

       // }
       /* catch(Exception e) {
            System.out.println("There is an error with job application");
            return ;
        }*/


    }

}
