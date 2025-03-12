package Controller;

import Products.Inventory;
import Products.Market;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Market_Controller implements Initializable {
    @FXML
    private TableView<Inventory> inventoryTableVIew;

    @FXML
    private TableColumn<Inventory, String> inventoryProduct;

    @FXML
    private TableColumn<Inventory, Integer> inventoryQuantity;

    @FXML
    private TableColumn<Inventory, Integer> productSellPrice;

    @FXML
    private TableView<Market> marketTableView;

    @FXML
    private TableColumn<Market, String> marketProduct;

    @FXML
    private TableColumn<Market, Integer> marketBuyPrice;

    @FXML
    private TextField sellQuantity;

    @FXML
    private Button sellButton;

    @FXML
    private TextField buyQuantity;

    @FXML
    private Button buyButton;

    private ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory_Controller.getInventoryList());

    private ObservableList<Market> marketList = FXCollections.observableArrayList();

    private static Market_Controller instance;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        inventoryProduct.setCellValueFactory(new PropertyValueFactory<>("products"));
        inventoryQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productSellPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryTableVIew.setItems(inventoryList);


        marketProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        marketBuyPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        if (marketList.isEmpty()) {
            marketList.add(new Market("Plant de carottes", 5));
            marketList.add(new Market("Plant de maïs",10 ));
            marketList.add(new Market("Veau", 75));
        }
        marketTableView.setItems(marketList);

        sellButton.setOnMouseClicked(event -> {
            TableView.TableViewSelectionModel<Inventory> selectionModel = inventoryTableVIew.getSelectionModel();

            if (selectionModel.isEmpty()) {
                System.out.println("Veuillez sélectionner un produit à vendre");
            }

            ObservableList<Integer> list = selectionModel.getSelectedIndices();
            Integer[] selectedIndices = new Integer[list.size()];
            selectedIndices = list.toArray(selectedIndices);
            int quantityToSell = 0;
            Arrays.sort(selectedIndices);

            try{
                quantityToSell = Integer.parseInt(sellQuantity.getText());

            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre");
            }

            if (quantityToSell != 0) {
                String productSellName = inventoryList.get(selectedIndices[0]).getProducts();
                int productSellPrice = inventoryList.get(selectedIndices[0]).getPrice();
                int quantityInInventory = inventoryList.get(selectedIndices[0]).getQuantity();
                if (quantityToSell <= quantityInInventory){
                    Inventory_Controller.setMoneyFromInventory(Inventory_Controller.getMoneyFromInventory() + (quantityToSell * productSellPrice));
                    inventoryList.get(selectedIndices[0]).setQuantity(quantityInInventory - quantityToSell);
                    inventoryTableVIew.refresh();
                }
                else {
                    System.out.println("Il n'y a pas assez de " + productSellName + " dans l'inventaire");
                }
            } else {
                System.out.println("Veuillez mettre un nombre pour vendre");
            }
        });


        buyButton.setOnMouseClicked(event -> {
            TableView.TableViewSelectionModel<Market> selectionModel = marketTableView.getSelectionModel();

            if (selectionModel.isEmpty()) {
                System.out.println("Veuillez sélectionner un produit à acheter");
            }

            ObservableList<Integer> list = selectionModel.getSelectedIndices();
            Integer[] selectedIndices = new Integer[list.size()];
            selectedIndices = list.toArray(selectedIndices);
            int quantity = 0;
            Arrays.sort(selectedIndices);

            try{
                 quantity = Integer.parseInt(buyQuantity.getText());

            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre");
            }

            if (quantity != 0) {

                String productBuyName = marketList.get(selectedIndices[0]).getProductName();
                int productBuyPrice = marketList.get(selectedIndices[0]).getUnitPrice();
                int currentMoney = Inventory_Controller.getMoneyFromInventory();
                int moneyToBuyProduct = quantity * productBuyPrice;

                if (currentMoney >= moneyToBuyProduct) {
                    currentMoney -= moneyToBuyProduct;
                    for (Inventory item : inventoryList) {
                        if (item.getProducts().equals(productBuyName)) {
                            item.setQuantity(item.getQuantity() + quantity);
                            break;
                        }
                    }
                    if (productBuyName.contains("Plant de")){
                        inventoryList.add(new Inventory(productBuyName, quantity, "seed", 1));
                    } else {
                        inventoryList.add(new Inventory(productBuyName, quantity, "animal", 1));
                    }
                    Inventory_Controller.setMoneyFromInventory(currentMoney);
                    marketTableView.refresh();

                    Inventory_Controller inventoryController = Inventory_Controller.getInstance();
                    if (inventoryController != null) {
                        inventoryController.updateInventoryList(inventoryList);
                    }

                    Modal_Add_To_Field_Controller modalController = Modal_Add_To_Field_Controller.getInstance();
                    if (modalController != null) {
                        modalController.updateInventoryList(inventoryList);
                    } else {
                        modalController = new Modal_Add_To_Field_Controller();
                        modalController.updateInventoryList(inventoryList);
                    }

                } else {
                    System.out.println(currentMoney);
                    System.out.println("Vous n'avez pas assez d'argent pour acheter ce produit");
                }

            } else {
                System.out.println("Veuillez mettre un nombre supérieur a 0");
            }

        });

    }

    public static Market_Controller getInstance() {
        return instance;
    }

    public void updateInventoryList(ObservableList<Inventory> newInventoryList) {
        if (inventoryTableVIew != null) {
            inventoryList.setAll(newInventoryList);
            inventoryTableVIew.refresh();
        }
    }



}