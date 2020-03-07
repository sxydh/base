/*
call()
The call() method calls a function with a given this value and arguments provided individually.
Note: While the syntax of this function is almost identical to that of apply(), the fundamental difference is that call() accepts an argument list, while apply() accepts a single array of arguments.
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/call

Syntax
function.call(thisArg, arg1, arg2, ...)

Parameters
thisArg Optional. The value of this provided for the call to a function. Note that this may not be the actual value seen by the method: if the method is a function in non-strict mode, null and undefined will be replaced with the global object and primitive values will be converted to objects.
arg1, arg2, ... Optional. Arguments for the function.
*/
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