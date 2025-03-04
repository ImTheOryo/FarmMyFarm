import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private FXMLLoader field = new FXMLLoader(getClass().getResource("/fxml/Field.fxml"));
    private FXMLLoader inventory = new FXMLLoader(getClass().getResource("/fxml/Inventory.fxml"));

    @Override
    public void start(Stage stage) throws Exception {
        try {
            VBox root = new VBox();
            FXMLLoader menu = new FXMLLoader(getClass().getResource("/fxml/MenuBar.fxml"));
            root.getChildren().add(menu.load());
            root.getChildren().add(field.load());

            Scene scene = new Scene(root, 1000, 600);

            VBox inventoryRoot = new VBox();
            FXMLLoader menu2 = new FXMLLoader(getClass().getResource("/fxml/MenuBar.fxml"));
            inventoryRoot.getChildren().add(menu2.load());
            inventoryRoot.getChildren().add(inventory.load());
            Scene inventoryScene = new Scene(inventoryRoot, 1000, 600);

            // Rechercher les labels après le chargement
            Label goToInventory = (Label) root.lookup("#goToInventory");
            Label goToField = (Label) inventoryRoot.lookup("#goToField");

            if (goToInventory != null) {
                goToInventory.setOnMouseClicked(event -> stage.setScene(inventoryScene));
            }

            if (goToField != null) {
                goToField.setOnMouseClicked(event -> stage.setScene(scene));
            }

            stage.setTitle("Farm my farm Champs");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}