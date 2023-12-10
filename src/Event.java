 import java.awt.Rectangle;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 

 
 class Event
   implements MouseListener
 {
   static Event e = new Event();
   
   public void mouseClicked(MouseEvent e) {
     switch (MessageFrame.m.Case) {
       case 1:
         if ((new Rectangle(e.getX() - 5, e.getY() - 27, 5, 5)).intersects(new Rectangle(400, 15, 50, 50)))
           MessageFrame.f.dispose(); 
         break;
     } 
   }
   
   public void mouseEntered(MouseEvent arg0) {}
   
   public void mouseExited(MouseEvent arg0) {}
   
   public void mousePressed(MouseEvent arg0) {}
   
   public void mouseReleased(MouseEvent e) {}
 }