package com.powerrangers.linkedhu.service;


import com.powerrangers.linkedhu.dto.SearchCommentDTO;
import com.powerrangers.linkedhu.dto.SearchPostDTO;
import com.powerrangers.linkedhu.dto.SearchUserDTO;
import com.powerrangers.linkedhu.entity.Comment;
import com.powerrangers.linkedhu.entity.JobPost;
import com.powerrangers.linkedhu.entity.LiveEventPost;
import com.powerrangers.linkedhu.entity.NormalPost;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    UserRepository userRepository;
    JobPostRepository jobPostRepository;
    LiveEventRepository liveEventRepository;
    CommentRepository commentRepository;
    NormalPostRepository normalPostRepository;

    public SearchService(UserRepository userRepository, JobPostRepository jobPostRepository
            , LiveEventRepository liveEventRepository, CommentRepository commentRepository,NormalPostRepository normalPostRepository) {
        this.userRepository = userRepository;
        this.jobPostRepository = jobPostRepository;
        this.liveEventRepository = liveEventRepository;
        this.commentRepository = commentRepository;
        this.normalPostRepository= normalPostRepository;
    }

    public List<SearchUserDTO> searchUser(String searchWord) {
        List<User> userList = userRepository.getSearchUsers(searchWord);
        List<SearchUserDTO> searchDTOList = new ArrayList<>();
        for (User u : userList) {
            SearchUserDTO searchUser = new SearchUserDTO();
            searchUser.setId(u.getId());
            searchUser.setUsername(u.getUsername());
            searchUser.setName(u.getName());
            searchUser.setSurname(u.getSurname());
            searchUser.setAvatarID(u.getAvatarID());
            searchDTOList.add(searchUser);

        }
        return searchDTOList;
    }

    public List<SearchPostDTO> searchPost(String searchWord) {
        List<JobPost> jobPostList = jobPostRepository.getSearchPosts(searchWord);
        List<LiveEventPost> liveEventPostList = liveEventRepository.getSearchPosts(searchWord);
        List<NormalPost> normalPostList=normalPostRepository.getSearchPosts(searchWord);

        List<SearchPostDTO> searchPostDTOList = new ArrayList<>();
        for (JobPost j : jobPostList) {
            SearchPostDTO searchPostDTO = new SearchPostDTO();
            searchPostDTO.setPostId(j.getId());
            searchPostDTO.setText(j.getJobDescription());
            searchPostDTO.setUserId(j.getUser().getId());
            searchPostDTOList.add(searchPostDTO);
        }

        for (LiveEventPost l : liveEventPostList) {
            SearchPostDTO searchPostDTO = new SearchPostDTO();
            searchPostDTO.setPostId(l.getId());
            searchPostDTO.setText(l.getLiveEventDescription());
            searchPostDTO.setUserId(l.getUser().getId());
        }
        for(NormalPost n:normalPostList){
            SearchPostDTO searchPostDTO=new SearchPostDTO();
            searchPostDTO.setPostId(n.getId());
            searchPostDTO.setText(n.getText());
            searchPostDTO.setUserId(n.getUser().getId());
            searchPostDTOList.add(searchPostDTO);
        }

        return searchPostDTOList;
    }

    public List<SearchCommentDTO> searchComments(String searchWord) {
        List<Comment> commentList = commentRepository.getSearchComments(searchWord);
        List<SearchCommentDTO> searchCommentDTOList=new ArrayList<>();
        for(Comment c:commentList){
            SearchCommentDTO searchCommentDTO=new SearchCommentDTO();
            searchCommentDTO.setText(c.getText());
            searchCommentDTO.setPostId(c.getPost().getId());
            searchCommentDTO.setUserId(c.getUser().getId());
            searchCommentDTOList.add(searchCommentDTO);
        }

        return searchCommentDTOList;

    }
}
