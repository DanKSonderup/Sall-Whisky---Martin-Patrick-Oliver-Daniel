package controller;

import java.util.ArrayList;

public abstract class MainController {


    /**
     * Finds warehouses that has atleast 1 rack which is not full
     * @return Warehouses where isFilled = false
     */
    public static List<Warehouse> getAvailableWarehouses() {

    }

    /**
     * Finds racks in a specific warehouse which are not full
     * @param warehouse from which we want non-full racks
     * @return Racks that are not full (isFilled = false)
     */
    public static List<Rack> getAvailableRacks(Warehouse warehouse) {

    }

    /**
     * Finds shelves at a specific rack which are not full
     * @param rack from which we want to find non-full shelves
     * @return Shelves that are not full (isFilled = false)
     */
    public static List<Shelf> getAvailableShelves(Rack rack) {

    }

    public static List<Position> getAvailablePositions() {

    }

    List<Observer> observers = new ArrayList<>();

    /**
     * Opret og returner en Cask
     * Pre: countryOfOrigin må ikke være null
     * Pre: sizeInLiters > 0
     * Pre: CaskSupplier og Position må ikke være null
     */
    public static Cask createCask(String countryOfOrigin, double sizeInLiters, String previousContent,
                                  Position position, CaskSupplier supplier) {

        return  null;
    }


    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
