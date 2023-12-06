package gui.guicontrollers;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateCaskViewController implements Initializable {
//        private Supplier supplier;


    @FXML
    private Button btnCreateCask;
    @FXML
    private Button btnFindAvailableStorageSpace;
    @FXML
    private TableColumn<Cask, String> columnCaskSupplier;
    @FXML
    private TableColumn<Cask, String> columnCountryOfOrigin;
    @FXML
    private TableColumn<Cask, Integer> columnID;
    @FXML
    private TableColumn<Cask, Integer> columnPosition;
    @FXML
    private TableColumn<Cask, String> columnPreviousContent;
    @FXML
    private TableColumn<Cask, Integer> columnRack;
    @FXML
    private TableColumn<Cask, Integer> columnShelf;
    @FXML
    private TableColumn<Cask, Double> columnSizeInLiters;
    @FXML
    private TableColumn<Cask, Integer> columnWarehouse;
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
    private TableView<Cask> tvwCreatedCasks;
    @FXML
    private TextField txfCountryOfOrigin;
    @FXML
    private TextField txfPreviousContent;
    @FXML
    private TextField txfSizeInLiters;
    private ArrayList<Cask> createdCasts = new ArrayList<>();
    private Warehouse currentlySelectedWarehouse;
    private Rack currentlySelectedRack;
    private Shelf currentlySelectedShelf;
    private double currentSizeInLiters;
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

        lvwCaskSupplier.getItems().setAll(Controller.getCaskSuppliers());

        lvwCaskSupplier.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwCaskSupplier.setStyle("-fx-border-color: transparent;");
        });

        lvwPosition.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwPosition.setStyle("-fx-border-color: transparent;");
        });

        columnID.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        columnSizeInLiters.setCellValueFactory(new PropertyValueFactory<Cask, Double>("sizeInLiters"));
        columnPreviousContent.setCellValueFactory(new PropertyValueFactory<Cask, String>("previousContent"));
        columnCountryOfOrigin.setCellValueFactory(new PropertyValueFactory<Cask, String>("countryOfOrigin"));
        columnCaskSupplier.setCellValueFactory(new PropertyValueFactory<Cask, String>("supplierName"));
        columnWarehouse.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("warehouseId"));
        columnRack.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("rackId"));
        columnShelf.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("shelfId"));
        columnPosition.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("positionId"));


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
                createdCasts.add(Controller.createCask(txfCountryOfOrigin.getText(), sizeInLiters, txfPreviousContent.getText(),
                        position, supplier));
                txfSizeInLiters.clear();
                txfPreviousContent.clear();
                txfCountryOfOrigin.clear();
                lvwWarehouse.getItems().clear();
                lvwRack.getItems().clear();
                lvwShelf.getItems().clear();
                lvwPosition.getItems().clear();
                lvwCaskSupplier.getSelectionModel().clearSelection();
                updateTvwCreatedCasks();
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
            if (sizeInLiters != currentSizeInLiters) {
                clearWareHouseListViews();
            }

            cask = new Cask(txfCountryOfOrigin.getText(), sizeInLiters, txfPreviousContent.getText());
            if (Controller.getAvailableWarehouses(cask).isEmpty()) return;
            lvwWarehouse.getItems().setAll(Controller.getAvailableWarehouses(cask));
            currentSizeInLiters = sizeInLiters;
        }
    }


    public void selectedStorageItemChanged() {

         Warehouse selectedWarehouse = lvwWarehouse.getSelectionModel().getSelectedItem();
         Rack selectedRack = lvwRack.getSelectionModel().getSelectedItem();
         Shelf selectedShelf = lvwShelf.getSelectionModel().getSelectedItem();

         if (selectedWarehouse == null) return;
         if (selectedWarehouse != currentlySelectedWarehouse) {
             currentlySelectedWarehouse = selectedWarehouse;
             lvwRack.getItems().setAll(Controller.getAvailableRacks(currentlySelectedWarehouse, cask));
             lvwShelf.getItems().clear();
             lvwPosition.getItems().clear();
             return;
         }
         if (selectedRack != currentlySelectedRack) {
             currentlySelectedRack = selectedRack;
             lvwShelf.getItems().clear();
             lvwPosition.getItems().clear();
             if (selectedRack != null) {
                 lvwShelf.getItems().setAll(Controller.getAvailableShelves(currentlySelectedRack, cask));
             }
             return;
         }
         if (selectedShelf != currentlySelectedShelf) {
             lvwPosition.getItems().removeAll();
             if (selectedShelf != null) {
                 currentlySelectedShelf = selectedShelf;
                 lvwPosition.getItems().setAll(Controller.getAvailablePositions(currentlySelectedShelf, cask));
             }
         }
    }

    private void clearWareHouseListViews() {
        lvwPosition.getItems().clear();
        lvwShelf.getItems().clear();
        lvwRack.getItems().clear();
        lvwPosition.getItems().clear();
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

    private void updateTvwCreatedCasks() {
        tvwCreatedCasks.getItems().setAll(createdCasts);
    }
}
