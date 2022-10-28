package com.powerrangers.linkedhu.controller;


import org.springframework.web.bind.annotation.*;
import com.powerrangers.linkedhu.service.PostService;
import com.powerrangers.linkedhu.entity.Post;
import com.powerrangers.linkedhu.dto.PostCreateDto;
import com.powerrangers.linkedhu.dto.ApplicationDTO;
import com.powerrangers.linkedhu.service.JobAppService;
import com.powerrangers.linkedhu.dto.PostUpdateDto;
import com.powerrangers.linkedhu.responses.PostResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;
    private JobAppService appService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public void createOnePost(@RequestBody PostCreateDto postDto) {
             postService.createOnePost(postDto);
    }


    @PutMapping("/{postId}")
    public void updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateDto updatePost) {
        postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
    
    @GetMapping("/user/{userId}")
    public Integer getOneUserAllPosts(@PathVariable Long userId) {
        return postService.getOneUserAllPosts(userId);
        }

}
