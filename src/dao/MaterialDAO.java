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
	
	
	public String generarNuevoCodigoMaterial() {
	    String nuevoCodigo = "MAT011"; // valor inicial por defecto
	    String sql = "SELECT TOP 1 CodMat FROM dbo.COMP_MATERIALES WHERE CodMat LIKE 'MAT%' ORDER BY CodMat DESC";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            String ultimo = rs.getString("CodMat"); // ejemplo: MAT025
	            int numero = Integer.parseInt(ultimo.substring(3)); // 25
	            numero++; // 26
	            nuevoCodigo = String.format("MAT%03d", numero); // MAT026
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return nuevoCodigo;
	}


	public List<String> obtenerProveedoresUnicos() {
	    List<String> proveedores = new ArrayList<>();
	    String sql = "SELECT DISTINCT ProvMat FROM dbo.COMP_MATERIALES ORDER BY ProvMat";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            proveedores.add(rs.getString("ProvMat"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return proveedores;
	}


}
