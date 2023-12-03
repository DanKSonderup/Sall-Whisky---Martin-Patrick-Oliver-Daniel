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
        private Cask cask;
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
            double sizeInLiters = 0;
            try {
                sizeInLiters = Double.parseDouble(SizeInLiterstxf.getText());
            } catch (NumberFormatException e) {
                SizeInLiterstxf.setStyle("-fx-border-color: red;");
                return;
            }
            cask = new Cask(CountryOfOrigintxf.getText(), sizeInLiters, PreviousContenttxf.getText());
            lvwWarehouse.getItems().setAll(MainController.getAvailableWarehouses(cask));

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
    }

    public void selectedStorageItemChanged() {
         Warehouse selectedWarehouse = lvwWarehouse.getSelectionModel().getSelectedItem();
         Rack selectedRack = lvwRack.getSelectionModel().getSelectedItem();
         Shelf selectedShelf = lvwShelf.getSelectionModel().getSelectedItem();

         if (selectedWarehouse != currentlySelectedWarehouse) {
             currentlySelectedWarehouse = selectedWarehouse;
             lvwRack.getItems().setAll(MainController.getAvailableRacks(currentlySelectedWarehouse, cask));
             lvwShelf.getItems().clear();
             lvwPosition.getItems().clear();
             return;
         }
         if (selectedRack != currentlySelectedRack) {
             currentlySelectedRack = selectedRack;
             lvwShelf.getItems().clear();
             lvwPosition.getItems().clear();
             if (selectedRack != null) {
                 lvwShelf.getItems().setAll(MainController.getAvailableShelves(currentlySelectedRack, cask));
             }
             return;
         }
         if (selectedShelf != currentlySelectedShelf) {
             lvwPosition.getItems().removeAll();
             if (selectedShelf != null) {
                 currentlySelectedShelf = selectedShelf;
                 lvwPosition.getItems().setAll(MainController.getAvailablePositions(currentlySelectedShelf, cask));
             }
         }



    }
}
