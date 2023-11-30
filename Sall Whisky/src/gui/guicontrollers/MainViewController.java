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
    public void crudCaskOnAction(ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CRUDCaskView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void crudStorageOnAction(ActionEvent event) {

    }

    @FXML
    void destillateAndFillOnCaskOnAction(ActionEvent event) {

    }
}
