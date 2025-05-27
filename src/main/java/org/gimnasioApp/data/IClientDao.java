package org.gimnasioApp.data;

import org.gimnasioApp.domain.Client;

import java.util.List;

public interface IClientDao {
    List<Client> listClients();
    boolean findClientById(Client client);
    boolean deleteClient(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
}

