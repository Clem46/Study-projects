import Mobile from './mobile';
import Img from './assets/images/basket.png';

export default class Basket extends Mobile{

    constructor(x, y) {
        super(Img, x, y, 128, 94);
        this.moving = null;
    }

    move(canvas){
        this.x = Math.max(0, Math.min(canvas.width - 128, this.x + this.deltaX));
        this.y = Math.max(0, Math.min(canvas.height - 94, this.y + this.deltaY));
    }

    moveLeft() {              
    this.deltaX =  - 10;
    }

    moveRight() {
    this.deltaX =  + 10;
    }

    moveUp() {              
    this.deltaY =  - 10;
    }

    moveDown() {
    this.deltaY =  + 10;
    }

    stopMoving() {
    this.deltaX = 0;
    this.deltaY = 0;
    }

    handleMoveKeys(keyManager) {
    this.stopMoving();
    if (keyManager.left)
        this.moveLeft();
    if (keyManager.right)
        this.moveRight();
    if (keyManager.up)
        this.moveUp();
    if (keyManager.down)
        this.moveDown();
    }
}