package Products;

import Controller.Inventory_Controller;
import Model.Vegetables;
import Utils.getLabel;
import Utils.setGridText;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class Carrot extends Vegetables {
    static Inventory inventory = new Inventory("Carotte", 0, "vegetable", 1);
    static Inventory_Controller inventory_controller = new Inventory_Controller();
    private String name = "Carotte";
    private int timeToGrow = 3;
    final String [] statut = {".", "c", "C"};
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

        timeline.setCycleCount(this.statut.length - 1);
        timeline.play();

    }

    @Override
    public void changeStatus(int statutIndex) {

        setGridText.setText(gridPane, row, column, this.statut[statutIndex]);
        if (statutIndex == this.statut.length - 1) {
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
        inventory.setQuantity(inventory.getQuantity() + quantityHarvest);
        inventory_controller.addToInventoryList(inventory);

        setGridText.setText(gridPane, row, column, "");
    }

}
