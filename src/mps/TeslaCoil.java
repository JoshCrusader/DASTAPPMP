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
import java.awt.geom.AffineTransform;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class TeslaCoil extends Tower{
   Timer t;
   int reloadtime = 0;
   int dmg = 2;
   int reload = reloadtime-1;
   Clip clip;
   int rotation = 0;
    public TeslaCoil(Game g,int x, int y,int interValX, int interValY){
        super(g,x,y,interValX,interValY);
        this.baseImage = new ImageIcon("Towers/base2.png").getImage();
        this.headImage = new ImageIcon("Towers/tring2.png").getImage();
        range = 105;
        range = range/48*interValX;
        //sfx();
        
        
    }
    
    public void paintMe(Graphics g){
        g.drawImage(this.baseImage,this.x, this.y, interValX, interValY,game);
        //g.drawImage(me.headImage,me.x, me.y, interValX, interValY,this);
        rotation++;
        if(rotation == 361){
            rotation = 0;
        }
        Image image = this.headImage;
        AffineTransform at = AffineTransform.getTranslateInstance(this.x, this.y);
        double a = (double)(interValX)/(double)(image.getWidth(game));
        double b = (double)(interValY)/(double)(image.getHeight(game));
        at.scale(a, b);
        ArrayList<Character> derps = findNearest();
        Graphics2D g2d = (Graphics2D)g;
            at.rotate(Math.toRadians(rotation),(double)(image.getWidth(game))/2.0,(double)(image.getHeight(game))/2.0);
            if(reload == reloadtime){
                for(int m = 0; m < derps.size();m++){
                    //the lightning effects//
                    Character derp = derps.get(m); 
                    g2d.setStroke(new BasicStroke(2));
                    g.setColor(Color.cyan);
                    damageHim(derp,dmg);
                    for(int z = 0; z < 2; z++){
                        int goalx = derp.x+interValX/2;
                        int goaly = derp.y+interValY/2;
                        int formx = x+interValX/2;
                        int formy = y+interValY/2;
                        int origx = formx; int origy = formy;
                        int distx = goalx - formx;
                        int disty = goaly - formy;
                        double myDist = Math.sqrt(distx*distx+disty*disty);
                        for(double i = 0; i < 4; i++){
                            int tempx = (int)(origx+(goalx-origx)*((i+1.0)/4.0)-(myDist-distx)/8 + Math.random()*(myDist-distx)/4);
                            int tempy = (int)(origy+(goaly-origy)*((i+1.0)/4.0)-(myDist-disty)/8 + Math.random()*(myDist-disty)/4);
                            if(i == 3){
                                tempx = goalx;tempy = goaly;
                            }
                            g.drawLine(formx,formy,tempx,tempy);
                            formx = tempx; formy = tempy;
                        }
                    }

                
                }
                /*
                //SOUND EFECTS//
                if(derps.size() == 0 && clip.isRunning() || health == 0){
                    clip.stop();
                }
                else if(derps.size() >= 1 && !clip.isRunning() && game.health > 0){
                    clip.loop(-1);
                }*/
            }
        g2d.drawImage(image, at, null);
        g2d.setStroke(new BasicStroke());
        
        
        if(reload == reloadtime) reload = 0;
        else reload++;
        if(game.tabon){
            g.setColor(Color.red);
            g.fillRect(this.x+(int)(interValX*0.05), this.y, (int)(interValX*0.9), (int)(interValY*0.2));
            g.setColor(Color.green);
            g.fillRect(this.x+(int)(interValX*0.05), this.y, (int)(interValX*0.9*((double)this.health/(double)this.maxhealth)), (int)(interValY*0.2));
            g.setColor(Color.black);
            g.drawRect(this.x+(int)(interValX*0.05), this.y, (int)(interValX*0.9), (int)(interValY*0.2));
        }
    }
    public ArrayList<Character> findNearest(){
        ArrayList<Character> c = new ArrayList<>();
        for(int i = 0; i < game.characters.size();i++){
            Character curcar = game.characters.get(i);
            if(curcar.health > 0){
                    double myx = Math.abs(curcar.x-x)*Math.abs(curcar.x-x);
                    double myy = Math.abs(curcar.y-y)*Math.abs(curcar.y-y);
                    double mydist = Math.sqrt(myx+myy);
                    if(mydist <= range){
                        c.add(curcar);
                    }
            }
        }
        return c;
    }
    public void damageHim(Character c, int dmg){
        if(c.health - dmg < 0){
            c.health = 0;
        }
        else{
            c.health-=dmg;
        }
    }
    public void sfx(){
        try{
            //credits to @Debbie Simon
            File file = new File("Spark.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }
}
