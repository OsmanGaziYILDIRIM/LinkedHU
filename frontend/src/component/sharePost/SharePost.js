import {Avatar, Snackbar} from '@mui/material'
import React, { useState } from 'react'
import "./SharePost.css"
import EventIcon from '@mui/icons-material/Event';
import PhotoLibraryIcon from "@mui/icons-material/PhotoLibrary"
import profilePic from "../../asset/image/pp1.jpg"
import Button from '@mui/material/Button';
import {PostService} from "../../service/PostService"
import MuiAlert from "@mui/material/Alert";
import EventPostForm from "./EventPostForm";
import JobPostForm from "./JobPostForm";



function SharePost() {
    const [open, setOpen] = React.useState(false);
    const Alert = React.forwardRef(function Alert(props, ref) {
        return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
    });

    const [input, setInput] = useState("")
    const handleClick = () => {
        setOpen(true);

    };

    let handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpen(false);
    };

function handleSubmit() {
    if (input.length > 0) {
        console.log(localStorage.getItem("username"))
        PostService.createPost(localStorage.getItem("userId"),input).then(res => {
            window.location.reload();

        })
    }else{
        handleClick();
    }
}


return (
        <div className="messageSender">
            <Snackbar open={open} autoHideDuration={300} onConlose={handleClose}>
                <Alert onClose={handleClose} severity="error" sx={{ width: '100%' }}>
                    Post text cannot be empty!
                </Alert>
            </Snackbar>
            <div className="messageSender__top">
                <Avatar src={profilePic} />
                <form >
                    <input
                        value={input}
                        onChange={e => setInput(e.target.value)}
                        className="messageSender__input"
                        placeholder={`Start a post.`} />

                </form>
            </div>

            <div className="messageSender__bottom">
                <div className="messageSender__option">
                    <PhotoLibraryIcon style={{ color: "green" }} />
                    <h3>Photo/Video</h3>
                </div>

                <EventPostForm />
                <JobPostForm />

                <div className="submitButton">
                    <Button onClick={handleSubmit} variant="contained">Submit</Button>
                </div>

            </div>


        </div>
    )
}

export default SharePost
