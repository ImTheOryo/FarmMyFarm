package Utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class getLabel {
    public static Label getLabel (GridPane GridField, int row, int column) {
        for (Node node : GridField.getChildren()) {
            if (GridPane.getColumnIndex(node) == null && GridPane.getRowIndex(node) == null) {
            } else {
                if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                    return ((Label) node);

                }
            }
        }
        return null;
    }
}
