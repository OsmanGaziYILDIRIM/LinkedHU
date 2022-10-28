package com.powerrangers.linkedhu.controller;

import org.springframework.web.bind.annotation.*;
import com.powerrangers.linkedhu.service.PostService;
import com.powerrangers.linkedhu.dto.ApplicationDTO;
import com.powerrangers.linkedhu.service.JobAppService;
import com.powerrangers.linkedhu.dto.AppListDTO;
import com.powerrangers.linkedhu.entity.JobApp;
import java.util.ArrayList;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/jobapp")
public class AppController {

    private PostService postService;
    private JobAppService appService;

    public AppController(PostService postService,JobAppService appService) {
        this.postService = postService; this.appService=appService;
    }

    @GetMapping
    public ArrayList<JobApp> getAppList(@RequestBody AppListDTO appListDTO) {
        System.out.println("getappliste girdi");
        return postService.getAppList(appListDTO);
    }

    @PostMapping
    public void makeAnJobApplication(@RequestBody ApplicationDTO appDto) {
        appService.makeAnApplication(appDto);

    }

}
