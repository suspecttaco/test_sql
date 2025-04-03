import java.sql.*;

import BDConnection.Mysql;

public class App {
    //Conexion con la bd local con credenciales
    static Connection bd = Mysql.ConectarBD("bd_inventario", "root", "Salsa1508");

    public static void main(String[] args) throws Exception {
        //Inserccion de filas
        insertarDato("carro",5);
        insertarDato("guiso",5);

        //Insertar con procedimineto
        insertWithProcedure("carro",5);
        insertWithProcedure("guiso",5);
        //Consulta de filas
        consultarDato("guiso");
        consultarDato("carro");
        //Pausa para revisar
        System.in.read();
        //Actualizacion de filas
        actualizarDato("guiso", 4);
        //Eliminacion de filas
        eliminarDato("carro");

        consultarDato("guiso");

        Mysql.Desconexion(bd);
    }

    //Funcion para insertar filas
    public static void insertarDato(String nom, int cantidad) {

        try {
            //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades
            String query = "insert into producto (nombre,cantidad) values ('" + nom + "','" + cantidad + "')";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Funcion para actualizar filas
    public static void actualizarDato(String nom, int cantidad){
        try {
            //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades
            String query = "update producto set cantidad = '" + cantidad + "' where nombre = '" + nom + "'";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Funcion para eliminar filas
    public static void eliminarDato(String nom){
        try {
            //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades, igual "nombre" es la columna dentro de mi tabla
            String query = "delete from producto where nombre = '" + nom + "'";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //Funcion para consultar filas filas
    public static void consultarDato(String nom){
        try {
            //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades, igual las columnas.
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

    public static void insertWithProcedure(String nom, int cant){
        try {
            //"producto" es mi tabla dentro de mi bd, cambienla segun sus necesidades
            String query = "call insertarProductos('" + nom + "',"+cant+")";
            Statement st = bd.createStatement();
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Success!! rows affected: "+ rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
