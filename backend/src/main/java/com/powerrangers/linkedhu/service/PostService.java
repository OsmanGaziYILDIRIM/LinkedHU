package com.powerrangers.linkedhu.service;

import com.powerrangers.linkedhu.entity.JobPost;
import com.powerrangers.linkedhu.entity.LiveEventPost;
import com.powerrangers.linkedhu.entity.Post;
import com.powerrangers.linkedhu.entity.NormalPost;
import com.powerrangers.linkedhu.entity.JobApp;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.repository.PostRepository;
import com.powerrangers.linkedhu.repository.JobPostRepository;
import com.powerrangers.linkedhu.repository.JobAppRepository;
import com.powerrangers.linkedhu.repository.LiveEventRepository;
import com.powerrangers.linkedhu.repository.NormalPostRepository;
import com.powerrangers.linkedhu.dto.PostCreateDto;
import com.powerrangers.linkedhu.dto.PostUpdateDto;
import com.powerrangers.linkedhu.dto.AppListDTO;
import com.powerrangers.linkedhu.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.List;


@Service
public class PostService {

    private PostRepository postRepository;
    private JobPostRepository jPostRepository;
    private LiveEventRepository lEventPostRepo;
    private NormalPostRepository nPostRepository;
    private JobAppRepository jobAppRepository;
  
    private UserService userService;

    public PostService(PostRepository postRepository,
                       UserService userService, JobPostRepository j, LiveEventRepository l,NormalPostRepository nPostRepository, JobAppRepository jobAppRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.jPostRepository=j;
        this.lEventPostRepo=l;
        this.nPostRepository=nPostRepository;
        this.jobAppRepository=jobAppRepository;
    }

    public static void sort(List<PostResponse> list)
    {

        list.sort((o1, o2)
                -> o1.getDate().compareTo(
                o2.getDate()));
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<JobPost> list;
        List<LiveEventPost> list2;
        List<NormalPost> list3;

        List<PostResponse> postList = new ArrayList<PostResponse>();
        if (userId.isPresent()) {
            list = jPostRepository.findByUserId(userId.get());
            list2 = lEventPostRepo.findByUserId(userId.get());
            list3 = nPostRepository.findByUserId(userId.get());
        } else {
            list = jPostRepository.findAll();
            list2 = lEventPostRepo.findAll();
            list3=nPostRepository.findAll();
        }


        for(int i =0 ; i<list.size() ; i++) {
            JobPost j = list.get(i);
            PostResponse p = new PostResponse();
            p.setJPResponse(j.getId(),j.getUser().getId(),j.getUser().getName()+" "+j.getUser().getSurname(),
                    j.getLastDate(),j.getPostType(),j.getPositionName(),j.getLocation(),j.getCompanyName(),j.getSalary(),j.getCurrency(),
                    j.getWorkType(),j.getJobDescription(),j.getJobTitle());
            postList.add(p);
        }
        for(int i=0 ; i < list2.size() ; i++) {
            LiveEventPost l = list2.get(i);
            PostResponse p = new PostResponse();
            p.setLEMResponse(l.getId(),l.getUser().getId(),l.getUser().getName()+" "+l.getUser().getSurname(),
                    l.getLastDate(),l.getPostType(),l.getLiveEventLink(),l.getLiveEventDescription(),l.getEventTitle(),l.getEventDate(),l.getLocation());
            postList.add(p);

        }
        for(int i=0 ; i < list3.size() ; i++) {
            NormalPost l = list3.get(i);
            PostResponse p = new PostResponse();
            p.setNMResponse(l.getId(),l.getUser().getId(),l.getUser().getName()+" "+l.getUser().getSurname(),
                    l.getLastDate(),l.getPostType(),l.getTitle(),l.getText());
            postList.add(p);

        }
        sort(postList);

        return postList;
    }

    public Post getOnePostById(Long Id) {
        JobPost j = jPostRepository.findById(Id).orElse(null);
        LiveEventPost l = lEventPostRepo.findById(Id).orElse(null);
        NormalPost n=nPostRepository.findById(Id).orElse(null);
        if(j!=null)
            return j;

        else if(l!=null)
            return l;

        else if(n!=null)
            return n;
        else
            return null;
    }


    public void createOnePost(PostCreateDto dtoObj) {
        try {
            if(dtoObj.getPostType().equals("JobPost")) {

            User user = userService.getOneUser(dtoObj.getUserId());
            if (user == null)
                return ;

            JobPost post = new JobPost();
            post.setUser(user);

            post.setLastDate(new Date());
            post.setCurrency(dtoObj.getCurrency());
            post.setLocation(dtoObj.getLocation());
            post.setPositionName(dtoObj.getPositionName());
            post.setPostType(dtoObj.getPostType());
            post.setSalary(dtoObj.getSalary());
            post.setWorkType(dtoObj.getWorkType());
            post.setJobDescription(dtoObj.getJobDescription());
            post.setCompanyName(dtoObj.getCompanyName());
            post.setJobTitle(dtoObj.getJobTitle());
            jPostRepository.save(post);

            }
            else if(dtoObj.getPostType().equals("LiveEventPost")) {
                User user = userService.getOneUser(dtoObj.getUserId());
                if (user == null)
                    return ;
                LiveEventPost post = new LiveEventPost();
                post.setUser(user);
                post.setLastDate(new Date());
                post.setEventDate(dtoObj.getEventDate());
                post.setLocation(dtoObj.getLocation());
                post.setPostType(dtoObj.getPostType());
                post.setEventTitle(dtoObj.getEventTitle());
                post.setLiveEventLink(dtoObj.getLiveEventLink());
                post.setLiveEventDescription(dtoObj.getLiveEventDescription());

                lEventPostRepo.save(post);
            }
            else if(dtoObj.getPostType().equals("NormalPost")) {
                User user = userService.getOneUser(dtoObj.getUserId());
                if (user == null)
                    return ;
                NormalPost post = new NormalPost();
                post.setUser(user);
                post.setLastDate(new Date());
                post.setText(dtoObj.getText());
                post.setPostType(dtoObj.getPostType());
                post.setTitle(dtoObj.getTitle());
                nPostRepository.save(post);
            }

            
        }
       catch(Exception e) {
            System.out.println("getAuthenticatedUsername user not found!");
            return;
        }
    }
    

    public void updateOnePostById(Long postId,  PostUpdateDto updateDto) {
        if(updateDto.getPostType().equals("JobPost")) {
            Optional<JobPost> post = jPostRepository.findById(postId);

            if(post.isPresent()) {
                JobPost updatePost = post.get();
                updatePost.setJobTitle(updateDto.getJobTitle());
                updatePost.setSalary(updateDto.getSalary());
                updatePost.setPositionName(updateDto.getPositionName());
                updatePost.setJobDescription(updateDto.getJobDescription());
                updatePost.setWorkType(updateDto.getWorkType());
                updatePost.setCurrency(updateDto.getCurrency());
                updatePost.setLocation(updateDto.getLocation());
                updatePost.setCompanyName(updateDto.getCompanyName());
                updatePost.setLastDate(new Date());
                jPostRepository.save(updatePost);
                return ;
            }
        }
        else if(updateDto.getPostType().equals("LiveEventPost")) {
                Optional<LiveEventPost> post = lEventPostRepo.findById(postId);
            if (post.isPresent()) {
                LiveEventPost updatePost = post.get();
                updatePost.setLiveEventDescription(updateDto.getLiveEventDescription());
                updatePost.setEventTitle(updateDto.getEventTitle());
                updatePost.setEventDate(updateDto.getEventDate());
                updatePost.setLiveEventLink(updateDto.getLiveEventLink());
                updatePost.setLocation(updateDto.getLocation());
                updatePost.setLastDate(new Date());
                lEventPostRepo.save(updatePost);
                return ;
            }

        }
        else if(updateDto.getPostType().equals("NormalPost")) {
            Optional<NormalPost> post = nPostRepository.findById(postId);
            if (post.isPresent()) {
                NormalPost updatePost = post.get();
                updatePost.setTitle(updateDto.getTitle());
                updatePost.setText(updateDto.getText());
                updatePost.setLastDate(new Date());
                nPostRepository.save(updatePost);
                return;
            }
        }
        return ;
    }

    public void deleteOnePostById(Long postId) {
        JobPost j = jPostRepository.findById(postId).orElse(null);
        LiveEventPost l = lEventPostRepo.findById(postId).orElse(null);
        NormalPost n=nPostRepository.findById(postId).orElse(null);
        if(j!=null)
            jPostRepository.delete(j);

        else if(l!=null)
            lEventPostRepo.delete(l);

        else if(n!=null)
            nPostRepository.delete(n);
    }
    
    public Integer getOneUserAllPosts( Long userId) {
        return jPostRepository.findByUserId(userId).size()+lEventPostRepo.findByUserId(userId).size()+nPostRepository.findByUserId(userId).size();
    }

    public ArrayList<JobApp> getAppList(AppListDTO appListDTO) {
        JobPost post = new JobPost();
        ArrayList<JobApp> liste=new ArrayList<JobApp>();

        post=jPostRepository.findById(appListDTO.getPostId()).get();
        List<Long> list= post.getUserApp();
        for(int i = 0 ; i< list.size(); i++) {
            liste.add(jobAppRepository.findById(list.get(i)).get());
        }
    return liste;
    }
}
