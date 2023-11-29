package model;

import java.util.ArrayList;
import java.util.List;

public class Position implements Observer {
    private int positionId = 0;
    private boolean isFilled = false;
    private double literCapacity;
    private List<Cask> casks = new ArrayList<>();

    public Position(double literCapacity) {
        this.literCapacity = literCapacity;
        positionId++;
    }

    /**
     * Checks if the position is full, if so, set isFilled to true.
     */
    @Override
    public void update() {
        double amountFilled = 0;
        for (Cask cask : casks) {

            amountFilled += cask.getSizeInLiters();

        }
        if (amountFilled == literCapacity)
            isFilled = true;
    }

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
}
