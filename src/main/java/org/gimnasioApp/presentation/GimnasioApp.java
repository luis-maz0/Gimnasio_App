package org.gimnasioApp.presentation;

import org.gimnasioApp.data.ClientDao;
import org.gimnasioApp.data.IClientDao;
import org.gimnasioApp.domain.Client;

import java.util.Scanner;

public class GimnasioApp {
    public static void main(String[] args) {
        gimnasioApp();
    }

    private static void mostrarTextoMenu(){
        System.out.println(""" 
                        GimnasioApp
                ----------------------------
                    1. Listar clientes
                    2. Buscar cliente
                    3. Agregar cliente
                    4. Modificar cliente
                    5. Eliminar Cliente
                    6. Salir
                -----------------------------
                    Eligir una opcion
                """);
    }

    private static void gimnasioApp(){
        int opcionELegida;
        var salir = false;
        var sc = new Scanner(System.in);
        IClientDao clienteDAO = new ClientDao();

        while (!salir){
            try {
                mostrarTextoMenu();
                opcionELegida = sc.nextInt();
                salir = ejecutarOpciones(sc, opcionELegida ,clienteDAO);
            } catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
        }
    }
    //TODO: REFACTOR
    private static boolean ejecutarOpciones(Scanner sc, int opcionELegida ,IClientDao clienteDao){
        var salir = false;
        String mensaje;

        switch (opcionELegida){
            case 1:
                System.out.println("*** Lista cliente ***");
                var clientes = clienteDao.listClients();
                clientes.forEach(client -> System.out.println(client.toString()));
                break;
            case 2:
                System.out.println("*** Introducir ID del cliente a buscar  ***");
                var idCliente = sc.nextInt();
                var cliente = new Client(idCliente);
                var encontrado = clienteDao.findClientById(cliente);
                mensaje = encontrado ? "Cliente encontrado " + cliente: "Cliente NO encontrado " + cliente;
                System.out.println(mensaje);
                break;
            case 3:
                System.out.println("*** Agregar cliente ***");
                System.out.println("Ingresar nombre: ");
                sc.nextLine();
                var nombre = sc.nextLine();
                System.out.println("Ingresar apellido: ");
                var apellido = sc.nextLine();
                System.out.println("Ingresar membresia: ");
                var membresia = sc.nextInt();
                var clienteNuevo = new Client(nombre,apellido,membresia);
                var agregado = clienteDao.addClient(clienteNuevo);
                mensaje  = agregado ? "Cliente agregado " + clienteNuevo: "Cliente NO agregado " + clienteNuevo;
                System.out.println(mensaje);
                break;
            case 4:
                System.out.println("*** Modificar cliente ***");
                System.out.println("ID cliente a modificar: ");
                var id = sc.nextInt();
                sc.nextLine();
                System.out.println("Ingresar nombre: ");
                nombre = sc.nextLine();
                System.out.println("Ingresar apellido: ");
                apellido = sc.nextLine();
                System.out.println("Ingresar membresia: ");
                membresia = sc.nextInt();
                var clienteModificado = new Client(id,nombre,apellido,membresia);
                var modificado = clienteDao.updateClient(clienteModificado);
                mensaje  = modificado ? "Cliente modificado " + clienteModificado: "Cliente NO agregado " + clienteModificado;
                System.out.println(mensaje);
                break;
            case 5:
                System.out.println("*** Eliminar Cliente ***");
                System.out.println("ID cliente a Eliminar: ");
                id = sc.nextInt();
                var clienteEliminar = new Client(id);
                var eliminado = clienteDao.deleteClient(clienteEliminar);
                mensaje  = eliminado ? "Cliente modificado " + clienteEliminar: "Cliente NO agregado " + clienteEliminar;
                System.out.println(mensaje);
                break;
            case 6:
                salir = true;
                System.out.println("Adios");
                break;
            default:
                System.out.println("Opcion invalidad, intentelo nuevamente");
        }
        return salir;
    }
}
