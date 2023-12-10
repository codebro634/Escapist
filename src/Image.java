 import java.awt.image.BufferedImage;

 
 class Image
 {
   final int x;
   final int y;
   final int width;
   final int height;
   final BufferedImage image;
   int duration;
   int when;
   
   Image(BufferedImage image, int x, int y, int width, int height, int duration, int when) {
     this.image = image;
     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
     this.duration = duration;
     this.when = when;
   }
 }