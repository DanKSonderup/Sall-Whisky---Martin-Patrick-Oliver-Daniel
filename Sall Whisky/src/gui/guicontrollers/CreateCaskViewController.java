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

        lvwPosition.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwPosition.setStyle("-fx-border-color: transparent;");
        });


    }

    /**
     * Creates a new cask
     * If the user has not entered a size for the cask prompt the user to enter one
     * If the user has not selected a position for the cask prompt the user to select one
     * If the user has not selected a supplier for the cask prompt the user to select one
     * @param event
     */
        @FXML
        void btnCreateCaskOnAction(ActionEvent event) {
            boolean missingInfo = canParseToDouble(txfSizeInLiters);
            if (lvwPosition.getSelectionModel().isEmpty()) {
                missingInfo = true;
                lvwPosition.setStyle("-fx-border-color: red;");
            }
            if (lvwCaskSupplier.getSelectionModel().isEmpty()) {
                missingInfo = true;
                lvwCaskSupplier.setStyle("-fx-border-color: red;");
            }

            if (!missingInfo) {
                double sizeInLiters = Double.parseDouble(txfSizeInLiters.getText());
                Position position = lvwPosition.getSelectionModel().getSelectedItem();
                CaskSupplier supplier = lvwCaskSupplier.getSelectionModel().getSelectedItem();
                MainController.createCask(txfCountryOfOrigin.getText(), sizeInLiters, txfPreviousContent.getText(),
                        position, supplier);
                txfSizeInLiters.clear();
                txfPreviousContent.clear();
                txfCountryOfOrigin.clear();
                lvwWarehouse.getItems().clear();
                lvwRack.getItems().clear();
                lvwShelf.getItems().clear();
                lvwPosition.getItems().clear();
                lvwCaskSupplier.getSelectionModel().clearSelection();
            }
        }


    /**
     * Takes the size of the cask and finds all warehouses with space for the cask
     * If the size entered is not a double, prompt the user to enter a double
     */
    @FXML
    void btnFindAvailableStorageSpaceOnAction(ActionEvent event) {
        boolean missingInfo = canParseToDouble(txfSizeInLiters);

        if (!missingInfo) {
            double sizeInLiters = Double.parseDouble(txfSizeInLiters.getText());
            cask = new Cask(txfCountryOfOrigin.getText(), sizeInLiters, txfPreviousContent.getText());
            lvwWarehouse.getItems().setAll(MainController.getAvailableWarehouses(cask));
        }
    }



    public void selectedStorageItemChanged() {

         Warehouse selectedWarehouse = lvwWarehouse.getSelectionModel().getSelectedItem();
         Rack selectedRack = lvwRack.getSelectionModel().getSelectedItem();
         Shelf selectedShelf = lvwShelf.getSelectionModel().getSelectedItem();

         if (selectedWarehouse == null) return;
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

    private boolean canParseToDouble(TextField txf) {
        boolean cannotParse = false;
        try {
            double returnValue = Double.parseDouble(txf.getText().trim());
        } catch (NumberFormatException exception) {
            cannotParse = true;
            txf.setStyle("-fx-border-color: red;");
            txf.setOnMouseClicked(e -> {txf.setStyle("-fx-border-color: transparent");});
        }
        return cannotParse;
    }
}
