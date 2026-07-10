package com.cvc.memory.simulator;

import java.util.Scanner;

public class PagingSimulator {
    private PageTable pageTable;
    private PhysicalMemory physicalMemory;
    private int pageSize;
    private int numPages;
    private int numFrames;

    public PagingSimulator(int pageSize, int numPages, int numFrames) {
        this.pageSize = pageSize;
        this.numPages = numPages;
        this.numFrames = numFrames;
        this.pageTable = new PageTable(numPages);
        this.physicalMemory = new PhysicalMemory(numFrames, pageSize);
    }

    // Cargar una página en memoria (simular)
    public void loadPage(int pageNumber) {
        if (pageNumber < 0 || pageNumber >= numPages) {
            System.out.println("Error: Página inválida");
            return;
        }

        PageTableEntry entry = pageTable.getEntry(pageNumber);
        if (entry.isPresent()) {
            System.out.println("La página " + pageNumber + " ya está cargada en el marco " + entry.getFrame());
            return;
        }

        int frame = physicalMemory.allocateFrame();
        if (frame == -1) {
            System.out.println("Error: No hay marcos disponibles en memoria física");
            return;
        }

        pageTable.setFrame(pageNumber, frame);
        System.out.println("Página " + pageNumber + " cargada en el marco " + frame);
    }

    // Traducir dirección virtual a física
    public int translate(int virtualAddress) {
        int pageNumber = virtualAddress / pageSize;
        int offset = virtualAddress % pageSize;

        System.out.println("Dirección virtual: " + virtualAddress);
        System.out.println("  Número de página: " + pageNumber);
        System.out.println("  Offset: " + offset);

        if (pageNumber >= numPages) {
            System.out.println("Error: Número de página fuera de rango");
            return -1;
        }

        PageTableEntry entry = pageTable.getEntry(pageNumber);
        if (!entry.isPresent()) {
            System.out.println("Error: Fallo de página - La página " + pageNumber + " no está en memoria");
            return -1;
        }

        int physicalAddress = (entry.getFrame() * pageSize) + offset;
        System.out.println("  Marco: " + entry.getFrame());
        System.out.println("  Dirección física: " + physicalAddress);
        return physicalAddress;
    }

    // Mostrar estado actual
    public void printState() {
        System.out.println("=== ESTADO DEL SISTEMA DE PAGINACIÓN ===");
        System.out.println("Tamaño de página: " + pageSize + " KB");
        System.out.println("Número de páginas: " + numPages);
        System.out.println("Número de marcos: " + numFrames);
        System.out.println("Memoria virtual total: " + (pageSize * numPages) + " KB");
        System.out.println("Memoria física total: " + (pageSize * numFrames) + " KB");
        System.out.println();

        pageTable.printTable();
        physicalMemory.printMemory();
    }

    // Menú interactivo para paginación
    public static void runPagingMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SIMULADOR DE PAGINACIÓN ===");
        System.out.print("Tamaño de página (KB): ");
        int pageSize = scanner.nextInt();

        System.out.print("Número de páginas virtuales: ");
        int numPages = scanner.nextInt();

        System.out.print("Número de marcos físicos: ");
        int numFrames = scanner.nextInt();

        PagingSimulator simulator = new PagingSimulator(pageSize, numPages, numFrames);

        int opcion;
        do {
            System.out.println("\n1. Cargar página en memoria");
            System.out.println("2. Traducir dirección virtual");
            System.out.println("3. Mostrar estado");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Número de página a cargar: ");
                    int page = scanner.nextInt();
                    simulator.loadPage(page);
                    break;

                case 2:
                    System.out.print("Dirección virtual: ");
                    int va = scanner.nextInt();
                    simulator.translate(va);
                    break;

                case 3:
                    simulator.printState();
                    break;

                case 4:
                    System.out.println("Saliendo de paginación...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        runPagingMenu();
    }
}
