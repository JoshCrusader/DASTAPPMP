/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
public class EditFrame extends JFrame implements ActionListener, WindowListener {
    JPanel secondPanel;
    
    JButton Load;
    JButton Cancel;
    JButton h1;
    JButton h2;
    JTextField rows;
    JTextField coloumns;
    JFrame momy;
    public EditFrame(JFrame momy){
        
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.momy = momy;
        this.setLayout(new GridLayout(1,2));
        JPanel topGrid = new JPanel(new GridLayout(4,1));
        JPanel botGrid = new JPanel(new GridLayout(2,1));
        h1 = new JButton("Coloumns:");
        rows = new JTextField("0");
        h2 = new JButton("Rows:");
        coloumns = new JTextField("0");
        topGrid.add(h1);
        topGrid.add(rows);
        topGrid.add(h2);
        topGrid.add(coloumns);
        h1.addActionListener(this);
        h2.addActionListener(this);
        Load = new JButton("Make");
        Cancel = new JButton("Cancel");
        botGrid.add(Load);
        botGrid.add(Cancel);
        Load.addActionListener(this);
        Cancel.addActionListener(this);
        this.add(topGrid);
        this.add(botGrid);
    }
    
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Load){
            try{
            BodyE app = new BodyE(Integer.parseInt(rows.getText()), Integer.parseInt(coloumns.getText()),momy);
            app.setVisible(true);
            this.dispose();
            }
            catch(Exception f){
                System.out.println("error");
            }
        }
        else if(e.getSource() == h1){
            try{
                int myRows = Integer.parseInt(rows.getText());
                coloumns.setText((int)Math.round(((double)myRows/1.6))+"");
            }
            catch(Exception f){
                rows.setText("0");
            }
        }
        else if(e.getSource() == h2){
            try{
                int myC = Integer.parseInt(coloumns.getText());
                rows.setText((int)(Math.round((double)myC*1.6))+"");
            }
            catch(Exception f){
                coloumns.setText("0");
            }
        }
        else if(e.getSource() == Cancel){
            momy.setVisible(true);
            this.dispose();
            
            
        }
    }

    public void windowOpened(WindowEvent e) {
    }
    public void windowClosing(WindowEvent e) {
        momy.setVisible(true);
    }

    public void windowClosed(WindowEvent e) {
    }
    public void windowIconified(WindowEvent e) {
    }
    public void windowDeiconified(WindowEvent e) {
    }
    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}