package model;

import java.util.ArrayList;
import java.util.List;

public class Position implements Observer {
    private static int nextPositionId = 0;
    private int positionId;
    private boolean isFilled = false; // Er vel altid false til at begynde med?
    private double literCapacity;
    private List<Cask> casks = new ArrayList<>(); // SKAL ÆNDRES TIL CASK

    public Position(double literCapacity, List<Cask> casks) {
        this.literCapacity = literCapacity;
        this.casks = casks;
        this.positionId = nextPositionId++;
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
}
