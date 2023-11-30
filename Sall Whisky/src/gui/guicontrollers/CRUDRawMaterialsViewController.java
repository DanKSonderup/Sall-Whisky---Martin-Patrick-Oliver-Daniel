package gui.guicontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CRUDRawMaterialsViewController {

    @FXML
    private Button btnCreateField;

    @FXML
    private Button btnCreateGrain;

    @FXML
    private Button btnCreateGrainSupplier;

    @FXML
    private Button btnCreateMaltbatch;

    @FXML
    private ComboBox<?> cbbPickGrainSupplier;

    @FXML
    private ListView<?> lvwFields;

    @FXML
    private ListView<?> lvwGrains;

    @FXML
    private TextArea txaCultivationDescription;

    @FXML
    private TextArea txaFieldDescription;

    @FXML
    private TextArea txaMaltbatchDescription;

    @FXML
    private TextField txfFieldName;

    @FXML
    private TextField txfGrainType;
}
