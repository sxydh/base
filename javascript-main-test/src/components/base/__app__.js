/*
apply()
apply() 方法调用一个具有给定this值的函数，以及以一个数组（或类数组对象）的形式提供的参数。
注意：call()方法的作用和 apply() 方法类似，区别就是call()方法接受的是参数列表，而apply()方法接受的是一个参数数组。

func.apply(thisArg, [argsArray])
thisArg
必选的。在 func 函数运行时使用的 this 值。请注意，this可能不是该方法看到的实际值：如果这个函数处于非严格模式下，则指定为 null 或 undefined 时会自动替换为指向全局对象，原始值会被包装。
argsArray
可选的。一个数组或者类数组对象，其中的数组元素将作为单独的参数传给 func 函数。如果该参数的值为 null 或  undefined，则表示不需要传入任何参数。从ECMAScript 5 开始可以使用类数组对象。 浏览器兼容性 请参阅本文底部内容。

返回值
调用有指定this值和参数的函数的结果。
*/
// 示例：用 apply 将数组各项添加到另一个数组
var array = ['a', 'b'];
var elements = [0, 1, 2];
array.push.apply(array, elements);
console.info(array); // ["a", "b", 0, 1, 2]

/*
call()
call() 方法使用一个指定的 this 值和单独给出的一个或多个参数来调用一个函数。
注意：该方法的语法和作用与 apply() 方法类似，只有一个区别，就是 call() 方法接受的是一个参数列表，而 apply() 方法接受的是一个包含多个参数的数组。
https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Function/call

语法
function.call(thisArg, arg1, arg2, ...)
thisArg
可选的。在 function 函数运行时使用的 this 值。请注意，this可能不是该方法看到的实际值：如果这个函数处于非严格模式下，则指定为 null 或 undefined 时会自动替换为指向全局对象，原始值会被包装。
arg1, arg2, ...
指定的参数列表。

返回值
使用调用者提供的 this 值和参数调用该函数的返回值。若该方法没有返回值，则返回 undefined。

描述
call() 允许为不同的对象分配和调用属于一个对象的函数/方法。
call() 提供新的 this 值给当前调用的函数/方法。你可以使用 call 来实现继承：写一个方法，然后让另外一个新的对象来继承它（而不是在新对象中再写一次这个方法）。
*/
// 示例：使用 call 方法调用父构造函数
function Product(name, price) {
  this.name = name;
  this.price = price;
}
function Food(name, price) {
  Product.call(this, name, price);
  this.category = 'food';
}
console.log(new Food('cheese', 5).name); // expected output: "cheese"

/*
encodeURI()
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/encodeURI
The encodeURI() function encodes a URI by replacing each instance of certain characters by one, two, three, or four escape sequences representing the UTF-8 encoding of the character (will only be four escape sequences for characters composed of two "surrogate" characters).

Syntax
encodeURI(URI)

相对应的是
decodeURI()
*/
var set1 = ";,/?:@&=+$#"; // Reserved Characters
var set2 = "-_.!~*'()";   // Unreserved Marks
console.log(encodeURI(set1)); // ;,/?:@&=+$#
console.log(encodeURI(set2)); // -_.!~*'()

/*
filter()
The filter() method creates an array filled with all array elements that pass a test (provided as a function).
Note: filter() does not execute the function for array elements without values.
Note: filter() does not change the original array.

Syntax
array.filter(function(currentValue, index, arr), thisValue)

Parameter Values
function(currentValue, index,arr) 
Required. A function to be run for each element in the array.
Function arguments:
currentValue	Required. The value of the current element
index	Optional. The array index of the current element
arr	Optional. The array object the current element belongs to

thisValue Optional. 
A value to be passed to the function to be used as its "this" value.
If this parameter is empty, the value "undefined" will be passed as its "this" value
*/
var ages = [32, 33, 16, 40];
function checkAdult(age) {
  return age >= 18;
}
function myFunction() {
  document.getElementById("demo").innerHTML = ages.filter(checkAdult);
}

/* 
for…in loop 
*/
for (let key in obj) {
  if (obj.hasOwnProperty(key)) {
      console.log(key + " -> " + obj[key]);
  }
}

/*
forEach()
Calls a function once for each element in an array, in order.
Note: the function is not executed for array elements without values.

Syntax
array.forEach(function(currentValue, index, arr), thisValue)

function(currentValue, index, arr)	Required. A function to be run for each element in the array.
Function arguments:
currentValue	Required. The value of the current element
index	Optional. The array index of the current element
arr	Optional. The array object the current element belongs to
thisValue	Optional. A value to be passed to the function to be used as its "this" value.
If this parameter is empty, the value "undefined" will be passed as its "this" value
*/
var fruits = ["apple", "orange", "cherry"];
fruits.forEach(myFunction);
function myFunction(item, index) {
    document.getElementById("demo").innerHTML += index + ":" + item + "<br>";
}

/* 
Object.getOwnPropertyNames() 
*/
var names = Object.getOwnPropertyNames(obj);
names.forEach((value, index, array) => {
    console.log(value + " -> " + obj[value]);
})

/*
join()
The join() method creates and returns a new string by concatenating all of the elements in an array (or an array-like object), separated by commas or a specified separator string. If the array has only one item, then that item will be returned without using the separator.

Syntax
arr.join([separator])

Parameters
separator Optional
*/
var a = ['Wind', 'Water', 'Fire'];
a.join();      // 'Wind,Water,Fire'
a.join(', ');  // 'Wind, Water, Fire'
a.join(' + '); // 'Wind + Water + Fire'
a.join('');    // 'WindWaterFire'

/* 
Object.keys() 
*/
var keys = Object.keys(obj);
keys.forEach((value, index, array) => {
    console.log(value + " -> " + obj[value]);
})

/*
Math.max()
The Math.max() function returns the largest of zero or more numbers.

Syntax
Math.max([value1[, value2[, ...]]])
*/
Math.max(10, 20);   //  20
Math.max(-10, -20); // -10
Math.max(-10, 20);  //  20

// Getting the maximum element of an array
var arr = [1,2,3];
var max = arr.reduce(function(a, b) {
    return Math.max(a, b);
});

/*
replace()
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/replace
The replace() method returns a new string with some or all matches of a pattern replaced by a replacement. The pattern can be a string or a RegExp, and the replacement can be a string or a function to be called for each match. If pattern is a string, only the first occurrence will be replaced.

Syntax
var newStr = str.replace(regexp|substr, newSubstr|function)

https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions#Advanced_searching_with_flags_2
g	Global search.
i	Case-insensitive search.
m	Multi-line search.
s	Allows . to match newline characters. (Added in ES2018, not yet supported in Firefox).
u	"unicode"; treat a pattern as a sequence of unicode code points.
y	Perform a "sticky" search that matches starting at the current position in the target string. See sticky.
*/
var re = /apples/gi;
var str = 'Apples are round, and apples are juicy.';
var newstr = str.replace(re, 'oranges');
console.log(newstr);  // oranges are round, and oranges are juicy.
// or
var find = "apples"
re = new RegExp(find, "gi");
newstr = str.replace(re, 'oranges');
console.log(newstr);  // oranges are round, and oranges are juicy.

/*
setInterval
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

/*
setTimeout
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

/* 
splice()
Adds/removes items to/from an array, and returns the removed item(s).
Note: This method changes the original array.

Syntax
array.splice(index, howmany, item1, ....., itemX)

index	Required. An integer that specifies at what position to add/remove items, Use negative values to specify the position from the end of the array
howmany	Optional. The number of items to be removed. If set to 0, no items will be removed
item1, ..., itemX	Optional. The new item(s) to be added to the array
*/
var fruits = ["Banana", "Orange", "Apple", "Mango"];
fruits.splice(2, 0, "Lemon", "Kiwi");

/*
trim()
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
The trim() method removes whitespace from both ends of a string. Whitespace in this context is all the whitespace characters (space, tab, no-break space, etc.) and all the line terminator characters (LF, CR, etc.).

Syntax
str.trim()
*/
var orig = '   foo  ';
console.log(orig.trim()); // 'foo'
// Another example of .trim() removing whitespace from just one side.
var orig = 'foo    ';
console.log(orig.trim()); // 'foo' 