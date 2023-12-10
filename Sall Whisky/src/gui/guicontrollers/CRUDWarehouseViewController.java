package gui.guicontrollers;

        import controller.Controller;
        import javafx.beans.value.ChangeListener;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;
        import model.*;

        import java.io.IOException;
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
    private TextField txfRackAmount;
    @FXML
    private TextField txfPositionAmount;
    @FXML
    private TextField txfShelfAmount;
    @FXML
    private TextField txfWarehouseAddress;
    private Stage stage;
    private Scene scene;
    private Rack rack;
    private Shelf shelf;
    private Position position;
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

        lvwWarehouse.getItems().setAll(Controller.getStorage().getWarehouses());

        }

        /** Pre: Racks, Shelves and Positions amount < 10 */
    @FXML
    void btnCreateWarehouseOnAction(ActionEvent event) {
        TextField[] txfFields = {txfRackAmount, txfPositionAmount, txfShelfAmount};
        for (int i = 0; i < txfFields.length; i++) {
            if (canParseToInteger(txfFields[i])) {
                return;
            }
        }
        boolean missingInfo = false;

        if (txfWarehouseAddress.getText().isEmpty()) {
            missingInfo = true;
            txfWarehouseAddress.setStyle("-fx-border-color: red;");
            txfWarehouseAddress.setOnMouseClicked(e -> {
                txfWarehouseAddress.setStyle("-fx-border-color: transparent;");});
            return;
        }
        if (Integer.parseInt(txfRackAmount.getText()) > 10) {
            missingInfo = true;
            txfRackAmount.setStyle("-fx-border-color: red;");
            txfRackAmount.setOnMouseClicked(e -> {
                txfRackAmount.setStyle("-fx-border-color: transparent;");});
            return;
        }
            if (Integer.parseInt(txfShelfAmount.getText()) > 10) {
                missingInfo = true;
                txfShelfAmount.setStyle("-fx-border-color: red;");
                txfShelfAmount.setOnMouseClicked(e -> {
                    txfShelfAmount.setStyle("-fx-border-color: transparent;");});
                return;
            }
                if (Integer.parseInt(txfPositionAmount.getText()) > 10) {
                    missingInfo = true;
                    txfPositionAmount.setStyle("-fx-border-color: red;");
                    txfPositionAmount.setOnMouseClicked(e -> {
                        txfPositionAmount.setStyle("-fx-border-color: transparent;");});
                    return;
                }

        if (!missingInfo) {
            warehouse = Controller.createWarehouse(txfWarehouseAddress.getText());
            for (int i = 0; i < Integer.parseInt(txfRackAmount.getText()); i++) {
                rack = Controller.createRack(warehouse);
                for (int j = 0; j < Integer.parseInt(txfShelfAmount.getText()); j++) {
                    shelf = Controller.createShelf(rack);
                    for (int k = 0; k < Integer.parseInt(txfPositionAmount.getText()); k++) {
                        position = Controller.createPosition(shelf, 500);
                    }
                }
            }
        }
        updateWarehouses();
        clearInput();
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
    private boolean canParseToInteger(TextField txf) {
        boolean cannotParse = false;
        try {
            double returnValue = Integer.parseInt(txf.getText().trim());
        } catch (NumberFormatException exception) {
            cannotParse = true;
            txf.setStyle("-fx-border-color: red;");
            txf.setOnMouseClicked(e -> {txf.setStyle("-fx-border-color: transparent");});
        }
        return cannotParse;
    }

    private void updateWarehouses() {
        lvwWarehouse.getItems().setAll(Controller.getStorage().getWarehouses());
    }

    private void clearInput() {
        txfRackAmount.clear();
        txfShelfAmount.clear();
        txfPositionAmount.clear();
        txfWarehouseAddress.clear();
    }

    @FXML
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnDestillateAndFillOnCaskOnAction(stage, scene, event);
    }

    @FXML
    void btnRawMaterialOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnRawMaterial(stage, scene, event);
    }

    @FXML
    void btnStartSideOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnStartView(stage, scene, event);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCRUDSupplier(stage, scene, event);
    }

    @FXML
    void btnCrudCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCrudCask(stage, scene, event);
    }
}

