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
public class ShopItem extends JButton{
    public String name;
    public int price;
    public int type;
    public String description;
    public String image;
    public ShopItem(String name,int price, int type, String description, String image, DashBoard frame){
        this.name = name;this.price = price; this.type = type;this.description = description;this.image = image;
        
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);this.setOpaque(true);
        makeImage paintedPart = new makeImage(image,0,0,200,200);
        paintedPart.setPreferredSize(new Dimension(200,200));
        add(paintedPart, BorderLayout.CENTER);
        JPanel botPanel = new JPanel(new GridLayout(1,2));
        JLabel namet = new JLabel(name);namet.setOpaque(false);
        JLabel pricet = new JLabel("Price: "+price);pricet.setOpaque(false);
        botPanel.add(namet);botPanel.add(pricet);
        add(botPanel,BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(200,230));
    }
}
