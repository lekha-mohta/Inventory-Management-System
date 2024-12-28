package trainsheffield;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopPage extends JPanel {

  private JButton jcomp1, jcomp2, jcomp3, jcomp22, jcomp23, jcomp24, jcomp25, jcomp26, jcomp27, jcomp28;
  private JButton[] productButtons = {jcomp1, jcomp2, jcomp3, jcomp22, jcomp23, jcomp24, jcomp25, jcomp26, jcomp27, jcomp28};
  private JLabel jcomp4, jcomp5, jcomp6, jcomp29, jcomp30, jcomp31, jcomp32, jcomp33, jcomp34, jcomp35;
  private JLabel[] productLabels;
  private JTextField  jcomp18 ,jcomp16, jcomp17;
  private JTextField[] quantityTextFields;
  private JButton jcomp11, jcomp13, jcomp15, jcomp44, jcomp46, jcomp48, jcomp50, jcomp52, jcomp54, jcomp56;
  private JButton[] plusButtons;
  private JButton jcomp12, jcomp14, jcomp43, jcomp45, jcomp47, jcomp49, jcomp51, jcomp53, jcomp55;
  private JButton[] minusButtons;
 
  private List<Product> products;
  private JLabel jcomp7;
  private JButton jcomp8;
  private JButton jcomp9;
  private JButton jcomp10;
  // private JButton jcomp11;
  // private JButton jcomp12;
  // private JButton jcomp13;
  // private JButton jcomp14;
  // private JButton jcomp15;
  private JLabel jcomp19;
  private JLabel jcomp20;
  private JLabel jcomp21;
  private JTextField jcomp36;
  private JTextField jcomp37;
  private JTextField jcomp38;
  private JTextField jcomp39;
  private JTextField jcomp40;
  private JTextField jcomp41;
  private JTextField jcomp42;
  // private JButton jcomp43;
  // private JButton jcomp44;
  // private JButton jcomp45;
  // private JButton jcomp46;
  // private JButton jcomp47;
  // private JButton jcomp48;
  // private JButton jcomp49;
  // private JButton jcomp50;
  // private JButton jcomp51;
  // private JButton jcomp52;
  // private JButton jcomp53;
  // private JButton jcomp54;
  // private JButton jcomp55;
  // private JButton jcomp56;
  private JLabel jcomp57;
  private JLabel jcomp58;
  private JLabel jcomp59;
  private JLabel jcomp60;
  private JLabel jcomp61;
  private JLabel jcomp62;
  private JLabel jcomp63;

  public ShopPage(List<Product> products) {
    this.products = products;


    //construct components
    jcomp1 = new JButton ("Button 1");
    jcomp2 = new JButton ("Button 2");
    jcomp3 = new JButton ("Button 3");
    jcomp4 = new JLabel ("newLabel");
    jcomp5 = new JLabel ("newLabel");
    jcomp6 = new JLabel ("newLabel");
    jcomp7 = new JLabel ("Trains Sheffield");
    jcomp8 = new JButton ("Order");
    jcomp9 = new JButton ("Dashboard");
    jcomp10 = new JButton ("-");
    jcomp11 = new JButton ("+");
    jcomp12 = new JButton ("-");
    jcomp13 = new JButton ("+");
    jcomp14 = new JButton ("-");
    jcomp15 = new JButton ("+");
    jcomp16 = new JTextField ("0");
    jcomp17 = new JTextField ("0");
    jcomp18 = new JTextField ("0");
    jcomp16.setEditable(false);
    jcomp17.setEditable(false);
    jcomp18.setEditable(false);
    jcomp19 = new JLabel ("Select Quantity:");
    jcomp20 = new JLabel ("Select Quantity:");
    jcomp21 = new JLabel ("Select Quantity:");
    jcomp22 = new JButton("Waiting for stock");
    jcomp23 = new JButton("Waiting for stock");
    jcomp24 = new JButton("Waiting for stock");
    jcomp25 = new JButton("Waiting for stock");
    jcomp26 = new JButton("Waiting for stock");
    jcomp27 = new JButton("Waiting for stock");
    jcomp28 = new JButton("Waiting for stock");
    jcomp29 = new JLabel("Select Quantity:");
    jcomp30 = new JLabel("Select Quantity:");
    jcomp31 = new JLabel("Select Quantity:");
    jcomp32 = new JLabel("Select Quantity:");
    jcomp33 = new JLabel("Select Quantity:");
    jcomp34 = new JLabel("Select Quantity:");
    jcomp35 = new JLabel("Select Quantity:");
    jcomp36 = new JTextField("0");
    jcomp37 = new JTextField("0");
    jcomp38 = new JTextField("0");
    jcomp39 = new JTextField("0");
    jcomp40 = new JTextField("0");
    jcomp41 = new JTextField("0");
    jcomp42 = new JTextField("0");
    jcomp36.setEditable(false);
    jcomp37.setEditable(false);
    jcomp38.setEditable(false);
    jcomp39.setEditable(false);
    jcomp40.setEditable(false);
    jcomp41.setEditable(false);
    jcomp42.setEditable(false);
    jcomp43 = new JButton("-");
    jcomp44 = new JButton("+");
    jcomp45 = new JButton("-");
    jcomp46 = new JButton("+");
    jcomp47 = new JButton("-");
    jcomp48 = new JButton("+");
    jcomp49 = new JButton("-");
    jcomp50 = new JButton("+");
    jcomp51 = new JButton("-");
    jcomp52 = new JButton("+");
    jcomp53 = new JButton("-");
    jcomp54 = new JButton("+");
    jcomp55 = new JButton("-");
    jcomp56 = new JButton("+");
    jcomp57 = new JLabel("Product Details");
    jcomp58 = new JLabel("Product Details");
    jcomp59 = new JLabel("Product Details");
    jcomp60 = new JLabel("Product Details");
    jcomp61 = new JLabel("Product Details");
    jcomp62 = new JLabel("Product Details");
    jcomp63 = new JLabel("Product Details");

    //update the buttons with the product details
    JButton[] productButtons = {jcomp1, jcomp2, jcomp3, jcomp22, jcomp23, jcomp24, jcomp25, jcomp26, jcomp27, jcomp28};
    JLabel[] productLabels = {jcomp4, jcomp5, jcomp6, jcomp57, jcomp58, jcomp59, jcomp60, jcomp61, jcomp62, jcomp63};
    quantityTextFields = new JTextField[]{jcomp18 ,jcomp16, jcomp17, jcomp36, jcomp37, jcomp38, jcomp39, jcomp40, jcomp41, jcomp42};
    JButton[] plusButtons = {jcomp11, jcomp13, jcomp15, jcomp44, jcomp46, jcomp48, jcomp50, jcomp52, jcomp54, jcomp56};

    try {
      products = DatabaseOperations.selectAllProducts();

      if (products != null) {
        int index = 0;

        for (Product product : products) {
            // System.out.println("in the loop");
            Object[] rowData = { product.getProductCode(), product.getProductName(), product.getManufacturer(),
            product.getRetailPrice(), product.getStock() };
            if (index < productButtons.length && product.getStock() > 0) {

              productButtons[index].setText(product.getProductName() + " " + product.getManufacturer());
              productLabels[index].setText("<html>code: " + product.getProductCode() + "<br>price: " + product.getRetailPrice() + "</html>");
              stockCheck(product, product.getStock(), quantityTextFields[index]);
              index++;

            }


        }
        // create an action listener for each button
        for (int i = 0; i < productButtons.length; i++) {
          if (i < products.size() && products.get(i).getStock() > 0) {
              final Product currentProduct = products.get(i);
              productButtons[i].addActionListener(createButtonClickListener(quantityTextFields[i], currentProduct));
          }
        }

        for (int i = 0; i < productButtons.length; i++) {
          if (i < products.size() && products.get(i).getStock() > 0) {
              final Product currentProduct = products.get(i);
              plusButtons[i].addActionListener(createButtonClickListener(quantityTextFields[i], currentProduct));
          }
        }
      }
    } catch (Exception e) {
        e.printStackTrace();
    }

      //adjust size and set layout
      setPreferredSize (new Dimension (1280, 1500));
      setLayout (null);

      //add components
      add(jcomp1);
      add(jcomp2);
      add(jcomp3);
      add(jcomp4);
      add(jcomp5);
      add(jcomp6);
      add(jcomp7);
      add(jcomp8);
      add(jcomp9);
      add(jcomp10);
      add(jcomp11);
      add(jcomp12);
      add(jcomp13);
      add(jcomp14);
      add(jcomp15);
      add(jcomp16);
      add(jcomp17);
      add(jcomp18);
      add(jcomp19);
      add(jcomp20);
      add(jcomp21);
      add(jcomp22);
      add(jcomp23);
      add(jcomp24);
      add(jcomp25);
      add(jcomp26);
      add(jcomp27);
      add(jcomp28);
      add(jcomp29);
      add(jcomp30);
      add(jcomp31);
      add(jcomp32);
      add(jcomp33);
      add(jcomp34);
      add(jcomp35);
      add(jcomp36);
      add(jcomp37);
      add(jcomp38);
      add(jcomp39);
      add(jcomp40);
      add(jcomp41);
      add(jcomp42);
      add(jcomp43);
      add(jcomp44);
      add(jcomp45);
      add(jcomp46);
      add(jcomp47);
      add(jcomp48);
      add(jcomp49);
      add(jcomp50);
      add(jcomp51);
      add(jcomp52);
      add(jcomp53);
      add(jcomp54);
      add(jcomp55);
      add(jcomp56);
      add(jcomp57);
      add(jcomp58);
      add(jcomp59);
      add(jcomp60);
      add(jcomp61);
      add(jcomp62);
      add(jcomp63);

      //set component bounds

      //product buttons
      jcomp1.setBounds (15, 60, 295, 85);
      jcomp2.setBounds (330, 60, 295, 85);
      jcomp3.setBounds (645,60,295,85);
      jcomp22.setBounds (965, 60, 295, 85);
      jcomp23.setBounds (15, 225, 295, 85);
      jcomp24.setBounds (330, 225, 295, 85);
      jcomp25.setBounds (645, 225, 295, 85);
      jcomp26.setBounds (965, 225, 295, 85);
      jcomp27.setBounds(15, 390, 295, 85);
      jcomp28.setBounds(330, 390, 295, 85);

      //product labels
      jcomp4.setBounds (15, 160, 100, 30);
      jcomp5.setBounds (330, 160, 100, 30);
      jcomp6.setBounds (645, 160, 100, 30);
      jcomp57.setBounds (965, 160, 100, 30);
      jcomp58.setBounds(15,325,100,30);
      jcomp59.setBounds(330,325,100,30);
      jcomp60.setBounds(645,325,100,30);
      jcomp61.setBounds(965,325,100,30);
      jcomp62.setBounds(15,490,100,30);
      jcomp63.setBounds(330,490,100,30);

      //Title and link buttons
      jcomp7.setBounds (50, 7, 120, 25);
      jcomp8.setBounds (15,600,100,30);
      jcomp9.setBounds (300, 10, 120, 25);

      // - buttons
      jcomp10.setBounds (135,190,45,25);
      jcomp12.setBounds (470,190,45,25);
      jcomp14.setBounds (775,190,45,25);
      jcomp43.setBounds(1085,190,45,25);
      jcomp45.setBounds(135,355,45,25);
      jcomp47.setBounds(470,355,45,25);
      jcomp49.setBounds(775,355,45,25);
      jcomp51.setBounds(1085,355,45,25);
      jcomp53.setBounds(470,520,45,25);
      jcomp55.setBounds(135,520,45,25);

      // + buttons
      jcomp11.setBounds (245,190,45,25);
      jcomp13.setBounds (580,190,45,25);
      jcomp15.setBounds (885,190,45,25);
      jcomp44.setBounds(1195,190,45,25);
      jcomp46.setBounds(245,355,45,25);
      jcomp48.setBounds(580,355,45,25);
      jcomp50.setBounds(885,355,45,25);
      jcomp52.setBounds(1195,355,45,25);
      jcomp54.setBounds(245,520,45,25);
      jcomp56.setBounds(580,520,45,25);

      //quantity text fields
      jcomp18.setBounds (190,190,45,25);
      jcomp16.setBounds (525,190,45,25);
      jcomp17.setBounds (830,190,45,25);
      jcomp36.setBounds(1140,190,45,25);
      jcomp37.setBounds(190,355,45,25);
      jcomp38.setBounds(525,355,45,25);
      jcomp39.setBounds(830,355,45,25);
      jcomp40.setBounds(1140,355,45,25);
      jcomp41.setBounds(190,520,45,25);
      jcomp42.setBounds(525,520,45,25);

      //Quantity labels
      jcomp19.setBounds (15,190,120,25);
      jcomp20.setBounds (330,190,120,25);
      jcomp21.setBounds (645,190,120,25);
      jcomp29.setBounds (965, 190, 120, 30);
      jcomp30.setBounds(15,355,120,30);
      jcomp31.setBounds(330,355,120,30);
      jcomp32.setBounds(645,355,120,30);
      jcomp33.setBounds(965,355,120,30);
      jcomp34.setBounds(15,520,120,30);
      jcomp35.setBounds(330,520,120,30);



      // Add an ActionListener to the "-" button
      jcomp10.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        int currentValue = Integer.parseInt(jcomp18.getText());
        if (currentValue > 0) {
            currentValue--;
        }
        jcomp18.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp11.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          Integer currentValue = Integer.parseInt(jcomp18.getText());
          jcomp18.setText(String.valueOf(currentValue));
           }
      });

      // Add an ActionListener to the "-" button
      jcomp12.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int currentValue = Integer.parseInt(jcomp16.getText());
          if (currentValue > 0) {
              currentValue--;
          }
          jcomp16.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp13.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int currentValue = Integer.parseInt(jcomp16.getText());
          jcomp16.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp14.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int currentValue = Integer.parseInt(jcomp17.getText());
          if (currentValue > 0) {
              currentValue--;
          }
          jcomp17.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp15.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int currentValue = Integer.parseInt(jcomp17.getText());
          jcomp17.setText(String.valueOf(currentValue));
      }
      });

      // Add an ActionListener to the "-" button
      jcomp43.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer currentValue = Integer.parseInt(jcomp36.getText());
            if (currentValue > 0) {
                currentValue--;
            }
            jcomp36.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp44.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp36.getText());
            jcomp36.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp45.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp37.getText());
            if (currentValue > 0) {
                currentValue--;
            }
            jcomp37.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp46.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp37.getText());
          jcomp37.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp47.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp38.getText());
            if (currentValue > 0) {
              currentValue--;
            }
            jcomp38.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp48.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp38.getText());
          jcomp38.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp49.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp39.getText());
            if (currentValue > 0) {
              currentValue--;
            }
            jcomp39.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp50.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp39.getText());
          jcomp39.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp51.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp40.getText());
            if (currentValue > 0) {
              currentValue--;
            }
            jcomp40.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "+" button
      jcomp52.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp40.getText());
           
          jcomp40.setText(String.valueOf(currentValue));
        }
      });


      // Add an ActionListener to the "+" button
      jcomp54.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int currentValue = Integer.parseInt(jcomp41.getText());
          
          jcomp41.setText(String.valueOf(currentValue));
        }
      });

      // Add an ActionListener to the "-" button
      jcomp55.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp41.getText());
            if (currentValue > 0) {
                currentValue--;
            }
            jcomp41.setText(String.valueOf(currentValue));
        }
      });

      jcomp56.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(jcomp42.getText());
            if (currentValue > 0) {
                currentValue--;
            }
          jcomp42.setText(String.valueOf(currentValue));
        }
      });




      //add action listeners
      openOrders();
      openDashboard();
  }


  //displays the order page when the order button is clicked
  //creates an order line for each product
  public void openOrders() {
    jcomp8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        ArrayList<Product> products = new ArrayList<>();

        //looking at each text field
        ArrayList<Object[]> orderLines = new ArrayList<>();

        for (int i = 0; i < quantityTextFields.length; i++) {
          String text = quantityTextFields[i].getText();
          int quantity = Integer.parseInt(text);

          if (quantity > 0) {
            try {
              products = DatabaseOperations.selectAllProducts();

              if (i < products.size()) {
                Product product = products.get(i);
                //System.out.println("product: " + product);
                Object[] productDetails = { product.getProductCode(), product.getManufacturer(),product.getProductName()
                  ,quantity, product.getRetailPrice(),
                  product.getStock() };
                // System.out.println("The row data:" +  Arrays.toString(productDetails));
                orderLines.add(productDetails);
                //System.out.println("orderLines: ");
                for (Object[] orderLine : orderLines) {
                  //System.out.println(Arrays.toString(orderLine));
                }

              }
          } catch (Exception x) {
              x.printStackTrace();
          }
          }

        }

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Code", "Brand", "Product Name","Quantity", "Line cost"}, 0);
        ShoppingBasket shoppingBasket = new ShoppingBasket(orderLines);
        shoppingBasket.setTableModel(tableModel);


        SwingUtilities.getWindowAncestor(ShopPage.this).dispose();
        JFrame frame = new JFrame ("Shopping Basket");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (new ShoppingBasket(orderLines));
        frame.pack();
        frame.setVisible (true);
      }
    });
  }

  //opens the user dashboard when clicked
  public void openDashboard() {
    jcomp9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        SwingUtilities.getWindowAncestor(ShopPage.this).dispose();
        Customer customer = new Customer();
        JFrame customerFrame = new JFrame("Customer Dashboard");
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.getContentPane().add(customer);
        customerFrame.pack();
        customerFrame.setVisible(true);

      }
    });
  }

  //don't allow the product quantity to go above the stock in the database
  public void stockCheck(Product product,int currentValue,final JTextField quantityTextField){
  
    if (currentValue > product.getStock()) {
        quantityTextField.setText(String.valueOf(product.getStock()));
    }
  }

  //increments the quantity when the button is clicked
  private ActionListener createButtonClickListener(final JTextField quantityTextField, final Product product) {
    return new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentValue = Integer.parseInt(quantityTextField.getText());
            currentValue++;
            quantityTextField.setText(String.valueOf(currentValue));
            stockCheck(product, currentValue, quantityTextField);
        }
    };
  }

  public static void main (String[] args) {
      JFrame frame = new JFrame ("Shop Page");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      List<Product> products = new ArrayList<>();
      frame.getContentPane().add (new ShopPage(products));
      frame.pack();
      frame.setVisible (true);

  }





}