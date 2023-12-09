package gui.guicontrollers;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CaskSupplier;
import model.GrainSupplier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateSupplierViewController implements Initializable {

    private Stage stage;
    private Scene scene;
    @FXML
    private Button btnCRUDCask;

    @FXML
    private Button btnCRUDRawMaterial;

    @FXML
    private Button btnCRUDStorage;

    @FXML
    private Button btnCRUDSupplier;
    @FXML
    private Button btnDestillateAndFillOnCask;

    @FXML
    private Button btnStartside;

    @FXML
    private Button btnCreateSupplier;

    @FXML
    private ComboBox<String> cbbSupplier;

    @FXML
    private ListView<CaskSupplier> lvwCaskSuppliers;

    @FXML
    private ListView<GrainSupplier> lvwGrainSuppliers;

    @FXML
    private TextField txfAddress;

    @FXML
    private TextField txfCountry;

    @FXML
    private TextField txfName;

    @FXML
    private TextField txfVatId;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lvwCaskSuppliers.getItems().setAll(Controller.getCaskSuppliers());
        lvwGrainSuppliers.getItems().setAll(Controller.getGrainSuppliers());


        ChangeListener<GrainSupplier> grainSupplierChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwGrainSuppliers.getSelectionModel().selectedItemProperty().addListener(grainSupplierChangeListener);

        ChangeListener<CaskSupplier> caskSupplierChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwCaskSuppliers.getSelectionModel().selectedItemProperty().addListener(caskSupplierChangeListener);

        String suppliers[] = {"Kornleverandør", "Fadleverandør"};
        cbbSupplier.setItems(FXCollections.observableArrayList(suppliers));
    }

    @FXML
    void btnCreateSupplierOnAction(ActionEvent event) {
        String name = txfName.getText().trim();
        String address = txfAddress.getText().trim();
        String country = txfCountry.getText().trim();
        String vatId = txfVatId.getText().trim();


        if (cbbSupplier.getSelectionModel().getSelectedItem().equals("Kornleverandør")) {
            Controller.createGrainSupplier(name, address, country, vatId);

        } else if (cbbSupplier.getSelectionModel().getSelectedItem().equals("Fadleverandør")) {
            Controller.createCaskSupplier(name, address, country, vatId);
        }
        updateLvwCaskSupplier();
        updateLvwGrainSupplier();

        clearInput();
    }

    private void clearInput() {
        cbbSupplier.getSelectionModel().clearSelection();
        txfName.clear();
        txfAddress.clear();
        txfCountry.clear();
        txfVatId.clear();
    }


    /**
     * Updates the grain supplier listview
     */
    private void updateLvwGrainSupplier() {
        lvwGrainSuppliers.getItems().setAll(Controller.getGrainSuppliers());
    }

    /**
     * Updates the cask supplier listview
     */
    private void updateLvwCaskSupplier() {
        lvwCaskSuppliers.getItems().setAll(Controller.getCaskSuppliers());
    }


    public void selectedStorageItemChanged() {
        GrainSupplier selectedGrainSupplier = lvwGrainSuppliers.getSelectionModel().getSelectedItem();
        CaskSupplier selectedCaskSupplier = lvwCaskSuppliers.getSelectionModel().getSelectedItem();
    }

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
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnDestillateAndFillOnCaskOnAction(stage, scene, event);
    }
}
