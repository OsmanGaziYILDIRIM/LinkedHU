package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.JobApp;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface JobAppRepository extends JpaRepository<JobApp,Long>{
    List<JobApp> findByPhoneNumber(String phoneNumber);
}
