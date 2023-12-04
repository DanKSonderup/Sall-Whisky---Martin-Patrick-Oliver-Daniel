package gui.guicontrollers;

import controller.MainController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cask;
import model.Distillate;
import model.Employee;
import model.Maltbatch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CRUDDistilleryAndFillController implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private Label alcoholPercentagelbl;

    @FXML
    private TextField alcoholPercentagetxf;

    @FXML
    private Label amountOfLiterlbl;

    @FXML
    private TextField amountOfLiterstxf;

    @FXML
    private Label availableCaskslbl;

    @FXML
    private ListView<Cask> availableCaskslvw;

    @FXML
    private Button btnCRUDCask;

    @FXML
    private Button btnCRUDRawMaterial;

    @FXML
    private Button btnCRUDStorage;

    @FXML
    private Button btnCRUDSupplier;

    @FXML
    private Button btnCreateDistillate;

    @FXML
    private Button btnDestillateAndFillOnCask;

    @FXML
    private Button btnFillOnCask;

    @FXML
    private Button btnStartside;

    @FXML
    private Label distillatelbl;

    @FXML
    private ListView<Distillate> distillatelvw;

    @FXML
    private Label distillationTimelbl;

    @FXML
    private TextField distillationTimetxf;

    @FXML
    private Label newMakeNrlbl;

    @FXML
    private TextField newMakeNrtxf;

    @FXML
    private ComboBox<Employee> pickEmployeeComboBox;

    @FXML
    private Label pickEmployeelbl;

    @FXML
    private ListView<Maltbatch> lvwMaltBatches;

    @FXML
    private Label pickMaltbatchlbl;

    @FXML
    private Label rygematerialelbl;

    @FXML
    private TextField rygematerialetxf;

    @FXML
    private Label typeLiterAmountlbl;

    @FXML
    private TextField typeLiterAmounttxf;

    @FXML
    void btnCrudCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCrudCask(stage, scene, event);
    }

    @FXML
    void btnCrudStorageOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCrudStorage(stage, scene, event);
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
    void btnCreateDistillateOnAction(ActionEvent event) {
        String newMakenr = newMakeNrtxf.getText().trim();
        double distillationTime;
        double alcoholPercentage;
        double amountInLiters;
        Employee employee = pickEmployeeComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Maltbatch> maltBatches = lvwMaltBatches.getSelectionModel().getSelectedItems();

        distillationTime = txfParseDouble(distillationTimetxf);
        alcoholPercentage = txfParseDouble(alcoholPercentagetxf);
        amountInLiters = txfParseDouble(amountOfLiterstxf);

        if (distillationTime < 0 || alcoholPercentage < 0 || amountInLiters < 0) {
            return;
        }

        if (employee == null) {
            pickEmployeeComboBox.setStyle("-fx-border-color: red;");
            return;
        }
        if (maltBatches.size() == 0) {
            lvwMaltBatches.setStyle("-fx-border-color: red;");
        }

        MainController.createDistillate(newMakenr, distillationTime, alcoholPercentage, amountInLiters, employee, maltBatches);
        updateControls();
    }

    @FXML
    void btnFillOnCaskOnAction(ActionEvent event) {

    }

    private double txfParseDouble(TextField txf) {
        double returnValue = -1.0;
        try {
            returnValue = Double.parseDouble(txf.getText().trim());
        } catch (NumberFormatException e) {
            txf.setStyle("-fx-border-color: red;");
        }
        return returnValue;
    }

    private void updateControls() {
        distillatelvw.getItems().setAll(MainController.getDistillates());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        distillatelvw.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvwMaltBatches.getItems().setAll(MainController.getMaltbatches());
        pickEmployeeComboBox.getItems().setAll(MainController.getEmployees());
    }
}
