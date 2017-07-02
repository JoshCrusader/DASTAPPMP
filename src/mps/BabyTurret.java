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
import java.awt.geom.Line2D;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.filechooser.*;
public class BabyTurret extends Tower{
   Timer t;
   int reloadtime = 10;
   int reload = reloadtime-1;
   Clip clip;
    public BabyTurret(Game g,int x, int y,int interValX, int interValY){
        super(g,x,y,interValX,interValY);
        range = 250;
        range = range/48*interValX;
    }
    
    public void paintMe(Graphics g){
        g.drawImage(this.baseImage,this.x, this.y, interValX, interValY,game);
        //g.drawImage(me.headImage,me.x, me.y, interValX, interValY,this);

        Image image = this.headImage;
        AffineTransform at = AffineTransform.getTranslateInstance(this.x, this.y);
        double a = (double)(interValX)/(double)(image.getWidth(game));
        double b = (double)(interValY)/(double)(image.getHeight(game));
        at.scale(a, b);
        Character derp = findNearest();
        Graphics2D g2d = (Graphics2D)g;
        if(derp == null)
            at.rotate(Math.atan2(-100,0),(double)(image.getWidth(game))/2.0,(double)(image.getHeight(game))/2.0);
        else{
            at.rotate(Math.atan2(derp.y-y,derp.x-x),(double)(image.getWidth(game))/2.0,(double)(image.getHeight(game))/2.0);
            if(reload == reloadtime){
                //sfx();
                g2d.setStroke(new BasicStroke(4));
                g.setColor(Color.pink);
                damageHim(derp,5);
                g.drawLine(x+interValX/2,y+interValY/2,derp.x+interValX/2,derp.y+interValY/2);
            }
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
    public Character findNearest(){
        Character c = null;
        for(int i = 0; i < game.characters.size();i++){
            Character curcar = game.characters.get(i);
            if(curcar.health > 0){
                if(c == null){
                    double myx = Math.abs(curcar.x-x)*Math.abs(curcar.x-x);
                    double myy = Math.abs(curcar.y-y)*Math.abs(curcar.y-y);
                    double mydist = Math.sqrt(myx+myy);
                    if(mydist <= range){
                        c = curcar;
                    }

                }
                else{
                    double distbeatx = Math.abs(c.x-x)*Math.abs(c.x-x);
                    double distbeaty = Math.abs(c.y-y)*Math.abs(c.y-y);
                    double distbeat = Math.sqrt(distbeatx+distbeaty);

                    double myx = Math.abs(curcar.x-x)*Math.abs(curcar.x-x);
                    double myy = Math.abs(curcar.y-y)*Math.abs(curcar.y-y);
                    double mydist = Math.sqrt(myx+myy);
                    if(mydist < distbeat && mydist <=range) c = curcar;
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
            File file = new File("laser1.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("wala kwenta buhay ko");
        }
        
    }
}
