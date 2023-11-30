package gui.guicontrollers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class CRUDCaskViewController {
    @FXML
    private AnchorPane ap_Pane1;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnViewDistillate;

    @FXML
    private TableView<?> tbwTable;


    @FXML
    void btnCreateOnAction(ActionEvent event) {
        System.out.println("Idk");
        ap_Pane1.setDisable(true);
    }

    @FXML
    void btnCreateFillOnCask(ActionEvent event) {
        System.out.println("Idk");
        ap_Pane1.setDisable(true);
    }
}
