'use strict';

// des listes pour des tests
const numbers = [2, 3, 5, 4, 10, 6];
const persons = [ {name : 'timoleon', age : 12 }, {name : 'bilbo', age : 111 }, {name : 'frodo', age : 33 }, {name : 'sam', age : 26 }];

/********** EXERCICE 1 ***********************/
console.log(` *** EXERCICE 1 *** `);

// exemple de manière de répondre aux questions d'un exercice

// Q1
/* computes the double of its parameter
 * @param x (number) a number
 * @return (number) the double of *x*
*/
const example = x => x * 2;

//Q2
// tests d'exécution de la fonction example
console.log(`Q2 - example(10) : ${example(10)}`);
console.log(`Q2 - example(21) : ${example(21)}`);

// Q3
/* filter and keep the elements of *list* smaller than *max*
 * @param list (Array) list of elements
 * @param max (Any) upper bound filter value
 * @return (Array) list of elements of *list* smaller than *max*
*/
const example2 = (list, max) => list.filter( elt => elt < max );

// Q4
// tests d'exécution de la fonction example2
console.log(`Q4 - example2(numbers, 5) : ${example2(numbers, 5)}`);

/*********************************************/



/********** EXERCICE 2 ***********************/
console.log(` *** EXERCICE 2 *** `);

//Q1
let nom_persons = persons.map(nom => nom.name);
//tests
console.log(`Q1 - example : ${nom_persons}`);

//Q2
let first_letter = nom_persons.map(nom => nom.substring(0,1).toUpperCase());
//tests
console.log(`Q2 - example : ${first_letter}`);

//Q3
let i_letter = nom_persons.map((nom, i) => nom.substring(i, i+1));
//tests
console.log(`Q3 - example : ${i_letter}`);

//Q4
//Q4.1
const capitalize = function(nom) {return nom.substring(0,1).toUpperCase() + nom.substring(1)};
//Q4.2
let all_capitalize = nom_persons.map(nom => capitalize(nom));
//tests
console.log(`Q4.1 - example : ${capitalize('bonjour')}`);
console.log(`Q4.2 - example : ${all_capitalize}`);

//Q5
const myMap = function(arr, func) {
    let res = [];
    for(let elt in arr)
        res.push(func(arr[elt]));
    
    return res;
}
//tests
console.log(`Q5 - example : ${myMap(nom_persons, capitalize)}`);

/*********************************************/


/********** EXERCICE 3 ***********************/
console.log(` *** EXERCICE 3 *** `);

//Q1
console.log(`Q1`);
console.log(`ages = [12, 111, 33, 26]`);
console.log(`maxAge = 111`);
console.log(`timoStr = 'timoleon'`);
console.log(`timoArray = ['t','i','m','o','l','e','o','n']`);
console.log(`newTimoStr = 't-i-m-o-l-e-o-n'`);

const ages = persons.map( person => person.age );
const maxAge = Math.max(...ages);
const timoStr = 'timoleon';
const timoArray = [...timoStr];                         
const newTimoStr = timoArray.join('-');

//Q2
const shiftCodePoint = function(car) {return 9398 + (car.codePointAt(0) - 97)};
//test
console.log(`Q2 - example : ${shiftCodePoint('c')}`);

//Q3
const shiftCodePointList = function(str) {return ([...str]).map(lettre => shiftCodePoint(lettre))}; 
//test 
console.log(`Q3 - example : ${shiftCodePointList('timoleon')}`);

//Q4
let circled_letter = nom_persons.map(nom => (shiftCodePointList(nom).map(lettre => String.fromCodePoint(lettre)).join("")));
//test
console.log(`Q4 - example : ${circled_letter}`);

/*********************************************/


/********** EXERCICE 4 ***********************/
console.log(` *** EXERCICE 4 *** `);

//Q1
let numbersx10 = numbers.map(nb => nb*10);
//test
console.log(`Q1 - example : ${numbersx10}`);

//Q2
const multiples = function(n, arr) {return arr.map(nb => nb * n)};
//test
console.log(`Q2 - example : ${multiples(10, numbers)}`);

//Q3
const multiples5 = multiples(5, numbers);
//test
console.log(`Q3 - example : ${multiples5}`);

//Q4
const multiplesFactory = function(n) {
    const mult = (arr) => multiples(n, arr);
    return mult;
}
const multiples100 = multiplesFactory(100);
//test
console.log(`Q4 - example : ${typeof(multiples100)}`);
console.log(`Q4 - example : ${multiples100(numbers)}`);

/*********************************************/

/********** EXERCICE 5 ***********************/
console.log(` *** EXERCICE 5 *** `);

//Q1
console.log(`Q1 :`);
numbers.forEach(nb => console.log(nb));

//Q2
console.log(`Q2 :`);
persons.forEach(pers => console.log(`${pers.name} a ${pers.age} ans`));

//Q3
const myForEach = function(arr, func) {
    for(let elt in arr)
        func(arr[elt]);
}

//Q4
myForEach(numbers, console.log);
myForEach(persons, person => console.log(`${person.name} a ${person.age} ans`));

/*********************************************/

/********** EXERCICE 6 ***********************/
console.log(` *** EXERCICE 6 *** `);

//Q1
let nbLesserThan5 = numbers.filter(nb => nb < 5);
//test
console.log(`Q1 - example : ${nbLesserThan5}`);

//Q3
const createAcronym = function(str) {
    const arr = str.split(" ").filter(mot => mot.length > 3);
    const res = (arr.map(mot => mot.charAt(0).toUpperCase())).join("");
    return res
}
//test
console.log(`Q3 - example : ${createAcronym('formations en informatique de lille')}`);
console.log(`Q3 - example : ${createAcronym('société nationale des chemins de fer français')}`);

/*********************************************/


/********** EXERCICE 7 ***********************/
console.log(` *** EXERCICE 7 *** `);

//Q1
const nbLetters = function(str) {
    const arr = str.split(" ");
    const res = arr.reduce((previous, mot) => previous + mot.length, 0);
    return res;
}
//test
console.log(`Q1 - example : ${nbLetters('formations en informatique de lille')}`);


//Q2
const maxnb = (n1,n2) => n1>n2?n1:n2;
//test
console.log(`Q2 - example : ${maxnb(10, 20)}`);

//Q2
const maxNumber = (arr) => arr.reduce((max, nb) => maxnb(max, nb));
//test
console.log(`Q2 - example : ${maxNumber(numbers)}`);

//Q3
const maxNumber2 = (arr) => maxnb(...arr);
//test
console.log(`Q3 - example : ${maxNumber(numbers)}`);

//Q4
const sum = (...nb) => nb.reduce((res, nb) => res + nb, 0);
//test
console.log(`Q4 - example : ${sum()}`);
console.log(`Q4 - example : ${sum(10,20)}`);
console.log(`Q4 - example : ${sum(1,6,8)}`);

/*********************************************/


/********** EXERCICE 8 ***********************/
console.log(` *** EXERCICE 8 *** `);

const lesInvites = ['Tim Oleon', 'Timo Leon', 'Bilbo', 'Frodo', 'Sam', 'Merry', 'Pippin']
const lesReponses = [
                  {nom : 'Sam', present : 'oui'},
                  {nom : 'Tim Oleon', present : 'non'},
                  {nom : 'Bilbo', present : 'oui'},
                  {nom : 'Frodo', present : 'oui'},
                  {nom : 'Timo Leon', present : 'non'},
                 ];

//Q1
const participants = (inv, rep) => {
    const repNo =rep.filter(pers => pers.present == 'non').map(pers => pers.nom);
    const res = inv.filter(pers => !(repNo.includes(pers)));
    return res;
}
//test
console.log(`Q1 - example : ${participants(lesInvites, lesReponses)}`);
/*********************************************/

/********** EXERCICE 9 ***********************/
console.log(` *** EXERCICE 9 *** `);



/*********************************************/

/********** EXERCICE 10 ***********************/
console.log(` *** EXERCICE 10 *** `);



/*********************************************/
