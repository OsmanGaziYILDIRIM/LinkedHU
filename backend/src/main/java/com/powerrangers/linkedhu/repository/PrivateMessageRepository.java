package com.powerrangers.linkedhu.repository;

import com.powerrangers.linkedhu.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
    List<PrivateMessage> findByReceiverUsernameOrSenderUsername(String receiverUsername, String senderUsername);
    List<PrivateMessage> findPrivateMessagesByReceiverUsernameAndSenderUsername(String receiverUsername, String senderUsername);

    @Query("SELECT p FROM PrivateMessage p WHERE p.receiverUsername=?1 or p.senderUsername=?1")
    List<PrivateMessage> getAllPrivateMessages(String person);

    @Query(value = "SELECT p FROM PrivateMessage p WHERE (p.senderUsername=?1 or p.receiverUsername=?1) and (p.senderUsername=?2 or p.receiverUsername=?2) ORDER BY p.date DESC")
    List<PrivateMessage> getClosestPrivateMessages(String owner, String owner2);
}
