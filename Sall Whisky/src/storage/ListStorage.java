package storage;

import controller.Storage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {
    private final List<Cask> casks = new ArrayList<>();
    private final List<CaskSupplier> caskSuppliers = new ArrayList<>();
    private final List<Warehouse> warehouses = new ArrayList<>();
    private final List<MaltBatch> maltBatches = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private StorageCounter storageCounter = new StorageCounter();

    //----------------------------------------------------------------------
    /** CaskSupplier */
    public List<CaskSupplier> getCaskSuppliers() {
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
    public List<Cask> getCasks() {
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
    public List<Warehouse> getWarehouses() {
        return new ArrayList<>(warehouses);
    }
    public void storeWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }
    public void deleteWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }



    //----------------------------------------------------------------------
    /** StorageCounter */
    public StorageCounter getStorageCounter() {
        return storageCounter;
    }
    //----------------------------------------------------------------------
    /** Maltbatch */
    public List<MaltBatch> getMaltBatches() {
        return new ArrayList<>(maltBatches);
    }
    public void storeMaltBatch(MaltBatch maltBatch) {
        maltBatches.add(maltBatch);
    }
    public void deleteMaltBatch(MaltBatch maltBatch) {
        maltBatches.remove(maltBatch);
    }

    //----------------------------------------------------------------------
    /** Employee */

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    public void storeEmployee(Employee employee) {
        employees.add(employee);
    }
    public void deleteEmployee(Employee employee) {
        employees.remove(employee);
    }
}
