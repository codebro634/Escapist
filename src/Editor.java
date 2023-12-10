 import java.awt.Rectangle;
 import javax.swing.JOptionPane;
 
 public class Editor
 {
   static final String editingCode = "asf123";
   static boolean editing = false;
   static boolean firstMarkedHorizontal;
   static short startXHorizontal;
   
   static void placeCommonBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5))
       return; 
     LevelControle.aktiveTiles.add(new T_common(false, (short)(x - x % 50), (short)(y - y % 50)));
   }
   static short startYHorizontal; static boolean firstMarkedVertikal; static short startXVertikal; static short startYVertikal;
   static void placeKillingBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5))
       return; 
     LevelControle.aktiveTiles.add(new T_killing(false, (short)(x - x % 50), (short)(y - y % 50)));
   }
   
   static void placeVanishingBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5))
       return; 
     short[] attributes = vanishingAttr();
     if (attributes != null)
       LevelControle.aktiveTiles.add(new T_vanishing(false, (short)(x - x % 50), (short)(y - y % 50), attributes[0], attributes[1])); 
   }
   private static short[] vanishingAttr() {
     short stayTime;
     short awayTime;
     try {
       stayTime = Short.parseShort(JOptionPane.showInputDialog("Staying time : "));
       awayTime = Short.parseShort(JOptionPane.showInputDialog("Vanished time : "));
     } catch (Exception e) {
       MessageFrame.errMessage(" 32 684 > Vanished time & Staying time > 0"); return null;
     }  if (stayTime <= 0 || awayTime <= 0) {
       MessageFrame.errMessage(" 32 684 > Vanished time & Staying time > 0");
       return null;
     } 
     return new short[] { stayTime, awayTime };
   }
 
 
 
 
   
   static void placeHorizontalBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5)) {
       return;
     }
     if (firstMarkedHorizontal) {
       short[] args = horizontalAttr();
       if (args != null) {
         LevelControle.aktiveTiles.add(new T_horizontal(false, startXHorizontal, (short)(x - x % 50), startYHorizontal, args[0]));
         firstMarkedHorizontal = false;
       } 
     } else {
       
       firstMarkedHorizontal = true;
       startYHorizontal = (short)(y - y % 50);
       startXHorizontal = (short)(x - x % 50);
     } 
   }
   
   private static short[] horizontalAttr() {
     short movespeed;
     try {
       movespeed = Byte.parseByte(JOptionPane.showInputDialog("Speed : "));
     } catch (Exception e) {
       MessageFrame.errMessage("128 > Speed > 0"); firstMarkedHorizontal = false; return null;
     }  if (movespeed <= 0) {
       firstMarkedHorizontal = false;
       MessageFrame.errMessage("128 > Speed > 0");
       return null;
     } 
     return new short[] { movespeed };
   }
 
 
 
 
   
   static void placeVertikalBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5)) {
       return;
     }
     if (firstMarkedVertikal) {
       byte[] args = vertikalAttr();
       if (args != null) {
         LevelControle.aktiveTiles.add(new T_vertikal(false, startYVertikal, (short)(y - y % 50), startXVertikal, args[0]));
         firstMarkedVertikal = false;
       } 
     } else {
       
       firstMarkedVertikal = true;
       startYVertikal = (short)(y - y % 50);
       startXVertikal = (short)(x - x % 50);
     } 
   }
   
   private static byte[] vertikalAttr() {
     byte movespeed;
     try {
       movespeed = Byte.parseByte(JOptionPane.showInputDialog("Speed : "));
     } catch (Exception e) {
       MessageFrame.errMessage("32 000 > Speed > 0"); firstMarkedVertikal = false; return null;
     }  if (movespeed <= 0) {
       MessageFrame.errMessage("128 > Speed > 0");
       firstMarkedVertikal = false;
       return null;
     } 
     return new byte[] { movespeed };
   }
   
   static void placeShootingBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5))
       return; 
     short[] args = shootingAttr();
     if (args != null)
       LevelControle.aktiveTiles.add(new T_shooting(false, (short)(x - x % 50), (short)(y - y % 50), (byte)args[0], (byte)args[1], args[2])); 
   }
   private static short[] shootingAttr() {
     byte direction;
     byte missileSpeed;
     short missileRate;
     try {
       direction = Byte.parseByte(JOptionPane.showInputDialog("Shooting  Direction (1 = north | 2 = south | 3 = west | 4 = east) : "));
       missileSpeed = Byte.parseByte(JOptionPane.showInputDialog("Missilespeed : "));
       missileRate = Short.parseShort(JOptionPane.showInputDialog("Shootingrate (milliseconds) : "));
     } catch (Exception e) {
       MessageFrame.errMessage("32 000 > Shootingrate > 0 | 5 > Direction > 0 | 50 > Speed > 0"); return null;
     }  if (direction < 1 || direction > 4 || missileSpeed > 49 || missileSpeed <= 0 || missileRate <= 0) {
       MessageFrame.errMessage("32 000 > Shootingrate > 0 | 5 > Direction > 0 | 50  > Speed > 0");
       return null;
     } 
     return new short[] { direction, missileSpeed, missileRate };
   }
   
   static void placeCoinBlock(short x, short y) {
     if (!Tiles.canPlace(x, y, 5, 5))
       return; 
     LevelControle.aktiveTiles.add(new T_Coin(false, (short)(x - x % 50), (short)(y - y % 50)));
   }
   
   static void removeBlock(int x, int y) {
     for (int i = 0; i < LevelControle.aktiveTiles.size(); i++) {
       if ((new Rectangle(((Tiles)LevelControle.aktiveTiles.get(i)).Xpos, ((Tiles)LevelControle.aktiveTiles.get(i)).Ypos, 50, 50)).intersects(new Rectangle(x, y, 5, 5)) && !((Tiles)LevelControle.aktiveTiles.get(i)).isAutoGenerated) {
         LevelControle.aktiveTiles.remove(i);
         return;
       } 
     } 
   }
   
   static void changeAttributes(int x, int y) {
     for (int i = 0; i < LevelControle.aktiveTiles.size(); i++) {
       if (((Tiles)LevelControle.aktiveTiles.get(i)).intersection(false, x, y, 5, 5) && !((Tiles)LevelControle.aktiveTiles.get(i)).isAutoGenerated) {
         short[] args; String[] data; byte[] args2; Tiles newTile = null;
         switch (((Tiles)LevelControle.aktiveTiles.get(i)).getID()) {
           case 3:
             args = vanishingAttr();
             if (args != null)
               newTile = new T_vanishing(false, (short)(x - x % 50), (short)(y - y % 50), args[0], args[1]); 
             break;
           case 4:
             data = ((Tiles)LevelControle.aktiveTiles.get(i)).getArgs().split("/")[0].split(",");
             args = horizontalAttr();
             if (args != null)
               newTile = new T_horizontal(false, Short.parseShort(data[0]), Short.parseShort(data[1]), Short.parseShort(data[2]), args[0]); 
             break;
           case 5:
             data = ((Tiles)LevelControle.aktiveTiles.get(i)).getArgs().split("/")[0].split(",");
             args2 = vertikalAttr();
             if (args2 != null)
               newTile = new T_vertikal(false, Short.parseShort(data[0]), Short.parseShort(data[1]), Short.parseShort(data[2]), args2[0]); 
             break;
           case 6:
             args = shootingAttr();
             if (args != null)
               newTile = new T_shooting(false, (short)(x - x % 50), (short)(y - y % 50), (byte)args[0], (byte)args[1], args[2]); 
             break;
         } 
         if (newTile != null) {
           LevelControle.aktiveTiles.remove(i);
           LevelControle.aktiveTiles.add(newTile);
         } 
       } 
     } 
   }
 }
