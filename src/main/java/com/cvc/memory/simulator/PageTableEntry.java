package com.cvc.memory.simulator;

public class PageTableEntry {
    private int frame;          // marco físico donde está la página (-1 si no está cargada)
    private boolean present;    // true si la página está en memoria física

    public PageTableEntry() {
        this.frame = -1;
        this.present = false;
    }

    public int getFrame() { return frame; }
    public void setFrame(int frame) { this.frame = frame; }
    public boolean isPresent() { return present; }
    public void setPresent(boolean present) { this.present = present; }

    @Override
    public String toString() {
        if (present) {
            return "Marco " + frame + " (presente)";
        } else {
            return "No presente (fallo de página)";
        }
    }
}
