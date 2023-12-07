package controller;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public abstract class Controller {

    private static List<Observer> observers = new ArrayList<>();
    private static Storage storage;

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    public static Storage getStorage() {
        return storage;
    }

    /** Returns all Casks from Storage */
    public static List<Cask> getCasks() {
        return storage.getCasks();
    }

    /**
     * Create and return all Casks that have more than 0 currentContentInLiters and was filled more than 3 years ago
     */
    public static List<Cask> getRipeCasks() {
        List<Cask> ripeCasks = new ArrayList<>();
        for (Cask cask: storage.getCasks()) {
            if (cask.getYoungestFillOnCask().getTimeOfFill().isBefore(LocalDate.now().minusYears(3)) && cask.getCurrentContentInLiters() > 0) {
                ripeCasks.add(cask);
            }
        }
        return ripeCasks;
    }

    /**
     * Finds warehouses that has atleast 1 rack where there is space for the Cask we're trying to add
     * @param cask we're checking for space for
     * @return Warehouses where
     */
    public static List<Warehouse> getAvailableWarehouses(Cask cask) {
        List<Warehouse> warehouses = new ArrayList<>(storage.getWarehouses());

        for (int i = 0; i < warehouses.size(); i++) {
            if (warehouses.get(i).isFilled()) {
                warehouses.remove(warehouses.get(i));
                i--;
            } else if (getAvailableRacks(warehouses.get(i), cask).isEmpty()) {
                warehouses.remove(warehouses.get(i));
                i--;
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
        List<Rack> racks = new ArrayList<>(warehouse.getRacks());
        for (int i = 0; i < racks.size(); i++) {
            if (racks.get(i).isFilled()) {
                racks.remove(racks.get(i));
                i--;
            } else if (getAvailableShelves(racks.get(i), cask).isEmpty()) {
                racks.remove(racks.get(i));
                i--;
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
        List<Shelf> shelves = new ArrayList<>(rack.getShelves());
        for (int i = 0; i < shelves.size(); i++) {
            if (shelves.get(i).isFilled()) {
                shelves.remove(shelves.get(i));
                i--;
            } else if (getAvailablePositions(shelves.get(i), cask).isEmpty()) {
                shelves.remove(shelves.get(i));
                i--;
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
        List<Position> positions = new ArrayList<>(shelf.getPositions());
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).isFilled()) {
                positions.remove(positions.get(i));
                i--;
            } else {
                double currentLiters = 0;
                for (Cask cask1 : positions.get(i).getCasks()) {
                    currentLiters += cask1.getSizeInLiters();
                }
                if (currentLiters + cask.getSizeInLiters() > positions.get(i).getLiterCapacity()) {
                    positions.remove(positions.get(i));
                    i--;
                }
            }
        }
        return positions;
    }

    /**
     * Create, store and return a Cask
     * Pre: sizeInLiters > 0 / Throw an illegalArgumentException if sizeInLiters <= 0
     * add the cask to the position
     */
    public static Cask createCask(String countryOfOrigin, double sizeInLiters, String previousContent,
                                  Position position, CaskSupplier supplier) {
        int id = storage.getStorageCounter().getCaskCount();
        storage.getStorageCounter().incrementCaskCount();
        if (sizeInLiters <= 0) {
            throw new IllegalArgumentException();
        }
        Cask cask;
        if (previousContent.isBlank()) {
            cask = new Cask(id, countryOfOrigin, sizeInLiters, position, supplier);
        } else {
            cask = new Cask(id, countryOfOrigin, sizeInLiters, previousContent, position, supplier);
        }
        storage.storeCask(cask);
        position.addCask(cask);
        return cask;
    }

    /** Remove a cask */
    public static void removeCask(Cask cask) {
        storage.deleteCask(cask);
    }

    /** Get all available casks */
    public static ArrayList<Cask> getAvailableCasks() {
        ArrayList<Cask> availableCasks = new ArrayList<>();
        for (Cask cask: Controller.getCasks()) {
            if (cask.getLitersAvailable() > 0) {
                availableCasks.add(cask);
            }
        }
        return availableCasks;
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
        Rack rack = new Rack(id, warehouse);
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
        Shelf shelf = new Shelf(id, rack);
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
        Position position = new Position(id, literCapacity, shelf);
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
    public static FillOnCask createFillOnCask(LocalDate timeOfFill, Cask cask, ArrayList<DistillateFill> distillateFills) throws IllegalArgumentException {
        FillOnCask fillOnCask = new FillOnCask(timeOfFill, cask);

        double sum = 0;
        for (DistillateFill distillateFill : distillateFills) {
            sum += distillateFill.getAmountOfDistillateInLiters();
            fillOnCask.addDistillateFill(distillateFill);
        }
        if (sum > cask.getSizeInLiters())
            throw new IllegalArgumentException("Fadets størrelse er mindre end din påfyldning");
        if (timeOfFill.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Dato er efter nuværende dato");
        }

        cask.addFillOnCask(fillOnCask);

        return fillOnCask;
    }

    /** Create, store and return an employee */
    public static Employee createEmployee(int id, String name) {
        Employee employee = new Employee(id, name);
        storage.storeEmployee(employee);
        return employee;
    }

    /** Returns Employees from Storage */
    public static List<Employee> getEmployees() {
        return new ArrayList<>(storage.getEmployees());
    }

    /**
     * Creates a Distillate and saves it to storage
     * @param newMakenr
     * Pre: 100 > alcoholPercentage > 0
     * Pre: amountInLiters > 0
     * @return the created Distillate
     */
    public static Distillate createDistillate(String newMakenr, double distillationTimeInHours,
                                              double alcoholPercentage, double amountInLiters, Employee employee,
                                              List<Maltbatch> maltbatches, String description) {
        Distillate distillate = new Distillate(newMakenr, distillationTimeInHours, alcoholPercentage, amountInLiters,
                employee, maltbatches, description);
        storage.storeDistillate(distillate);
        return distillate;
    }

    /** Return all distillates */
    public static List<Distillate> getDistillates() {
        return storage.getDistillates();
    }

    /** Return all distillates that still haven't been fully filled on a Cask */
    public static List<Distillate> getAvailableDistillates() {
        List<Distillate> distillates = new ArrayList<>();
        for (Distillate distillate: storage.getDistillates())  {
            if (distillate.getAmountInLiters() > 0) {
                distillates.add(distillate);
            }
        }
        return distillates;
    }

    /**
     * Create, store and return a maltbatch
     * Add the connection to the grain
     * Pre: A grain is created for the maltbatch
     */
    public static Maltbatch createMaltbatch (String name, String description, Grain grain) {
        Maltbatch maltbatch = new Maltbatch(name, description, grain);
        storage.storeMaltbatch(maltbatch);
        return maltbatch;
    }

    /** Return all maltbatches */
    public static List<Maltbatch> getMaltbatches() {
        return storage.getMaltbatches();
    }

    /** Remove a maltbatch */
    public static void removeMaltbatch(Maltbatch maltbatch) {
        storage.deleteMaltbatch(maltbatch);
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

    /** Return all fields */
    public static List<Grain> getGrains() {
        return storage.getGrains();
    }

    /** Remove a grain */
    public static void removeGrain(Grain grain) {
        storage.deleteGrain(grain);
    }

    /** Create, store and return a field */
    public static Field createField (String name, String description) {
        Field field = new Field(name, description);
        storage.storeField(field);
        return field;
    }

    /** Return all fields */
    public static List<Field> getFields() {
        return storage.getFields();
    }

    /** Remove a field */
    public static void removeField(Field field) {
        storage.deleteField(field);
    }

    /** Create, store and return a GrainSupplier */
    public static GrainSupplier createGrainSupplier (String name, String address, String country, String vatId) {
        GrainSupplier grainSupplier = new GrainSupplier(name, address, country, vatId);
        storage.storeGrainSupplier(grainSupplier);
        return grainSupplier;
    }


    /** Create, store and return a CaskSupplier */
    public static CaskSupplier createCaskSupplier (String name, String address, String country, String vatId) {
        CaskSupplier caskSupplier = new CaskSupplier(name, address, country, vatId);
        storage.storeCaskSupplier(caskSupplier);
        return caskSupplier;
    }

    /**
     * Create, store and return a Whisky
     * Pre: alcoholPercentage > 0 && alcoholPercentage < 100
     */
    public static Whisky createWhisky(String name, double waterInLiters, List<WhiskyFill> whiskyFills, String description) {
        Whisky whisky = new Whisky(name,waterInLiters, whiskyFills, description);
        storage.storeWhisky(whisky);
        return whisky;
    }
    /** Get all whiskys */
    public static List<Whisky> getWhiskies() {
        return new ArrayList<>(storage.getWhiskies());
    }

    /**
     * Create, store and return a WhiskyBottle
     * Increment WhiskyBottleCount
     * Pre: centiliterCapacity > 0
     */
    public static WhiskyBottle createWhiskyBottle(int centiliterCapacity, Whisky whisky) {
        WhiskyBottle whiskyBottle = new WhiskyBottle(storage.getStorageCounter().getWhiskyBottleCount(),centiliterCapacity, whisky);
        storage.storeWhiskyBottle(whiskyBottle);
        storage.getStorageCounter().incrementWhiskyBottleCount();
        return whiskyBottle;
    }


    /**
     * create multiple WhiskyBottles for a specific Whisky object
     * Pre: numberOfBottles > 0
     * Pre: centiliterCapacity > 0
     */
    public static void createWhiskyBottlesForWhisky(int numberOfBottles, int centiliterCapacity, Whisky whisky) {
        for (int i = 0; i < numberOfBottles; i++) {
            createWhiskyBottle(centiliterCapacity, whisky);
        }
    }

    /** Calculates and returns the amount of bottles needed for a whisky creation */
    public static int amountOfBottles(Whisky whisky, int whiskyBottleCapacity) {
        return (int) (whisky.totalFluidsInWhisky() * 100) / whiskyBottleCapacity;
    }

    /**
     * Create and return a WhiskyFill
     * Pre: amountOfDistilateFillInLiters > 0
     * Add connection to cask
     */
    public static WhiskyFill createWhiskyFill(double amountOfDistilateFillInLiters, List<FillOnCask> fillOnCasks, double alcoholPercentage, Cask cask) throws InterruptedException {
        WhiskyFill whiskyFill = new WhiskyFill(amountOfDistilateFillInLiters, fillOnCasks, LocalDate.now(), alcoholPercentage, cask);

        if (cask.getCurrentContentInLiters() - amountOfDistilateFillInLiters < 0) {
            throw new InterruptedException("Du prøver at påfylde flere liter end du har tilgængelig");
        } else if ((cask.getCurrentContentInLiters() - amountOfDistilateFillInLiters) == 0) {
            for (FillOnCask fillOnCask: fillOnCasks) {
                fillOnCask.getCask().addPreviousFillOnCask(fillOnCask);
                fillOnCask.getCask().removeFillOnCask(fillOnCask);
            }
        }
        cask.setCurrentContentInLiters(cask.getCurrentContentInLiters() - amountOfDistilateFillInLiters);

        return whiskyFill;
    }

    /** Get all grainSupplier objects */
    public static List<GrainSupplier> getGrainSuppliers() {
        return storage.getGrainSuppliers();
    }

    /** Get all caskSupplier objects */
    public static List<CaskSupplier> getCaskSuppliers() {
        return storage.getCaskSuppliers();
    }

    /**
     * Return a String containing the entire Story and process of a Whisky
     */
    public static String generateStoryForWhisky(Whisky whisky) {
        Stack<String> infoStrings = new Stack<>();


        String whiskyInfo = whisky.getName() + "\nTappet d. " + whisky.getWhiskyFills().get(0).getTimeOfFill() +
                "\nFortyndet med " + whisky.getWaterInLiters() +
                " liter vand \nBeskrivelse: " + whisky.getDescription();

        infoStrings.add(whiskyInfo + "\n ------------------- \n");

        String caskInfos = "\nFade i denne whisky:\n\n";
        for (WhiskyFill whiskyFill: whisky.getWhiskyFills()) {
            Cask currentCask = whiskyFill.getCask();
            caskInfos += "FadID: " + currentCask.getCaskId()
                    + "\nOprindelsesland: " + currentCask.getCountryOfOrigin()
                    + "\nTidligere indhold: " + currentCask.getPreviousContent()
                    + "\nFadtype: Egetræ"
                    + "\nLeverandør: " + currentCask.getSupplierName()
                    + "\n - Land: " + currentCask.getSupplier().getCountry();
            caskInfos += "\n\n";
        }


        infoStrings.add(caskInfos);


        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = infoStrings.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        return sb.toString();
    }

    /** Observer methods */
    public static void notifyObserver() {
            for (Observer observer : observers) {
                observer.update();
            }
    }

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }
    }
