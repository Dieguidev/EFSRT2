package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arreglo.Materiales;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnMantenimiento;
	private JMenu mnRegistro;
	private JMenu mnConsulta;
	private JMenuItem mntmEliminar;
	private JSeparator separator;
	private JMenuItem mntmRegistroMaterial;
	private JMenuItem mntmModificarMaterial;
	private JMenuItem mntmConsultaPorTipo;
	private JMenu mnReportes;
	private JMenuItem mntmReporteStockMate;
	private JMenuItem mntmReporteStockProve;
	private JMenuItem mntmReporteBajoStock;
	private JMenuItem mntmConsultaUnidMed;
	private JSeparator separator_6;
	private JMenuItem mntmConsultaColor;
	private JSeparator separator_7;
	private JMenuItem mntmReporteGeneralMate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("SRM - LINO Y SEDA SAC.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 618);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		mntmRegistroMaterial = new JMenuItem("Registrar Material");
		mntmRegistroMaterial.addActionListener(new MntmMatriculaActionListener());
		mnRegistro.add(mntmRegistroMaterial);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		
		mntmEliminar = new JMenuItem("Eliminar Material");
		mntmEliminar.addActionListener(new MtnEliminar());
		
		separator = new JSeparator();
		mnMantenimiento.add(separator);
		mnMantenimiento.add(mntmEliminar);
		
		JSeparator separator_1 = new JSeparator();
		mnMantenimiento.add(separator_1);
		
		mntmModificarMaterial = new JMenuItem("Modificar Material");
		mnMantenimiento.add(mntmModificarMaterial);
		mntmModificarMaterial.addActionListener(new MntmModificarMaterial());
		
		mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		mntmConsultaPorTipo = new JMenuItem("Consulta por Tipo");
		mnConsulta.add(mntmConsultaPorTipo);
		mntmConsultaPorTipo.addActionListener(new MntmConsultaPorTipo());
		
		JSeparator separator_4 = new JSeparator();
		mnConsulta.add(separator_4);
		
		JMenuItem mntmConsultaProveedor = new JMenuItem("Consulta por Proveedor");
		mnConsulta.add(mntmConsultaProveedor);
		mntmConsultaProveedor.addActionListener(new MntmConsultaPorProveedor());
		
		JSeparator separator_5 = new JSeparator();
		mnConsulta.add(separator_5);
		
		mntmConsultaUnidMed = new JMenuItem("Consulta por Unidad de Medida");
		mnConsulta.add(mntmConsultaUnidMed);
		mntmConsultaUnidMed.addActionListener(new MntmConsultaPorUnidMedida());
		
		separator_6 = new JSeparator();
		mnConsulta.add(separator_6);
		
		mntmConsultaColor = new JMenuItem("Consulta por Color");
		mnConsulta.add(mntmConsultaColor);
		mntmConsultaColor.addActionListener(new MntmConsultaPorColor());
		
		mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mntmReporteStockMate = new JMenuItem("Stock Total según Tipo Material");
		mnReportes.add(mntmReporteStockMate);
		mntmReporteStockMate.addActionListener(new MntmReporteSTM());
		
		JSeparator separator_2 = new JSeparator();
		mnReportes.add(separator_2);
		
		mntmReporteStockProve = new JMenuItem("Stock total según Proveedor");
		mnReportes.add(mntmReporteStockProve);
		mntmReporteStockProve.addActionListener(new MntmReporteSTP());
		
		JSeparator separator_3 = new JSeparator();
		mnReportes.add(separator_3);
		
		mntmReporteBajoStock = new JMenuItem("Reporte de bajo Stock");
		mnReportes.add(mntmReporteBajoStock);
		mntmReporteBajoStock.addActionListener(new MntmReporteBS());
		
		separator_7 = new JSeparator();
		mnReportes.add(separator_7);
		
		mntmReporteGeneralMate = new JMenuItem("Reporte general de Materiales");
		mnReportes.add(mntmReporteGeneralMate);
		mntmReporteGeneralMate.addActionListener(new MntmReporteRG());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public class DatosCompartidos {
	    public static Materiales materiales = new Materiales();
	}
	
	private class MtnEliminar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgEliminarMaterial mantenimientoCurso = new DlgEliminarMaterial();
			mantenimientoCurso.setVisible(true);
			mantenimientoCurso.setModal(true);
			mantenimientoCurso.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmMatriculaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgRegistroMateriales dlgRegistroMateriales = new DlgRegistroMateriales();
			dlgRegistroMateriales.setModal(true);
			dlgRegistroMateriales.setVisible(true);
			dlgRegistroMateriales.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmModificarMaterial implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgModificarMaterial dlgModificaMaterial = new DlgModificarMaterial();
			dlgModificaMaterial.setModal(true);
			dlgModificaMaterial.setVisible(true);
			dlgModificaMaterial.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmConsultaPorTipo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgConsultaPorTipo dlgConsultaPorTipo = new DlgConsultaPorTipo();
			dlgConsultaPorTipo.setModal(true);
			dlgConsultaPorTipo.setVisible(true);
			dlgConsultaPorTipo.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmConsultaPorProveedor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgConsultaPorProveedor dlgConsultaProveedor = new DlgConsultaPorProveedor();
			dlgConsultaProveedor.setModal(true);
			dlgConsultaProveedor.setVisible(true);
			dlgConsultaProveedor.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmConsultaPorUnidMedida implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgConsultaPorUnidMedida dlgConsultaPorUnidMedida = new DlgConsultaPorUnidMedida();
			dlgConsultaPorUnidMedida.setModal(true);
			dlgConsultaPorUnidMedida.setVisible(true);
			dlgConsultaPorUnidMedida.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmConsultaPorColor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgConsultaPorColor dlgConsultaPorcolor = new DlgConsultaPorColor();
			dlgConsultaPorcolor.setModal(true);
			dlgConsultaPorcolor.setVisible(true);
			dlgConsultaPorcolor.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmReporteSTM implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgReporteSTM dlgReporteStm = new DlgReporteSTM();
			dlgReporteStm.setModal(true);
			dlgReporteStm.setVisible(true);
			dlgReporteStm.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmReporteSTP implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgReporteSTP dlgReporteStp = new DlgReporteSTP();
			dlgReporteStp.setModal(true);
			dlgReporteStp.setVisible(true);
			dlgReporteStp.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmReporteBS implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgReporteBS dlgReporteBs = new DlgReporteBS();
			dlgReporteBs.setModal(true);
			dlgReporteBs.setVisible(true);
			dlgReporteBs.setLocationRelativeTo(contentPane);
		}
	}
	private class MntmReporteRG implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DlgReporteRG dlgReporteRg = new DlgReporteRG();
			dlgReporteRg.setModal(true);
			dlgReporteRg.setVisible(true);
			dlgReporteRg.setLocationRelativeTo(contentPane);
		}
	}
}















