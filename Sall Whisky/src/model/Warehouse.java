package model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Observer {
    private static int nextWarehouseId = 0;
    private int warehouseId;
    private String address;
    private boolean isFilled;
    private List<Rack> racks = new ArrayList<>();

    public Warehouse(String address, List<Rack> racks) {
        this.address = address;
        this.racks = racks;
        this.warehouseId = nextWarehouseId++; // increments the warehouseId for every warehouse created, for unique id-generation

        if (racks.isEmpty())
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

    /** Getters */
    public int getWarehouseId() {
        return warehouseId;
    }

    public String getAddress() {
        return address;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public List<Rack> getRacks() {
        return racks;
    }
}
