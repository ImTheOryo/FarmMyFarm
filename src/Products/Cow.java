package Products;

import Controller.Inventory_Controller;
import Model.Animals;
import Utils.getLabel;
import Utils.setGridText;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cow extends Animals {
    private GridPane gridPane;
    private int row;
    private int column;
    static Inventory cowInInventory = new Inventory("Vache", 0, "animal", 1);
    static Inventory milkInInventory = new Inventory("Lait", 0, "product", 10);
    static Inventory_Controller inventoryController = new Inventory_Controller();

    private int timeToGrow = 1;
    final int MIN_MILK_QUANTITY_HARVEST = 1;
    final int MAX_MILK_QUANTITY_HARVEST = 5;
    final String [] statut = {"v", "v+F","V", "V+L"};

    @FXML
    public Button getProduction;

    @FXML
    public Button removeFromField;

    @FXML
    public AnchorPane modalWhatToDo;

    private static Cow instance;

    @Override
    public void addBabyToField(GridPane gridPane, int row, int column) {
        this.gridPane = gridPane;
        this.row = row;
        this.column = column;
        instance = this;
        growAnimal();
    }

    @Override
    public void addAdultToField(GridPane gridPane, int row, int column) {
        this.gridPane = gridPane;
        this.row = row;
        this.column = column;
        instance = this;
        AtomicInteger statusIndex = new AtomicInteger(1);
        changeStatus(statusIndex.get());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(this.timeToGrow), event -> {
            statusIndex.getAndIncrement();
            changeStatus(statusIndex.get());
        }));


        timeline.setCycleCount(this.statut.length - 2);
        timeline.play();
    }

    @Override
    public void babyToAdult() {
        Inventory cornInventory = new Inventory("Maïs", 0, "Seed", 1);

        cornInventory.setQuantity(cornInventory.getQuantity() - 1);
        inventoryController.addToInventoryList(cornInventory);

    }


    @Override
    public void growAnimal() {

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
    public void changeStatus(int statusIndex) {
        setGridText.setText(gridPane, row, column, this.statut[statusIndex]);
        if (statusIndex == this.statut.length - 3){
            babyToAdult();
        }

        if (statusIndex == this.statut.length - 1) {
            Label label = getLabel.getLabel(gridPane, row, column);
            label.setOnMouseClicked(mouseEvent ->{
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modalWhatToDo.fxml"));

                    Cow newController = instance;
                    loader.setController(newController);

                    AnchorPane modalContent = loader.load();

                    Stage modalStage = new Stage();
                    modalStage.initModality(Modality.APPLICATION_MODAL);
                    modalStage.setScene(new Scene(modalContent, 400, 200));
                    modalStage.centerOnScreen();
                    modalStage.show();

                } catch (IOException e){
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Erreur lors du chargement de la modale", e);

                }
            });
        }
    }

    @Override
    public void getProductFromAnimal() {
        int quantityFromAnimal = (int) (Math.random() * (MAX_MILK_QUANTITY_HARVEST - MIN_MILK_QUANTITY_HARVEST + 1) + MIN_MILK_QUANTITY_HARVEST);
        System.out.println(String.format("%d %s récolté", quantityFromAnimal, "Lait"));
        milkInInventory.setQuantity(milkInInventory.getQuantity() + quantityFromAnimal);
        inventoryController.addToInventoryList(milkInInventory);
        setGridText.setText(gridPane, row, column, "V");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(this.timeToGrow), event -> {
            changeStatus(statut.length - 1);
        }));

        timeline.play();
    }

    @Override
    public void removeAnimalFromField() {
        cowInInventory.setQuantity(cowInInventory.getQuantity() + 1);
        int quantityFromAnimal = (int) (Math.random() * (MAX_MILK_QUANTITY_HARVEST - MIN_MILK_QUANTITY_HARVEST + 1) + MIN_MILK_QUANTITY_HARVEST);
        System.out.println(String.format("%d %s récolté", quantityFromAnimal, "Lait"));
        milkInInventory.setQuantity(milkInInventory.getQuantity() + quantityFromAnimal);
        inventoryController.addToInventoryList(milkInInventory);
        inventoryController.addToInventoryList(cowInInventory);
        setGridText.setText(gridPane, row, column, "");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getProduction.setOnMouseClicked(event -> {
            System.out.println("Production");
           getProductFromAnimal();
            modalWhatToDo.getScene().getWindow().hide();

        });

        removeFromField.setOnMouseClicked(event -> {
            removeAnimalFromField();
            modalWhatToDo.getScene().getWindow().hide();
        });
    }

    public static Cow getInstance() {
        return instance;
    }
}
