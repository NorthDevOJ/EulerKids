/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author alejandro
 */
public class Pentagon extends Polygon implements Shape, Identifiable {

    private int x;
    private int y;
    private int sideLength;
    private int height;
    private static final int ID = 1;

    public Pentagon(int x, int y, int height) {
        this.npoints = 5;
        this.x = x;
        this.y = y;
        this.height = height;
        this.sideLength = calculateSideLength();
        this.ypoints = new int[npoints];
        this.xpoints = new int[npoints];
        calculateVertices();

    }

    public int calculateSideLength() {
        return (int) Math.round(height / (0.5 * (1 + Math.sqrt(5))));

    }

    public void calculateVertices() {
        double angle = Math.toRadians(-90);
        for (int i = 0; i < 5; i++) {
            int xp = (int) (this.x + sideLength * Math.cos(angle));
            int yp = (int) (this.y + sideLength * Math.sin(angle));

            this.xpoints[i] = xp;
            this.ypoints[i] = yp;

            angle += 2 * Math.PI / 5;
        }
    }

    public int getHeight() {
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

        for (int i = 0; i < npoints; i++) {
            ypoints[i] += speed;
        }
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
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Pentagono";
    }

}
