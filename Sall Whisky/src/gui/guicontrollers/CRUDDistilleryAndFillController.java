package gui.guicontrollers;

import controller.MainController;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
            return;
        }

        MainController.createDistillate(newMakenr, distillationTime, alcoholPercentage, amountInLiters, employee, maltBatches);
        clearErrorMarkings();

        updateControls();

//        MainController.createDistillate()
        }

    @FXML
    void btnFillOnCaskOnAction(ActionEvent event) {
        Distillate distillate = distillatelvw.getSelectionModel().getSelectedItem();
        Cask cask = availableCaskslvw.getSelectionModel().getSelectedItem();
        double amountInLiters = txfParseDouble(typeLiterAmounttxf);

        if (cask == null) {
            availableCaskslvw.setStyle("-fx-border-color: red;");
            return;
        }
        if (amountInLiters < 0) {
            return;
        }

        if (distillate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("Du skal vælge et distillat");
            alert.setContentText("Du skal vælge et distillat for at påfylde");
            alert.show();
            return;
        }
        ArrayList<DistillateFill> distillateFills = new ArrayList<>();
        try {
            distillateFills.add(new DistillateFill(amountInLiters, distillate));
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("For stor påfyldning");
            alert.setContentText("Du har prøvet på at påfylde flere liter end der er tilgængelige for dit destillat!");
            alert.show();
            return;
        }
        if (!canFillOnCask(amountInLiters, cask)) {
            MainController.createFillOnCask(LocalDate.now(), cask, distillateFills);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Påfyldning oprettet");
            alert.setHeaderText("En påfyldning på Cask ID: " + cask.getCaskId() + " er oprettet");
            alert.setContentText("En påfyldning på " + amountInLiters + " liter er oprettet");
            alert.show();
        }

            try {
                MainController.createFillOnCask(LocalDate.now(), cask, distillateFills);
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fejl");
                alert.setHeaderText("Fejl");
                alert.setContentText(e.getMessage());
                alert.show();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Påfyldning");
            alert.setHeaderText("Påfyldning oprettet");
            alert.setContentText("En påfyldning af " + amountInLiters + " liter er blevet påfyldt på fadID: " + cask.getCaskId());
            alert.show();
            clearErrorMarkings();
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

    private void clearErrorMarkings() {
        newMakeNrtxf.setStyle("-fx-border-color: transparent;");
        pickEmployeeComboBox.setStyle("-fx-border-color: transparent;");
        amountOfLiterstxf.setStyle("-fx-border-color: transparent;");
        alcoholPercentagetxf.setStyle("-fx-border-color: transparent;");
        typeLiterAmounttxf.setStyle("-fx-border-color: transparent;");
    }

    private void updateControls() {
        distillatelvw.getItems().setAll(MainController.getAvailableDistillates());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableCaskslvw.getItems().setAll(MainController.getCasks());
        lvwMaltBatches.getItems().setAll(MainController.getMaltbatches());
        pickEmployeeComboBox.getItems().setAll(MainController.getEmployees());
    }

    private boolean canFillOnCask(double amountInLiters, Cask cask) {
        double currentContent = 0;
        for (FillOnCask fillOnCask: cask.getFillOnCasks()) {
            for (DistillateFill distillateFill: fillOnCask.getDistillateFills()) {
                currentContent += distillateFill.getAmountOfDistillateInLiters();
            }
        }
        return currentContent > cask.getSizeInLiters();
    }

    private ArrayList<String> getAvailableCasksAsStrings() {
        ArrayList<String> caskStrings = new ArrayList<>();
        for (Cask cask: MainController.getAvailableCasks()) {
            String s = cask.getCaskId() + " | str(" + cask.getSizeInLiters() + ") " +
                    "| liter tilgængelige(" + cask.getLitersAvailable() + ")";
            caskStrings.add(s);
        }
        return caskStrings;
    }
}
