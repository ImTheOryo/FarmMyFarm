package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class Inventory_Controller {

        @FXML
        public TableView<String> inventoryTableView;
        public ArrayList<String> inventoryList = new ArrayList<>();
        public void initialize(){
            this.inventoryList.add("500");
        }

        public static void updateInventory(){
        }
    }
