package gui.guicontrollers;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Whisky;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateWhiskyStoryViewController implements Initializable {

        private MainViewController mainViewController;
        @FXML
        private Button btnCopyStory;

        @FXML
        private Label whiskyStorylbl;

        @FXML
        private TextArea whiskyStorytxa;

    /**
     * Copies the clipboard to the TextArea
     */
        @FXML
            void btnCopyStory(ActionEvent event) {
                String ctc = whiskyStorytxa.getText().toString();
                StringSelection stringSelection = new StringSelection(ctc);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }

            public void initialize(URL url, ResourceBundle resourceBundle) {
            whiskyStorytxa.setText(Controller.generateStoryForWhisky(mainViewController.getWhisky()));
            }

        }

