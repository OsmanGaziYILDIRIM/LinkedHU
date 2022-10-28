package com.powerrangers.linkedhu.controller;
import org.springframework.web.bind.annotation.*;
import com.powerrangers.linkedhu.service.CommentService;
import com.powerrangers.linkedhu.entity.Comment;
import com.powerrangers.linkedhu.dto.CommentCreateDto;
import com.powerrangers.linkedhu.dto.CommentUpdateDto;
import com.powerrangers.linkedhu.responses.CommentResponse;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId,
                                                @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateDto request) {
        return commentService.createOneComment(request);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto request) {
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        commentService.deleteOneCommentById(commentId);
    }
}