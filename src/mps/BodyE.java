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
public class BodyE extends JFrame implements ActionListener, WindowListener{
    JMenuItem save;
    EditMap ks;
    JFrame main;
    public BodyE(int x, int y, JFrame main){
        this.setLayout(new BorderLayout());
        this.main = main;
        this.addWindowListener(this);
        JPanel body = new JPanel(new BorderLayout());
        this.setSize(1280, 835);
        this.setLocationRelativeTo(null);
        //Editor k = new Editor(18,10, 1800,1000);
        ks = new EditMap(x, y,1280,800);
        // (18/1.6)
        //60,33
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        
        JMenu file = new JMenu("File");
        menubar.add(file);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        body.add(ks,BorderLayout.CENTER);
        this.add(body,BorderLayout.CENTER);
    }
    public BodyE(JFrame main, String[][] Buttons){
        this.setLayout(new BorderLayout());
        this.main = main;
        this.addWindowListener(this);
        JPanel body = new JPanel(new BorderLayout());
        this.setSize(1280, 835);
        this.setLocationRelativeTo(null);
        //Editor k = new Editor(18,10, 1800,1000);
        ks = new EditMap(1280,800,Buttons);
        // (18/1.6)
        //60,33
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);
        
        JMenu file = new JMenu("File");
        menubar.add(file);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        body.add(ks,BorderLayout.CENTER);
        this.add(body,BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JOSH MAPS", "jmap", "Maps");
        chooser.setFileFilter(filter);
       chooser.setCurrentDirectory(new File(System.getProperty("user.dir")+"/Maps"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try 
            {
                File f = new File(chooser.getSelectedFile()+".jmap");
                //FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(ks.cellX+" "+ks.cellY);
                bw.newLine();
                for(int i = 0; i < ks.cellY; i++){
                    String k = "";
                    for(int z = 0; z < ks.cellX; z++){
                        if(ks.Buttons[i][z].equals("1") || ks.Buttons[i][z].equals("2")|| ks.Buttons[i][z].equals("3")|| ks.Buttons[i][z].equals("4")){
                            k = k+"#";
                        }
                        else{
                            k = k+(ks.Buttons[i][z]);
                        }
                    }
                    if(i > 0)
                        bw.newLine();
                    bw.write(k);
                }
                bw.close();
                //fw.write(sb.toString());
            } 
            catch (Exception ex) 
            {
            }
            }
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
