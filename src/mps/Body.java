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
public class Body extends JFrame {
    public Body(){
        this.setLayout(new BorderLayout());
        JPanel body = new JPanel(new BorderLayout());
        this.setSize(1440, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Editor k = new Editor(18,10, 1800,1000);
        EditorBeta k = new EditorBeta(18,11, 1440,900);
        // (18/1.6)
        //60,33
        body.add(k,BorderLayout.CENTER);
        this.add(body,BorderLayout.CENTER);
    }
}
