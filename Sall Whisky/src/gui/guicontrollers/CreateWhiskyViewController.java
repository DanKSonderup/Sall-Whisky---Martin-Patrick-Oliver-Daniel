package gui.guicontrollers;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.*;

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
    private ComboBox<?> bottleSizecbb;

    @FXML
    private Label bottleSizelbl;

    @FXML
    private Button btnCreateWhisky;

    @FXML
    private Button btnRegistrerAlcoholPercentage;

    @FXML
    private ListView<Distillate> lvwDestillatesReadyForFill;

    @FXML
    private ListView<Whisky> lvwWhiskyReadyForFill;

    @FXML
    private TextField txfAlcoholPercentage;

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
    private final List<Distillate> lvwDistilatesReadyForFillTemp = new ArrayList<>();
    @FXML
    void amountOfBottlesCalculation(ActionEvent event) {

    }

    @FXML
    void btnCreateWhisky(ActionEvent event) {

    }

    @FXML
    void btnRegisterAlcoholPercentageOnAction(ActionEvent event) {
        double value = Double.parseDouble(txfAlcoholPercentage.getText().trim());
        Distillate distillate = lvwDestillatesReadyForFill.getSelectionModel().getSelectedItem();
        distillate.setAlcoholPercentage(value);

        for (Whisky whisky : MainController.getStorage().getWhiskies()) {

        }

    }

    public void selectedStorageItemChanged() {
        Distillate selectedDistilate = lvwDestillatesReadyForFill.getSelectionModel().getSelectedItem();
    }

    private void updatelvwWhiskyReadyForFill() {

    }


    private void updatelvwDestillatesReadyForFill() {
        for (Cask cask : MainController.getCasks()) {
            for (FillOnCask fillOnCask : cask.getFillOnCasks()) {
                if (fillOnCask.getTimeOfFill().isBefore(LocalDate.now().minusYears(3))) {
                    for (DistillateFill distillateFill : fillOnCask.getDistillateFills()) {
                        lvwDistilatesReadyForFillTemp.add(distillateFill.getDistillate());
                    }
                }
            }
        }
        lvwDestillatesReadyForFill.getItems().setAll(lvwDistilatesReadyForFillTemp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatelvwDestillatesReadyForFill();
        ChangeListener<Distillate> distilateReadyForFillChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        // lvwDestillatesReadyForFill.getSelectionModel().selectedItemProperty().addListener(distilateReadyForFillChangeListener);
    }
}
