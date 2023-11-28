package model;

import java.util.ArrayList;
import java.util.List;

public class Shelf implements Observer {
    private static int nextShelfId = 0;
    private int shelfId;
    private boolean isFilled;
    private List<Position> positions = new ArrayList<>();

    public Shelf(List<Position> positions) {
        this.positions = positions;
        this.shelfId = nextShelfId++;

        if (positions.isEmpty())
            isFilled = true;
        else isFilled = false;
    }

    /**
     * Return a list of all the positions on the shelf that are not fully filled.
     */
    public List<Position> getAvailablePositions() {
        List<Position> availablePositions = new ArrayList<>();
        for (Position position : positions) {
            if (!isFilled)
                availablePositions.add(position);
        }
        return availablePositions;
    }

    /**
     * Checks if all the positions on the shelf are full, if so, set isFilled to true.
     */
    @Override
    public void update() {
        if (getAvailablePositions().isEmpty())
            isFilled = true;
    }

    /** Getters */
    public int getShelfId() {
        return shelfId;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public List<Position> getPositions() {
        return positions;
    }
}
