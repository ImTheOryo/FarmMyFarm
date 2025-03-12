package Controller;

import Products.Carrot;
import Products.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Modal_Add_To_Field_Controller implements Initializable {

    private Field_Controller fieldController;

    public TableColumn<Inventory, String> seedOrAnimalLabel;

    public TableColumn<Inventory, Integer> seedOrAnimalQuantity;

    public Button selectedSeedOrAnimal;

    public TableView<Inventory> seedOrAnimalTableView;

    public  ObservableList<Inventory> modalInventoryList = FXCollections.observableArrayList();

    public AnchorPane seedOrAnimalModal;

    ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory_Controller.getInventoryList());

    private static Modal_Add_To_Field_Controller instance;

    public void setFieldController(Field_Controller fieldController) {
        this.fieldController = fieldController;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        seedOrAnimalLabel.setCellValueFactory(new PropertyValueFactory<>("products"));
        seedOrAnimalQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


        for (Inventory item : inventoryList) {
            if (item.getType().equals("seed") || item.getType().equals("animal")) {
                modalInventoryList.add(item);
            }
        }
        seedOrAnimalTableView.setItems(modalInventoryList);

        selectedSeedOrAnimal.setOnMouseClicked(event -> {
            TableView.TableViewSelectionModel<Inventory> selectionModel = seedOrAnimalTableView.getSelectionModel();

            if (selectionModel.isEmpty()) {
                System.out.println("Veuillez sélectionner un plant ou un animal");
            }

            ObservableList<Integer> list = selectionModel.getSelectedIndices();
            Integer[] selectedIndices = new Integer[list.size()];
            selectedIndices = list.toArray(selectedIndices);

            Arrays.sort(selectedIndices);


            if (fieldController != null) {

                if (modalInventoryList.get(selectedIndices[0]).getQuantity() > 0) {
                    fieldController.plantIntoTheGrid(modalInventoryList.get(selectedIndices[0]).getProducts());
                    modalInventoryList.get(selectedIndices[0]).setQuantity(modalInventoryList.get(selectedIndices[0]).getQuantity() - 1);
                    seedOrAnimalModal.getScene().getWindow().hide();
                } else {
                    System.out.println("Il n'y a plus de " + modalInventoryList.get(selectedIndices[0]).getProducts() + " dans l'inventaire");
                }
            }

        });

    }

    public static Modal_Add_To_Field_Controller getInstance() {
        return instance;
    }

    public void initialiseInventoryList(ObservableList<Inventory> newInventoryList){
        if (seedOrAnimalTableView == null){
            inventoryList.setAll(newInventoryList);
            seedOrAnimalTableView.setItems(inventoryList);
            seedOrAnimalTableView.refresh();
        }
    }

    public void updateInventoryList(ObservableList<Inventory> newInventoryList) {
        if (seedOrAnimalTableView != null) {
            inventoryList.setAll(newInventoryList);
            seedOrAnimalTableView.refresh();
        }
    }
}
