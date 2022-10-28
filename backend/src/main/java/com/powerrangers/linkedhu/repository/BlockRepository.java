package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.Block;
import com.powerrangers.linkedhu.entity.PrivateMessage;
import com.powerrangers.linkedhu.entity.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    User findByUsername(String username);
    Block getBlockByUsername(String username);
    boolean existsByUsername(String username);
    @Transactional void deleteBlockByUsername(String username);
}
