package com.powerrangers.linkedhu.service;
import com.powerrangers.linkedhu.entity.Comment;
import com.powerrangers.linkedhu.entity.Post;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.CommentRepository;
import com.powerrangers.linkedhu.dto.CommentCreateDto;
import com.powerrangers.linkedhu.dto.CommentUpdateDto;
import com.powerrangers.linkedhu.responses.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;
    private InsultCheckService ıService;

    public CommentService(CommentRepository commentRepository, UserService userService,
                          PostService postService,InsultCheckService ıService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
        this.ıService=ıService;
    }

    public List<CommentResponse> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = commentRepository.findAll();
        return comments.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateDto request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(!ıService.checkInsult(request.getText())) {
            return null;
        }
        if(user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            commentToSave.setCreateDate(new Date());
            return commentRepository.save(commentToSave);
        }
        else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateDto request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }


}