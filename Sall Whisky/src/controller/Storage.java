package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public interface Storage {
    //-------------------------------------------------
    /** StorageCounter */
    StorageCounter getStorageCounter();

    //-------------------------------------------------
    /** CaskSuppliers */
    List<CaskSupplier> getCaskSuppliers();
    void storeCaskSupplier(CaskSupplier caskSupplier);
    void deleteCaskSupplier(CaskSupplier caskSupplier);

    //-------------------------------------------------
    /** Casks */
    List<Cask> getCasks();
    void storeCask(Cask cask);
    void deleteCask(Cask cask);

    //-------------------------------------------------
    /** Warehouses */
    List<Warehouse> getWarehouses();
    void storeWarehouse(Warehouse warehouse);
    void deleteWarehouse(Warehouse warehouse);

    //-------------------------------------------------
    /** Employee */
    List<Employee> getEmployees();
    void storeEmployee(Employee employee);
    void deleteEmployee(Employee employee);

    //-------------------------------------------------
    /** Maltbatch */
    List<MaltBatch> getMaltBatches();
    void storeMaltBatch(MaltBatch maltBatch);
    void deleteMaltBatch(MaltBatch maltBatch);

    //-------------------------------------------------
    /** Grain */
    List<Grain> getGrain();
    void storeGrain(Grain grain);
    void deleteGrain(Grain grain);
}
