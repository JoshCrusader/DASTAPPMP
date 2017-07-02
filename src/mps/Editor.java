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
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
public class Editor extends JPanel implements ActionListener,MouseListener, MouseMotionListener, KeyListener{
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
    private ArrayList<Node> finalDestination = new ArrayList<>();
    private Timer t;
    private Timer t2;
    
    private int counterAnim = 11;
    private int posx;
    private int posy;
    
    private double formx;
    private double formy;
    private String direction = "r";
    private boolean tapos = false;
    public Editor(int x, int y,int mySizeX, int mySizeY){
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
                    image = new ImageIcon("walk"+direction+animTime+".png").getImage();
                    if(!tapos)
                        g.drawImage(image,z*interValX, i*interValY, interValX, interValY, this);
                    
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
        if(tapos){
            Image image = new ImageIcon("walk"+direction+animTime+".png").getImage(); 
            g.drawImage(image,posx, posy, interValX, interValY, this);
        }
        Node tlatest2 = tlatest;
        while(tlatest != null && tlatest.getParent() != null){
                g.setColor(Color.cyan);
                g.drawLine(tlatest.x*interValX + interValX/2, tlatest.y*interValY+ interValY/2, tlatest.getParent().x*interValX+ interValX/2, tlatest.getParent().y*interValY+ interValY/2);
                //g.fillRect(tlatest.x*interValX, tlatest.y*interValY, interValX, interValY);
                tlatest = tlatest.getParent();
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
            aStar();
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
            
            //animate the character to move and change image
            if(counterAnim > 10){
                counterAnim = 0;
                formx = finalDestination.get(finalDestination.size()-1).x * interValX;
                formy = finalDestination.get(finalDestination.size()-1).y * interValY;
                finalDestination.remove(finalDestination.size()-1);
            }
            if(finalDestination.size() > 0){
            double myposx =  (finalDestination.get(finalDestination.size()-1).x) * interValX;
            double myposy = (finalDestination.get(finalDestination.size()-1).y) * interValY;
            posx = (int)(formx + (myposx-formx) *((double)(counterAnim)/10.0)); 
            posy = (int)(formy + (myposy-formy) *((double)(counterAnim)/10.0)); 
            
            if(myposy - formy < 0)
                direction = "u";
            else if(myposy -formy > 0)
                direction = "d";
            else if(myposx - formx < 0)
                direction = "l";
            else
                direction = "r";
            counterAnim++;
            }
            
            else{
                counterAnim  = 11;
                direction = "d";
                System.out.println("hehedone");
                tapos = false;
                t2.stop();
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
    
    
    
    public void aStar(){
        Node[][]  nodes = new Node[cellY][cellX];
        ArrayList<Node> Close = new ArrayList<>();
        ArrayList<Node> Open = new ArrayList<>();
        int regNodes = 0;
        Node start=null;
        Node end=null;
        Node latest = null;
        Node prior = null;
        boolean tapos = false;
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
        int[][] possibles = {{1,0},{0,1},{-1,0},{0,-1}};
        while(!tapos && prior != null ){//&& Close.size() != countRegularNodes(Open)+1){
            Close.add(prior);
            for(int z = 0; z < possibles.length;z++){
                int targetX = possibles[z][0];
                int targetY = possibles[z][1];
                if((prior.x - targetX) > -1 && (prior.x - targetX) <cellX &&
                    (prior.y - targetY) > -1 && (prior.y - targetY) <cellY &&
                    nodes[prior.y - targetY][prior.x - targetX] != null){
                    Node sNode = nodes[prior.y - targetY][prior.x - targetX];
                    sNode.setCosts(start, end);
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
            else{
                finalDestination = new ArrayList<Node>();
                Node tempLast = prior;
                while(tempLast != null){
                    posx = tempLast.x*interValX;
                    posy = tempLast.y*interValY;
                    finalDestination.add(tempLast);
                    tempLast = tempLast.getParent();
                }
                t2 = new Timer(50,this);
                t2.start();
            }
            if(prior != null)
                tlatest = prior;

        }

    }

    public Node getPriorNode(ArrayList <Node> nodes){
        Node lowestNode = null;
        for(int i = 0; i < nodes.size(); i++){
            if(i == 0 || nodes.get(i).FCost < lowestNode.FCost){
                lowestNode = nodes.get(i);
                //System.out.println(nodes.get(i).FCost);
            }
            else if(nodes.get(i).FCost == lowestNode.FCost && nodes.get(i).HCost < lowestNode.HCost){
                lowestNode = nodes.get(i);
            }
        }
        return lowestNode;
    }
}
