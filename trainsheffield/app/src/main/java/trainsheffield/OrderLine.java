package trainsheffield;

import java.math.BigDecimal;

public class OrderLine {

    private Product product;
    private int quantity;
    private BigDecimal retailPrice;

    public OrderLine(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.retailPrice = product.getRetailPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

}
