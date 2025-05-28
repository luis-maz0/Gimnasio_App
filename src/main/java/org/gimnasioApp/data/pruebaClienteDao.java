package org.gimnasioApp.data;

import org.gimnasioApp.domain.Client;

public class pruebaClienteDao {
    public static void main(String[] args) {
        IClientDao cliente = new ClientDao();
        //Listado de clientes
        var clientes = cliente.listClients();
        clientes.forEach(System.out::println);

        //Busqueda por ID
        var cliente1 = new Client(5);
        var clienteEncontrado = cliente.findClientById(cliente1);
        if(clienteEncontrado){
            System.out.println("Cliente encontrado: " + cliente1);
        }else {
            System.out.println("No se encontro cliente");
        }
    }
}
