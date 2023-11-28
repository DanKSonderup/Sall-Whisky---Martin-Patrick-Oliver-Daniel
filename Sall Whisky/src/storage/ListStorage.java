package storage;

import controller.Storage;
import model.Cask;
import model.CaskSupplier;
import model.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {
    private final List<Cask> casks = new ArrayList<>();
    private final List<CaskSupplier> caskSuppliers = new ArrayList<>();
    private final List<Warehouse> warehouses = new ArrayList<>();

    //----------------------------------------------------------------------
    /** CaskSupplier */
    public ArrayList<CaskSupplier> getCaskSuppliers() {
        return new ArrayList<>(caskSuppliers);
    }
    public void storeCaskSupplier(CaskSupplier caskSupplier) {
        caskSuppliers.add(caskSupplier);
    }
    public void deleteCaskSupplier(CaskSupplier caskSupplier) {
        caskSuppliers.remove(caskSupplier);
    }
    //----------------------------------------------------------------------

    /** Casks */
    public ArrayList<Cask> getCasks() {
        return new ArrayList<>(casks);
    }
    public void storeCask(Cask cask) {
        casks.add(cask);
    }
    public void deleteCask(Cask cask) {
        casks.remove(cask);
    }
    //----------------------------------------------------------------------
    /** Warehouses */
    public ArrayList<Warehouse> getWarehouses() {
        return new ArrayList<>(warehouses);
    }
    public void storeWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }
    public void deleteWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }
}
