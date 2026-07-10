package com.cvc.memory.simulator;

import java.util.Scanner;

public class MainSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========================================");
            System.out.println("   SIMULADOR DE ADMINISTRACIÓN DE MEMORIA");
            System.out.println("========================================");
            System.out.println("1. Asignación de memoria (First/Best/Worst Fit)");
            System.out.println("2. Traducción de direcciones (Paginación)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    MemorySimulator.runMemorySimulator();
                    break;
                case 2:
                    PagingSimulator.runPagingMenu();
                    break;
                case 3:
                    System.out.println("Saliendo del simulador...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 3);

        scanner.close();
    }
}