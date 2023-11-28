package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Observer {
    private int warehouseId = 0;
    private String address;
    private boolean isFilled;
    private List<Rack> racks = new ArrayList<>();

    public Warehouse(String address, List<Rack> racks) {
        this.address = address;
        this.racks = racks;
        warehouseId++; // increments the warehouseId for every warehouse created, for unique id-generation

        if (racks.isEmpty()) // Kan eventuelt ændres til altid at initialize som false?
            isFilled = true;
        else isFilled = false;
    }

    /**
     * Return a list of all the racks in the warehouse that are not fully filled.
     */
    public List<Rack> getAvailableRacks() {
        List<Rack> availableRacks = new ArrayList<>();
        for (Rack rack : racks) {
            if (!isFilled)
                availableRacks.add(rack);
        }
        return availableRacks;
    }

    /**
     * Checks if all the racks in the warehouse are full, if so, set isFilled to true.
     */
    @Override
    public void update() {
        if (getAvailableRacks().isEmpty())
            isFilled = true;
    }
}
