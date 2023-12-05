package model;

import controller.MainController;
import controller.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Position implements Observer, Serializable {
    private int positionId;
    private boolean isFilled = false;
    private double literCapacity;
    private List<Cask> casks = new ArrayList<>();
    private Shelf shelf;

    public Position(int positionId, double literCapacity, Shelf shelf) {
        this.positionId = positionId;
        this.literCapacity = literCapacity;
        this.shelf = shelf;
    }

    /**
     * Checks if the position is full, if so, set isFilled to true.
     */
    public void update() {
        double amountFilled = 0;
        for (Cask cask : casks)
            amountFilled += cask.getSizeInLiters();

        if (amountFilled == literCapacity) {
            isFilled = true;
        } else {
            isFilled = false;
            MainController.notifyObserver();
        }
    }



    /** Getters */
    public int getPositionId() {
        return positionId;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public Shelf getShelf() {
        return shelf;
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

    @Override
    public String toString() {
        return "" + positionId;
    }
}
