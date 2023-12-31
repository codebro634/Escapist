 import java.awt.Graphics;
 import java.awt.Rectangle;
 import java.awt.image.BufferedImage;
 
 
 public abstract class Tiles
 {
   protected static final byte KachelSize = 50;
   protected short Xpos;
   protected short Ypos;
   protected final boolean isAutoGenerated;
   
   protected Tiles(boolean isAutoGenerated) {
     this.isAutoGenerated = isAutoGenerated;
   } protected abstract BufferedImage getImage(); protected abstract byte getID(); protected abstract void main();
   protected abstract String getArgs();
   void render(Graphics g) {
     main();
     g.drawImage(getImage(), this.Xpos, this.Ypos, 50, 50, null);
   }
   
   static boolean doesIntersect(boolean Player, int x, int y, int width, int height) {
     boolean intersection = false;
     for (Tiles t : LevelControle.aktiveTiles) {
       if (t.intersection(Player, x, y, width, height))
         intersection = true; 
     } 
     return intersection;
   }
   
   static boolean canPlace(int x, int y, int width, int height) {
     for (Tiles t : LevelControle.aktiveTiles) {
       short[] pos = t.realPos();
       if ((new Rectangle(x, y, width, height)).intersects(new Rectangle(pos[0], pos[1], 50, 50)))
         return false; 
     } 
     return true;
   }
   
   boolean intersection(boolean Player, int x, int y, int width, int height) {
     return (new Rectangle(this.Xpos, this.Ypos, 50, 50)).intersects(new Rectangle(x, y, width, height));
   }
   
   protected short[] realPos() {
     return new short[] { this.Xpos, this.Ypos };
   }
 }