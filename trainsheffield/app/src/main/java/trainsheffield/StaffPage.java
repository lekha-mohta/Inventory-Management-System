package trainsheffield;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import trainsheffield.Order.Status;

/*
 * @author Joel Foster
 */

public class StaffPage extends JPanel {

    /**
     * Creates new form StaffPage
     */
    public StaffPage() {
        initComponents();
        addActionListeners();
        populateProductTable();
        populateOrderTable(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        productOrderSelectPanel = new javax.swing.JPanel();
        productPageButton = new javax.swing.JButton();
        orderPageButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        cardLayout = new CardLayout();
        productPanel = new javax.swing.JPanel();
        productSearchPanel = new javax.swing.JPanel();
        productSearchLabel = new javax.swing.JLabel();
        productSearchTextField = new javax.swing.JTextField();
        productSearchButton = new javax.swing.JButton();
        productClearSearchButton = new javax.swing.JButton();
        addProductButton = new javax.swing.JButton();
        productScrollPane = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        orderPanel = new javax.swing.JPanel();
        orderSearchPanel = new javax.swing.JPanel();
        orderSearchLabel = new javax.swing.JLabel();
        orderSearchTextField = new javax.swing.JTextField();
        orderSearchButton = new javax.swing.JButton();
        orderClearSearchButton = new javax.swing.JButton();
        orderStatusComboBox = new javax.swing.JComboBox<>();
        orderScrollPane = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        addEditProductPanel = new AddEditProductPanel(this);

        setLayout(new java.awt.GridBagLayout());

        titlePanel.setBackground(new java.awt.Color(204, 204, 204));
        titlePanel.setPreferredSize(new java.awt.Dimension(1013, 100));

        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Staff Dashboard");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        titlePanelLayout.setVerticalGroup(
                titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.01;
        add(titlePanel, gridBagConstraints);

        homeButton.setFont(new java.awt.Font("sansserif", 0, 14));
        homeButton.setText("Home");
        productOrderSelectPanel.add(homeButton);

        productPageButton.setFont(new java.awt.Font("sansserif", 0, 14));
        productPageButton.setText("Products");
        productOrderSelectPanel.add(productPageButton);

        orderPageButton.setFont(new java.awt.Font("sansserif", 0, 14));
        orderPageButton.setText("Orders");
        productOrderSelectPanel.add(orderPageButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.1;
        add(productOrderSelectPanel, gridBagConstraints);

        // init product panel
        productPanel.setLayout(new java.awt.BorderLayout());

        productSearchLabel.setFont(new java.awt.Font("sansserif", 0, 14));
        productSearchLabel.setLabelFor(productSearchTextField);
        productSearchLabel.setText("Search by Product Code, Name, Manufacturer etc.:");
        productSearchPanel.add(productSearchLabel);

        productSearchTextField.setColumns(25);
        productSearchTextField.setMinimumSize(new java.awt.Dimension(300, 40));
        productSearchPanel.add(productSearchTextField);

        productSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        productSearchButton.setText("Search");
        productSearchPanel.add(productSearchButton);

        productClearSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        productClearSearchButton.setText("Clear search");
        productSearchPanel.add(productClearSearchButton);

        addProductButton.setText("Add a product");
        addProductButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        productSearchPanel.add(addProductButton);

        productPanel.add(productSearchPanel, java.awt.BorderLayout.NORTH);

        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Product Code", "Name", "Manufacturer", "Product Type", "Gauge", "Retail Price", "Stock"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        productTable.setEditingColumn(6);
        productScrollPane.setViewportView(productTable);

        productPanel.add(productScrollPane, java.awt.BorderLayout.CENTER);

        // init order panel
        orderPanel.setLayout(new java.awt.BorderLayout());

        orderSearchLabel.setFont(new java.awt.Font("sansserif", 0, 14));
        orderSearchLabel.setLabelFor(orderSearchTextField);
        orderSearchLabel.setText("Search by Order ID, User ID, Customer Name etc.:");
        orderSearchPanel.add(orderSearchLabel);

        orderSearchTextField.setColumns(25);
        orderSearchTextField.setMinimumSize(new java.awt.Dimension(300, 40));
        orderSearchPanel.add(orderSearchTextField);

        orderSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        orderSearchButton.setText("Search");
        orderSearchPanel.add(orderSearchButton);

        orderClearSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        orderClearSearchButton.setText("Clear search");
        orderSearchPanel.add(orderClearSearchButton);

        orderStatusComboBox.setModel(new DefaultComboBoxModel<>(Status.values()));
        orderStatusComboBox.removeItem(Status.PENDING);
        orderSearchPanel.add(orderStatusComboBox);

        orderPanel.add(orderSearchPanel, java.awt.BorderLayout.NORTH);

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Order ID", "Email", "Customer Name", "Status", "Date"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        orderTable.setEditingColumn(5);
        orderScrollPane.setViewportView(orderTable);

        orderPanel.add(orderScrollPane, java.awt.BorderLayout.CENTER);

        addEditProductPanel.setPreferredSize(productPanel.getSize());

        mainPanel.setLayout(cardLayout);
        mainPanel.add(productPanel, PRODUCT_PANEL);
        mainPanel.add(orderPanel, ORDER_PANEL);
        mainPanel.add(addEditProductPanel, ADD_EDIT_PRODUCT);
        cardLayout.show(mainPanel, PRODUCT_PANEL);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(mainPanel, gridBagConstraints);
    }

    /**
     * Method to add action listeners to GUI components
     */
    public void addActionListeners() {
        /*
         * Navigation buttons
         */

        // checks if the home button is clicked, and if so, goes back to the customer dashboard
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer();
                JFrame customerFrame = new JFrame("Customer Dashboard");
                customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerFrame.getContentPane().add(customer);
                customerFrame.pack();
                customerFrame.setVisible(true);
                JFrame staffFrame = (JFrame) SwingUtilities.getWindowAncestor(StaffPage.this);
                if (staffFrame != null) {
                    staffFrame.dispose();
                }
            }
        });

        orderPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, ORDER_PANEL);
            }
        });

        productPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, PRODUCT_PANEL);
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEditProductPanel.clearInputFields();
                addEditProductPanel.setEditMode(false);
                cardLayout.show(mainPanel, ADD_EDIT_PRODUCT);
            }
        });

        /*
         * Product page action listeners
         */

        productSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                populateProductTable();
            }
        });

        productClearSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                productSearchTextField.setText("");
                populateProductTable();
            }
        });

        // Checks if a row of product table is double clicked, and opens the edit
        // product page for double clicked product if so
        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Double-click detected
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String productCode = String.valueOf(productTable.getValueAt(selectedRow, 0));
                        addEditProductPanel.loadProductDetails(productCode);
                        addEditProductPanel.setEditMode(true);
                        cardLayout.show(mainPanel, ADD_EDIT_PRODUCT);
                    }
                }
            }
        });

        // checks if the stock of a product was edited in the product table, and updates
        // its value in the database if so
        productTable.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    // checks if the column edited was the stock column
                    if (column == 6) {
                        String productCode = String.valueOf(productTable.getValueAt(row, 0));
                        int editedStock = (int) productTable.getValueAt(row, 6);

                        // attempts to update the stock in the database and shows a success message if successful
                        try {
                            if (DatabaseOperations.updateStock(productCode, editedStock)) {
                                JOptionPane.showMessageDialog(null, "Updated the stock of the product successfully!",
                                        "Stock Updated Successfully!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            populateProductTable();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            }
        });

        /*
         * Order page action listeners
         */

        orderSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (orderStatusComboBox.getSelectedItem() == Status.FULFILLED) {
                    populateOrderTable(true);
                } else {
                    populateOrderTable(false);
                }
            }
        });

        orderClearSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                orderSearchTextField.setText("");
                if (orderStatusComboBox.getSelectedItem() == Status.FULFILLED) {
                    populateOrderTable(true);
                } else {
                    populateOrderTable(false);
                }
            }
        });

        orderStatusComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (orderStatusComboBox.getSelectedItem().equals(Status.FULFILLED)) {
                    populateOrderTable(true);
                } else if (orderStatusComboBox.getSelectedItem().equals(Status.CONFIRMED)) {
                    populateOrderTable(false);
                }
            }
        });

        // checks to see if an order is double clicked in the order table, and opens a
        // joptionpane with order details
        // and choices to fulfill or delete the order if the order is a confirmed order
        orderTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Double-click detected
                    int selectedRow = orderTable.getSelectedRow();
                    if (selectedRow != -1) { // makes sure selected row is valid
                        String orderID = String.valueOf(orderTable.getValueAt(selectedRow, 0));
                        try {
                            StaffOrder order = DatabaseOperations.selectOrder(orderID);
                            boolean validCard = DatabaseOperations.validCard(order.getUserID());

                            // creates message content containing all the order details, to be displayed in
                            // the joptionpane
                            String message = "Order ID: " + orderID + "\nEmail: "
                                    + DatabaseOperations.selectUser(order.getUserID()).getEmail()
                                    + "\nCustomer Name: "
                                    + Users.getUserName(DatabaseManager.getConnection(), order.getUserID());
                            String[] address = Users.getAddress(DatabaseManager.getConnection(), order.getUserID());
                            String addressString = address[0] + " " + address[1] + ", " + address[2] + ", "
                                    + address[3];
                            message = message + ("\nAddress: " + addressString);
                            message = message + ("\nValid bank card exists: " + validCard);
                            message = message + "\nProducts:";

                            // adds each order line to the order details message
                            for (OrderLine orderLine : order.getOrderLines()) {
                                message = message
                                        + ("\n" + orderLine.getProduct().getProductCode() + "-"
                                                + orderLine.getProduct().getProductName() + ", Quantity: "
                                                + orderLine.getQuantity());
                            }

                            // only allows confirmed orders to be displayed and marked as fulfilled
                            if (order.getOrderStatus() == Status.CONFIRMED) {
                                Object[] options = { "Fulfill", "Delete", "Cancel" };
                                int action = JOptionPane.showOptionDialog(null, message, "Order: " + orderID,
                                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                                        options[2]);

                                if (action == 0) { // fulfill clicked
                                    boolean outOfStock = false;

                                    // checks if a product has enough of the required quantity
                                    for (OrderLine orderLine : order.getOrderLines()) {
                                        int stock = orderLine.getProduct().getStock();
                                        if (stock < orderLine.getQuantity()) {
                                            outOfStock = true;
                                        }
                                    }

                                    if (!outOfStock && validCard) {

                                        // reduces the stock of each product by the required amount
                                        for (OrderLine orderLine : order.getOrderLines()) {
                                            int stock = orderLine.getProduct().getStock();
                                            DatabaseOperations.updateStock(orderLine.getProduct().getProductCode(),
                                                    stock - orderLine.getQuantity());
                                        }

                                        DatabaseOperations.fulfillOrder(orderID);
                                        populateOrderTable(false);
                                        populateProductTable();
                                    } else if (!validCard) {
                                        JOptionPane.showMessageDialog(null, "User does not have a valid card",
                                                "No Valid Card", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                                "Some contents of the order are out of stock. Order cannot be fulfilled",
                                                "Out of Stock", JOptionPane.ERROR_MESSAGE);
                                    }

                                } else if (action == 1) {
                                    DatabaseOperations.deleteOrder(orderID);
                                    populateOrderTable(false);
                                }
                            }
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    /**
     * Method to populate the <code>productTable</code> with product information.
     * Utilises
     * methods from {@link DatabaseOperations} to
     * retrieve and populate an array of <code>Product</code> objects from the
     * database based on the
     * <code>search</code> string, and then appends the
     * data from each object to the <code>productTable</code>.
     */
    public void populateProductTable() {
        String search = productSearchTextField.getText();
        ArrayList<Product> products;
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0); // removes all rows so duplicates are not added

        if (search.equals("")) {
            try {
                products = DatabaseOperations.selectAllProducts();
                new ShopPage(products);

                for (Product product : products) {
                    Object[] rowData = { product.getProductCode(), product.getProductName(), product.getManufacturer(),
                            product.getProductType(), product.getGauge(), product.getRetailPrice(),
                            product.getStock() };
                    model.addRow(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                products = DatabaseOperations.selectSearchedProducts(search);

                for (Product product : products) {
                    Object[] rowData = { product.getProductCode(), product.getProductName(), product.getManufacturer(),
                            product.getProductType(), product.getGauge(), product.getRetailPrice(),
                            product.getStock() };
                    model.addRow(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to populate the <code>orderTable</code> with order information.
     * Utilises
     * methods from {@link DatabaseOperations} to
     * retrieve and populate an array of <code>StaffOrder</code> objects from the
     * database based on the
     * <code>search</code> string, and then appends the
     * data from each object to the <code>orderTable</code>.
     */
    public void populateOrderTable(boolean fulfilled) {
        String search = orderSearchTextField.getText();
        ArrayList<StaffOrder> orders;
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        model.setRowCount(0); // removes all rows so duplicates are not added

        if (search.equals("")) {
            try {
                if (fulfilled) {
                    orders = DatabaseOperations.selectFulfilledOrders();
                } else {
                    orders = DatabaseOperations.selectAllOrders();
                }

                for (StaffOrder order : orders) {
                    Object[] rowData = { order.getOrderID(), DatabaseOperations.selectUser(order.getUserID()).getEmail(),
                            Users.getUserName(DatabaseManager.getConnection(), order.getUserID()),
                            order.getOrderStatus(), order.getOrderDate() };

                    model.addRow(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (fulfilled) {
                    orders = DatabaseOperations.selectFulfilledSearchedOrders(search);
                } else {
                    orders = DatabaseOperations.selectSearchedOrders(search);
                }

                for (StaffOrder order : orders) {
                    Object[] rowData = { order.getOrderID(), DatabaseOperations.selectUser(order.getUserID()).getEmail(),
                            Users.getUserName(DatabaseManager.getConnection(), order.getUserID()),
                            order.getOrderStatus(), order.getOrderDate() };

                    model.addRow(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Used to switch to product panel from child panel classes
     */
    public void openProductPanel() {
        cardLayout.show(mainPanel, PRODUCT_PANEL);
    }

    // Variables declaration
    private javax.swing.JButton addProductButton;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel productPanel;
    private javax.swing.JPanel productOrderSelectPanel;
    private javax.swing.JPanel productSearchPanel;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel orderSearchPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane productScrollPane;
    private javax.swing.JButton orderPageButton;
    private javax.swing.JButton productPageButton;
    private javax.swing.JButton homeButton;
    private javax.swing.JTable productTable;
    private javax.swing.JButton productSearchButton;
    private javax.swing.JButton productClearSearchButton;
    private javax.swing.JLabel productSearchLabel;
    private javax.swing.JTextField productSearchTextField;
    private javax.swing.JScrollPane orderScrollPane;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton orderSearchButton;
    private javax.swing.JButton orderClearSearchButton;
    private javax.swing.JLabel orderSearchLabel;
    private javax.swing.JTextField orderSearchTextField;
    private javax.swing.JComboBox<Status> orderStatusComboBox;
    private javax.swing.JLabel titleLabel;
    private CardLayout cardLayout;
    private AddEditProductPanel addEditProductPanel;
    private static final String PRODUCT_PANEL = "Product Panel";
    private static final String ORDER_PANEL = "Order Panel";
    private static final String ADD_EDIT_PRODUCT = "Add Edit Product";
    // End of variables declaration
}
