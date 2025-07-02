/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.Polygon;
import java.awt.Shape;
import shapes.Circle;
import shapes.Pentagon;
import shapes.Square;
import shapes.Triangle;

/**
 *
 * @author alejandro
 */
public class ObstacleGen {
    private int id;
    private int WIDTH;
    private int HEIGHT;
    private int obstaclewidth;
    private int obstacleheight;
    Shape obstacle;

    public ObstacleGen( int obstaclewidth, int obstacleheight){
     
        this.obstaclewidth=obstaclewidth;
        this.obstacleheight=obstacleheight;
    }
    
    public Shape getObstacle() {
        return this.obstacle;
    }
    
    public void setCenter(int x, int y){
        this.WIDTH=x;
        this.HEIGHT=y;
    }
    public void receiveID(int id){
        this.id=id;
        switch(id){
            case 0:
                this.obstacle=this.createCircle();
                break;
            case 1:
                this.obstacle=this.createPentagon();
                break;
            case 2:
                this.obstacle=this.createTriangle();
                break;
            case 3:
                this.obstacle=this.createSquare();
                break;
        }  
     
    }
    public  Square createSquare(){
        Square square=new Square(this.WIDTH, this.HEIGHT, this.obstaclewidth,this.obstacleheight);
        return square;
    }
    public Circle createCircle(){
        Circle circle=new Circle(this.WIDTH, this.HEIGHT, this.obstaclewidth,this.obstacleheight);
        return circle;
    }
    public Pentagon createPentagon(){
            Pentagon pentagon=new Pentagon(this.WIDTH+45, this.HEIGHT+35,this.obstacleheight);
        return pentagon;
    }
    public Triangle createTriangle(){
        Triangle triangle=new Triangle( this.WIDTH+45, this.HEIGHT+35, (double)this.obstaclewidth,(double)this.obstacleheight);
        return triangle;
    }
}
