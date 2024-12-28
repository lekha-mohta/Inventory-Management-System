package trainsheffield;

/**
 * @author Joel Foster
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class TrainSet extends Product {
    private String eraCode;
    private Controller controller;
    private ArrayList<TrackPack> trackPacks;
    private ArrayList<Locomotive> locomotives;
    private ArrayList<RollingStock> rollingStocks;
    private Map<String, Integer> quantities;

    /*
     **************
     * Get methods
     **************
     */

    public String getEraCode() {
        return eraCode;
    }

    public Controller getController() {
        return controller;
    }

    public ArrayList<TrackPack> getTrackPacks() {
        return trackPacks;
    }

    public ArrayList<Locomotive> getLocomotives() {
        return locomotives;
    }

    public ArrayList<RollingStock> getRollingStocks() {
        return rollingStocks;
    }

    public Map<String, Integer> getQuantities() {
        return quantities;
    }

    /*
     **************
     * Set methods
     **************
     */

    public void setEraCode(String eraCode) {
        this.eraCode = eraCode;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setTrackPacks(ArrayList<TrackPack> trackPack) {
        this.trackPacks = trackPack;
    }

    public void setLocomotives(ArrayList<Locomotive> locomotives) {
        this.locomotives = locomotives;
    }

    public void setRollingStocks(ArrayList<RollingStock> rollingStocks) {
        this.rollingStocks = rollingStocks;
    }

    public void setQuantities(Map<String, Integer> quantities) {
        this.quantities = quantities;
    }

    /*
     ***************
     * Constructors
     ***************
     */

    /**
     * Constructor for objects of class <code>TrainSet</code>. This default version
     * does not take a value for the
     * <code>stock</code> of the product and so initially sets the value to 0.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param eraCode
     * @param controller
     * @param trackPacks
     * @param locomotives
     * @param rollingStocks
     * @param quantities
     */
    public TrainSet(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, String eraCode, Controller controller, ArrayList<TrackPack> trackPacks,
            ArrayList<Locomotive> locomotives,
            ArrayList<RollingStock> rollingStocks, Map<String, Integer> quantities) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge);
        this.eraCode = eraCode;
        this.controller = controller;
        this.trackPacks = trackPacks;
        this.locomotives = locomotives;
        this.rollingStocks = rollingStocks;
        this.quantities = quantities;
    }

    /**
     * Constructor for objects of class <code>TrainSet</code>. Overloaded version of
     * default
     * constructor that takes a value for <code>stock</code>.
     *
     * @param productCode
     * @param productName
     * @param manufacturer
     * @param retailPrice
     * @param productType
     * @param gauge
     * @param eraCode
     * @param controller
     * @param trackPack
     * @param locomotives
     * @param rollingStocks
     * @param quantities
     * @param stock
     */
    public TrainSet(String productCode, String productName, String manufacturer, BigDecimal retailPrice,
            String productType, String gauge, String eraCode, Controller controller, ArrayList<TrackPack> trackPacks,
            ArrayList<Locomotive> locomotives,
            ArrayList<RollingStock> rollingStocks, Map<String, Integer> quantities, int stock) {

        super(productCode, productName, manufacturer, retailPrice, productType, gauge, stock);
        this.eraCode = eraCode;
        this.controller = controller;
        this.trackPacks = trackPacks;
        this.locomotives = locomotives;
        this.rollingStocks = rollingStocks;
        this.quantities = quantities;
    }
}
