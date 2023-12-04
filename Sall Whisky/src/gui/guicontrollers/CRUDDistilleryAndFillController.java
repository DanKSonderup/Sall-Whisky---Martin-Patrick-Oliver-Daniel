package gui.guicontrollers;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cask;
import model.Distillate;
import model.Employee;
import model.Maltbatch;

import java.io.IOException;

public class CRUDDistilleryAndFillController {

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
    private ComboBox<Maltbatch> pickMaltbatchComboBox;

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
        Maltbatch maltbatch = pickMaltbatchComboBox.getSelectionModel().getSelectedItem();

        
        MainController.createDistillate()
    }

}
