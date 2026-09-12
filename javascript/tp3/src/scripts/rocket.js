import Mobile from './mobile';
import Img from './assets/images/rocket-right.png';

export default class Rocket extends Mobile {

    constructor(x, y) {
        super(Img, x, y, 107, 38, 6, 0);
    }
}