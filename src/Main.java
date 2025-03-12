import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    private FXMLLoader field = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
    @FXML
    private FXMLLoader inventory = new FXMLLoader(getClass().getResource("/fxml/Inventory.fxml"));
    @FXML
    private FXMLLoader market = new FXMLLoader(getClass().getResource("/fxml/Market.fxml"));

    @Override
    public void start(Stage stage) throws Exception {
        try {
            VBox root = new VBox();
            FXMLLoader menu = new FXMLLoader(getClass().getResource("/fxml/MenuBar.fxml"));
            root.getChildren().add(menu.load());
            root.getChildren().add(field.load());

            Scene scene = new Scene(root, 1000, 675);
            stage.setTitle("Votre champ");
            stage.setScene(scene);


            VBox inventoryRoot = new VBox();
            FXMLLoader menu2 = new FXMLLoader(getClass().getResource("/fxml/MenuBar.fxml"));
            inventoryRoot.getChildren().add(menu2.load());
            inventoryRoot.getChildren().add(inventory.load());
            Scene inventoryScene = new Scene(inventoryRoot, 1000, 675);

            VBox marketRoot = new VBox();
            FXMLLoader menu3 = new FXMLLoader(getClass().getResource("/fxml/MenuBar.fxml"));
            marketRoot.getChildren().add(menu3.load());
            marketRoot.getChildren().add(market.load());
            Scene marketScene = new Scene(marketRoot, 1000, 675);


            ImageView goToInventory = (ImageView) root.lookup("#goToInventory");
            ImageView goToMarket = (ImageView) root.lookup("#goToMarket");

            ImageView goToField = (ImageView) inventoryRoot.lookup("#goToField");
            ImageView goToMarket2 = (ImageView) inventoryRoot.lookup("#goToMarket");

            ImageView goToField2 = (ImageView) marketRoot.lookup("#goToField");
            ImageView goToInventory2 = (ImageView) marketRoot.lookup("#goToInventory");


            if (goToInventory != null) {
                goToInventory.setOnMouseClicked(event ->{
                    stage.setTitle("Inventaire");
                    stage.setScene(inventoryScene);
                });
            }

            if (goToInventory2 != null) {
                goToInventory2.setOnMouseClicked(event ->{
                    stage.setTitle("Inventaire");
                    stage.setScene(inventoryScene);
                });
            }

            if (goToField != null) {
                goToField.setOnMouseClicked(event -> {
                    stage.setTitle("Vos champs");
                    stage.setScene(scene);
                });
            }

            if (goToField2 != null) {
                goToField2.setOnMouseClicked(event -> {
                    stage.setTitle("Vos champs");
                    stage.setScene(scene);
                });
            }

            if (goToField != null) {
                goToMarket.setOnMouseClicked(event -> {
                    stage.setTitle("Marché");
                    stage.setScene(marketScene);
                });
            }

            if (goToField2 != null) {
                goToMarket2.setOnMouseClicked(event -> {
                    stage.setTitle("Marché");
                    stage.setScene(marketScene);
                });
            }


            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}