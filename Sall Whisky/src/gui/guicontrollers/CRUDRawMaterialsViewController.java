package gui.guicontrollers;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDRawMaterialsViewController implements Initializable {

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
    private Button btnDestillateAndFillOnCask;
    @FXML
    private Button btnRemoveMaltbatch;
    @FXML
    private Button btnStartside;
    @FXML
    private Button btnUpdateField;
    @FXML
    private Button btnUpdateGrain;
    @FXML
    private Button btnUpdateMaltbatch;
    @FXML
    private ComboBox<GrainSupplier> cbbPickGrainSupplier;
    @FXML
    private ListView<Field> lvwFields;
    @FXML
    private ListView<Grain> lvwGrains;
    @FXML
    private ListView<Maltbatch> lvwMaltbatches;
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
    private TextField txfMaltbatchName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvwFields.getItems().setAll(MainController.getFields());
        lvwGrains.getItems().setAll(MainController.getGrains());
        lvwMaltbatches.getItems().setAll(MainController.getMaltbatches());
        cbbPickGrainSupplier.setItems(FXCollections.observableArrayList(MainController.getGrainSuppliers()));

        lvwFields.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwFields.setStyle("-fx-border-color: transparent;");
            updateLvwGrains();
        });

        lvwGrains.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            lvwGrains.setStyle("-fx-border-color: transparent;");
            updateLvwMaltbatches();
        });

        cbbPickGrainSupplier.valueProperty().addListener((o, ov, nv) -> {
            cbbPickGrainSupplier.setStyle("-fx-border-color: transparent;");
        });

        /**
         * If no field (or grain) is selected, have the delete button be disabled
         */
    }


    /**
     * Creates a new field and updates the listview
     * If Name is empty prompt the user to enter a name
     */
    @FXML
    void btnCreateFieldOnAction(ActionEvent event) {
        if (txfFieldName.getText().isEmpty()) {
            txfFieldName.setStyle("-fx-border-color: red;");
            txfFieldName.setOnMouseClicked(e -> {txfFieldName.setStyle("");});
        }
        else {
            MainController.createField(txfFieldName.getText(), txaFieldDescription.getText());
            txfFieldName.setText("");
            txaFieldDescription.setText("");
            updateLvwFields();
        }
    }

    /**
     * Updates the selected field with name and/or description
     * If name is empty, prompt the user to enter a name
     * Update the listview.
     */
    @FXML
    void btnUpdateFieldOnAction(ActionEvent event) {
        boolean missingInfo = false;
        if (lvwFields.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwFields.setStyle("-fx-border-color: red;");
        }
        if (txfFieldName.getText().isEmpty()) {
            missingInfo = true;
            txfFieldName.setStyle("-fx-border-color: red;");
            txfFieldName.setOnMouseClicked(e -> {txfFieldName.setStyle("");});
        }
        if (!missingInfo) {
            Field field = lvwFields.getSelectionModel().getSelectedItem();
            field.setName(txfFieldName.getText());
            field.setDescription(txaFieldDescription.getText());
            txfFieldName.setText("");
            txaFieldDescription.setText("");
            updateLvwFields();
        }
    }

    /**
     * Deletes a field.
     * If the user has not selected any fields show an error
     * If the field has a connection to any grain-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     * Checks all grain to see if the field is connected to any, if so, abort deletion
     */
    @FXML
    void btnDeleteFieldOnAction(ActionEvent event) {
        Field field = lvwFields.getSelectionModel().getSelectedItem();
        if (lvwFields.getSelectionModel().isEmpty()) {
            lvwFields.setStyle("-fx-border-color: red;");
        }
        else if (isConnectedToGrain(field)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("Mark kan ikke slettes");
            alert.setContentText("Denne mark kan ikke slettes, da der findes korn i systemet" +
                    ", som er tilknyttet denne mark. Slet først disse korn og prøv igen.");
            alert.show();
        }
        else {
            ButtonType choice = showDeleteConfirmationDialog(field);
            if (choice.getText() == "Ja") {
                MainController.removeField(field);
            }
        }

        updateLvwFields();
    }

    /**
     * Creates a new Grain and updates the listview
     * If graintype is empty prompt the user to enter a graintype
     * If cultivationDescription is empty set to ""
     * If no field selected, prompt user to select a field
     */
    @FXML
    void btnCreateGrainOnAction(ActionEvent event) {
        boolean missingInfo = false;
        if (lvwFields.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwFields.setStyle("-fx-border-color: red;");
        }
        if (txfGrainType.getText().isEmpty()) {
            missingInfo = true;
            txfGrainType.setStyle("-fx-border-color: red;");
            txfGrainType.setOnMouseClicked(e -> {txfGrainType.setStyle("-fx-border-color: transparent;");});
        }
        if (cbbPickGrainSupplier.getSelectionModel().isEmpty()) {
            missingInfo = true;
            cbbPickGrainSupplier.setStyle("-fx-border-color: red;");
        }

        if (!missingInfo) {
            GrainSupplier grainSupplier = cbbPickGrainSupplier.getValue();
            Field field = lvwFields.getSelectionModel().getSelectedItem();
            MainController.createGrain(txfGrainType.getText(), grainSupplier,
                    txaCultivationDescription.getText(), field);
            txfGrainType.setText("");
            txaCultivationDescription.setText("");
        }

        updateLvwGrains();
    }

    /**
     * Updates the selected grain with graintype and/or cultivationDescription
     * If no grain is selected, prompt the user to select one
     * If graintype is empty, prompt the user to enter a graintype
     * Update the listview.
     */
    @FXML
    void btnUpdateGrainOnAction(ActionEvent event) {
        boolean missingInfo = false;
        if (lvwGrains.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwGrains.setStyle("-fx-border-color: red;");
        }
        if (txfGrainType.getText().isEmpty()) {
            missingInfo = true;
            txfGrainType.setStyle("-fx-border-color: red;");
            txfGrainType.setOnMouseClicked(e -> txfGrainType.setStyle("-fx-border-color: transparent;"));
        }
        if (!missingInfo) {
            Grain grain = lvwGrains.getSelectionModel().getSelectedItem();
            grain.setGrainType(txfGrainType.getText());
            grain.setCultivationDescription(txaCultivationDescription.getText());
            updateLvwGrains();
        }
    }

    /**
     * Deletes a grain.
     * If the grain has a connection to any maltbatch-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     */
    @FXML
    void btnDeleteGrainOnAction(ActionEvent event) {
        Grain grain = lvwGrains.getSelectionModel().getSelectedItem();
        if (lvwGrains.getSelectionModel().isEmpty()) {
            lvwGrains.setStyle("-fx-border-color: red;");
        }
        else if (isConnectedToMaltbatch(grain)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("Korn kan ikke slettes");
            alert.setContentText("Dette korn kan ikke slettes, da der findes maltbatches i systemet" +
                    ", som er tilknyttet dette korn. Slet først disse maltbatches og prøv igen.");
            alert.show();
        }
        else {
            ButtonType choice = showDeleteConfirmationDialog(grain);
            if (choice.getText() == "Ja") {
                MainController.removeGrain(grain);
            }
        }
        updateLvwGrains();
    }

    /**
     * Creates a new maltbatch and updates the listview
     * If name is empty, prompt the user to enter a name
     * If no grain selected, prompt the user to select a grain
     */
    @FXML
    void btnCreateMaltbatchOnAction(ActionEvent event) {
        boolean missingInfo = false;
        if (lvwGrains.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwGrains.setStyle("-fx-border-color: red;");
        }
        if (txfMaltbatchName.getText().isEmpty()) {
            missingInfo = true;
            txfMaltbatchName.setStyle("-fx-border-color: red;");
            txfMaltbatchName.setOnMouseClicked(e -> {txfMaltbatchName.setStyle("-fx-border-color: transparent;");});
        }
        if (!missingInfo) {
            Grain grain = lvwGrains.getSelectionModel().getSelectedItem();
            MainController.createMaltbatch(txfMaltbatchName.getText(), txaMaltbatchDescription.getText(), grain);
            txfMaltbatchName.setText("");
            txaMaltbatchDescription.setText("");
        }
        updateLvwMaltbatches();
    }

    /**
     * Updates the selected maltbatch with name and/or description
     * If no maltbatch selected, prompt the user to select one
     * If name is empty, prompt the user to enter a name
     * Update the listview.
     */
    @FXML
    void btnUpdateMaltbatchOnAction(ActionEvent event) {
        boolean missingInfo = false;
        if (lvwMaltbatches.getSelectionModel().isEmpty()) {
            missingInfo = true;
            lvwMaltbatches.setStyle("-fx-border-color: red;");
        }
        if (txfMaltbatchName.getText().isEmpty()) {
            missingInfo = true;
            txfMaltbatchName.setStyle("-fx-border-color: red;");
            txfMaltbatchName.setOnMouseClicked(e -> {txfMaltbatchName.setStyle("-fx-border-color: transparent;");});
        }
        if (!missingInfo) {
            Maltbatch maltbatch = lvwMaltbatches.getSelectionModel().getSelectedItem();
            maltbatch.setName(txfMaltbatchName.getText());
            maltbatch.setDescription(txaMaltbatchDescription.getText());
            updateLvwMaltbatches();
        }
    }

    /**
     * Deletes a maltbatch.
     * If the maltbatch has a connection to any distillate-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     */
    @FXML
    void btnRemoveMaltbatchOnAction(ActionEvent event) {
        Maltbatch maltbatch = lvwMaltbatches.getSelectionModel().getSelectedItem();
        if (lvwMaltbatches.getSelectionModel().isEmpty()) {
            lvwMaltbatches.setStyle("-fx-border-color: red;");
        }
        else if (isConnectedToDistillate(maltbatch)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("Maltbatch kan ikke slettes");
            alert.setContentText("Dette maltbatch kan ikke slettes, da der findes distillater i systemet" +
                    ", som er tilknyttet dette batch. Slet først disse distillater og prøv igen.");
            alert.show();
        }
        else {
            ButtonType choice = showDeleteConfirmationDialog(maltbatch);
            if (choice.getText() == "Ja") {
                MainController.removeMaltbatch(maltbatch);
            }
        }
        updateLvwMaltbatches();

    }

    /**
     * Opens the view for supplier creation
     */
    @FXML
    void btnCreateSupplierOnAction(ActionEvent event) {
        // TODO SKAL DENNE KNAP VÆRE DER? KAN MAN IKKE BARE TRYKKE PÅ ADMINISTRER LEVERANDØR OG SÅ OPRETTE EN?
    }


    /**
     * Updates the listView with all the fields from storage
     */
    private void updateLvwFields() {
        lvwFields.getItems().setAll(MainController.getFields());
    }

    /**
     * Updates the listView with all the grains from storage
     * If the user has selected a field, only show the grains on that field
     */
    private void updateLvwGrains() {
        if (lvwFields.getSelectionModel().isEmpty())
            lvwGrains.getItems().setAll(MainController.getGrains());
        else {
            lvwGrains.getItems().clear();
            for (Grain grain : MainController.getGrains()) {
                if (grain.getField().equals(lvwFields.getSelectionModel().getSelectedItem())) {
                    lvwGrains.getItems().add(grain);
                }
            }
        }
    }

    /**
     * Updates the listView with all the maltbatches from storage
     */
    private void updateLvwMaltbatches() {
        if (lvwGrains.getSelectionModel().isEmpty())
            lvwMaltbatches.getItems().setAll(MainController.getMaltbatches());
        else {
            lvwMaltbatches.getItems().clear();
            for (Maltbatch maltbatch : MainController.getMaltbatches()) {
                if (maltbatch.getGrain().equals(lvwGrains.getSelectionModel().getSelectedItem())) {
                    lvwMaltbatches.getItems().add(maltbatch);
                }
            }
        }
    }

    private ButtonType showDeleteConfirmationDialog(Object o) {
        String typeOfObjectToDelete = "";
        if (o instanceof Field) typeOfObjectToDelete = "denne mark";
        else if (o instanceof Grain) typeOfObjectToDelete = "dette korn";
        else if (o instanceof Maltbatch) typeOfObjectToDelete = "dette maltbatch";


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Bekræft sletning");
        alert.setHeaderText("Er du sikker på, at du vil slette " + typeOfObjectToDelete);
        alert.getButtonTypes().setAll(new ButtonType("Ja"), new ButtonType("Fortyd"));

        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }

    private boolean isConnectedToGrain(Field field) {
        boolean ConnectionFound = false;
        int i = 0;
        List<Grain> grains = MainController.getGrains();
        while (!ConnectionFound && i < grains.size()) {
            if (grains.get(i).getField() == field) {
                ConnectionFound = true;
            }
            i++;
        }
        return ConnectionFound;
    }

    private boolean isConnectedToMaltbatch(Grain grain) {
        boolean ConnectionFound = false;
        int i = 0;
        List<Maltbatch> maltbatches = MainController.getMaltbatches();
        while (!ConnectionFound && i < maltbatches.size()) {
            if (maltbatches.get(i).getGrain() == grain) {
                ConnectionFound = true;
            }
            i++;
        }
        return ConnectionFound;
    }

    private boolean isConnectedToDistillate(Maltbatch maltbatch) {
        boolean ConnectionFound = false;
        int i = 0;
        List<Distillate> distillates = MainController.getDistillates();
        while (!ConnectionFound && i < distillates.size()) {
            if (distillates.get(i).getMaltbatches() == maltbatch) {
                ConnectionFound = true;
            }
            i++;
        }
        return ConnectionFound;
    }


    @FXML
    void btnStartSideOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnCrudCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCrudCaskOnAction(stage, scene, event);
    }

    @FXML
    void btnCrudStorageOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnRawMaterialOnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        // TODO
    }

}
