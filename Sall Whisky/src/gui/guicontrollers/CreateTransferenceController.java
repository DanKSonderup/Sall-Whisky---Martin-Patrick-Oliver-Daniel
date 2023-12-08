package gui.guicontrollers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cask;
import model.FillOnCask;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateTransferenceController implements Initializable {

    @FXML
    private Button btnShowAvailableCasksForTransference;

    @FXML
    private Button btnTransference;

    @FXML
    private TableColumn<Cask, FillOnCask> tbcAge;

    @FXML
    private TableColumn<Cask, FillOnCask> tbcAge1;

    @FXML
    private TableColumn<Cask, Double> tbcAlcoholPercentage;

    @FXML
    private TableColumn<Cask, Double> tbcAlcoholPercentage1;

    @FXML
    private TableColumn<Cask, Integer> tbcCaskID;

    @FXML
    private TableColumn<Cask, Integer> tbcCaskID1;

    @FXML
    private TableColumn<Cask, Double> tbcTotalLitersOfFills;

    @FXML
    private TableColumn<Cask, Double> tbcTotalLitersOfFills1;

    @FXML
    private TableView<Cask> tbvAvailableCasksForTransference;

    @FXML
    private TableView<Cask> tbvCasksWithDestillate;

    @FXML
    void btnShowAvailableCasksForTransferenceOnAction(ActionEvent event) {
        Cask selectedCask = tbvCasksWithDestillate.getSelectionModel().getSelectedItem();
        if (selectedCask == null) {
            tbvCasksWithDestillate.setStyle("-fx-border-color: red;");
            return;
        }
        double currentContentInLiters = selectedCask.getCurrentContentInLiters();
        tbvAvailableCasksForTransference.getItems().setAll(Controller.getCasksWithXLitersAvailable(currentContentInLiters));
    }

    @FXML
    void btnTransferenceOnAction(ActionEvent event) {
        Cask oldCask = tbvCasksWithDestillate.getSelectionModel().getSelectedItem();
        Cask newCask = tbvAvailableCasksForTransference.getSelectionModel().getSelectedItem();

        Controller.createPutOnCask(oldCask, newCask);
        System.out.println(oldCask.getCurrentPutOnCasks());
        System.out.println(oldCask.getLitersAvailable());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbcCaskID.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        tbcCaskID1.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        tbcAlcoholPercentage.setCellValueFactory(new PropertyValueFactory<Cask, Double>("TotalAlcoholPercentage"));
        tbcAlcoholPercentage1.setCellValueFactory(new PropertyValueFactory<Cask, Double>("TotalAlcoholPercentage"));
        tbcAge.setCellValueFactory(new PropertyValueFactory<Cask, FillOnCask>("YoungestFillOnCask"));
        tbcAge1.setCellValueFactory(new PropertyValueFactory<Cask, FillOnCask>("YoungestFillOnCask"));
        tbcTotalLitersOfFills.setCellValueFactory(new PropertyValueFactory<Cask, Double>("CurrentContentInLiters"));
        tbcTotalLitersOfFills1.setCellValueFactory(new PropertyValueFactory<Cask, Double>("LitersAvailable"));

        tbvCasksWithDestillate.getItems().setAll(Controller.getCasksWithDistillateOn());
    }
}

