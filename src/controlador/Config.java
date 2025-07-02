/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.geom.RoundRectangle2D;
import Vista.Iniciar;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Config extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeigth = 640;

    Image background;
    Image pp;
    Image bs;
    Image bin;
    Image bcentral;
    private Clip clip;
    // Bird
    int birdx = boardWidth / 8;
    int birdy = boardHeigth / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {

        int x = birdx;
        int y = birdy;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    // obstaculos
    int LimiteX = boardWidth;
    int limiteY = 0;
    int limiteWidth = 64;
    int limiteHeight = 640;

    class obstaculo {

        int x = LimiteX;
        int y = limiteY;
        int width = limiteWidth;
        int height = limiteHeight;
        Image img;
        boolean pasó = false;

        obstaculo(Image img) {
            this.img = img;
        }
    }

    // Lógica del juego//
    Bird bird;
    int velocidadx = -4;
    int velocidadY = 0;
    int gravedad = 1;

    ArrayList<obstaculo> obstaculos;
    Random random = new Random();
    ArrayList<Integer> listaNumeros = new ArrayList<>();

    Timer tiempo;
    Timer posicionObstaculos;
    Timer cambioNumeroRandom;
    Timer AparicionDeParEImpar;
    Timer hideTimer;
    boolean Derrota = false;
    double puntaje = 0;
    boolean mostrarValor = true;
    int indiceActual = 0;
    int CONTADORDETIEMPO = 0;
    boolean mostrarTextos;
    Timer reiniciarJuegoTimer; // Temporizador para reiniciar el juego después de esperar 3 segundos
    int segundosRestantes = 3; // Segundos restantes para reiniciar el juego

    public void music() {
        try {
            // Cargar el archivo de audio
            File soundFile = new File("src/controlador/music/Ffsong.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));

            // Obtener el control de volumen
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Establecer el volumen al 80%
            float volume = 0.8f; // 80%
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

            // Establecer el volumen
            float volume = 0.70f; 
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float newVolume = min + (max - min) * volume;
            volumeControl.setValue(newVolume);

            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public Config() {
        music();
        setPreferredSize(new Dimension(boardWidth, boardHeigth));
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);

        // Cargar imágenes //
        background = new ImageIcon(getClass().getResource("./ff images/bg.png")).getImage();
        pp = new ImageIcon(getClass().getResource("./ff images/fish1.png")).getImage();
        bs = new ImageIcon(getClass().getResource("./ff images/up.png")).getImage();
        bin = new ImageIcon(getClass().getResource("./ff images/down.png")).getImage();
        bcentral = new ImageIcon(getClass().getResource("./ff images/midd.png")).getImage();

        // bird
        bird = new Bird(pp);

        // obstaculos
        obstaculos = new ArrayList<obstaculo>();

        // Tiempo de juego
        tiempo = new Timer(1000 / 60, this); // 1000/60 = 16.6
        tiempo.start();

        // Tiempo de cambio de obstaculos
        posicionObstaculos = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicionObstaculo();
            }
        });
        posicionObstaculos.start();

        // TIEMPO DE CAMBIO DE NUMERO RANDOM
        cambioNumeroRandom = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumeroRandom();
            }

        });
        cambioNumeroRandom.start();

        // TIEMPO DE CAMBIO DE TEXTOS PAR-IMPAR
        if (AparicionDeParEImpar == null) {
            AparicionDeParEImpar = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cambia el estado de showText
                    mostrarTextos = !mostrarTextos;
                    // Repite la acción después de 3 segundos para desactivar los textos
                    if (mostrarTextos) {
                        // Creamos un temporizador nuevo que se ejecuta una vez después de 3 segundos
                        hideTimer = new Timer(2200, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mostrarTextos = false;
                                repaint();
                            }
                        });
                        hideTimer.setRepeats(false); // Se ejecuta solo una vez
                        hideTimer.start(); // Inicia el temporizador
                    }
                    // Redibuja el panel
                }
            });
            AparicionDeParEImpar.start(); // Inicia el temporizador
        }

        // Inicializar el temporizador para reiniciar el juego
        reiniciarJuegoTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                segundosRestantes--;

                // Mostrar la cuenta regresiva en forma de texto
                if (segundosRestantes > 0) {
                    JOptionPane.showMessageDialog(null, segundosRestantes);
                } else {
                    // Reiniciar el juego
                    reiniciarJuego();
                    reiniciarJuegoTimer.stop(); // Detener el temporizador después de reiniciar el juego
                }
            }
        });

    }

    public void posicionObstaculo() {
        int abrirEspacio = boardHeigth / 3;
        obstaculo obstaculoSuperior = new obstaculo(bs);
        obstaculoSuperior.y = (int) (limiteY - (limiteHeight / 1.40)); // randomObstaculoY;
        obstaculos.add(obstaculoSuperior);
        obstaculo ObstaculoInferior = new obstaculo(bin);
        ObstaculoInferior.y = (int) ((obstaculoSuperior.y + limiteHeight) * 1.5) + (abrirEspacio);
        obstaculos.add(ObstaculoInferior);
        obstaculo ObstaculoCentral = new obstaculo(bcentral);
        ObstaculoCentral.height = 65;
        ObstaculoCentral.y = (int) (boardHeigth / 2.1);
        obstaculos.add(ObstaculoCentral);
        System.out.println(obstaculoSuperior.y);
        System.out.println(ObstaculoInferior.y);
        System.out.println(ObstaculoCentral.y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujar(g);

    }

    public void dibujar(Graphics g) {

        // Fondo
        g.drawImage(background, 0, 0, boardWidth, boardHeigth, null);

        // bird
        g.drawImage(bird.img, bird.x, birdy, bird.width, bird.height, null);

        // obstaculos
        for (int i = 0; i < obstaculos.size(); i++) {
            obstaculo Obstaculo = obstaculos.get(i);
            g.drawImage(Obstaculo.img, Obstaculo.x, Obstaculo.y, Obstaculo.width, Obstaculo.height, null);

        }

        // Puntaje
        g.setColor(new Color(0xFFDE59));
        if (Derrota) {
            try {
                // Cargar la fuente desde el archivo
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));

                // Registrar la fuente
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);

                // Usar la fuente
                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 25);

                // Establecer la fuente en el objeto Graphics
                g.setFont(schoolbellFont);

                // Dibujar el texto
                Color oldColor = g.getColor();
                Color textColor = new Color(0xFFDE59);
                g.setColor(textColor);
                g.drawString("Perdiste.", (int) (360 / 2.6), 640 / 2);
                g.drawString("Tu puntaje fue: " + String.valueOf((int) puntaje), (int) (360 / 3.9), (int) (640 / 1.8));
                g.setColor(oldColor);

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
                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 25);

                // Establecer la fuente en el objeto Graphics
                g.setFont(schoolbellFont);

                // Dibujar el texto
                Color oldColor = g.getColor();
                g.setColor(Color.WHITE);
                g.drawString("Puntaje: " + String.valueOf((int) puntaje), 10, 45);
                g.setColor(oldColor);

            } catch (FontFormatException | IOException e) {
                // Manejar cualquier excepción que pueda ocurrir durante la carga de la fuente
                e.printStackTrace();
            }
        }

        // NÚMERO RANDOM
        if (!listaNumeros.isEmpty()) {
            try {
                // Cargar la fuente desde el archivo
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));

                // Registrar la fuente
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);

                // Usar la fuente
                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 40);

                // Establecer la fuente en el objeto Graphics
                g.setFont(schoolbellFont);

                // Dibujar el texto en un cuadrado con bordes redondos
                int x = 10;
                int y = 100;
                int width = 60;
                int height = 60;
                int borderRadius = 20; // Radio de los bordes redondos
                // int padding = 10; Espacio entre el texto y los bordes del cuadrado

                // Crear la forma del cuadrado con bordes redondos
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, borderRadius, borderRadius);
                Graphics2D g2d = (Graphics2D) g;
                // Rellenar el cuadrado con un color de fondo
                g2d.setColor(Color.WHITE); // Cambia al color de fondo deseado
                g2d.fill(roundedRectangle);

                // Dibujar el contorno del cuadrado
                g2d.setColor(Color.WHITE); // Cambia al color del borde deseado
                g2d.draw(roundedRectangle);

                // Dibujar el texto
                Color oldColor = g.getColor();
                Color textColor = new Color(0xFFDE59);
                g2d.setColor(textColor);
                int numeroActual = listaNumeros.get(listaNumeros.size() - 1); // Obtén el último número de la lista
                int stringWidth = g2d.getFontMetrics().stringWidth(String.valueOf(numeroActual));
                int stringHeight = g2d.getFontMetrics().getHeight();
                g2d.drawString(String.valueOf(numeroActual), x + (width - stringWidth) / 2, y + (width) / 2 + (stringHeight / 4));
                // g.drawString(String.valueOf(numeroActual), 10, 150);
                comprobarNumero(numeroActual);
                g2d.setColor(oldColor);

            } catch (FontFormatException | IOException e) {
                // Manejar cualquier excepción que pueda ocurrir durante la carga de la fuente
                e.printStackTrace();
            }
        }

        // OPCIONES PAR-IMPAR
        if (mostrarTextos) {
            try {
                // Cargar la fuente desde el archivo
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));

                // Registrar la fuente
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);

                // Usar la fuente
                Font schoolbellFont = new Font("Schoolbell", Font.PLAIN, 25);

                // Establecer la fuente en el objeto Graphics
                g.setFont(schoolbellFont);

                // Guarda el color actual
                Color oldColor = g.getColor();
                Color textColor = new Color(0xFFDE59);
                g.setColor(textColor);
                g.drawString("Impar", 150, 250);
                g.drawString("Par", 160, 425);
                g.setColor(oldColor);

            } catch (FontFormatException | IOException e) {
                // Manejar cualquier excepción que pueda ocurrir durante la carga de la fuente
                e.printStackTrace();
            }
        }
    }

    public void movimiento() {

        // bird
        velocidadY += gravedad;
        birdy += velocidadY;
        birdy = Math.max(birdy, 0);

        // obstaculos
        for (int i = 0; i < obstaculos.size(); i++) {
            obstaculo Obstaculo = obstaculos.get(i);
            Obstaculo.x += velocidadx;

            if (!Obstaculo.pasó && birdx > Obstaculo.x + Obstaculo.width) {
                Obstaculo.pasó = true;
                puntaje += 0.33334; // 0.3... debido a que son 3 obstaculos, entonnces 0.3... * 3 = 1;

                // Verifica si el bird pasa por el espacio superior o inferior
                if (birdy < boardHeigth / 2) { // Bird pasa por el espacio superior
                    // Comprobar si el número aleatorio coincide con el espacio superior "IMPAR"
                    if (!listaNumeros.isEmpty() && listaNumeros.get(listaNumeros.size() - 1) % 2 != 0) {
                        // El número es impar, el juego continúa
                    } else {
                        // El número no coincide con el espacio superior "IMPAR", el jugador pierde
                        Derrota = true;
                    }
                } else { // Bird pasa por el espacio inferior
                    // Comprobar si el número aleatorio coincide con el espacio inferior "PAR"
                    if (!listaNumeros.isEmpty() && listaNumeros.get(listaNumeros.size() - 1) % 2 == 0) {
                        // El número es par, el juego continúa
                    } else {
                        // El número no coincide con el espacio inferior "PAR", el jugador pierde
                        
                        Derrota = true;
                        
                    }
                }
            }

            if (GolpearObstaculo(bird, Obstaculo)) {
                
                Derrota = true;
                
            }
        }
        if (birdy > boardHeigth || birdy == 0) {
            Derrota = true;
        }

        // Detener el juego si se estableció Derrota
        if (Derrota) {
            
            clip.stop();
            lostMusic();
            tiempo.stop();
            posicionObstaculos.stop();
            cambioNumeroRandom.stop();
            AparicionDeParEImpar.stop();
            if (hideTimer != null) {
                hideTimer.stop();
            }
            // Mostrar el JOptionPane para volver a intentarlo o salir
            int option = JOptionPane.showConfirmDialog(null, "¿Quieres intentarlo de nuevo?", "Juego terminado", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Iniciar la cuenta regresiva para reiniciar el juego
                segundosRestantes = 3;
                reiniciarJuegoTimer.start();
            } else {
                JOptionPane.showMessageDialog(null, "¡Gracias por jugar! Puntaje: " + (int) puntaje);
                clip.stop();
                Iniciar.frame.dispose();
                Iniciar inicio = new Iniciar();
                inicio.setVisible(true);
            }
        }

    }

    public String comprobarNumero(int numero) {
        String valor = null;
        if (numero % 2 == 0) {
            valor = "PAR";
        }
        if (numero % 2 == 1) {
            valor = "IMPAR";
        }
        return valor;
    }

    public boolean GolpearObstaculo(Bird a, obstaculo b) {
        return a.x < b.x + b.width
                && a.x + a.width > b.x
                && birdy < b.y + b.height
                && birdy + a.height > b.y;
    }

    public void NumeroRandom() {
        int numero = (int) (Math.random() * 30 + 1);
        listaNumeros.add(numero);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movimiento();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocidadY = -9;
            if (Derrota) {
                // Reiniciar el juego si se presiona espacio después de una derrota
                segundosRestantes = 3;
                reiniciarJuegoTimer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Método para reiniciar el juego
    public void reiniciarJuego() {
        birdy = boardHeigth / 2;
        bird.y = birdy;
        velocidadY = 0;
        obstaculos.clear();
        listaNumeros.clear();
        puntaje = 0;
        Derrota = false;
        mostrarTextos = false;
        tiempo.start();
        posicionObstaculos.start();
        cambioNumeroRandom.start();
        AparicionDeParEImpar.start();
        repaint();
        music();
    }
}
