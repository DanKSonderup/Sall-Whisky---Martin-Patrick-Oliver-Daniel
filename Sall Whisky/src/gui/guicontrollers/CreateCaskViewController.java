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


    @FXML
    private Button btnCreateCask;
    @FXML
    private Button btnFindAvailableStorageSpace;
    @FXML
    private ListView<CaskSupplier> lvwCaskSupplier;
    @FXML
    private ListView<Position> lvwPosition;
    @FXML
    private ListView<Rack> lvwRack;
    @FXML
    private ListView<Shelf> lvwShelf;
    @FXML
    private ListView<Warehouse> lvwWarehouse;
    @FXML
    private TextField txfCountryOfOrigin;
    @FXML
    private TextField txfPreviousContent;
    @FXML
    private TextField txfSizeInLiters;
    private ArrayList<Warehouse> warehouses = new ArrayList<>();
    private Warehouse currentlySelectedWarehouse;
    private Rack currentlySelectedRack;
    private Shelf currentlySelectedShelf;
    private Cask cask;

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

        lvwCaskSupplier.getItems().setAll(MainController.getCaskSuppliers());

        lvwCaskSupplier.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwCaskSupplier.setStyle("-fx-border-color: transparent;");
        });

    }

    /**
     *
     * @param event
     */
        @FXML
        void btnCreateCaskOnAction(ActionEvent event) {
            boolean missingInfo = false;
            try {
                double sizeInLiters = Double.parseDouble(txfSizeInLiters.getText());
            } catch (NumberFormatException exception) {
                missingInfo = true;
                txfSizeInLiters.setStyle("-fx-border-color: red;");
                txfSizeInLiters.setOnMouseClicked(e -> {txfSizeInLiters.setStyle("-fx-border-color: transparent");});
            }
        }


    /**
     *
     * @param event
     */
    @FXML
    void btnFindAvailableStorageSpaceOnAction(ActionEvent event) {
        boolean missingInfo = false;
        double sizeInLiters = 0;
        try {
            sizeInLiters = Double.parseDouble(txfSizeInLiters.getText());
        } catch (NumberFormatException exception) {
            missingInfo = true;
            txfSizeInLiters.setStyle("-fx-border-color: red;");
            txfSizeInLiters.setOnMouseClicked(e -> {txfSizeInLiters.setStyle("-fx-border-color: transparent");});
        }
        if (lvwCaskSupplier.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwCaskSupplier.setStyle("-fx-border-color: red;");
        }

        if (!missingInfo) {
            cask = new Cask(txfCountryOfOrigin.getText(), sizeInLiters, txfPreviousContent.getText());
            lvwWarehouse.getItems().setAll(MainController.getAvailableWarehouses(cask));
        }
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
