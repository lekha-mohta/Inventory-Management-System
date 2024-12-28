package trainsheffield;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.*;

public class Customer extends JPanel {
    String[] bankingDetailsArray = BankingDetails.getBankingDetails();
    String userRole = Users.getRole(Login.currentUser[0]);
    public Customer() {
        initComponents();
        addActionListeners();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        personalOrderSelectPanel = new javax.swing.JPanel();
        bankDetailsButton = new javax.swing.JButton();
        orderPageButton = new javax.swing.JButton();
        personalDetailsButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        dashboardButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        cardLayout = new CardLayout();
        personalDetailsPanel = new javax.swing.JPanel();
        personalDetailsScrollPane = new javax.swing.JScrollPane();
        personalDetailsTable = new javax.swing.JTable();
        bankDetailsPanel = new javax.swing.JPanel();
        editPersonalDetailsButton = new javax.swing.JButton();
        backToShopPageButton = new javax.swing.JButton();
        bankDetailsTable = new javax.swing.JTable();
        bankDetailsScrollPane = new javax.swing.JScrollPane();
        orderPanel = new javax.swing.JPanel();
        orderSearchPanel = new javax.swing.JPanel();
        orderSearchLabel = new javax.swing.JLabel();
        orderSearchTextField = new javax.swing.JTextField();
        orderSearchButton = new javax.swing.JButton();
        orderClearSearchButton = new javax.swing.JButton();
        orderScrollPane = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        editBankDetailsPanel = createEditBankDetailsPanel();

        setLayout(new java.awt.GridBagLayout());

        //Title Pannel for the Dashboard
        titlePanel.setBackground(new java.awt.Color(204, 204, 204));
        titlePanel.setPreferredSize(new java.awt.Dimension(1013, 300));

        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("User Dashboard");

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

        //Button to return to the main shop page
        backToShopPageButton.setFont(new java.awt.Font("sansserif", 0, 14));
        backToShopPageButton.setText("Shop Page");
        personalOrderSelectPanel.add(backToShopPageButton);

        //Button to view the personal details of a user
        personalDetailsButton.setFont(new java.awt.Font("sansserif", 0, 14));
        personalDetailsButton.setText("Personal Details");
        personalOrderSelectPanel.add(personalDetailsButton);

        //Button to view the bank details of a user
        bankDetailsButton.setFont(new java.awt.Font("sansserif", 0, 14));
        bankDetailsButton.setText("Bank Details");
        personalOrderSelectPanel.add(bankDetailsButton);

        //Button to view the orders of a user
        orderPageButton.setFont(new java.awt.Font("sansserif", 0, 14));
        orderPageButton.setText("Orders");
        personalOrderSelectPanel.add(orderPageButton);

        //Button to view the Staff/Manager Dashboard based if the user role is Staff/Manager
        dashboardButton.setFont(new java.awt.Font("sansserif", 0, 14));
        dashboardButton.setText("Staff/Manager Dashboard");
        if (!userRole.equals("0")) {
            personalOrderSelectPanel.add(dashboardButton);
        }

        //Button to logout from the system
        logoutButton.setFont(new java.awt.Font("sansserif", 0, 14));
        logoutButton.setText("Logout");
        personalOrderSelectPanel.add(logoutButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.1;
        add(personalOrderSelectPanel, gridBagConstraints);

        //Initializing the Personal Details Panel
        personalDetailsPanel.setLayout(new java.awt.BorderLayout());

        //Button to edit personal details of a user
        editPersonalDetailsButton.setFont(new java.awt.Font("sansserif", 0, 14));
        editPersonalDetailsButton.setText("Edit Personal Details");
        personalDetailsPanel.add(editPersonalDetailsButton);

        personalDetailsPanel.add(editPersonalDetailsButton, java.awt.BorderLayout.NORTH);

        //Table to display personal details of a user
        personalDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Name", "Email", "House Number", "Road Name", "City", "Postcode"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        //Displaying users personal details from the database
        DefaultTableModel model = (DefaultTableModel) personalDetailsTable.getModel();
        Connection connection = DatabaseManager.getConnection();
        String name = Users.getUserName(connection, Login.currentUser[0]);
        String email = Users.getUserEmail(connection, Login.currentUser[0]);
        String address [] = Users.getAddress(connection, Login.currentUser[0]);
        String houseNum = address [0];
        String roadName = address [1];
        String city = address [2];
        String postcode = address [3];
        Object[] rowData = {name, email, houseNum, roadName, city, postcode};
        model.addRow(rowData);

        personalDetailsTable.setEditingColumn(6);
        personalDetailsScrollPane.setViewportView(personalDetailsTable);

        //Panel to edit personal details of the user
        editPersonalDetailsPanel = createEditPersonalDetailsPanel();

        personalDetailsPanel.add(personalDetailsScrollPane, java.awt.BorderLayout.CENTER);

        //Initializing Bank Details Panel
        bankDetailsPanel.setLayout(new java.awt.BorderLayout());

        //Table to display the bank details
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Card Type");
        tableModel.addColumn("Card Number");
        tableModel.addColumn("Expiry");
        tableModel.addColumn("CVV");

        bankDetailsTable.setEditingColumn(5);
        bankDetailsScrollPane.setViewportView(bankDetailsTable);

        //Adding values to the bank details pannel
        for (String details : bankingDetailsArray) {
            String[] splitDetails = details.split("\\s+");
            tableModel.addRow(splitDetails);
        }

        bankDetailsTable.setModel(tableModel);

        //Panel to edit the bank details of a user
        editBankDetailsPanel = createEditBankDetailsPanel();

        bankDetailsPanel.add(bankDetailsScrollPane, java.awt.BorderLayout.CENTER);

       //Initializing the Order Details Panel
        orderPanel.setLayout(new java.awt.BorderLayout());

        //Button to search an order
        orderSearchLabel.setFont(new java.awt.Font("sansserif", 0, 14));
        orderSearchLabel.setLabelFor(orderSearchTextField);
        orderSearchLabel.setText("Search by Order ID, User ID, Customer Name etc.:");
        orderSearchPanel.add(orderSearchLabel);

        //Search text field
        orderSearchTextField.setColumns(25);
        orderSearchTextField.setMinimumSize(new java.awt.Dimension(300, 40));
        orderSearchPanel.add(orderSearchTextField);

        //Search button
        orderSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        orderSearchButton.setText("Search");
        orderSearchPanel.add(orderSearchButton);

        //Clear search button
        orderClearSearchButton.setFont(new java.awt.Font("SansSerif", 1, 12));
        orderClearSearchButton.setText("Clear search");
        orderSearchPanel.add(orderClearSearchButton);

        orderPanel.add(orderSearchPanel, java.awt.BorderLayout.NORTH);

        //Table to display the order details of a user
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Date", "Order ID", "Customer Name", "Product ID", "Quantity", "Status"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });


        String[] orderDetails = Order.getUsersOrderDetails();
        DefaultTableModel ordrmodel = (DefaultTableModel) orderTable.getModel();
        ordrmodel.setRowCount(0); // Clear existing rows

        for (String orderDetail : orderDetails) {
            String[] orderDetailValues = orderDetail.split("/");
            ordrmodel.addRow(orderDetailValues);
        }

        orderTable.setEditingColumn(6);
        orderScrollPane.setViewportView(orderTable);



        orderPanel.add(orderScrollPane, java.awt.BorderLayout.CENTER);

        //Adding all Features to main panel
        mainPanel.setLayout(cardLayout);
        mainPanel.add(personalDetailsPanel, PRODUCT_PANEL);
        mainPanel.add(orderPanel, ORDER_PANEL);
        mainPanel.add(bankDetailsPanel, BANK_DETAIL_PANEL);
        mainPanel.add(editPersonalDetailsPanel, EDIT_PERSONAL_DETAILS);
        mainPanel.add(editBankDetailsPanel, EDIT_BANK_DETAILS);
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

    // Variables declaration
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel personalDetailsPanel;
    private javax.swing.JPanel personalOrderSelectPanel;
    private javax.swing.JPanel bankDetailsPanel;
    private javax.swing.JPanel orderPanel;
    private javax.swing.JPanel showEditPersonalDetailsPanel;
    private javax.swing.JPanel showEditBankDetailsPanel;
    private JPanel editPersonalDetailsPanel;
    private JPanel editBankDetailsPanel;
    private javax.swing.JPanel orderSearchPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane personalDetailsScrollPane;
    private javax.swing.JButton orderPageButton;
    private javax.swing.JButton bankDetailsButton;
    private javax.swing.JTable bankDetailsTable;
    private javax.swing.JScrollPane bankDetailsScrollPane;
    private javax.swing.JButton personalDetailsButton;
    private javax.swing.JTable personalDetailsTable;
    private javax.swing.JButton editPersonalDetailsButton;
    private javax.swing.JButton backToShopPageButton;
    private javax.swing.JScrollPane orderScrollPane;
    private javax.swing.JTable orderTable;
    private javax.swing.JButton orderSearchButton;
    private javax.swing.JButton dashboardButton;
    private javax.swing.JButton orderClearSearchButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel orderSearchLabel;
    private javax.swing.JTextField orderSearchTextField;
    private javax.swing.JLabel titleLabel;
    private CardLayout cardLayout;
    private static final String PRODUCT_PANEL = "Product Panel";
    private static final String ORDER_PANEL = "Order Panel";
    private static final String BANK_DETAIL_PANEL = "Bank Detail Panel";
    private static final String EDIT_PERSONAL_DETAILS = "Edit Personal Details";
    private static final String EDIT_BANK_DETAILS = "Edit Bank Details";
    private JTextField newNameField;
    private JTextField newHouseField;
    private JTextField newRoadField;
    private JTextField newCityField;
    private JTextField newPostcodeField;
    private JTextField newCardTypeField;
    private JTextField newCardNameField;
    private JTextField newCardNumberField;
    private JTextField newExpiryField;
    private JTextField newCvvField;
    private List<Product> products;
    //End of variable Declaration

   //Action Listeners for all the Buttons
    public void addActionListeners() {

        personalDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, PRODUCT_PANEL);
            }
        });

        editPersonalDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, EDIT_PERSONAL_DETAILS);
            }
        });

        orderPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, ORDER_PANEL);
            }
        });

        bankDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, BANK_DETAIL_PANEL);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Users.logout();
            }
        });

        backToShopPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               SwingUtilities.getWindowAncestor(Customer.this).dispose();
                JFrame frame = new JFrame ("Shop Page");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add (new ShopPage(products));
                frame.pack();
                frame.setVisible (true);
                JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Customer.this);
                if (loginFrame != null) {
                loginFrame.dispose();
                }
            }
        });

        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (userRole.equals("1")) {
                    StaffPage staff = new StaffPage();
                    JFrame staffframe = new JFrame("Staff Dashboard");
                    staffframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    staff.setPreferredSize(new Dimension(1000, 600));
                    staffframe.getContentPane().add(staff);
                    staffframe.pack();
                    staffframe.setVisible(true);
                    JFrame customerFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(Customer.this);
                    if (customerFrame != null) {
                        customerFrame.dispose();
                    }
                } else {
                    ManagerPage manager = new ManagerPage();
                    JFrame managerframe = new JFrame("Manager Dashboard");
                    managerframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    manager.setPreferredSize(new Dimension(1000, 600));
                    managerframe.getContentPane().add(manager);
                    managerframe.pack();
                    managerframe.setVisible(true);
                    JFrame customerFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(Customer.this);
                    if (customerFrame != null) {
                        customerFrame.dispose();
                    }
                }
            }
        });
    }

    //Populate the order table with user's orders
    public void populateOrderTable() {
        String search = orderSearchTextField.getText();
        ArrayList<Order> orders;
        if (search.equals("")) {
            Order.getUsersOrderDetails();
        }
    }

    //Panel for editing users Personal Details
    private JPanel createEditPersonalDetailsPanel() {
        JPanel panel = new JPanel();

        newNameField = new JTextField(30);
        newHouseField = new JTextField(30);
        newRoadField = new JTextField(30);
        newCityField = new JTextField(30);
        newPostcodeField = new JTextField(30);

        JButton savePersonalDetailsButton = new JButton("Save Personal Details");

        savePersonalDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = newNameField.getText();
                String newHouse = newHouseField.getText();
                String newRoad = newRoadField.getText();
                String newCity = newCityField.getText();
                String newPostcode = newPostcodeField.getText();


                JOptionPane.showMessageDialog(null, "Personal details saved successfully!");

                cardLayout.show(mainPanel, PRODUCT_PANEL);
            }
        });

        panel.add(new JLabel("New Name:"));
        panel.add(newNameField);
        panel.add(new JLabel("New House Number:"));
        panel.add(newHouseField);
        panel.add(new JLabel("New Road Name:"));
        panel.add(newRoadField);
        panel.add(new JLabel("New City:"));
        panel.add(newCityField);
        panel.add(new JLabel("New Postcode:"));
        panel.add(newPostcodeField);

        panel.add(savePersonalDetailsButton);

        return panel;
    }

    //Panel for editing User's Bank Details
    private JPanel createEditBankDetailsPanel() {
        JPanel panel = new JPanel();
        newCardTypeField = new JTextField(30);
        newCardNameField = new JTextField(30);
        newCardNumberField = new JTextField(30);
        newExpiryField = new JTextField(30);
        newCvvField = new JTextField(30);

        JButton saveBankDetailsButton = new JButton("Save Bank Details");
        saveBankDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCardType = newCardTypeField.getText();
                String newCardName = newCardNameField.getText();
                String newCardNumber = newCardNumberField.getText();
                String newExpiry = newExpiryField.getText();
                String newCvv = newCvvField.getText();


                JOptionPane.showMessageDialog(null, "Bank details saved successfully!");

                // After saving, switch back to the main panel
                cardLayout.show(mainPanel, PRODUCT_PANEL);
            }
        });

        panel.add(new JLabel("New Card Type:"));
        panel.add(newCardTypeField);
        panel.add(new JLabel("New Card Name:"));
        panel.add(newCardNameField);
        panel.add(new JLabel("New Card Number:"));
        panel.add(newCardNumberField);
        panel.add(new JLabel("New Expiry:"));
        panel.add(newExpiryField);
        panel.add(new JLabel("New CVV:"));
        panel.add(newCvvField);

        panel.add(saveBankDetailsButton);

        return panel;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("User Dashboard");
        Customer page = new Customer();
        page.setPreferredSize(new Dimension(1000, 600));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(page);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
