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
import java.util.*;
import java.applet.Applet;
import java.awt.geom.Rectangle2D;
public class GodIntro extends JPanel implements ActionListener{
    private ArrayList <Integer> a;
    private int perc = 0;
    Timer t0 = new Timer(30000,this);
    Timer t1 = new Timer(60,this);
    Timer t2 = new Timer(150, this);
    Timer t3 = new Timer(100, this);
    Timer t4 = new Timer(30, this);
    String word1 = "JOSH CRUZADA";
    String word2 = "DEVELOPMENT";
    boolean[] stop1 = new boolean[word1.length()];
    boolean[] stop2 = new boolean[word2.length()];
    int n;
    int xn;
    boolean trys  = false;
    String[] chars = {"A","B","C","D","E","F","G","H","I","J","K","L","N","M","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
   
    
    int perc1 = 0;
    int perc2 = 0;
    int perc3 = 0;
    MPS theF;
    public GodIntro(int x, int y, MPS theF){
        this.setVisible(true);
        this.setSize(x,y);
        this.setLayout(new BorderLayout());
        //this.setBackground(Color.green);
        this.setBackground(Color.black);
        
        //setting up for the god Intro
        a = new ArrayList <Integer> ();
        
        n = 420/word1.length();
        xn = (int)(Math.random()*n);
        
        
        for(int i = 0; i < stop1.length; i++){
            stop1[i] = false;
        }
        for(int i = 0; i < stop2.length; i++){
            stop1[i] = false;
        }
        repaint();
        t1.start();
        this.theF = theF;
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        if(t1.isRunning()){
            theF.setOpacity((float)((double)(perc)/100.0));
        }
        g.setColor(Color.green);
        g.setFont(new Font("Arial", 70, 70));
        Font font = new Font("Arial", 70, 70);
        Graphics2D g2d = (Graphics2D) g;
        //int n = 420/word1.length();
        for(int i = 0; i < word1.length();i++){
            //int xn = (int)(Math.random()*n);
            int x =  + n*2*i + xn;
            int y = 210 -120 - xn;
            //g.drawString(word1.charAt(i)+"", 180+49*i, 210);
            //g.drawString(word1.charAt(i)+"", x, y);
            Rectangle2D rect = font.getStringBounds(word1.substring(0,i), g2d.getFontRenderContext());
            int desiredx = (int)(x+((180+rect.getWidth())-x)*((double)perc/100.0));
            int desiredy = (int)(y+((210)-y)*((double)perc/100.0));
            int randn = (int)(Math.random()*chars.length);
            if((perc != 100 || stop1[i] == false)&& word1.charAt(i) != ' ')
                g.drawString(chars[randn], desiredx, desiredy);
            else
                g.drawString(word1.charAt(i)+"", desiredx, desiredy);
        }
        font = new Font("Calibri", 50, 60);
        g.setFont(font);
        
        for(int i = 0; i < word2.length();i++){
            int xn = (int)(Math.random()*n);
            int x =  + n*2*i + xn;
            int y = 210 -120 - xn;
            Rectangle2D rect = font.getStringBounds(word2.substring(0,i), g2d.getFontRenderContext());
            int randn = (int)(Math.random()*chars.length);
            if(stop2[i] == false && word2.charAt(i) != ' ')
                g.drawString(chars[randn], 275+41*i, 280);
            else
                g.drawString(word2.charAt(i)+"", 275+(int)(rect.getWidth()), 280);
        }
        
        if(trys == true){
            int speed0 = -1000;
            int speed1 = (int) (1000*((double)(perc1)/100.0));
            int speed2 = (int) (70*((double)(perc2)/100.0));
            int speed3 = (int) (1350*((double)(perc3)/100.0));
            int totals = speed0+speed1+speed2+speed3;
            int[] xpoints = {0+totals,40+totals,600+totals,540+totals,0+totals};
            int[] ypoints = {380,350,350,380,380};
            
            g.fillPolygon(xpoints,ypoints, 4);
            int subx1 = (int)(this.getWidth()*((double)(perc1)/100.0));
            g.fillRect(-1*this.getWidth()*2/2+subx1,40, this.getWidth()/2, 30);
            g.fillRect(this.getWidth()/2*3-subx1,40, this.getWidth()/2, 30);
            theF.setOpacity(1-(float)((double)(perc3)/100.0));
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == t0){
            t1.start();
            t0.stop();
        }
        if(e.getSource() == t1){
            if(perc != 100){
                perc+=2;
            }
            else if (t2.isRunning() == false){
                t2.start();
            }
            repaint();
        }
        else if(e.getSource() == t2){
            int i;
            for(i =0; i < stop1.length; i ++){
                if(stop1[i] == false){
                    stop1[i] = true;
                    break;
                }
            }
            if(i == stop1.length){
                t3.start();
                t2.stop();
            }
        }
        else if(e.getSource() == t3){
            int i;
            for(i = 0; i < stop2.length;i++){
                if(stop2[i] == false){
                    stop2[i] = true;
                    break;
                }
            }
            if(i == stop2.length){
                trys = true;
                t4.start();
                t1.stop();
                t3.stop();
            }
        }
        else if(e.getSource() == t4){
            if(perc1 < 100){
                perc1+=4;
            }
            else if(perc2 < 100){
                perc2+=2;
            }
            else if(perc3 < 100){
                perc3+=4;
            }
            else{
                theF.setVisible(false);
                t4.stop();
                practiceF p = new practiceF();
                AnimPractice ap = new AnimPractice(p);
                p.add(ap);
                theF.dispose();
                //System.exit(0);
            }
        }
    }
}
