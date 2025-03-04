package Products;

import Model.Vegetables;
import Utils.getLabel;
import Utils.setGridText;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class Carrot extends Vegetables {

    private String name = "Carotte";
    private double unitPrice = 1.59;
    private int timeToGrow = 3;
    private String [] status = {".", "c", "C"};
    final int MIN_QUANTITY_HARVEST = 2;
    final int MAX_QUANTITY_HARVEST = 10;
    private int row;
    private int column;
    private GridPane gridPane;

    @Override
    public void plantProduct(GridPane gridPane, int row, int column) {
        this.gridPane = gridPane;
        this.row = row;
        this.column = column;
        growProduct();
    }

    @Override
    public void growProduct() {

        AtomicInteger statusIndex = new AtomicInteger();
        changeStatus(statusIndex.get());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(this.timeToGrow), event -> {
            statusIndex.getAndIncrement();
            changeStatus(statusIndex.get());
        }));

        timeline.setCycleCount(this.status.length - 1);
        timeline.play();

    }

    @Override
    public void changeStatus(int statusIndex) {

        setGridText.setText(gridPane, row, column, this.status[statusIndex]);
        if (statusIndex == this.status.length - 1) {
            Label label = getLabel.getLabel(gridPane, row, column);
            label.setOnMouseClicked(event -> {
                harvestProduct();
                event.consume();
            });
        }
    }

    @Override
    public void harvestProduct() {

        int quantityHarvest = (int) (Math.random() * (MAX_QUANTITY_HARVEST - MIN_QUANTITY_HARVEST + 1) + MIN_QUANTITY_HARVEST);
        System.out.println(String.format("%d %s récolté", quantityHarvest, this.name));
        setGridText.setText(gridPane, row, column, "");
    }


}
