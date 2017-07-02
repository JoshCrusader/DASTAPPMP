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
public class Node {
    public int x;
    public int y;
    public int GCost = 0; //+node from home
    public int HCost; //node to end
    public int FCost; //G+H
    private String type;
    private Node parentNode;
    public Node(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setType(String t){
        type = t;
    }
    public Node getParent(){
        return parentNode;
    }
    public void setParent(Node p){
        parentNode = p;
    }
    public void setCosts(Node start, Node end){
        GCost = (Math.abs(start.x - x) + Math.abs(start.y - y))*10;
        HCost = (Math.abs(end.x - x) + Math.abs(end.y - y))*10;
        FCost = GCost+HCost;
    }
    public void setCostAgain(int gc ,Node end){
        GCost = gc;
        HCost = (Math.abs(end.x - x) + Math.abs(end.y - y))*10;
        FCost = GCost + HCost;
    }
}
