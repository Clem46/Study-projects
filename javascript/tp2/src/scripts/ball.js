
// la source de l'image à utiliser pour la balle
import ballImgSrc from './assets/images/ball.png';

/* TYPE Ball */
export default class Ball {

	static BALL_WIDTH = 48;

  constructor(x, y, deltaX = 3, deltaY = -2) {
    this.image = this.#createImage(ballImgSrc);
    this.x =x;
    this.y = y;
    this.deltaX = deltaX;
    this.deltaY = deltaY;
  }

  /* draw this ball, using the given drawing 2d context */
  draw(context) {
    context.drawImage(this.image, this.x, this.y);
  }

  move(canvas){
    if ((this.x + this.deltaX) < 0|| (this.x + this.deltaX + Ball.BALL_WIDTH) > canvas.width){
      this.deltaX = -this.deltaX;
    }
    if ((this.y + this.deltaY + Ball.BALL_WIDTH) > canvas.height || (this.y + this.deltaY) < 0){
      this.deltaY = -this.deltaY  ;
    }
      
    
    this.x += this.deltaX;
    this.y += this.deltaY;
  }

  /* crée l'objet Image à utiliser pour dessiner cette balle */
  #createImage(imageSource) {
	  const newImg = new Image();
  	newImg.src = imageSource;
  	return newImg;
  }
  get width() {
    return this.image.width;
  }
  get height() {
    return this.image.height;
  }

  collisionWith(obstacle){
    const A1x = this.x
    const A1y = this.y;
    const A2x = this.x + this.width;
    const A2y = this.y + this.height;
    const A1xbis = obstacle.getX();
    const A1ybis = obstacle.getY();
    const A2xbis = obstacle.getX() + obstacle.getWidth();
    const A2ybis = obstacle.getY() + obstacle.getHeight();
    const P1x = Math.max(A1x, A1xbis);
    const P1y = Math.max(A1y, A1ybis);
    const P2x = Math.min(A2x, A2xbis);
    const P2y = Math.min(A2y, A2ybis);
    return P1x < P2x && P1y < P2y;
  }
}
