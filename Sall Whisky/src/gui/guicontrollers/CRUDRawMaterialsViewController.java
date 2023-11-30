package gui.guicontrollers;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Field;
import model.Grain;
import model.GrainSupplier;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDRawMaterialsViewController implements Initializable {

    @FXML
    private Button btnCreateField;

    @FXML
    private Button btnCreateGrain;

    @FXML
    private Button btnCreateGrainSupplier;

    @FXML
    private Button btnCreateMaltbatch;

    @FXML
    private Button btnDeleteField;

    @FXML
    private Button btnDeleteGrain;

    @FXML
    private Button btnRemoveMaltbatch;

    @FXML
    private ComboBox<GrainSupplier> cbbPickGrainSupplier;

    @FXML
    private ListView<Field> lvwFields;

    @FXML
    private ListView<Grain> lvwGrains;

    @FXML
    private TextArea txaCultivationDescription;

    @FXML
    private TextArea txaFieldDescription;

    @FXML
    private TextArea txaMaltbatchDescription;

    @FXML
    private TextField txfFieldName;

    @FXML
    private TextField txfGrainType;

    @FXML
    void btnCreateFieldOnAction(ActionEvent event) {
        MainController.createField(txfFieldName.getText(), txaFieldDescription.getText());

    }

    @FXML
    void btnCreateGrainOnAction(ActionEvent event) {

    }

    @FXML
    void btnCreateMaltbatchOnAction(ActionEvent event) {

    }

    @FXML
    void btnCreateSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteFieldOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteGrainOnAction(ActionEvent event) {

    }

    @FXML
    void btnRemoveMaltbatchOnAction(ActionEvent event) {

    }

    @FXML
    void cbbPickGrainSupplierOnSelection(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvwFields.getItems().setAll(MainController.getFields());
        lvwGrains.getItems().setAll(MainController.getGrains());
    }
}
