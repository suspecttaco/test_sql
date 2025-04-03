import java.sql.*;

import BDConnection.Mysql;

public class App {
    static Connection bd = Mysql.ConectarBD("bd_inventario", "root", "Salsa1508");

    public static void main(String[] args) throws Exception {

        insertarDato("carro",5);
        insertarDato("guiso",5);

        consultarDato("guiso");
        consultarDato("carro");

        System.in.read();

        actualizarDato("guiso", 4);
        eliminarDato("carro");

        consultarDato("guiso");

        Mysql.Desconexion(bd);
    }

    public static void insertarDato(String nom, int cantidad) {

        try {
            String query = "insert into producto (nombre,cantidad) values ('" + nom + "','" + cantidad + "')";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void actualizarDato(String nom, int cantidad){
        try {
            String query = "update producto set cantidad = '" + cantidad + "' where nombre = '" + nom + "'";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void eliminarDato(String nom){
        try {
            String query = "delete from producto where nombre = '" + nom + "'";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void consultarDato(String nom){
        try {
            String query = "select * from producto where nombre = '" + nom + "'";
            Statement st = bd.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            resultSet.next();
            System.out.println("id: " + resultSet.getString("id_producto") +" nombre: " + resultSet.getString("nombre") + " cantidad: " + resultSet.getString("cantidad"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
