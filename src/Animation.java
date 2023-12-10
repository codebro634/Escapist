 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.util.ArrayList;
 
 
 
 public class Animation
 {
   public static boolean isAnimating() {
     return !(!images.isEmpty() && !texts.isEmpty());
   }
   
   public static void renderAnimations(Graphics g) {
     int i;
     for (i = 0; i < images.size(); i++) {
       if (((Image)images.get(i)).when > 0) {
         ((Image)images.get(i)).when -= 50;
       }
       else if (((Image)images.get(i)).duration > 0) {
         ((Image)images.get(i)).duration -= 50;
         g.drawImage(((Image)images.get(i)).image, ((Image)images.get(i)).x, ((Image)images.get(i)).y, ((Image)images.get(i)).width, ((Image)images.get(i)).height, null);
       } else {
         
         images.remove(i);
       } 
     } 
     
     for (i = 0; i < texts.size(); i++) {
       if (((Text)texts.get(i)).when > 0) {
         ((Text)texts.get(i)).when -= 50;
       }
       else if (((Text)texts.get(i)).duration > 0) {
         ((Text)texts.get(i)).duration -= 50;
         g.setFont(new Font(((Text)texts.get(i)).font, 0, ((Text)texts.get(i)).size));
         g.setColor(((Text)texts.get(i)).color);
         g.drawString(((Text)texts.get(i)).text, ((Text)texts.get(i)).x, ((Text)texts.get(i)).y);
       } else {
         
         texts.remove(i);
       } 
     } 
   }
   
   private static ArrayList<Image> images = new ArrayList<>();
   
   public static void Image(BufferedImage image, int x, int y, int width, int height, int duration, int when) {
     images.add(new Image(image, x, y, width, height, duration, when));
   }
   
   private static ArrayList<Text> texts = new ArrayList<>();
   
   public static void Text(int x, int y, int size, Color color, String font, String text, int duration, int when) {
     texts.add(new Text(x, y, size, color, font, text, duration, when));
   }
 }
