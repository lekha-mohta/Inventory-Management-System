package trainsheffield;

/**
 * @author Joel Foster
 */

import java.math.BigDecimal;

/**
 * Class that is used to create instances of Locomotive objects.
 */
public class Locomotive extends Product {
    /**
     * Enum for set values of Dcc codes.
     */
    public enum DccCode {
        ANALOGUE, DCC_READY, DCC_FITTED, DCC_SOUND
    };

    private DccCode dccCode;
    private String eraCode;

    /*
     **************
     * Get methods
     **************
     */

    public String getEraCode() {
        return eraCode;
    }

    public DccCode getDccCode() {
        return dccCode;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setEraCode(String eraCode) {
        this.eraCode = eraCode;
    }

    public void setDccCode(DccCode dccCode) {
        this.dccCode = dccCode;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    // Constructor without value for stock, sets default value to 0
    /**
     * Constructor for objects of class <code>Locomotive</code>. This default
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
     * @param dccCode
     */
    public Locomotive(String productCode, String productName, String manufacturer,
            BigDecimal retailPrice, String productType, String gauge, String eraCode, DccCode dccCode) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.eraCode = eraCode;
        this.dccCode = dccCode;
    }

    /**
     * Constructor for objects of class <code>Locomotive</code>. Overloaded version
     * of default
     * constructor that takes a value for <code>stock</code>.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param eraCode
     * @param dccCode
     * @param stock
     */
    public Locomotive(String productCode, String productName, String manufacturer,
            BigDecimal retailPrice, String productType, String gauge, String eraCode, DccCode dccCode, int stock) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge,
                stock);
        this.eraCode = eraCode;
        this.dccCode = dccCode;
    }
}
