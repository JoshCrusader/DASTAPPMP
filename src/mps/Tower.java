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
import java.awt.geom.AffineTransform;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.swing.filechooser.*;
public abstract class Tower{
    int maxhealth = 100;
    int health  = 100;
    int x;
    int y;
    int interValX;
    int interValY;
    Game game;
    int range = 50;
    Image baseImage = new ImageIcon("Towers/base1.png").getImage();
    Image headImage = new ImageIcon("Towers/head1.jpeg").getImage();
    public Tower(Game g,int x, int y,int interValX, int interValY){
        this.game = g;
        this.x = x;
        this.y = y;
        this.interValX = interValX;
        this.interValY = interValY;
    }
    public abstract void paintMe(Graphics g);
}
