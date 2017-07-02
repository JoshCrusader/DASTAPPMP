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
public class makeImage extends JPanel {
    String image;
    int x;
    int y;
    int w;
    int h;
    public makeImage(String image, int x, int y,int w,int h){
        this.x =x;this.y=y;this.w=w;this.h=h;
        this.image = image;
        repaint();
    }
    public void paintComponent(Graphics g){
        Image imaget = new ImageIcon(image).getImage();
        g.drawImage(imaget,x,y, w, h, this);
    }
    public void update(String image){
        this.image = image;
        repaint();
    }
}
