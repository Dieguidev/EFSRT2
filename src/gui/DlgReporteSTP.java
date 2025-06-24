package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import clases.Material;
import clases.MaterialDAO;

import java.util.*;


public class DlgReporteSTP extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblReporte;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane;
    

    public DlgReporteSTP() {
        setTitle("Reporte - Stock Total por Proveedor");
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 50, 440, 280);
        getContentPane().add(scrollPane);

        tblReporte = new JTable();
        modelo = new DefaultTableModel();
        modelo.addColumn("Proveedor");
        modelo.addColumn("Stock Total");
        tblReporte.setModel(modelo);
        scrollPane.setViewportView(tblReporte);
        
        JLabel lblReporte = new JLabel("REPORTE:");
        lblReporte.setBounds(20, 27, 59, 14);
        getContentPane().add(lblReporte);
        
        JLabel lblTitulo = new JLabel("STOCK TOTAL POR PROVEEDOR");
        lblTitulo.setBounds(20, 11, 321, 14);
        getContentPane().add(lblTitulo);

        ajustarAnchoColumnas();
        generarReporte();
    }

    private void ajustarAnchoColumnas() {
        TableColumnModel tcm = tblReporte.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(300); // Proveedor
        tcm.getColumn(1).setPreferredWidth(100); // Total
    }

    
    private void generarReporte() {
        modelo.setRowCount(0);
        MaterialDAO dao = new MaterialDAO();
        List<Material> lista = dao.listarMateriales();
        Map<String, Integer> resumen = new HashMap<>();

        for (Material m : lista) {
            String proveedor = m.getProveedor();
            int cantidad = m.getCantidad();
            resumen.put(proveedor, resumen.getOrDefault(proveedor, 0) + cantidad);
        }

        for (Map.Entry<String, Integer> entry : resumen.entrySet()) {
            modelo.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }
}