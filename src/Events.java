 import java.awt.Rectangle;
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;
 import java.awt.event.MouseMotionListener;
 import javax.swing.JOptionPane;
 import javax.swing.SwingUtilities;
 
 
 public class Events
   implements KeyListener, MouseListener, MouseMotionListener
 {
   static Events events = new Events();
   static boolean aPressed;
   static boolean dPressed;
   static short mouseX;
   static short mouseY;
   
   public void keyPressed(KeyEvent e) {
     switch (Controle.c.getGamestate()) {
 
       
       case 2:
         switch (e.getKeyChar()) {
           case 'a':
             aPressed = true;
             break;
           case 'd':
             dPressed = true;
             break;
         } 
         break;
     } 
   }
   
   public void keyReleased(KeyEvent e) {
     switch (Controle.c.getGamestate()) {
 
       
       case 2:
         switch (e.getKeyChar()) {
           case 'a':
             aPressed = false;
             Player.lastDirection = false;
             break;
           case 'd':
             dPressed = false;
             Player.lastDirection = true;
             break;
         } 
         break;
     } 
   }
   
   public void keyTyped(KeyEvent e) {
     switch (Controle.c.getGamestate()) {
       case 1:
         switch (e.getKeyChar()) {
           case 'c':
             MessageFrame.enabledEditingMessage();
             break;
         } 
       case 2:
         switch (e.getKeyChar()) {
           case 'q':
             if (Editor.editing)
               Editor.placeCommonBlock(mouseX, mouseY); 
             break;
           case 'w':
             if (Editor.editing)
               Editor.placeKillingBlock(mouseX, mouseY); 
             break;
           case 'e':
             if (Editor.editing)
               Editor.placeVanishingBlock(mouseX, mouseY); 
             break;
           case 'r':
             if (Editor.editing)
               Editor.placeHorizontalBlock(mouseX, mouseY); 
             break;
           case 't':
             if (Editor.editing)
               Editor.placeVertikalBlock(mouseX, mouseY); 
             break;
           case 'z':
             if (Editor.editing)
               Editor.placeShootingBlock(mouseX, mouseY); 
             break;
           case 'u':
             if (Editor.editing)
               Editor.placeCoinBlock(mouseX, mouseY); 
             break;
           case 'f':
             if (Editor.editing) {
               LevelControle.setTilesLevel((byte)LevelControle.getCurrentLevel());
               LevelControle.getTilesLevel((byte)LevelControle.getCurrentLevel());
             } 
             break;
           case 'g':
             if (Editor.editing)
               LevelControle.changeLevel((byte)(LevelControle.getCurrentLevel() + 1)); 
             break;
           case 'h':
             if (Editor.editing)
               LevelControle.changeLevel((byte)(LevelControle.getCurrentLevel() - 1)); 
             break;
           case ' ':
             Player.jump();
             break;
         } 
         break;
     } 
   }
 
 
   
   public void mouseDragged(MouseEvent e) {}
 
 
   
   public void mouseMoved(MouseEvent e) {
     mouseX = (short)(e.getX() - 5);
     mouseY = (short)(e.getY() - 27);
   }
 
   
   public void mouseClicked(MouseEvent e) {}
 
   
   public void mouseEntered(MouseEvent arg0) {}
   
   public void mouseExited(MouseEvent arg0) {}
   
   public void mousePressed(MouseEvent arg0) {}
   
   public void mouseReleased(MouseEvent e) {
     switch (Controle.c.getGamestate()) {
       case 1:
         if ((new Rectangle(e.getX() - 5, e.getY() - 27, 5, 5)).intersects(new Rectangle(50, 50, 150, 150))) {
           if (!Editor.editing) {
             try {
               if (JOptionPane.showInputDialog("Editing mode - Password: ").equals("asf123"))
               { Editor.editing = true;
                 MessageFrame.enabledEditingMessage(); }
               else
               
               { MessageFrame.errMessage("Wrong password!"); } 
             } catch (Exception exception) {}
           } else {
             
             Editor.editing = false;
             JOptionPane.showMessageDialog(null, "Editing disabled");
           } 
         }
         if ((new Rectangle(e.getX() - 5, e.getY() - 27, 5, 5)).intersects(new Rectangle(1050, 400, 150, 450))) {
           LevelControle.startGame();
         }
         break;
       case 2:
         if (SwingUtilities.isRightMouseButton(e) && Editor.editing) {
           if (e.isShiftDown()) {
             Editor.changeAttributes(mouseX, mouseY);
           } else {
             Editor.removeBlock(mouseX, mouseY);
           } 
         } else if (Editor.editing && 
           e.isShiftDown()) {
           int newX = mouseX - mouseX % 50, newY = mouseY - mouseY % 50;
           if (!Tiles.doesIntersect(false, newX, newY, 30, 45)) {
             Player.setX(newX);
             Player.setY(newY);
           } 
         } 
 
 
 
 
         
         if ((new Rectangle(-3 + 50 * Controle.c.getWidth() / 50, 0, 50, 50)).intersects(new Rectangle(e.getX() - 5, e.getY() - 27, 5, 5))) {
           Controle.c.setGamestate((byte)1);
           LevelControle.setTilesLevel((byte)LevelControle.getCurrentLevel());
         } 
         break;
     } 
   }
 }
