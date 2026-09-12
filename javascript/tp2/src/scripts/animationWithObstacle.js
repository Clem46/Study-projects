import Animation from './animation';
import KeyManager from './keyManager';

export default class AnimationWithObstacle extends Animation{
  
    constructor(canvas, obstacle){
        super(canvas);
        this.obstacle = obstacle;
        this.keyManager = new KeyManager();
    }

    animate(){
        this.context.clearRect(0,0, this.canvas.width, this.canvas.height);
        this.obstacle.handleMoveKeys(this.keyManager);
        this.obstacle.move(this.canvas);
        this.obstacle.draw(this.context);
        this.balls.forEach(ball => ball.move(this.canvas));
        this.balls = this.balls.filter(ball => !(ball.collisionWith(this.obstacle)))
        this.balls.forEach(ball => ball.draw(this.context));
        
        this.request = window.requestAnimationFrame(() => this.animate());
    }

    keyDownActionHandler(event) {
        switch (event.key) {
            case "ArrowLeft":
            case "Left":
                this.keyManager.leftPressed();
                break;
            case "ArrowRight":
            case "Right":
                this.keyManager.rightPressed();
                break;
            case "ArrowUp":
            case "Up":
                this.keyManager.upPressed();
                break;
            case "ArrowDown":
            case "Down":
                this.keyManager.downPressed();
                break;
            default: return;
        }
        event.preventDefault();
  }

  keyUpActionHandler(event) {
    switch (event.key) {
        case "ArrowLeft":
        case "Left":
            this.keyManager.leftReleased();
            break;
        case "ArrowRight":
        case "Right":
            this.keyManager.rightReleased();
            break;
        case "ArrowUp":
        case "Up":
            this.keyManager.upReleased();
            break;
        case "ArrowDown":
        case "Down":
            this.keyManager.downReleased();
            break;
        default: return;
    }
    event.preventDefault();
    }
}