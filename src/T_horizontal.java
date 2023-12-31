 import java.awt.image.BufferedImage;
 
 
 public class T_horizontal
   extends Tiles
 {
   private static final byte ID = 4;
   private final short startX;
   private final short zielX;
   private final short movespeed;
   
   T_horizontal(boolean isAutoGenerated, short startX, short zielX, short y, short movespeed) {
     super(isAutoGenerated);
     if (startX > zielX) {
       this.leftToRight = true;
       this.image = Controle.birdLEFT[0];
       this.startX = zielX;
       this.zielX = startX;
     } else {
       
       this.image = Controle.birdRIGHT[0];
       this.startX = startX;
       this.zielX = zielX;
     } 
     this.Ypos = y;
     this.Xpos = startX;
     this.movespeed = movespeed;
   }
   private BufferedImage image; private boolean leftToRight; private byte imageTime; private byte imageAt; private boolean left;
   protected BufferedImage getImage() {
     return this.image;
   }
   
   protected byte getID() {
     return 4;
   }
   
   protected String getArgs() {
     if (this.isAutoGenerated)
       return null; 
     if (this.leftToRight)
       return String.valueOf(this.zielX) + "," + this.startX + "," + this.Ypos + "," + this.movespeed + "/"; 
     return String.valueOf(this.startX) + "," + this.zielX + "," + this.Ypos + "," + this.movespeed + "/";
   }
 
 
   
   protected void main() {
     this.imageTime = (byte)(this.imageTime + 1);
     if (this.imageTime >= 5) {
       this.imageTime = 0;
       this.imageAt = (byte)(this.imageAt + 1);
       if (this.imageAt == Controle.right.length) {
         this.imageAt = 0;
       }
     } 
     if (this.left) {
       this.image = Controle.birdLEFT[this.imageAt];
     } else {
       this.image = Controle.birdRIGHT[this.imageAt];
     }  move();
     movePlayer();
   }
 
   
   private void movePlayer() {
     int playerX = Player.getX(), playerY = Player.getY(), width = 30, height = 45;
     short amount = 0;
     
     while (true) {
       if (!intersection(true, playerX + amount, playerY, width, height)) {
         Player.setX(playerX + amount);
         return;
       } 
       if (!intersection(true, playerX - amount, playerY, width, height)) {
         Player.setX(playerX - amount);
         return;
       } 
       amount = (short)(amount + 1);
     } 
   }
 
 
 
   
   private void move() {
     if (this.left) {
       this.Xpos = (short)(this.Xpos - this.movespeed);
       if (this.Xpos <= this.startX) {
         this.Xpos = this.startX;
         this.left = false;
       } 
     } else {
       
       this.Xpos = (short)(this.Xpos + this.movespeed);
       if (this.Xpos >= this.zielX) {
         this.Xpos = this.zielX;
         this.left = true;
       } 
     } 
   }
 }