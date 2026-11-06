package Arboles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinarioBusqueda inventario = new ArbolBinarioBusqueda();
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE CONTROL DE INVENTARIO (ABB) ===");
            System.out.println("a) Registrar / Insertar nuevo producto");
            System.out.println("b) Buscar producto por nombre");
            System.out.println("c) Dar de baja / Eliminar producto");
            System.out.println("d) Mostrar inventario completo (InOrder)");
            System.out.println("e) Salir");
            System.out.print("Elige una opción: ");

            String entrada = scanner.nextLine().trim().toLowerCase();

            switch (entrada) {
                case "a":
                case "1":
                    System.out.print("Ingresa el nombre del producto: ");
                    String nombreReg = scanner.nextLine();
                    System.out.print("Ingresa la cantidad en stock: ");
                    int stockReg = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingresa el precio unitario: ");
                    double precioReg = Double.parseDouble(scanner.nextLine());
                    inventario.insertar(nombreReg, stockReg, precioReg);
                    break;

                case "b":
                case "2":
                    System.out.print("Ingresa el nombre del producto a buscar: ");
                    String nombreBus = scanner.nextLine();
                    Producto encontrado = inventario.buscar(nombreBus);
                    if (encontrado != null) {
                        System.out.println("¡Producto encontrado!");
                        System.out.println("-> Nombre: " + encontrado.nombre + " | Stock: " + encontrado.stock + " | Precio: $" + encontrado.precio);
                    } else {
                        System.out.println("El producto no se encuentra registrado en el inventario.");
                    }
                    break;

                case "c":
                case "3":
                    System.out.print("Ingresa el nombre del producto a eliminar: ");
                    String nombreEli = scanner.nextLine();
                    inventario.eliminar(nombreEli);
                    break;

                case "d":
                case "4":
                    inventario.mostrarInOrder();
                    break;

                case "e":
                case "5":
                    System.out.println("Saliendo del sistema de inventario. ¡Hasta luego!");
                    opcion = 5;
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
