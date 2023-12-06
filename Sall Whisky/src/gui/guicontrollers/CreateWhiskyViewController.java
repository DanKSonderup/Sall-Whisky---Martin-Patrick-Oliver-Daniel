package gui.guicontrollers;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    private ListView<FillOnCask> lvwFillOnCaskReadyForFill;

    @FXML
    private ListView<WhiskyFill> lvwWhiskyBatch;

    @FXML
    private TextField txfAlcoholPercentage;

    @FXML
    private TextField txfWhiskyName;

    @FXML
    private TextField txfTypeOfWhisky;

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
    private final List<FillOnCask> lvwFillOnCaskReadyForFillTemp = new ArrayList<>();
    private final List<WhiskyFill> lvwWhiskyFillReadyForFillTemp = new ArrayList<>();

    private WhiskyFill whiskyFill;
    private Whisky whisky;


    @FXML
    void amountOfBottlesCalculation(ActionEvent event) {

    }

    @FXML
    void btnCreateWhisky(ActionEvent event) {
        double amountOfWhiskyFill = Double.parseDouble(amountOfFillCltxf.getText().trim());
        double waterInLiters = Double.parseDouble(wateredWhiskytxf.getText().trim());
        String name = txfWhiskyName.getText().trim();

        // whisky = MainController.createWhisky(name, waterInLiters, whiskyFill.getAlcoholPercentage(), )
    }

    @FXML
    void btnRegisterAlcoholPercentageOnAction(ActionEvent event) {
        FillOnCask fillOnCask = lvwFillOnCaskReadyForFill.getSelectionModel().getSelectedItem();
        double value = 0.0;
        double amountToFill = 0.0;

        value = txfParseDouble(txfAlcoholPercentage);
        amountToFill = txfParseDouble(amountOfFillCltxf);

        if (value < 0 || amountToFill < 0) {
            return;
        }
        whiskyFill = MainController.createWhiskyFill(amountToFill, fillOnCask, value);


        lvwWhiskyFillReadyForFillTemp.add(whiskyFill);
        lvwFillOnCaskReadyForFill.getItems().clear();
        updatelvwWhiskyFillReadyForFill();
        updateContentOfWhisky(whiskyFill.toString());
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
        lvwWhiskyBatch.getItems().setAll(lvwWhiskyFillReadyForFillTemp);
    }

    private void updateContentOfWhisky(String content) {
        txaContentOfWhisky.appendText(content + " \n");
    }


    private void updatelvwFillOnCaskReadyForFill() {
        for (Cask cask : MainController.getCasks()) {
            for (FillOnCask fillOnCask : cask.getFillOnCasks()) {
                if (fillOnCask.getTimeOfFill().isBefore(LocalDate.now().minusYears(3))) {
                    lvwFillOnCaskReadyForFillTemp.add(fillOnCask);
                }
            }
        }
        lvwFillOnCaskReadyForFill.getItems().setAll(lvwFillOnCaskReadyForFillTemp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // updatelvwWhiskyFillReadyForFill();
        updatelvwFillOnCaskReadyForFill();


        Integer bottleSize[] = {3, 10, 50, 70};
        bottleSizecbb.setItems(FXCollections.observableArrayList(bottleSize));

    }
}
