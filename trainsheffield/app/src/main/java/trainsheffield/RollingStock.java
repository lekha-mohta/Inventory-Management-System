package trainsheffield;

/**
 * @author Joel Foster
 */

import java.math.BigDecimal;

public class RollingStock extends Product {
    private String eraCode;

    /*
     **************
     * Get methods
     **************
     */

    public String getEraCode() {
        return eraCode;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setEraCode(String eraCode) {
        this.eraCode = eraCode;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    /**
     * Constructor for objects of class <code>RollingStock</code>. This default
     * version does not take a value for the
     * <code>stock</code> of the product and so initially sets the value to 0.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param eraCode
     */
    public RollingStock(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, String eraCode) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.eraCode = eraCode;
    }

    /**
     * Constructor for objects of class <code>RollingStock</code>. Overloaded
     * version of default
     * constructor that takes a value for <code>stock</code>
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param eraCode
     * @param stock
     */
    public RollingStock(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, String eraCode, int stock) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge, stock);
        this.eraCode = eraCode;
    }
}
