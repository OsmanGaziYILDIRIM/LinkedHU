package com.powerrangers.linkedhu.repository;


import com.powerrangers.linkedhu.entity.NormalPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalPostRepository extends JpaRepository<NormalPost, Long> {
    List<NormalPost> findByUserId(Long userId);

    @Query("SELECT n FROM NormalPost n WHERE n.text LIKE CONCAT('%',?1,'%')")
    List<NormalPost> getSearchPosts(String searchWord);
}
