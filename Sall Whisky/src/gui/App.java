package gui;

import controller.Controller;
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
        Controller.setStorage(storage);
        initStorage();


        Application.launch(Gui.class);



        // ListStorage.saveStorage(storage);
    }

    // Bare til at teste, skal fjernes da vi jo bruger serialization
    public static void initStorage() {
        CaskSupplier cs1 = Controller.createCaskSupplier("CaskTest1", "CaskVej1", "Caskland", "123");
        Controller.createCaskSupplier("CaskTest2", "CaskVej2", "Caskland", "125");

        Controller.createGrainSupplier("GrainTest1", "GrainVej1", "Grainland", "234");
        Controller.createGrainSupplier("GrainTest2", "GrainVej2", "Grainland", "237");

        Field f1 = Controller.createField("f1", "mark lavet via initStorage");
        Field f2 = Controller.createField("f2", "mark lavet via initStorage");

        GrainSupplier gs1 = Controller.createGrainSupplier("Jørgen", "Hjem", "Danmark", "13578");

        Grain g1 = Controller.createGrain("Byg", gs1, "Korn lavet i initStorage", f1);
        Grain g2 = Controller.createGrain("Hvede", gs1, "Korn lavet i initStorage", f1);

        Maltbatch m1 = Controller.createMaltbatch("nvm4949", "jeg er hoar", g1);


        Warehouse w1 = Controller.createWarehouse("Lager1");
        Warehouse w2 = Controller.createWarehouse("Lager2");
        Rack r1 = Controller.createRack(w1);
        Rack r2 = Controller.createRack(w2);
        Rack r3 = Controller.createRack(w2);

        Shelf sh1 = Controller.createShelf(r1);
        Shelf sh2 = Controller.createShelf(r2);
        Shelf sh3 = Controller.createShelf(r3);
        Shelf sh4 = Controller.createShelf(r1);
        Shelf sh5 = Controller.createShelf(r1);

        Controller.createPosition(sh1, 30);
        Controller.createPosition(sh1, 30);
        Controller.createPosition(sh1, 30);
        Controller.createPosition(sh1, 30);
        Controller.createPosition(sh2, 50);
        Controller.createPosition(sh1, 40);
        Controller.createPosition(sh3, 100);
        Position p1 = Controller.createPosition(sh5, 50);

        Employee em1 = Controller.createEmployee(1, "Hans");

        Cask mainCask = Controller.createCask("Frankrig", 50, "Bourbon", p1, cs1);

        Distillate testD = Controller.createDistillate("Idk", 20, 50, 50, em1, Controller.getMaltbatches());

        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        distillateFills.add(new DistillateFill(10, testD));

        ArrayList<DistillateFill> distillateFills2 = new ArrayList<>();
        distillateFills2.add(new DistillateFill(30, testD));

        Controller.createFillOnCask(LocalDate.of(2015, 10, 2), mainCask, distillateFills);

        FillOnCask foc = Controller.createFillOnCask(LocalDate.of(2015, 10, 2), mainCask, distillateFills2);
    }
}
