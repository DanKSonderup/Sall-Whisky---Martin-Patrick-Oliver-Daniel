package storage;

import controller.Storage;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class ListStorage implements Storage {
    private final List<Cask> casks = new ArrayList<>();
    private final List<CaskSupplier> caskSuppliers = new ArrayList<>();
    private final List<Warehouse> warehouses = new ArrayList<>();
    private final List<Maltbatch> maltbatches = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Distillate> distillates = new ArrayList<>();
    private final List<Grain> grains = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();
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
    public List<Maltbatch> getMaltBatches() {
        return new ArrayList<>(maltbatches);
    }
    public void storeMaltBatch(Maltbatch maltBatch) {
        maltbatches.add(maltBatch);
    }
    public void deleteMaltBatch(Maltbatch maltBatch) {
        maltbatches.remove(maltBatch);
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


    /** Distillate */
    @Override
    public List<Distillate> getDistillate() {
        return new ArrayList<>(distillates);
    }
    public void storeDistillate(Distillate distillate) {
        distillates.add(distillate);
    }
    public void deleteDistillate(Distillate distillate) {
        distillates.remove(distillate);
    }

    /** Grain */
    @Override
    public List<Grain> getGrains() {
        return new ArrayList<>(grains);
    }
    @Override
    public void storeGrain(Grain grain) {
        grains.add(grain);
    }
    @Override
    public void deleteGrain(Grain grain) {
        grains.remove(grain);
    }

    /** Field */
    @Override
    public List<Field> getFields() {
        return new ArrayList<>(fields);
    }
    @Override
    public void storeField(Field field) {
        fields.add(field);
    }
    @Override
    public void deleteField(Field field) {
        fields.remove(field);
    }
}
