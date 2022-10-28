import {Card, Divider, List, ListItem, ListItemAvatar, ListItemText, Skeleton, Stack} from "@mui/material";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import BeachAccessIcon from "@mui/icons-material/BeachAccess";
import './AboutProfile.css';
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import SettingsIcon from '@mui/icons-material/Settings';
import ChatIcon from '@mui/icons-material/Chat';
import React, {useEffect, useState} from 'react';
import {UserService} from "../../service/UserService";
import {useParams} from "react-router-dom";


export default function AboutProfile() {
    const userId = useParams();
    const [user, setUser] = useState([]);


    useEffect(() => {
        UserService.getUserById(userId.userid).then(response => {
            const userObject = response.data
            setUser(userObject);
        });
    }, []);

    console.log(user);

    const customDivider = (dividerName) => (
        <div>
            <Divider  component="li" />
                <li>
                    <Typography
                        sx={{ mt: 1, ml: 1 }}
                        color="text.secondary"
                        display="block"
                        variant="caption"
                    >
                        {dividerName}
                    </Typography>
                </li>
        </div>
    )

    const listItem = (name) => (
        <ListItem>
            <ListItemText primary={name}/>
        </ListItem>
    )

    const selectItem = (number, dict) => (
        <ListItem>
            <ListItemText primary={dict[number]}/>
        </ListItem>
    )

    const genderDict = {
        1 : 'Male',
        2 : 'Female',
        3 : 'Other'
    }

    const departmentDict = {
        1 : 'Computer Engineering',
        2 : 'Artificial Intelligence Engineering'
    }

    const degreeDict = {
        1 : "Bachelor's",
        2 : 'Master',
        3 : 'PhD',
        4 : 'Exchange'
    }

    const isAuthenticatedToUpdate = (user) => {
        if (user.username === localStorage.getItem("username")) {
            return (
                <Button href={"/profile/" + userId.userid + "/settings"}
                        style={{right:-50}}
                        variant="contained"
                        startIcon={<SettingsIcon />}
                >
                    Set
                </Button>
            )
        } else {
            return (
                <div>
                </div>
            )
        }
    }

    const messageButton = (user) => {
        if (user.username !== localStorage.getItem("username")) {
            return (
                <Button
                    variant="contained" startIcon={<ChatIcon />}
                    href={"/chat/" } marginright={2}
                >
                    Message
                </Button>
            )
        } else {
            return (
                <div>
                </div>
            )
        }
    }

    if (user.usertype === 1) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                        {customDivider('Name')}
                        <ListItem>
                            <ListItemAvatar>
                                <Avatar>
                                    <BeachAccessIcon/>
                                </Avatar>
                            </ListItemAvatar>
                            <ListItemText primary={user.name + " " + user.surname}/>
                            {messageButton(user)}
                        </ListItem>

                        {customDivider('About me')}
                        {listItem(user.text)}
                        {customDivider('Department')}
                        {selectItem(user.department, departmentDict)}
                        {customDivider('Degree')}
                        {selectItem(user.degree, degreeDict)}
                        {customDivider('Graduation Year')}
                        {listItem(user.graduate_year)}
                        {customDivider('Email')}
                        {listItem(user.username)}
                        {customDivider('Phone')}
                        {listItem(user.phoneNumber)}
                        {customDivider('Gender')}
                        {selectItem(user.gender, genderDict)}
                    </List>
                    {isAuthenticatedToUpdate(user)}
                </Card>
            </Grid>
        )
    } else if (user.usertype === 2) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                        {customDivider('Name')}
                        <ListItem>
                            <ListItemAvatar>
                                <Avatar>
                                    <BeachAccessIcon/>
                                </Avatar>
                            </ListItemAvatar>
                            <ListItemText primary={user.name + " " + user.surname}/>
                            {messageButton(user)}
                        </ListItem>

                        {customDivider('About me')}
                        {listItem(user.text)}
                        {customDivider('Graduation Year')}
                        {listItem(user.graduate_year)}
                        {customDivider('Email')}
                        {listItem(user.username)}
                        {customDivider('Phone')}
                        {listItem(user.phoneNumber)}
                        {customDivider('Gender')}
                        {selectItem(user.gender, genderDict)}
                    </List>
                    {isAuthenticatedToUpdate(user)}
                </Card>
            </Grid>
        )
    } else if (user.usertype === 3) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
                        {customDivider('Name')}
                        <ListItem>
                            <ListItemAvatar>
                                <Avatar>
                                    <BeachAccessIcon/>
                                </Avatar>
                            </ListItemAvatar>
                            <ListItemText primary={user.name + " " + user.surname}/>
                            {messageButton(user)}
                        </ListItem>

                        {customDivider('About me')}
                        {listItem(user.text)}
                        {customDivider('Email')}
                        {listItem(user.username)}
                        {customDivider('Phone')}
                        {listItem(user.phoneNumber)}
                        {customDivider('Gender')}
                        {selectItem(user.gender, genderDict)}
                    </List>
                    {isAuthenticatedToUpdate(user)}
                </Card>
            </Grid>

        )
    } else if (user.usertype === 4) {
        return (
            <h1>Prohibited User</h1>
        )
    } else if (user.usertype === undefined) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Stack direction={'column'}>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="rect" width={360} height={200}/>
                </Stack>
            </Grid>
        )
    }
}