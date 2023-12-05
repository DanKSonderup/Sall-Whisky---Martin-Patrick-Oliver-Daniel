package gui.guicontrollers;

        import controller.MainController;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;
        import model.Position;
        import model.Rack;
        import model.Shelf;
        import model.Warehouse;

        import java.net.URL;
        import java.util.ResourceBundle;

public class CRUDWarehouseViewController {

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
    private Label warehouselbl;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvwWarehouse.getItems().setAll(MainController.getStorage().getWarehouses());
        for (Warehouse warehouse : MainController.getStorage().getWarehouses()) {
           lvwRack.getItems().setAll(warehouse.getRacks());
        }

        }

    @FXML
    void btnCreateWarehouseOnAction(ActionEvent event) {

    }

    @FXML
    void btnCrudWarehouseOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteWarehouseOnAction(ActionEvent event) {

    }

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

    @FXML
    void rackAmountOnAction(ActionEvent event) {

    }

}
