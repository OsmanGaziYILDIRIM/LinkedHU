import React, {useEffect, useState} from "react";
import "./ProfileCard.css";
import avatar from "../../asset/image/pp1.jpg";
import {UserService} from "../../service/UserService";

function ProfileCard(props) {

    return (
        <div className="card-container">
            <div className="header1">
                <img src={avatar} alt={props.name} />
            </div>
            <h1 className="bold-text">
                {props.name}
            </h1>
            <h2 className="role-text">
                {props.title}
            </h2>
            <div>


            <h2 className="normal-text">{props.description}</h2>
            </div>
            <div className="social-container">
                <div className="followers">
                    <h1 className="bold-text">{props.posts}</h1>
                    <h2 className="smaller-text">Posts</h2>
                </div>
                {/*<div className="likes">*/}
                {/*    <h1 className="bold-text">{props.likes}</h1>*/}
                {/*    <h2 className="smaller-text">Likes</h2>*/}
                {/*</div>*/}

            </div>
        </div>
    );
}

export default ProfileCard;