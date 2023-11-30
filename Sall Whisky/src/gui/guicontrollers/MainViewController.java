package gui.guicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainViewController {

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
    void btnCrudCaskOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CRUDCaskView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnCrudStorageOnAction(ActionEvent event) throws IOException{
        URL url = new File("Sall Whisky/src/gui/views/CRUDStorageView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnDestillateAndFillOnCaskOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CreateDistilleryFillOnCask.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnRawMaterialOnAction(ActionEvent event) {

    }

    @FXML
    void btnStartSideOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {

    }
}
