package trainsheffield;

/*
 * Author: Joel Foster
 */

import java.sql.Date;
import java.util.ArrayList;

import trainsheffield.Order.Status;

public class StaffOrder {
    private String orderID;
    private String userID;
    private Status orderStatus;
    private Date orderDate;
    private ArrayList<OrderLine> orderLines;

    // Get methods
    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    // Set methods
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    /**
     * Constructor for <code>StaffOrder</code> objects.
     *
     * @param orderID
     * @param userID
     * @param orderStatus
     * @param orderDate
     * @param orderLines
     */
    public StaffOrder(String orderID, String userID, Status orderStatus, Date orderDate,
            ArrayList<OrderLine> orderLines) {

        this.orderID = orderID;
        this.userID = userID;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderLines = orderLines;
    }
}
