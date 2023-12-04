package controller;

import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class MainController {

    private static List<Observer> observers = new ArrayList<>();
    private static Storage storage;

    public static void setStorage(Storage storage) {
        MainController.storage = storage;
    }

    public static Storage getStorage() {
        return storage;
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
     * Pre: sizeInLiters > 0 / Throw an illegalArgumentException if sizeInLiters <= 0
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
     */
    public static Warehouse createWarehouse(String address) {
        int id = storage.getStorageCounter().getWarehouseCount();
        Warehouse warehouse = new Warehouse(id, address);
        storage.storeWarehouse(warehouse);
        storage.getStorageCounter().incrementWarehouseCount();
        addObserver(warehouse);
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
        addObserver(rack);
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
        addObserver(shelf);
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
     * Create and return FillOnCask object
     * Connection is added to cask
     * Connection is added to DistillateFill
     * If distillateFill is > sizeInLiters (Cask) throw an illegalArgumentException
     * If timeOfFill is after LocalDate.now() throw an illegalArgumentException
     */
    public static FillOnCask createFillOnCask(LocalDate timeOfFill, Cask cask, ArrayList<DistillateFill> distillateFills) {
        FillOnCask fillOnCask = new FillOnCask(timeOfFill, cask);
        cask.addFillOnCask(fillOnCask);

        double sum = 0;
        for (DistillateFill distillateFill : distillateFills) {
            sum += distillateFill.getAmountOfDistillate();
            fillOnCask.addDistillateFill(distillateFill);
        }
        if (sum > cask.getSizeInLiters())
            throw new IllegalArgumentException("Fadets størrelse er mindre end din påfyldning");
        if (timeOfFill.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Dato er efter nuværende dato");

        return fillOnCask;
    }

    /**
     * Create, store and return an employee
     */
    public static Employee createEmployee(int id, String name) {
        Employee employee = new Employee(id, name);
        storage.storeEmployee(employee);
        return employee;
    }


    /**
     * Creates a Distillate and saves it to storage
     * @param newMakenr
     * Pre: alcoholPercentage > 0
     * Pre: amountInLiters > 0
     * @param employee
     * @param maltbatches
     * @return the created Distillate
     */
    public static Distillate createDistillate(String newMakenr, double distillationTimeInHours,
                                              double alcoholPercentage, double amountInLiters, Employee employee,
                                              List<Maltbatch> maltbatches) {
        Distillate distillate = new Distillate(newMakenr, distillationTimeInHours, alcoholPercentage, amountInLiters,
                employee, maltbatches);
        storage.storeDistillate(distillate);
        return distillate;
    }

    /**
     * Return all distillates
     */
    public static List<Distillate> getDistillates() {
        return storage.getDistillates();
    }

    /**
     * Create, store and return a maltbatch
     * Add the connection to the grain
     * Pre: A grain is created for the maltbatch
     */
    public static Maltbatch createMaltbatch (String name, String description, Grain grain) {
        Maltbatch maltbatch = new Maltbatch(name, description, grain);
        storage.storeMaltBatch(maltbatch);
        return maltbatch;
    }
    /**
     * Return all maltbatches
     */
    public static List<Maltbatch> getMaltbatches() {
        return storage.getMaltBatches();
    }

    /**
     * Remove a maltbatch
     */
    public static void removeMaltbatch(Maltbatch maltbatch) {
        storage.deleteMaltBatch(maltbatch);
    }


    /**
     * Create, store and return a grain
     * Add the connection to the grain supplier
     * Pre: A grain supplier and a field is created
     */
    public static Grain createGrain (String grainType, GrainSupplier grainSupplier, String cultivationDescription, Field field) {
        Grain grain = new Grain(grainType, grainSupplier, cultivationDescription, field);
        storage.storeGrain(grain);
        return grain;
    }

    /**
     * Return all fields
     */
    public static List<Grain> getGrains() {
        return storage.getGrains();
    }

    /**
     * Remove a grain
     */
    public static void removeGrain(Grain grain) {
        storage.deleteGrain(grain);
    }

    /**
     * Create, store and return a field
     */
    public static Field createField (String name, String description) {
        Field field = new Field(name, description);
        storage.storeField(field);
        return field;
    }

    /**
     * Return all fields
     */
    public static List<Field> getFields() {
        return storage.getFields();
    }

    /**
     * Remove a field
     */
    public static void removeField(Field field) {
        storage.deleteField(field);
    }

    /**
     * Create, store and return a GrainSupplier
     */
    public static GrainSupplier createGrainSupplier (String name, String address, String country, String vatId) {
        GrainSupplier grainSupplier = new GrainSupplier(name, address, country, vatId);
        storage.storeGrainSupplier(grainSupplier);
        return grainSupplier;
    }


    /**
     * Create, store and return a CaskSupplier
     */
    public static CaskSupplier createCaskSupplier (String name, String address, String country, String vatId) {
        CaskSupplier caskSupplier = new CaskSupplier(name, address, country, vatId);
        storage.storeCaskSupplier(caskSupplier);
        return caskSupplier;
    }

    /**
     * Create, store and return a Whisky
     * Pre: alcoholPercentage > 0 && alcoholPercentage < 100
     */
    public static Whisky createWhisky(String name, double alcoholPercentage, List<WhiskyFill> whiskyFills) {
            Whisky whisky = new Whisky(name, alcoholPercentage, whiskyFills);
            WhiskyBottle whiskyBottle = new WhiskyBottle(storage.getStorageCounter(), 0, whisky);
            whisky.addWhiskyBottle(whiskyBottle);
            storage.storeWhisky(whisky);
        return whisky;
    }



    /**
     * Return all grainSupplier objects
     */
    public static List<GrainSupplier> getGrainSuppliers() {
        return storage.getGrainSuppliers();
    }

    public static List<CaskSupplier> getCaskSuppliers() {
        return storage.getCaskSuppliers();
    }

    public static void notifyObserver() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    public static void addObserver(Observer observer) {
        observers.add(observer);
    }
    }
