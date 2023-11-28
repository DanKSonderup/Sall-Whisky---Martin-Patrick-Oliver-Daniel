package model;

import java.util.ArrayList;
import java.util.List;

public class Rack implements Observer {
    private int rackId = 0;
    private boolean isFilled;
    private List<Shelf> shelves = new ArrayList<>();

    public Rack(List<Shelf> shelves) {
        this.shelves = shelves;
        rackId++; // increments the rackId for every rack created.
                  // DETTE BETYDER SÅ AT VI IKKE STARTER FRA 0 IGEN NÅR VI LAVER RACKS PÅ ET NYT LAGER
                  // TÆNKER OM DET KAN FIKSES VED AT DET ER WAREHOUSE DER OPRETTER RACKS
                  // samme gør sig gældene for shelves og positions

        if (shelves.isEmpty())
            isFilled = true;
        else isFilled = false;
    }

    /**
     * Return a list of all the shelves on the rack that are not fully filled.
     */
    public List<Shelf> getAvailableShelves() {
        List<Shelf> availableShelves = new ArrayList<>();
        for (Shelf shelf : shelves) {
            if (!isFilled)
                availableShelves.add(shelf);
        }
        return availableShelves;
    }

    /**
     * Checks if all the shelves on the rack are full, if so, set isFilled to true.
     */
    @Override
    public void update() {
        if (getAvailableShelves().isEmpty())
            isFilled = true;
    }
}
