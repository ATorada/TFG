/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Visual;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ángel Torada
 */
public class ListarFinanzas extends javax.swing.JDialog {

    /**
     * Constructor ListarFinanzas
     *
     * @param parent Instancia del parent
     * @param modal Boolean de si es modal o no
     */
    public ListarFinanzas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //Cargar Tabla
        cargarTabla();

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

        jButtonCerrarSesion = new javax.swing.JButton();
        jPanelGasto = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFinanzas = new javax.swing.JTable();
        jButtonSalir = new javax.swing.JButton();
        jLabelGastosFlexiblesTitulo = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage((new ImageIcon(Login.class.getResource("/Imagenes/Icon.png"))).getImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonCerrarSesion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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
        getContentPane().add(jButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 120, -1));

        jPanelGasto.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGasto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelGasto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableFinanzas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Cantidad", "Periodo", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFinanzas);

        jPanelGasto.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 300));

        getContentPane().add(jPanelGasto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 490, 320));

        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 110, -1));

        jLabelGastosFlexiblesTitulo.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabelGastosFlexiblesTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelGastosFlexiblesTitulo.setText("FINANZAS");
        getContentPane().add(jLabelGastosFlexiblesTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 490, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoMorado.png"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 530, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botón que se encarga de salir de jDialog
    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    //Listeners que se encargan de cambiar el botón de cerrar sesión on hover
    private void jButtonCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionMouseEntered
        jButtonCerrarSesion.setBackground(new Color(255, 102, 102));
        jButtonCerrarSesion.setForeground(Color.white);
    }//GEN-LAST:event_jButtonCerrarSesionMouseEntered

    private void jButtonCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionMouseExited
        jButtonCerrarSesion.setBackground(Color.white);
        jButtonCerrarSesion.setForeground(Color.black);
    }//GEN-LAST:event_jButtonCerrarSesionMouseExited

    private void jButtonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarSesionActionPerformed
        Login.INSTANCE.setVisible(true);
        Main.INSTANCE.dispose();
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelGastosFlexiblesTitulo;
    private javax.swing.JPanel jPanelGasto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFinanzas;
    // End of variables declaration//GEN-END:variables

    //Método que se encarga de cargar la tabla de finanzas
    private void cargarTabla() {
        //Obtiene todos los gastos e ingresos del usuario junto a su respectiva información
        HashMap ingresos = Login.CONTROLADOR.obtenerTodosLosIngresosUsuario(Login.USUARIO);
        HashMap gastos = Login.CONTROLADOR.obtenerTodosLosGastosUsuario(Login.USUARIO);

        DefaultTableModel model = (DefaultTableModel) jTableFinanzas.getModel();

        for (Object entry : ingresos.entrySet()) {
            ArrayList atributos = ((Map.Entry<String, ArrayList>) entry).getValue();
            model.addRow(new Object[]{atributos.get(0), atributos.get(1), atributos.get(2), "Ingreso"});
        }

        for (Object entry : gastos.entrySet()) {
            ArrayList atributos = ((Map.Entry<String, ArrayList>) entry).getValue();
            model.addRow(new Object[]{atributos.get(0), atributos.get(1), atributos.get(2), "Gasto"});
        }

    }
}
