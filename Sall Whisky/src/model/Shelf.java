package model;

import java.util.ArrayList;
import java.util.List;

public class Shelf implements Observer {
    private int shelfId;
    private boolean isFilled = true;
    private List<Position> positions = new ArrayList<>();

    public Shelf(int shelfId) {
        this.shelfId = shelfId;

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
        else isFilled = false;
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

    /** Add & remove position */
    public void addPosition(Position position) {
        positions.add(position);
    }
    public void removePosition(Position position) {
        positions.remove(position);
    }
}
