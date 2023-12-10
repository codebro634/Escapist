 import java.awt.Graphics;
 import java.awt.Rectangle;
 import java.awt.image.BufferedImage;
 
 
 class missile
 {
   static final byte missileSize = 25;
   private static final BufferedImage image = Controle.missile;
   
   private short x;
   
   private short y;
   private long spawnTime = System.currentTimeMillis(); private final byte direction; private final byte speed; boolean isActive = true;
   
   missile(short x, short y, byte direction, byte speed) {
     this.direction = direction;
     this.speed = speed;
     this.x = x;
     this.y = y;
   }
   
   void main(Graphics g) {
     switch (this.direction) {
       case 1:
         this.y = (short)(this.y - this.speed);
         break;
       case 2:
         this.y = (short)(this.y + this.speed);
         break;
       case 3:
         this.x = (short)(this.x - this.speed);
         break;
       case 4:
         this.x = (short)(this.x + this.speed);
         break;
     } 
     if ((new Rectangle(this.x, this.y, 25, 25)).intersects(new Rectangle(Player.getX(), Player.getY(), 30, 45))) {
       this.isActive = false;
       AnimationSpecific.dieAnimation();
     } 
     if ((System.currentTimeMillis() - this.spawnTime > ((50 / this.speed + 1) * 50) && Tiles.doesIntersect(false, this.x, this.y, 25, 25)) || this.x + 25 < 0 || this.y + 25 < 0 || this.y > Controle.c.getHeight() || this.x > Controle.c.getWidth())
       this.isActive = false; 
     if (this.isActive)
       g.drawImage(image, this.x, this.y, 25, 25, null); 
   }
 }
