// multi arrow function
var maf = a => b => c => d => a * b / c - d;
var maf = maf(10);
maf = maf(2);
maf = maf(5);
maf = maf(3);
console.log(maf);