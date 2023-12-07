package model;

import controller.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rack implements Observer, Serializable {
    private int rackId;
    private boolean isFilled = true;
    private List<Shelf> shelves = new ArrayList<>();
    private Warehouse warehouse;

    public Rack(int rackId, Warehouse warehouse) {
        this.rackId = rackId;
        this.warehouse = warehouse;
    }

    /** Return a list of all the shelves on the rack that are not fully filled. */
    public List<Shelf> getAvailableShelves() {
        List<Shelf> availableShelves = new ArrayList<>();
        for (Shelf shelf : shelves) {
            if (!shelf.isFilled())
                availableShelves.add(shelf);
        }
        return availableShelves;
    }

    /** Checks if all the shelves on the rack are full, if so, set isFilled to true. */
    public void update() {
        if (getAvailableShelves().isEmpty()) {
            isFilled = true;
        } else {
            isFilled = false;
        }
    }

    /** Getters */
    public int getRackId() {
        return rackId;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    /** Add & remove shelf */
    public void addShelf(Shelf shelf) {
        shelves.add(shelf);
    }
    public void removeShelf(Shelf shelf) {
        shelves.remove(shelf);
    }

    @Override
    public String toString() {
        return "" + rackId;
    }
}
