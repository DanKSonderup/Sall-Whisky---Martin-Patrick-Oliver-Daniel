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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Cask;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    private Button btnDestillateAndFillOnCask;
    @FXML
    private Button btnEditCask;
    @FXML
    private Button btnStartside;
    @FXML
    private Button btnViewDistillate;
    @FXML
    private ListView<Cask> lvwCasks;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvwCasks.getItems().setAll(MainController.getCasks());
    }

    @FXML
    void btnCreateCaskOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CreateCaskView.fxml").toURI().toURL();
        Parent root1 = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Opret fad");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Opens the distillary and fill view
     */
    @FXML
    void btnCreateFillOnCask(ActionEvent event) throws IOException {
        SwitchSceneController.btnDestillateAndFillOnCaskOnAction(stage, scene, event);
    }


    /**
     * Deletes the selected Cask
     * If the cask has any fillOnCasks connected
     */
    @FXML
    void btnDeleteCaskOnAction(ActionEvent event) {
        Cask cask = lvwCasks.getSelectionModel().getSelectedItem();
        if (lvwCasks.getSelectionModel().isEmpty()) {
            lvwCasks.setStyle("-fx-border-color: red;");
        }
        //TODO
    }



    @FXML
    void btnEditCaskOnAction(ActionEvent event) {
        //TODO
    }



    @FXML
    void btnViewDistillateForCaskOnAction(ActionEvent event) {
        //TODO

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
