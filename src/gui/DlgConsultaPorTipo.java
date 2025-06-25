package gui;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import clases.Material;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import dao.MaterialDAO;



public class DlgConsultaPorTipo extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private Materiales arregloMateriales = MenuPrincipal.DatosCompartidos.materiales;
	private JTable tblResultadosMaterial;
	private JButton btnConsultar;
	private JLabel lblTipoMaterial;
	private JComboBox<String> cmbTipoMaterial;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	
	private final String [] tiposMaterial = {
		    "Materia Prima",
		    "Insumos Auxiliares",
		    "Empaque y Etiqueta",
		    "Quimicos",
		    "Herramientas"
	};

	public DlgConsultaPorTipo() {
		setTitle("Consulta por Tipo de Material");
		setBounds(100, 100, 815, 485);
		getContentPane().setLayout(null);
		
		lblTipoMaterial = new JLabel("Tipo de Material:");
		lblTipoMaterial.setBounds(10, 26, 94, 14);
		getContentPane().add(lblTipoMaterial);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new BtnConsultarActionListener());
		btnConsultar.setBounds(288, 22, 89, 23);
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
		modelo.addColumn("PROVEEDOR");
		modelo.addColumn("UNID");
		modelo.addColumn("COLOR");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		
		tblResultadosMaterial.setModel(modelo);
		
		cmbTipoMaterial = new JComboBox<String>();
		cmbTipoMaterial.setBounds(125, 22, 153, 22);
		getContentPane().add(cmbTipoMaterial);
		
		lblNewLabel_1 = new JLabel("Materiales según su tipo:");
		lblNewLabel_1.setBounds(10, 96, 209, 14);
		getContentPane().add(lblNewLabel_1);
		modelo = new DefaultTableModel();
		cargarTiposMaterial();
		ajustarAnchoColumnas();
	}

	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblResultadosMaterial.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(4));  // CodigoMaterial
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));  // NombreMaterial
		tcm.getColumn(2).setPreferredWidth(anchoColumna(14));  // proveedor
		tcm.getColumn(3).setPreferredWidth(anchoColumna(4));  // unidad
		tcm.getColumn(4).setPreferredWidth(anchoColumna(4));  // color
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // Cantidad
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));  // Fecha
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));  // Hora
	}
	void cargarTiposMaterial() {
	    cmbTipoMaterial.removeAllItems();
	    for (String tipo : tiposMaterial) {
	        cmbTipoMaterial.addItem(tipo);
	    }
	}
	
	private class BtnConsultarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			String selecTipo = (String)cmbTipoMaterial.getSelectedItem();
			//Material material = null;
			MaterialDAO dao = new MaterialDAO();
			  DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
              modelo.setRowCount(0);
              
              for (Material material : dao.listarMateriales()) {
                  if (selecTipo.equals(material.getTipoMaterial())) {
                      imprimirDatos(material);
                  }
              }

			//for(int i = 0; i<arregloMateriales.tamaño();i++) {
				//material = arregloMateriales.obtener(i);
				//if(selecTipo.equals(material.getTipoMaterial())) {
					
		          //     imprimirDatos(material);
				//}
		//	}
			
			
		}
	}
	private void imprimirDatos(Material material) {
		DefaultTableModel modelo = (DefaultTableModel) tblResultadosMaterial.getModel();
			Object[] fila = { 
					material.getCodigoMaterial(),
					material.getNombreMaterial(),
					material.getProveedor(),
					material.getUnidadMedida(),
					material.getColor(),
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