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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.Timer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.filechooser.FileNameExtensionFilter;
public class BodyG extends JFrame implements WindowListener,MouseListener{
    JFrame main;
    Game baby;
    Clip clip;
    int MW,MH;
    Game k;
    public BodyG(String[][] Buttons, JFrame main){
        setVisible(true);
        this.setLayout(null);
        this.main = main;

        GraphicsDevice screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int MW = screenSize.getDisplayMode().getWidth();this.MW = MW;
        int MH = screenSize.getDisplayMode().getHeight();this.MH = MH;
        this.setSize(MW, MH);
        this.setLocationRelativeTo(null);
        
        //Editor k = new Editor(18,10, 1800,1000);
        k = new Game(MW,MH,Buttons);
        // (18/1.6)
        //60,33
        baby = k;
        music();
        
        this.add(k,BorderLayout.CENTER);
        this.addWindowListener(this);
    }
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        if(clip != null){
            clip.stop();
            clip= null;
        }
        main.setVisible(true);
        if(baby.DashF != null){
            baby.DashF.dispose();
        }
        
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }
    public void windowDeiconified(WindowEvent e) {
    }
    public void windowDeactivated(WindowEvent e){
    }
    public void windowActivated(WindowEvent e) {
    }
    public void music(){
        try{
            //credits to @Debbie Simon
            File file = new File("Dungeon.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.loop(-1);
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
}
