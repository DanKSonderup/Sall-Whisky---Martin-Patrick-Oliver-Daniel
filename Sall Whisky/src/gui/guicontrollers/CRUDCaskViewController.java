package gui.guicontrollers;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cask;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDCaskViewController implements Initializable {

    @FXML
    private Button btnCRUDCask;
    @FXML
    private Button btnCRUDRawMaterial;
    @FXML
    private Button btnCRUDStorage;
    @FXML
    private Button btnCRUDSupplier;
    @FXML
    private Button btnCreateCask;
    @FXML
    private Button btnCreateFillOnCask;
    @FXML
    private Button btnDeleteCask;
    @FXML
    private Button btnEditCask;
    @FXML
    private Button btnStartside;
    @FXML
    private ListView<Cask> lvwCasks;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        List<String> stringList = new ArrayList<>();
        for (Cask cask : MainController.getCasks()) {
            stringList.add(MainController.caskViewString(cask));
        }

         */
        updateLvwCasks();
    }

    @FXML
    void btnCreateCaskOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CreateCaskView.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Opret fad");
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        updateLvwCasks();
    }

    /**
     * Opens the distillary and fill view
     */
    @FXML
    void btnCreateFillOnCask(ActionEvent event) throws IOException {
        SwitchSceneController.btnDestillateAndFillOnCaskOnAction(stage, scene, event);
        updateLvwCasks();
    }


    /**
     * Deletes the selected Cask
     * If the cask has any fillOnCasks or previousFillOnCasks connected
     * prompt the user with that info and abort deletion
     */
    @FXML
    void btnDeleteCaskOnAction(ActionEvent event) {
        Cask cask = lvwCasks.getSelectionModel().getSelectedItem();
        if (lvwCasks.getSelectionModel().isEmpty()) {
            lvwCasks.setStyle("-fx-border-color: red;");
        }
        else if (!cask.getFillOnCasks().isEmpty() || !cask.getPreviousFillOnCask().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fejl");
            alert.setHeaderText("Fad kan ikke slettes");
            alert.setContentText("Dette fad kan ikke slettes, da der er eller har været opfyldninger" +
                    "på faddet.");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bekræft sletning");
            alert.setHeaderText("Er du sikker på, at du vil slette dette fad");
            alert.getButtonTypes().setAll(new ButtonType("Ja"), new ButtonType("Fortryd"));
            ButtonType choice = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (choice.getText() == "Ja") {
                MainController.removeCask(cask);
            }
            updateLvwCasks();

        }
    }



    @FXML
    void btnEditCaskOnAction(ActionEvent event) {
        //TODO Til version to når der skal kunne omhældes
    }

    /**
     * Updates the listView with all the casks
     */
    private void updateLvwCasks() {
        lvwCasks.getItems().setAll(MainController.getCasks());
    }

    @FXML
    void btnCrudStorageOnAction(ActionEvent event) throws IOException {
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
    void btnStartSideOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnStartView(stage, scene, event);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        SwitchSceneController.btnCRUDSupplier(stage, scene, event);
    }

}
