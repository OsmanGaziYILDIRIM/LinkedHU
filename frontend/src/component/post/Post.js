import Avatar from '@mui/material/Avatar';
import React, {useEffect, useState} from 'react'
import "./Post.css"
import ThumbUpIcon from "@mui/icons-material/ThumbUp"
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline"
import NearMeIcon from "@mui/icons-material/NearMe"
import {ExpandMoreOutlined} from "@mui/icons-material"
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Button from '@mui/material/Button';
import ReactTimeAgo from 'react-time-ago'
import en from 'javascript-time-ago/locale/en.json'
import TimeAgo from 'javascript-time-ago'
import {PostService} from "../../service/PostService";
import JobPost from './JobPost'
import JobPostForm from "../sharePost/JobPostForm";
import WorkIcon from "@mui/icons-material/Work";
import EventPost from "./EventPost";
import Feed from "../feed/Feed";
import Input from "@mui/material/Input";
import SendIcon from "@mui/icons-material/Send";
import Box from "@mui/material/Box";
import {Card, Divider} from "@mui/material";
import Typography from "@mui/material/Typography";
import {UserService} from "../../service/UserService";
import profilePic from "../../asset/image/pp1.png";
import EventPostForm from "../sharePost/EventPostForm";


TimeAgo.addDefaultLocale(en)

function Post({
                  profilePic, username, timestamp, message, userId, postId, fullName, email, postType, workType,
                  jobTitle, jobDescription, jobLocation, jobCompany, JobSalary, currency,
                  eventTitle, eventDescription, eventLocation, eventDate, eventLink,
              }) {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const [goToUser, setGoToUser] = useState();
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    const goToUserProfile = () => {
        window.location.href = '/profile/' + userId;
    }
    const deletePost = () => {
        if ("" + userId === localStorage.getItem('userId')) {
            PostService.deletePost(postId).then(() => {
                window.location.reload();
            })
        }

    }
    // get comments
    const [comments, setComments] = useState([]);
    useEffect(() => {
        PostService.getPostComments(postId).then(response => {
            const userObject = response.data
            console.log(userObject)
            setComments(userObject);
        });
    }, []);

    const handleComment = (userId, postId, text) => {
        PostService.createComment(userId, postId, text).then(() => {
                window.location.reload();
            }
        )
    }
    const [comment, setComment] = useState('');
    const handleCommentChange = (event) => {
        setComment(event.target.value);
    }


    return (
        <div className="post">
            <div className="post__top">

                <Avatar onClick={goToUserProfile} src={profilePic}
                        className="post__avatar"/>

                <div className="post__topInfo">

                    <h3 className="username">{username}</h3>
                    <p className="username"><ReactTimeAgo date={timestamp - 61} locale="en-US"/></p>
                </div>
                {("" + userId === localStorage.getItem('userId')) ?
                    <div className="moreButton">
                        <Button
                            id="basic-button"
                            aria-controls={open ? 'basic-menu' : undefined}
                            aria-haspopup="true"
                            aria-expanded={open ? 'true' : undefined}
                            onClick={handleClick}
                        >
                            <ExpandMoreOutlined style={{color: "black"}}/>
                        </Button>
                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={open}
                            onClose={handleClose}
                            MenuListProps={{
                                'aria-labelledby': 'basic-button',
                            }}
                        >
                            <MenuItem onClick={deletePost}>Delete</MenuItem>
                            {postType === "JobPost" ?
                                <JobPostForm
                                    updateMode={true}
                                    postId={postId}
                                    postType={postType}
                                    positionName={jobTitle}
                                    companyName={jobCompany}
                                    jobDescription={jobDescription}
                                    location={jobLocation}
                                    salary={JobSalary}
                                    currency={currency}
                                    workType={workType}
                                />
                                :
                                <EventPostForm
                                    updateMode={true}
                                    postId={postId}
                                    postType={postType}
                                    eventTitle={eventTitle}
                                    liveEventDescription={eventDescription}
                                    location={jobLocation}
                                    liveEventLink={eventLink}
                                    eventDate={eventDate}

                                />}



                        </Menu>
                    </div>
                    :
                    <p></p>
                }
            </div>

            <div className="post__bottom">

                {(postType === "JobPost") ?
                    <JobPost
                        fullName={fullName}
                        email={email}
                        jobTitle={jobTitle}
                        jobDescription={jobDescription}
                        jobLocation={jobLocation}
                        jobCompany={jobCompany}
                        jobSalary={JobSalary}
                        currency={currency}
                        workType={workType}
                    />
                    :
                    (postType === "LiveEventPost") ?
                        <EventPost
                            eventName={eventTitle}
                            eventLocation={eventLocation}
                            eventDescription={eventDescription}
                            eventDate={eventDate}
                            eventLink={eventLink}
                            eventLocation={jobLocation}
                        />
                        :
                        <p>{message}</p>

                }


            </div>

            <Divider variant="middle"/>
            <Box
                component="form"
                sx={{
                    '& > :not(style)': {m: 2},
                }}
                noValidate
                autoComplete="off"
            >
                <Input onChange={handleCommentChange} placeholder="Write Your Comment" sx={{width: "70%",}}/>
                <Button variant="contained"
                        onClick={() => handleComment(localStorage.getItem("userId"), postId, comment)}
                        endIcon={<SendIcon/>}>
                    Send
                </Button>
            </Box>
            {comments.map((commentt) => (
                <Card sx={{marginBottom: 2}}>
                    <Typography variant="h6" gutterBottom component="div" sx={{marginTop: 1, marginLeft: 2}}>
                        {commentt.name}
                    </Typography>

                    <Typography variant="subtitle1" gutterBottom component="div"
                                sx={{marginTop: 1, marginLeft: 2, marginRight: 2, display: "flex"}}>
                        {commentt.text}
                    </Typography>
                </Card>

            ))}


        </div>
    )
}


export default Post