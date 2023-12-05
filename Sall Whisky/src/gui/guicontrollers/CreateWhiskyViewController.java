package gui.guicontrollers;

import controller.MainController;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
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
    private ListView<WhiskyFill> lvwWhiskyFillReadyForFill;

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

        whisky = MainController.createWhisky(name, waterInLiters, whiskyFill.getAlcoholPercentage(), )

    }

    @FXML
    void btnRegisterAlcoholPercentageOnAction(ActionEvent event) {
        double value = Double.parseDouble(txfAlcoholPercentage.getText().trim());
        FillOnCask fillOnCask = lvwFillOnCaskReadyForFill.getSelectionModel().getSelectedItem();

        double amountToFill = Double.parseDouble(amountOfFillCltxf.getText().trim());
        whiskyFill = MainController.createWhiskyFill(amountToFill, fillOnCask, value);
        lvwWhiskyFillReadyForFillTemp.add(whiskyFill);
        updatelvwWhiskyFillReadyForFill();
    }

    public void selectedStorageItemChanged() {
        FillOnCask selectedFillOnCask = lvwFillOnCaskReadyForFill.getSelectionModel().getSelectedItem();
        WhiskyFill selectedWhiskyFill = lvwWhiskyFillReadyForFill.getSelectionModel().getSelectedItem();
    }

    private void updatelvwWhiskyFillReadyForFill() {
        lvwWhiskyFillReadyForFill.getItems().setAll(lvwWhiskyFillReadyForFillTemp);
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
        updatelvwWhiskyFillReadyForFill();
        updatelvwFillOnCaskReadyForFill();
        ChangeListener<FillOnCask> fillOnCaskChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwFillOnCaskReadyForFill.getSelectionModel().selectedItemProperty().addListener(fillOnCaskChangeListener);

        ChangeListener<WhiskyFill> whiskyFillChangeListener = (ov, o, n) -> this.selectedStorageItemChanged();
        lvwWhiskyFillReadyForFill.getSelectionModel().selectedItemProperty().addListener(whiskyFillChangeListener);


        Integer bottleSize[] = {3, 10, 50, 70};
        bottleSizecbb.setItems(FXCollections.observableArrayList(bottleSize));

    }
}
