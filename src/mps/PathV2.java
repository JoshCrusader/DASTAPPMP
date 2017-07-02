/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mps;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author drjeoffreycruzada
 */
public class PathV2 {
    private int cellY;
    private int cellX;
    private String[][] Buttons;
    int interValX;
    int interValY;
    
    
    public ArrayList<Node> finalDestination;
    public int posx;
    public int posy;
    public Node tlatest;
    public boolean tapos= false;
    
    
    Node[][]  nodes = new Node[cellY][cellX];
    ArrayList<Node> Close = new ArrayList<>();
    ArrayList<Node> Open = new ArrayList<>();
    int regNodes = 0;
    Node start=null;
    Node end=null;
    Node latest = null;
    Node prior = null;
    Path f = null;
    public PathV2(int cellX, int cellY, String[][] Buttons, int interValX, int interValY, Path f){
        this.f = f;
        this.cellX = cellX;
        this.cellY = cellY;
        this.Buttons = Buttons;
        this.interValX = interValX;
        this.interValY = interValY;
        aStar();
    }
    public void aStar(){
        nodes = new Node[cellY][cellX];
        Close = new ArrayList<>();
        Open = new ArrayList<>();
        regNodes = 0;
        start=null;
        end=null;
        latest = null;
        prior = null;
        tapos = false;
        for(int i = 0; i < cellY; i++){
            for(int z = 0; z < cellX; z++){
                String type = " ";
                if(Buttons[i][z].equals("#")){
                    type = "#";
                }
                else if(Buttons[i][z].equals(" ")){
                    type = " ";
                }
                else if(Buttons[i][z].equals("S")){
                    type = "S";
                }
                else if(Buttons[i][z].equals("G")){
                    type = "G";
                }
                Node newNode = new Node(z,i,type);
                if(Buttons[i][z].equals("S")){
                    start = newNode;
                    //Open.add(newNode);
                    prior = newNode;
                }
                else if(Buttons[i][z].equals("G")){
                    end = newNode;
                }
                nodes[i][z] = newNode;
            }
        }
        

    }
    public void updatePath(){
        int[][] possibles = {{1,0},{0,1},{-1,0},{0,-1}};
        if(!tapos && prior != null ){//&& Close.size() != countRegularNodes(Open)+1){
            Close.add(prior);
            for(int z = 0; z < possibles.length;z++){
                int targetX = possibles[z][0];
                int targetY = possibles[z][1];
                if((prior.x - targetX) > -1 && (prior.x - targetX) <cellX &&
                    (prior.y - targetY) > -1 && (prior.y - targetY) <cellY &&
                    nodes[prior.y - targetY][prior.x - targetX] != null){
                    Node sNode = nodes[prior.y - targetY][prior.x - targetX];
                    //sNode.setCosts(start, end);
                    sNode.setCostAgain(prior.GCost+10, end);
                    if(sNode.getType().equals("G")) {
                        sNode.setParent(prior);
                        prior = sNode;
                        tapos = true;
                    }
                    else if(sNode.getType().equals(" ")){
                        Open.add(sNode);
                        sNode.setParent(prior);
                        nodes[prior.y - targetY][prior.x - targetX] = null;
                    }
                    
                    
                }
            }
            this.tapos = tapos;
            latest = prior;
            if(!tapos){ 
                prior = getPriorNode(Open);
                Open.remove(prior);
            }
            else{
                finalDestination = new ArrayList<Node>();
                Node tempLast = prior;
                while(tempLast != null){
                    posx = tempLast.x*interValX;
                    posy = tempLast.y*interValY;
                    finalDestination.add(tempLast);
                    tempLast = tempLast.getParent();
                }
                //t2 = new Timer(50,this);
                //t2.start();
            }
            if(prior != null)
                tlatest = prior;

        }
    }

    public Node getPriorNode(ArrayList <Node> nodes){
        Node lowestNode = null;
        for(int i = 0; i < nodes.size(); i++){
            if(i == 0 || nodes.get(i).FCost < lowestNode.FCost){
                lowestNode = nodes.get(i);
                //System.out.println(nodes.get(i).FCost);
            }
            else if(nodes.get(i).FCost == lowestNode.FCost && nodes.get(i).GCost < lowestNode.GCost){
                lowestNode = nodes.get(i);
            }
            else if(nodes.get(i).FCost == lowestNode.FCost && nodes.get(i).GCost == lowestNode.GCost && nodes.get(i).HCost < lowestNode.HCost){
                lowestNode = nodes.get(i);
            }
        }
        return lowestNode;
    }
}