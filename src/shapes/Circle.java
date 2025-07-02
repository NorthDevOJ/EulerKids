/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Rectangle;
import java.awt.Shape;


/**
 *
 * @author alejandro
 */
public class Circle extends Rectangle implements Shape, Identifiable{
    private static final int ID=0;
    public Circle(int x, int y, int width, int height){
        super(x,y,width,height);
    }

    public int getYpos() {
        return this.y;
    }

    public int getHeight0() {
        return height;
    }
    
    
    public void setYplusSpeed(int speed) {
        this.y +=speed;
    }

    @Override
    public String toString() {
        return "Circulo";
    }
    
    @Override
    public int getID(){
        return ID;
    }
    
}
