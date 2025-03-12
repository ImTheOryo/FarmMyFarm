package Controller;

import Products.Carrot;
import Products.Corn;
import Products.Cow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

    public class Field_Controller {
        private int row;
        private int column;

        @FXML
        public GridPane GridField;

        @FXML
        private void initialize() {
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    Label label = new Label("");
                    GridField.add(label, i, j);
                    GridField.setHalignment(label, HPos.CENTER);
                    GridField.setValignment(label, VPos.CENTER);

                }
            }
        }
        @FXML
        public void GridClicked(MouseEvent mouseEvent) throws IOException {
            this.column = (int) Math.floor((mouseEvent.getX() / 160));
            this.row = (int) (Math.floor((mouseEvent.getY() / 100)));

            for (Node node : GridField.getChildren()) {
                if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
                } else {
                    if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                        if (!VerifyIsUsed(column, row)) {

                            FXMLLoader modalSeedOrAnimalLoader = new FXMLLoader(getClass().getResource("/fxml/ModalAddToField.fxml"));
                            VBox root = new VBox();
                            root.getChildren().add(modalSeedOrAnimalLoader.load());

                            Modal_Add_To_Field_Controller modalController = modalSeedOrAnimalLoader.getController();
                            modalController.setFieldController(this);

                            Scene scene = new Scene(root,600,300);
                            Stage stage = new Stage();
                            stage.setTitle("Add to field");
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                }
            }

        }

        public boolean VerifyIsUsed(int column, int row){
           for(Node node : GridField.getChildren()){
               if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null){
               } else {
                   if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row){

                       String [] test = {".","c","C","v","V","V+L"};
                       for (String s : test){
                           if ( ((Label) node).getText().contains(s)){
                               return true;
                           }
                       }
                   }
               }
           }
           return false;
        }

        public void plantIntoTheGrid(String planting){

            for (Node node : GridField.getChildren()) {
                if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
                } else {
                    if (GridPane.getColumnIndex(node) == this.column && GridPane.getRowIndex(node) == this.row) {
                        switch (planting) {
                            case "Plant de carottes":
                                Carrot carrot = new Carrot();
                                carrot.plantProduct(GridField, this.row, this.column);
                                break;
                            case "Plant de maïs":
                                Corn corn = new Corn();
                                corn.plantProduct(GridField, this.row, this.column);
                                break;
                            case "Veau":
                                Cow cow = new Cow();
                                cow.addBabyToField(GridField, this.row, this.column);
                                break;
                            case "Vache":
                                Cow cow2 = new Cow();
                                cow2.addAdultToField(GridField, this.row, this.column);
                                break;
                        }
                    }
                }
            }
        }

    }

