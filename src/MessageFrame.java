 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Point;
 import java.awt.Toolkit;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 
 public class MessageFrame
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   static JFrame f;
   static MessageFrame m = new MessageFrame(); byte Case;
   
   private static void Frame_setup() {
     f.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Controle.realCursor, new Point(f.getX() + 10, f.getY()), "img"));
     f.getContentPane().add(m);
     f.setResizable(false);
     f.setVisible(true);
     f.addMouseListener(Event.e);
   }
   private String message;
   static void errMessage(String message) {
     f = new JFrame("ERROR");
     Frame_setup();
     f.setSize(500, 200);
     f.setIconImage(Controle.error);
     m.Case = 1;
     m.message = message;
     f.setLocationRelativeTo(Controle.c);
   }
   
   static void enabledEditingMessage() {
     f = new JFrame("Editing enabled");
     Frame_setup();
     f.setSize(480, 1000);
     f.setIconImage(Controle.editing);
     m.Case = 2;
     f.setLocationRelativeTo(Controle.c);
   }
   
   static void disposeFrame() {
     if (f != null) {
       f.dispose();
     }
   }
 
   
   public void paintComponent(Graphics g) {
     int i;
     g.clearRect(0, 0, getWidth(), getHeight());
     switch (this.Case) {
       case 1:
         g.fillRect(0, 0, getWidth(), getHeight());
         g.setColor(Color.WHITE);
         g.setFont(new Font("Arial", 1, 30));
         g.drawImage(Controle.error, 15, 15, 75, 75, null);
         g.drawString("Syntax Error : ", 120, 60);
         g.setFont(new Font("Arial", 0, 15));
         g.drawString(this.message, 15, 135);
         g.setColor(Color.black);
         g.fillRect(400, 15, 50, 50);
         g.setColor(Color.gray);
         g.fillRect(405, 20, 40, 40);
         g.setColor(Color.WHITE);
         g.drawString("OK", 415, 45);
         break;
       case 2:
         g.fillRect(0, 0, getWidth(), getHeight());
         g.drawImage(Controle.coin, 15, 25, 50, 50, null);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Arial", 1, 30));
         g.drawImage(Controle.tile2, 15, 100, 50, 50, null);
         g.drawImage(Controle.killingBlock, 15, 175, 50, 50, null);
         g.drawImage(Controle.vanishingBlock, 15, 250, 50, 50, null);
         g.drawImage(Controle.birdRIGHT[0], 15, 325, 50, 50, null);
         g.drawImage(Controle.vertikalBlock, 15, 400, 50, 50, null);
         g.drawImage(Controle.shootingBlock, 15, 475, 50, 50, null);
         for (i = 0; i < 7; i++) {
           g.drawString("+", 200, i * 75 + 65);
           g.drawString("=", 90, i * 75 + 65);
           g.drawImage(Controle.mauszeiger, 135, 25 + i * 75, 50, 50, null);
         } 
         g.drawString("                      'U'", 100, 65);
         g.drawString("'Q'", 275, 135);
         g.drawString("'W'", 275, 205);
         g.drawString("'E'", 275, 280);
         g.drawString("'R'", 275, 355);
         g.drawString("'T'", 275, 430);
         g.drawString("'Z'", 275, 505);
         g.drawString("Teleport             =   SHIFT + ", 15, 580);
         g.drawImage(Controle.character, 160, 540, 50, 50, null);
         g.drawImage(Controle.mauszeiger, 400, 545, 50, 50, null);
         g.drawString("Go to next level =     'g' ", 15, 655);
         g.drawString("Go to previous level =     'h' ", 15, 730);
         g.drawString("Reload level =     'f' ", 15, 805);
         g.drawString("Remove any tile = RIGHT +  ", 15, 880);
         g.drawString("Change attr. = SHIFT RIGHT +  ", 15, 955);
         g.drawImage(Controle.mauszeiger, 435, 915, 50, 50, null);
         g.drawImage(Controle.mauszeiger, 400, 840, 50, 50, null);
         break;
     } 
   }
 }