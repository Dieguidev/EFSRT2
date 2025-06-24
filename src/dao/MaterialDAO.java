package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import clases.Material;
import clases.ConexionSQL;


public class MaterialDAO {
	public List<Material> listarMateriales() {
	    List<Material> lista = new ArrayList<>();
	    String sql = "SELECT CodMat, NomMat, TipMat, Color, ProvMat, CantMat, UnidMed, Fecha, Hora FROM dbo.COMP_MATERIALES";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Material m = new Material(
	                rs.getString("CodMat"),
	                rs.getString("NomMat"),
	                rs.getString("TipMat"),
	                rs.getString("Color"),
	                rs.getString("UnidMed"),
	                rs.getInt("CantMat"),
	                rs.getString("ProvMat"),
	                rs.getString("Fecha"),
	                rs.getString("Hora")
	            );
	            lista.add(m);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error en listarMateriales(): " + e.getMessage());
	    }

	    return lista;
	}


}
