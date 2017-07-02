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
import javax.swing.filechooser.*;
public class Classic extends JPanel implements ActionListener,MouseListener, MouseMotionListener, KeyListener{
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
    public Classic(int mySizeX, int mySizeY, String[][] Buttons){
        int y = Buttons.length;
        int x = Buttons[0].length;
        this.setLayout(null);
        this.setFocusable(true);
        this.mySizeX = mySizeX;
        this.mySizeY = mySizeY;
        this.cellX = x;
        this.cellY = y;
        interValX = mySizeX/cellX;
        interValY = mySizeY/cellY;
        this.setSize(mySizeX, mySizeY);
        this.Buttons = new String[y][x];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        System.out.println(y);
        System.out.println(x);
        for(int i = 0; i < y; i++){
            for(int z = 0; z < x; z++){
                this.Buttons[i][z] = "#";
            }
        }
        this.Buttons = Buttons;
        t = new Timer(100,this);
        repaint();
        t.start();
    }
    public void paint(Graphics g){
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
                }
                else{
                    g.fillRect(z*interValX, i*interValY, interValX, interValY);
                }
                
            }
        }
        if (yep){
        Image image = new ImageIcon("Nexus.png").getImage();
        g.drawImage(image,nexx, nexy, interValX*2, interValY*2, this);
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
                /* health bar
                g.setColor(Color.green);
                g.fillRect(me.x, me.y, (int)(interValX*0.9), (int)(interValY*0.2));
                g.setColor(Color.black);
                g.drawRect(me.x, me.y, (int)(interValX*0.9), (int)(interValY*0.2));
                */
            }
            paintSearch(g);

    }
    public void mouseDragged(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){

    }
    public void mouseEntered(MouseEvent e){
        
    }
    public void mouseExited(MouseEvent e){

    }
    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
        finals = true;
        startOn = false;
        goalOn = false;
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
            if(!tapos){
                aStar();
                searchT.start();
            }
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
        else if(e.getSource() == searchT){
            animateSearch();
        }
        
        for(int i = 0; i < characters.size();i++){
            characters.get(i).update();
            if(characters.get(i).tapos == false){
                characters.remove(characters.get(i));
            }
        }
        rotation +=5;
        repaint();
    }
    public void aStar(){
        nodes = new Node[cellY][cellX];
        Close = new ArrayList<>();
        Open = new ArrayList<>();
        regNodes = 0;
        start=null;
        end=null;
        latest = null;
        prior = null;
        tapos = false;
        for(int i = 0; i < cellY; i++){
            for(int z = 0; z < cellX; z++){
                String type = " ";
                if(Buttons[i][z].equals("#")){
                    type = "#";
                }
                else if(Buttons[i][z].equals(" ")){
                    type = " ";
                }
                else if(Buttons[i][z].equals("S")){
                    type = "S";
                }
                else if(Buttons[i][z].equals("G")){
                    type = "G";
                }
                Node newNode = new Node(z,i,type);
                if(Buttons[i][z].equals("S")){
                    start = newNode;
                    //Open.add(newNode);
                    prior = newNode;
                }
                else if(Buttons[i][z].equals("G")){
                    end = newNode;
                }
                nodes[i][z] = newNode;
            }
        }
    }
    public void animateSearch(){
        int[][] possibles = {{1,0},{0,1},{-1,0},{0,-1}};
        if(!tapos && prior != null ){//&& Close.size() != countRegularNodes(Open)+1){
            Close.add(prior);
            for(int z = 0; z < possibles.length;z++){
                int targetX = possibles[z][0];
                int targetY = possibles[z][1];
                if((prior.x - targetX) > -1 && (prior.x - targetX) <cellX &&
                    (prior.y - targetY) > -1 && (prior.y - targetY) <cellY &&
                    nodes[prior.y - targetY][prior.x - targetX] != null){
                    Node sNode = nodes[prior.y - targetY][prior.x - targetX];
                    //sNode.setCosts(start, end);
                    sNode.setCostAgain(prior.GCost+10, end);
                    if(sNode.getType().equals("G")) {
                        sNode.setParent(prior);
                        prior = sNode;
                        tapos = true;
                    }
                    else if(sNode.getType().equals(" ")){
                        Open.add(sNode);
                        sNode.setParent(prior);
                        nodes[prior.y - targetY][prior.x - targetX] = null;
                    }
                    
                    
                }
            }
            this.tapos = tapos;
            latest = prior;
            if(!tapos){ 
                prior = getPriorNode(Open);
                Open.remove(prior);
            }
            

        }
        else{
            nodes = new Node[cellY][cellX];
            Close = new ArrayList<>();
            Open = new ArrayList<>();
            regNodes = 0;
            start=null;
            end=null;
            latest = null;
            prior = null;
            tapos = false;
            searchT.stop();
            Path pa = new Path(cellX,cellY,Buttons,interValX,interValY);
            characters.add(new Character(pa));
        }
    }
    public void paintSearch(Graphics g){
        g.setColor(Color.red);
        for(int i = 0; i < Close.size(); i++){
            g.fillOval(Close.get(i).x*interValX, Close.get(i).y*interValY, interValX, interValY);
        }
        g.setColor(Color.blue);
        for(int i = 0; i < Open.size(); i++){
            g.fillOval(Open.get(i).x*interValX, Open.get(i).y*interValY, interValX, interValY);
        }
        g.setColor(Color.cyan);
        if(prior!= null){
            g.fillOval(prior.x*interValX, prior.y*interValY, interValX, interValY);
        }
        g.setColor(Color.black);
    }
    public Node getPriorNode(ArrayList <Node> nodes){
        Node lowestNode = null;
        for(int i = 0; i < nodes.size(); i++){
            if(i == 0 || nodes.get(i).FCost < lowestNode.FCost){
                lowestNode = nodes.get(i);
                //System.out.println(nodes.get(i).FCost);
            }
            else if(nodes.get(i).FCost == lowestNode.FCost && nodes.get(i).GCost < lowestNode.GCost){
                lowestNode = nodes.get(i);
            }
            else if(nodes.get(i).FCost == lowestNode.FCost && nodes.get(i).GCost == lowestNode.GCost && nodes.get(i).HCost < lowestNode.HCost){
                lowestNode = nodes.get(i);
            }
        }
        return lowestNode;
    }
}