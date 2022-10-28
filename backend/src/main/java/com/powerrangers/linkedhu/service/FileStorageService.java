package com.powerrangers.linkedhu.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.powerrangers.linkedhu.entity.FileDB;
import com.powerrangers.linkedhu.entity.JobApp;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.FileDBRepository;
import com.powerrangers.linkedhu.repository.JobAppRepository;
import com.powerrangers.linkedhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NamingEnumeration;

@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobAppRepository jobAppRepository;

    public FileDB store(MultipartFile file,Long appID) throws IOException {
        User user = securityService.getAuthorizedMember();
        if (user==null){return null;}
        JobApp jobApp= new JobApp();
        jobApp=jobAppRepository.getById(appID);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user.getId());
        fileDBRepository.save(fileDB);
        jobApp.setFileId(fileDB.getId());
        jobAppRepository.save(jobApp);
        userRepository.save(user);
        return fileDB;
    }

    public FileDB storeAvatar(MultipartFile file) throws IOException {
        User user = securityService.getAuthorizedMember();

        if (user==null){return null;}
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user.getId());

        fileDBRepository.save(fileDB);
        user.setAvatarID(fileDB.getId());
        userRepository.save(user);
        return fileDB;
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


}