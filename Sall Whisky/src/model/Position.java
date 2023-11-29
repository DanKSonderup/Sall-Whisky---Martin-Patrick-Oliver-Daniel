package model;

import java.util.ArrayList;
import java.util.List;

public class Position implements Observer {
    private int positionId;
    private boolean isFilled = false;
    private double literCapacity;
    private List<Cask> casks = new ArrayList<>();

    public Position(int positionId, double literCapacity) {
        this.positionId = positionId;
        this.literCapacity = literCapacity;
    }

    /**
     * Checks if the position is full, if so, set isFilled to true.
     */
    @Override
    public void update() {
        double amountFilled = 0;
        for (Cask cask : casks)
            amountFilled += cask.getSizeInLiters();

        if (amountFilled == literCapacity)
            isFilled = true;
    }

    /** Getters */
    public int getPositionId() {
        return positionId;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public double getLiterCapacity() {
        return literCapacity;
    }

    public List<Cask> getCasks() {
        return casks;
    }

    /** Add & remove shelf */
    public void addCask(Cask cask) {
        casks.add(cask);
    }
    public void removeCask(Cask cask) {
        casks.remove(cask);
    }
}
