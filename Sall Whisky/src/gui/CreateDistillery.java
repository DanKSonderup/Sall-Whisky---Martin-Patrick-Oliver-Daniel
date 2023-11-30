package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Maltbatch;
import storage.ListStorage;


public class CreateDistillery extends Application {
    @Override
    public void start(Stage stage) {
        GridPane pane = new GridPane();
        this.initContent(pane);
        pane.setMinHeight(600);
        pane.setMinWidth(750);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //-----------------------------------------------------------
    // Data felter til Gridpane indsættes her

    ListStorage listStorage;
    private Label lblAvailableMaltBatches = new Label("Tilgængelige maltbatches");
    private Label lblShowSelectedMaltBatches = new Label("Valgte maltbatches");
    private final ListView<Maltbatch> lvwMaltBatch = new ListView<>();
    private final ListView<Maltbatch> lvwShowSelectedMaltBatches = new ListView<>();
    private final TextField txfNewMakeNr = new TextField();
    private final TextField txfMaltBatch = new TextField();
    private final TextField txfEmployee = new TextField();
    private final TextField txfDistillationTime = new TextField();
    private final TextField txfAmountOfLiquid = new TextField();
    private final TextField txfSmokingMaterial = new TextField();
    private final TextField txfAlkohol = new TextField();
    private Button btnAccept = new Button("Opret destillat");
    private Button btnCancel = new Button("Anuller");
    private Accordion acMaltBatches = new Accordion();






    public void initContent (GridPane pane) {
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // lvwMaltBatch.getItems().setAll(listStorage.getMaltBatches);

        Label lblNewMakeNumber = new Label("New Make Nr");
        pane.add(lblNewMakeNumber,0,0);
        pane.add(txfNewMakeNr,1,0);

        Label lblMaltBatch = new Label("Maltbatch(es)");
        pane.add(lblMaltBatch,0,1);

        TitledPane tpMaltBatches = showMaltBatches("Maltbatches");
        acMaltBatches.getPanes().addAll(tpMaltBatches);
        pane.add(acMaltBatches, 1, 1);

        /*Label lblselectedMaltBatches = new Label("Valgte maltbatch(es)");
        pane.add(lblselectedMaltBatches, 3, 1);
        pane.add(lvwShowSelectedMaltBatches, 3, 2);*/

        Label lblEmployee = new Label("Medarbejder");
        pane.add(lblEmployee,0,3);
        pane.add(txfEmployee,1,3);

        // TextFields.bindAutoCompletion(txfEmployee, listStorage.getEmployees());


        Label lblDistillationtime = new Label("Destilleringstid");
        pane.add(lblDistillationtime,0,4);
        pane.add(txfDistillationTime,1,4);

        Label lblAmountOfLiquid = new Label("Væske mængde");
        pane.add(lblAmountOfLiquid,0,5);
        pane.add(txfAmountOfLiquid,1,5);

        Label lblSmokingmaterial = new Label("Rygemateriale");
        pane.add(lblSmokingmaterial,0,6);
        pane.add(txfSmokingMaterial,1,6);

        Label lblAlkoholProcentage = new Label("Alkohol");
        pane.add(lblAlkoholProcentage,0,7);
        pane.add(txfAlkohol,1,7);

        Label lblLiter = new Label("L");
        pane.add(lblLiter,2,5);

        Label lblProcentage = new Label("%");
        pane.add(lblProcentage,2,7);

        pane.add(btnAccept, 1, 8);
        pane.add(btnCancel, 2, 8);
    }

    private TitledPane showMaltBatches(String title) {
        ListView<Maltbatch> maltBatchListView = new ListView<>();
        // maltBatchListView.getItems().setAll(listStorage.getMaltBatches);

        VBox vBox = new VBox(lblAvailableMaltBatches, maltBatchListView, lblShowSelectedMaltBatches, lvwShowSelectedMaltBatches);

        TitledPane titledPane = new TitledPane(title, vBox);
        titledPane.setText(title);
        return titledPane;
    }
}
