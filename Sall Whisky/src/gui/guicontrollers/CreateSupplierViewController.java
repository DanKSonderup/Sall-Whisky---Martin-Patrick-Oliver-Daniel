package gui.guicontrollers;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.CaskSupplier;
import model.GrainSupplier;
import model.Warehouse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateSupplierViewController implements Initializable {

    private ArrayList<CaskSupplier> caskSuppliers = new ArrayList<>();
    private ArrayList<GrainSupplier> grainSuppliers = new ArrayList<>();



    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateSupplier;

    @FXML
    private Button btnDeleteCaskSupplier;

    @FXML
    private Button btnDeleteGrainSupplier;

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
    private TextField txfSupplier;

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

    }

    @FXML
    void btnCreateSupplierOnAction(ActionEvent event) {

    }


    public void selectedStorageItemChanged() {
        GrainSupplier selectedGrainSupplier = lvwGrainSupplier.getSelectionModel().getSelectedItem();
        CaskSupplier selectedCaskSupplier = lvwCaskSupplier.getSelectionModel().getSelectedItem();

    }
}
