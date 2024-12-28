package trainsheffield;

/**
 * @author Joel Foster
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;

import trainsheffield.Locomotive.DccCode;
import trainsheffield.Order.Status;
import trainsheffield.Users.Roles;

public class DatabaseOperations {

    private static Connection connection = DatabaseManager.getConnection();

    /*
     * METHODS RELATED TO PRODUCTS
     */

    /**
     * Takes a <code>ResultSet</code> object and parses its contents into a product
     * object.
     *
     * @param resultSet <code>ResultSet</code> to be parsed
     * @return Result of the parse as a <code>Product</code> object
     */
    private static Product parseResultSet(ResultSet resultSet) {
        Product product = null;

        try {
            String productCode = resultSet.getString("product_id");
            String productType = resultSet.getString("category");
            String productName = resultSet.getString("name");
            String manufacturer = resultSet.getString("brand");
            BigDecimal retailPrice = resultSet.getBigDecimal("price");
            String gauge = resultSet.getString("gauge");
            int stock = resultSet.getInt("quantity");
            String eraCode = resultSet.getString("era_code");

            // sets up relevant object based on product type
            if (productType.equals("locomotive")) {
                String dccCodeString = resultSet.getString("dcc_code");
                DccCode dccCode = null;
                try {
                    dccCode = DccCode.valueOf(dccCodeString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                product = new Locomotive(productCode, productName, manufacturer, retailPrice, productType, gauge,
                        eraCode, dccCode, stock);
            } else if (productType.equals("rolling stock")) {
                product = new RollingStock(productCode, productName, manufacturer, retailPrice, productType,
                        gauge, eraCode, stock);
            } else if (productType.equals("train set")) {
                Controller controller = selectControllerFromTrainSet(productCode);
                ArrayList<TrackPack> trackPacks = selectTrackPacksFromTrainSet(productCode);
                ArrayList<Locomotive> locomotives = selectLocomotivesFromTrainSet(productCode);
                ArrayList<RollingStock> rollingStock = selectRollingStockFromTrainSet(productCode);
                Map<String, Integer> quantities = selectBoxSetQuantities(productCode);

                product = new TrainSet(productCode, productName, manufacturer, retailPrice, productType, gauge, eraCode,
                        controller, trackPacks, locomotives, rollingStock, quantities, stock);
            } else if (productType.equals("controller")) {
                Boolean digital = resultSet.getBoolean("digital");
                product = new Controller(productCode, productName, manufacturer, retailPrice, productType, gauge,
                        digital, stock);
            } else if (productType.equals("track pack")) {
                ArrayList<TrackPiece> trackPieces = selectTrackPiecesFromTrackPack(productCode);
                Map<String, Integer> quantities = selectBoxSetQuantities(productCode);

                product = new TrackPack(productCode, productName, manufacturer, retailPrice, productType, gauge,
                        trackPieces, quantities, stock);
            } else if (productType.equals("track piece")) {
                product = new TrackPiece(productCode, productName, manufacturer, retailPrice, productType, gauge,
                        stock);
            }

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    /**
     * Returns an <code>ArrayList</code> of products in the database that correspond
     * to a given search string.
     *
     * @param search String used to search database
     * @return <code>ArrayList</code> of all related products in their given object
     *         form
     * @throws SQLException
     */
    public static ArrayList<Product> selectSearchedProducts(String search) throws SQLException {
        try {
            ArrayList<Product> products = new ArrayList<>();

            String selectSQL = "SELECT * FROM Products WHERE (product_id LIKE ?) OR " +
                    "(name LIKE ?) OR (brand LIKE ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns an <code>ArrayList</code> of all products in the database.
     *
     * @return <code>ArrayList</code> of all products in their given object form
     * @throws SQLException
     */
    public static ArrayList<Product> selectAllProducts() throws SQLException {
        try {
            ArrayList<Product> products = new ArrayList<>();

            String selectSQL = "SELECT * FROM Products";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Creates an <code>ArrayList</code> of all the <code>Product</code> objects of
     * the specified product type from the result of a SQL select statement
     * performed on the connected database.
     *
     * @param selectedProductType <code>String</code> value of the product type to
     *                            be retrieved
     * @return An <code>ArrayList</code> of <code>Locomotive</code> objects
     * @throws SQLException
     */
    public static ArrayList<Product> selectAllProductType(String selectedProductType) throws SQLException {
        try {
            ArrayList<Product> products = new ArrayList<>();

            String selectSQL = "SELECT * FROM Products WHERE category = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, selectedProductType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSet(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches connected database for the product with the given product code.
     *
     * @param productCode Product code to be searched
     * @return returns <code>Product</code> object containing the result of search
     * @throws SQLException
     */
    public static Product selectProductFromProductCode(String productCode) throws SQLException {
        try {
            Product product = null;

            String selectSQL = "SELECT * FROM Products WHERE product_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = parseResultSet(resultSet);
            }

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches the database for the <code>Controller</code> of a given train set.
     *
     * @param boxSetCode product code of train set to be searched
     * @param connection Database connection object
     * @return <code>Controller</code> object of the given train set.
     * @throws SQLException
     */
    public static Controller selectControllerFromTrainSet(String boxSetCode) throws SQLException {
        try {
            String selectSQL = "SELECT p.product_id, p.name, p.category, p.brand, p.price, p.gauge, p.digital FROM " +
                    "Products AS p, BoxedSets AS b WHERE b.BoxSetId = ? AND b.ProductId = p.product_id AND " +
                    "p.category = 'controller'";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, boxSetCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String productType = resultSet.getString("category");
                String productCode = resultSet.getString("product_id");
                String productName = resultSet.getString("name");
                String manufacturer = resultSet.getString("brand");
                BigDecimal retailPrice = resultSet.getBigDecimal("price");
                String gauge = resultSet.getString("gauge");
                Boolean digital = resultSet.getBoolean("digital");

                return new Controller(productCode, productName, manufacturer, retailPrice, productType, gauge, digital);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Gets the track packs for a specified train set from the database
     *
     * @param boxSetCode Box set to get track packs for
     * @return <code>ArrayList</code> of relevant <code>TrackPack</code> objects
     * @throws Exception
     */
    public static ArrayList<TrackPack> selectTrackPacksFromTrainSet(String boxSetCode) throws Exception {
        try {
            ArrayList<Product> products = selectBoxSetProducts(boxSetCode, "track pack");
            ArrayList<TrackPack> trackPacks = new ArrayList<>();

            for (Product product : products) {
                if (product instanceof TrackPack) {
                    trackPacks.add((TrackPack) product);
                }
            }

            return trackPacks;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Gets the locomotives for a specified train set from the database
     *
     * @param boxSetCode Box set to get locomotives for
     * @return <code>ArrayList</code> of relevant <code>Locomotive</code> objects
     * @throws Exception
     */
    public static ArrayList<Locomotive> selectLocomotivesFromTrainSet(String boxSetCode) throws Exception {
        try {
            ArrayList<Product> products = selectBoxSetProducts(boxSetCode, "locomotive");
            ArrayList<Locomotive> locomotives = new ArrayList<>();

            for (Product product : products) {
                if (product instanceof Locomotive) {
                    locomotives.add((Locomotive) product);
                }
            }

            return locomotives;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Gets the rolling stock for a specified train set from the database
     *
     * @param boxSetCode Box set to get rolling stock for
     * @return <code>ArrayList</code> of relevant <code>RollingStock</code> objects
     * @throws Exception
     */
    public static ArrayList<RollingStock> selectRollingStockFromTrainSet(String boxSetCode) throws Exception {
        try {
            ArrayList<Product> products = selectBoxSetProducts(boxSetCode, "rolling stock");
            ArrayList<RollingStock> rollingStock = new ArrayList<>();

            for (Product product : products) {
                if (product instanceof RollingStock) {
                    rollingStock.add((RollingStock) product);
                }
            }

            return rollingStock;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Gets the track pieces for a specified track pack from the database
     *
     * @param boxSetCode Box set to get track pieces for
     * @return <code>ArrayList</code> of relevant <code>TrackPiece</code> objects
     * @throws Exception
     */
    public static ArrayList<TrackPiece> selectTrackPiecesFromTrackPack(String boxSetCode) throws Exception {
        try {
            ArrayList<Product> products = selectBoxSetProducts(boxSetCode, "track piece");
            ArrayList<TrackPiece> trackPieces = new ArrayList<>();

            for (Product product : products) {
                if (product instanceof TrackPiece) {
                    trackPieces.add((TrackPiece) product);
                }
            }

            return trackPieces;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches the database for a list of distinct manufacturer names.
     *
     * @return <code>ArrayList</code> of manufacturer names as <code>String</code>
     *         objects
     * @throws SQLException
     */
    public static ArrayList<String> selectDistinctManufacturers() throws SQLException {
        try {
            ArrayList<String> manufacturers = new ArrayList<>();

            String selectSQL = "SELECT DISTINCT brand FROM Products";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(resultSet.getString("brand"));
            }

            return manufacturers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches the database for the products that are part of a specified box set.
     *
     * @param boxSetCode  Box set to search
     * @param productType Type of products to search for
     * @return An <code>ArrayList</code> of <code>Product</code> objects that are
     *         part of the box set
     * @throws SQLException
     */
    public static ArrayList<Product> selectBoxSetProducts(String boxSetCode, String productType) throws SQLException {
        try {
            ArrayList<Product> products = new ArrayList<>();

            String selectSQL = "SELECT p.product_id, p.brand, p.name, p.category, p.quantity, p.price, p.era_code, "
                    + "p.dcc_code, p.gauge, p.digital FROM Products AS p, BoxedSets AS b WHERE b.BoxSetId = ? AND "
                    + "b.ProductId = p.product_id AND p.category = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, boxSetCode);
            preparedStatement.setString(2, productType);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSet(resultSet));
            }

            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Searches the database for the quantities of each product in a specified box
     * set
     *
     * @param boxSetCode Box set to search
     * @return A <code>Map</code> of product code <code>Strings</code> and quantity
     *         <code>Integers</code>
     * @throws SQLException
     */
    public static Map<String, Integer> selectBoxSetQuantities(String boxSetCode) throws SQLException {
        try {
            Map<String, Integer> quantities = new HashMap<>();

            String selectSQL = "SELECT b.ProductId, b.Quantity FROM BoxedSets AS b, Products AS p WHERE b.BoxSetId = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, boxSetCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productCode = resultSet.getString(1);
                int quantity = resultSet.getInt(2);

                quantities.put(productCode, quantity);
            }

            return quantities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Method used to check if a product exists, or if it is a newly created
     * product. Performs the relevant operations
     * to the database based on the result of the check i.e. inserts if new, updates
     * if exists.
     *
     * @param product
     * @throws SQLException
     */
    public static boolean insertUpdateProduct(Product product) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Products WHERE product_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, product.getProductCode());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return updateProduct(product);
            } else {
                return insertProduct(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Takes a product object from the program and attempts to insert it into the
     * database. Calls methods to populate the box set table also if the product is
     * a type of box set.
     *
     * @param product <code>Product</code> object to be inserted
     * @return <code>Boolean</code> value representing the success of the operation.
     * @throws SQLException
     */
    public static boolean insertProduct(Product product) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Products (product_id, name, brand, category, gauge, price, quantity, "
                    + "era_code, dcc_code, digital) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getManufacturer());
            preparedStatement.setString(4, product.getProductType());
            preparedStatement.setString(5, product.getGauge());
            preparedStatement.setBigDecimal(6, product.getRetailPrice());
            preparedStatement.setInt(7, product.getStock());
            preparedStatement.setString(8, "");
            preparedStatement.setString(9, "");
            preparedStatement.setInt(10, 0);

            int rowsAffected = 0;

            if (product.getProductType().equals("locomotive")) {
                Locomotive locomotive = (Locomotive) product;
                preparedStatement.setString(8, locomotive.getEraCode());
                preparedStatement.setString(9, locomotive.getDccCode().toString());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("rolling stock")) {
                RollingStock rollingStock = (RollingStock) product;
                preparedStatement.setString(8, rollingStock.getEraCode());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("controller")) {
                Controller controller = (Controller) product;
                preparedStatement.setBoolean(10, controller.getDigital());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("train set")) {
                TrainSet trainSet = (TrainSet) product;
                preparedStatement.setString(8, trainSet.getEraCode());

                ArrayList<Product> boxSetContents = new ArrayList<>();
                if (trainSet.getLocomotives().size() > 0)
                    boxSetContents.addAll(trainSet.getLocomotives());
                if (trainSet.getRollingStocks().size() > 0)
                    boxSetContents.addAll(trainSet.getRollingStocks());
                if (trainSet.getTrackPacks().size() > 0)
                    boxSetContents.addAll(trainSet.getTrackPacks());
                boxSetContents.add(trainSet.getController());

                Map<String, Integer> quantities = trainSet.getQuantities();
                quantities.put(trainSet.getController().getProductCode(), 1);

                rowsAffected = preparedStatement.executeUpdate();
                insertBoxSetContents(trainSet.getProductCode(), boxSetContents, quantities);
            } else if (product.getProductType().equals("track pack")) {
                TrackPack trackPack = (TrackPack) product;

                ArrayList<Product> boxSetContents = new ArrayList<>();
                if (trackPack.getTrackPieces().size() > 0)
                    boxSetContents.addAll(trackPack.getTrackPieces());

                rowsAffected = preparedStatement.executeUpdate();
                insertBoxSetContents(trackPack.getProductCode(), boxSetContents, trackPack.getTrackPieceQuantities());
            }

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Inserts the relevant rows into the database to represent relations between
     * box sets and their contained products and quantities.
     *
     * @param boxSetCode        Parent box set product
     * @param products          Contents of parent box set
     * @param productQuantities Quantities of each daughter product
     * @throws SQLException
     */
    public static void insertBoxSetContents(String boxSetCode, ArrayList<Product> products,
            Map<String, Integer> productQuantities) throws SQLException {
        try {
            String insertSQL = "INSERT INTO BoxedSets VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            for (Product product : products) {
                preparedStatement.setString(1, boxSetCode);
                preparedStatement.setString(2, product.getProductCode());
                preparedStatement.setInt(3, productQuantities.get(product.getProductCode()));

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates the values of a product in the database
     *
     * @param product The product with updated values
     * @return <code>Boolean</code> value representing the success of the operation
     * @throws SQLException
     */
    public static boolean updateProduct(Product product) throws SQLException {
        try {
            if (product.getProductType() == "train set" || product.getProductType() == "track pack") {
                // deletes all relations in boxedsets to create new ones in case box set
                // contents have changed
                String deleteSQL = "DELETE FROM BoxedSets WHERE BoxSetId = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1, product.getProductCode());
                preparedStatement.executeUpdate();
            }

            String updateSQL = "UPDATE Products SET name=?, brand=?, category=?, gauge=?, price=?, quantity=?, "
                    + "era_code=?, dcc_code=?, digital=? WHERE product_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getManufacturer());
            preparedStatement.setString(3, product.getProductType());
            preparedStatement.setString(4, product.getGauge());
            preparedStatement.setBigDecimal(5, product.getRetailPrice());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setString(7, "");
            preparedStatement.setString(8, "");
            preparedStatement.setInt(9, 0);
            preparedStatement.setString(10, product.getProductCode());

            int rowsAffected = 0;

            if (product.getProductType().equals("locomotive")) {
                Locomotive locomotive = (Locomotive) product;
                preparedStatement.setString(7, locomotive.getEraCode());
                preparedStatement.setString(8, locomotive.getDccCode().toString());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("rolling stock")) {
                RollingStock rollingStock = (RollingStock) product;
                preparedStatement.setString(7, rollingStock.getEraCode());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("controller")) {
                Controller controller = (Controller) product;
                preparedStatement.setBoolean(9, controller.getDigital());
                rowsAffected = preparedStatement.executeUpdate();
            } else if (product.getProductType().equals("train set")) {
                TrainSet trainSet = (TrainSet) product;
                preparedStatement.setString(7, trainSet.getEraCode());

                ArrayList<Product> boxSetContents = new ArrayList<>();
                if (trainSet.getLocomotives().size() > 0)
                    boxSetContents.addAll(trainSet.getLocomotives());
                if (trainSet.getRollingStocks().size() > 0)
                    boxSetContents.addAll(trainSet.getRollingStocks());
                if (trainSet.getTrackPacks().size() > 0)
                    boxSetContents.addAll(trainSet.getTrackPacks());
                boxSetContents.add(trainSet.getController());

                Map<String, Integer> quantities = trainSet.getQuantities();
                quantities.put(trainSet.getController().getProductCode(), 1);

                rowsAffected = preparedStatement.executeUpdate();
                insertBoxSetContents(trainSet.getProductCode(), boxSetContents, quantities);
            } else if (product.getProductType().equals("track pack")) {
                TrackPack trackPack = (TrackPack) product;

                ArrayList<Product> boxSetContents = new ArrayList<>();
                if (trackPack.getTrackPieces().size() > 0)
                    boxSetContents.addAll(trackPack.getTrackPieces());

                rowsAffected = preparedStatement.executeUpdate();
                insertBoxSetContents(trackPack.getProductCode(), boxSetContents, trackPack.getTrackPieceQuantities());
            } else if (product.getProductType().equals("track piece")) {
                rowsAffected = preparedStatement.executeUpdate();
            }

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Takes a given product and updates its quantity value in the database
     *
     * @param productCode product code to edit stock of
     * @param stock       new value for quantity
     * @return <code>boolean</code> value representing the success of the operation
     * @throws SQLException
     */
    public static boolean updateStock(String productCode, int stock) throws SQLException {
        try {
            String updateSQL = "UPDATE Products SET quantity = ? WHERE product_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, stock);
            preparedStatement.setString(2, productCode);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Deletes a specified product from the database.
     *
     * @param productCode Product to be deleted
     * @return <code>Boolean</code> value representing the success of the operation
     * @throws SQLException
     */
    public static boolean deleteProduct(String productCode) throws SQLException {
        try {
            Product product = selectProductFromProductCode(productCode);
            if (product.getProductType() == "train set" || product.getProductType() == "track pack") {
                String deleteSQL = "DELETE FROM BoxedSets WHERE BoxSetId = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
                preparedStatement.setString(1, product.getProductCode());
                preparedStatement.executeUpdate();
            }

            String deleteSQL = "DELETE FROM Products WHERE product_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, product.getProductCode());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * METHODS RELATED TO ORDERS
     */

    /**
     * Selects all orders from the database
     *
     * @return an <code>ArrayList</code> of <code>StaffOrder</code> objects
     *         containing order details for each order
     * @throws SQLException
     */
    public static ArrayList<StaffOrder> selectAllOrders() throws SQLException {
        try {
            ArrayList<StaffOrder> orders = new ArrayList<>();

            String selectSQL = "SELECT * FROM Orders WHERE status = 'CONFIRMED' ORDER BY date";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String orderID = resultSet.getString("order_id");
                String userID = resultSet.getString("user_id");
                Status orderStatus = Status.valueOf(resultSet.getString("status"));
                Date orderDate = resultSet.getDate("date");

                orders.add(
                        new StaffOrder(orderID, userID, orderStatus, orderDate, selectOrderLinesFromOrderID(orderID)));
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Selects all orders from the database that correspond to a given search input
     *
     * @param search string to search database for data with likeness to
     * @return an <code>ArrayList</code> of <code>StaffOrder</code> objects
     *         containing order details for each order
     * @throws SQLException
     */
    public static ArrayList<StaffOrder> selectSearchedOrders(String search) throws SQLException {
        try {
            ArrayList<StaffOrder> orders = new ArrayList<>();

            String selectSQL = "SELECT * FROM Orders WHERE (order_id LIKE ? OR user_id LIKE ?) "
                    + "AND status = 'CONFIRMED' ORDER BY date";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String orderID = resultSet.getString("order_id");
                String userID = resultSet.getString("user_id");
                Status orderStatus = Status.valueOf(resultSet.getString("status"));
                Date orderDate = resultSet.getDate("date");

                orders.add(
                        new StaffOrder(orderID, userID, orderStatus, orderDate, selectOrderLinesFromOrderID(orderID)));
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Selects all fulfilled orders from the database
     *
     * @return an <code>ArrayList</code> of <code>StaffOrder</code> objects
     *         containing order details for each order
     * @throws SQLException
     */
    public static ArrayList<StaffOrder> selectFulfilledOrders() throws SQLException {
        try {
            ArrayList<StaffOrder> orders = new ArrayList<>();

            String selectSQL = "SELECT * FROM Orders WHERE status = 'FULFILLED' ORDER BY date";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String orderID = resultSet.getString("order_id");
                String userID = resultSet.getString("user_id");
                Status orderStatus = Status.valueOf(resultSet.getString("status"));
                Date orderDate = resultSet.getDate("date");

                orders.add(
                        new StaffOrder(orderID, userID, orderStatus, orderDate, selectOrderLinesFromOrderID(orderID)));
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Selects all fulfilled orders from the database that correspond to a given search input
     *
     * @param search string to search database for data with likeness to
     * @return an <code>ArrayList</code> of <code>StaffOrder</code> objects
     *         containing order details for each order
     * @throws SQLException
     */
    public static ArrayList<StaffOrder> selectFulfilledSearchedOrders(String search) throws SQLException {
        try {
            ArrayList<StaffOrder> orders = new ArrayList<>();

            String selectSQL = "SELECT * FROM Orders WHERE (order_id LIKE ? OR user_id LIKE ?) "
                    + "AND status = 'FULFILLED' ORDER BY date";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String orderID = resultSet.getString("order_id");
                String userID = resultSet.getString("user_id");
                Status orderStatus = Status.valueOf(resultSet.getString("status"));
                Date orderDate = resultSet.getDate("date");

                orders.add(
                        new StaffOrder(orderID, userID, orderStatus, orderDate, selectOrderLinesFromOrderID(orderID)));
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Selects order details from the database from a given orderID
     *
     * @param orderID order to select
     * @return <code>StaffOrder</code> object containing the order details from the
     *         database.
     * @throws SQLException
     */
    public static StaffOrder selectOrder(String orderID) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Orders WHERE order_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, orderID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userID = resultSet.getString("user_id");
                Status orderStatus = Status.valueOf(resultSet.getString("status"));
                Date orderDate = resultSet.getDate("date");

                return new StaffOrder(orderID, userID, orderStatus, orderDate, selectOrderLinesFromOrderID(orderID));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Takes a <code>String</code> orderID input and returns an
     * <code>ArrayList</code> of <code>OrderLine</code> objects
     *
     * @param orderID Order to search order lines for
     * @return <code>ArrayList</code> of <code>OrderLine</code> objects for the
     *         given orderID
     * @throws SQLException
     */
    public static ArrayList<OrderLine> selectOrderLinesFromOrderID(String orderID) throws SQLException {
        try {
            ArrayList<OrderLine> orderLines = new ArrayList<>();

            String selectSQL = "SELECT product_id, quantity FROM OrderDetails WHERE order_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, orderID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String productID = resultSet.getString("product_id");
                int quantity = resultSet.getInt("quantity");

                orderLines.add(new OrderLine(selectProductFromProductCode(productID), quantity));
            }

            return orderLines;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Changes the status of a given order to fulfilled in the database
     *
     * @param orderID the order to update the status of
     * @throws SQLException
     */
    public static void fulfillOrder(String orderID) throws SQLException {
        try {
            String updateSQL = "UPDATE Orders SET STATUS = 'FULFILLED' WHERE order_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, orderID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Deletes an order from the database from a given orderID
     *
     * @param orderID order to delete
     * @throws SQLException
     */
    public static void deleteOrder(String orderID) throws SQLException {
        try {
            String deleteSQL = "DELETE FROM Orders WHERE order_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, orderID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * METHODS RELATED TO USERS
     */

    /**
     * Gets user details of a specific user from the database.
     * @param userID the userID of the user details to get
     * @return a user object containing relevant user details
     * @throws SQLException
     */
    public static User selectUser(String userID) throws SQLException {
        try {
            String selectSQL = "SELECT user_id, first_name, last_name, email, user_role FROM Users WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int roleInt = resultSet.getInt("user_role");

                Roles role;
                switch (roleInt) {
                    case 2:
                        role = Roles.MANAGER;
                        break;
                    case 1:
                        role = Roles.STAFFMEMBER;
                        break;
                    case 0:
                        role = Roles.CUSTOMER;
                        break;
                    default:
                        role = Roles.CUSTOMER;
                }

                return new User(userID, name, email, role);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns an <code>ArrayList</code> of all users in the database.
     *
     * @return <code>ArrayList</code> of all users in their given object form
     * @throws SQLException
     */
    public static ArrayList<User> selectAllUsers() throws SQLException {
        try {
            ArrayList<User> users = new ArrayList<>();

            String selectSQL = "SELECT user_id, first_name, last_name, email, user_role FROM Users";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String userID = resultSet.getString("user_id");
                String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int roleInt = resultSet.getInt("user_role");

                Roles role;
                switch (roleInt) {
                    case 2:
                        role = Roles.MANAGER;
                        break;
                    case 1:
                        role = Roles.STAFFMEMBER;
                        break;
                    case 0:
                        role = Roles.CUSTOMER;
                        break;
                    default:
                        role = Roles.CUSTOMER;
                }

                users.add(new User(userID, name, email, role));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns an <code>ArrayList</code> of all users in the database based on a given search input.
     *
     * @param search the string to search the table with
     * @return <code>ArrayList</code> of all users in their given object form
     * @throws SQLException
     */
    public static ArrayList<User> selectSearchedUsers(String search) throws SQLException {
        try {
            ArrayList<User> users = new ArrayList<>();

            String selectSQL = "SELECT user_id, first_name, last_name, email, user_role FROM Users WHERE "
                    + "user_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            preparedStatement.setString(4, "%" + search + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String userID = resultSet.getString("user_id");
                String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int roleInt = resultSet.getInt("user_role");

                Roles role;
                switch (roleInt) {
                    case 2:
                        role = Roles.MANAGER;
                        break;
                    case 1:
                        role = Roles.STAFFMEMBER;
                        break;
                    case 0:
                        role = Roles.CUSTOMER;
                        break;
                    default:
                        role = Roles.CUSTOMER;
                }

                users.add(new User(userID, name, email, role));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns an <code>ArrayList</code> of all users in the database with the specified role.
     *
     * @param searchRole the role to search
     * @return <code>ArrayList</code> of all users in their given object form
     * @throws SQLException
     */
    public static ArrayList<User> selectAllUsersWithRole(Roles searchRole) throws SQLException {
        try {
            ArrayList<User> users = new ArrayList<>();

            String selectSQL = "SELECT user_id, first_name, last_name, email, user_role FROM Users WHERE user_role = ?";

            int searchRoleInt;

            switch (searchRole) {
                case MANAGER:
                    searchRoleInt = 2;
                    break;
                case STAFFMEMBER:
                    searchRoleInt = 1;
                    break;
                case CUSTOMER:
                    searchRoleInt = 0;
                    break;
                default:
                    searchRoleInt = 0;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, searchRoleInt);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String userID = resultSet.getString("user_id");
                String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int roleInt = resultSet.getInt("user_role");

                Roles role;
                switch (roleInt) {
                    case 2:
                        role = Roles.MANAGER;
                        break;
                    case 1:
                        role = Roles.STAFFMEMBER;
                        break;
                    case 0:
                        role = Roles.CUSTOMER;
                        break;
                    default:
                        role = Roles.CUSTOMER;
                }

                users.add(new User(userID, name, email, role));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns an <code>ArrayList</code> of all users in the database with the
     * specified role based on a given search input.
     *
     * @param search the string to search the table with
     * @param searchRole the role to search
     * @return <code>ArrayList</code> of all users in their given object form
     * @throws SQLException
     */
    public static ArrayList<User> selectSearchedUsersWithRole(String search, Roles searchRole) throws SQLException {
        try {
            ArrayList<User> users = new ArrayList<>();

            String selectSQL = "SELECT user_id, first_name, last_name, email, user_role FROM Users WHERE "
                    + "(user_id LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR email LIKE ?) AND user_role = ?";

            int searchRoleInt;

            switch (searchRole) {
                case MANAGER:
                    searchRoleInt = 2;
                    break;
                case STAFFMEMBER:
                    searchRoleInt = 1;
                    break;
                case CUSTOMER:
                    searchRoleInt = 0;
                    break;
                default:
                    searchRoleInt = 0;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            preparedStatement.setString(4, "%" + search + "%");
            preparedStatement.setInt(5, searchRoleInt);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String userID = resultSet.getString("user_id");
                String name = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
                String email = resultSet.getString("email");
                int roleInt = resultSet.getInt("user_role");

                Roles role;
                switch (roleInt) {
                    case 2:
                        role = Roles.MANAGER;
                        break;
                    case 1:
                        role = Roles.STAFFMEMBER;
                        break;
                    case 0:
                        role = Roles.CUSTOMER;
                        break;
                    default:
                        role = Roles.CUSTOMER;
                }

                users.add(new User(userID, name, email, role));
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates the role of a given user in the database
     * @param userID user who's role is to be updated
     * @param role the new role of the user
     * @return <code>boolean</code> value representing the success of the operation
     * @throws SQLException
     */
    public static boolean updateRole(String userID, Roles role) throws SQLException {
        try {
            String updateSQL = "UPDATE Users set user_role = ? WHERE user_id = ?";

            int roleInt;

            switch (role) {
                case MANAGER:
                    roleInt = 2;
                    break;
                case STAFFMEMBER:
                    roleInt = 1;
                    break;
                case CUSTOMER:
                    roleInt = 0;
                    break;
                default:
                    roleInt = 0;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, roleInt);
            preparedStatement.setString(2, userID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /*
     * Methods related to banking details
     */

    /**
     * Checks the database to see if a given user has a valid bank card
     * @param userID user to check
     * @return <code>boolean</code> value representing the presence of a valid card
     * @throws SQLException
     */
    public static boolean validCard(String userID) throws SQLException {
        try {
            boolean valid = false;
            LocalDate currentDate = LocalDate.now();
            String selectSQL = "SELECT * FROM BankingDetails WHERE userid = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String expiryDate = resultSet.getString("expiry");
                int expiryMonth = Integer.valueOf(expiryDate.substring(0, 2));
                int expiryYear = Integer.valueOf("20" + expiryDate.substring(3));

                if (expiryYear > currentDate.getYear()) {
                    valid = true;
                } else if (expiryYear == currentDate.getYear() && expiryMonth > currentDate.getMonthValue()) {
                    valid = true;
                }
            }
            return valid;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
