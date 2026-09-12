import Mobile from './mobile';  
import Img1 from './assets/images/blue-egg.png';
import Img2 from './assets/images/green-egg.png';
import Img3 from './assets/images/yellow-egg.png';

const image = [Img1, Img2, Img3];

export default class Egg extends Mobile {

    constructor(x, y) {
        let nb = Math.floor(Math.random()*3);
        super(image[nb], x, y, 64, 80, 0, 4);
    }
}