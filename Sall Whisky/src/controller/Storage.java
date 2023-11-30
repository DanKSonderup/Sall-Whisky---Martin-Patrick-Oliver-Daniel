package controller;

import model.*;

import java.util.List;

public interface Storage {

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

    /** Employee */
    List<Distillate> getDistillate();
    void storeDistillate(Distillate distillate);
    void deleteDistillate(Distillate distillate);

    //-------------------------------------------------
    /** StorageCounter */
    StorageCounter getStorageCounter();

    /** Maltbatch */
    List<Maltbatch> getMaltBatches();
    void storeMaltBatch(Maltbatch maltBatch);
    void deleteMaltBatch(Maltbatch maltBatch);

    /** Grain */
    List<Grain> getGrain();
    void storeGrain(Grain grain);
    void deleteGrain(Grain grain);

}
