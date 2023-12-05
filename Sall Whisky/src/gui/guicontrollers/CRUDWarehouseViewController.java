package gui.guicontrollers;

        import controller.MainController;
        import javafx.beans.value.ChangeListener;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
        import model.*;
        import java.net.URL;
        import java.util.ResourceBundle;

public class CRUDWarehouseViewController implements Initializable {

    @FXML
    private AnchorPane ap_Pane1;

    @FXML
    private Button btnCRUDCask;

    @FXML
    private Button btnCRUDRawMaterial;

    @FXML
    private Button btnCRUDSupplier;

    @FXML
    private Button btnCRUDWarehouse;

    @FXML
    private Button btnCreateWarehouse;

    @FXML
    private Button btnDeleteWarehouse;

    @FXML
    private Button btnDestillateAndFillOnCask;

    @FXML
    private Button btnStartside;

    @FXML
    private ListView<Position> lvwPosition;

    @FXML
    private ListView<Rack> lvwRack;

    @FXML
    private ListView<Shelf> lvwShelf;

    @FXML
    private ListView<Warehouse> lvwWarehouse;

    @FXML
    private Label rackAmountlbl;

    @FXML
    private TextField rackAmounttxf;

    @FXML
    private Label warehouseAddresslbl;

    @FXML
    private TextField warehouseAddresstxf;

    @FXML
    private Label warehouselbl;
    private Rack rack;
    private Shelf shelf;
    private Position position;

    @FXML
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) {

    }

    @FXML
    void btnRawMaterialOnAction(ActionEvent event) {

    }

    @FXML
    void btnStartSideOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
    }

    private Warehouse currentlySelectedWarehouse;
    private Rack currentlySelectedRack;
    private Shelf currentlySelectedShelf;
    private Warehouse warehouse;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChangeListener<Warehouse> warehouseChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwWarehouse.getSelectionModel().selectedItemProperty().addListener(warehouseChangeListener);

        ChangeListener<Rack> rackChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwRack.getSelectionModel().selectedItemProperty().addListener(rackChangeListener);

        ChangeListener<Shelf> shelfChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwShelf.getSelectionModel().selectedItemProperty().addListener(shelfChangeListener);

        ChangeListener<Position> positionChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwPosition.getSelectionModel().selectedItemProperty().addListener(positionChangeListener);

        lvwWarehouse.getItems().setAll(MainController.getStorage().getWarehouses());

        }

    @FXML
    void btnCreateWarehouseOnAction(ActionEvent event) {
        boolean missingInfo = canParseToDouble(rackAmounttxf);

        if (!missingInfo) {
            warehouse = MainController.createWarehouse(warehouseAddresstxf.getText());
            for (int i = 0; i < Integer.parseInt(rackAmounttxf.getText()); i++) {
                rack = MainController.createRack(warehouse);
                for (int j = 0; j < 5; j++) {
                    shelf = MainController.createShelf(rack);
                    for (int k = 0; k < 5; k++) {
                        position = MainController.createPosition(shelf, 500);
                    }
                }
            }
        }
        updateWarehouses();
        rackAmounttxf.clear();
        warehouseAddresstxf.clear();

    }

    @FXML
    void btnCrudWarehouseOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteWarehouseOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void rackAmountOnAction(ActionEvent event) {

    }

    public void selectedStorageItemChanged() {
        Warehouse selectedWarehouse = lvwWarehouse.getSelectionModel().getSelectedItem();
        Rack selectedRack = lvwRack.getSelectionModel().getSelectedItem();
        Shelf selectedShelf = lvwShelf.getSelectionModel().getSelectedItem();

        if (selectedWarehouse == null) return;
        if (selectedWarehouse != currentlySelectedWarehouse) {
            currentlySelectedWarehouse = selectedWarehouse;
            lvwRack.getItems().setAll(currentlySelectedWarehouse.getRacks());
            lvwShelf.getItems().clear();
            lvwPosition.getItems().clear();
            return;
        }
        if (selectedRack != currentlySelectedRack) {
            currentlySelectedRack = selectedRack;
            lvwShelf.getItems().clear();
            lvwPosition.getItems().clear();
            if (selectedRack != null) {
                lvwShelf.getItems().setAll(currentlySelectedRack.getShelves());
            }
            return;
        }
        if (selectedShelf != currentlySelectedShelf) {
            lvwPosition.getItems().removeAll();
            if (selectedShelf != null) {
                currentlySelectedShelf = selectedShelf;
                lvwPosition.getItems().setAll(currentlySelectedShelf.getPositions());
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

    private void updateWarehouses() {
        lvwWarehouse.getItems().setAll(MainController.getStorage().getWarehouses());
    }
}

