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
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.swing.filechooser.*;
public class EditorBeta extends JPanel implements ActionListener,MouseListener, MouseMotionListener, KeyListener{
    private int mySizeX;
    private int mySizeY;
    private int cellX;
    private int cellY;
    private String[][] Buttons;
    private boolean on = false;
    private MouseEvent mouse;
    private int interValX;
    private int interValY;
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
    
    private ArrayList<Character> characters = new ArrayList<>();
            
    private double formx;
    private double formy;
    private String direction = "r";
    private boolean tapos = false;
    public EditorBeta(int x, int y,int mySizeX, int mySizeY){
        this.setLayout(null);
        this.setFocusable(true);
        this.mySizeX = mySizeX;
        this.mySizeY = mySizeY;
        this.cellX = x;
        this.cellY = y;
        interValX = mySizeX/cellX;
        interValY = mySizeY/cellY;
        this.setSize(mySizeX, mySizeY);
        Buttons = new String[y][x];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        for(int i = 0; i < y; i++){
            for(int z = 0; z < x; z++){
                Buttons[i][z] = "#";
            }
        }
        t = new Timer(100,this);
        repaint();
        
    }
    public void paint(Graphics g){
        super.paintComponent(g);
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
                    
                }
                else if(Buttons[i][z].equals("G")){
                    Image image = new ImageIcon("EXIT3.png").getImage();
                    g.drawImage(image,z*interValX, i*interValY, interValX, interValY, Color.yellow, this);
                }
                else{
                    g.fillRect(z*interValX, i*interValY, interValX, interValY);
                }
                
            }
        }
            for(int i = 0; i < characters.size();i++){
                Character me = characters.get(i);
                g.drawImage(me.image,me.x, me.y, interValX, interValY,this);
                g.setColor(Color.cyan);
                for(int x = 0; x < me.RouteTaken.size()-1;x++){
                    int firstx = me.RouteTaken.get(x).x*interValX+interValX/2;
                    int firsty = me.RouteTaken.get(x).y*interValX+interValX/2;
                    int secondx = me.RouteTaken.get(x+1).x*interValX+interValX/2;
                    int secondy = me.RouteTaken.get(x+1).y*interValX+interValX/2;
                    g.drawLine(firstx, firsty, secondx, secondy);
                }
                if(me.RouteTaken.size() > 0 && me.RouteTaken.get(me.RouteTaken.size()-1) != null){
                    int firstx = me.RouteTaken.get(me.RouteTaken.size()-1).x*interValX+interValX/2;
                    int firsty = me.RouteTaken.get(me.RouteTaken.size()-1).y*interValX+interValX/2;
                    int secondx = me.x+interValX/2;
                    int secondy = me.y+interValX/2;
                    g.drawLine(firstx, firsty, secondx, secondy);
                }
                /*
                g.setColor(Color.green);
                g.fillRect(me.x, me.y, interValX-5, 10);
                g.setColor(Color.black);
                g.drawRect(me.x, me.y, interValX-5, 10);
                */
            }

    }
    public void mouseDragged(MouseEvent e){
        paintBox(e);
    }
    public void mouseMoved(MouseEvent e){
        paintBox(e);
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){
        
    }
    public void mouseExited(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){
        paintBox(e);
    }
    public void mouseReleased(MouseEvent e){
        finals = true;
        startOn = false;
        goalOn = false;
        paintBox(e);
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_S){
            startOn = !startOn;
            goalOn = false;
        }
        else if(e.getKeyCode() == e.VK_G){
            goalOn = !goalOn;
            startOn = false;
        }
        else if(e.getKeyCode() == e.VK_ENTER){
            Path pa = new Path(cellX,cellY,Buttons,interValX,interValY);
            characters.add(new Character(pa));
            
        }
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyTyped(KeyEvent e){
        
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == t){
            if(animTime == 9){
                animTime = 1;
            }
            else{
                animTime++;
            }
        }
        else if(e.getSource() ==t2 ){
            
        }
        for(int i = 0; i < characters.size();i++){
            characters.get(i).update();
            if(characters.get(i).tapos == false){
                characters.remove(characters.get(i));
            }
        }
        repaint();
    }
    public void paintBox(MouseEvent e){
        int mouseX = (int)e.getPoint().getX();
        int mouseY = (int)e.getPoint().getY();
        for(int i = 0; i < cellY; i++){
            for(int z = 0; z < cellX; z++){
                if(mouseX >= z*interValX && mouseX <= z*interValX+interValX && mouseY >= i*interValY && mouseY <= i*interValY+interValY){
                    if(!startOn && !goalOn){
                        if(finals == true){
                            if(Buttons[i][z].equals("1") || Buttons[i][z].equals("3")){
                                Buttons[i][z] = "S";
                                t.start();
                            }
                            else if(Buttons[i][z].equals("2") || Buttons[i][z].equals("4")){
                                Buttons[i][z] = "G";
                            }
                            finals = false;
                        }
                        else if(e.getButton() == MouseEvent.BUTTON1){
                            Buttons[i][z] = " ";
                        }
                        else if(e.getButton() == MouseEvent.BUTTON2 ||e.getButton() == MouseEvent.BUTTON3){
                            Buttons[i][z] = "#";
                        }
                        
                    }
                    else {
                        if(Buttons[i][z].equals(" ")){
                            if(startOn){
                                Buttons[i][z] = "1";
                            }
                            else if(goalOn){
                                Buttons[i][z] = "2";
                            }
                        }
                        else if(Buttons[i][z].equals("#")){
                            if(startOn){
                                Buttons[i][z] = "3";
                            }
                            else if(goalOn){
                                Buttons[i][z] = "4";
                            }
                        }
                    }
                }
                else if(startOn || goalOn){
                    if(Buttons[i][z].equals("1") || Buttons[i][z].equals("2")){
                        Buttons[i][z] = " ";
                    }
                    else if(Buttons[i][z].equals("3") || Buttons[i][z].equals("4")|| (Buttons[i][z].equals("S")
                            && startOn)|| (Buttons[i][z].equals("G") && goalOn)){
                        Buttons[i][z] = "#";
                        if((Buttons[i][z].equals("S")&& startOn)){
                            animTime = 1;
                        }
                    }
                }
                else if(!startOn && !goalOn){
                    if(Buttons[i][z].equals("1") || Buttons[i][z].equals("2")){
                        Buttons[i][z] = " ";
                    }
                    else if(Buttons[i][z].equals("3") || Buttons[i][z].equals("4")){
                        Buttons[i][z] = "#";
                    }
                }
            }
        }
       repaint();
    }
    public void saveMap() {
        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JOSH MAPS", "jmap", "Maps");
        chooser.setFileFilter(filter);
       chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try 
            {
                File f = new File(chooser.getSelectedFile()+".jmap");
                //FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                for(int i = 0; i < cellY; i++){
                    String k = "";
                    for(int z = 0; z < cellX; z++){
                        if(Buttons[i][z].equals("1") || Buttons[i][z].equals("2")|| Buttons[i][z].equals("3")|| Buttons[i][z].equals("4")){
                            k = k+"#";
                        }
                        else{
                            k = k+(Buttons[i][z]);
                        }
                    }
                    if(i > 0)
                        bw.newLine();
                    bw.write(k);
                }
                bw.close();
                //fw.write(sb.toString());
            } 
            catch (Exception ex) 
            {
            }
            }
    }
    public void loadMap() {
        String sb = "TEST CONTENT";
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JOSH MAPS", "jmap", "Maps");
        chooser.setFileFilter(filter);
       chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try 
            {
                File f = new File(chooser.getSelectedFile()+".jmap");
                //FileWriter fw = new FileWriter(f);
                Scanner reader = new Scanner(f);
                ArrayList<String> Loaded = new ArrayList<>();
                while(reader.hasNextLine())
                {
                    Loaded.add(reader.nextLine());
                }
                reader.close();
                String[][] Buttonz = new String[Loaded.size()][Loaded.get(0).length()];
                for(int i = 0;i< Loaded.size();i++){
                    for(int x = 0; x < Loaded.get(0).length();x++){
                        Buttonz[i][x] = Loaded.get(i).charAt(x)+"";
                    }
                }
                //fw.write(sb.toString());
            } 
            catch (Exception ex) 
            {
            }
            }
    }
}