package com.cvc.memory.simulator;

public class Process {
    private String id;
    private int size;

    public Process(String id, int size) {
        this.id = id;
        this.size = size;
    }

    public String getId() { return id; }
    public int getSize() { return size; }
}
m
