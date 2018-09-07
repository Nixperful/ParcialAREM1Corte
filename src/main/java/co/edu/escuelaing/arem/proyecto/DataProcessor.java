/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arem.proyecto;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author 2110432
 */
public class DataProcessor {
    LinkedList<Float> data= new LinkedList();
    
    public DataProcessor(LinkedList data) {
             
        this.data=data;
    }

    public LinkedList getData() {
        return data;
    }
    public float getSum() {
        float sum=0;
        for (int i=0; i<data.size();i++){
            sum+=data.get(i);
        }
        return sum;
    }
    public float getMult() {
        float mul=1;
        for (int i=0; i<data.size();i++){
            mul*=data.get(i);
        }
        return mul;
    }

    public float getMin() {
        float min=data.get(0);
        
        for (int i=0; i<data.size();i++){
            if(data.get(i)<min){
                min=data.get(i);
            }
        }
        
        return min;
    }
    public float getMax() {
        float max=data.get(0);
        for (int i=0; i<data.size();i++){
            if(data.get(i)>max){
                max=data.get(i);
            }
        }
        return max;
    }
    
    
}
