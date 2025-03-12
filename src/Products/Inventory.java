package Products;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {
    private final StringProperty products;
    private final IntegerProperty quantity;
    private final StringProperty type;
    private final IntegerProperty price;
    public Inventory(String products, int quantity, String type, int price) {
        this.products = new SimpleStringProperty(products);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.type = new SimpleStringProperty(type);
        this.price = new SimpleIntegerProperty(price);
    }

    public StringProperty productsProperty() {
        return products;
    }

    public String getProducts() {
        return products.get();
    }

    public void setProducts(String products) {
        this.products.set(products);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public IntegerProperty priceProperty() {
        return price;
    }
    public int getPrice() {
        return price.get();
    }
    public void setPrice(int price) {
        this.price.set(price);
    }
}
