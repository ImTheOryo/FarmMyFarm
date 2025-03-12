package Products;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Market {
    private final StringProperty productName;
    private final IntegerProperty unitPrice;
    public Market(String productName, int unitPrice) {
        this.productName = new SimpleStringProperty(productName);
        this.unitPrice = new SimpleIntegerProperty(unitPrice);
    }
    public StringProperty productNameProperty() {
        return productName;
    }
    public IntegerProperty unitPriceProperty() {
        return unitPrice;
    }
    public String getProductName() {
        return productName.get();
    }
    public void setProductName(String productName) {
        this.productName.set(productName);
    }
    public int getUnitPrice() {
        return unitPrice.get();
    }
    public void setUnitPrice(int unitPrice) {
        this.unitPrice.set(unitPrice);
    }
}
