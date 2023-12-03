package gui;

import controller.MainController;
import controller.Storage;
import gui.guicontrollers.CreateSupplierViewController;
import javafx.application.Application;
import model.Field;
import model.Grain;
import model.GrainSupplier;
import model.Maltbatch;
import storage.ListStorage;

public class App {
    public static void main(String[] args) {

//        Storage storage = ListStorage.loadStorage();
        Storage storage = new ListStorage();
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage created");
        }
        MainController.setStorage(storage);
        initStorage();

        Application.launch(Gui.class);



        // ListStorage.saveStorage(storage);
    }

    // Bare til at teste, skal fjernes da vi jo bruger serialization
    public static void initStorage() {
        Field f1 = MainController.createField("f1", "mark lavet via initStorage");
        Field f2 = MainController.createField("f2", "mark lavet via initStorage");

        GrainSupplier gs1 = MainController.createGrainSupplier("Jørgen", "Hjem", "Danmark", "13578");

        Grain g1 = MainController.createGrain("Byg", gs1, "Korn lavet i initStorage", f1);
        Grain g2 = MainController.createGrain("Hvede", gs1, "Korn lavet i initStorage", f1);

        Maltbatch m1 = MainController.createMaltbatch("nvm4949", "jeg er hoar", g1);
    }
}
