/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Visual;

import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ángel Torada
 */
public class UnirseUnidadFamiliar extends javax.swing.JDialog {

    /**
     * Constructor AnyadirGasto
     *
     * @param parent Instancia del parent
     * @param modal Boolean de si es modal o no
     */
    public UnirseUnidadFamiliar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Oculto el panel de error
        jPanelErrorUsuario.setVisible(false);
        //Establezco la localización del dialog en el centro de la pantalla
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelUnidadFamiliar = new javax.swing.JPanel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPanelErrorUsuario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonUnirse = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabelUnirseAUnaUnidadFamiliar = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage((new ImageIcon(Login.class.getResource("/Imagenes/Icon.png"))).getImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelUnidadFamiliar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelUnidadFamiliar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsuario.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jLabelUsuario.setText("Usuario");
        jPanelUnidadFamiliar.add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 54, -1, -1));
        jPanelUnidadFamiliar.add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 50, 230, 27));

        jPanelErrorUsuario.setBackground(new java.awt.Color(255, 102, 102));
        jPanelErrorUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelUnidadFamiliar.add(jPanelErrorUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 50, 230, 27));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Introduzca un usuario que pertenezca a la unidad familiar");
        jPanelUnidadFamiliar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));

        getContentPane().add(jPanelUnidadFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 350, 90));

        jButtonUnirse.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jButtonUnirse.setText("Unirse");
        jButtonUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUnirseActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUnirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 110, -1));

        jButtonSalir.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 110, -1));

        jLabelUnirseAUnaUnidadFamiliar.setFont(new java.awt.Font("Segoe UI Variable", 1, 22)); // NOI18N
        jLabelUnirseAUnaUnidadFamiliar.setText("UNIRSE A UNA UNIDAD FAMILIAR");
        getContentPane().add(jLabelUnirseAUnaUnidadFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoMorado.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón que se encarga de añadir un usuario a la unidad familiar
    private void jButtonUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUnirseActionPerformed
        //Se comprueba que el usuario no esté vacío, en caso de serlo muestra el panel de error
        if (!jTextFieldUsuario.getText().isEmpty()) {
            try {
                //Intenta insertar el usuario en la unidad familiar, si va todo correctamente avisa el usuario y lo añade a la unidad familiar
                int id = Login.CONTROLADOR.obtenerIdUnidadFamiliar(jTextFieldUsuario.getText());
                if (id <= 0) {
                    JOptionPane.showMessageDialog(this, "Ese usuario no existe o no está en una unidad familiar", "Usuario no añadido a la unidad familiar", JOptionPane.WARNING_MESSAGE);
                } else {
                    Login.CONTROLADOR.actualizarUnidadFamiliar(Login.USUARIO, id);
                    JOptionPane.showMessageDialog(this, "Has sido añadido", "Unión completada", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (SQLException ex) {
                //En caso de error avisa al user
                JOptionPane.showMessageDialog(this, "Ese usuario no existe o no está en una unidad familiar", "Usuario no añadido a la unidad familiar", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            jPanelErrorUsuario.setVisible(true);
        }
    }//GEN-LAST:event_jButtonUnirseActionPerformed

    //Botón que se encarga de cerrar el dialog actual
    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonUnirse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelUnirseAUnaUnidadFamiliar;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelErrorUsuario;
    private javax.swing.JPanel jPanelUnidadFamiliar;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}