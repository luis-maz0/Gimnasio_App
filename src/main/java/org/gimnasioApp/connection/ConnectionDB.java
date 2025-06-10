package org.gimnasioApp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionDB {
    public static Connection getConnection(){
        Connection connection = null;

        Dotenv dotenv = Dotenv.load();
        String dbName = dotenv.get("DB_NAME");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASS");
        String baseUrl = dotenv.get("DB_URL");
        String url = baseUrl + "/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pass);

        }catch (Exception e){
            System.out.println("ERROR en la conexion a la base de datos " + e);
        }
        return connection;
    }

    public static void main(String[] args) {
        //Test de conexion
        var conexion = ConnectionDB.getConnection();
        if( conexion != null ){
            System.out.println("Conexion exitosa " + conexion);
        }else{
            System.out.println("Conexion no establecida");
        }
    }

}
