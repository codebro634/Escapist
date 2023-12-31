 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 
 
 public class LevelControle
 {
   static ArrayList<Tiles> aktiveTiles = new ArrayList<>();
   
   static void render(Graphics g) {
     for (Tiles t : aktiveTiles)
       t.render(g); 
     if (Editor.editing) {
       
       try {
         Graphics2D g2 = (Graphics2D)g;
         g2.setStroke(new BasicStroke(5.0F));
       } catch (Exception exception) {}
       
       g.drawRect(Events.mouseX - Events.mouseX % 50, Events.mouseY - Events.mouseY % 50, 50, 50);
       g.setFont(new Font("Arial", 1, 30));
       g.clearRect(700, Controle.c.getHeight() - 35, g.getFontMetrics(new Font("Arial", 1, 30)).stringWidth("Editing : enabled"), 30);
       g.drawString("Editing : enabled", 700, Controle.c.getHeight() - 10);
       if (Editor.firstMarkedHorizontal) {
         g.setColor(Color.GREEN);
         g.drawRect(Editor.startXHorizontal, Editor.startYHorizontal, 50, 50);
         g.drawRect(Events.mouseX - Events.mouseX % 50, Editor.startYHorizontal, 50, 50);
       } 
       if (Editor.firstMarkedVertikal) {
         g.setColor(Color.GREEN);
         g.drawRect(Editor.startXVertikal, Editor.startYVertikal, 50, 50);
         g.drawRect(Editor.startXVertikal, Events.mouseY - Events.mouseY % 50, 50, 50);
       } 
     } 
     g.drawImage(Controle.homescreen, -4 + 50 * Controle.c.getWidth() / 50, 0, 50, 50, null);
     g.drawImage(Controle.vertikalBlock, 0, 0, 50, 50, null);
     g.drawImage(Controle.deaths, 300, 0, 50, 50, null);
     g.drawImage(Controle.coin, 650, 7, 35, 35, null);
     g.setFont(new Font("Berlin Sans FB Demi", 1, 50));
     g.setColor(Color.lightGray);
     g.drawString(": " + currentLevel, 50, 37);
     g.drawString(": " + deaths, 350, 37);
     g.drawString(": " + player_coin, 700, 37);
     g.drawString("FPS : " + Controle.c.FPS, 1100, 37);
   }
   
   private static int currentLevel = -1;
   
   static int player_coin = 0;
   
   static int getCurrentLevel() {
     return currentLevel;
   }
   
   static void setCurrentLevel(byte level) {
     currentLevel = level;
   }
   
   private static int deaths = 0;
   
   static int getDeaths() {
     return deaths;
   }
   
   static void setDeaths(int i) {
     deaths = i;
   }
   
   static void addDeath() {
     deaths++;
   }
   
   static void startGame() {
     MessageFrame.disposeFrame();
     Controle.c.setGamestate((byte)2);
     getTilesLevel((byte)currentLevel);
     reset();
   }
   
   static void reset() {
     Player.setX(0);
     Player.setY(50 * (1 + Controle.c.getHeight() / 50 / 2));
   }
   
   static void putEnd() {
     Player.setX(50 * (-1 + Controle.c.getWidth() / 50) + 30 + 40);
     Player.setY(50 * (1 + Controle.c.getHeight() / 50 / 2));
   }
   
   static boolean changeLevel(byte newLevel) {
     if (newLevel > 0) {
       if (newLevel > currentLevel) {
         reset();
       } else {
         putEnd();
       } 
       setTilesLevel((byte)currentLevel);
       currentLevel = newLevel;
       getTilesLevel(newLevel);
       
       return true;
     } 
     return false;
   }
 
   
   static void setTilesLevel(byte level) {
     if (level < 1) {
       return;
     }
     
     File levelFile = new File("Level_" + level + ".dat");
     if (!levelFile.exists()) {
       try {
         levelFile.createNewFile();
         (new File("Level_" + level + ".dat")).createNewFile();
       } catch (IOException e) {
         e.printStackTrace();
       } 
     }
     FileWriter writeFile = null;
     BufferedWriter writer = null;
     try {
       writeFile = new FileWriter(levelFile);
       writer = new BufferedWriter(writeFile);
       
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 1 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 2 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 3 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 4 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 5 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 6 && !t.isAutoGenerated)
           writer.write(t.getArgs()); 
       } 
       writer.newLine();
       for (Tiles t : aktiveTiles) {
         if (t.getID() == 7 && !t.isAutoGenerated) {
           writer.write(t.getArgs());
         }
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       try {
         if (writer != null) {
           writer.close();
         }
       } catch (Exception exception) {}
     } 
   }
 
 
   
   static void getTilesLevel(byte level) {
     if (level < 1) {
       return;
     }
     AnimationSpecific.clearAll();
     if (level < 10) {
       AnimationSpecific.addAnimation(Controle.c.getWidth() / 65, (int)(Controle.c.getHeight() / 1.8D), "Level : 00" + level, Color.lightGray, new Font("Berlin Sans FB Demi", 2, 300), 2500);
     } else if (level < 100) {
       AnimationSpecific.addAnimation(Controle.c.getWidth() / 65, (int)(Controle.c.getHeight() / 1.8D), "Level : 0" + level, Color.lightGray, new Font("Berlin Sans FB Demi", 2, 300), 2500);
     } else {
       AnimationSpecific.addAnimation(Controle.c.getWidth() / 65, (int)(Controle.c.getHeight() / 1.8D), "Level : " + level, Color.lightGray, new Font("Berlin Sans FB Demi", 2, 300), 2500);
     } 
     
     aktiveTiles.clear();
     
     for (short s = 0; s <= Controle.c.getWidth() / 50; s = (short)(s + 1)) {
       aktiveTiles.add(new T_common(true, (short)(s * 50), (short)0));
       aktiveTiles.add(new T_killing(true, (short)(s * 50), (short)(Controle.c.getHeight() - Controle.c.getHeight() % 50)));
     } 
     for (int i = 0; i <= Controle.c.getHeight() / 50; i++) {
       if (i != Controle.c.getHeight() / 50 / 2 && i != Controle.c.getHeight() / 50 / 2 + 1) {
         aktiveTiles.add(new T_common(true, (short)0, (short)(i * 50)));
         aktiveTiles.add(new T_common(true, (short)(Controle.c.getWidth() - Controle.c.getWidth() % 50), (short)(i * 50)));
       } 
     } 
     aktiveTiles.add(new T_common(true, (short)50, (short)(50 * (2 + Controle.c.getHeight() / 50 / 2))));
     aktiveTiles.add(new T_common(true, (short)(Controle.c.getWidth() / 50 * 50 - 50), (short)(50 * (2 + Controle.c.getHeight() / 50 / 2))));
     
     FileReader readFile = null;
     BufferedReader reader = null;
     
     File levelFile = new File("Level_" + level + ".dat");
     if (!levelFile.exists()) {
       try {
         levelFile.createNewFile();
         (new File("Level_" + level + ".dat")).createNewFile();
       } catch (IOException e) {
         e.printStackTrace();
       } 
     }
 
     
     try {
       readFile = new FileReader("Level_" + level + ".dat");
       reader = new BufferedReader(readFile);
 
       
       String line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 2)
                 aktiveTiles.add(new T_common(false, (short)Integer.parseInt(args[0]), (short)Integer.parseInt(args[1]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 2)
                 aktiveTiles.add(new T_killing(false, (short)Integer.parseInt(args[0]), (short)Integer.parseInt(args[1]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 4)
                 aktiveTiles.add(new T_vanishing(false, Short.parseShort(args[0]), Short.parseShort(args[1]), Short.parseShort(args[2]), Short.parseShort(args[3]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 4)
                 aktiveTiles.add(new T_horizontal(false, Short.parseShort(args[0]), Short.parseShort(args[1]), Short.parseShort(args[2]), Short.parseShort(args[3]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 4)
                 aktiveTiles.add(new T_vertikal(false, Short.parseShort(args[0]), Short.parseShort(args[1]), Short.parseShort(args[2]), Byte.parseByte(args[3]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 5)
                 aktiveTiles.add(new T_shooting(false, Short.parseShort(args[0]), Short.parseShort(args[1]), Byte.parseByte(args[2]), Byte.parseByte(args[3]), Short.parseShort(args[4]))); 
             } 
             b++; }
         
         } 
       } 
       line = reader.readLine();
       if (line != null) {
         String[] elements = line.split("/");
         if (elements != null && elements.length > 0) {
           byte b; int j; String[] arrayOfString; for (j = (arrayOfString = elements).length, b = 0; b < j; ) { String str = arrayOfString[b];
             if (str.contains(",")) {
               String[] args = str.split(",");
               if (args.length == 2)
                 aktiveTiles.add(new T_Coin(false, Short.parseShort(args[0]), Short.parseShort(args[1]))); 
             } 
             b++; }
         
         } 
       } 
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       try {
         if (reader != null) {
           reader.close();
         }
       } catch (IOException e) {
         e.printStackTrace();
       } 
     } 
   }
 }
