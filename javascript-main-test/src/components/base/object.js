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
for…in loop 
*/
for (let key in obj) {
    if (obj.hasOwnProperty(key)) {
        console.log(key + " -> " + obj[key]);
    }
}

/* 
Object.keys() 
*/
var keys = Object.keys(obj);
keys.forEach((value, index, array) => {
    console.log(value + " -> " + obj[value]);
})

/* 
Object.getOwnPropertyNames() 
*/
var names = Object.getOwnPropertyNames(obj);
names.forEach((value, index, array) => {
    console.log(value + " -> " + obj[value]);
})
