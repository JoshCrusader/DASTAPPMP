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
import javax.swing.filechooser.FileNameExtensionFilter;
public class BodyC extends JFrame implements WindowListener{
    JFrame main;
    public BodyC(String[][] Buttons, JFrame main){
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.main = main;
        JPanel body = new JPanel(new BorderLayout());
        this.setSize(1440, 900);
        this.setLocationRelativeTo(null);
        //Editor k = new Editor(18,10, 1800,1000);
        Tests k = new Tests(1440,900,Buttons);
        // (18/1.6)
        //60,33
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        body.add(k,BorderLayout.CENTER);
        this.add(body,BorderLayout.CENTER);
        this.addWindowListener(this);
    }
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        main.setVisible(true);
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
}
