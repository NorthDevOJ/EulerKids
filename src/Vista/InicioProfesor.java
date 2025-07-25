/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author perfil
 */
public class InicioProfesor extends javax.swing.JFrame {
  
        private LinkedList<String> profesores = new LinkedList<>();
        public static String identificacionProfesor = "";
        
    public InicioProfesor() {
        initComponents();
        this.setLocationRelativeTo(null);
        btnSalir.setOpaque(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
        btnMinimizar.setOpaque(false);
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.setBorderPainted(false);
        btnPadre.setOpaque(false);
        btnPadre.setContentAreaFilled(false);
        btnPadre.setBorderPainted(false);
        btnRegresar.setOpaque(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnEstudiante.setOpaque(false);
        btnEstudiante.setContentAreaFilled(false);
        btnEstudiante.setBorderPainted(false);  
        btnInicio.setOpaque(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setBorderPainted(false);
        ImageIcon icono = new ImageIcon(getClass().getResource("/icons/icon.png"));
        this.setIconImage(icono.getImage());
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
        txtID = new javax.swing.JTextField();
        btnEstudiante = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        btnPadre = new javax.swing.JButton();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        root.setPreferredSize(new java.awt.Dimension(836, 700));
        root.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtID.setBackground(new java.awt.Color(238, 238, 238));
        txtID.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtID.setBorder(null);
        txtID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtID.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIDMousePressed(evt);
            }
        });
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        root.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 300, 40));

        btnEstudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudianteActionPerformed(evt);
            }
        });
        root.add(btnEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 260, 50));

        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        root.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        root.add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 50, 50));

        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        root.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 50, 50));

        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        root.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 462, 130, 50));

        btnPadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPadreActionPerformed(evt);
            }
        });
        root.add(btnPadre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 250, 50));

        txtContraseña.setBackground(new java.awt.Color(238, 238, 238));
        txtContraseña.setBorder(null);
        root.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login profesor.png"))); // NOI18N
        root.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 836, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudianteActionPerformed
        InicioEstudiante inicioestudiante = new InicioEstudiante();
        inicioestudiante.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEstudianteActionPerformed

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

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        cargarProfesoresDesdeArchivo();
        System.out.println("padres: "+profesores);
        if (verificarProfesorExiste(txtID.getText())){
            String contraseña = new String(txtContraseña.getPassword());
            System.out.println("Digitada: "+contraseña);
            identificacionProfesor = txtID.getText();
            if(verificarContraseña(txtID.getText(), contraseña)){
                Seleccion seleccion = new Seleccion();
                seleccion.setVisible(true);
                this.dispose();            
            }
            else{
                JOptionPane.showMessageDialog(this, "Contrseña incorrecta", "Error de ingreso", JOptionPane.ERROR_MESSAGE);        
            }
        } else {
            JOptionPane.showMessageDialog(this, "Número de indentificación no registrado", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
        }   
        //Falta funcion que verifique el login y mandar la cedula al frame de seleccion con un static
    }//GEN-LAST:event_btnInicioActionPerformed

    private void txtIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMousePressed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void btnPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPadreActionPerformed
        InicioPadre iniciopadre = new InicioPadre();
        iniciopadre.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPadreActionPerformed

    public void cargarProfesoresDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("profesores.txt"))) {
            String line;            
            int count = 0;
            while ((line = br.readLine()) != null) {
                count++;
                switch (count) {
                    case 1:  
                        profesores.add(line.trim());
                }
                if (count == 8) { // Reinicia el contador después de procesar el ultimo campo
                    count = -1;
                }
            }
            System.out.print("prof"+profesores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public boolean verificarContraseña(String identificacion, String contraseña) {
        try (BufferedReader br = new BufferedReader(new FileReader("profesores.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(identificacion)) {
                    // Saltar líneas hasta llegar a la contraseña almacenada
                    for (int i = 0; i < 2; i++) {
                        br.readLine();
                    }
                    String contrasenaAlmacenada = br.readLine().trim();
                    System.out.println(contrasenaAlmacenada);
                    return contrasenaAlmacenada.equals(contraseña);
                }    
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return false;
    }
        
    public boolean verificarProfesorExiste(String identificacion) {
        System.err.println(!profesores.contains(identificacion));
        return profesores.contains(identificacion);
    }
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
            java.util.logging.Logger.getLogger(InicioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEstudiante;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnPadre;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel root;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
