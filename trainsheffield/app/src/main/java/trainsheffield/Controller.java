package trainsheffield;

import java.math.BigDecimal;

public class Controller extends Product {
    private boolean digital;

    /*
     **************
     * Get methods
     **************
     */

    public boolean getDigital() {
        return digital;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    /**
     * Constructor for objects of class <code>Controller</code>. This default
     * version does not take a value for the
     * <code>stock</code> of the product and so initially sets the value to 0.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param digital
     */
    public Controller(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, boolean digital) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.digital = digital;
    }

    /**
     * Constructor for objects of class <code>Controller</code>. Overloaded version
     * of default
     * constructor that takes a value for <code>stock</code>.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param stock
     * @param digital
     */
    public Controller(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, boolean digital, int stock) {
        super(productCode, productName, manufacturer, retailPrice, productType, gauge, stock);
        this.digital = digital;
    }
}
