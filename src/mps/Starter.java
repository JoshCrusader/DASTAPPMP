/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

import javax.swing.JPanel;

/**
 *
 * @author drjeoffreycruzada
 */
public class Starter {
    public static void main(String[] args) {
        MPS clas = new MPS();
        GodIntro l = new GodIntro(clas.getSize().width,clas.getSize().height,clas);
        JPanel mainPanel = new JPanel(null);
        mainPanel.setSize(clas.getSize().width,clas.getSize().height);
        mainPanel.add(l);
        clas.add(mainPanel);
        
        l.updateUI();
        
    }
}
