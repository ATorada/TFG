/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visual;

import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ángel Torada
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Constructor del Frame Registro
     *
     */
    public Registro() {
        initComponents();

        //Establece la posición del frame centrada en la pantalla
        setLocationRelativeTo(null);

        //Oculta los paneles de error
        jPanelErrorUsuario.setVisible(false);
        jPanelErrorContrasenya.setVisible(false);
        jPanelErrorRepetirContrasenya.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        jLabelContrasenya = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jButtonVolver = new javax.swing.JButton();
        jButtonRegistrarse = new javax.swing.JButton();
        jLabelContrasenyaRepetir = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jPasswordFieldRepetir = new javax.swing.JPasswordField();
        jPanelErrorUsuario = new javax.swing.JPanel();
        jPanelErrorContrasenya = new javax.swing.JPanel();
        jPanelErrorRepetirContrasenya = new javax.swing.JPanel();
        jLabelError = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage((new ImageIcon(Login.class.getResource("/Imagenes/Icon.png"))).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoMini.png"))); // NOI18N
        getContentPane().add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 30, 300, 230));

        jLabelContrasenya.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabelContrasenya.setText("Contraseña");
        getContentPane().add(jLabelContrasenya, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabelUsuario.setText("Usuario");
        getContentPane().add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));
        getContentPane().add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 210, -1));

        jButtonVolver.setBackground(new java.awt.Color(253, 243, 255));
        jButtonVolver.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, -1));

        jButtonRegistrarse.setBackground(new java.awt.Color(253, 243, 255));
        jButtonRegistrarse.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jButtonRegistrarse.setText("Registrarse");
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        jLabelContrasenyaRepetir.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabelContrasenyaRepetir.setText("Repetir Contraseña");
        getContentPane().add(jLabelContrasenyaRepetir, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));
        getContentPane().add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 258, 210, -1));
        getContentPane().add(jPasswordFieldRepetir, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 210, -1));

        jPanelErrorUsuario.setBackground(new java.awt.Color(255, 102, 102));
        getContentPane().add(jPanelErrorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 199, 214, 30));

        jPanelErrorContrasenya.setBackground(new java.awt.Color(255, 102, 102));
        getContentPane().add(jPanelErrorContrasenya, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 257, 214, 30));

        jPanelErrorRepetirContrasenya.setBackground(new java.awt.Color(255, 102, 102));
        getContentPane().add(jPanelErrorRepetirContrasenya, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 319, 213, 30));

        jLabelError.setBackground(new java.awt.Color(255, 255, 255));
        jLabelError.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabelError.setForeground(new java.awt.Color(242, 69, 69));
        jLabelError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabelError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 360, 30));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoMorado.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 391, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón que se encarga de registrar a un usuario
    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed

        //Comprueba que las contraseñas estén iguales y que tanto el nombre como la contraseña no estén vacías
        if (jPasswordField.getText().equals(jPasswordFieldRepetir.getText())) {
            if (!jTextFieldUsuario.getText().isEmpty() && (!jPasswordField.getText().isEmpty() || !jPasswordFieldRepetir.getText().isEmpty())) {
                if (comprobarContrasenya(jPasswordField.getText())) {
                    try {
                        Login.CONTROLADOR.registrarUsuario(jTextFieldUsuario.getText().toLowerCase(), jPasswordField.getText());
                        JOptionPane.showMessageDialog(this, "La cuenta se ha creado correctamente", "Cuenta creada", JOptionPane.INFORMATION_MESSAGE);
                        Login.INSTANCE.setVisible(true);
                        jLabelError.setText("");
                        this.dispose();
                    } catch (SQLException ex) {
                        jPanelErrorUsuario.setVisible(true);
                        jPanelErrorContrasenya.setVisible(false);
                        jPanelErrorRepetirContrasenya.setVisible(false);
                        jLabelError.setText("Ese usuario ya existe, introduzca otro nombre.");
                    }
                } else {
                    jPanelErrorUsuario.setVisible(false);
                    jPanelErrorContrasenya.setVisible(true);
                    jPanelErrorRepetirContrasenya.setVisible(true);
                }
            } else {
                if (jPasswordField.getText().isEmpty()) {
                    jPanelErrorContrasenya.setVisible(true);
                    jLabelError.setText("La casilla de contraseña no puede estar vacía.");
                } else {
                    jPanelErrorContrasenya.setVisible(false);
                }
                if (jPasswordFieldRepetir.getText().isEmpty()) {
                    jPanelErrorRepetirContrasenya.setVisible(true);
                    jLabelError.setText("La casilla de contraseña no puede estar vacía.");
                } else {
                    jPanelErrorRepetirContrasenya.setVisible(false);
                }
                if (jTextFieldUsuario.getText().isEmpty()) {
                    jPanelErrorUsuario.setVisible(true);
                    jLabelError.setText("La casilla de usuario no puede estar vacía.");
                } else {
                    jPanelErrorUsuario.setVisible(false);
                }
            }
        } else {
            jPanelErrorUsuario.setVisible(false);
            jPanelErrorContrasenya.setVisible(true);
            jPanelErrorRepetirContrasenya.setVisible(true);
            jLabelError.setText("Las contraseñas no coinciden.");
        }
    }//GEN-LAST:event_jButtonRegistrarseActionPerformed

    //Botón que se encarga de volver a la pantalla de login
    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        Login.INSTANCE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelContrasenya;
    private javax.swing.JLabel jLabelContrasenyaRepetir;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelErrorContrasenya;
    private javax.swing.JPanel jPanelErrorRepetirContrasenya;
    private javax.swing.JPanel jPanelErrorUsuario;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPasswordField jPasswordFieldRepetir;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

    //Método que comprueba que la contraseña cumpla todos los requisitos
    private boolean comprobarContrasenya(String contrasenya) {

        boolean contrasenyaValida = true;

        if (!contrasenya.matches("(.*[A-Z].*)")) {
            jLabelError.setText("La contraseña debe tener mínimo una mayúscula.");
            contrasenyaValida = false;
        }

        if (!contrasenya.matches("(.*[a-z].*)")) {
            jLabelError.setText("La contraseña debe tener mínimo una minúscula.");
            contrasenyaValida = false;
        }

        if (!contrasenya.matches("(.*[0-9].*)")) {
            jLabelError.setText("La contraseña debe tener mínimo un número.");
            contrasenyaValida = false;
        }

        if (!contrasenya.matches("(.*[@,#,$,%].*$)")) {
            jLabelError.setText("La contraseña debe tener mínimo un carácter especial.");
            contrasenyaValida = false;
        }
        return contrasenyaValida;
    }
}
