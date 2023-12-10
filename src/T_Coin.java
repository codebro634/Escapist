 import java.awt.Color;
 import java.awt.Graphics;
 import java.awt.Rectangle;
 import java.awt.image.BufferedImage;
 
 
 public class T_Coin
   extends Tiles
 {
   private static final byte ID = 7;
   private static final BufferedImage image = Controle.coin;
   private static final short height = 30;
   private static final short width = 30;
   
   T_Coin(boolean isAutoGenerated, short x, short y) {
     super(isAutoGenerated);
     this.Xpos = x;
     this.Ypos = y;
     this.realY = this.Ypos;
   }
   private short realY; private boolean collected; private boolean up;
   protected byte getID() {
     return 7;
   }
   
   protected BufferedImage getImage() {
     return image;
   }
 
 
   
   protected String getArgs() {
     if (this.isAutoGenerated)
       return null; 
     return String.valueOf(this.Xpos) + "," + this.realY + "/";
   }
   
   boolean intersection(boolean Player, int x, int y, int width, int height) {
     return false;
   }
 
 
 
   
   protected void main() {
     if (this.up) {
       this.Ypos = (short)(this.Ypos - 1);
       if (this.Ypos < this.realY - 30) {
         this.up = false;
       }
     } else {
       this.Ypos = (short)(this.Ypos + 1);
       if (this.Ypos > this.realY) {
         this.up = true;
       }
     } 
     if (!this.collected && (new Rectangle(this.Xpos + 10, this.Ypos + 20, 30, 30)).intersects(new Rectangle(Player.getX(), Player.getY(), 30, 45))) {
       this.collected = true;
       Animation.Image(Controle.coin, Player.getX() + 30, Player.getY() - 40, 20, 20, 500, 0);
       Animation.Text(Player.getX() - 10, Player.getY() - 20, 30, Color.YELLOW, "Arial", "+1", 500, 0);
       LevelControle.player_coin++;
     } 
   }
   
   protected short[] realPos() {
     return new short[] { this.Xpos, this.realY };
   }
   
   void render(Graphics g) {
     main();
     if (!this.collected)
       g.drawImage(getImage(), this.Xpos + 10, this.Ypos + 20, 30, 30, null); 
   }
 }