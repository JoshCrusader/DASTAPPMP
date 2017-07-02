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
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
public class Plasma1 extends Bullet{
    private int endx;
    private int endy;
    private int startx;
    private int starty;
    public Plasma1(Tower start, Character end, Game game){
        super(start,end,game);
        damage = 5;
        endx = end.x;
        endy = end.y;
        startx = start.x;
        starty = start.y;
    }
    public void paintMe(Graphics g){
        oldx = x;
        oldy = y;
        x = (int)(startx - (startx - endx) * ((double)animTime/(double)speed))+interValX/2;
        y = (int)(starty - (starty - endy) * ((double)animTime/(double)speed))+interValY/2;
        AffineTransform at = AffineTransform.getTranslateInstance(this.x, this.y);
        double a = (double)(interValX/2)/(double)(image.getWidth(game));
        double b = (double)(interValY/2)/(double)(image.getHeight(game));
        at.scale(a, b);
        at.rotate(Math.atan2(endy-starty,endx-startx),(double)(image.getWidth(game))/4.0,(double)(image.getHeight(game))/4.0);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(image, at, null);
        if(animTime == speed-1 && end != null){
            damageHim(end,damage);
        }
        this.animTime++;
    }
    
    public void damageHim(Character c, int dmg){
        if(c.health - dmg < 0){
            c.health = 0;
        }
        else{
            c.health-=dmg;
        }
    }
}
