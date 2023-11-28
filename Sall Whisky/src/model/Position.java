package model;

import java.util.ArrayList;
import java.util.List;

public class Position implements Observer {
    private static int nextPositionId = 0;
    private int positionId;
    private boolean isFilled = false; // Er vel altid false til at begynde med?
    private double literCapacity;
    private List<Object> casks = new ArrayList<>(); // SKAL ÆNDRES TIL CASK

    public Position(double literCapacity, List<Object> casks) {
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
        for (Object cask : casks) {
            /*
            amountfilled += cask.getSizeInLiters()
             */
        }
        if (amountFilled == literCapacity)
            isFilled = true;
    }
}
