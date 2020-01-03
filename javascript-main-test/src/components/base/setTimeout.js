/*
Ref: https://developer.mozilla.org/zh-CN/docs/Web/API/Window/setTimeout

Syntax:
var timeoutID = scope.setTimeout(function[, delay, param1, param2, ...]);
var timeoutID = scope.setTimeout(function[, delay]);
var timeoutID = scope.setTimeout(code[, delay]);
*/

let timeoutID = setTimeout(() => {
    console.log(Math.random());
}, 2000);

// clearTimeout(timeoutID);