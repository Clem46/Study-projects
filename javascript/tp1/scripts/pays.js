const numberFormatter = new Intl.NumberFormat();

dataPays.forEach( pays => pays.toString = function() { return `${this.nom} : ${this.population} hab., ${this.superficie}km², ${this.PIB} milliardsUS$` } );
for(let i = 0; i < 5; i++){
    console.log(`Q1 : ${dataPays[i].toString()}`);
}


/****  QUESTION 2 *****/
console.log(' *** QUESTION 2 ***');

const populationTotale = dataPays.reduce((pop, pays) => pop + pays.population, 0);

console.log(`population totale : ${numberFormatter.format(populationTotale)}`);
/***********************/


/****  QUESTION 3 *****/
console.log(' *** QUESTION 3 ***');

const findData = nom => dataPays.find(pays => pays.nom == nom);

console.log(`${findData('France').toString()}`);

/***********************/


/****  QUESTION 4 *****/
console.log(' *** QUESTION 4 ***');
const dixpluspeuples = dataPays.sort((a,b) => b.population - a.population).slice(0,10).map(pays => pays.nom);

console.log(`10 plus peuplés : ${dixpluspeuples}`);
/***********************/


/****  QUESTION 5 *****/
console.log(' *** QUESTION 5 ***');

const densitePays = dataPays.map(pays => ({nom : pays.nom, densite : (pays.population / pays.superficie).toFixed(2)})).filter(pays => pays.densite > 1000).sort((a, b) => b.densite - a.densite);

densitePays.forEach(pays => console.log(`${pays.nom} : ${pays.densite} : `));
/***********************/


/****  QUESTION 6 *****/
console.log(' *** QUESTION 6 ***');

const pib = dataPays
    .filter(pays => ((pays.PIB * 1000000000) / pays.population) < 10000)
    .map(pays => pays.population)
    .reduce((res, pop) => res + pop);

console.log(pib);
    /***********************/
