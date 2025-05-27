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

    @Override
    public boolean findClientById(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }
}
