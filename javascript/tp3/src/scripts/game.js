import Basket from './basket';
import KeyManager from './keyManager';
import Egg from './egg';
import Rocket from './rocket';

export default class Game {

   #canvas;
   spawnEgg;
   spawnRocket;
   // à compléter

   constructor(canvas) {
      this.#canvas = canvas;
      this.context = canvas.getContext("2d");
      this.player = new Basket(canvas.width/2, canvas.height/2);
      this.eggs = Array();
      this.rockets = Array();
      this.score = 0;
      this.request = null;
      this.keyManager = new KeyManager();
      this.timer = 1000;
      this.life = 3;
   }

   /** donne accès au canvas correspondant à la zone de jeu */
   get canvas() {
      return this.#canvas;
   }

   animate() {
      this.context.clearRect(0,0, this.#canvas.width, this.#canvas.height);

      this.eggs.forEach(egg => egg.move());
      this.eggs.forEach(egg => egg.draw(this.context));

      this.rockets.forEach(egg => egg.move());
      this.rockets.forEach(egg => egg.draw(this.context));

      this.player.handleMoveKeys(this.keyManager);
      this.player.move(this.#canvas);
      this.player.draw(this.context);

      this.eggs = this.eggs.filter(egg => {if(egg.collisionWith(this.player)){
         this.score += 100;
         document.getElementById("score").textContent = this.score;
         return false;  
      }else if(this.rockets.some(rocket => rocket.collisionWith(egg)))
         return false;
      return true;
      });

      this.rockets = this.rockets.filter(rocket => {if(rocket.collisionWith(this.player)){
         this.score -= 500;
         document.getElementById("score").textContent = this.score;
         document.getElementById(`life-${this.life}`).style.display = "none";
         this.life -= 1;
         return false;
      }
      return true;
      })

      this.eggs = this.eggs.filter(egg => (egg.getY() < this.#canvas.height));

      if(this.life > 0)
         this.request = window.requestAnimationFrame(() => this.animate());
      else
         alert("Game Over !");
   }

   startAndStop() {
      if (this.request === null){
         this.spawnEgg = setInterval(() => this.#spawnEgg(), this.timer);
         this.spawnRocket = setInterval(() => this.#spawnRocket(), this.timer);
         this.request = window.requestAnimationFrame(() => this.animate());
      }
      else{
         clearInterval(this.spawnEgg);
         clearInterval(this.spawnRocket);
         window.cancelAnimationFrame(this.request);
         this.request = null;
      }
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

   #spawnEgg(){  
      if(Math.random() < 0.75){
         let x = Math.floor(Math.random()*(this.#canvas.width - 64));
         this.eggs.push(new Egg(x, -80));
      }
   }

   #spawnRocket(){
      if(Math.random() < 0.5){
         let y = Math.floor(Math.random()*(this.#canvas.height - 38));
         this.rockets.push(new Rocket(-107, y));
      }
   }
}



