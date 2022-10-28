import axios from "axios";
import {
    AUTH,
    BASE_URL,
    POSTS,
    COMMENTS
} from "../api_config";

const getAllPosts = () => {
    return axios.get(BASE_URL+POSTS,{});
};

const createPost = (userId, text) => {
    return axios.post(BASE_URL+POSTS,{ userId: userId, text: text, title: "title", postType: "NormalPost" });

};
const createJobPost = (userId, jobDescription, positionName, location, companyName, salary, currency, workType) => {
    return axios.post(BASE_URL+POSTS,{ userId: userId, jobDescription: jobDescription, positionName: positionName,
        location: location, companyName: companyName, salary: salary, currency: currency, workType: workType,
        postType: "JobPost"});
};

const createEventPost = (userId, liveEventLink, liveEventDescription, eventTitle, eventDate, location ) => {
    return axios.post(BASE_URL+POSTS,{ userId: userId, liveEventLink: liveEventLink, liveEventDescription: liveEventDescription,
        eventTitle: eventTitle, eventDate: eventDate, postType: "LiveEventPost", location: location});

};
const updateJobPost=(postId, postType, userId, positionName, location, companyName, salary, currency, workType, jobDescription,)=>{
    return axios.put(BASE_URL+POSTS+"/"+postId,{ postType: postType, userId: userId, positionName: positionName,
        location: location, companyName: companyName, salary: salary, currency: currency, workType: workType,
        jobDescription: jobDescription});

}
const updateEventPost=(postId, postType, userId, location, liveEventLink, liveEventDescription, eventTitle, eventDate)=>{
    return axios.put(BASE_URL+POSTS+"/"+postId,{ postType: postType, userId: userId,
        location: location, liveEventLink: liveEventLink, liveEventDescription: liveEventDescription,
        eventTitle: eventTitle, eventDate: eventDate});

}
const getUserPosts = (userId) => {
    return axios.get(BASE_URL+POSTS+"/user/"+userId,{});
};

const deletePost= (postId) => {
    return axios.delete(BASE_URL+POSTS+"/"+postId,{});
};

const getPostComments = (postId) => {
    return axios.get(BASE_URL+COMMENTS+"?postId="+postId, {});
};
const createComment = (userId, postId, text) => {
    return axios.post(BASE_URL+COMMENTS,{ userId: userId, postId: postId, text: text });
};
export const PostService = {getAllPosts,createPost, getUserPosts, deletePost, createEventPost, createJobPost,
    updateJobPost,updateEventPost, getPostComments, createComment};