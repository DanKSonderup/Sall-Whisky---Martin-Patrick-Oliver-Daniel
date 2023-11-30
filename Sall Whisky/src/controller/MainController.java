package controller;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class MainController {

    private static List<Observer> observers = new ArrayList<>();
    private static Storage storage;

    public static void setStorage(Storage storage) {
        MainController.storage = storage;
    }

    /**
     * Finds warehouses that has atleast 1 rack where there is space for the Cask we're trying to add
     * @param cask we're checking for space for
     * @return Warehouses where
     */
    public static List<Warehouse> getAvailableWarehouses(Cask cask) {

        List<Warehouse> warehouses = new ArrayList<>(storage.getWarehouses());
        for (Warehouse warehouse: warehouses) {
            if (getAvailableRacks(warehouse, cask).isEmpty()) {
                warehouses.remove(warehouse);
            }
        }
        return warehouses;
    }

    /**
     * Finds racks in a specific warehouse which have space for the cask we're trying to add
     * @param warehouse from which we want non-full racks
     * @return Racks that are not full
     */
    public static List<Rack> getAvailableRacks(Warehouse warehouse, Cask cask) {
        List<Rack> racks = new ArrayList<>(warehouse.getAvailableRacks());
        for (Rack rack: racks) {
            if (getAvailableShelves(rack, cask).isEmpty()) {
                racks.remove(rack);
            }
        }
        return racks;
    }

    /**
     * Finds shelves at a specific rack which are not full
     * @param rack from which we want to find non-full shelves
     * @return Shelves that are not full (isFilled = false)
     */
    public static List<Shelf> getAvailableShelves(Rack rack, Cask cask) {
        List<Shelf> shelves = new ArrayList<>(rack.getAvailableShelves());
        for (Shelf shelf: shelves) {
            if (getAvailablePositions(shelf, cask).isEmpty()) {
                shelves.remove(shelf);
            }
        }
        return shelves;
    }

    /**
     * Finds positions at a specific shelf that aren't full
     * @param shelf from which we want to find non-full positions
     * @return positions at which the cask can be added to
     */

    public static List<Position> getAvailablePositions(Shelf shelf, Cask cask) {
        List<Position> positions = new ArrayList<>(shelf.getAvailablePositions());
        for (Position position : positions) {
            double currentCapacity = 0;
            for (Cask cask1 : position.getCasks()) {
                currentCapacity += cask1.getSizeInLiters();
            }
            if (currentCapacity + cask.getSizeInLiters() > position.getLiterCapacity()) {
                positions.remove(position);
            }
        }
        return positions;
    }


    /**
     * Create and return a Cask
     * Pre: countryOfOrigin is not null
     * Pre: sizeInLiters > 0 / Throw an illegalArgumentException if sizeInLiters <= 0
     * Pre: CaskSupplier og Position is not null
     */
    public static Cask createCask(String countryOfOrigin, double sizeInLiters, String previousContent,
                                  Position position, CaskSupplier supplier) {
        int id = storage.getStorageCounter().getCaskCount();
        if (sizeInLiters <= 0) {
            throw new IllegalArgumentException();
        }
        Cask cask;
        if (previousContent.isBlank()) {
            cask = new Cask(id, countryOfOrigin, sizeInLiters, position, supplier);
        } else {
            cask = new Cask(id, countryOfOrigin, sizeInLiters, previousContent, position, supplier);
        }
        return cask;
    }


    /**
     * Create and return a Warehouse.
     * Increment warehouse counter.
     * Pre: address is not null
     */
    public static Warehouse createWarehouse(String address) {
        int id = storage.getStorageCounter().getWarehouseCount();
        Warehouse warehouse = new Warehouse(id, address);
        storage.storeWarehouse(warehouse);
        storage.getStorageCounter().incrementWarehouseCount();

        return warehouse;
    }

    /**
     * Create and return a rack
     * Add the connection to the warehouse
     * Increment rack counter
     * Pre: A warehouse is created for the rack
     */
    public static Rack createRack(Warehouse warehouse) {
        int id = storage.getStorageCounter().getRackCount();
        Rack rack = new Rack(id);
        warehouse.addRack(rack);
        storage.getStorageCounter().incrementRackCount();

        return rack;
    }

    /**
     * Create and return a shelf
     * Add the connection to the rack
     * Increment shelf counter
     * Pre: A rack is created for the shelf
     */
    public static Shelf createShelf(Rack rack) {
        int id = storage.getStorageCounter().getShelfCount();
        Shelf shelf = new Shelf(id);
        rack.addShelf(shelf);
        storage.getStorageCounter().incrementShelfCount();

        return shelf;
    }

    /**
     * Create and return a position
     * Add the connection to the shelf
     * Increment position counter
     * Notify observers
     * Pre: A shelf is created for the shelf
     */
    public static Position createPosition(Shelf shelf, double literCapacity) {
        int id = storage.getStorageCounter().getPositionCount();
        Position position = new Position(id, literCapacity);
        shelf.addPosition(position);
        storage.getStorageCounter().incrementPositionCount();
        notifyObserver();

        return position;
    }

    /**
     * PRE: AlcoholPercentage and amountInLiters must be > 0, DistillationTime must not be after current time
     * Creates a Distillate and saves it to storage
     * @param newMakenr
     * @param distillationTime
     * @param alcoholPercentage
     * @param amountInLiters
     * @param employee
     * @param maltBatches
     * @return The distillate that was created
     */
    public static Distillate createDistillate(String newMakenr, LocalDateTime distillationTime, double alcoholPercentage, double amountInLiters, Employee employee, List<MaltBatch> maltBatches) {

    }

    /**
     * Creates a GrainSupplier Object and saves it to Storage
     * @return the created GrainSupplier
     */
    public static GrainSupplier createGrainSupplier() {

    }

    public static void notifyObserver() {
        for (Observer observer : observers) {
            observer.notify();
        }
    }
}
