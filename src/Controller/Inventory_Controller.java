package Controller;

import Products.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class Inventory_Controller implements Initializable {

    @FXML
    private TableView<Inventory> inventoryTableView;

    @FXML
    private TableColumn<Inventory, String> products;

    @FXML
    private TableColumn<Inventory, Integer> quantity;

    private static ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

    private static Inventory_Controller instance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        products.setCellValueFactory(new PropertyValueFactory<>("products"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        if (inventoryList.isEmpty()) {
            inventoryList.add(new Inventory("Argent", 500, "money", 1));
            inventoryList.add(new Inventory("Plant de carottes", 5, "seed", 1));
            inventoryList.add(new Inventory("Veau", 1, "animal", 1));
        }

        inventoryTableView.setItems(inventoryList);
    }
    public void addToInventoryList(Inventory inventory) {
        for (Inventory item : inventoryList) {
            if (item.getProducts().equals(inventory.getProducts())) {
                item.setQuantity(inventory.getQuantity());
                Market_Controller controller = Market_Controller.getInstance();
                if (controller != null) {
                    controller.updateInventoryList(inventoryList);
                }

                return;
            }
        }
        inventoryList.add(inventory);
        Market_Controller controller = Market_Controller.getInstance();
        if (controller != null) {
            controller.updateInventoryList(inventoryList);
        }
    }

    public void printInventory() {
        System.out.println("Current Inventory:");
        for (Inventory item : inventoryList) {
            System.out.println(item.getProducts() + ": " + item.getQuantity());
        }
    }

    public static ObservableList<Inventory> getInventoryList() {return inventoryList;}

    public static int getMoneyFromInventory() {
        int money = 0;
        for (Inventory item : inventoryList) {
            if (item.getProducts().equals("Argent")) {
                money += item.getQuantity();
            }
        }
        return money;
    }

    public static void setMoneyFromInventory(int money) {
        for (Inventory item : inventoryList) {
            if (item.getProducts().equals("Argent")) {
                item.setQuantity(money);
            }
        }
    }

    public static Inventory_Controller getInstance() {
        return instance;
    }

    public void updateInventoryList(ObservableList<Inventory> newInventoryList) {
        if (inventoryTableView != null) {
            inventoryList.setAll(newInventoryList);
            inventoryTableView.refresh();
        }
    }
}
