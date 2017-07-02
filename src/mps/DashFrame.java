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
public class DashFrame extends JFrame {
    JPanel parent;
    public DashFrame(JPanel parent){
        super("Dashboard c: im so epal");
        this.setSize(600,800);
        this.setLocationRelativeTo(null);
        
        this.parent = parent;
        
        
    }

}
