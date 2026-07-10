package com.cvc.memory.simulator;

public class MemoryBlock {
    private int start;
    private int size;
    private boolean free;
    private String processId;

    public MemoryBlock(int start, int size) {
        this.start = start;
        this.size = size;
        this.free = true;
        this.processId = null;
    }

    public int getStart() { return start; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public boolean isFree() { return free; }
    public void setFree(boolean free) { this.free = free; }
    public String getProcessId() { return processId; }
    public void setProcessId(String processId) { this.processId = processId; }

    @Override
    public String toString() {
        return "[" + start + " - " + (start + size - 1) + "] " +
                (free ? "LIBRE" : "OCUPADO (" + processId + ")") +
                " | tamaño=" + size + "KB";
    }
}
