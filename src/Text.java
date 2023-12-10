 import java.awt.Color;
 
 class Text
 {
   final int x;
   final int y;
   final int size;
   final Color color;
   final String font;
   final String text;
   int duration;
   int when;
   
   Text(int x, int y, int size, Color color, String font, String text, int duration, int when) {
     this.x = x;
     this.y = y;
     this.size = size;
     this.color = color;
     this.font = font;
     this.text = text;
     this.duration = duration;
     this.when = when;
   }
 }