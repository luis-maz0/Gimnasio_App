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

        //Agregar cliente
        var nuevoCliente = new Client("Raul","Dominguez",300 );
        var agregado = cliente.addClient(nuevoCliente);
        if(agregado){
            System.out.println("Cliente agregado exitosamente " + nuevoCliente.toString());
        }else{
            System.out.println("No se pudo agregar cliente");
        }
        //Listado de clientes
        System.out.println("cliente.listClients() = " + cliente.listClients());
    }
}
