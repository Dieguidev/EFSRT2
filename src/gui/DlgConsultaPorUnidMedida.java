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

public class DlgConsultaPorUnidMedida extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Materiales arregloMateriales = MenuPrincipal.DatosCompartidos.materiales;
	private JTable tblResultadosMaterial;
	private JButton btnConsultar;
	private JLabel lblTipoMaterial;
	private JComboBox<String> cmbUnidMed;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	
	private final String [] unidadesDeMedida = {
			"Unid.",
			"Kg.", 
			"Cm.", 
			"Lt."
	};
	
	public DlgConsultaPorUnidMedida() {
		setTitle("Consulta por Tipo de Material");
		setBounds(100, 100, 815, 485);
		getContentPane().setLayout(null);
		
		lblTipoMaterial = new JLabel("Unidad de Medida:");
		lblTipoMaterial.setBounds(10, 26, 113, 14);
		getContentPane().add(lblTipoMaterial);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new BtnConsultarActionListener());
		btnConsultar.setBounds(237, 22, 89, 23);
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
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("COLOR");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		
		tblResultadosMaterial.setModel(modelo);
		
		cmbUnidMed = new JComboBox<String>();
		cmbUnidMed.setBounds(133, 22, 94, 22);
		getContentPane().add(cmbUnidMed);
		
		lblNewLabel_1 = new JLabel("Materiales según la Unid Medida:");
		lblNewLabel_1.setBounds(10, 96, 211, 14);
		getContentPane().add(lblNewLabel_1);
		modelo = new DefaultTableModel();
		cargarUnidadMedida();
		ajustarAnchoColumnas();
	}

	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblResultadosMaterial.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna( 4));  // codigo
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));  // nombre
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14));  // tipo material
		tcm.getColumn(3).setPreferredWidth(anchoColumna(14));  // proveedor
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));  // cantidad
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // color
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));  // fecha
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));  // hora
	}
	void cargarUnidadMedida() {
		cmbUnidMed.removeAllItems();
	    for (String unidadMedida : unidadesDeMedida) {
	    	cmbUnidMed.addItem(unidadMedida);
	    }
	}
	private class BtnConsultarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String selecUnidadMedida = (String)cmbUnidMed.getSelectedItem();
			Material material = null;
			DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
            modelo.setRowCount(0);
            
			for(int i = 0; i<arregloMateriales.tamaño();i++) {
				material = arregloMateriales.obtener(i);
				if(selecUnidadMedida.equals(material.getUnidadMedida())) {
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
					material.getProveedor(),
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