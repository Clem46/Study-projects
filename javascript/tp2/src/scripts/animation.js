import Ball from './ball';

/* TYPE Animation */
export default class Animation {

  constructor(canvas){
    this.canvas = canvas;
    this.balls = Array(); 
    this.request = null;
    this.context = canvas.getContext("2d");
  }

  animate(){
    this.context.clearRect(0,0, this.canvas.width, this.canvas.height);
    this.balls.forEach(ball => ball.move(this.canvas));
    this.balls.forEach(ball => ball.draw(this.context));
    this.request = window.requestAnimationFrame(() => this.animate());
  }

  /* start the animation or stop it if previously running */
  startAndStop() {
    if (this.request === null)
      this.request = window.requestAnimationFrame(() => this.animate());
    else{
      window.cancelAnimationFrame(this.request);
      this.request = null;
    }
  }

  addBall(){
    this.balls.push(this.randomBall());
  }

  randomBall(){
    const x = Math.floor(Math.random()*(this.canvas.width-Ball.BALL_WIDTH));
    const y = Math.floor(Math.random()*(this.canvas.height-Ball.BALL_WIDTH));
    let deltaX;
    let deltaY;
    do{
      deltaX = Math.floor(Math.random()*10) -5;
      deltaY = Math.floor(Math.random()*10) -5;
    }while(deltaX == 0 && deltaY == 0)
    return new Ball(x,y,deltaX,deltaY);
  }
}
