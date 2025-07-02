
package controlador;

import Vista.Bienvenida;


public class Arranque {
    public static void main(String[] args) {
        Bienvenida inicio = new Bienvenida();
        inicio.CargarScreen(inicio);
    }
}
