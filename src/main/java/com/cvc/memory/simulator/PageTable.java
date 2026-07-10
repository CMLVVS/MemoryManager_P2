package com.cvc.memory.simulator;

public class PageTable {
    private PageTableEntry[] entries;
    private int numPages;

    public PageTable(int numPages) {
        this.numPages = numPages;
        this.entries = new PageTableEntry[numPages];
        for (int i = 0; i < numPages; i++) {
            entries[i] = new PageTableEntry();
        }
    }

    public PageTableEntry getEntry(int pageNumber) {
        if (pageNumber < 0 || pageNumber >= numPages) {
            return null;
        }
        return entries[pageNumber];
    }

    public void setFrame(int pageNumber, int frame) {
        if (pageNumber >= 0 && pageNumber < numPages) {
            entries[pageNumber].setFrame(frame);
            entries[pageNumber].setPresent(true);
        }
    }

    public int getNumPages() { return numPages; }

    public void printTable() {
        System.out.println("--- TABLA DE PÁGINAS ---");
        for (int i = 0; i < numPages; i++) {
            System.out.println("Página " + i + " -> " + entries[i]);
        }
        System.out.println();
    }
}