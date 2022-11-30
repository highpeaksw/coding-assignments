const prompt=require("prompt-sync")();
var fs = require('fs');
var input = fs.readFileSync('input.txt').toString().split('\r\n'); //Read file from input.txt
var inputObj = {}; //Convert to object
input.forEach(function(line) {
    var lineArr = line.split(':');
    inputObj[lineArr[0]] = Number(lineArr[1]);
});
const sortArray=Object.entries(inputObj).sort((a,b)=>{ //Convert object to array and sorting it in ascending order
    return a[1]-b[1];
})
var emp=Number(prompt("Enter the number of employees "))
var min=sortArray[emp-1][1]-sortArray[0][1]
var minIndex=0
//Working below to find the minimum difference between  goodies
for(var i=1;i<sortArray.length-emp+1;i++){
    if(sortArray[i+emp-1][1]-sortArray[i][1]<min){
        min=sortArray[i+emp-1][1]-sortArray[i][1]
        minIndex=i
    }
}
const result=sortArray.slice(minIndex,minIndex+emp).map((item)=>{ 
   return `\n${item[0]}:${item[1]}`
}).join(' ')
const data=`The goodies selected for distribution are: \n${result}\n\nAnd the difference between the chosen goodie with highest price and the lowest price is ${min}`
console.log(data)
fs.writeFileSync("output.txt",data) //Write the output into output.txt file
console.log(data)