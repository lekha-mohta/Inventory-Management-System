package trainsheffield;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class TrackPack extends Product {
    private ArrayList<TrackPiece> trackPieces;
    private Map<String, Integer> trackPieceQuantities;

    /*
     **************
     * Get methods
     **************
     */

    public ArrayList<TrackPiece> getTrackPieces() {
        return trackPieces;
    }

    public Map<String, Integer> getTrackPieceQuantities() {
        return trackPieceQuantities;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setTrackPieces(ArrayList<TrackPiece> trackPieces) {
        this.trackPieces = trackPieces;
    }

    public void setTrackPieceQuantities(Map<String, Integer> trackPieces) {
        this.trackPieceQuantities = trackPieces;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    /**
     * Constructor for objects of class <code>TrackPack</code>. This default
     * version does not take a value for the
     * <code>stock</code> of the product and so initially sets the value to 0.
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param trackPieces
     * @param trackPieceQuantities
     */
    public TrackPack(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, ArrayList<TrackPiece> trackPieces,
            Map<String, Integer> trackPieceQuantities) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.trackPieces = trackPieces;
        this.trackPieceQuantities = trackPieceQuantities;
    }

    /**
     * Constructor for objects of class <code>TrackPack</code>. Overloaded version
     * of default
     * constructor that takes a value for <code>stock</code>.
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param trackPieces
     * @param trackPieceQuantities
     * @param stock
     */
    public TrackPack(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, ArrayList<TrackPiece> trackPieces,
            Map<String, Integer> trackPieceQuantities, int stock) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge, stock);
        this.trackPieces = trackPieces;
        this.trackPieceQuantities = trackPieceQuantities;
    }


    /*
     ******************
     * General Methods
     ******************
     */

    public int getQuantityOfTrackPiece(String trackProductCode) {
        return trackPieceQuantities.get(trackProductCode);
    }

    public void setQuantityOfTrackPiece(String trackProductCode, int quantity) {
        trackPieceQuantities.put(trackProductCode, quantity);
    }
}
