package trainsheffield;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;
import java.util.List;


public class Checkout extends JPanel {
    ArrayList<String> productCart = new ArrayList<String>();
    String[] items = {"Train Set", "1", "5","Track Set", "3", "7"};
    public static String[] totalCost = new String[1];;
    private List<Product> products;
    private JLabel jcomp1;
    private JTable jcomp2;
    private JButton jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JComboBox jcomp9;
    private JLabel jcomp10;
    private JTextField jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JTextField jcomp14;
    private JTextField jcomp15;
    private JButton jcomp16;
    private JLabel jcomp17;
    private JTextField jcomp18;
    private JButton jcomp19;

    public Checkout() {
        //construct components
        jcomp1 = new JLabel ("Welcome to Trains Sheffield");
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Code", "Brand", "Product Name","Quantity", "Line cost"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        };
        jcomp2 = new JTable(tableModel);
        jcomp19 = new JButton ("<- Back");
        jcomp3 = new JButton ("My Profile");
        jcomp4 = new JLabel ("Checkout");
        jcomp5 = new JLabel ("Total:");
        jcomp6 = new JLabel ("£ 0");
        jcomp7 = new JLabel ("Pay with Exisiting Payment Method");
        jcomp8 = new JLabel ("Pay with New Payment Method");
        jcomp9 = new JComboBox ();
        jcomp10 = new JLabel ("Card Number");
        jcomp11 = new JTextField ();
        jcomp12 = new JLabel ("Expiry");
        jcomp13 = new JLabel ("CVV");
        jcomp14 = new JTextField (5);
        jcomp15 = new JTextField (3);
        jcomp16 = new JButton ("Place Order");
        jcomp17 = new JLabel("Name on Card");
        jcomp18 = new JTextField();

        JScrollPane tableScrollPane = new JScrollPane(jcomp2);
        populateExistingPaymentMethod();
        
        for (String item : items) {
            productCart.add(item);
        }

        parseCart();

        //adjust size and set layout
        setPreferredSize (new Dimension (734, 472));
        setLayout (null);

        //add components
        add (jcomp1);
        add(tableScrollPane);
        //add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (jcomp11);
        add (jcomp12);
        add (jcomp13);
        add (jcomp14);
        add (jcomp15);
        add (jcomp16);
        add (jcomp17);
        add (jcomp18);
        add (jcomp19);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (275, 10, 165, 25);
        tableScrollPane.setBounds(20, 80, 690, 200);
        //jcomp2.setBounds (20, 80, 690, 200);
        jcomp3.setBounds (610, 45, 100, 25);
        jcomp19.setBounds (610, 15, 100, 25);
        jcomp4.setBounds (20, 50, 100, 25);
        jcomp5.setBounds (20, 290, 100, 25);
        jcomp6.setBounds (55, 290, 100, 25);
        jcomp7.setBounds (20, 320, 210, 20);
        jcomp8.setBounds (340, 285, 185, 20);
        jcomp9.setBounds (20, 345, 205, 25);
        jcomp10.setBounds (340, 310, 100, 25);
        jcomp11.setBounds (340, 335, 210, 25);
        jcomp12.setBounds (340, 360, 100, 25);
        jcomp13.setBounds (450, 360, 100, 25);
        jcomp14.setBounds (340, 385, 100, 25);
        jcomp15.setBounds (450, 385, 100, 25);
        jcomp16.setBounds (585, 415, 125, 45);
        jcomp17.setBounds (340, 410, 125, 25);
        jcomp18.setBounds (340, 435, 210, 25);

        this.addListener();
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("Checkout");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Checkout());
        frame.pack();
        frame.setVisible (true);
    }

    //populate the combox with the users saved card details
    public void populateExistingPaymentMethod() {
        String existingPaymentMethods [] = BankingDetails.getBankingDetails();
        jcomp9.addItem(" ");
        for (String paymentMethod : existingPaymentMethods) {
            jcomp9.addItem(paymentMethod);
        }
    }
    
    //validates the payment method and then saves it to the db
    public boolean cardForm() {
        String cardNumber = jcomp11.getText();
        String cardExp = jcomp14.getText();
        String cardCVV = jcomp15.getText();
        String userid = Login.currentUser[0];
        String card_name = jcomp18.getText();
        if (jcomp9.getSelectedItem().toString() == " " && cardNumber.isEmpty() && cardExp.isEmpty() && cardCVV.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a payment method!", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (jcomp9.getSelectedItem().toString() != " " && (!cardNumber.isEmpty() || !cardExp.isEmpty() || !cardCVV.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Please select ONE payment method!", "ERROR", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (jcomp9.getSelectedItem().toString() == " " && !cardNumber.isEmpty() && !cardExp.isEmpty() && !cardCVV.isEmpty()) {
            String card_type = BankingDetails.getCardType(cardNumber);
            Boolean verifiedCard = BankingDetails.verifyBank(cardNumber, cardExp, cardCVV);
            if (verifiedCard == true) {
                String encCardNumber = BankingDetails.encodeToBase64(cardNumber);
                BankingDetails.saveBankingDetails(userid, card_type, card_name, encCardNumber, cardExp, cardCVV);
                return true;
            }
        } else if (jcomp9.getSelectedItem().toString() != " " && cardNumber.isEmpty() && cardExp.isEmpty() && cardCVV.isEmpty()) {
            return true;
        }
        return false;
    }

    public void getCustomerDash() {
        Customer customer = new Customer();
        JFrame customerFrame = new JFrame("Customer Dashboard");
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.getContentPane().add(customer);
        customerFrame.pack();
        customerFrame.setVisible(true);
        JFrame checkoutFrame = (JFrame) SwingUtilities.getWindowAncestor(Checkout.this);
        if (checkoutFrame != null) {
            checkoutFrame.dispose();
        }
    }

    public void parseCart() {
        DefaultTableModel tableModel = (DefaultTableModel) jcomp2.getModel();
        
        // Clear existing rows in the table
        tableModel.setRowCount(0);
    
        double totalPrice = 0.0; 

        for (Object[] orderLine : ShoppingBasket.orderLines) {
            // Convert each Object[] to a String and add it to productCart
            productCart.add(Arrays.toString(orderLine));
            //System.out.println(Arrays.toString(orderLine));
            tableModel.addRow(orderLine);
            totalPrice += Double.parseDouble(orderLine[4].toString()) * Integer.parseInt(orderLine[3].toString());

        }
        // Update the jcomp6 label with the total price
        jcomp6.setText("£ " + String.format("%.2f", totalPrice));
        totalCost[0] = String.valueOf(totalPrice);
    }
    
    
    //checks if users has entered valid payment before placing order
    public void addListener() {
        jcomp16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cardForm() == true) {
                    try {
                    getCustomerDash();
                    Order.saveOrder(ShoppingBasket.orderLines);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                }
            }
        });

        jcomp3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer();
                JFrame customerFrame = new JFrame("Customer Dashboard");
                customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerFrame.getContentPane().add(customer);
                customerFrame.pack();
                customerFrame.setVisible(true);
                JFrame checkoutFrame = (JFrame) SwingUtilities.getWindowAncestor(Checkout.this);
                if (checkoutFrame != null) {
                    checkoutFrame.dispose();
                }
            }
        });

        jcomp19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(Checkout.this).dispose();
                JFrame frame = new JFrame ("Shop Page");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add (new ShopPage(products));
                frame.pack();
                frame.setVisible (true);
                JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Checkout.this);
                if (loginFrame != null) {
                loginFrame.dispose();
                }
            }
        });
    }

}