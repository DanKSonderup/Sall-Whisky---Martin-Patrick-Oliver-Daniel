package controller;

import model.Cask;
import model.CaskSupplier;
import model.StorageCounter;
import model.Warehouse;

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
    /** StorageCounter */
    StorageCounter getStorageCounter();

}
