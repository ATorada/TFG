/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Visual;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Ángel Torada
 */
public class UnidadFamiliar extends javax.swing.JFrame {

    /**
     * Constructor de la Unidad Familiar
     */
    public UnidadFamiliar() {
        initComponents();

        //Carga en caso de que tenga o no una unidad familiar
        cargarValores();

        //Se encarga de poner la ventana en el centro
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

        jPanelResumen = new javax.swing.JPanel();
        jLabelGastosTitulo = new javax.swing.JLabel();
        jLabelIngresosTitulo = new javax.swing.JLabel();
        jLabelIngresos = new javax.swing.JLabel();
        jLabelGastos = new javax.swing.JLabel();
        jLabelParticipantesTitulo = new javax.swing.JLabel();
        jLabelParticipantes = new javax.swing.JLabel();
        jButtonCerrarSesion = new javax.swing.JButton();
        jButtonSalirDeLaUnidad = new javax.swing.JButton();
        jLabelNoEstasEnUnidadFamiliar = new javax.swing.JLabel();
        jButtonCrear = new javax.swing.JButton();
        jButtonUnirse = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage((new ImageIcon(Login.class.getResource("/Imagenes/Icon.png"))).getImage());
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelResumen.setBackground(new java.awt.Color(249, 246, 248));
        jPanelResumen.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelResumen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelGastosTitulo.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabelGastosTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGastosTitulo.setText("GASTOS DEL MES");
        jPanelResumen.add(jLabelGastosTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 108, 112, -1));

        jLabelIngresosTitulo.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabelIngresosTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIngresosTitulo.setText("INGRESOS DEL MES");
        jPanelResumen.add(jLabelIngresosTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 39, -1, -1));

        jLabelIngresos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIngresos.setText("$");
        jPanelResumen.add(jLabelIngresos, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 67, 100, -1));

        jLabelGastos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGastos.setText("$");
        jPanelResumen.add(jLabelGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 130, 100, -1));

        jLabelParticipantesTitulo.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabelParticipantesTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelParticipantesTitulo.setText("PARTICIPANTES");
        jPanelResumen.add(jLabelParticipantesTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 164, 112, -1));

        jLabelParticipantes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelParticipantes.setText("$");
        jPanelResumen.add(jLabelParticipantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 100, -1));

        getContentPane().add(jPanelResumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 200, 230));

        jButtonCerrarSesion.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButtonCerrarSesion.setText("Cerrar Sesión");
        jButtonCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonCerrarSesionMouseExited(evt);
            }
        });
        jButtonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 120, -1));

        jButtonSalirDeLaUnidad.setText("Salir de la Unidad Familiar");
        jButtonSalirDeLaUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirDeLaUnidadActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalirDeLaUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 200, -1));

        jLabelNoEstasEnUnidadFamiliar.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabelNoEstasEnUnidadFamiliar.setText("NO ESTÁS EN NINGUNA UNIDAD FAMILIAR");
        getContentPane().add(jLabelNoEstasEnUnidadFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jButtonCrear.setText("Crear una Unidad");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 140, -1));

        jButtonUnirse.setText("Unirse a una Unidad");
        jButtonUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUnirseActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUnirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 160, -1));

        jButtonVolver.setText("Volver");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoMorado.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        Main.INSTANCE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonVolverActionPerformed

    //Botón que se encarga de salir de la unidad familiar
    private void jButtonSalirDeLaUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirDeLaUnidadActionPerformed
        try {
            Login.CONTROLADOR.actualizarUnidadFamiliar(Login.USUARIO, 0);
            cargarValores();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadFamiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSalirDeLaUnidadActionPerformed

    //Botón que se encarga de crear una unidad familiar con el usuario actual
    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        try {
            Login.CONTROLADOR.crearUnidadFamiliar(Login.USUARIO);
            cargarValores();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadFamiliar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCrearActionPerformed

    //Botón que se encarga de unir al usuario actual a una unidad familiar existente gracias a un Dialog
    private void jButtonUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUnirseActionPerformed
        UnirseUnidadFamiliar unidadFamiliar = new UnirseUnidadFamiliar(this, true);
        unidadFamiliar.setVisible(true);
        cargarValores();
    }//GEN-LAST:event_jButtonUnirseActionPerformed

    //Listeners que cambian el color del botón de cerrar sesión
    private void jButtonCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionMouseEntered
        jButtonCerrarSesion.setBackground(new Color(255, 102, 102));
        jButtonCerrarSesion.setForeground(Color.white);
    }//GEN-LAST:event_jButtonCerrarSesionMouseEntered

    private void jButtonCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionMouseExited
        jButtonCerrarSesion.setBackground(Color.white);
        jButtonCerrarSesion.setForeground(Color.black);
    }//GEN-LAST:event_jButtonCerrarSesionMouseExited

    private void jButtonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionActionPerformed
        Main.INSTANCE.dispose();
        Login.INSTANCE.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonSalirDeLaUnidad;
    private javax.swing.JButton jButtonUnirse;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelGastos;
    private javax.swing.JLabel jLabelGastosTitulo;
    private javax.swing.JLabel jLabelIngresos;
    private javax.swing.JLabel jLabelIngresosTitulo;
    private javax.swing.JLabel jLabelNoEstasEnUnidadFamiliar;
    private javax.swing.JLabel jLabelParticipantes;
    private javax.swing.JLabel jLabelParticipantesTitulo;
    private javax.swing.JPanel jPanelResumen;
    // End of variables declaration//GEN-END:variables

    //Método que se encarga de cargar los valores de la unidad familiar
    private void cargarValores() {
        //Obtiene todos los gastos e ingresos de los usuarios de la unidad familiar
        HashMap map = Login.CONTROLADOR.obtenerUnidadFamiliar(Login.USUARIO);

        //Comprueba que si está vacío, si no lo está carga al usuario su panel de unidad familiar
        if (!map.isEmpty()) {
            jPanelResumen.setVisible(true);
            jButtonSalirDeLaUnidad.setVisible(true);
            jLabelNoEstasEnUnidadFamiliar.setVisible(false);
            jButtonCrear.setVisible(false);
            jButtonUnirse.setVisible(false);

            double totalGastos = 0;
            double totalIngresos = 0;

            for (Object entry : map.entrySet()) {
                ArrayList finanzas = ((Map.Entry<String, ArrayList>) entry).getValue();
                totalGastos += (double) finanzas.get(0);
                totalIngresos += (double) finanzas.get(1);
            }

            jLabelGastos.setText(String.valueOf(String.valueOf(new BigDecimal(totalGastos).setScale(2, RoundingMode.HALF_DOWN).toString())));
            jLabelIngresos.setText(String.valueOf(String.valueOf(new BigDecimal(totalIngresos).setScale(2, RoundingMode.HALF_DOWN).toString())));
            jLabelParticipantes.setText(String.valueOf(map.size()));
        } else {
            jPanelResumen.setVisible(false);
            jButtonSalirDeLaUnidad.setVisible(false);
            jLabelNoEstasEnUnidadFamiliar.setVisible(true);
            jButtonCrear.setVisible(true);
            jButtonUnirse.setVisible(true);
        }
    }
}