package Model;

import javafx.scene.layout.GridPane;

public abstract class Vegetables {

    public abstract void plantProduct (GridPane gridPane, int row, int column);

    public abstract void growProduct ();

    public abstract void changeStatus (int statusIndex);

    public abstract void harvestProduct ();

}
