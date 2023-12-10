 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Point;
 import java.awt.Toolkit;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import java.awt.image.BufferedImage;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Timer;
 import javax.imageio.ImageIO;
 import javax.swing.JFrame;
 import javax.swing.JPanel;
 
 public class Controle
   extends JPanel
 {
   private static final long serialVersionUID = 1L;
   static final int renderingSpeed = 50;
   private static JFrame f = new JFrame("Escapist - by Blum3t0pf (v 2.4)");
   private static final int startHeight = 1025;
   private static final int startWidth = 1500;
   
   public static void main(String[] args0) {
     f.getContentPane().add(c);
     f.setResizable(false);
     f.setSize(1500, 1025);
     f.addKeyListener(Events.events);
     f.addMouseListener(Events.events);
     f.addMouseMotionListener(Events.events);
     f.setIconImage(character);
     f.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(realCursor, new Point(f.getX() + 10, f.getY()), "img"));
     f.setVisible(true);
     (new Timer()).scheduleAtFixedRate(new Renderer(), 0L, 50L);
     f.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent) {
             LevelControle.setTilesLevel((byte)LevelControle.getCurrentLevel());
             Controle.setLevelDeaths();
             try {
               Thread.sleep(100L);
             } catch (InterruptedException e) {
               e.printStackTrace();
             } 
             System.exit(-1);
           }
         });
     f.setLocationRelativeTo((Component)null);
     getLevelDeaths();
   }
 
   
   private static void getLevelDeaths() {
     FileReader readFile = null;
     BufferedReader reader = null;
     
     File levelFile = new File("currentStats.dat");
     if (!levelFile.exists()) {
       try {
         levelFile.createNewFile();
         (new File("currentStats.dat")).createNewFile();
       } catch (IOException e) {
         e.printStackTrace();
       } 
     }
 
     
     try {
       readFile = new FileReader("currentStats.dat");
       reader = new BufferedReader(readFile);
       
       String line = reader.readLine();
       if (line == null || line.equals("")) {
         LevelControle.setCurrentLevel((byte)1);
         LevelControle.setDeaths(0);
       } else {
         
         String[] args = line.split("/");
         LevelControle.setCurrentLevel(Byte.parseByte(args[0]));
         LevelControle.getTilesLevel((byte)LevelControle.getCurrentLevel());
         LevelControle.setDeaths(Integer.parseInt(args[1]));
         LevelControle.player_coin = Integer.parseInt(args[2]);
       }
     
     }
     catch (Exception e) {
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
   
   private static void setLevelDeaths() {
     File levelFile = new File("currentStats.dat");
     if (!levelFile.exists()) {
       try {
         levelFile.createNewFile();
         (new File("currentStats.dat")).createNewFile();
       } catch (IOException e) {
         e.printStackTrace();
       } 
     }
     FileWriter writeFile = null;
     BufferedWriter writer = null;
     try {
       writeFile = new FileWriter(levelFile);
       writer = new BufferedWriter(writeFile);
       
       writer.write(LevelControle.getCurrentLevel() + "/" + LevelControle.getDeaths() + "/" + LevelControle.player_coin);
     }
     catch (Exception e) {
       e.printStackTrace();
     } finally {
       try {
         if (writer != null) {
           writer.close();
         }
       } catch (Exception exception) {}
     } 
   }
 
   
   ClassLoader cl = getClass().getClassLoader(); static BufferedImage character; static BufferedImage characterUPSIDEDOWN; static BufferedImage homescreen; static BufferedImage background; static BufferedImage editing; static BufferedImage deaths; static BufferedImage openDoor; static BufferedImage error; static BufferedImage mauszeiger; static BufferedImage realCursor;
   static BufferedImage coin;
   static BufferedImage pointer2;
   static BufferedImage[] right = new BufferedImage[3]; static BufferedImage[] left = new BufferedImage[3]; static BufferedImage[] birdRIGHT = new BufferedImage[4]; static BufferedImage[] birdLEFT = new BufferedImage[4]; static BufferedImage tile1; static BufferedImage tile2; static BufferedImage killingBlock; static BufferedImage shootingBlock; static BufferedImage missile;
   static BufferedImage vanishingBlock;
   static BufferedImage vertikalBlock;
   static Controle c = new Controle();
 

   private byte gamestate;
 

   short FPS;
 
   static boolean tickFinished;
 
   private static byte time;
 

   private Controle() {
     this.gamestate = 1; try { character = ImageIO.read(this.cl.getResourceAsStream("char.png")); characterUPSIDEDOWN = ImageIO.read(this.cl.getResourceAsStream("upsidedown.png")); homescreen = ImageIO.read(this.cl.getResourceAsStream("homescreen.png")); background = ImageIO.read(this.cl.getResourceAsStream("background.jpg")); tile1 = ImageIO.read(this.cl.getResourceAsStream("tile1.jpg")); tile2 = ImageIO.read(this.cl.getResourceAsStream("tile2.jpg")); killingBlock = ImageIO.read(this.cl.getResourceAsStream("killingBlock.png")); shootingBlock = ImageIO.read(this.cl.getResourceAsStream("shootingBlock.png")); missile = ImageIO.read(this.cl.getResourceAsStream("missile.png")); vanishingBlock = ImageIO.read(this.cl.getResourceAsStream("vanishingBlock.png")); vertikalBlock = ImageIO.read(this.cl.getResourceAsStream("vertikalBlock.png")); editing = ImageIO.read(this.cl.getResourceAsStream("editing.png")); deaths = ImageIO.read(this.cl.getResourceAsStream("deaths.png")); openDoor = ImageIO.read(this.cl.getResourceAsStream("openDoor.png")); error = ImageIO.read(this.cl.getResourceAsStream("err.png")); mauszeiger = ImageIO.read(this.cl.getResourceAsStream("mauszeiger.png")); realCursor = ImageIO.read(this.cl.getResourceAsStream("cursor.png")); coin = ImageIO.read(this.cl.getResourceAsStream("coin.png")); pointer2 = ImageIO.read(this.cl.getResourceAsStream("pointer2.png")); int i; for (i = 0; i < right.length; i++) { right[i] = ImageIO.read(this.cl.getResourceAsStream("right" + (i + 1) + ".png")); left[i] = ImageIO.read(this.cl.getResourceAsStream("left" + (i + 1) + ".png")); }
        for (i = 0; i < birdRIGHT.length; i++) { birdLEFT[i] = ImageIO.read(this.cl.getResourceAsStream("bird" + (i + 1) + "L.png")); birdRIGHT[i] = ImageIO.read(this.cl.getResourceAsStream("bird" + (i + 1) + ".png")); }
        }
     catch (Exception e) { e.printStackTrace(); }
      } byte getGamestate() { return this.gamestate; }
 
   
   void setGamestate(byte newstate) {
     this.gamestate = newstate;
   }
 
 
 
   
   public void paintComponent(Graphics g) {
     long time = System.currentTimeMillis();
     
     tickFinished = false;
     g.clearRect(0, 0, getWidth(), getHeight());
     g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
     
     switch (this.gamestate) {
       case 1:
         renderHomescreen(g);
         break;
       case 2:
         renderIngame(g);
         break;
     } 
     tickFinished = true;
     
     System.out.println("Der Tick hat " + (System.currentTimeMillis() - time) + " Millisekunden gebraucht (MAX " + '2' + ")");
   }
 
 
   
   private void renderHomescreen(Graphics g) {
     time = (byte)(time + 1);
     g.setColor(Color.darkGray);
     if (time >= 30)
       time = 0; 
     if (time <= 15)
       g.fillRect(700, 660, 150, 30); 
     g.drawImage(editing, 50, 50, 150, 150, null);
     g.setColor(Color.black);
     g.setFont(new Font("Arial", 1, 100));
     g.setColor(Color.black);
     g.clearRect(380, 50, 470, 150);
     g.clearRect(1030, 50, 340, 150);
     g.drawString("Escapist", 400, 150);
     g.drawString("v (2.4)", 1050, 150);
     g.drawImage(openDoor, 1050, 400, 150, 450, null);
     g.drawImage(deaths, 75, 330, 150, 150, null);
     g.drawImage(vertikalBlock, 75, 530, 150, 150, null);
     g.drawImage(coin, 75, 730, 150, 150, null);
     g.drawImage(character, 700, 500, 150, 150, null);
     g.drawImage(pointer2, 560, 525, 125, 125, null);
     g.setColor(Color.black);
     g.setFont(new Font("Berlin Sans FB Demi", 1, 150));
     g.drawString(": " + LevelControle.getDeaths(), 250, 450);
     g.drawString(": " + LevelControle.getCurrentLevel(), 250, 635);
     g.drawString(": " + LevelControle.player_coin, 250, 835);
   }
   
   private void renderIngame(Graphics g) {
     LevelControle.render(g);
     Player.render(g);
     AnimationSpecific.render(g);
     Animation.renderAnimations(g);
   }
 }
