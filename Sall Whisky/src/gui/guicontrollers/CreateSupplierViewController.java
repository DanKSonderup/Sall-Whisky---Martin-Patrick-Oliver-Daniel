package gui.guicontrollers;

import controller.MainController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CaskSupplier;
import model.GrainSupplier;
import model.Supplier;
import model.Warehouse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private Button btnCancel;

    @FXML
    private Button btnCreateSupplier;

    @FXML
    private Button btnDeleteCaskSupplier;

    @FXML
    private Button btnDeleteGrainSupplier;

    @FXML
    private ComboBox<String> cbbSupplier;

    @FXML
    private ListView<CaskSupplier> lvwCaskSupplier;

    @FXML
    private ListView<GrainSupplier> lvwGrainSupplier;

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

        lvwCaskSupplier.getItems().setAll(MainController.getCaskSuppliers());
        lvwGrainSupplier.getItems().setAll(MainController.getGrainSuppliers());


        ChangeListener<GrainSupplier> grainSupplierChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwGrainSupplier.getSelectionModel().selectedItemProperty().addListener(grainSupplierChangeListener);

        ChangeListener<CaskSupplier> caskSupplierChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwCaskSupplier.getSelectionModel().selectedItemProperty().addListener(caskSupplierChangeListener);

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
            MainController.createGrainSupplier(name, address, country, vatId);
            // HELLO

        } else if (cbbSupplier.getSelectionModel().getSelectedItem().equals("Fadleverandør")) {
            MainController.createCaskSupplier(name, address, country, vatId);
        }
        updateLvwCaskSupplier();
        updateLvwGrainSupplier();

        clearInput();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearInput();
    }

    private void clearInput() {
        cbbSupplier.getSelectionModel().clearSelection();
        txfName.clear();
        txfAddress.clear();
        txfCountry.clear();
        txfVatId.clear();
    }

    @FXML
    void btnDeleteGrainSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteCaskSupplierOnAction(ActionEvent event) {

    }


    /**
     * Updates the grain supplier listview
     */
    private void updateLvwGrainSupplier() {
        lvwGrainSupplier.getItems().setAll(MainController.getGrainSuppliers());
    }

    /**
     * Updates the cask supplier listview
     */
    private void updateLvwCaskSupplier() {
        lvwCaskSupplier.getItems().setAll(MainController.getCaskSuppliers());
    }


    public void selectedStorageItemChanged() {
        GrainSupplier selectedGrainSupplier = lvwGrainSupplier.getSelectionModel().getSelectedItem();
        CaskSupplier selectedCaskSupplier = lvwCaskSupplier.getSelectionModel().getSelectedItem();
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
