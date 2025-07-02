package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorUsuario {

    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public void registrarUsuario(String nombreUsuario, String contrasena) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_USUARIOS, true))) {
            // Escribir los datos del nuevo usuario en el archivo de texto
            writer.write(nombreUsuario + ";" + contrasena);
            writer.newLine();
            System.out.println("Usuario registrado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        // Aquí implementarías la lógica para verificar las credenciales de inicio de sesión
        // Por ejemplo, podrías leer el archivo de usuarios y comparar las credenciales proporcionadas
        // Aquí solo te proporciono un esqueleto básico
        // Debes modificar esta parte según tus necesidades
        return false;
    }
}
