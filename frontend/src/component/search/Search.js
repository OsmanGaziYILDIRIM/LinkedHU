import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import {Divider, ListItem, ListItemAvatar, ListItemText} from "@mui/material";
import {Avatar} from "@mui/material";
import Paper from "@mui/material/Paper";
import List from "@mui/material/List";
import {useEffect, useState} from "react";
import NotFound from "../error/NotFound";
import {SearchService} from "../../service/SearchService";
import {useParams} from "react-router-dom";

const defaultSearchResults = {
    users: [],
    posts: [],
    comments: []
}


export default function Search() {
    let { query } = useParams();

    const [searchResults, setSearchResults] = useState(defaultSearchResults)

    useEffect(() => {
        SearchService.searchUser(query).then(response => {
           setSearchResults({
               ...searchResults,
               users: response.data
           })
        })
        SearchService.searchPost(query).then(response => {
            setSearchResults({
                ...searchResults,
                posts: response.data
            })
        }).catch(error => {
            console.log(error)
        })
        SearchService.searchComment(query).then(response => {
            setSearchResults({
                ...searchResults,
                comments: response.data
            })
        })
    }, [])

    console.log(searchResults)

    const goToUserProfile = (user) => {
        window.location.href = '/profile/'+ user.id;
    }

    return (
        <Box flex={4} >
            <Box>
                <Typography variant="h4">Users Result</Typography>
                <Divider/>
                <Paper variant="outlined">
                    <List>
                        {searchResults.users.length === 0 ?
                            <NotFound/> :
                            searchResults.users.map(user => (
                                <ListItem
                                    key={user.name}
                                    >
                                    <ListItemAvatar>
                                        <Avatar onClick={() =>goToUserProfile(user)}></Avatar>
                                    </ListItemAvatar>
                                    <ListItemText primary={user.name + ' ' + user.surname}
                                                  secondary={user.username}>

                                    </ListItemText>
                                </ListItem>))}
                    </List>
                </Paper>
                <Typography variant="h4">Posts Result</Typography>
                <Divider/>
                <Paper variant="outlined">
                    <List>
                        {searchResults.posts.length === 0 ?
                            <NotFound/> :
                            searchResults.posts.map(post => (
                                <ListItem
                                    key={post.title}
                                >
                                    <ListItemText primary={post.title}
                                                  secondary={post.content}>

                                    </ListItemText>
                                </ListItem>))}
                    </List>
                </Paper>
                <Typography variant="h4">Comments Result</Typography>
                <Divider/>
                <Paper variant="outlined">
                    <List>
                        {searchResults.comments.length === 0 ?
                            <NotFound/> :
                            searchResults.comments.map(comment => (
                                <ListItem
                                    key={comment.content}
                                >
                                    <ListItemText primary={comment.content}
                                                  secondary={comment.post.title}>

                                    </ListItemText>
                                </ListItem>))}
                    </List>
                </Paper>
            </Box>
        </Box>
    )
}