 import java.util.TimerTask;
 
 class Renderer
   extends TimerTask
 {
   private short renderedMilliseconds;
   private byte renderedAmount;
   
   public void run() {
     this.renderedMilliseconds = (short)(this.renderedMilliseconds + 50);
     if (this.renderedMilliseconds == 1000) {
       this.renderedMilliseconds = 0;
       Controle.c.FPS = this.renderedAmount;
       this.renderedAmount = 0;
     } 
     if (Controle.tickFinished) {
       Controle.c.repaint();
       this.renderedAmount = (byte)(this.renderedAmount + 1);
     } else {
       
       System.out.println("Couldn't keep up : Tick didnt finish!");
     } 
   }
 }
