import React, { useEffect, useState } from 'react'
import Post from "../post/Post";
import JobPost from "../post/JobPost";
import EventPost from "../post/EventPost";
import {PostService} from "../../service/PostService";
import profilePic from "../../asset/image/pp1.jpg"
import {UserService} from "../../service/UserService";


function Feed() {
    const [fullName, setFullName] = useState("");
    const [email, setEmail] = useState("");
    const [posts, setPosts] = useState([]);


    useEffect((e) => {
        PostService.getAllPosts().then(response => {
            console.log(response);
            let postArray = response.data
            setPosts(postArray.reverse());
        })
    }, [])
    console.log(posts);

    const [user, setUser] = useState([]);
    let userId = localStorage.getItem("userId");
    useEffect(() => {
        UserService.getUserById(userId).then(response => {
            const userObject = response.data
            setFullName(userObject.name + " " + userObject.surname)
            setEmail(userObject.username)
            setUser(userObject);
        });
    }, []);

    return (
        <div className="feed">

            {posts.map((post) => (

                <Post
                    profilePic={profilePic}
                    message={post.text}
                    timestamp={Date.parse(post.date)}
                    username={post.name}
                    userId={post.userId}
                    postId={post.id}
                    fullName={fullName}
                    email={email}
                    postType={post.postType}
                    jobTitle={post.positionName}
                    jobDescription={post.jobDescription}
                    jobLocation={post.location}
                    jobCompany={post.companyName}
                    JobSalary={post.salary}
                    workType={post.workType}
                    currency={post.currency}
                    eventTitle={post.eventTitle}
                    eventDescription={post.liveEventDescription}
                    eventLocation={post.eventLocation}
                    eventDate={post.eventDate}
                    eventLink={post.liveEventLink}
                />
            ))}

        </div>
    )
}

export default Feed