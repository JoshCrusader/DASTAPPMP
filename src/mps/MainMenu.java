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
import javax.swing.filechooser.FileNameExtensionFilter;
public class MainMenu extends JFrame implements ActionListener {
    JButton play;
    JButton Exit;
    JButton Create;
    JPanel firstPanel;
    JPanel secondPanel;
    
    JButton Load;
    JButton Open;
    JButton Cancel;
    
    JButton h1;
    JButton h2;
    JTextField rows;
    JTextField coloumns;
    public MainMenu(){
        
        this.setSize(500,700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        makeFirstPanel();

    }
    
    private void makeFirstPanel(){
        firstPanel = new JPanel(new GridLayout(2,1));
        JPanel imageTop = new JPanel();
        firstPanel.add(imageTop);
        
        JPanel bottomPanel = new JPanel(new GridLayout(3,1));
        play = new JButton("Play");
        Create = new JButton("Edit");
        Exit = new JButton("Exit");
        
        Exit.addActionListener(this);
        play.addActionListener(this);
        Create.addActionListener(this);
        
        bottomPanel.add(play);
        bottomPanel.add(Create);
        bottomPanel.add(Exit);
        
        firstPanel.add(bottomPanel);
        add(firstPanel);
    }
    public void makesecondPanel(){
        secondPanel = new JPanel(new GridLayout(2,1));
        JPanel imageTop = new JPanel();
        secondPanel.add(imageTop);
        
        JPanel bottomPanel = new JPanel(new GridLayout(3,1));
        JPanel secondTop = new JPanel(new GridLayout(1,4));
        
        h1 = new JButton("X Cells:");
        h1.setFont(new Font("Arial", 25, 25));
        rows = new JTextField("0");
        rows.setFont(new Font("Arial", 90, 90));
        h2 = new JButton("Y Cells:");
        h2.setFont(new Font("Arial", 25, 25));
        coloumns = new JTextField("0");
        coloumns.setFont(new Font("Arial", 90, 90));
        
        secondTop.add(h1);
        secondTop.add(rows);
        secondTop.add(h2);
        secondTop.add(coloumns);
        
        Load = new JButton("Make map");
        JPanel midPanel = new JPanel(new GridLayout(2,1));
        Cancel = new JButton("Cancel");
        Open = new JButton("Open map");
        midPanel.add(Load);
        midPanel.add(Open);
        
        Load.addActionListener(this);
        Cancel.addActionListener(this);
        h1.addActionListener(this);
        h2.addActionListener(this);
        Open.addActionListener(this);
        bottomPanel.add(secondTop);
        bottomPanel.add(midPanel);
        bottomPanel.add(Cancel);
        
        
        secondPanel.add(bottomPanel);
        add(secondPanel);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Exit){
            System.exit(0);
        }
        else if(e.getSource() == play){
            String[][] Buttonz = loadMap(); 
            if(Buttonz != null && Buttonz.length >0){
                BodyC g = new BodyC(Buttonz,this);
                g.setVisible(true);
            }
        }
        else if(e.getSource() == Create){
            this.remove(firstPanel);
            makesecondPanel();
            secondPanel.updateUI();
        }
        else if(e.getSource() == Load){
            System.out.println("hehe fasasfafas");
            BodyE app = new BodyE(Integer.parseInt(rows.getText()), Integer.parseInt(coloumns.getText()),this);
            app.setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == Cancel){
            this.remove(secondPanel);
            makeFirstPanel();
            firstPanel.updateUI();
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
        else if(e.getSource() == Open){
            String[][] Buttonz = loadMap();
            if(Buttonz  != null && Buttonz.length >0){
            BodyE app = new BodyE(this,Buttonz);
            app.setVisible(true);
            }
        }
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
                System.out.println(Loaded.size());
                Buttonz = new String[Loaded.size()][Loaded.get(0).length()];
                for(int i = 0;i< Loaded.size();i++){
                    for(int x = 0; x < Loaded.get(0).length();x++){
                        Buttonz[i][x] = Loaded.get(i).charAt(x)+"";
                    }
                }
                this.setVisible(false);
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