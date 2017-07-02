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
import java.io.*;
import java.util.ArrayList;
public abstract class Bullet {
    
    public Tower start;
    public Character end;
    public int speed = 3;
    public int damage = 20;
    public Image image = new ImageIcon("Towers/bullet1.png").getImage();;
    public int animTime = 0;
    protected int interValX = 0;
    protected int interValY = 0;
    int x = 0; int y = 0;
    int oldx, oldy;
    Game game;
    public Bullet(Tower start, Character end,Game game){
        this.start = start;
        this.end = end;
        this.interValX = start.interValX;
        this.interValY = start.interValY;
        this.game = game;
    }
    public abstract void paintMe(Graphics g);
}
