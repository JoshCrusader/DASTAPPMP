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
public class Minion extends Character {
    public Minion(Path p,int hcap,int dcap){
        super(p);
        maxHealth = 50+hcap;
        health = 50+hcap;
        Damage = 30+dcap;
        int reward = 60;
        super.imageF = "Enemies/Minion/";
    }
    
}
