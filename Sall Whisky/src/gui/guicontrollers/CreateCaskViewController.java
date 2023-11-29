package gui.guicontrollers;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.List;

public class CreateCaskViewController {

//        private Supplier supplier;

        @FXML
        private Label SizeInLiterslbl;

        @FXML
        private TextField SizeInLiterstxf;

        @FXML
        private Label CountryOfOriginlbl;

        @FXML
        private TextField CountryOfOrigintxf;

        @FXML
        private TableView<?> Maintable;

        @FXML
        private TableColumn<?, ?> PositionColumn;

        @FXML
        private Label PreviousContentlbl;

        @FXML
        private TextField PreviousContenttxf;

        @FXML
        private TableColumn<?, ?> RackColumn;

        @FXML
        private TableColumn<?, ?> ShelfColumn;

        @FXML
        private Label Supplierlbl;

        @FXML
        private ListView<?> Supplierlvw;

        @FXML
        private TableColumn<?, ?> WarehouseColumn;

        @FXML
        private Button btnCreateCask;

        @FXML
        private Button btnFindWarehousePosition;

        @FXML
        private Button btnPickSupplier;

        @FXML
        private Button btnPickWarehousePosition;

        @FXML
        void btnCreateCask(ActionEvent event) {

        }

        @FXML
        void btnFindWarehousePosition(ActionEvent event) {
            List<Position> list = new ArrayList<>();
            double sizeInLiters = Double.parseDouble(SizeInLiterstxf.getText());
            Cask cask = new Cask(CountryOfOrigintxf.getText(), sizeInLiters, PreviousContenttxf.getText());

            MainController.getAvailableWarehouses(cask);
            for (Warehouse warehouse : MainController.getAvailableWarehouses(cask)) {
                for (Rack rack : MainController.getAvailableRacks(warehouse, cask)) {
                    for (Shelf shelf : MainController.getAvailableShelves(rack, cask)) {
                        for (Position position : MainController.getAvailablePositions(shelf, cask)) {
                            list.add(position);
                        }
                    }
                }
            }
        }

        @FXML
        void btnPickSupplier(ActionEvent event) {
//            this.supplier = Supplierlvw.getSelectionModel().getSelectedItem();
        }

        @FXML
        void btnPickWarehousePosition(ActionEvent event) {

        }

    }
