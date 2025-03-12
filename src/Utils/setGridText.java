package Utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

    public class setGridText {

        public static void setText (GridPane GridField,int row, int column, String text) {
            if (GridField != null){
                for (Node node : GridField.getChildren()) {
                    if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
                    } else {
                        if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                            ((Label) node).setText(text);

                        }
                    }
                }
            } else {
                System.out.println("GridField is null");
            }

        }
    }
