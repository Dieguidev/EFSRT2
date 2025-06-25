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
	            int numero = Integer.parseInt(ultimo.substring(3).trim()); // 25
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

	
	public List<String> obtenerColoresUnicos() {
	    List<String> colores = new ArrayList<>();
	    String sql = "SELECT DISTINCT Color FROM dbo.COMP_MATERIALES ORDER BY Color";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            colores.add(rs.getString("Color"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return colores;
	}
	
	
	public List<Material> listarPorColor(String color) {
	    List<Material> lista = new ArrayList<>();
	    String sql = "SELECT CodMat, NomMat, TipMat,Color, ProvMat, UnidMed, CantMat, Fecha, Hora FROM dbo.COMP_MATERIALES WHERE Color = ?";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, color);
	        ResultSet rs = ps.executeQuery();

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
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	
	public List<String> obtenerUnidadesUnicas() {
	    List<String> unidades = new ArrayList<>();
	    String sql = "SELECT DISTINCT UnidMed FROM dbo.COMP_MATERIALES ORDER BY UnidMed";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            unidades.add(rs.getString("UnidMed"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return unidades;
	}


	
	public List<Material> listarPorUnidad(String unidad) {
	    List<Material> lista = new ArrayList<>();
	    String sql = "SELECT CodMat, NomMat, TipMat, ProvMat, CantMat, Color, Fecha, Hora, UnidMed FROM dbo.COMP_MATERIALES WHERE UnidMed = ?";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, unidad);
	        ResultSet rs = ps.executeQuery();

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
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	
	public boolean insertarMaterial(Material m) {
	    String sql = "INSERT INTO dbo.COMP_MATERIALES (CodMat, NomMat, TipMat, Color, UnidMed, CantMat, ProvMat, Fecha, Hora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, m.getCodigoMaterial());
	        ps.setString(2, m.getNombreMaterial());
	        ps.setString(3, m.getTipoMaterial());
	        ps.setString(4, m.getColor());
	        ps.setString(5, m.getUnidadMedida());
	        ps.setInt(6, m.getCantidad());
	        ps.setString(7, m.getProveedor());
	        ps.setString(8, m.getFecha());
	        ps.setString(9, m.getHora());

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	public List<String> obtenerTiposMaterial() {
	    List<String> tipos = new ArrayList<>();
	    String sql = "SELECT DISTINCT TipMat FROM dbo.COMP_MATERIALES ORDER BY TipMat";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            tipos.add(rs.getString("TipMat"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tipos;
	}


	public List<String> obtenerCodigosMaterial() {
	    List<String> codigos = new ArrayList<>();
	    String sql = "SELECT CodMat FROM dbo.COMP_MATERIALES ORDER BY CodMat";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            codigos.add(rs.getString("CodMat"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return codigos;
	}

	
	public Material buscarMaterial(String codigo) {
	    Material m = null;
	    String sql = "SELECT * FROM dbo.COMP_MATERIALES WHERE CodMat = ?";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, codigo);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            m = new Material(
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
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return m;
	}
	
	public boolean actualizarMaterial(Material m) {
	    String sql = "UPDATE dbo.COMP_MATERIALES SET NomMat=?, TipMat=?, Color=?, ProvMat=?, UnidMed=?, CantMat=? WHERE CodMat=?";

	    try (Connection con = ConexionSQL.conectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, m.getNombreMaterial());
	        ps.setString(2, m.getTipoMaterial());
	        ps.setString(3, m.getColor());
	        ps.setString(4, m.getProveedor());
	        ps.setString(5, m.getUnidadMedida());
	        ps.setInt(6, m.getCantidad());
	        ps.setString(7, m.getCodigoMaterial());

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
