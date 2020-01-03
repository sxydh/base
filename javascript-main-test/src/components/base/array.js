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