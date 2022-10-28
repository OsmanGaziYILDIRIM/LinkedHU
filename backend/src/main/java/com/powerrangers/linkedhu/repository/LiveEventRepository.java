package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.LiveEventPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveEventRepository extends JpaRepository<LiveEventPost, Long> {
    List<LiveEventPost> findByUserId(Long userId);

    @Query("SELECT l FROM LiveEventPost l WHERE l.liveEventDescription LIKE CONCAT('%',?1,'%') ")
    List<LiveEventPost> getSearchPosts(String searchWord);
}
