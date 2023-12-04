package gui.guicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CRUDCaskViewController {

    private Stage stage;
    private Scene scene;
    @FXML
    private AnchorPane ap_Pane1;

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
    private Button btnDelete;

    @FXML
    private Button btnDestillateAndFillOnCask;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnStartside;

    @FXML
    private Button btnViewDistillate;

    @FXML
    private ListView<?> lvwCountryOfOrigin;

    @FXML
    private ListView<?> lvwID;

    @FXML
    private ListView<?> lvwPosition;

    @FXML
    private ListView<?> lvwPrevContent;

    @FXML
    private ListView<?> lvwRack;

    @FXML
    private ListView<?> lvwShelf;

    @FXML
    private ListView<?> lvwSize;

    @FXML
    private ListView<?> lvwWarehouse;

    @FXML
    void btnCreateFillOnCask(ActionEvent event) {

    }

    @FXML
    void btnCreateCaskOnAction(ActionEvent event) throws IOException{
            URL url = new File("Sall Whisky/src/gui/views/CreateCaskView.fxml").toURI().toURL();
            Parent root1 = FXMLLoader.load(url);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Opret fad");
            stage.setScene(new Scene(root1));
            stage.show();
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