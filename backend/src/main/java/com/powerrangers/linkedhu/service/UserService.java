package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.dto.*;
import com.powerrangers.linkedhu.entity.PrivateMessage;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.mapper.PrivateMessageMapper;
import com.powerrangers.linkedhu.repository.BlockRepository;
import com.powerrangers.linkedhu.repository.PrivateMessageRepository;
import com.powerrangers.linkedhu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PrivateMessageRepository privateMessageRepository;
    private final SecurityService securityService;
    private final BlockRepository blockRepository;
    private final InsultCheckService insultCheckService;
    private final EmailSenderService emailSenderService;
    private final PrivateMessageMapper privateMessageMapper;

    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    //GET USER "PUBLIC" INFORMATIONS METHOD FOR USER'S
    public ResponseEntity<ProfileResponseDTO> getUserProfile(Long userId) {
        User authorized = securityService.getAuthorizedMember();
        if (authorized == null) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); }
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundUser = user.get();
            ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(foundUser.getUsertype(), foundUser.getUsername(),
                    foundUser.getName(), foundUser.getSurname(),
                    foundUser.getBirthdate(), foundUser.getGender(),
                    foundUser.getPhoneNumber(), foundUser.getDepartment(),
                    foundUser.getGraduate_year(), foundUser.getDegree(), foundUser.getText(),foundUser.getAvatarID(),
                    isBlockedToUser(userId, authorized.getId()));

            return new ResponseEntity<ProfileResponseDTO>(profileResponseDTO, HttpStatus.OK);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    //GET USER INFORMATIONS METHOD FOR ADMIN AND USER'S OWN
    public ResponseEntity<SettingsResponseDTO> getUserSettings(Long userId) {
        User authorizedMember = securityService.getAuthorizedMember();
        Optional<User> user = userRepository.findById(userId);
        if (authorizedMember == null) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); }
        if(authorizedMember.getId() == userId ||
                authorizedMember.isAdmin() == true) {
            if (user.isPresent()) {
                User foundUser = user.get();
                SettingsResponseDTO settingsResponseDTO = new SettingsResponseDTO(foundUser.getUsername(),
                        foundUser.getName(), foundUser.getSurname(),
                        foundUser.getBirthdate(), foundUser.getGender(),
                        foundUser.getPhoneNumber(), foundUser.getDepartment(), foundUser.getGraduate_year(), foundUser.getDegree(), foundUser.getText());

                return new ResponseEntity<SettingsResponseDTO>(settingsResponseDTO, HttpStatus.OK);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    //SET USER INFORMATIONS METHOD FOR ADMIN AND USER'S OWN
    public ResponseEntity<String> updateOneUser(SettingsRequestDTO profileRequest) {
        User authorizedMember = securityService.getAuthorizedMember();
        if ( authorizedMember == null) { return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error!! Reason: Forbidden method.");}
        if (authorizedMember.getId() == profileRequest.getUserId() ||
                authorizedMember.isAdmin() == true) {
            Optional<User> user = userRepository.findById(profileRequest.getUserId());
            if (!user.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: There is no user with this id.");
            }
            User foundUser = user.get();
            if (profileRequest.getPassword() != null) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                foundUser.setPassword(passwordEncoder.encode(profileRequest.getPassword()));
            }

            if (profileRequest.getBirthdate() != null) {
                foundUser.setBirthdate(profileRequest.getBirthdate());
            }

            if (profileRequest.getGender() != null) {
                foundUser.setGender(profileRequest.getGender());
            }

            if (profileRequest.getPhoneNumber() != null) {
                foundUser.setPhoneNumber(profileRequest.getPhoneNumber());
            }

            //@FirstName
            if (profileRequest.getName() != null) {
                foundUser.setName(profileRequest.getName());
            }

            if (profileRequest.getSurname() != null) {
                foundUser.setSurname(profileRequest.getSurname());
            }

            if (profileRequest.getText() != null) {
                foundUser.setText(profileRequest.getText());
            }

            if(profileRequest.getDepartment()!=null){
                foundUser.setDepartment(profileRequest.getDepartment());
            }

            if(profileRequest.getDegree()!=null){
                foundUser.setDegree(profileRequest.getDegree());
            }

            if(profileRequest.getGraduate_year()!=null){
                foundUser.setGraduate_year(profileRequest.getGraduate_year());
            }

            userRepository.save(foundUser);
            return ResponseEntity.ok("User is successfully updated.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error!! Reason: Forbidden method.");
    }

    public ResponseEntity<String> sendPrivateMessage(PrivateMessageDTO privateMessageDTO) {
        if (privateMessageDTO.getSenderUsername().equals(privateMessageDTO.getReceiverUsername())) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Sender == Receiver ??");};
        if (!userRepository.existsByUsername(privateMessageDTO.getSenderUsername()) ||
                !userRepository.existsByUsername(privateMessageDTO.getReceiverUsername())) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Sender or Receiver does not exist."); }
        if (userIsBlockedBySystem(privateMessageDTO.getSenderUsername())) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).
                body("Error!! Reason: Sender is blocked from the system."); }
        if (!insultCheckService.checkInsult(privateMessageDTO.getContent())) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).
                body("Error!! Reason: Content is containing insult words."); }
        Long senderId = userRepository.getUserId(privateMessageDTO.getSenderUsername());
        Long receiverId = userRepository.getUserId(privateMessageDTO.getReceiverUsername());
        if (isBlockedToUser(senderId, receiverId)) { return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).
                body("Error!! Reason: Sender has been blocked by the Receiver."); }
        PrivateMessage privateMessage = new PrivateMessage(privateMessageDTO.getSenderUsername(),
                privateMessageDTO.getReceiverUsername(), privateMessageDTO.getContent());
        privateMessageRepository.save(privateMessage);
        return ResponseEntity.ok("Message successfully sent.");
    }

    public boolean isBlockedToUser(Long blocked, Long by) {
        if (blocked == by) return false;
        return userRepository.getBlockedUsers(blocked, by) > 0; }

    public List<PrivateMessageDTO> getAllMessagesOfUser(Long userId) {
        String desiredUsername = this.userRepository.getUserNameById(userId);
        User authorizedUser = securityService.getAuthorizedMember();
        String username = authorizedUser.getUsername();
        if (authorizedUser == null) { return null; }
        List<PrivateMessage> list = privateMessageRepository.findPrivateMessagesByReceiverUsernameAndSenderUsername(username, desiredUsername);
        list.addAll(privateMessageRepository.findPrivateMessagesByReceiverUsernameAndSenderUsername(desiredUsername, username));
        return privateMessageMapper.mapToDto(list);
    }

    public boolean userIsBlockedBySystem(String username) {
        return blockRepository.existsByUsername(username);
    }

    public ResponseEntity<String> blockUser(Long userId) {
        User user = securityService.getAuthorizedMember();
        if (user == null) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Request does not belong anyone.");}
        if (userId == user.id) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Can not block yourself.");}
        Optional<User> optionalDestUser = userRepository.findById(userId);
        if (!optionalDestUser.isPresent()) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: There is no user with this userId.");}
        if (user.isBlockedUser(userId)) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: This user is already blocked by you.");}
        user.addBlockedUser(userId);
        userRepository.save(user);
        return ResponseEntity.ok("User is successfully BLOCKED by you.");
    }

    public ResponseEntity<String> unBlockUser(Long userId) {
        User user = securityService.getAuthorizedMember();
        if (user == null) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Request does not belong anyone.");}
        if (userId == user.id) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Can not unblock yourself.");}
        Optional<User> optionalDestUser = userRepository.findById(userId);
        if (!optionalDestUser.isPresent()) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: There is no user with this userId.");}
        if (!user.isBlockedUser(userId)) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: This user is not blocked by you.");}
        user.removeBlockedUser(userId);
        userRepository.save(user);
        return ResponseEntity.ok("User is successfully UNBLOCKED by you.");
    }

    public List<User> listAll() {
        return userRepository.findAll(Sort.by("username").ascending());
    }

    public ResponseEntity<String> sendEmailInBulk(MailDTO mailDTO){
        User user = securityService.getAuthorizedMember();
        if (user == null) { return ResponseEntity.
                status(HttpStatus.UNAUTHORIZED).body("Error!! Reason: Request does not belong anyone.");}
        if (user.isStudent() && !user.isStudentRepresentative()) { return ResponseEntity.
                status(HttpStatus.FORBIDDEN).body("Error!! Reason: You are not authorized to use this action.");
        }
        if (!insultCheckService.checkInsult(mailDTO.getSubject()) || !insultCheckService.checkInsult(mailDTO.getContent())) { return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).body("Error!! Reason: Content is containing insult words.");
        }
        List<User> userList = userRepository.getEnabledUsers();
        for (User iterate : userList) {
            emailSenderService.sendEmail(iterate.getUsername(),
                    mailDTO.getSubject(),
                    mailDTO.getContent());
        }
        return ResponseEntity.ok("Email is successfully sent in bulk.");
    }


    public List<CloserChatDTO> getClosestChat() {
        User user = securityService.getAuthorizedMember();
        if (user == null) { return null;}

        List<CloserChatDTO> chats = new ArrayList<>();
        List<PrivateMessage> partners = privateMessageRepository.getAllPrivateMessages(user.getUsername());

        List<String> partnerNames = new ArrayList<>();
        for(PrivateMessage p: partners) {
            if (p.getSenderUsername().equals(user.getUsername())) {
                if (!partnerNames.contains(p.getReceiverUsername()))
                    partnerNames.add(p.getReceiverUsername());
            }
            if (p.getReceiverUsername().equals(user.getUsername())) {
                if (!partnerNames.contains(p.getSenderUsername()))
                    partnerNames.add(p.getSenderUsername());
            }
        }

        for (int i=partnerNames.size() - 1; i >= 0; i--) {
            List<PrivateMessage> messages = privateMessageRepository.getClosestPrivateMessages(user.getUsername(), partnerNames.get(i));
            PrivateMessage oldestMessage = messages.get(0);
            User owner = userRepository.findByUsername(partnerNames.get(i));
            if (owner != null) {
                chats.add(new CloserChatDTO( owner.getId(),
                        owner.getName() +" "+ owner.getSurname(),
                        oldestMessage.getContent(),
                        oldestMessage.getDate()));
            }
        }
        return chats;
    }
}