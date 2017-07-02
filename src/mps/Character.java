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
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Character {
    //from Editor
    Node CurrentRoute = null;
    int interValX;
    int interValY;
    
    //from the path
    ArrayList<Node> finalDestination;
    ArrayList<Node> RouteTaken;
    //placement attributes
    Path p;
    int x;
    int y;
    
    //updateVariables
    
    int speed = 10;//the higher, the slower, this is the # of frames
    double formx;
    double formy;
    int counterAnim = speed+1;
    int animTime = 1;
    boolean tapos = true;//its still going on
    
    
    //stats
    int maxHealth = 100;
    int health = 100;
    int Damage = 150;
    int reward = 60;
    String direction = "d";
    String imageF = "Enemies/Minion/";
    Image image = new ImageIcon(imageF+direction+animTime+".jpeg").getImage();
    int rot = 0;
    
    public Character(Path p){
        this.p = p;
        this.finalDestination = p.finalDestination;
        this.interValX = p.interValX;
        this.interValY = p.interValY;
        RouteTaken = new ArrayList<Node>();
    }
    
    public void update(){
        animTime++;
        if(health > 0){
            if(counterAnim > speed && finalDestination != null && finalDestination.size() > 0){
                counterAnim = 0;
                RouteTaken.add(finalDestination.get(finalDestination.size()-1));
                formx = finalDestination.get(finalDestination.size()-1).x * interValX;
                formy = finalDestination.get(finalDestination.size()-1).y * interValY;
                finalDestination.remove(finalDestination.size()-1);
            }
            if(finalDestination != null && finalDestination.size() > 0){
            double myposx =  (finalDestination.get(finalDestination.size()-1).x) * interValX;
            double myposy = (finalDestination.get(finalDestination.size()-1).y) * interValY;
            x = (int)(formx + (myposx-formx) *((double)(counterAnim)/(double) speed)); 
            y = (int)(formy + (myposy-formy) *((double)(counterAnim)/(double) speed)); 
            if(myposy - formy < 0)
                direction = "u";
            else if(myposy -formy > 0)
                direction = "d";
            else if(myposx - formx < 0)
                direction = "l";
            else
                direction = "r";
            counterAnim++;
            }

            else{
                counterAnim  = speed+1;
                tapos = false;
            }
            if(animTime > 9){
                animTime = 1;
            }
            image = new ImageIcon(imageF+"walk"+direction+animTime+".png").getImage();
    }
        else{
            rot++;
            image = new ImageIcon(imageF+"dead"+direction+".png").getImage();
        }
    }
}
