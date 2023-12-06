package gui.guicontrollers;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cask;
import model.FillOnCask;
import model.Whisky;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

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
    private Button btnDestillateAndFillOnCask;

    @FXML
    private Button btnStartside;
    @FXML
    private Button btnCreateWhisky;
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
    private ListView<Whisky> lvwWhisky;

    @FXML
    void btnCrudCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCrudCask(stage, scene, event);
    }

    @FXML
    void btnCrudStorageOnAction(ActionEvent event) throws IOException{
        SwitchSceneController.btnCrudStorage(stage, scene, event);
    }

    @FXML
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnDestillateAndFillOnCaskOnAction(stage, scene, event);
    }

    @FXML
    void btnRawMaterialOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnRawMaterial(stage, scene, event);
    }

    @FXML
    void btnCreateWhiskyOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CreateWhiskyView.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Opret Whisky");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        updateViews();
    }

    @FXML
    void btnStartSideOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCRUDSupplier(stage, scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbcCaskID.setCellValueFactory(new PropertyValueFactory<Cask, Integer>("caskId"));
        tbcAlcoholPercentage.setCellValueFactory(new PropertyValueFactory<Cask, Double>("TotalAlcoholPercentage"));
        tbcAge.setCellValueFactory(new PropertyValueFactory<Cask, FillOnCask>("YoungestFillOnCask"));
        tbcTotalLitersOfFills.setCellValueFactory(new PropertyValueFactory<Cask, Double>("CurrentContentInLiters"));
        tbvRipeCasks.getItems().setAll(MainController.getRipeCasks());
        lvwWhisky.getItems().setAll(MainController.getWhiskies());
    }

    private void updateViews() {
        tbvRipeCasks.getItems().setAll(MainController.getRipeCasks());
        lvwWhisky.getItems().setAll(MainController.getWhiskies());
    }
}
