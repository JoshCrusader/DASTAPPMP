/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

/**
 *
 * @author drjeoffreycruzada
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;
public class DashBoard extends JPanel implements KeyListener,ActionListener {
    
    Game parent;
    DashFrame fam;
    
    int Health = 100;
    int mHealth = 100;
    ArrayList <ShopItem> items = new ArrayList<>();
    JPanel panel2;
    
    
    ShopItem selected = null;
    ShopItem sample = new ShopItem("",0, 0, desc1(), "EXIT3.png",this);
    makeImage preview;
    JTextPane but2;
    public DashBoard(Game parent,DashFrame fam){
        this.parent = parent;
        this.fam = fam;
        Health = parent.health;
        mHealth = parent.maxhealth;
        this.setFocusable(true);
        this.grabFocus();
        this.addKeyListener(this);
        this.setLayout(new GridLayout(3,1));
        this.setBackground(Color.darkGray);
        
        //panell TOPPPP LANEEEE//
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel topP = new JPanel(new GridLayout(1,2));
        panel1.add(topP);
        preview = new makeImage("wall.jpeg",0,0,300,266);
        but2=  new JTextPane();but2.setEditable(false);but2.addKeyListener(this);
        but2.setText("non selected");
        JScrollPane scrolly2 = new JScrollPane(but2);
        topP.add(preview);
        topP.add(scrolly2);
        
        panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        
        addItem("Cancel",0, -1, desc1(), "Icons/Cancel.jpeg");
        addItem("Baby Turret",200, 0, desc1(), "Icons/BabyTurret.jpeg");
        addItem("Tesla Tower",4500, 1, desc1(), "Icons/Tesla.jpeg");
        addItem("PB101",0, 2, desc1(), "Icons/Cancel.jpeg");
        addItem("beast like",4020, 0, desc1(), "Portal.png");
        addItem("dire hut",9020, 0, desc1(), "PlayButt.png");
        addItem("baby poo",10000, 0, desc1(), "wall.jpeg");
        JScrollPane scrolly = new JScrollPane(panel2);
        add(panel1);
        add(scrolly);
        add(panel3);
        repaint();
    }
    public void paint(Graphics g){
        super.paintComponents(g);
        g.fillRect(0, 580, 600, 70);
        g.setColor(Color.red);
        g.fillRect(30, 670, 540, 100);
        g.setColor(Color.green);
        g.fillRect(30, 670, (int)(540*((double)Health/(double)(mHealth))), 100);
        g.setColor(Color.black);
        g.drawRect(30, 670, 540, 100);
        g.setColor(Color.blue);
        g.setFont(new Font("Arial",30,30));
        if(selected != null){
            g.drawString("Selected: "+selected.name+" [Price:"+selected.price+"]", 30, 550);
        }
        else{
            g.drawString("Nothing is selected", 30, 550);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",50,50));
        g.drawString("Money: "+parent.money, 30, 640);
        g.drawString(Health+"/"+mHealth, 180, 745);
    }
    
    public void updates(){
        Health = parent.health;
        mHealth = parent.maxhealth;
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("asfas");
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ESCAPE){
            fam.setVisible(false);
            parent.grabFocus();
        }
    }
    public void addItem(String name,int price, int type, String description, String image){
        ShopItem i = new ShopItem(name,price,type,description, image, this);
        i.addActionListener(this);
        i.addKeyListener(this);
        panel2.add(i);
        items.add(i);
        
    }
    public void keyReleased(KeyEvent e) {
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().equals(sample.getClass())){
            if(((ShopItem)e.getSource()).type == -1){
                selected = null;
                preview.update("wall.jpeg");
                but2.setText("no selection made");
            }
            else{
                selected = (ShopItem)e.getSource();
                preview.update(selected.image);
                but2.setText(((ShopItem)e.getSource()).description);
            }
            parent.selection = ((ShopItem)(e.getSource())).type;
        }
        else{
        }
    }
    public String desc1(){
        String desc = "Lazer tower\n" +
        "This first algorithmic tower tower with an\n" +
        "artificial intelligence and shoot an beam of\n" +
        "molten children's toys especially female baby clothing\n" +
        "for maximum destruction on these artificial semi classy\n" +
        "musculine data scum, the concept of this tower of basic defense\n" +
        "was inspired by surrounding environment of our creator's location\n" +
        "when he was conceptualizing us, I mean... can you even\n" +
        "ask for a better design? wonderful!"
        ;
        return desc;
    }
    public String desc2(){
        return"k";
    }

}
