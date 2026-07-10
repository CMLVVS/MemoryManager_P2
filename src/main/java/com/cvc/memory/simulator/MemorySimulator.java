package com.cvc.memory.simulator;

import java.util.Scanner;

public class MemorySimulator {
    private static MemoryManager manager;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SIMULADOR DE ASIGNACIÓN DE MEMORIA ===");
        System.out.print("Ingrese el tamaño total de memoria (KB): ");
        int total = scanner.nextInt();
        scanner.nextLine();
        manager = new MemoryManager(total);

        int opcion;
        do {
            System.out.println("\n1. Crear proceso (First Fit)");
            System.out.println("2. Crear proceso (Best Fit)");
            System.out.println("3. Crear proceso (Worst Fit)");
            System.out.println("4. Liberar proceso");
            System.out.println("5. Mostrar estado de memoria");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearProceso("First Fit");
                    break;
                case 2:
                    crearProceso("Best Fit");
                    break;
                case 3:
                    crearProceso("Worst Fit");
                    break;
                case 4:
                    liberarProceso();
                    break;
                case 5:
                    manager.printMemory();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 6);
    }

    private static void crearProceso(String algoritmo) {
        System.out.print("ID del proceso: ");
        String id = scanner.nextLine();
        System.out.print("Tamaño requerido (KB): ");
        int size = scanner.nextInt();
        scanner.nextLine();

        Process p = new Process(id, size);
        boolean exito = false;

        switch (algoritmo) {
            case "First Fit":
                exito = manager.firstFit(p);
                break;
            case "Best Fit":
                exito = manager.bestFit(p);
                break;
            case "Worst Fit":
                exito = manager.worstFit(p);
                break;
        }

        if (exito) {
            System.out.println("Proceso " + id + " asignado con " + algoritmo);
        } else {
            System.out.println("No hay espacio suficiente para " + id);
        }
        manager.printMemory();
    }

    private static void liberarProceso() {
        System.out.print("ID del proceso a liberar: ");
        String id = scanner.nextLine();
        if (manager.deallocate(id)) {
            System.out.println("Proceso " + id + " liberado");
        } else {
            System.out.println("Proceso no encontrado");
        }
        manager.printMemory();
    }
}
