package controller;

import model.*;

import java.util.List;

public interface Storage {


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
    List<Distillate> getDistillates();
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
    List<Grain> getGrains();
    void storeGrain(Grain grain);
    void deleteGrain(Grain grain);

    /** Field */
    List<Field> getFields();
    void storeField(Field field);
    void deleteField(Field field);

    /** GrainSupplier */
    List<GrainSupplier> getGrainSuppliers();
    void storeGrainSupplier(GrainSupplier grainSupplier);
    void deleteGrainSupplier(GrainSupplier grainSupplier);

    /** CaskSupplier */
    List<CaskSupplier> getCaskSuppliers();
    void storeCaskSupplier(CaskSupplier caskSupplier);
    void deleteCaskSupplier(CaskSupplier caskSupplier);

    /** Whisky */
    List<Whisky> getWhiskies();
    void storeWhisky(Whisky whisky);
    void deleteWhisky(Whisky whisky);

}
