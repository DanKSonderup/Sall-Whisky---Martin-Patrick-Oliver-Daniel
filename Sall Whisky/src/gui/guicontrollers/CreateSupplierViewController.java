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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateSupplierViewController implements Initializable {

    private ArrayList<CaskSupplier> caskSuppliers = new ArrayList<>();
    private ArrayList<GrainSupplier> grainSuppliers = new ArrayList<>();

    private Supplier supplier;

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


    public CreateSupplierViewController() {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CaskSupplier caskSupplier1 = new CaskSupplier("CaskTest1", "CaskVej1", "Caskland", "123");
        CaskSupplier caskSupplier2 = new CaskSupplier("CaskTest2", "CaskVej2", "Caskland", "125");
        caskSuppliers.add(caskSupplier1);
        caskSuppliers.add(caskSupplier2);

        GrainSupplier grainSupplier1 = new GrainSupplier("GrainTest1", "GrainVej1", "Grainland", "234");
        GrainSupplier grainSupplier2 = new GrainSupplier("GrainTest2", "GrainVej2", "Grainland", "237");
        grainSuppliers.add(grainSupplier1);
        grainSuppliers.add(grainSupplier2);

        lvwCaskSupplier.getItems().setAll(caskSuppliers);
        lvwGrainSupplier.getItems().setAll(grainSuppliers);

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
            /** TODO
             * Opret med MainController og ikke bare new grainSupplier
             */
            supplier = new GrainSupplier(name, address, country, vatId);
            grainSuppliers.add((GrainSupplier) supplier);
        } else if (cbbSupplier.getSelectionModel().getSelectedItem().equals("Fadleverandør")) {
            /** TODO
             * Opret med MainController og ikke bare new CaskSupplier
             */
            supplier = new CaskSupplier(name, address, country, vatId);
            caskSuppliers.add((CaskSupplier) supplier);
        }
        updateLvwCaskSupplier();
        updateLvwGrainSupplier();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
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




    private void updateLvwGrainSupplier() {
        // TODO update from storage, not grainSuppliers
        lvwGrainSupplier.getItems().setAll(grainSuppliers);
    }

    private void updateLvwCaskSupplier() {
        // TODO update from storage, not caskSuppliers
        lvwCaskSupplier.getItems().setAll(caskSuppliers);
    }


    public void selectedStorageItemChanged() {
        GrainSupplier selectedGrainSupplier = lvwGrainSupplier.getSelectionModel().getSelectedItem();
        CaskSupplier selectedCaskSupplier = lvwCaskSupplier.getSelectionModel().getSelectedItem();
    }
}
