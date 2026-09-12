export default class Obstacle{
    constructor(x, y, width, height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    draw(context){
        context.fillStyle = "rgb(0,0,0)";
        context.fillRect(this.x, this.y, this.width, this.height);
    }

    getX(){
        return this.x;
    }

    getY(){
        return this.y;
    }

    getWidth(){
        return this.width;
    }

    getHeight(){
        return this.height;
    }

    moveLeft() {              
    this.deltaX =  - 10;   // le déplacement se fera vers la gauche, par pas de 10px
    }

    moveRight() {
    this.deltaX =  + 10;   // le déplacement se fera vers la droite, par pas de 10px
    }

    moveUp() {              
    this.deltaY =  - 10;   // le déplacement se fera vers la gauche, par pas de 10px
    }

    moveDown() {
    this.deltaY =  + 10;   // le déplacement se fera vers la droite, par pas de 10px
    }

    stopMoving() {
    this.deltaX = 0;
    this.deltaY = 0;
    }

    move(box) {              // déplace sans sortir des limites de *box*
    this.x = Math.max(0, Math.min(box.width - this.width, this.x + this.deltaX));
    this.y = Math.max(0, Math.min(box.height - this.height, this.y + this.deltaY));
    }
    
    handleMoveKeys(keyManager) {
    this.stopMoving();    // on réinitialise les déplacements
    if (keyManager.left)  // touche flèche gauche pressée ?
        this.moveLeft();
    if (keyManager.right) // touche flèche droite pressée ?
        this.moveRight();
    if (keyManager.up)
        this.moveUp();
    if (keyManager.down)
        this.moveDown();
    }
}