package Controller;

import Products.Carrot;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

    public class Field_Controller {



        @FXML
        private GridPane GridField;

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

        public void GridClicked(MouseEvent mouseEvent) {
            int column = (int) Math.floor((mouseEvent.getX() / 160));
            int row = (int) (Math.floor((mouseEvent.getY() / 100)));

            for (Node node : GridField.getChildren()) {
                if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
                } else {
                    if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                        if (!VerifyIsUsed(column, row)) {
                            Carrot carrot = new Carrot();
                            carrot.plantProduct(GridField, row, column);
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

                       String [] test = {".","c","C"};
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

    }
