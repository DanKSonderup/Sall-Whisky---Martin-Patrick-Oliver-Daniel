package gui;

import controller.MainController;
import controller.Storage;
import javafx.application.Application;
import model.*;
import storage.ListStorage;

import java.time.LocalDate;
import java.util.ArrayList;

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
        CaskSupplier cs1 = MainController.createCaskSupplier("CaskTest1", "CaskVej1", "Caskland", "123");
        MainController.createCaskSupplier("CaskTest2", "CaskVej2", "Caskland", "125");

        MainController.createGrainSupplier("GrainTest1", "GrainVej1", "Grainland", "234");
        MainController.createGrainSupplier("GrainTest2", "GrainVej2", "Grainland", "237");

        Field f1 = MainController.createField("f1", "mark lavet via initStorage");
        Field f2 = MainController.createField("f2", "mark lavet via initStorage");

        GrainSupplier gs1 = MainController.createGrainSupplier("Jørgen", "Hjem", "Danmark", "13578");

        Grain g1 = MainController.createGrain("Byg", gs1, "Korn lavet i initStorage", f1);
        Grain g2 = MainController.createGrain("Hvede", gs1, "Korn lavet i initStorage", f1);

        Maltbatch m1 = MainController.createMaltbatch("nvm4949", "jeg er hoar", g1);


        Warehouse w1 = MainController.createWarehouse("Lager1");
        Warehouse w2 = MainController.createWarehouse("Lager2");
        Rack r1 = MainController.createRack(w1);
        Rack r2 = MainController.createRack(w2);
        Rack r3 = MainController.createRack(w2);

        Shelf sh1 = MainController.createShelf(r1);
        Shelf sh2 = MainController.createShelf(r2);
        Shelf sh3 = MainController.createShelf(r3);
        Shelf sh4 = MainController.createShelf(r1);
        Shelf sh5 = MainController.createShelf(r1);

        MainController.createPosition(sh1, 30);
        MainController.createPosition(sh1, 30);
        MainController.createPosition(sh1, 30);
        MainController.createPosition(sh1, 30);
        MainController.createPosition(sh2, 50);
        MainController.createPosition(sh1, 40);
        MainController.createPosition(sh3, 100);
        Position p1 = MainController.createPosition(sh5, 50);

        Employee em1 = MainController.createEmployee(1, "Hans");

        Cask mainCask = MainController.createCask("Frankrig", 50, "Bourbon", p1, cs1);

        Distillate testD = MainController.createDistillate("Idk", 20, 50, 50, em1, MainController.getMaltbatches());

        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(new DistillateFill(10, testD));

        ArrayList<DistillateFill> distillateFills2 = new ArrayList<>();
        distillateFills2.add(new DistillateFill(30, testD));

        MainController.createFillOnCask(LocalDate.of(2015, 10, 2), mainCask, distillateFills);

        FillOnCask foc = MainController.createFillOnCask(LocalDate.of(2015, 10, 2), mainCask, distillateFills2);
    }
}
