package gui.guicontrollers;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCaskViewController implements Initializable {
//        private Supplier supplier;

        private ArrayList<Warehouse> warehouses = new ArrayList<>();
        private Warehouse currentlySelectedWarehouse;
        private Rack currentlySelectedRack;
        private Shelf currentlySelectedShelf;
        @FXML
        private Label CountryOfOriginlbl;

        @FXML
        private TextField CountryOfOrigintxf;

        @FXML
        private Label PreviousContentlbl;

        @FXML
        private TextField PreviousContenttxf;

        @FXML
        private Label SizeInLiterslbl;

        @FXML
        private TextField SizeInLiterstxf;

        @FXML
        private Label Supplierlbl;

        @FXML
        private ListView<?> Supplierlvw;

        @FXML
        private Button btnCreateCask;

        @FXML
        private Button btnFindWarehousePosition;

        @FXML
        private Button btnPickSupplier;

        @FXML
        private Button btnPickWarehousePosition;

        @FXML
        private ListView<Position> lvwPosition;

        @FXML
        private ListView<Rack> lvwRack;

        @FXML
        private ListView<Shelf> lvwShelf;

        @FXML
        private ListView<Warehouse> lvwWarehouse;

        @FXML
        void btnCreateCask(ActionEvent event) {

        }



        @FXML
        void btnFindWarehousePosition(ActionEvent event) {
            double sizeInLiters = Double.parseDouble(SizeInLiterstxf.getText());
            Cask cask = new Cask(CountryOfOrigintxf.getText(), sizeInLiters, PreviousContenttxf.getText());
            lvwWarehouse.getItems().setAll(warehouses);

            /*
            List<Position> list = new ArrayList<>();
            double sizeInLiters = Double.parseDouble(SizeInLiterstxf.getText());
            Cask cask = new Cask(CountryOfOrigintxf.getText(), sizeInLiters, PreviousContenttxf.getText());

            MainController.getAvailableWarehouses(cask);
            for (Warehouse warehouse : MainController.getAvailableWarehouses(cask)) {
                for (Rack rack : MainController.getAvailableRacks(warehouse, cask)) {
                    for (Shelf shelf : MainController.getAvailableShelves(rack, cask)) {
                        for (Position position : MainController.getAvailablePositions(shelf, cask)) {
                            list.add(position);
                        }
                    }
                }
            }

             */
        }

        @FXML
        void btnPickSupplier(ActionEvent event) {
//            this.supplier = Supplierlvw.getSelectionModel().getSelectedItem();
        }

        @FXML
        void btnPickWarehousePosition(ActionEvent event) {

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChangeListener<Warehouse> warehouseChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwWarehouse.getSelectionModel().selectedItemProperty().addListener(warehouseChangeListener);

        ChangeListener<Rack> rackChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwRack.getSelectionModel().selectedItemProperty().addListener(rackChangeListener);

        ChangeListener<Shelf> shelfChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwShelf.getSelectionModel().selectedItemProperty().addListener(shelfChangeListener);

        ChangeListener<Position> positionChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwPosition.getSelectionModel().selectedItemProperty().addListener(positionChangeListener);

        Warehouse lager1 = new Warehouse(1, "Test1");
        Warehouse lager2 = new Warehouse(2, "Test2");
        warehouses.add(lager1);
        warehouses.add(lager2);
        Rack rack1 = new Rack(1);
        Rack rack2 = new Rack(2);
        Rack rack3 = new Rack(3);
        lager1.addRack(rack1);
        lager2.addRack(rack2);
        lager2.addRack(rack3);

        Shelf shelf1 = new Shelf(1);
        Shelf shelf2 = new Shelf(2);

        rack1.addShelf(shelf1);
        rack2.addShelf(shelf2);

        Position pos1 = new Position(1, 30);
        Position pos2 = new Position(2, 50);
        Position pos3 = new Position(3, 40);

        shelf1.addPosition(pos1);
        shelf1.addPosition(pos2);
        shelf2.addPosition(pos3);


    }

    public void selectedStorageItemChanged() {
         Warehouse selectedWarehouse = lvwWarehouse.getSelectionModel().getSelectedItem();
         Rack selectedRack = lvwRack.getSelectionModel().getSelectedItem();
         Shelf selectedShelf = lvwShelf.getSelectionModel().getSelectedItem();

         if (selectedWarehouse != currentlySelectedWarehouse) {
             lvwRack.getItems().removeAll();
             lvwShelf.getItems().removeAll();
             lvwPosition.getItems().removeAll();
             currentlySelectedWarehouse = selectedWarehouse;
             lvwRack.getItems().setAll(currentlySelectedWarehouse.getRacks());
             return;
         }
         if (selectedRack != currentlySelectedRack) {
             lvwShelf.getItems().removeAll();
             lvwPosition.getItems().removeAll();
             if (selectedRack != null) {
                 currentlySelectedRack = selectedRack;
                 lvwShelf.getItems().setAll(currentlySelectedRack.getShelves());
                 return;
             }
         }
         if (selectedShelf != currentlySelectedShelf) {
             lvwPosition.getItems().removeAll();
             if (selectedShelf != null) {
                 currentlySelectedShelf = selectedShelf;
                 lvwPosition.getItems().setAll(currentlySelectedShelf.getPositions());
             }
         }

         /*
         if (selectedWarehouse != null) {
             currentlySelectedWarehouse = selectedWarehouse;
             lvwRack.getItems().setAll(currentlySelectedWarehouse.getRacks());
         }
         if (selectedRack != null) {
             currentlySelectedRack = selectedRack;
             lvwShelf.getItems().setAll(currentlySelectedRack.getShelves());
         }
         if (selectedShelf != null) {
             currentlySelectedShelf = selectedShelf;
             lvwPosition.getItems().setAll(currentlySelectedShelf.getPositions());
         }

          */
    }
}
