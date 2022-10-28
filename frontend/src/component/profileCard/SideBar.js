import React, {useEffect, useState} from "react";
import {makeStyles} from "@mui/styles";

import ProfileCard from "./ProfileCard";
import {UserService} from "../../service/UserService";
import {PostService} from "../../service/PostService";

const Style = makeStyles((theme) => ({
    sidebar: {
        height: "100%",
        display: "flex",
        flexDirection: "column",

    },
}));

const userTitles = {
    1: "STUDENT",
    2: "GRADUATE",
    3: "ACADEMICIAN",
    4: "ADMIN",
};


const Sidebar = () => {
    const classes = Style();

    const userId = localStorage.getItem("userId");
    const [user, setUser] = useState([]);
    const [postSize, setPostSize] = useState([]);

    useEffect((e) => {
        PostService.getUserPosts(userId).then(response => {
            console.log(response.data);
            setPostSize(response.data);
        })
    }, [])

    useEffect(() => {
        UserService.getUserById(userId).then(response => {
            const userObject = response.data
            console.log(userObject);
            setUser(userObject);
        });
    }, []);

    return (
        <div className={classes.sidebar}>
            <ProfileCard
                name={user.name + " " + user.surname}
                title={userTitles[user.usertype]}
                description={user.text}
                posts={postSize}
                likes="54"

            />
        </div>
    );
};

export default Sidebar;
