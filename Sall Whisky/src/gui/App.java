package gui;

import controller.MainController;
import controller.Storage;
import javafx.application.Application;
import storage.ListStorage;

public class App {
    public static void main(String[] args) {
        /*
        Storage storage = ListStorage.loadStorage();
        if (storage == null) {
            storage = new ListStorage();
            System.out.println("Empty ListStorage created");
        }
        Controller.setStorage(storage);

        if (Controller.getStudents().isEmpty()) {
            initStorage();
            System.out.println("Storage initialized");
        }

         */

        Application.launch(Gui.class);

        // ListStorage.saveStorage(storage);
    }
}
