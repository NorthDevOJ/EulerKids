/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.Iniciar;
import modelo.ObstacleGen;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Polygon;
import shapes.Circle;
import shapes.CollisionChecker;
import shapes.Identifiable;
import shapes.Pentagon;
import shapes.Square;
import shapes.Triangle;
import static Vista.Iniciar.app;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.Random;
import modelo.MP3Player;
// import music.gswing.mp3;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author alejandro
 */
public class Display extends JPanel implements ActionListener, KeyListener {

    private int space;
    private int speed;
    private int itemwidth = 80;
    private int itemheight = 70;
    private int WIDTH = 500;
    private int HEIGHT = 700;
    private int move = 20;
    private int count = 1;
    private ArrayList<Shape> shapes;
    private ArrayList<Rectangle> lines;
    private Rectangle car;
    private Timer timer;
    boolean linef = true;
    boolean fshape;
    ObstacleGen obstaclegen;
    private int checkID = -1;
    private int score = 0;
    private boolean over = false;
    private Clip clip;

    public Display() {
        this.timer = new Timer(20, this);
        this.shapes = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.car = new Rectangle(WIDTH / 2 - 90, HEIGHT - 100, itemwidth, itemheight);
        this.space = 300;
        this.speed = 2;
        this.fshape = true;
        addKeyListener(this);
        setFocusable(true);
        addShape(true);
        addShape(false);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        timer.start();
        music();
        
    }

    public void music() {
        try {
            // Cargar el archivo de audio
            File soundFile = new File("src/controlador/music/gswing.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));

            // Obtener el control de volumen
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Establecer el volumen al 40%
            float volume = 0.70f; // 40%
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float newVolume = min + (max - min) * volume;
            volumeControl.setValue(newVolume);

            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    
    public void lostMusic() {
        try {
            // Cargar el archivo de audio
            File soundFile = new File("src/controlador/music/gameover.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));

            // Obtener el control de volumen
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Establecer el volumen al 40%
            float volume = 0.70f; // 40%
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float newVolume = min + (max - min) * volume;
            volumeControl.setValue(newVolume);

            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void addLine(boolean first) {
        int x = WIDTH / 2 - 2;
        int y = 700;
        int width = 4;
        int height = 100;
        int sp = 130;
        if (first) {
            lines.add(new Rectangle(x, y - (lines.size() * sp), width, height));
        } else {
            lines.add(new Rectangle(x, lines.get(lines.size() - 1).y - sp, width, height));
        }

    }

    public void addShape(boolean first) {
        Random random = new Random();
        this.obstaclegen = new ObstacleGen(this.itemwidth, this.itemheight);
        ObstacleGen gen = this.obstaclegen;
        int shapeID = random.nextInt(3);
        int x;

        if (first == true) {
            x = WIDTH / 2 - 90;
            gen.setCenter(x, 0);
            gen.receiveID(shapeID);

            //agregando 1ra figura geometrica            
            shapeID = random.nextInt(4);
            // System.out.println(shapeID);
            gen.receiveID(shapeID);

            this.shapes.add(gen.getObstacle());
            this.fshape = false;
            // System.out.println("" + gen.getObstacle().getClass());

        } else {
            x = WIDTH / 2 + 10;
            gen.setCenter(x, 0);
            gen.receiveID(shapeID);
            //validando para que no se repitan figuras geometricas
            while (shapes.get(0).getClass().equals(gen.getObstacle().getClass())) {
                shapeID = random.nextInt(4);
                // System.out.println(shapeID);
                gen.receiveID(shapeID);
            }
            this.shapes.add(gen.getObstacle());
            this.fshape = true;
            // System.out.println("" + gen.getObstacle().getClass());
        }
    }

    Image turtle = new ImageIcon(getClass().getResource("./Gsimg/turtle.png")).getImage();
    Image road = new ImageIcon(getClass().getResource("./Gsimg/road.png")).getImage();
    Image bg = new ImageIcon(getClass().getResource("./Gsimg/bg.png")).getImage();

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        Graphics2D graphics2 = (Graphics2D) graphics;
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
        // System.out.println("WIDTH: "+500);
        // System.out.println("HEIGHT: "+700);
        /*
        for (Rectangle rect : lines) {
            graphics.setColor(Color.white);
            graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
        }*/
 /*for (Rectangle rect : lines) {
            // Dibujar imágenes del paisaje en lugar de rectángulos
            graphics.drawImage(imagenPaisaje, rect.x, rect.y, rect.width, rect.height, null);
        }*/
        // graphics.setColor(Color.gray);
        // graphics.fillRect(WIDTH / 2 - 100, 0, 200, HEIGHT);
        graphics.drawImage(road, WIDTH / 2 - 100, 0, 200, HEIGHT, null);
        // System.out.println("WIDTH: "+200);
        // System.out.println("HEIGHT: "+700);
        // graphics.setColor(Color.blue);
        // graphics.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        graphics.drawImage(turtle, car.x, car.y, car.width, car.height, null);
        // System.out.println("Car x: "+car.x);
        // System.out.println("Car y: "+car.y);
        // System.out.println("Car width: "+car.width);
        // System.out.println("Car height: "+car.height);
        Color shapeColor = new Color(0xffde59);
        graphics.setColor(shapeColor);
        for (Shape shape : shapes) {
            if (shape instanceof Triangle || shape instanceof Pentagon) {
                Polygon polygon = (Polygon) shape;
                graphics.fillPolygon(polygon);
            } else {
                if (shape instanceof Square) {
                    Rectangle square = (Rectangle) shape;
                    graphics.fillRect(square.x, square.y, square.width, square.height);
                } else if (shape instanceof Circle) {
                    Rectangle circle = (Rectangle) shape;
                    graphics.fillOval(circle.x, circle.y, circle.width, circle.height);
                }

            }

        }
        graphics.setColor(Color.black);

        if (over == true) {
            try {
                clip.stop();
                // Cargar la fuente desde el archivo
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));

                // Registrar la fuente
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);

                // Usar la fuente
                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 25);

                // Establecer la fuente en el objeto Graphics
                graphics.setFont(schoolbellFont);

                // Guarda el color actual
                Color oldColor = graphics.getColor();
                Color textColor = new Color(0x6471C0);
                graphics.setColor(textColor);
                graphics.drawString("Game Over", WIDTH / 2 - 50, 700 / 2);
                timer.stop();
                lostMusic();
                graphics.drawString("Restart Game? [Y] [N]", WIDTH / 2 - 100, 700 / 2 + 30);
                graphics.setColor(oldColor);

            } catch (FontFormatException | IOException e) {
                // Manejar cualquier excepción que pueda ocurrir durante la carga de la fuente
                e.printStackTrace();
            }

        } else {
            try {
                // Cargar la fuente desde el archivo
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));

                // Registrar la fuente
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);

                // Usar la fuente
                Font schoolbellFont1 = new Font("Schoolbell", Font.PLAIN, 25);

                // Guarda el color actual
                Color oldColor = graphics.getColor();
                Color textColor = new Color(0x6471C0);
                graphics.setColor(Color.white);
                // Establecer la fuente en el objeto Graphics
                graphics.setFont(schoolbellFont1);
                graphics.drawString("Puntaje: " + score, 350, 40);
                // Dibujar el texto en un cuadrado con bordes redondos
                int x = 40;
                int y = 80;
                graphics.setColor(Color.white);
                int width = 150;
                int height = 60;
                int borderRadius = 20; // Radio de los bordes redondos
                // int padding = 10; Espacio entre el texto y los bordes del cuadrado

                // Crear la forma del cuadrado con bordes redondos
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, borderRadius, borderRadius);
                Graphics2D g2d = (Graphics2D) graphics;
                // Rellenar el cuadrado con un color de fondo
                g2d.setColor(Color.WHITE); // Cambia al color de fondo deseado
                g2d.fill(roundedRectangle);

                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 30);
                g2d.setFont(schoolbellFont);
                // Dibujar el contorno del cuadrado
                g2d.setColor(Color.WHITE); // Cambia al color del borde deseado
                g2d.draw(roundedRectangle);

                graphics.setColor(textColor);

                // Dibujar el texto
                g2d.setColor(textColor);
                int stringWidth = g2d.getFontMetrics().stringWidth(String.valueOf(this.getShapeFromId(checkID)));
                int stringHeight = g2d.getFontMetrics().getHeight();
                g2d.drawString(String.valueOf(this.getShapeFromId(checkID)), x + (width - stringWidth) / 2, y + (2 * height) / 3);
                g2d.setColor(oldColor);
                graphics.setColor(oldColor);
            } catch (FontFormatException | IOException e) {
                // Manejar cualquier excepción que pueda ocurrir durante la carga de la fuente
                e.printStackTrace();
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Shape shape;
        Rectangle line;
        if (checkID == -1) {
            Random random = new Random();
            int rnum = random.nextInt(2);
            Identifiable rshape = (Identifiable) this.shapes.get(rnum);
            int correctShapeId = rshape.getID();
            checkID = correctShapeId;
            // System.out.println("correctShapeID: " + correctShapeId);
        }

        // System.out.println("Speed: "+ speed);
        count++;

        for (int i = 0; i < shapes.size(); i++) {
            shape = this.shapes.get(i);
            if (count % 1000 == 0) {
                if (move < 10) {
                    speed++;
                    move += 10;
                    System.out.println("Speed: " + speed);
                }
            }

            if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                triangle.setYplusSpeed(speed);
            } else if (shape instanceof Pentagon) {
                Pentagon pentagon = (Pentagon) shape;
                pentagon.setYplusSpeed(speed);
            } else if (shape instanceof Square) {
                Square square = (Square) shape;
                square.setYplusSpeed(speed);

            } else if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                circle.setYplusSpeed(speed);
            }
        }

        for (Shape s : shapes) {
            if (s instanceof Rectangle) {
                //Collision check for rectangles
                Rectangle rect = (Rectangle) s;
                if (rect.intersects(car)) {
                    if (rect instanceof Square && checkID != 3) {
                        Square square = (Square) rect;
                        car.y = square.getYpos() + itemheight;
                    } else if (rect instanceof Circle && checkID != 0) {
                        Circle circle = (Circle) s;

                        car.y = circle.y + itemheight;
                    }
                }
            } else {
                //Collision check for polygons
                Polygon pol = (Polygon) s;
                //intersects check
                if (CollisionChecker.polygonIntersectsRectangle(pol, car)) {
                    if (pol instanceof Triangle && checkID != 2) {
                        Triangle triangle = (Triangle) pol;
                        car.y = triangle.getlowestYPoint() + itemheight;
                    } else if (pol instanceof Pentagon && checkID != 1) {
                        Pentagon pentagon = (Pentagon) pol;

                        car.y = pentagon.getlowestYPoint() + itemheight;

                    }
                }

            }
        }

        //car out of frame
        if (car.y + car.height > HEIGHT) {
            this.over = true;
        } else {
            this.score += 3;
        }

        //obstacles out of frame
        for (int i = 0; i < shapes.size(); i++) {
            shape = shapes.get(0);
            if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                if (triangle.getlowestYPoint() + triangle.getHeight() > HEIGHT) {
                    shapes.remove(0);
                    shapes.remove(0);
                    addShape(fshape);
                    addShape(fshape);
                    Random random = new Random();
                    int rnum = random.nextInt(2);
                    Identifiable rshape = (Identifiable) this.shapes.get(rnum);
                    int correctShapeId = rshape.getID();
                    checkID = correctShapeId;
                    // System.out.println("correctShapeID: " + correctShapeId);

                }
            } else if (shape instanceof Pentagon) {
                Pentagon pentagon = (Pentagon) shape;
                if (pentagon.getlowestYPoint() + pentagon.getHeight() > HEIGHT) {
                    shapes.remove(0);
                    shapes.remove(0);
                    addShape(fshape);
                    addShape(fshape);
                    Random random = new Random();
                    int rnum = random.nextInt(2);
                    Identifiable rshape = (Identifiable) this.shapes.get(rnum);
                    int correctShapeId = rshape.getID();
                    checkID = correctShapeId;
                    // System.out.println("correctShapeID: " + correctShapeId);

                }
            } else if (shape instanceof Square) {
                Square square = (Square) shape;
                if (square.getYpos() + square.height > HEIGHT) {
                    shapes.remove(0);
                    shapes.remove(0);
                    addShape(fshape);
                    addShape(fshape);
                    Random random = new Random();
                    int rnum = random.nextInt(2);
                    Identifiable rshape = (Identifiable) this.shapes.get(rnum);
                    int correctShapeId = rshape.getID();
                    checkID = correctShapeId;
                    // System.out.println("correctShapeID: " + correctShapeId);

                }
            } else if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                if (circle.y + circle.height > this.HEIGHT) {
                    shapes.remove(0);
                    shapes.remove(0);
                    addShape(fshape);
                    addShape(fshape);
                    Random random = new Random();
                    int rnum = random.nextInt(2);
                    Identifiable rshape = (Identifiable) this.shapes.get(rnum);
                    int correctShapeId = rshape.getID();
                    checkID = correctShapeId;
                    // System.out.println("correctShapeID: " + correctShapeId);

                }

            }
        }

        //Creating and deleting lines
        for (int i = 0; i < lines.size(); i++) {
            line = this.lines.get(i);
            if (count % 1000 == 0) {
                speed++;
            }
            line.y += speed;
        }

        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            if (line.y > HEIGHT) {
                lines.remove(line);
                addLine(false);
            }
        }

        repaint();

    }

    //moving up
    public void moveup() {
        if (car.y - move < 0) {
            // System.out.println("\b");
        } else {
            car.y -= move;
        }
    }

    public void movedown() {
        if (car.y + move + car.height > HEIGHT - 1) {
            // System.out.println("\b");
        } else {
            car.y += move;
        }
    }

    public void moveleft() {
        /*  if (car.x - move < WIDTH / 2 - 90) {
            System.out.println("\b");
        } else {
            car.x -= move;
        }*/
        car.x = WIDTH / 2 - 90;
    }

    public void moveright() {
        /*if (car.x + move > WIDTH / 2 + 10) {
            System.out.println("\b");
        } else {
            car.x += move;
        }*/
        car.x = WIDTH / 2 + 10;;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                moveup();
                break;
            case KeyEvent.VK_DOWN:
                movedown();
                break;
            case KeyEvent.VK_LEFT:
                moveleft();
                break;
            case KeyEvent.VK_RIGHT:
                moveright();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            /*case KeyEvent.VK_UP:
                moveup();
                break;
            case KeyEvent.VK_DOWN:
                movedown();
                break;
            case KeyEvent.VK_LEFT:
                moveleft();
                break;
            case KeyEvent.VK_RIGHT:
                moveright();
                break;*/
            case KeyEvent.VK_ESCAPE:
                timer.stop();
                clip.stop();
                break;
            case KeyEvent.VK_ENTER:
                if (over != true) {
                    timer.start();
                }
                clip.start();
                break;
            case KeyEvent.VK_Y:
                if (over == true) {
                    clip.start(); //  Reanudar el clip
                    this.restartGame();
                    music();                   
                }
                break;
            case KeyEvent.VK_N:
                if (over == true) {
                    clip.stop(); //  Detener la reproducción al finalizar el juego
                    this.setVisible(false);
                    app.dispose();
                    Iniciar inicio = new Iniciar();
                    inicio.setVisible(true);
                }
                break;
            default:
                break;
        }
    }

    public void restartGame() {
        music();        
        over = false;
        score = 0;
        shapes.clear();
        lines.clear();
        car.setBounds(WIDTH / 2 - 90, HEIGHT - 100, itemwidth, itemheight);
        checkID = -1;
        addShape(true);
        addShape(false);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        addLine(true);
        timer.start();
        speed = 2;
        move = 20;
        count = 1;
        
    }

    public Identifiable getShapeFromId(int checkID) {
        for (Shape shape : this.shapes) {
            Identifiable check = (Identifiable) shape;
            if (check.getID() == checkID) {
                return check;
            }
        }
        return null;
    }
}
