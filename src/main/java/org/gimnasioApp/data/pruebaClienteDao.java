package org.gimnasioApp.data;

public class pruebaClienteDao {
    public static void main(String[] args) {
        IClientDao cliente = new ClientDao();
        var clientes = cliente.listClients();
        clientes.forEach(System.out::println);
    }
}
