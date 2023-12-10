 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.util.ArrayList;
 
 
 public class AnimationSpecific
 {
   private static ArrayList<AnimationSpecific> list = new ArrayList<>();
   
   static void render(Graphics g) {
     for (int i = 0; i < list.size(); i++) {
       if (((AnimationSpecific)list.get(i)).activeTime > ((AnimationSpecific)list.get(i)).time) {
         list.remove(i);
       } else {
         ((AnimationSpecific)list.get(i)).main(g);
       } 
     } 
     if (dying) {
       Player.setX(500);
       Player.setY(-1000);
       dieTime++;
       dieX += 3;
       int lastY = dieY;
       dieY -= 15;
       dieY = (int)(dieY + 0.08D * (dieTime * dieTime));
       if (lastY > dieY) {
         g.drawImage(Controle.character, dieX, dieY, 30, 45, null);
       } else {
         g.drawImage(Controle.characterUPSIDEDOWN, dieX, dieY, 30, 45, null);
       } 
       if (dieY > Controle.c.getHeight()) {
         LevelControle.addDeath();
         dying = false;
         Player.lastDeathTime = System.currentTimeMillis();
         LevelControle.reset();
       } 
     } 
   }
   
   static void addAnimation(int x, int y, String text, Color color, Font font, int time) {
     list.add(new AnimationSpecific(x, y, text, color, font, time));
   }
   
   static void clearAll() {
     list.clear();
   }
   static boolean dying = false; private static int dieX; private static int dieY; private static int dieTime; private String text; private Color color; private Font font;
   static boolean isAnimating() {
     return !list.isEmpty();
   }
   private int x; private int y; private int time; private int activeTime;
   private int amount;
   private String bisjetzt;
   
   static void dieAnimation() {
     Player.resetPhysics();
     dying = true;
     dieX = Player.getX();
     dieY = Player.getY();
     dieTime = 0;
   }
 
 
   
   private AnimationSpecific(int x, int y, String text, Color color, Font font, int time)
   {
     this.amount = -1;
     this.bisjetzt = ""; this.text = text; this.color = color; this.font = font;
     this.x = x;
     this.y = y;
     this.time = time; } private void main(Graphics g) { if (this.amount < this.text.length() - 1) {
       this.amount++;
       this.bisjetzt = String.valueOf(this.bisjetzt) + this.text.charAt(this.amount);
     } 
     g.setFont(this.font);
     g.setColor(this.color);
     g.drawString(this.bisjetzt, this.x, this.y);
     this.activeTime += 50; }
 
 }
