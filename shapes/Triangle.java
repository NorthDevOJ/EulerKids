/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author alejandro
 */
public class Triangle extends Polygon implements Shape, Identifiable{

    int x;
    int y;
    double width;
    double height;
    private static final int ID=2;

    public Triangle(int x, int y, double width, double height) {
        this.npoints = 3;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        calculateVertices();
    }

    private void calculateVertices() {
        // Top vertex
        xpoints[0] = x;
        ypoints[0] = (int) (y - height / 2);

        // Left vertex
        xpoints[1] = (int) (x - width / 2);
        ypoints[1] = (int) (y + height / 2);

        // Right vertex
        xpoints[2] = (int) (x + width / 2);
        ypoints[2] = (int) (y + height / 2);
    }

    public double getHeight() {
        return height;
    }

    public int[] getXpoints() {
        return this.xpoints;
    }

    public int[] getYpoints() {
        return this.ypoints;
    }

    public void setYplusSpeed(int speed) {
        this.y += speed;
        calculateVertices();
    }

    public int getlowestYPoint() {
        int minY = this.ypoints[0];
        for (int i = 1; i < npoints; i++) {
            if (ypoints[i] < minY) {
                minY = ypoints[i];
            }
        }
        return minY;
    }
    
  
    @Override
       public int getID(){
        return ID;
    }
    

    @Override
    public String toString() {
        return "Triangulo";
    }

}
