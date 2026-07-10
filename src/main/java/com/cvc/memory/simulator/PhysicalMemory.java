package com.cvc.memory.simulator;

import java.util.ArrayList;
import java.util.List;

public class PhysicalMemory {
    private int numFrames;
    private int frameSize;
    private boolean[] freeFrames;
    private List<Integer> allocatedFrames;

    public PhysicalMemory(int numFrames, int frameSize) {
        this.numFrames = numFrames;
        this.frameSize = frameSize;
        this.freeFrames = new boolean[numFrames];
        this.allocatedFrames = new ArrayList<>();
        for (int i = 0; i < numFrames; i++) {
            freeFrames[i] = true;
        }
    }

    public int getNumFrames() { return numFrames; }
    public int getFrameSize() { return frameSize; }

    // Asignar un marco libre
    public int allocateFrame() {
        for (int i = 0; i < numFrames; i++) {
            if (freeFrames[i]) {
                freeFrames[i] = false;
                allocatedFrames.add(i);
                return i;
            }
        }
        return -1; // No hay marcos libres
    }

    // Liberar un marco
    public void freeFrame(int frame) {
        if (frame >= 0 && frame < numFrames) {
            freeFrames[frame] = true;
            allocatedFrames.remove(Integer.valueOf(frame));
        }
    }

    public boolean isFrameFree(int frame) {
        return freeFrames[frame];
    }

    public int getFreeFramesCount() {
        int count = 0;
        for (int i = 0; i < numFrames; i++) {
            if (freeFrames[i]) count++;
        }
        return count;
    }

    public void printMemory() {
        System.out.println("--- MEMORIA FÍSICA ---");
        System.out.println("Marcos totales: " + numFrames);
        System.out.println("Tamaño de marco: " + frameSize + " KB");
        System.out.println("Marcos libres: " + getFreeFramesCount());
        System.out.println("Marcos ocupados: " + allocatedFrames.size());

        System.out.print("Estado: ");
        for (int i = 0; i < numFrames; i++) {
            System.out.print(freeFrames[i] ? "[L]" : "[O]");
        }
        System.out.println("\n");
    }
}

