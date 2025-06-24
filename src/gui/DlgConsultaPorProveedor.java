package gui;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import arreglo.Materiales;
import clases.Material;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgConsultaPorProveedor extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Materiales arregloMateriales = MenuPrincipal.DatosCompartidos.materiales;
	private JTable tblResultadosMaterial;
	private JButton btnConsultar;
	private JLabel lblProveedor;
	private JComboBox<String> cmbProveedor;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	

	public DlgConsultaPorProveedor() {
		setTitle("Consulta por Tipo de Material");
		setBounds(100, 100, 815, 485);
		getContentPane().setLayout(null);
		
		lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(10, 26, 94, 14);
		getContentPane().add(lblProveedor);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new BtnConsultarActionListener());
		btnConsultar.setBounds(264, 22, 89, 23);
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
		modelo.addColumn("UNID");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("COLOR");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		
		tblResultadosMaterial.setModel(modelo);
		
		cmbProveedor = new JComboBox<String>();
		cmbProveedor.setBounds(78, 22, 176, 22);
		getContentPane().add(cmbProveedor);
		
		lblNewLabel_1 = new JLabel("Materiales según el proveedor:");
		lblNewLabel_1.setBounds(10, 96, 181, 14);
		getContentPane().add(lblNewLabel_1);
		modelo = new DefaultTableModel();
		cargarProveedores();
		ajustarAnchoColumnas();
	}

	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblResultadosMaterial.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4));  // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));  // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14));  // tipo material
		tcm.getColumn(3).setPreferredWidth(anchoColumna(4));  // unidad
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));  // cantidad
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // color
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));  // fecha
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));  // hora
	}
	void cargarProveedores() {
	    cmbProveedor.removeAllItems();
	    java.util.HashSet<String>proveedoresUnicos = new java.util.HashSet<String>();
	    for (int i = 0; i<arregloMateriales.tamaño(); i++) {
	       String proveedor = arregloMateriales.obtener(i).getProveedor();
	       if(!proveedoresUnicos.contains(proveedor)) {
		    	proveedoresUnicos.add(proveedor);
		    	cmbProveedor.addItem(proveedor);
		    }
	    }
	   
	}
	
	private class BtnConsultarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String selecProveedor = (String)cmbProveedor.getSelectedItem();
			Material material = null;
			DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
            modelo.setRowCount(0);
			for(int i = 0; i<arregloMateriales.tamaño();i++) {
				material = arregloMateriales.obtener(i);
				if(selecProveedor.equals(material.getProveedor())) {
		               imprimirDatos(material);
				}
			}
			
			
		}
	}
	private void imprimirDatos(Material material) {
		DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
			Object[] fila = { 
					material.getCodigoMaterial(),
					material.getNombreMaterial(),
					material.getTipoMaterial(),
					material.getUnidadMedida(),
					material.getCantidad(),
					material.getColor(),
					material.getFecha(),
					material.getHora()
					};
					modelo.addRow(fila);
	}
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
}