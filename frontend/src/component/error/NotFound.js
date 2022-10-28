import {Avatar, ListItem, ListItemAvatar, ListItemText} from "@mui/material";
import InfoIcon from '@mui/icons-material/Info';
import React from "react";

export default function NotFound () {
    return (
        <ListItem>
            <ListItemAvatar>
                <Avatar>
                    <InfoIcon />
                </Avatar>
            </ListItemAvatar>
            <ListItemText primary="Not found" />
        </ListItem>
    )
}
