
package trainsheffield;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingBasket extends JPanel {
    private JButton jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JList jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JTable jcomp8;
    private JLabel jcomp9;
    private JTextField jcomp10;
    private JButton jcomp11;
    ArrayList<String> productCart = new ArrayList<String>();
    public static ArrayList<Object[]> orderLines;
    private List<Product> shopProducts;

    public ShoppingBasket(ArrayList<Object[]> orderLines) {


        setLayout(new BorderLayout());


        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Code", "Brand", "Product Name","Quantity", "Line cost"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        };

        Connection connection = DatabaseManager.getConnection();
        String name = Users.getUserName(connection, Login.currentUser[0]);
        String email = Users.getUserEmail(connection, Login.currentUser[0]);
        String [] addressarray = Users.getAddress(connection, Login.currentUser[0]);
        String houseNumber = addressarray[0];
        String roadName = addressarray[1];
        String city = addressarray[2];
        String postcode = addressarray[3];
        String address = houseNumber + ", " + roadName + ", " + city + ", " + postcode;
        String[] jcomp4Items = {name, email, address};

        //construct components
        jcomp1 = new JButton ("Checkout");
        jcomp2 = new JLabel ("Status:");
        jcomp3 = new JLabel ("PENDING");
        jcomp4 = new JList (jcomp4Items);
        jcomp5 = new JLabel ("Trains Sheffield");
        jcomp6 = new JLabel ("Order Details");
        jcomp7 = new JLabel ("Order Items");
        jcomp8 = new JTable(tableModel);
        jcomp9 = new JLabel ("Total: ");
        jcomp10 = new JTextField ("£");
        jcomp10.setEditable(false);
        jcomp11 = new JButton("Back");
        JScrollPane tableScrollPane = new JScrollPane(jcomp8);

        //adjust size and set layout
        setPreferredSize (new Dimension (734, 472));
        setLayout (null);


        // Add each Object[] in orderLines as a row in the table model
        for (Object[] orderLine : orderLines) {
            tableModel.addRow(orderLine);
        }

        setOrderLines(orderLines);
        setTableModel(tableModel);
        parseCart();
        //add action listeners
        openCheckout();

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add(tableScrollPane);
        add (jcomp9);
        add (jcomp10);
        add(jcomp11);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (610, 420, 100, 25);
        jcomp2.setBounds (20, 25, 100, 25);
        jcomp3.setBounds (95, 25, 100, 25);
        jcomp4.setBounds (20, 100, 690, 130);
        jcomp5.setBounds (320, 10, 140, 25);
        jcomp6.setBounds (20, 60, 100, 25);
        jcomp7.setBounds (20, 245, 100, 25);
        jcomp9.setBounds(150,420,100,25);
        jcomp10.setBounds(200,420,100,25);
        jcomp11.setBounds(20,420,100,25);
        tableScrollPane.setBounds (20, 280, 690, 130);


        jcomp11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Shop Page");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add (new ShopPage(shopProducts));
                frame.pack();
                frame.setVisible (true);
                JFrame Sframe = (JFrame) SwingUtilities.getWindowAncestor(ShoppingBasket.this);
                if (Sframe != null) {
                Sframe.dispose();
                }
            }
        });



    }

    public void setOrderLines(ArrayList<Object[]> orderLines) {
        this.orderLines = orderLines;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.jcomp8.setModel(tableModel);
    }
    public void parseCart() {
        DefaultTableModel tableModel = (DefaultTableModel) jcomp8.getModel();
        // System.out.println("in the parseCart method: " + productName + manufacturer + productCode + retailPrice);

        // Clear existing rows in the table
        tableModel.setRowCount(0);

        double totalPrice = 0.0; // Variable to store the total price

        for (Object[] orderLine : orderLines) {
            // Convert each Object[] to a String and add it to productCart
            productCart.add(Arrays.toString(orderLine));
            //System.out.println(Arrays.toString(orderLine));
            tableModel.addRow(orderLine);
            totalPrice += Double.parseDouble(orderLine[4].toString()) * Integer.parseInt(orderLine[3].toString());

        }
        // Update the jcomp6 label with the total price
        jcomp10.setText("£ " + String.format("%.2f", totalPrice));
    }

    //displays the checkout page upon finishing an order
        //pass in the productCart array list to this
    public void openCheckout() {
        jcomp1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame ("Checkout");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                //orderLines = new ArrayList<Object[]>();
                //System.out.println(orderLines);
                frame.getContentPane().add (new Checkout());
                frame.pack();
                frame.setVisible (true);
                JFrame Sframe = (JFrame) SwingUtilities.getWindowAncestor(ShoppingBasket.this);
                if (Sframe != null) {
                Sframe.dispose();
            }
        }
        });
    }
    public static void main (String[] args) {
    JFrame frame = new JFrame ("Order Page");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    List<Object> product = new ArrayList<>();
    frame.pack();
    frame.setVisible (true);
    }
}
