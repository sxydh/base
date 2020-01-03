/*
Ref: https://developer.mozilla.org/zh-CN/docs/Web/API/Window/setInterval

Syntax:
let intervalID = window.setInterval(func, delay[, param1, param2, ...]);
let intervalID = window.setInterval(code, delay);
*/

let i = 0;
let intervalID = setInterval(() => {
    console.log(Math.random());
    if (i == 5) {
        clearInterval(intervalID);
    }
    i++;
}, 2000);
