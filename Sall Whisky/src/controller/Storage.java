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
    /** Distillates */
    List<Distillate> getDistillates();
    void storeDistillate(Distillate distillate);
    void deleteDistillate(Distillate distillate);

    //-------------------------------------------------
    /** StorageCounter */
    StorageCounter getStorageCounter();

    //-------------------------------------------------
    /** Maltbatch */
    List<Maltbatch> getMaltbatches();
    void storeMaltbatch(Maltbatch maltBatch);
    void deleteMaltbatch(Maltbatch maltBatch);

    //-------------------------------------------------
    /** Grain */
    List<Grain> getGrains();
    void storeGrain(Grain grain);
    void deleteGrain(Grain grain);

    //-------------------------------------------------
    /** Field */
    List<Field> getFields();
    void storeField(Field field);
    void deleteField(Field field);

    //-------------------------------------------------
    /** GrainSupplier */
    List<GrainSupplier> getGrainSuppliers();
    void storeGrainSupplier(GrainSupplier grainSupplier);
    void deleteGrainSupplier(GrainSupplier grainSupplier);

    //-------------------------------------------------
    /** CaskSupplier */
    List<CaskSupplier> getCaskSuppliers();
    void storeCaskSupplier(CaskSupplier caskSupplier);
    void deleteCaskSupplier(CaskSupplier caskSupplier);

    //-------------------------------------------------
    /** WhiskyBottle */
    List<WhiskyBottle> getWhiskyBottles();
    void storeWhiskyBottle(WhiskyBottle whiskyBottle);
    void deleteWhiskyBottle(WhiskyBottle whiskyBottle);

    //-------------------------------------------------
    /** Whisky */
    List<Whisky> getWhiskies();
    void storeWhisky(Whisky whisky);
    void deleteWhisky(Whisky whisky);
}
