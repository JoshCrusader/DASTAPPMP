/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

/**
 *
 * @author 11540907
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.filechooser.*;
public class Game extends JPanel implements ActionListener,MouseListener, MouseMotionListener, KeyListener{
    private int mySizeX;
    private int mySizeY;
    private int cellX;
    private int cellY;
    private String[][] Buttons;
    private boolean on = false;
    private MouseEvent mouse;
    public int interValX;
    public int interValY;
    private boolean startOn = false;
    private boolean goalOn = false;
    private boolean finals = false;
    private int animTime = 1;
    private Node tlatest;
    private Timer t;
    private Timer t2;
    
    private int counterAnim = 11;
    private int posx;
    private int posy;
    //things to paint//
    public ArrayList<Character> characters = new ArrayList<>();
    private ArrayList<Tower> towers = new ArrayList<>();
    public ArrayList<Bullet> bullets = new ArrayList<>();
    //
    
    private double formx;
    private double formy;
    private String direction = "r";
    private boolean tapos = false;
    
    
    private int rotation = 0;
    //ASTAR//
    Node[][]  nodes = new Node[cellY][cellX];
    ArrayList<Node> Close = new ArrayList<>();
    ArrayList<Node> Open = new ArrayList<>();
    int regNodes = 0;
    Node start=null;
    Node end=null;
    Node latest = null;
    Node prior = null;
    Timer searchT = new Timer(250,this);
    Timer t3 = new Timer(100,this);
    ArrayList <PathV2> Paths = new ArrayList<>();
    
    //game variables//
    int enemeyCounter = 0;
    
    //stats//
    int maxhealth = 1000;
    int health = 1000;
    int money = 9550; int allowance=0; //allowance = counter for next money
    
    public DashFrame DashF;
    public DashBoard DashB;
    
    
    //ui variables//
    boolean tabon=false;
    
    
    
    
    
    int goalx=-1;
    int goaly=-1;
    
    
    
    //TOWER VARIABLES//
    String[] selectedIconsG = {"Towers/head1t.png","Towers/head1t.png","Towers/head2.png"};
    String[] selectedIconsB = {"Towers/head1b.png","Towers/head1b.png","Towers/head2.png"};
    int selection = -1;
    
    
    //MOUSE VARIABLES//
    int mousex=0,selectx=0;
    int mousey=0,selecty=0;
    boolean wegood = false;
    Tower selectT = null;
    public Game(int mySizeX, int mySizeY, String[][] Buttons){
        int y = Buttons.length;
        int x = Buttons[0].length;
        this.setLayout(null);
        this.setFocusable(true);
        this.mySizeX = mySizeX;
        this.mySizeY = mySizeY;
        this.cellX = x;
        this.cellY = y;
        interValX = (int)Math.ceil((double)mySizeX/cellX);
        interValY = (int)Math.ceil((double)mySizeY/cellY);
        this.setSize(mySizeX, mySizeY);
        this.Buttons = new String[y][x];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        for(int i = 0; i < y; i++){
            for(int z = 0; z < x; z++){
                this.Buttons[i][z] = "#";
            }
        }
        this.Buttons = Buttons;
        t = new Timer(100,this);
        repaint();
        makeDash();
        t.start();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
            int nexx = 0;
            int nexy = 0;
            boolean yep  = false;
            for(int i = 0; i < cellY; i++){
                for(int z = 0; z < cellX; z++){
                    if(Buttons[i][z].equals(" ")){
                        g.setColor(Color.gray);
                    }
                    else if(Buttons[i][z].equals("1") || Buttons[i][z].equals("3") || Buttons[i][z].equals("S")){
                        g.setColor(Color.red);
                    }
                    else if(Buttons[i][z].equals("2") || Buttons[i][z].equals("4") || Buttons[i][z].equals("G")){
                        g.setColor(Color.green);
                    }
                    else{
                        g.setColor(Color.darkGray);
                    }
                    if(Buttons[i][z].equals("#")){
                        Image image = new ImageIcon("wall2.png").getImage();//wall2.png
                        g.drawImage(image,z*interValX, i*interValY, interValX, interValY, Color.yellow, this);

                    }
                    else if(Buttons[i][z].equals(" ")){
                        Image image = new ImageIcon("Tile2.jpeg").getImage();//Tile2.jpeg

                        g.drawImage(image,z*interValX, i*interValY, interValX, interValY, Color.darkGray, this);

                    }
                    else if(Buttons[i][z].equals("S")){
                        Image image = new ImageIcon("Tile2.jpeg").getImage();
                        g.drawImage(image,z*interValX, i*interValY, interValX, interValY, Color.yellow, this);

                        image = new ImageIcon("Portal.png").getImage();
                        AffineTransform at = AffineTransform.getTranslateInstance(interValX*z, interValY*i);
                        double a = (double)(interValX)/(double)(image.getWidth(this));
                        double b = (double)(interValY)/(double)(image.getHeight(this));
                        at.scale(a, b);
                        at.rotate(Math.toRadians(rotation),(double)(image.getWidth(this))/2.0,(double)(image.getHeight(this))/2.0);
                        Graphics2D g2d = (Graphics2D)g;
                        g2d.drawImage(image, at, null);
                    }
                    else if(Buttons[i][z].equals("G")){
                        Image image = new ImageIcon("Tile2.jpeg").getImage();//EXIT3.png
                        g.drawImage(image,z*interValX, i*interValY, interValX, interValY, this);
                        nexx = z*interValX-interValX/2;
                        nexy = i*interValY-interValY/2;
                        yep = true;
                        goalx = z*interValX;
                        goaly = i*interValY;
                    }
                    else{
                        g.fillRect(z*interValX, i*interValY, interValX, interValY);
                    }

                }
            }
            g.setColor(Color.cyan);
            if(selectT != null){
                g.drawOval(selectT.x+interValX/2-selectT.range, selectT.y+interValY/2-selectT.range, selectT.range*2, selectT.range*2);
            }g.setColor(Color.black);

                for(int i = 0; i < characters.size();i++){
                    Character me = characters.get(i);
                    g.drawImage(me.image,me.x, me.y, interValX, interValY,this);
                    if(me.health > 0){
                        g.setColor(Color.red);
                        g.fillRect(me.x, me.y, (int)(interValX*0.9), (int)(interValY*0.2));
                        g.setColor(Color.green);
                        g.fillRect(me.x, me.y, (int)(interValX*0.9*((double)me.health/(double)me.maxHealth)), (int)(interValY*0.2));
                        g.setColor(Color.black);
                        g.drawRect(me.x, me.y, (int)(interValX*0.9), (int)(interValY*0.2));
                    }

                }
                
                for(int i = 0; i < bullets.size();i++){
                    if(health > 0){
                        if(bullets.get(i).speed > bullets.get(i).animTime){
                            Bullet cool = bullets.get(i);
                            cool.paintMe(g);
                        }
                        else{
                            bullets.remove(bullets.get(i));
                        }
                    }

                }

                for(int i = 0; i < towers.size();i++){
                    if(health > 0){
                    Tower me = towers.get(i);
                    me.paintMe(g);
                    }

                }
                
                if(selection >= 0){
                    Image image=null;
                    if(wegood){
                        image = new ImageIcon(selectedIconsG[selection]).getImage();
                    }
                    else
                        image = new ImageIcon(selectedIconsB[selection]).getImage();
                    g.drawImage(image, selectx, selecty,interValX,interValY, this);
                }
                if (yep){
                Image image = new ImageIcon("Nexus.png").getImage();
                g.drawImage(image,nexx, nexy, interValX*2, interValY*2, this);
                }
                if(goalx >=0){
                    g.setColor(Color.red);
                    g.fillRect(goalx, goaly, (int)(interValX*0.9), (int)(interValY*0.2));
                    g.setColor(Color.green);
                    g.fillRect(goalx, goaly, (int)(interValX*0.9*((double)health/(double)maxhealth)), (int)(interValY*0.2));
                    g.setColor(Color.black);
                    g.drawRect(goalx, goaly, (int)(interValX*0.9), (int)(interValY*0.2));
                }
                if(health == 0){
                }
    }
    public void mouseDragged(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
        updateMouse(e);
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){
        
    }
    public void mouseExited(MouseEvent e){
    }
    public void mousePressed(MouseEvent e){
        updateMouse(e);
        //new Tower
        if(selection == 0 && money >= 200 && wegood){
            money -= 200;
            towers.add(new BabyTurret(this,selectx,selecty,interValX,interValY));
        }
        else if(selection == 1 && money >= 4500 && wegood){
            money-= 4500;
            towers.add(new TeslaCoil(this,selectx,selecty,interValX,interValY));
        }
        else if(selection == 2 && money >= 0 && wegood){
            money-= 0;
            towers.add(new PB101(this,selectx,selecty,interValX,interValY));
        }
    }
    public void mouseReleased(MouseEvent e){
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_UP){
         this.setBounds(this.getX(), this.getY()-10, mySizeX, mySizeY);
        }
        else if(e.getKeyCode() == e.VK_DOWN){
            this.setBounds(this.getX(), this.getY()+10, mySizeX, mySizeY);
        }
        else if(e.getKeyCode() == e.VK_LEFT){
            this.setBounds(this.getX()-10, this.getY(), mySizeX, mySizeY);
        }
        else if(e.getKeyCode() == e.VK_RIGHT){
            this.setBounds(this.getX()+10, this.getY(), mySizeX, mySizeY);
        }
        else if(e.getKeyCode() == e.VK_G){
        }
        else if(e.getKeyCode() == e.VK_ENTER){
           tabon = !tabon;
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(DashF == null){
                makeDash();
            }
            else if(DashF.isVisible()){
                DashF.setVisible(false);
            }
            else if(!DashF.isVisible()){
                DashF.setVisible(true);
            }
        }
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyTyped(KeyEvent e){
        
    }
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    
    int frequency = 100;
    int freqcount = 0;
    
    //ending//
    int endperc1 = 0;
    //
    public void actionPerformed(ActionEvent e){
        if(e.getSource() != t3){
                if(e.getSource() == t){
                    if(animTime == 9){
                        animTime = 1;
                    }
                    else{
                        animTime++;
                    }
                }


                ///make algorithm here
                if(enemeyCounter < frequency){
                    enemeyCounter++;
                }
                else{
                    makeEnemies();
                    if(freqcount < 3){
                        freqcount ++;
                    }
                    else{
                        hcap+=10;dcap+=5;
                        if(frequency != 10){
                        frequency -=10;
                        freqcount = 0;
                        }
                    }
                    enemeyCounter = 0;
                }
                for(int i = 0; i < characters.size();i++){
                    characters.get(i).update();
                    if(characters.get(i).tapos == false){
                        if(this.health - characters.get(i).Damage < 0 ){
                            this.health = 0;
                        }
                        else{
                        this.health -= ((double)(characters.get(i).Damage));
                        }
                        characters.remove(characters.get(i));
                    }
                    else if(characters.get(i).health <= 0){
                        if(characters.get(i).rot <= 1)
                            money+=characters.get(i).reward;
                        else if(characters.get(i).rot == 200)
                            characters.remove(characters.get(i));
                    }
                }


                if(allowance < 7){
                    allowance ++;
                }
                else{
                money+=1;allowance = 0;}

                rotation +=5;
                repaint();
                if(DashB != null){
                    DashB.updates();
                }
                if(health == 0){
                    t.stop();
                    t3.start();
                }
        }
        else{
            if(endperc1 < 100){
                endperc1++;
            }
            
        }
    }
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    ////////////////////////////////////////////////
    private void makeDash(){
        DashF = new DashFrame(this);
        DashB = new DashBoard(this,DashF);
        DashF.add(DashB);
        DashF.setVisible(true);
    }
    int hcap=0,dcap =0;
    public void makeEnemies(){
        for(int i = 0; i < cellY; i++){
            for(int z = 0; z < cellX; z++){
                if(Buttons[i][z].equals("S")){
                    //System.out.println("hehe xd");
                    String[][] Butoners = new String[cellY][cellX];
                    for(int b = 0; b < cellY; b++){
                        for(int a = 0; a < cellX; a++){
                            if(Buttons[b][a].equals("S") && (b != i || a != z)){
                                Butoners[b][a] = " ";
                            }
                            else{
                                Butoners[b][a] = Buttons[b][a];
                            }
                            //System.out.print(Butoners[b][a]);
                        }
                        //System.out.println("");
                    }
                    Path hehe = new Path(cellX,cellY,Butoners,interValX,interValY);
                    characters.add(new Minion(hehe,hcap,dcap));
                }
            }
        }
    }
    public void updateMouse(MouseEvent e){

       double MouseX = e.getPoint().getX();
       double MouseY = e.getPoint().getY();
       mousex = (int)MouseX;
       mousey = (int)MouseY;
       int divx = (int)(MouseX)/interValX;
       int divy = (int)(MouseY)/interValY;
       selectx = divx*interValX;
       selecty = divy*interValY;
       if(Buttons[divy][divx].equals("#")){
           wegood = true;
       }
       else
           wegood = false;
       selectT = null;
       for(int i = 0; i < towers.size();i++){
           if(selectx == towers.get(i).x && selecty == towers.get(i).y){
               wegood = false;selectT = towers.get(i);
           }
       }
       //new Tower
       if(selection == 0){
           selectT = new BabyTurret(this,selectx,selecty,interValX,interValY);
       }
       else if( selection == 1){
           selectT = new TeslaCoil(this,selectx,selecty,interValX,interValY);
       }
       else if( selection == 1){
           selectT = new PB101(this,selectx,selecty,interValX,interValY);
       }
    }

}