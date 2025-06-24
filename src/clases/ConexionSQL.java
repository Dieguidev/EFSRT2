package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=MATERIALESTextil;encrypt=true;trustServerCertificate=true;";
    //String user = "tu_usuario";
    private static final String USER = "sa";
    private static final String PASSWORD = "sql"; 

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();

            return null;
        }
    }
}