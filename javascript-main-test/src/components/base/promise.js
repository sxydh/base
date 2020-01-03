/**
 * 语法
 * new Promise( function(resolve, reject) {...} );
 * 参数
 * executor
 * executor是带有 resolve 和 reject 两个参数的函数 。Promise构造函数执行时立即调用executor 函数， 
 * resolve 和 reject 两个函数作为参数传递给executor（executor 函数在Promise构造函数返回所建promise实例对象前被调用）。
 * resolve 和 reject 函数被调用时，分别将promise的状态改为fulfilled（完成）或rejected（失败）。
 * executor 内部通常会执行一些异步操作，一旦异步操作执行完毕(可能成功/失败)，要么调用resolve函数来将promise状态改成fulfilled，
 * 要么调用reject 函数将promise的状态改为rejected。如果在executor函数中抛出一个错误，那么该promise 状态为rejected。executor函数的返回值被忽略。
 * 
 * Promise.resolve(value)
 * 返回一个状态由给定value决定的Promise对象。如果该值是thenable(即，带有then方法的对象)，返回的Promise对象的最终状态由then方法执行决定；
 * 否则的话(该value为空，基本类型或者不带then方法的对象),返回的Promise对象状态为fulfilled，并且将该value传递给对应的then方法。通常而言，
 * 如果你不知道一个值是否是Promise对象，使用Promise.resolve(value) 来返回一个Promise对象,这样就能将该value以Promise对象形式使用。
 */
let myFirstPromise = new Promise(function (resolve, reject) {
    //当异步代码执行成功时，我们才会调用resolve(...), 当异步代码失败时就会调用reject(...)
    //在本例中，我们使用setTimeout(...)来模拟异步代码，实际编码时可能是XHR请求或是HTML5的一些API方法.
    setTimeout(function () {
        // resolve("成功!"); //代码正常执行！
        reject("失败!");
    }, 250);
});

myFirstPromise.then(
    function (successMessage) {
        //successMessage的值是上面调用resolve(...)方法传入的值.
        //successMessage参数不一定非要是字符串类型，这里只是举个例子
        console.log("Yay! " + successMessage);
    },
    function (failMessage) {
        console.log(failMessage)
    });

/**
 * 异步链
 */
for (let i = 0, p = Promise.resolve(); i < 5; i++) {
    p.then(() => new Promise((resolve, reject) => {
        console.log(i);
        resolve();
    }));
}