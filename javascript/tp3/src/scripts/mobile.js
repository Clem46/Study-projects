export default class Mobile {

    constructor(Img, x, y, width, height, deltaX = 0, deltaY = 0) {
        this.image = this.#createImage(Img);
        this.x =x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
      }

    #createImage(imageSource) {
	  const newImg = new Image();
  	newImg.src = imageSource;
  	return newImg;
  }

  draw(context){
    context.drawImage(this.image, this.x, this.y);
  }

  move() {
    this.x += this.deltaX;
    this.y += this.deltaY;
  }

  getX() {
    return this.x;
  }
  getY() {
    return this.y;
  }

  getWidth() {
    return this.width;
  }

  getHeight() {
    return this.height;
  }

  collisionWith(mobile){
    const A1x = this.x
    const A1y = this.y;
    const A2x = this.x + this.width;
    const A2y = this.y + this.height;
    const A1xbis = mobile.getX();   
    const A1ybis = mobile.getY();
    const A2xbis = mobile.getX() + mobile.getWidth();
    const A2ybis = mobile.getY() + mobile.getHeight();
    const P1x = Math.max(A1x, A1xbis);
    const P1y = Math.max(A1y, A1ybis);
    const P2x = Math.min(A2x, A2xbis);
    const P2y = Math.min(A2y, A2ybis);
    return P1x < P2x && P1y < P2y;
  }
}