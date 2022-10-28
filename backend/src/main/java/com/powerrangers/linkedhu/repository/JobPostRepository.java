package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByUserId(Long userId);

    @Query("SELECT j FROM JobPost j WHERE j.jobDescription LIKE CONCAT('%',?1,'%')")
    List<JobPost> getSearchPosts(String searchWord);
}
