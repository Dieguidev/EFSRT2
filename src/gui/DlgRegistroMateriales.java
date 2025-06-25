package gui;



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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DlgRegistroMateriales extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblCodMaterial;
	private JLabel lblNombreMaterial;
	private JLabel lblTipoMaterial;
	private JLabel lblColor;
	private JLabel lblHora;
	private JTextField txtCodigoMaterial;
	private JTextField txtNombreMaterial;
	private JTextField txtColor;
	private JTextField txtProveedor;
	private JButton btnAdicionar;
	private JScrollPane scrollPane;
	private JTable tblPersona;
	private DefaultTableModel modelo;


	
	//  Constantes para los tipos de operaciones
	public final static int ADICIONAR = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgRegistroMateriales dialog = new DlgRegistroMateriales();
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
	public DlgRegistroMateriales() {
		setResizable(false);
		setTitle("Registro de Material");
		setBounds(100, 100, 874, 610);
		getContentPane().setLayout(null);
		
		lblCodMaterial = new JLabel("Codigo de Material:");
		lblCodMaterial.setBounds(10, 10, 126, 23);
		getContentPane().add(lblCodMaterial);
		
		lblNombreMaterial = new JLabel("Nombre de material");
		lblNombreMaterial.setBounds(10, 35, 126, 23);
		getContentPane().add(lblNombreMaterial);
	
		lblTipoMaterial = new JLabel("Tipo de material:");
		lblTipoMaterial.setBounds(10, 60, 110, 23);
		getContentPane().add(lblTipoMaterial);
		
		lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 85, 70, 23);
		getContentPane().add(lblColor);
		
		lblHora = new JLabel("Proveedor:");
		lblHora.setBounds(10, 110, 110, 23);
		getContentPane().add(lblHora);
		
		txtCodigoMaterial = new JTextField();
		txtCodigoMaterial.setEditable(false);
		txtCodigoMaterial.setBounds(146, 10, 86, 23);
		getContentPane().add(txtCodigoMaterial);
		txtCodigoMaterial.setColumns(10);

		txtNombreMaterial = new JTextField();
		txtNombreMaterial.setBounds(146, 35, 251, 23);
		getContentPane().add(txtNombreMaterial);
		txtNombreMaterial.setColumns(10);
		
		txtColor = new JTextField();
		txtColor.setBounds(146, 85, 101, 23);
		getContentPane().add(txtColor);
		txtColor.setColumns(10);

		txtProveedor = new JTextField();
		txtProveedor.setBounds(146, 110, 101, 23);
		getContentPane().add(txtProveedor);
		txtProveedor.setColumns(10);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(645, 10, 120, 48);
		getContentPane().add(btnAdicionar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 271, 838, 289);
		getContentPane().add(scrollPane);
		
		tblPersona = new JTable();
		tblPersona.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblPersona);

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
		tblPersona.setModel(modelo);
		
		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setBounds(10, 133, 94, 23);
		getContentPane().add(lblCantidad);
		
		JLabel lblProveedor = new JLabel("Unidad de medida:");
		lblProveedor.setBounds(10, 160, 110, 23);
		getContentPane().add(lblProveedor);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(146, 135, 52, 23);
		getContentPane().add(txtCantidad);
		
		txtCodigoMaterial.setText(new MaterialDAO().generarNuevoCodigoMaterial());
	    
		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 184, 110, 23);
		getContentPane().add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setColumns(10);
		txtFecha.setBounds(146, 184, 86, 23);
		getContentPane().add(txtFecha);
		
		lblHora_1 = new JLabel("Hora:");
		lblHora_1.setBounds(10, 207, 110, 23);
		getContentPane().add(lblHora_1);
		
		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setColumns(10);
		txtHora.setBounds(146, 208, 86, 23);
		getContentPane().add(txtHora);
		
		cmbTipoMaterial = new JComboBox<String>();
		cmbTipoMaterial.removeAllItems();
		for (String tipo : new MaterialDAO().obtenerTiposMaterial()) {
		    cmbTipoMaterial.addItem(tipo);
		}

		cmbTipoMaterial.setBounds(146, 60, 190, 22);
		getContentPane().add(cmbTipoMaterial);
	
		cmbUnidadMedida = new JComboBox<String>();
		cmbUnidadMedida.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Unid.",
				"Kg.", 
				"Cm.", 
				"Lt."
		}));
		cmbUnidadMedida.setBounds(146, 160, 94, 22);
		cargarUnidadesDesdeBase();
		getContentPane().add(cmbUnidadMedida);
		ajustarAnchoColumnas();
		listar();	
		}
	
	//  Declaración global
	private JTextField txtCantidad;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblHora_1;
	private JTextField txtHora;
	private JComboBox<String> cmbTipoMaterial;
	private JComboBox<String> cmbUnidadMedida;
	
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
	}
	
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		if (!validarCamposObligatorios()) {
	        return;
	    }

		try { 
		    adicionarMaterial();
		    listar();
		} catch (Exception e) {
		    error("Error al adicionar material: " + e.getMessage(), txtNombreMaterial);
		}
	}
	
	void cargarUnidadesDesdeBase() {
	    cmbUnidadMedida.removeAllItems();
	    for (String unidad : new MaterialDAO().obtenerUnidadesUnicas()) {
	        cmbUnidadMedida.addItem(unidad);
	    }
	}

	//  Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblPersona.getColumnModel();
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
	    MaterialDAO dao = new MaterialDAO();
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
	
	void limpiarCampos() {
	    txtNombreMaterial.setText("");
	    txtColor.setText("");
	    txtProveedor.setText("");
	    txtCantidad.setText("");
	    txtFecha.setText(obtenerFecha());
	    txtHora.setText(obtenerHora());
	    cmbTipoMaterial.setSelectedIndex(0);
	    cmbUnidadMedida.setSelectedIndex(0);
	}

	void adicionarMaterial() {
    String codigo = leerCodigoMaterial();
    String nombreMaterial = leerNombreMaterial();
    String tipoMaterial = leerTipoMaterial();
    String color = leerColor();
    String unidadMedida = leerUnidadMedida();
    String fecha = obtenerFecha();
    String hora = obtenerHora();
    int cantidad = leerCantidad();
    String proveedor = leerProveedor();

    if (unidadInvalida(tipoMaterial, unidadMedida)) {
        JOptionPane.showMessageDialog(this, "Unidad de medida inválida según el tipo de material", "ERROR", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Material nuevoMaterial = new Material(codigo, nombreMaterial, tipoMaterial, color, unidadMedida, cantidad, proveedor, fecha, hora);
    MaterialDAO dao = new MaterialDAO();
    
    
    if (dao.insertarMaterial(nuevoMaterial)) {
        mensaje("Material registrado exitosamente.");
        limpiarCampos();
        listar();  // actualiza tabla desde base de datos
        txtCodigoMaterial.setText(new MaterialDAO().generarNuevoCodigoMaterial());  // genera nuevo código
    } else {
        error("Ocurrió un error al registrar el material.", txtNombreMaterial);
    }
	}
	
	boolean unidadInvalida(String tipo, String unidad) {
	    return (tipo.equals("Materia prima textil") && (unidad.equals("Unid.") || unidad.equals("Lt."))) ||
	           (tipo.equals("Insumos auxiliares") && (unidad.equals("Kg.") || unidad.equals("Cm."))) ||
	           (tipo.equals("Empaque y etiqueta") && (unidad.equals("Kg.") || unidad.equals("Cm.") || unidad.equals("Lt."))) ||
	           (tipo.equals("Químicos") && (unidad.equals("Kg.") || unidad.equals("Cm."))) ||
	           (tipo.equals("Herramientas") && (unidad.equals("Kg.") || unidad.equals("Cm.") || unidad.equals("Lt.")));
	}

	boolean validarCamposObligatorios() {
	    if (leerNombreMaterial().trim().isEmpty()) {
	        error("El nombre del material no puede estar vacío.", txtNombreMaterial);
	        return false;
	    }
	    if (leerProveedor().trim().isEmpty()) {
	        error("El proveedor no puede estar vacío.", txtProveedor);
	        return false;
	    }
	    if (txtCantidad.getText().trim().isEmpty()) {
	        error("La cantidad no puede estar vacía.", txtCantidad);
	        return false;
	    }
	    try {
	        Integer.parseInt(txtCantidad.getText().trim());
	    } catch (NumberFormatException e) {
	        error("Ingrese una cantidad válida (solo números enteros).", txtCantidad);
	        return false;
	    }

	    return true;
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	
	String leerCodigoMaterial() {
		return txtCodigoMaterial.getText().trim();
	}
	String leerColor() {
		return txtColor.getText();
	}
	String leerNombreMaterial() {
		return txtNombreMaterial.getText().trim();
	}
	String leerTipoMaterial() {
		return (String) cmbTipoMaterial.getSelectedItem();
	}
	String leerProveedor() {
		return txtProveedor.getText().trim();
	}
	int leerCantidad() {
	    String texto = txtCantidad.getText().trim();
	    if (texto.isEmpty()) {
	        throw new NumberFormatException("La cantidad está vacía");
	    }
	    return Integer.parseInt(texto);
	}

	String leerUnidadMedida() {
		return (String )cmbUnidadMedida.getSelectedItem();
	}
	
	String obtenerFecha() {
		LocalDateTime fecha = LocalDateTime.now();
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return fecha.format(formato);

	}
	String obtenerHora() {
		LocalDateTime hora = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaActual = hora.format(formato);
		return horaActual;
	}
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