package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import clases.ConexionSQL;



public class DlgReporteSTM extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblReporte;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane;
    
  
    public DlgReporteSTM() {
        setTitle("Reporte - Stock Total por Tipo de Material");
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 440, 280);
        getContentPane().add(scrollPane);

        tblReporte = new JTable();
        modelo = new DefaultTableModel();
        modelo.addColumn("Tipo de Material");
        modelo.addColumn("Stock Total");
        tblReporte.setModel(modelo);
        scrollPane.setViewportView(tblReporte);
        
        JLabel lblReporte = new JLabel("REPORTE:");
        lblReporte.setBounds(20, 27, 59, 14);
        getContentPane().add(lblReporte);
        
        JLabel lblTitulo = new JLabel("STOCK TOTAL POR TIPO DE MATERIAL");
        lblTitulo.setBounds(20, 11, 321, 14);
        getContentPane().add(lblTitulo);

        ajustarAnchoColumnas();
        generarReporte();
    }

    private void ajustarAnchoColumnas() {
        TableColumnModel tcm = tblReporte.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(300); // Tipo
        tcm.getColumn(1).setPreferredWidth(100); // Total
    }

    public void generarReporte() {
        modelo.setRowCount(0);

        String sql = "SELECT TipMat, SUM(CantMat) AS StockTotal FROM COMP_MATERIALES GROUP BY TipMat";

        try (Connection con = ConexionSQL.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String tipo = rs.getString("TipMat");
                int stock = rs.getInt("StockTotal");
                Object[] fila = { tipo, stock };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error al obtener stock por tipo de material: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}