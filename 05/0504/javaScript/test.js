var a = typeof "Hello World!"
console.log(a);

function Introduce(name, age, city) {
	this.name = name;
	this.age = age;
	this.city = city;

	console.log("Hello my name is " + this.name + " \nI'm " +
	this.age + "years old\nAnd I live in " + this.city);
}

var jason = new Introduce("Jason", 20, "Seoul");

const numbers = [2, 4, 3];
const names = ["first", "Mike", "Tyson", "fourth", "Teller"];


for(let i = 0; i < 3; i++)
{
	console.log(names[numbers[i]]);
}

/* 브라우저에서는 prompt()로 메시지 출력 후 입력 받는 게 가능하지만 
 * 터미널 node 환경에서는 불가능하다.
while(1)
{
	var alphabet = prompt("Enter \'z\' if you want to quit this loop");
	console.log("You just enter " + alphabet);
}
*/

