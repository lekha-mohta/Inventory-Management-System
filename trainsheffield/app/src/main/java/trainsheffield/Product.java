package trainsheffield;

/**
 * @author Joel Foster
 */

import java.math.BigDecimal;

/**
 * Abstract class used as a base for subclasses of specific product types to
 * inherit from.
 */
abstract class Product {
    private String productCode;
    private String productName;
    private String manufacturer;
    private BigDecimal retailPrice;
    private String productType;
    private String gauge;
    private int stock;

    /*
     **************
     * Get methods
     **************
     */

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public String getProductType() {
        return productType;
    }

    public String getGauge() {
        return gauge;
    }

    public int getStock() {
        return stock;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setGauge(String gauge) {
        this.gauge = gauge;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    // Product constructor without value for stock, sets default value to 0
    /**
     * Constructor for objects of abstract class <code>Product</code>, used by
     * inherited subclasses. This default version does not take
     * a value for the <code>stock</code> of the product and so initially sets the
     * value to 0.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     */
    public Product(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge) {

        this.productCode = productCode;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.retailPrice = retailPrice;
        this.productType = productType;
        this.gauge = gauge;
        this.stock = 0;
    }

    // Constructor used when stock number is supplied
    /**
     * Constructor for objects of abstract class <code>Product</code>, used by
     * inherited subclasses. Overloaded version of default
     * constructor that takes a value for <code>stock</code>.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param stock
     */
    public Product(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, int stock) {
        this(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.stock = stock;
    }
}
