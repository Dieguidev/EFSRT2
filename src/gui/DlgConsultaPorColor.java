package gui;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import clases.Material;
import dao.MaterialDAO;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgConsultaPorColor extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblResultadosMaterial;
	private JButton btnConsultar;
	private JLabel lblColorMaterial;
	private JComboBox<String> cmbColorMaterial;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	

	public DlgConsultaPorColor() {
		setTitle("Consulta por Tipo de Material");
		setBounds(100, 100, 815, 485);
		getContentPane().setLayout(null);
		
		lblColorMaterial = new JLabel("Color del Material:");
		lblColorMaterial.setBounds(10, 26, 138, 14);
		getContentPane().add(lblColorMaterial);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new BtnConsultarActionListener());
		btnConsultar.setBounds(260, 22, 89, 23);
		getContentPane().add(btnConsultar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 779, 2);
		getContentPane().add(separator);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 779, 314);
		getContentPane().add(scrollPane);
		
		tblResultadosMaterial = new JTable();
		scrollPane.setViewportView(tblResultadosMaterial);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("TIPO MATERIAL");
		modelo.addColumn("PROVEEDOR");
		modelo.addColumn("UNID");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		
		tblResultadosMaterial.setModel(modelo);
		
		cmbColorMaterial = new JComboBox<String>();
		cmbColorMaterial.setBounds(140, 22, 110, 22);
		getContentPane().add(cmbColorMaterial);
		
		lblNewLabel_1 = new JLabel("Materiales según su color:");
		lblNewLabel_1.setBounds(10, 96, 146, 14);
		getContentPane().add(lblNewLabel_1);
		modelo = new DefaultTableModel();
		cargarColores();
		ajustarAnchoColumnas();
	}

	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblResultadosMaterial.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4));  // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));  // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14));  // tipo
		tcm.getColumn(3).setPreferredWidth(anchoColumna(14));  // proveedor
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));  // unid
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // cantidad
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));  // fecha
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));  // hora
	}
	void cargarColores() {
	    cmbColorMaterial.removeAllItems();
	    MaterialDAO dao = new MaterialDAO();
	    for (String color : dao.obtenerColoresUnicos()) {
	        cmbColorMaterial.addItem(color);
	    }
	}

	
	private class BtnConsultarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String selecColor = (String)cmbColorMaterial.getSelectedItem();
			DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
             modelo.setRowCount(0);
             MaterialDAO dao = new MaterialDAO();
             for (Material m : dao.listarPorColor(selecColor)) {
                 imprimirDatos(m);
             }


		}
	}
	
	private void imprimirDatos(Material material) {
		DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
			Object[] fila = { 
					material.getCodigoMaterial(),
					material.getNombreMaterial(),
					material.getTipoMaterial(),
					material.getProveedor(),
					material.getUnidadMedida(),
					material.getCantidad(),
					material.getFecha(),
					material.getHora()
					};
					modelo.addRow(fila);
	}
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
}