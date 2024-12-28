package trainsheffield;

import java.math.BigDecimal;

public class TrackPiece extends Product {
    /*
     ***************
     * Constructors
     ***************
     */

    /**
     * Constructor for objects of class <code>TrackPiece</code>. This default
     * version does not take a value for the
     * <code>stock</code> of the product and so initially sets the value to 0.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     */
    public TrackPiece(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
    }

    /**
     * Constructor for objects of class <code>TrackPiece</code>. Overloaded version
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
     */
    public TrackPiece(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, int stock) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge, stock);
    }
}
