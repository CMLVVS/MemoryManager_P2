package com.cvc.memory.simulator;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {
    private List<MemoryBlock> blocks;
    private int totalMemory;

    public MemoryManager(int totalMemory) {
        this.totalMemory = totalMemory;
        this.blocks = new ArrayList<>();
        blocks.add(new MemoryBlock(0, totalMemory));
    }

    public boolean firstFit(Process p) {
        for (MemoryBlock block : blocks) {
            if (block.isFree() && block.getSize() >= p.getSize()) {
                allocate(block, p);
                return true;
            }
        }
        return false;
    }

    public boolean bestFit(Process p) {
        MemoryBlock bestBlock = null;
        int minWaste = Integer.MAX_VALUE;

        for (MemoryBlock block : blocks) {
            if (block.isFree() && block.getSize() >= p.getSize()) {
                int waste = block.getSize() - p.getSize();
                if (waste < minWaste) {
                    minWaste = waste;
                    bestBlock = block;
                }
            }
        }

        if (bestBlock != null) {
            allocate(bestBlock, p);
            return true;
        }
        return false;
    }

    public boolean worstFit(Process p) {
        MemoryBlock worstBlock = null;
        int maxWaste = -1;

        for (MemoryBlock block : blocks) {
            if (block.isFree() && block.getSize() >= p.getSize()) {
                int waste = block.getSize() - p.getSize();
                if (waste > maxWaste) {
                    maxWaste = waste;
                    worstBlock = block;
                }
            }
        }

        if (worstBlock != null) {
            allocate(worstBlock, p);
            return true;
        }
        return false;
    }

    private void allocate(MemoryBlock block, Process p) {
        int remaining = block.getSize() - p.getSize();

        if (remaining > 0) {
            block.setSize(p.getSize());
            block.setFree(false);
            block.setProcessId(p.getId());

            MemoryBlock newFreeBlock = new MemoryBlock(block.getStart() + p.getSize(), remaining);
            int index = blocks.indexOf(block);
            blocks.add(index + 1, newFreeBlock);
        } else {
            block.setFree(false);
            block.setProcessId(p.getId());
        }
    }

    public boolean deallocate(String processId) {
        for (int i = 0; i < blocks.size(); i++) {
            MemoryBlock block = blocks.get(i);
            if (!block.isFree() && block.getProcessId().equals(processId)) {
                block.setFree(true);
                block.setProcessId(null);
                mergeFreeBlocks();
                return true;
            }
        }
        return false;
    }

    private void mergeFreeBlocks() {
        List<MemoryBlock> merged = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            MemoryBlock current = blocks.get(i);
            if (current.isFree()) {
                int start = current.getStart();
                int size = current.getSize();
                while (i + 1 < blocks.size() && blocks.get(i + 1).isFree()) {
                    size += blocks.get(i + 1).getSize();
                    i++;
                }
                merged.add(new MemoryBlock(start, size));
            } else {
                merged.add(current);
            }
        }
        blocks = merged;
    }

    public int getExternalFragmentation() {
        int totalFree = 0;
        for (MemoryBlock block : blocks) {
            if (block.isFree()) {
                totalFree += block.getSize();
            }
        }
        return totalFree;
    }

    public int getInternalFragmentation() {
        return 0;
    }

    public void printMemory() {
        System.out.println("--- ESTADO DE MEMORIA ---");
        for (MemoryBlock block : blocks) {
            System.out.println(block);
        }
        System.out.println("Fragmentación externa: " + getExternalFragmentation() + " KB");
        System.out.println("Fragmentación interna: " + getInternalFragmentation() + " KB");
        System.out.println();
    }

    public List<MemoryBlock> getBlocks() { return blocks; }
}
