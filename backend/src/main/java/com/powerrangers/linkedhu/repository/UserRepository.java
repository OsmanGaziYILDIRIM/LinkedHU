package com.powerrangers.linkedhu.repository;
import com.powerrangers.linkedhu.entity.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    //User findByMail(String email);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);

    @Query("select u.username from User u where u.id = ?1")
    String getUserNameById(Long id);

    @Query("SELECT id FROM User WHERE username = ?1")
    Long getUserId(String username);

    @Query("SELECT COUNT(u) from User u WHERE u.id = ?2 and ?1 in elements(u.blockedUser)")
    Integer getBlockedUsers(Long blocked, Long by);
    //search
    @Query("SELECT u FROM User u WHERE (u.username LIKE CONCAT('%',?1,'%') OR  u.name LIKE CONCAT('%',?1,'%') OR u.surname LIKE CONCAT('%',?1,'%'))AND u.Enable=true AND u.usertype <>4")
    List<User> getSearchUsers(String searchWord);

    @Query("SELECT u FROM User u WHERE u.Enable = true")
    List<User> getEnabledUsers();

    @Query("SELECT u.Enable FROM User u WHERE u.username = ?1")
    Boolean getUserEnableStatu(String username);

    @Query("SELECT u FROM User u WHERE u.usertype = 1 and u.authorities.size > 1")
    List<User> getStudentRepresentatives();
}
