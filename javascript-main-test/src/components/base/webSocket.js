/* 
Ref: https://developer.mozilla.org/zh-CN/docs/Web/API/WebSocket
Before: npm i ws
 */

// Create WebSocket connection.
const WebSocket = require('ws');
const socket = new WebSocket('ws://localhost:8887');

// Connection opened
socket.addEventListener('open', function (event) {
    socket.send('Hello Server!');
});

// Listen for messages
socket.addEventListener('message', function (event) {
    console.log('Message from server ', event.data);
});

let i = 0;
let intervalID = setInterval(() => {
    socket.send(Math.random());
    if (i == 5) {
        clearInterval(intervalID);
        socket.close;
    }
    i++;
}, 2000);