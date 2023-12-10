 import java.awt.Graphics;
 
 public class Player
 {
   private static int x;
   private static int y;
   static final int height = 45;
   
   static void render(Graphics g) {
     if (Events.aPressed)
       moveLeft(); 
     if (Events.dPressed)
       moveRight(); 
     fall();
     
     if (x > Controle.c.getWidth()) {
       LevelControle.changeLevel((byte)(LevelControle.getCurrentLevel() + 1));
     }
     if (x < -29 && 
       !LevelControle.changeLevel((byte)(LevelControle.getCurrentLevel() - 1))) {
       LevelControle.reset();
     }
     draw(g);
   }
 
   
   static final int width = 30;
   
   private static byte imageAt;
   
   private static void draw(Graphics g) {
     if (Events.aPressed || Events.dPressed) {
       imageTime = (byte)(imageTime + 1);
       if (imageTime >= 5) {
         imageTime = 0;
         imageAt = (byte)(imageAt + 1);
         if (imageAt == Controle.right.length) {
           imageAt = 0;
         }
       } 
       if (Events.aPressed) {
         if (System.currentTimeMillis() - lastDeathTime > 1500L || System.currentTimeMillis() % 2L != 0L) {
           g.drawImage(Controle.left[imageAt], x, y, 30, 45, null);
         }
       }
       else if (System.currentTimeMillis() - lastDeathTime > 1500L || System.currentTimeMillis() % 2L != 0L) {
         g.drawImage(Controle.right[imageAt], x, y, 30, 45, null);
       }
     
     } else if (lastDirection) {
       if (System.currentTimeMillis() - lastDeathTime > 1500L || System.currentTimeMillis() % 2L != 0L) {
         g.drawImage(Controle.right[0], x, y, 30, 45, null);
       }
     }
     else if (System.currentTimeMillis() - lastDeathTime > 1500L || System.currentTimeMillis() % 2L != 0L) {
       g.drawImage(Controle.left[1], x, y, 30, 45, null);
     } 
   }
   private static byte imageTime; static boolean lastDirection = true;
   static long lastDeathTime;
   private static short fall_time = 0;
   private static short jump_time;
   private static final float acceleration = 0.25F;
   
   static void resetPhysics() {
     fall_time = 0;
     jump_time = 0;
   }
   
   private static final short jumpStrength = 25;
   private static final short jumpDuration = 20;
   
   private static void fall() {
     if (jump_time > 0) {
       jump_time = (short)(jump_time - 1);
       short s = 25;
       while (s > 0 && Tiles.doesIntersect(true, x, y - s, 30, 45)) {
         s = (short)(s - 1);
         jump_time = 0;
       } 
       y -= s;
     } 
 
     
     if (fall_time < 15)
       fall_time = (short)(fall_time + 1); 
     short amount = (short)(int)(0.25F * (fall_time * fall_time));
     while (amount > 0 && Tiles.doesIntersect(true, x, y + amount, 30, 45)) {
       amount = (short)(amount - 1);
       fall_time = 0;
     } 
     if (amount > 50)
       amount = 50; 
     y += amount;
     if (Tiles.doesIntersect(true, x, y + 1, 30, 45)) {
       jump_time = 0;
       canJump = true;
     } else {
       
       canJump = false;
     } 
     if (Tiles.doesIntersect(true, x, y, 30, 45)) {
       AnimationSpecific.dieAnimation();
     }
   }
 
   
   private static boolean canJump = false;
   private static final byte movespeed = 10;
   
   static void jump() {
     if (canJump) {
       canJump = false;
       fall_time = 0;
       jump_time = 20;
     } 
   }
 
 
   
   private static void moveLeft() {
     if (Tiles.doesIntersect(true, x, y, 30, 45))
       return; 
     byte amount = 10;
     while (amount > 0 && Tiles.doesIntersect(true, x - amount, y, 30, 45))
       amount = (byte)(amount - 1); 
     x -= amount;
   }
   
   private static void moveRight() {
     if (Tiles.doesIntersect(true, x, y, 30, 45))
       return; 
     byte amount = 10;
     while (amount > 0 && Tiles.doesIntersect(true, x + amount, y, 30, 45))
       amount = (byte)(amount - 1); 
     x += amount;
   }
 
 
   
   static int getY() {
     return y;
   }
   
   static void setY(int y) {
     Player.y = y;
   }
   
   static int getX() {
     return x;
   }
   
   static void setX(int x) {
     Player.x = x;
   }
 }
