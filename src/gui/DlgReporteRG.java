package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import clases.ConexionSQL;
;

public class DlgReporteRG extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTable tblReporte;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;



	public DlgReporteRG() {
		setTitle("Reporte - Resumen General de Materiales");
		setBounds(100, 100, 820, 500);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Resumen General de Materiales");
		lblTitulo.setBounds(10, 10, 400, 25);
		getContentPane().add(lblTitulo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 784, 390);
		getContentPane().add(scrollPane);

		tblReporte = new JTable();
		scrollPane.setViewportView(tblReporte);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("TIPO");
		modelo.addColumn("PROVEEDOR");
		modelo.addColumn("UNIDAD");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		tblReporte.setModel(modelo);

		ajustarAnchoColumnas();
		generarResumenGeneral();  
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblReporte.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(14));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void generarResumenGeneral() {
	    modelo.setRowCount(0); // Limpiar tabla

	    String sql = "SELECT * FROM COMP_MATERIALES";

	    try (Connection con = ConexionSQL.conectar();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {

	        while (rs.next()) {
	            Object[] fila = {
	                rs.getString("CodMat"),
	                rs.getString("NomMat"),
	                rs.getString("TipMat"),
	                rs.getString("ProvMat"),
	                rs.getString("UnidMed"),
	                rs.getInt("CantMat"),
	                rs.getDate("Fecha"),
	                rs.getTime("Hora")
	            };
	            modelo.addRow(fila);
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al generar resumen general: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
