package com.powerrangers.linkedhu.controller;


import com.powerrangers.linkedhu.dto.SearchCommentDTO;
import com.powerrangers.linkedhu.dto.SearchPostDTO;
import com.powerrangers.linkedhu.dto.SearchUserDTO;
import com.powerrangers.linkedhu.entity.Comment;
import com.powerrangers.linkedhu.service.SearchService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;


    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PreAuthorize("permitAll")
    @GetMapping("/user")
    public List<SearchUserDTO> searchUsers(@RequestParam(value = ("searchWord")) String searchWord) {
        return searchService.searchUser(searchWord);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/post")
    public List<SearchPostDTO> searchPosts(@RequestParam(value = ("searchWord")) String searchWord) {
        return searchService.searchPost(searchWord);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/comment")
    public List<SearchCommentDTO> searchComments(@RequestParam(value = ("searchWord")) String searchWord) {
        return searchService.searchComments(searchWord);
    }


}