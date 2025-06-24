package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import clases.ConexionSQL;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DlgReporteBS extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTable tblReporte;
	private DefaultTableModel modelo;
	private JTextField txtLimite;
	private JButton btnGenerar;
	private JScrollPane scrollPane;
	private JLabel lblTitulo;
	private JLabel lblLimite;


	public DlgReporteBS() {
		setTitle("Reporte - Materiales con Bajo Stock");
		setBounds(100, 100, 964, 500);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Materiales con Stock Menor o Igual al Límite");
		lblTitulo.setBounds(10, 10, 400, 25);
		getContentPane().add(lblTitulo);

		lblLimite = new JLabel("Límite de Stock:");
		lblLimite.setBounds(10, 45, 100, 25);
		getContentPane().add(lblLimite);

		txtLimite = new JTextField("10");
		txtLimite.setBounds(120, 45, 50, 25);
		getContentPane().add(txtLimite);
		txtLimite.setColumns(10);

		btnGenerar = new JButton("Generar Reporte");
		btnGenerar.setBounds(190, 45, 150, 25);
		getContentPane().add(btnGenerar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 928, 360);
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

		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarReporteBajoStock();
			}
		});
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblReporte.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4));  // Código
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14)); // Nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14)); // Tipo
		tcm.getColumn(3).setPreferredWidth(anchoColumna(14)); // Proveedor
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));  // Unidad
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // Cantidad
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4)); // Fecha
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4)); // Hora
	}

	private int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}

	private void generarReporteBajoStock() {
	    modelo.setRowCount(0);

	    int limite;
	    try {
	        limite = Integer.parseInt(txtLimite.getText().trim());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Ingrese un número válido como límite", "Error", JOptionPane.ERROR_MESSAGE);
	        txtLimite.requestFocus();
	        return;
	    }

	    String sql = "SELECT * FROM COMP_MATERIALES WHERE CantMat <= ?";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, limite);
	        ResultSet rs = ps.executeQuery();

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
	        JOptionPane.showMessageDialog(this, "Error al consultar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
