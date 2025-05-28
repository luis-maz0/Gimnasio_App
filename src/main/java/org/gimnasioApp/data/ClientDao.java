package org.gimnasioApp.data;

import org.gimnasioApp.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.gimnasioApp.connection.ConnectionDB.getConnection;

public class ClientDao implements IClientDao{
    //TODO: En el try-catch se puede utilizar try-with-resources para simplificar el codigo.
    @Override
    public List<Client> listClients() {
        ArrayList<Client> clients = new ArrayList<Client>();
        //Preparacion para ejecutar queries SQL
        PreparedStatement ps;
        ResultSet rs;
        Connection connection = getConnection();
        var querySQL = "SELECT * FROM client ORDER BY id";
        try{
            ps = connection.prepareStatement(querySQL);
            rs = ps.executeQuery();

            while (rs.next()){
                var client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName( rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }

        }catch (Exception e){
            System.out.println("ERROR AL LISTAR CLIENTES " + e);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("ERROR AL CERRAR CONEXION " + e);
            }
        }
        return clients;
    }

    //TODO: Este metodo puede mejorar, en lugar de retornar un valor booleano, podria retornar un tipo de dato Optional<Client>.
    @Override
    public boolean findClientById(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConnection();
        var sql = "SELECT * FROM client WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            //Se rellena el parametro ?
            ps.setInt(1, client.getId());
            rs = ps.executeQuery();
            if( rs.next() ){
                client.setFirstName(rs.getString("first_name"));
                client.setLastName( rs.getString("last_name") );
                client.setMembership(rs.getInt("membership"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion " + e.getMessage() );
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO client( first_name, last_name, membership) VALUES(?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al agregar cliente " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }
}
