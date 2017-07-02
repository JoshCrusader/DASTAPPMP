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
public class Loading extends JPanel implements ActionListener {
    private int firstx=0;
    private int secondx=0;
    private int sidex = 0;
    private int sidey = 0;
    private int multiplex = 1;
    private int count = 0;
    private boolean ended = false;
    private Timer t = new Timer(20, this);
    public Loading(int x, int y){
        this.setVisible(true);
        this.setSize(x,y);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.green);
        firstx = -x/2;secondx = x;sidex = x; sidey = y;

        t.start();
        
    }
    public void paint(Graphics g){
        super.paintComponent(g);
        int divider = 15;
        for(int x = 0; x < divider; x++){
            int posy = firstx-50*x;
            if(firstx-50*x > 0)
                posy = 0;
            int posz = secondx+50*x;
            if(secondx+50*x < sidex/2)
                posz = sidex/2;
            //Color[] colortab = {Color.black,Color.blue,Color.cyan,Color.red,Color.pink,Color.green,Color.gray,Color.yellow};
            //g.setColor(colortab[(int)(Math.random()*colortab.length)]);
            g.fillRect(posz, (sidey/divider*x), sidex/2, sidey/divider);
           // g.fillOval(posz-(sidey/divider)/2, (sidey/divider*x), sidey/divider, sidey/divider);
            g.fillRect(posy, (sidey/divider*x), sidex/2, sidey/divider);
            //g.fillOval(posy+sidex/2-(sidey/divider)/2, (sidey/divider*x), sidey/divider, sidey/divider);
        }
        if(firstx-50*divider > 0 && ended == false){
            if(count < 20){
                count++;
            }
            else{
                multiplex = -1;
                ended = true;
            }
        }
        if(firstx < (-sidex)/2&&ended == true){//if(firstx+(sidey/divider)/2 < (-sidex)/2&&ended == true){
            t.stop();
            JPanel par = (JPanel)this.getParent();
           this.getParent().remove(this);
            par.updateUI();
        }
        secondx-=10*multiplex;
        firstx+=10*multiplex;
    }
    public void actionPerformed(ActionEvent e){
        repaint();
    }
}
