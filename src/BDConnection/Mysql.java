package BDConnection;

import java.sql.*;

public class Mysql {
    public static Connection ConectarBD(String bd, String user, String pass){
        Connection conexion;

        String host = "jdbc:mysql://localhost/";

        System.out.println("Conectando....");

        try {
            conexion = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Conexion exitosa!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

        return conexion;
    }

    public static void Desconexion(Connection cb){
        try {
            cb.close();
            System.out.println("Desconectado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
