import React, {useEffect} from 'react';
import {makeStyles} from '@mui/styles';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import {MoreHoriz} from "@mui/icons-material";
import PlayCircleFilled from '@mui/icons-material/PlayCircleFilled';
import {Column, Row, Item} from '@mui-treasury/components/flex';
import {
    Info,
    InfoTitle,
    InfoSubtitle,
    InfoCaption,
} from '@mui-treasury/components/info';
import {useGrowAvatarStyles} from '@mui-treasury/styles/avatar/grow';
import {useMusicInfoStyles} from '@mui-treasury/styles/info/music';
import ChatListItem from "./ChatListItem";
import Box from "@mui/material/Box";
import {PostService} from "../../service/PostService";
import ChatService from "../../service/ChatService";
import Post from "../post/Post";
import profilePic from "../../asset/image/pp1.png";

const useStyles = makeStyles(() => ({
    text: {
        display: 'flex',
        alignItems: 'center',
        '& > svg': {
            fontSize: 18,
            color: '#888',
            marginRight: 4,
        },
    },
}));

export const ChatList = React.memo(function MusicListItem() {
    const commonProps = {
        blur: '12px',
        radius: 16,
        size: 48,
        opacity: 0.6,
    };
    const src =
        'https://www.personality-insights.com/wp-content/uploads/2017/12/default-profile-pic-e1513291410505.jpg';
    const avatarStyles = useGrowAvatarStyles({src, ...commonProps});
    const src2 =
        'https://www.personality-insights.com/wp-content/uploads/2017/12/default-profile-pic-e1513291410505.jpg';
    const avatarStyles2 = useGrowAvatarStyles({src: src2, ...commonProps});
    const styles = useStyles();

    const [chatList, setChatList] = React.useState([]);
    useEffect((e) => {
        ChatService.getChatList().then(response => {
            console.log(response.data);
            setChatList(response.data);
        })
    }, [])


    return (
        <>

            <Column gap={2} width={'100%'}>

                {
                    chatList.map((item) => (
                        <ChatListItem
                            fullName={item.fullname}
                            lastMessage={item.lastmessage}
                            userId={item.userId}
                            date={item.date}
                        />
                    ))
                }

            </Column>
        </>
    );
});