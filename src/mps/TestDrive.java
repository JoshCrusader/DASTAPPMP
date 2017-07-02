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
public class TestDrive {
    public static void main(String[] args) {
        ArrayList <Integer> a = new ArrayList<>();
        int s = Integer.parseInt(Integer.toBinaryString(450));
        System.out.println(s);
        
        int deci = 1;
        while(s > 0){
            if(s%10 == 1)
                a.add(deci);
            s/=10;
            deci *=2;
        }
        for(int i = 0; i < a.size(); i ++){
            System.out.println(a.get(i));
        }
    }
}
