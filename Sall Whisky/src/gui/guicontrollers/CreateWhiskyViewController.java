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
    private Label FillOnWhiskylbl;

    @FXML
    private ListView<Whisky> WhiskyPropertieslvw;

    @FXML
    private Label amountOfBottleslbl;

    @FXML
    private TextField amountOfBottlestxf;

    @FXML
    private Label amountOfFillCllbl;

    @FXML
    private TextField amountOfFillCltxf;

    @FXML
    private ComboBox<Integer> bottleSizecbb;

    @FXML
    private Label bottleSizelbl;

    @FXML
    private Button btnCreateWhisky;

    @FXML
    private Button btnRegistrerAlcoholPercentage;

    @FXML
    private TableColumn<Cask, FillOnCask> tbcAge;

    @FXML
    private TableColumn<Cask, Double> tbcAlcoholPercentage;

    @FXML
    private TableColumn<Cask, Integer> tbcCaskID;

    @FXML
    private TableColumn<Cask, Double> tbcTotalLitersOfFills;

    @FXML
    private TableView<Cask> tbvRipeCasks;

    @FXML
    private ListView<WhiskyFill> lvwWhiskyBatch;

    @FXML
    private TextField txfAlcoholPercentage;

    @FXML
    private TextField txfWhiskyName;

    @FXML
    private TextField txfTypeOfWhisky;
    @FXML
    private Button btnCalcNumberOfBottles;

    @FXML
    private Label typeOfWhiskylbl;

    @FXML
    private Label wateredWhiskylbl;

    @FXML
    private TextField wateredWhiskytxf;

    @FXML
    private Label whiskyPropertieslbl;
    @FXML
    private TextArea txaContentOfWhisky;
    @FXML
    private TextArea txaDescriptionOfWhisky;
    private final List<WhiskyFill> whiskyFills = new ArrayList<>();
    private WhiskyFill whiskyFill;
    private Whisky whisky;


    /**
     * Create a Whisky with the name and description specified in the TextFields.
     * Create a set amount of WhiskyBottles for the Whisky calculated by the size of the bottles,
     * amount of water in Liters
     * and amount of Bottles
     */
    @FXML
    void btnCreateWhisky(ActionEvent event) {
        int sizeOfBottle = bottleSizecbb.getSelectionModel().getSelectedItem();
        double waterInLiters = Double.parseDouble(wateredWhiskytxf.getText().trim());
        int amountOfBottles = Integer.parseInt(amountOfBottlestxf.getText().trim());

        String name = txfWhiskyName.getText().trim();
        String description = txaDescriptionOfWhisky.getText().trim();
        whisky = Controller.createWhisky(name, waterInLiters, new ArrayList<>(whiskyFills), description);

        Controller.createWhiskyBottlesForWhisky(amountOfBottles, sizeOfBottle, whisky);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Whisky");
        alert.setHeaderText("Whisky oprettet");
        alert.setContentText("En ny whisky er oprettet med navn: " + whisky.getName() + "\n" + "Der er oprettet " + amountOfBottles + " flasker med den valgte whisky");
        alert.show();
        clearAllEditableFields();
    }

    /**
     * Gives the selected Cask from the table view a new AlcoholPercentage
     * Make sure the new value is a double
     * If no cask is selected show an errorWindow with a message
     * Make sure the input for value and amountToFill is valid. Value < 0, amountToFill < 0. If value > 99 exit method
     * and indicate error
     * Create a new WhiskyFill and handle any possible exception and show error window
     * Update all relevant List and tableViews
     * Check whether the type of whisky is Blended or Single malt by the size of whiskyFills
     */
    @FXML
    void btnRegisterAlcoholPercentageOnAction(ActionEvent event) {
        Cask cask = tbvRipeCasks.getSelectionModel().getSelectedItem();
        if (cask == null) {
            showErrorWindow("Intet fad", "Du har ikke valgt et fad");
            return;
        }
        double value = 0.0;
        double amountToFill = 0.0;

        value = txfParseDouble(txfAlcoholPercentage);
        amountToFill = txfParseDouble(amountOfFillCltxf);

        if (value < 0 || amountToFill < 0 || value > 99) {
            if (value > 99) {
                txfAlcoholPercentage.setStyle("-fx-border-color: red;");
            }
            return;
        }
        ArrayList<FillOnCask> tempFillsOnCask = new ArrayList<>();
        tempFillsOnCask.addAll(cask.getFillOnCasks());
        try {
            whiskyFill = Controller.createWhiskyFill(amountToFill, tempFillsOnCask, value, cask);
        } catch (InterruptedException e) {
            showErrorWindow("Påfyldningsfejl", e.getMessage());
            return;
        }


        whiskyFills.add(whiskyFill);
        tbvRipeCasks.getItems().removeAll();
        updateRipeCasks();
        updatelvwWhiskyFillReadyForFill();
        updateContentOfWhisky(whiskyFill.toString());
        btnCreateWhisky.setDisable(true);

        if (whiskyFills.size() > 1) {
            txfTypeOfWhisky.setText("Blended");
        } else {
            txfTypeOfWhisky.setText("Single malt");
        }
        clearErrorMarkings();
    }

    /**
     * Calculates the amount of bottles from a Whisky
     * If waterInLiters < 0 or sizeOfBottles == 0 exit the method
     */
    @FXML
    void btnCalcNumberOfBottlesOnAction(ActionEvent event) {
        if (bottleSizecbb.getSelectionModel().getSelectedItem() == null) {
            bottleSizecbb.setStyle("-fx-border-color: red;");
            return;
        }
        int sizeOfBottle = bottleSizecbb.getSelectionModel().getSelectedItem();
        double waterInLiters = Double.parseDouble(wateredWhiskytxf.getText().trim());

        if (waterInLiters < 0) {
            return;
        }

        String name = txfWhiskyName.getText().trim();
        String description = txaDescriptionOfWhisky.getText().trim();
        whisky = new Whisky(name, waterInLiters, whiskyFills, description);
        int amountOfBottles = Controller.amountOfBottles(whisky, sizeOfBottle);
        amountOfBottlestxf.setText("" + amountOfBottles);

        btnCreateWhisky.setDisable(false);
        clearErrorMarkings();
    }


    /**
     * Creates an alert
     * @param header decides the alert's HeaderText
     * @param content decides the alert's ContentText
     */
    private void showErrorWindow(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fejl");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    /**
     * Clear all the error markings of wateredWhisky, alcoholPercentage and amountOfFill textFields
     */
    private void clearErrorMarkings() {
        wateredWhiskytxf.setStyle("-fx-border-color: transparent;");
        amountOfFillCltxf.setStyle("-fx-border-color: transparent;");
        txfAlcoholPercentage.setStyle("-fx-border-color: transparent;");
    }
    /**
     * Checks whether a TextField can be parsed into a Double
     * If the TextField can't be parsed into a double catch a NumberFormatException and mark the field as red
     * Else return the amount declared in the TextField
     */
    private double txfParseDouble(TextField txf) {
        double returnValue = -1.0;
        try {
            returnValue = Double.parseDouble(txf.getText().trim());
        } catch (NumberFormatException e) {
            txf.setStyle("-fx-border-color: red;");
        }
        txf.setOnMouseClicked(e -> {txf.setStyle("-fx-border-color: transparent;");});
        return returnValue;
    }
    private void clearAllEditableFields() {
        lvwWhiskyBatch.getItems().clear();
        txfAlcoholPercentage.clear();
        amountOfFillCltxf.clear();
        txfWhiskyName.clear();
        wateredWhiskytxf.clear();
        txaContentOfWhisky.clear();
        txaDescriptionOfWhisky.clear();
        whiskyFills.clear();
        txfTypeOfWhisky.clear();
        amountOfBottlestxf.clear();
    }

    /**
     * Updates the WhiskyBatch ListView
     */
    private void updatelvwWhiskyFillReadyForFill() {
        if (whiskyFills.isEmpty()) {
            return;
        }
        lvwWhiskyBatch.getItems().setAll(whiskyFills);
    }

    /**
     * Adds text to the ContentOfWhisky TextArea
     */
    private void updateContentOfWhisky(String content) {
        txaContentOfWhisky.appendText(content + " \n");
    }


    /**
     * Updates the RipeCask TableView
     */
    private void updateRipeCasks() {
        tbvRipeCasks.getItems().setAll(Controller.getRipeCasks());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // updatelvwWhiskyFillReadyForFill();
        Integer bottleSize[] = {50, 75, 100, 120};
        bottleSizecbb.setItems(FXCollections.observableArrayList(bottleSize));

        tbcCaskID.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        tbcAlcoholPercentage.setCellValueFactory(new PropertyValueFactory<Cask, Double>("TotalAlcoholPercentage"));
        tbcAge.setCellValueFactory(new PropertyValueFactory<Cask, FillOnCask>("YoungestFillOnCask"));
        tbcTotalLitersOfFills.setCellValueFactory(new PropertyValueFactory<Cask, Double>("CurrentContentInLiters"));
        updateRipeCasks();
    }
}
