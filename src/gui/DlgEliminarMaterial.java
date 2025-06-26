package gui;

import arreglo.Materiales;
import clases.Material;
import dao.MaterialDAO;
import gui.MenuPrincipal.DatosCompartidos;

import java.awt.EventQueue;


import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DlgEliminarMaterial extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigo;
	private JLabel lblNombreMaterial;
	private JLabel lblTipoMaterial;
	private JLabel lblColor;
	private JLabel lblProveedor;
	private JTextField txtNombreMaterial;
	private JTextField txtTipoMaterial;
	private JTextField txtColor;
	private JTextField txtProveedor;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblMateriales;
	private DefaultTableModel modelo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgEliminarMaterial dialog = new DlgEliminarMaterial();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgEliminarMaterial() {
		setResizable(false);
		setTitle("Eliminar Material");
		setBounds(100, 100, 880, 610);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("C\u00F3digo");
		lblCodigo.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigo);
		
		lblNombreMaterial = new JLabel("Nombre de material:");
		lblNombreMaterial.setBounds(10, 35, 129, 23);
		getContentPane().add(lblNombreMaterial);
	
		lblTipoMaterial = new JLabel("Tipo de material:");
		lblTipoMaterial.setBounds(10, 60, 96, 23);
		getContentPane().add(lblTipoMaterial);
		
		lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 85, 70, 23);
		getContentPane().add(lblColor);
		
		lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(10, 110, 70, 23);
		getContentPane().add(lblProveedor);

		txtNombreMaterial = new JTextField();
		txtNombreMaterial.setBounds(166, 35, 221, 23);
		getContentPane().add(txtNombreMaterial);
		txtNombreMaterial.setEditable(false);
		txtNombreMaterial.setColumns(10);

		txtTipoMaterial = new JTextField();
		txtTipoMaterial.setBounds(166, 60, 143, 23);
		getContentPane().add(txtTipoMaterial);
		txtTipoMaterial.setEditable(false);
		txtTipoMaterial.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(166, 85, 86, 23);
		getContentPane().add(txtColor);
		txtColor.setEditable(false);
		txtColor.setColumns(10);

		txtProveedor = new JTextField();
		txtProveedor.setBounds(166, 110, 86, 23);
		getContentPane().add(txtProveedor);
		txtProveedor.setEditable(false);
		txtProveedor.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(514, 10, 101, 23);
		getContentPane().add(btnBuscar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(514, 197, 124, 47);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 307, 844, 253);
		getContentPane().add(scrollPane);
		
		tblMateriales = new JTable();
		tblMateriales.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblMateriales);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		modelo.addColumn("Código");
		modelo.addColumn("Nombre");
		modelo.addColumn("Color");
		modelo.addColumn("Tipo");
		modelo.addColumn("Proveedor");
		modelo.addColumn("Unidad Medida");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblMateriales.setModel(modelo);
		
		cmbCodigo = new JComboBox<String>();
		cmbCodigo.setBounds(166, 10, 86, 22);
		getContentPane().add(cmbCodigo);
		
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(10, 136, 70, 23);
		getContentPane().add(lblCantidad);
		
		lblUnidadDeMedida = new JLabel("Unidad de medida:");
		lblUnidadDeMedida.setBounds(10, 162, 110, 23);
		getContentPane().add(lblUnidadDeMedida);
		
		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(166, 137, 50, 23);
		getContentPane().add(txtCantidad);
		
		txtUnidadMedida = new JTextField();
		txtUnidadMedida.setEditable(false);
		txtUnidadMedida.setColumns(10);
		txtUnidadMedida.setBounds(166, 163, 50, 23);
		getContentPane().add(txtUnidadMedida);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 197, 110, 23);
		getContentPane().add(lblFecha);
		
		lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 221, 110, 23);
		getContentPane().add(lblHora);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(166, 197, 86, 23);
		getContentPane().add(txtFecha);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setColumns(10);
		txtHora.setBounds(166, 221, 86, 23);
		getContentPane().add(txtHora);
		
		lblMaterialesEliminados = new JLabel("MATERIALES ELIMINADOS:");
		lblMaterialesEliminados.setBounds(10, 273, 175, 23);
		getContentPane().add(lblMaterialesEliminados);
		
		ajustarAnchoColumnas();
		listar();
		modelo.setRowCount(0);
		cargarCodigosMaterial();
	}
	
	//  Declaración global
	private JTextField txtCantidad;
	private JTextField txtUnidadMedida;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JComboBox<String> cmbCodigo;
	private JLabel lblHora;
	private JLabel lblFecha;
	private JLabel lblUnidadDeMedida;
	private JLabel lblCantidad;
	private JLabel lblMaterialesEliminados;
	MaterialDAO dao = new MaterialDAO();

	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			eliminarMaterial();
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		consultarMaterial();
	}

	//  Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblMateriales.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(6));  // CodigoMaterial
		tcm.getColumn(1).setPreferredWidth(anchoColumna(14));  // NombreMaterial
		tcm.getColumn(2).setPreferredWidth(anchoColumna(8));  // ColorMaterial
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10));  // TipoMaterial
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10));  // Proveedor
		tcm.getColumn(5).setPreferredWidth(anchoColumna(4));  // UnidadMedida
		tcm.getColumn(6).setPreferredWidth(anchoColumna(4));  // Cantidad
		tcm.getColumn(7).setPreferredWidth(anchoColumna(4));  // Fecha
		tcm.getColumn(8).setPreferredWidth(anchoColumna(4));  // Hora
	}
	void listar() {
	    modelo.setRowCount(0);
	    for (Material m : dao.listarMateriales()) {
	        Object[] fila = {
	            m.getCodigoMaterial(),
	            m.getNombreMaterial(),
	            m.getColor(),
	            m.getTipoMaterial(),
	            m.getProveedor(),
	            m.getUnidadMedida(),
	            m.getCantidad(),
	            m.getFecha(),
	            m.getHora()
	        };
	        modelo.addRow(fila);
	    }
	}	
	void cargarCodigosMaterial() {
		cmbCodigo.removeAllItems();
	    for (String cod : dao.obtenerCodigosMaterial()) {
	        cmbCodigo.addItem(cod);
	    }
	    btnBuscar.setEnabled(cmbCodigo.getItemCount() > 0);

	}
	void consultarMaterial() {
	    String codigo = leerCodigo();
	    Material m = dao.buscarMaterial(codigo);
	    if (m == null) {
	        mensaje("El código no existe.");
	        return;
	    }

	    txtNombreMaterial.setText(m.getNombreMaterial());
	    txtTipoMaterial.setText(m.getTipoMaterial());
	    txtColor.setText(m.getColor());
	    txtProveedor.setText(m.getProveedor());
	    txtCantidad.setText(String.valueOf(m.getCantidad()));
	    txtUnidadMedida.setText(m.getUnidadMedida());
	    txtFecha.setText(m.getFecha());
	    txtHora.setText(m.getHora());
	}
	
	void eliminarMaterial() {
	    String codigo = leerCodigo();
	    Material m = dao.buscarMaterial(codigo);
	    if (m == null) {
	        mensaje("No se encontró el material.");
	        return;
	    }

	    int ok = confirmar("¿Desea eliminar el registro?");
	    if (ok == 0) {
	        if (dao.eliminarMaterial(codigo)) {
	            mensaje("Material eliminado correctamente.");
	            listar();
	            cargarCodigosMaterial();
	            limpiarCampos();
	        } else {
	            mensaje("Error al eliminar el material.");
	        }
	    }
	}
		
	void limpiarCampos() {
		txtNombreMaterial.setText("");
		txtTipoMaterial.setText("");
		txtColor.setText("");
		txtProveedor.setText("");
		txtCantidad.setText("");
		txtUnidadMedida.setText("");
		txtFecha.setText("");
		txtHora.setText("");
	}
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	String leerCodigo() {
		return (String) cmbCodigo.getSelectedItem();
	}
	String leerNombreMaterial() {
		return txtNombreMaterial.getText().trim();
	}
	String leerTipoMaterial() {
		return txtTipoMaterial.getText().trim();
	}
	String leerColor() {
		return txtColor.getText().trim();
	}
	String leerProveedor() {
		return txtProveedor.getText().trim();
	}
	int leerCantidad() {
		return Integer.parseInt(txtCantidad.getText().trim());
	}
	String leerCantidadMedida() {
		return txtUnidadMedida.getText().trim();
	}
	String leerFecha() {
		return txtFecha.getText().trim();
	}
	String leerHora() {
		return txtHora.getText().trim();
	}
	

	//  Métodos que retornan valor (con parámetros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	double ajustar(double numero) {
		return (int)(numero * 10) / 10.0;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}