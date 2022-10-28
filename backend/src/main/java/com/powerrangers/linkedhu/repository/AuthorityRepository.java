package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Transactional Authority save(Authority authority);
    @Transactional Authority findByAuthority(String authority);

    @Query("SELECT COUNT(a) from Authority a")
    @Transactional Integer getAuthoritiesMaxCount();
}
