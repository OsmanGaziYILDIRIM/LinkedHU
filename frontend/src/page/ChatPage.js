import React, {useEffect} from "react";
import ChatMsg from "../component/chat/Chat";
import Grid from "@mui/material/Grid";
import {Card, Divider} from "@mui/material";
import Box from '@mui/material/Box';
import Input from '@mui/material/Input';
import Button from "@mui/material/Button";
import SendIcon from '@mui/icons-material/Send';
import {ChatList} from "../component/chatList/ChatList";
import ChatService from "../service/ChatService";
import ChatListItem from "../component/chatList/ChatListItem";

var stompClient = null;
var baseAddress = 'http://localhost:8080';
var Stomp = require('stompjs');
var SockJS = require('sockjs-client');

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS(baseAddress + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic', function (message) {
            handleReceivedMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage(sender, reciever, message) {
    stompClient.send("/app/chat", {},
        JSON.stringify({'senderUsername':sender,'receiverUsername' : reciever , 'content':message}));
}

function handleReceivedMessage(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message.senderUsername + ": " + message.content));
    response.appendChild(p);
}
function getChatScreen({receiverId, message}){
    console.log(message)
    if (receiverId===localStorage.getItem("username")) {
        return(
            <ChatMsg
                side={'right'}
                messages={[
                    `${message}`
                ]}
            />
        )
    }
    else {
        return(
            <ChatMsg
                avatar={''}
                messages={[
                    `${message}`
                ]}
            />
        )
    }
}


export default function ChatPage() {
    const [chatList, setChatList] = React.useState([]);
    useEffect((e) => {
        ChatService.getChatList().then(response => {
            console.log(response.data);
            setChatList(response.data);
        })
    }, [])

    const [message, setMessage] = React.useState("");

    function handleSendMessage(event) {
        event.preventDefault();

        sendMessage(localStorage.getItem("username"),"osman@gmail.com",message);

        window.location.reload();

    }
    const [chat, setChat] = React.useState([]);
    useEffect((e) => {
        ChatService.getMessage(2).then(response => {
            console.log(response.data);
            setChat(response.data.reverse());
        })
    }, [])


    const handleMessageChange = (e) => {
        setMessage(e.target.value);
    }
    connect();
    return (

        <Grid container spacing={1} justifyContent="center" alignItems="center" sx={{marginTop:3 }}>
            <Grid item xs={12} sm={3}>
                <Card sx={{height:550, boxShadow: "0px 5px 5px hsl(180, 3%, 45%)"}}>
                    <ChatList/>
                </Card>
            </Grid>
            <Grid item xs={12} sm={6} onLoad={connect}>
                <Card sx={{height:550, display:"flex", flexDirection:"column-reverse", overflowY:"auto", boxShadow: "5px 5px 5px hsl(180, 3%, 45%)"}}>
                    <Box
                        component="form"
                        sx={{
                            '& > :not(style)': { m: 2},
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <Input onChange={handleMessageChange} placeholder="Placeholder" sx={{ width:"70%",}}/>
                        <Button variant="contained" onClick={handleSendMessage} endIcon={<SendIcon />}>
                            Send
                        </Button>
                    </Box>
                    <Divider variant="middle" />

                    {
                        chat.map((item) => (
                            getChatScreen({receiverId:item.senderUsername, message:item.content})
                        ))
                    }




                </Card>

            </Grid>
        </Grid>


    );
}
