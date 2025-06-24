package clases;


import java.sql.*;
import java.util.ArrayList;

public class MaterialDAO {

    public ArrayList<Material> listarMateriales() {
        ArrayList<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM COMP_MATERIALES";

        try (Connection cn = ConexionSQL.conectar();
             Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material m = new Material(
                    rs.getString("CodMat"),
                    rs.getString("NomMat"),
                    rs.getString("TipMat"),
                    rs.getString("Color"),
                    rs.getString("ProvMat"),
                    rs.getInt("CantMat"),
                    rs.getString("UnidMed"),
                    rs.getDate("Fecha").toString(),
                    rs.getTime("Hora").toString()
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar materiales: " + e.getMessage());
        }
        return lista;
    }
}
