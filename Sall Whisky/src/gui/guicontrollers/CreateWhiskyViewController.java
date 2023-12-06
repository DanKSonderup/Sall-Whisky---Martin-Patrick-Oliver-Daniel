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
    private final List<WhiskyFill> whiskyFills = new ArrayList<>();

    private WhiskyFill whiskyFill;
    private Whisky whisky;


    @FXML
    void btnCreateWhisky(ActionEvent event) {
        int sizeOfBottle = bottleSizecbb.getSelectionModel().getSelectedItem();
        double waterInLiters = Double.parseDouble(wateredWhiskytxf.getText().trim());
        int amountOfBottles = Integer.parseInt(amountOfBottlestxf.getText().trim());

        String name = txfWhiskyName.getText().trim();
        whisky = Controller.createWhisky(name, waterInLiters, whiskyFills);

        Controller.createWhiskyBottlesForWhisky(amountOfBottles, sizeOfBottle, whisky);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Whisky");
        alert.setHeaderText("Whisky oprettet");
        alert.setContentText("En ny whisky er oprettet med navn: " + whisky.getName() + "\n" + "Der er oprettet " + amountOfBottles + " flasker med den valgte whisky");
        alert.show();
        System.out.println(Controller.getStorage().getWhiskies());
    }

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
        try {
            whiskyFill = Controller.createWhiskyFill(amountToFill, cask.getFillOnCasks(), value, cask);
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

    @FXML
    void btnCalcNumberOfBottlesOnAction(ActionEvent event) {
        int sizeOfBottle = bottleSizecbb.getSelectionModel().getSelectedItem();
        double waterInLiters = Double.parseDouble(wateredWhiskytxf.getText().trim());

        if (waterInLiters < 0) {
            return;
        }
        if (sizeOfBottle == 0) {
            bottleSizecbb.setStyle("-fx-border-color: red;");
            return;
        }

        String name = txfWhiskyName.getText().trim();
        whisky = new Whisky(name, waterInLiters, whiskyFills);
        int amountOfBottles = Controller.amountOfBottles(whisky, sizeOfBottle);
        amountOfBottlestxf.setText("" + amountOfBottles);

        btnCreateWhisky.setDisable(false);
        clearErrorMarkings();
    }


    private void showErrorWindow(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fejl");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void clearErrorMarkings() {
        wateredWhiskytxf.setStyle("-fx-border-color: transparent;");
        amountOfFillCltxf.setStyle("-fx-border-color: transparent;");
        txfAlcoholPercentage.setStyle("-fx-border-color: transparent;");
    }
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

    private void updatelvwWhiskyFillReadyForFill() {
        lvwWhiskyBatch.getItems().setAll(whiskyFills);
    }

    private void updateContentOfWhisky(String content) {
        txaContentOfWhisky.appendText(content + " \n");
    }


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
