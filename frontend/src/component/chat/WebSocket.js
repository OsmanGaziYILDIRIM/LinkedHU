
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

function sendMessage({sender, reciever, message}) {

    stompClient.send("/app/chat", {},
        JSON.stringify({'senderUsername':`${sender}`,'receiverUsername' : `${reciever}` , 'content':`${message}`}));
}

function handleReceivedMessage(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message.senderUsername + ": " + message.content));
    response.appendChild(p);
}

// export default function WebSocket() {
//     return(
//         <div onLoad={disconnect}>
//             <div>
//                 <input type="text" id="from" placeholder="Kullanici Adi"/>
//             </div>
//             <br/>
//             <div>
//                 <button id="connect" onClick={connect}>Baglan</button>
//                 <button id="disconnect" disabled="disabled" onClick={disconnect}>
//                     Cikis
//                 </button>
//             </div>
//             <br/>
//             <div id="conversationDiv">
//                 <input type="text" id="text" placeholder="Mesaj.."/>
//                 <button id="sendMessage" onClick={sendMessage}>Send</button>
//                 <p id="response"></p>
//             </div>
//         </div>
//     )
// }

export {setConnected, connect, disconnect, sendMessage,handleReceivedMessage};