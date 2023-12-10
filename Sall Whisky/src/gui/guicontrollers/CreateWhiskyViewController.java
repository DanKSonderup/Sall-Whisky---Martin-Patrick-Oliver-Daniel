package gui.guicontrollers;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateWhiskyViewController implements Initializable {
    @FXML
    private TextField amountOfFillCltxf;

    @FXML
    private Button btnCalcNumberOfBottles;

    @FXML
    private Button btnCreateWhisky;

    @FXML
    private Button btnRegisterAlcoholpercentage;

    @FXML
    private ComboBox<Integer> cbbBottleSizeInCl;

    @FXML
    private Label lblAmountOfBottles;

    @FXML
    private Label lblTypeOfWhisky;

    @FXML
    private ListView<WhiskyFill> lvwWhiskybatch;

    @FXML
    private TableColumn<Cask, TapFromDistillate> tbcAge;

    @FXML
    private TableColumn<Cask, Double> tbcAlcoholPercentage;

    @FXML
    private TableColumn<Cask, Integer> tbcCaskID;

    @FXML
    private TableColumn<Cask, Double> tbcTotalLitersOfFills;

    @FXML
    private TableView<Cask> tvwRipeCasks;

    @FXML
    private TextArea txaContentOfWhisky;

    @FXML
    private TextArea txaDescriptionOfWhisky;

    @FXML
    private TextField txfAlcoholPercentage;

    @FXML
    private TextField txfWaterForDilution;

    @FXML
    private TextField txfWhiskyName;
    private final List<WhiskyFill> whiskyFills = new ArrayList<>();
    private WhiskyFill whiskyFill;
    private Whisky whisky;

    @FXML
    void btnCalcNumberOfBottlesOnAction(ActionEvent event) {
        if (cbbBottleSizeInCl.getSelectionModel().getSelectedItem() == null) {
            cbbBottleSizeInCl.setStyle("-fx-border-color: red;");
            return;
        }
        int sizeOfBottle = cbbBottleSizeInCl.getSelectionModel().getSelectedItem();
        double waterInLiters = txfParseDouble(txfWaterForDilution);

        if (waterInLiters < 0) {
            return;
        }

        String name = txfWhiskyName.getText().trim();
        String description = txaDescriptionOfWhisky.getText().trim();
        whisky = new Whisky(name, waterInLiters, whiskyFills, description);
        int amountOfBottles = Controller.amountOfBottles(whisky, sizeOfBottle);
        lblAmountOfBottles.setText("" + amountOfBottles);

        btnCreateWhisky.setDisable(false);
        clearErrorMarkings();
    }

    @FXML
    void btnCreateWhiskyOnAction(ActionEvent event) {
        int sizeOfBottle = cbbBottleSizeInCl.getSelectionModel().getSelectedItem();
        double waterInLiters = txfParseDouble(txfWaterForDilution);
        int amountOfBottles = Integer.parseInt(lblAmountOfBottles.getText());

        if (amountOfBottles < 0) {
            return;
        }

        String name = txfWhiskyName.getText().trim();
        if (name.isEmpty()) {
            txfWhiskyName.setStyle("-fx-border-color: red;");
            return;
        }
        String description = txaDescriptionOfWhisky.getText().trim();
        whisky = Controller.createWhisky(name, waterInLiters, new ArrayList<>(whiskyFills), description);

        Controller.createWhiskyBottlesForWhisky(amountOfBottles, sizeOfBottle, whisky);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Whisky");
        alert.setHeaderText("Whisky oprettet");
        alert.setContentText("En ny whisky er oprettet med navn: " + whisky.getName() + "\n" + "Der er oprettet " + amountOfBottles + " flasker med den valgte whisky");
        alert.show();
        clearAllEditableFields();
        clearErrorMarkings();
    }

    @FXML
    void btnRegisterAlcoholpercentageOnAction(ActionEvent event) {
        Cask cask = tvwRipeCasks.getSelectionModel().getSelectedItem();
        if (cask == null) {
            showErrorWindow("Intet fad", "Du har ikke valgt et fad");
            return;
        }
        double value = 0.0;
        double amountToFill = 0.0;

        value = txfParseDouble(txfAlcoholPercentage);
        amountToFill = txfParseDouble(amountOfFillCltxf);

        if (value < 1 || amountToFill < 1 || value > 99) {
            if (value > 99 || value < 1) {
                txfAlcoholPercentage.setStyle("-fx-border-color: red;");
            }
            if (amountToFill < 1) {
                amountOfFillCltxf.setStyle("-fx-border-color: red;");
            }
            return;
        }
        ArrayList<TapFromDistillate> tempFillsOnCask = new ArrayList<>();
        for (FillOnCask fillOnCask : cask.getCurrentFillOnCasks()) {
            tempFillsOnCask.add(fillOnCask.getTapFromDistillate());
        }

        try {
            whiskyFill = Controller.createWhiskyFill(amountToFill, tempFillsOnCask, value, cask);
        } catch (InterruptedException e) {
            showErrorWindow("Påfyldningsfejl", e.getMessage());
            return;
        }


        whiskyFills.add(whiskyFill);
        tvwRipeCasks.getItems().removeAll();
        updateRipeCasks();
        updatelvwWhiskyFillReadyForFill();
        updateContentOfWhisky(whiskyFill.toString());
        btnCreateWhisky.setDisable(true);



        if (whiskyFills.size() > 1 && haveDuplicate(whiskyFills)) {
            lblTypeOfWhisky.setText("Blended");
        } else {
            lblTypeOfWhisky.setText("Single malt");
        }
        clearErrorMarkings();
    }

    public static boolean haveDuplicate(List<WhiskyFill> whiskyFills) {
        for (int i = 0; i < whiskyFills.size() - 1; i++) {
            WhiskyFill current = whiskyFills.get(i);
            WhiskyFill next = whiskyFills.get(i + 1);
            if (!current.getCask().equals(next.getCask())) {
                return true;
            }
        }
        return false;
    }

    private void showErrorWindow(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fejl");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void clearErrorMarkings() {
        txfWaterForDilution.setStyle("-fx-border-color: transparent;");
        amountOfFillCltxf.setStyle("-fx-border-color: transparent;");
        txfAlcoholPercentage.setStyle("-fx-border-color: transparent;");
        txfWhiskyName.setStyle("-fx-border-color: transparent;");
    }
    private double txfParseDouble(TextField txf) {
        double returnValue = -1.0;
        try {
            returnValue = Double.parseDouble(txf.getText().trim());
        } catch (NumberFormatException e) {
            txf.setStyle("-fx-border-color: red;");
            return returnValue;
        }
        txf.setOnMouseClicked(e -> {txf.setStyle("-fx-border-color: transparent;");});
        return returnValue;
    }
    private void clearAllEditableFields() {
        lvwWhiskybatch.getItems().clear();
        txfAlcoholPercentage.clear();
        amountOfFillCltxf.clear();
        txfWhiskyName.clear();
        txfWaterForDilution.clear();
        txaContentOfWhisky.clear();
        txaDescriptionOfWhisky.clear();
        whiskyFills.clear();
        lblAmountOfBottles.setText("" + 0);
        lblTypeOfWhisky.setText("");
        cbbBottleSizeInCl.valueProperty().set(null);
    }

    private void updatelvwWhiskyFillReadyForFill() {
        if (whiskyFills.isEmpty()) {
            return;
        }
        lvwWhiskybatch.getItems().setAll(whiskyFills);
    }

    private void updateContentOfWhisky(String content) {
        txaContentOfWhisky.appendText(content + " \n");
    }


    private void updateRipeCasks() {
        tvwRipeCasks.getItems().setAll(Controller.getRipeCasks());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // updatelvwWhiskyFillReadyForFill();
        Integer bottleSize[] = {50, 75, 100, 120};
        cbbBottleSizeInCl.setItems(FXCollections.observableArrayList(bottleSize));

        tbcCaskID.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        tbcAlcoholPercentage.setCellValueFactory(new PropertyValueFactory<Cask, Double>("TotalAlcoholPercentage"));
        tbcAge.setCellValueFactory(new PropertyValueFactory<Cask, TapFromDistillate>("YoungestFillOnCask"));
        tbcTotalLitersOfFills.setCellValueFactory(new PropertyValueFactory<Cask, Double>("CurrentContentInLiters"));
        updateRipeCasks();
    }
}
