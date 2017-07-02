/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class AnimPractice extends JPanel implements ActionListener, KeyListener, MouseListener{
    int option = 2;
    int multiplier = 1;
    boolean canPress = true;
    boolean canMove = true;
    int perc = 0;
    ArrayList<int[]> rects = new ArrayList<>();
    int[] rect1 = {175,350, 100, 166,175,350, 100, 166};
    int[] rect2 = {300,350, 100, 166,300,350, 100, 166};
    int[] rect3 = {425,400, 150, 250,425,400, 150, 250};
    int[] rect4 = {600,350, 100, 166,600,350, 100, 166};
    int[] rect5 = {725,350, 100, 166,725,350, 100, 166};
    
    boolean played = false;
    Clip clip;
    JFrame parent;
    Timer t;
    
    
    //variables for enter animation//
    Timer t2 = new Timer(50,this);
    int perc2 = 0;
    int perc3 = 0;
    public AnimPractice(JFrame parent){
        
        this.parent = parent;
        this.setSize(1000,700);
        t = new Timer(50, this);
        rects.add(rect1);
        rects.add(rect2);
        rects.add(rect3);
        rects.add(rect4);
        rects.add(rect5);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.grabFocus();
        t.start();
    }
    public void paint(Graphics g){
        super.paint(g);
        Image image;
        if(option == 0 || option == 1){
            image = new ImageIcon("Run.png").getImage();
        }
        else if(option == 2 || option == 3){
            image = new ImageIcon("Maze.png").getImage();
        }
        else{
            image = new ImageIcon("Exiting.png").getImage();
        }
        g.drawImage(image, 0, 0,1000,700, this);
        for(int i = 0; i < rects.size();i++){
            String images = "Buttons/";
            if(i == 0){
                images += "Play1";
            }
            else if(i == 1){
                images += "Run1";
            }
            else if(i == 2){
                images += "make1";
            }
            else if(i == 3){
                images += "edit1";
            }
            else{
                images += "exit1";
            }
            if( i == option)
                images += "s";
            images+= ".png";
            image = new ImageIcon(images).getImage();
            g.drawImage(image,rects.get(i)[0], rects.get(i)[1], rects.get(i)[2], rects.get(i)[3],this);
            
            
        }
        //g.drawRect(175,400, 150, 250);
        //g.drawRect(50,350, 100, 166);
        //g.drawRect(350,350, 100, 166);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == t){
            if(parent.isVisible() && clip!= null && !clip.isRunning()){
                clip.start();
            }
            repaint();
            if(played == false){
                music();
                played = true;
            }
            if(!canMove){
                for(int i = 0; i < rects.size();i++){
                    int desiredX = 0;
                    int desiredY = 350;
                    int desiredW = 100;
                    int desiredH = 166; 
                    if(i == option){
                        desiredX = 425;
                        desiredY = 400;
                        desiredW = 150;
                        desiredH = 250; 
                    }
                    else if(i < option){
                        desiredX = 425-125*(option-i);
                    }
                    else if(i > option){
                        desiredX = 450+125*(i-option);
                    }
                    rects.get(i)[0] = (int)(rects.get(i)[4]+(double)(desiredX-rects.get(i)[4])*((double)perc/100.0));
                    rects.get(i)[1] = (int)(rects.get(i)[5]+(double)(desiredY-rects.get(i)[5])*((double)perc/100.0));
                    rects.get(i)[2] = (int)(rects.get(i)[6]+(double)(desiredW-rects.get(i)[6])*((double)perc/100.0));
                    rects.get(i)[3] = (int)(rects.get(i)[7]+(double)(desiredH-rects.get(i)[7])*((double)perc/100.0));
                }
                if(perc <100){
                    perc+=20;
                }
                else{
                    for(int i = 0; i < rects.size();i++){
                        rects.get(i)[4] = rects.get(i)[0];
                        rects.get(i)[5] = rects.get(i)[1];
                        rects.get(i)[6] = rects.get(i)[2];
                        rects.get(i)[7] = rects.get(i)[3];
                    }
                    perc = 0;
                    canPress = true;
                    canMove = true;
                }
            }

        }
        else if(e.getSource() == t2){
            repaint();
            int desiredX = 425;
            int desiredY = 400;
            int desiredW = 150;
            int desiredH = 250;
            if(perc2 < 100){
                rects.get(option)[0] = (int)(rects.get(option)[4]+(425.0 - rects.get(option)[4])*((double)perc2/100));
                rects.get(option)[1] = (int)(rects.get(option)[5]+(420.0 - rects.get(option)[5])*((double)perc2/100));
                rects.get(option)[2] = (int)(rects.get(option)[6]+(150.0 - rects.get(option)[6])*((double)perc2/100));
                rects.get(option)[3] = (int)(rects.get(option)[7]+(250.0 - rects.get(option)[7])*((double)perc2/100));
                perc2+=50;
            }
           rects.get(option)[4] = rects.get(option)[0];
           rects.get(option)[5] = rects.get(option)[1];
           rects.get(option)[6] = rects.get(option)[2];
           rects.get(option)[7] = rects.get(option)[3]; 
           if(perc3 < 100 && perc2 >= 100){
                rects.get(option)[0] = (int)(rects.get(option)[4]+(425.0 - rects.get(option)[4])*((double)perc3/100));
                rects.get(option)[1] = (int)(rects.get(option)[5]+(200.0 - rects.get(option)[5])*((double)perc3/100));
                rects.get(option)[2] = (int)(rects.get(option)[6]+(150.0 - rects.get(option)[6])*((double)perc3/100));
                rects.get(option)[3] = (int)(rects.get(option)[7]+(250.0 - rects.get(option)[7])*((double)perc3/100));
                perc3+=25;
            }
           else if(perc3 >=100 && perc2 >= 100){
               t2.stop();
               if(option == 0){
                   option0();
               }
               else if(option == 1){
                   option1();
               }
               else if(option == 2){
                   option2();
               }
               else if(option == 3){
                   option3();
               }
               canPress = true;
           }
        }
    }

    public void keyTyped(KeyEvent e) {
    }
    public void select(int choose){
        option = choose;
        canPress = false;canMove = false;
        perc = 0;
        buttonS();
        
    }
    public void enterd(){
        perc2 = 0;perc3 = 0;
        t2.start();
    }
    public void option0(){
        String[][] Buttonz = loadMap(); 
        if(Buttonz != null && Buttonz.length >0){
            BodyG g = new BodyG(Buttonz,parent);
            clip.stop();
        }
    }public void option1(){
        String[][] Buttonz = loadMap(); 
        if(Buttonz != null && Buttonz.length >0){
            BodyC g = new BodyC(Buttonz,parent);
            clip.stop();
        }
    }
    public void option2(){
        EditFrame app = new EditFrame(parent);
        clip.stop();
        app.setVisible(true);
        parent.setVisible(false);
    }
    public void option3(){
        String[][] Buttonz = loadMap();
        if(Buttonz  != null && Buttonz.length >0){
        BodyE app = new BodyE(this.parent,Buttonz);
        app.setVisible(true);
        
        }
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(option-1 >= 0 && canPress && canMove){
                select(option-1);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(option+1 <= rects.size()-1 && canPress && canMove){
                select(option+1);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_5){
            if(canPress && option != 4 && canMove){
                select(4);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_4){
            if(canPress && option != 3 && canMove){
                select(3);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_3){
            if(canPress && option != 2 && canMove){
                select(2);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_2){
            if(canPress && option != 1 && canMove){
                select(1);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_1){
            if(canPress && option != 0 && canMove){
                select(0);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(option == rects.size()-1){
                System.exit(0);
            }
            else if(canPress){
                canPress = false;
                selSound();
                enterd();
            }
        }
    }


    public void keyReleased(KeyEvent e) {
    }
    
    public void music(){
        try{
            //credits to @Debbie Simon
            File file = new File("Creep.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(-1);
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }
    public void buttonS(){
        try{
            //credits to @Debbie Simon
            int num = (int)(Math.random()*3.0);
            File file = new File("button2.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }
    public void selSound(){
        try{
            //credits to @Debbie Simon
            int num = (int)(Math.random()*3.0);
            File file = new File("selButton.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }
    public boolean isClicked(int[] butt, MouseEvent e){
        double MouseX = e.getPoint().getX();
        double MouseY = e.getPoint().getY();
        return (MouseX > butt[0] && MouseX < butt[0]+butt[2] && MouseY > butt[1] && MouseY < butt[1]+butt[3]);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < rects.size();i++){
            if(isClicked(rects.get(i),e) && canPress && i != option  && canMove){
                select(i);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public String[][] loadMap() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JOSH MAPS", "jmap", "Maps");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")+"/Maps"));
        String[][] Buttonz = new String[0][0];
        int retrival = chooser.showOpenDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try 
            {
                File f = chooser.getSelectedFile();
                //FileWriter fw = new FileWriter(f);
                Scanner reader = new Scanner(f);
                ArrayList<String> Loaded = new ArrayList<>();
                if(reader.hasNextLine()){
                    reader.nextLine();
                }
                while(reader.hasNextLine())
                {
                    String thel = reader.nextLine();
                    Loaded.add(thel);
                }
                reader.close();
                Buttonz = new String[Loaded.size()][Loaded.get(0).length()];
                for(int i = 0;i< Loaded.size();i++){
                    for(int x = 0; x < Loaded.get(0).length();x++){
                        Buttonz[i][x] = Loaded.get(i).charAt(x)+"";
                    }
                }
                parent.setVisible(false);
                return Buttonz;
                //fw.write(sb.toString());
            } 
            catch (Exception ex) 
            {
            }
        }
        return Buttonz;
    }
}
