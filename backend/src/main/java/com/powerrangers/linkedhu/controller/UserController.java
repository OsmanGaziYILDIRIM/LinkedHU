package com.powerrangers.linkedhu.controller;

import antlr.StringUtils;
import com.powerrangers.linkedhu.dto.*;
import com.powerrangers.linkedhu.entity.PrivateMessage;
import com.powerrangers.linkedhu.mapper.PrivateMessageMapper;
import com.powerrangers.linkedhu.service.UserService;
import com.sun.mail.smtp.SMTPAddressFailedException;
import lombok.RequiredArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PrivateMessageMapper privateMessageMapper;

    //GET USER "PUBLIC" INFORMATIONS METHOD FOR USERS
    @PreAuthorize("permitAll")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<ProfileResponseDTO> getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    //GET USER PRIVATE INFORMATIONS METHOD FOR ADMIN AND MEMBER'S OWN
    @PreAuthorize("permitAll")
    @GetMapping(value = "/set/{userId}")
    public ResponseEntity<SettingsResponseDTO> getUserSettings(@PathVariable Long userId) {
        return userService.getUserSettings(userId);
    }

    //SET USER (SETTINGS) INFORMATIONS METHOD FOR ADMIN AND MEMBER'S OWN
    @PreAuthorize("permitAll")
    @PostMapping("/update")
    public ResponseEntity<String> setUserSettings(@RequestBody SettingsRequestDTO profileRequest){
        return userService.updateOneUser(profileRequest);
    }

    //GET OWNED PRIVATE MESSAGE HISTORY ON A CHAT CHANNEL FROM DATABASE
    @PreAuthorize("permitAll")
    @GetMapping(value = "/messenger/{userId}")
    public List<PrivateMessageDTO> getPrivateMessages(@Valid @PathVariable Long userId) {
        return userService.getAllMessagesOfUser(userId);
    }

    //GET CLOSEST CHAT INFORMATIONS FROM DATABASE
    @PreAuthorize("permitAll")
    @GetMapping(value = "/messenger/closest")
    public List<CloserChatDTO> getClosestChat() {
        return userService.getClosestChat();
    }

    //BLOCK A SPESIFIC USER METHOD FOR USERS
    @PreAuthorize("permitAll")
    @PostMapping(value = "/block/{userId}")
    public ResponseEntity<String> applyBlockUser(@Valid @PathVariable Long userId) {
        return userService.blockUser(userId);
    }

    //UNBLOCK A SPESIFIC USER METHOD FOR USERS
    @PreAuthorize("permitAll")
    @PostMapping(value = "/unblock/{userId}")
    public ResponseEntity<String> applyUnBlockUser(@Valid @PathVariable Long userId) {
        return userService.unBlockUser(userId);
    }

    //SEND AN EMAIL TO USERS (BULK) METHOD FOR USERS
    @PreAuthorize("permitAll")
    @PostMapping(value = "/email")
    public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO){
        return userService.sendEmailInBulk(mailDTO);
    }
}
