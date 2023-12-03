package gui.guicontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class SwitchSceneController {


    public static void btnCrudCaskOnAction(Stage stage, Scene scene, ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CRUDCaskView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void btnCrudStorageOnAction(Stage stage, Scene scene, ActionEvent event) throws IOException{
        URL url = new File("Sall Whisky/src/gui/views/CRUDStorageView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void btnCRUDRawMaterials(Stage stage, Scene scene, ActionEvent event) throws IOException {
        URL url = new File("Sall Whisky/src/gui/views/CRUDRawMaterialsView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
