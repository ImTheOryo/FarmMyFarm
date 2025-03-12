package Model;


import Controller.Field_Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

    public abstract class Animals implements Initializable {
        private Field_Controller fieldController;

        @FXML
        public Button getProduction;

        @FXML
        public Button removeFromField;
        public abstract void addBabyToField(GridPane gridPane, int row, int column);

        public abstract void addAdultToField(GridPane gridPane, int row, int column);

        public abstract void babyToAdult();

        public abstract void growAnimal();

        public abstract void changeStatus(int statusIndex);

        public abstract void getProductFromAnimal();

        public abstract void removeAnimalFromField();

        public void setFieldController(Field_Controller fieldController) {
            this.fieldController = fieldController;
        }

    }
