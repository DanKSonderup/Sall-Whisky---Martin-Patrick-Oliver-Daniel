package gui.guicontrollers;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Field;
import model.Grain;
import model.GrainSupplier;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TextArea txaCultivationDescription;
    @FXML
    private TextArea txaFieldDescription;
    @FXML
    private TextArea txaMaltbatchDescription;
    @FXML
    private TextField txfFieldName;
    @FXML
    private TextField txfGrainType;

    /** LISTS FOR TEST A GUI*/
    private List<Field> fieldList = new ArrayList<>();
    private List<Grain> grainList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        lvwFields.getItems().setAll(MainController.getFields());
//        lvwGrains.getItems().setAll(MainController.getGrains());
        Field selectedItem = lvwFields.getSelectionModel().getSelectedItem();

        /**
         * If no field (or grain) is selected, have the delete button be disabled
         */
    }


    /**
     * Creates a new field and updates the listview
     * If Name is empty prompt the user to enter a name
     * If Description is empty set to ""
     *
     */
    @FXML
    void btnCreateFieldOnAction(ActionEvent event) {

        // ERROR HANDLING
        if (txfFieldName.getText().isEmpty()) {
            txfFieldName.setStyle("-fx-border-color: red;");
            txfFieldName.setOnMouseClicked(e -> {
                txfFieldName.setStyle("");
            });
            return;
        }

//        Field field = MainController.createField(txfFieldName.getText(), txaFieldDescription.getText());
        fieldList.add(new Field(txfFieldName.getText(), txaFieldDescription.getText()));

        txfFieldName.setText("");
        txaFieldDescription.setText("");
        updateLvwFields();
    }

    /**
     * Updates the selected field with name and/or description
     * If name is empty, prompt the user to enter a name
     * Update the listview.
     */
    @FXML
    void btnUpdateFieldOnAction(ActionEvent event) {
        // TODO
        Field field = lvwFields.getSelectionModel().getSelectedItem();
        field.setName(txfFieldName.getText());
        field.setDescription(txaFieldDescription.getText());

        txfFieldName.setText("");
        txaFieldDescription.setText("");
        updateLvwFields();
    }

    /**
     * Deletes a field.
     * If the field has a connection to any grain-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     * Checks all grain to see if the field is connected to any, if so, abort deletion
     */
    @FXML
    void btnDeleteFieldOnAction(ActionEvent event) {
        Field field = lvwFields.getSelectionModel().getSelectedItem();

        boolean grainConnectionFound = false;
        while (!grainConnectionFound)
        for (Grain grain : MainController.getGrains()) {
            if (grain.getField() == field) {

            }
        }
        // TODO
    }

    /**
     * Creates a new Grain and updates the listview
     * If graintype is empty prompt the user to enter a graintype
     * If cultivationDescription is empty set to ""
     * If no field selected, prompt user to select a field
     */
    @FXML
    void btnCreateGrainOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Updates the selected grain with graintype and/or cultivationDescription
     * If graintype is empty, prompt the user to enter a graintype
     * Update the listview.
     */
    @FXML
    void btnUpdateGrainOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Deletes a grain.
     * If the grain has a connection to any maltbatch-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     */
    @FXML
    void btnDeleteGrainOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Creates a new maltbatch and updates the listview
     * If description is empty set to ""
     * If no grain selected, prompt the user to select a grain
     */
    @FXML
    void btnCreateMaltbatchOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Updates the selected maltbatch with name and/or description
     * If name is empty, prompt the user to enter a name
     * Update the listview.
     */
    @FXML
    void btnUpdateMaltbatchOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Deletes a maltbatch.
     * If the maltbatch has a connection to any distillate-objects, prompt the user with that info and abort deletion
     * Prompt the user with a confirmation dialog (delete or keep)
     */
    @FXML
    void btnRemoveMaltbatchOnAction(ActionEvent event) {
        // TODO
    }

    /**
     * Opens the view for supplier creation
     */
    @FXML
    void btnCreateSupplierOnAction(ActionEvent event) {
        // TODO
    }


    /**
     * Updates the listView with all the fields from storage
     */
    private void updateLvwFields() {
        // TODO update from storage, not fieldList
        lvwFields.getItems().setAll(fieldList);
    }

    /**
     * Updates the listView with all the grains from storage
     */
    private void updateLvwGrains() {
        //TODO update from storage, not grainList
        lvwGrains.getItems().setAll(grainList);
    }



}
