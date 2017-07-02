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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myBox {
    int x;
    int y;
    int w;
    int h;
    int prevx;
    int prevy;
    int prevw;
    int prevh;
    
    public myBox(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        prevx = x;
        prevy = y;
        prevw = w;
        prevh = h;
    }
    
    public boolean isMouseIn(MouseEvent e){
        double MouseX = e.getPoint().getX();
        double MouseY = e.getPoint().getY();
        return (MouseX > x && MouseX < x+w && MouseY > y && MouseY < y+h);
    }
    
}
