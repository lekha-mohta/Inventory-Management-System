package trainsheffield;

import javax.swing.*;

import com.google.common.math.BigDecimalMath;
import com.mysql.cj.util.StringUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order extends JPanel {

    private static int lastOrderId;
    private Integer orderId;
    private String userId;
    private Date orderDate;
    private Status status;
    private List<OrderLine> orderLines;
    private static List<Product> addedProductDetails = new ArrayList<>();

    public Order() {
        //object
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame ("Order");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add (new Order());
        // System.out.println("in the Order class: " + product);

        frame.pack();
        frame.setVisible (true);
    }

    public enum Status {
        PENDING, CONFIRMED, FULFILLED
    }

    static String order_Id = generateOrderid(DatabaseManager.getConnection());

    //gets all orders for all users
    //adds to dashboard
    public static String[] getOrderDetails() {
    try {
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement orderStatement = connection.prepareStatement("SELECT * FROM Orders");
        ResultSet orderResultSet = orderStatement.executeQuery();
        ArrayList<String> allordersList = new ArrayList<>();
        while (orderResultSet.next()) {
            String orderId = orderResultSet.getString("order_id");
            String userId = orderResultSet.getString("user_id");
            String orderStatus = orderResultSet.getString("status");
            String orderDate = orderResultSet.getString("date");
            String userName = Users.getUserName(connection, userId);
            PreparedStatement orderDetailsStatement = connection.prepareStatement("SELECT product_id, quantity FROM OrderDetails WHERE order_id = ?");
            orderDetailsStatement.setString(1, orderId);
            ResultSet orderDetailsResultSet = orderDetailsStatement.executeQuery();
            while (orderDetailsResultSet.next()) {
                String productId = orderDetailsResultSet.getString("product_id");
                int quantity = orderDetailsResultSet.getInt("quantity");
                String shortOrderDate = orderDate.substring(0, 10);
                String orderDetails = shortOrderDate + "/" + orderId + "/" + userName + "/" + productId + "/" + quantity + "/" + orderStatus;
                allordersList.add(orderDetails);
            }
        }
        String[] allordersListArray = allordersList.toArray(new String[allordersList.size()]);
        return allordersListArray;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }

    //gets all orders for a specific user
    public static String[] getUsersOrderDetails() {
    try {
        Connection connection = DatabaseManager.getConnection();
        String userId = Login.currentUser[0];
        String userName = Users.getUserName(connection, userId);
        PreparedStatement orderStatement = connection.prepareStatement("SELECT * FROM Orders WHERE user_id = ?");
        orderStatement.setString(1, userId);
        ResultSet orderResultSet = orderStatement.executeQuery();
        ArrayList<String> ordersList = new ArrayList<>();
        while (orderResultSet.next()) {
            String orderId = orderResultSet.getString("order_id");
            String orderStatus = orderResultSet.getString("status");
            String orderDate = orderResultSet.getString("date");
            PreparedStatement orderDetailsStatement = connection.prepareStatement("SELECT product_id, quantity FROM OrderDetails WHERE order_id = ?");
            orderDetailsStatement.setString(1, orderId);
            ResultSet orderDetailsResultSet = orderDetailsStatement.executeQuery();
            while (orderDetailsResultSet.next()) {
                String productId = orderDetailsResultSet.getString("product_id");
                int quantity = orderDetailsResultSet.getInt("quantity");
                String shortOrderDate = orderDate.substring(0, 10);
                String orderDetails = shortOrderDate + "/" + orderId + "/" + userName + "/" + productId + "/" + quantity + "/" + orderStatus;
                ordersList.add(orderDetails);
            }
        }
        String[] ordersListArray = ordersList.toArray(new String[ordersList.size()]);
        return ordersListArray;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }



    // public static void addProductDetails(Product product) {
    //     addedProductDetails.add(product);
    // }

    public void removeOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
    }

    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.valueOf(0);
        for (OrderLine orderLine : orderLines) {
            // totalCost += orderLine.getProductCode().getRetailPrice() * orderLine.getQuantity();
            totalCost = totalCost.add(
                    orderLine.getProduct().getRetailPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity())));
        }
        return totalCost;
    }

    public static String generateOrderid(Connection connection) {
        String orderID;
        do {
            orderID = String.format("%05d", (int) (Math.random() * 100000)); 
        } while (checkOrderIdExists(connection, orderID));
        
        return orderID;
        }
      
    // Check if the user id already exists in the database
    private static boolean checkOrderIdExists(Connection connection, String orderID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Orders WHERE order_id = ?");
            preparedStatement.setString(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true; 
        }
    }

    public static void saveOrder(ArrayList<Object[]> orderLines) throws SQLException {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection();
    
            LocalDate todaysDate = LocalDate.now();
            Date sqlDate = java.sql.Date.valueOf(todaysDate);
            String userId = Login.currentUser[0];
            String status = "CONFIRMED";
            String orderId = order_Id;
            String total_Cost = Checkout.totalCost[0];
            String sqlOrders = "INSERT INTO Orders (order_id, user_id, status, date, total_cost) VALUES (?,?,?,?, ?)";
            PreparedStatement statementOrders = connection.prepareStatement(sqlOrders);
    
            statementOrders.setString(1, orderId);
            statementOrders.setString(2, userId);
            statementOrders.setString(3, status);
            statementOrders.setDate(4, (java.sql.Date) sqlDate);
            statementOrders.setString(5, total_Cost);
            statementOrders.executeUpdate();
    
            String sqlOrderDetails = "INSERT INTO OrderDetails (product_id, order_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement statementOrderDetails = connection.prepareStatement(sqlOrderDetails);
    
            for (Object[] orderLine : orderLines) {
                String productId = (String) orderLine[0];
                int quantity = Integer.parseInt(orderLine[3].toString());
                statementOrderDetails.setString(1, productId);
                statementOrderDetails.setString(2, orderId);
                statementOrderDetails.setInt(3, quantity);
                statementOrderDetails.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    
    
}


