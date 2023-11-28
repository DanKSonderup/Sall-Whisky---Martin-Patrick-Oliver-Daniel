package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class MainController {

    List<Observer> observers = new ArrayList<>();
    private static Storage storage;

    public static void setStorage(Storage storage) {
        MainController.storage = storage;
    }

    /**
     * Finds warehouses that has atleast 1 rack which is not full
     * @return Warehouses where isFilled = false
     */
    public static List<Warehouse> getAvailableWarehouses() {

        return null;
    }

    /**
     * Finds racks in a specific warehouse which are not full
     * @param warehouse from which we want non-full racks
     * @return Racks that are not full (isFilled = false)
     */
    public static List<Rack> getAvailableRacks(Warehouse warehouse) {

        return null;
    }

    /**
     * Finds shelves at a specific rack which are not full
     * @param rack from which we want to find non-full shelves
     * @return Shelves that are not full (isFilled = false)
     */
    public static List<Shelf> getAvailableShelves(Rack rack) {

        return null;
    }

    /**
     * Finds positions at a specific shelf which is not full
     * @param shelf
     * @param cask
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
     * Pre: sizeInLiters > 0
     * Pre: CaskSupplier og Position is not null
     */
    public static Cask createCask(String countryOfOrigin, double sizeInLiters, String previousContent,
                                  Position position, CaskSupplier supplier) {
        Cask cask;
        if (previousContent.isBlank()) {
            cask = new Cask(countryOfOrigin, sizeInLiters, position, supplier);
        } else {
            cask = new Cask(countryOfOrigin, sizeInLiters, previousContent, position, supplier);
        }
        return cask;
    }


    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
