/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import static Vista.InicioPadre.identificacionPadre;
import static Vista.InicioProfesor.identificacionProfesor;
/**
 *
 * @author jalil
 */
public class Seleccion extends javax.swing.JFrame {

    private LinkedList<String> apodos = new LinkedList<>();    // Lista para almacenar el apodo
    private LinkedList<String> iconos = new LinkedList<>();    // Lista para almacenar el icono
    private LinkedList<String> cargos = new LinkedList<>();    // Lista para almacenar la persona a cargo
    public static String icono = "";
    public static String apodo = "";
    // Listas temporales para guardar apodos e iconos correspondientes
        private LinkedList<String> apodosTemp = new LinkedList<>();
        private LinkedList<String> iconosTemp = new LinkedList<>();

    /**
     * Creates new form Seleccion
     */
    public Seleccion() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnSalir.setOpaque(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnMinimizar.setOpaque(false);
        btnMinimizar.setContentAreaFilled(false);
        btnRegresar.setOpaque(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        // btnEscoger.setOpaque(false);
        // btnEscoger.setContentAreaFilled(false);
        // btnEscoger.setBorderPainted(false);
        ImageIcon icono = new ImageIcon(getClass().getResource("/icons/icon.png"));
        this.setIconImage(icono.getImage());
        //Hacer invisibles los iconos
        perfil1.setVisible(false);
        perfil2.setVisible(false);
        perfil3.setVisible(false);
        String cargo = "";
        String identificacion = "";
        System.out.println("identificacionPa"+identificacionPadre);
        System.out.println("identificacionPRo"+identificacionProfesor);
        if(!identificacionProfesor.isEmpty()){
            identificacion = identificacionProfesor;
            cargo = "profesor";
        } else {
            identificacion = identificacionPadre;
            cargo = "padre";
        }
        System.out.println("identificacion"+identificacion);
        obtenerEstudiantes(identificacion, cargo);
        int contador = 0;
        for (int i = 0; i < cargos.size(); i++) {
            if (cargos.get(i).equals(identificacion)) {
                // Si se encuentra una coincidencia, obtener los valores de las otras listas
                apodosTemp.add(apodos.get(i));
                iconosTemp.add(iconos.get(i));
                
            }
            if(contador == 2){
                break;
            }
            contador++;
        }
        System.out.println(contador);
        System.out.println(apodosTemp);
        System.out.println(iconosTemp);
        
        if (apodosTemp.size() == 1){
            perfil1.setVisible(true);
            perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(0)+".png")));
            try {
            mostrarApodo(txtApodo1, apodosTemp.get(0));
            } catch (FontFormatException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (apodosTemp.size() == 2){
            perfil1.setVisible(true);
            perfil2.setVisible(true);
            perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(0)+".png")));
            perfil2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(1)+".png")));   
            try {
            mostrarApodo(txtApodo1, apodosTemp.get(0));
            mostrarApodo(txtApodo2, apodosTemp.get(1));
            } catch (FontFormatException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            }
        }if (apodosTemp.size() == 3){
            perfil1.setVisible(true);
            perfil2.setVisible(true);
            perfil3.setVisible(true);
            perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(0)+".png")));
            perfil2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(1)+".png")));
            perfil3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/"+iconosTemp.get(2)+".png")));
            try {
            mostrarApodo(txtApodo1, apodosTemp.get(0));
            mostrarApodo(txtApodo2, apodosTemp.get(1));
            mostrarApodo(txtApodo3, apodosTemp.get(2));
            } catch (FontFormatException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeleccionIcono.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void mostrarApodo(javax.swing.JTextArea txtApodo, String apodo) throws FontFormatException, IOException {
        txtApodo.setEditable(true);
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/Schoolbell-Regular.ttf"));
        txtApodo.setFont(customFont.deriveFont(Font.PLAIN, 30));
        txtApodo.setText(""); // Limpiar el área de texto antes de agregar nueva descripción
        txtApodo.append("Perfil de: "+apodo); // Agregar la nueva descripción al área de texto
        txtApodo.setEditable(false);

    }
    public void obtenerEstudiantes(String identificacion, String cargo) {

        try (BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"))) {
            String line;           
            int count = 0;
            while ((line = br.readLine()) != null) {
                count++;
                if(cargo.equals("padre"))
                    switch (count) {
                        case 2:  
                            apodos.add(line.trim());
                            break;
                        case 9:  
                            cargos.add(line.trim());
                            break;
                        case 10: 
                            iconos.add(line.trim());
                            break;
                    }
                else{
                    switch (count) {
                        case 2:  
                            apodos.add(line.trim());
                            break;
                        case 8:  
                            cargos.add(line.trim());
                            break;
                        case 10: 
                            iconos.add(line.trim());
                            break;
                    }
                }
                if (count == 10) { // Reinicia el contador después de procesar el décimo campo
                    count = -1;
                }
            }
                
            System.out.println(apodos);
            System.out.println(cargos);
            System.out.println(iconos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root = new javax.swing.JPanel();
        perfil1 = new javax.swing.JLabel();
        perfil2 = new javax.swing.JLabel();
        perfil3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        scrollApodo1 = new javax.swing.JScrollPane();
        txtApodo1 = new javax.swing.JTextArea();
        scrollApodo2 = new javax.swing.JScrollPane();
        txtApodo2 = new javax.swing.JTextArea();
        scrollApodo3 = new javax.swing.JScrollPane();
        txtApodo3 = new javax.swing.JTextArea();
        cambiarIcono = new javax.swing.JButton();
        seleccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        root.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        perfil1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfil1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfil1MouseClicked(evt);
            }
        });
        root.add(perfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 280, 240));

        perfil2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfil2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfil2MouseClicked(evt);
            }
        });
        root.add(perfil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 290, 230));

        perfil3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        perfil3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perfil3MouseClicked(evt);
            }
        });
        root.add(perfil3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 196, 300, 250));

        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        root.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 50));

        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        root.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 30, 50, 50));

        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        root.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 30, 50, 50));

        scrollApodo1.setBorder(null);
        scrollApodo1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollApodo1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtApodo1.setColumns(20);
        txtApodo1.setRows(5);
        txtApodo1.setBorder(null);
        scrollApodo1.setViewportView(txtApodo1);

        root.add(scrollApodo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 330, 60));

        scrollApodo2.setBorder(null);
        scrollApodo2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollApodo2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtApodo2.setColumns(20);
        txtApodo2.setRows(5);
        scrollApodo2.setViewportView(txtApodo2);

        root.add(scrollApodo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, 330, 60));

        scrollApodo3.setBorder(null);
        scrollApodo3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollApodo3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txtApodo3.setColumns(20);
        txtApodo3.setRows(5);
        scrollApodo3.setViewportView(txtApodo3);

        root.add(scrollApodo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 460, 320, 60));

        cambiarIcono.setText("jButton1");
        cambiarIcono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarIconoActionPerformed(evt);
            }
        });
        root.add(cambiarIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, 50, 40));

        seleccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/seleccion estudiante.png"))); // NOI18N
        root.add(seleccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Principal principal = new Principal();
        principal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void perfil1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfil1MouseClicked
        icono = iconosTemp.get(0);
        apodo = apodosTemp.get(0);
        Iniciar iniciar = new Iniciar();
        iniciar.setVisible(true);
        System.out.println(icono);
        this.dispose();
    }//GEN-LAST:event_perfil1MouseClicked

    private void perfil2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfil2MouseClicked
        icono = iconosTemp.get(1);
        apodo = apodosTemp.get(1);
        Iniciar iniciar = new Iniciar();
        iniciar.setVisible(true);
        System.out.println(icono);
        this.dispose();
    }//GEN-LAST:event_perfil2MouseClicked

    private void perfil3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perfil3MouseClicked
        icono = iconosTemp.get(2);
        apodo = apodosTemp.get(2);
        Iniciar iniciar = new Iniciar();
        iniciar.setVisible(true);
        System.out.println(icono);
        this.dispose();
    }//GEN-LAST:event_perfil3MouseClicked

    private void cambiarIconoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarIconoActionPerformed
        icono = iconosTemp.get(0);
        SeleccionIcono selecIcono = new SeleccionIcono();
        selecIcono.setVisible(true);
        this.dispose();//Falta que solo cambia el del estudiante seleccionado
    }//GEN-LAST:event_cambiarIconoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seleccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton cambiarIcono;
    private javax.swing.JLabel perfil1;
    private javax.swing.JLabel perfil2;
    private javax.swing.JLabel perfil3;
    private javax.swing.JPanel root;
    private javax.swing.JScrollPane scrollApodo1;
    private javax.swing.JScrollPane scrollApodo2;
    private javax.swing.JScrollPane scrollApodo3;
    private javax.swing.JLabel seleccion;
    private javax.swing.JTextArea txtApodo1;
    private javax.swing.JTextArea txtApodo2;
    private javax.swing.JTextArea txtApodo3;
    // End of variables declaration//GEN-END:variables
}
