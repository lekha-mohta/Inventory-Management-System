package trainsheffield;

import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import trainsheffield.Locomotive.DccCode;

import java.awt.CardLayout;

/*
 * @author Joel Foster
 */

public class AddEditProductPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddEditProduct
     */
    public AddEditProductPanel() {
        initComponents();
        addActionListeners();
        populateComboBoxes();
    }

    public AddEditProductPanel(StaffPage staffPage) {
        initComponents();
        addActionListeners();
        populateComboBoxes();
        this.staffPage = staffPage;
        manager = false;
    }

    public AddEditProductPanel(ManagerPage managerPage) {
        initComponents();
        addActionListeners();
        populateComboBoxes();
        this.managerPage = managerPage;
        manager = true;
    }

    private void initComponents() {
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        productCodePanel = new javax.swing.JPanel();
        productCodeLabel = new javax.swing.JLabel();
        productCodeTextField = new javax.swing.JTextField();
        productNamePanel = new javax.swing.JPanel();
        productNameLabel = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        manufacturerPanel = new javax.swing.JPanel();
        manufacturerLabel = new javax.swing.JLabel();
        manufacturerComboBox = new javax.swing.JComboBox<>();
        retailPricePanel = new javax.swing.JPanel();
        retailPriceLabel = new javax.swing.JLabel();
        retailPriceTextField = new javax.swing.JTextField();
        stockLabel = new javax.swing.JLabel();
        stockComboBox = new javax.swing.JComboBox<>();
        gaugePanel = new javax.swing.JPanel();
        gaugeLabel = new javax.swing.JLabel();
        gaugeTextField = new javax.swing.JTextField();
        productTypePanel = new javax.swing.JPanel();
        productTypeLabel = new javax.swing.JLabel();
        productTypeComboBox = new javax.swing.JComboBox<>();
        eraDccPanel = new javax.swing.JPanel();
        eraCodeLabel = new javax.swing.JLabel();
        eraCodeTextField = new javax.swing.JTextField();
        eraCodeLabel2 = new javax.swing.JLabel();
        eraCodeTextField2 = new javax.swing.JTextField();
        dccCodeLabel = new javax.swing.JLabel();
        dccCodeComboBox = new javax.swing.JComboBox<>();
        digitalLabel = new javax.swing.JLabel();
        digitalComboBox = new javax.swing.JComboBox<>();
        trainSetPanel = new javax.swing.JPanel();
        controllerPanel = new javax.swing.JPanel();
        controllerLabel = new javax.swing.JLabel();
        controllerComboBox = new javax.swing.JComboBox<>();
        locomotivePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        locomotiveComboBox = new javax.swing.JComboBox<>();
        addLocomotiveButton = new javax.swing.JButton();
        allLocomotivesComboBox = new javax.swing.JComboBox<>();
        rollingStockPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        rollingStockComboBox = new javax.swing.JComboBox<>();
        addRollingStockButton = new javax.swing.JButton();
        allRollingStockComboBox = new javax.swing.JComboBox<>();
        addTrackPackPanel = new javax.swing.JPanel();
        trackPackLabel = new javax.swing.JLabel();
        addTrackPackButton = new javax.swing.JButton();
        trackPackComboBox = new javax.swing.JComboBox<>();
        allTrackPackComboBox = new javax.swing.JComboBox<>();
        removeLocomotiveButton = new javax.swing.JButton();
        removeRollingStockButton = new javax.swing.JButton();
        removeTrackPackButton = new javax.swing.JButton();
        trackPackPanel = new javax.swing.JPanel();
        trackPieceLabel = new javax.swing.JLabel();
        trackPiecesComboBox = new javax.swing.JComboBox<>();
        addTrackPieceButton = new javax.swing.JButton();
        allTrackPiecesComboBox = new javax.swing.JComboBox<>();
        removeTrackPieceButton = new javax.swing.JButton();
        quantityLabel = new javax.swing.JLabel();
        quantityComboBox = new javax.swing.JComboBox<>();
        errorAlertPanel = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();
        bottomButtonPanel = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        jPanel5.setPreferredSize(new java.awt.Dimension(1054, 35));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel3.setText("Add/Edit Product");
        jLabel3.setToolTipText("");
        jPanel5.add(jLabel3);

        add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.GridLayout(11, 1));

        productCodePanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        productCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productCodeLabel.setLabelFor(productCodeTextField);
        productCodeLabel.setText("Product Code:");
        productCodePanel.add(productCodeLabel);

        productCodeTextField.setColumns(15);
        productCodeTextField.setToolTipText("");
        productCodePanel.add(productCodeTextField);

        jPanel4.add(productCodePanel);

        productNamePanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        productNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productNameLabel.setLabelFor(productNameTextField);
        productNameLabel.setText("Product Name:");
        productNamePanel.add(productNameLabel);

        productNameTextField.setColumns(15);
        productNamePanel.add(productNameTextField);

        jPanel4.add(productNamePanel);

        manufacturerPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        manufacturerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manufacturerLabel.setLabelFor(manufacturerComboBox);
        manufacturerLabel.setText("Manufacturer:");
        manufacturerPanel.add(manufacturerLabel);

        manufacturerComboBox.setEditable(true);
        manufacturerPanel.add(manufacturerComboBox);

        jPanel4.add(manufacturerPanel);

        retailPricePanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        retailPriceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        retailPriceLabel.setLabelFor(retailPriceTextField);
        retailPriceLabel.setText("Retail Price:");
        retailPricePanel.add(retailPriceLabel);

        retailPriceTextField.setColumns(15);
        retailPriceTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        retailPriceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                retailPriceTextFieldKeyTyped(evt);
            }
        });
        retailPricePanel.add(retailPriceTextField);

        stockLabel.setText("Stock:");
        retailPricePanel.add(stockLabel);

        stockComboBox.setModel(new DefaultComboBoxModel<>(new Integer[] { 0 }));
        for (int i = 1; i <= 99; i++) {
            stockComboBox.addItem(i);
        }
        stockComboBox.setEditable(true);
        retailPricePanel.add(stockComboBox);

        jPanel4.add(retailPricePanel);

        gaugePanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        gaugeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gaugeLabel.setLabelFor(gaugeTextField);
        gaugeLabel.setText("Gauge:");
        gaugePanel.add(gaugeLabel);

        gaugeTextField.setColumns(15);
        gaugePanel.add(gaugeTextField);

        eraCodeLabel2.setLabelFor(eraCodeTextField2);
        eraCodeLabel2.setText("Era Code:");
        gaugePanel.add(eraCodeLabel2);

        eraCodeTextField2.setColumns(10);
        gaugePanel.add(eraCodeTextField2);

        eraCodeLabel2.setVisible(false);
        eraCodeTextField2.setVisible(false);

        jPanel4.add(gaugePanel);

        productTypePanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        productTypeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productTypeLabel.setLabelFor(productTypeComboBox);
        productTypeLabel.setText("Product Type:");
        productTypePanel.add(productTypeLabel);

        productTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "locomotive",
                "rolling stock", "train set", "controller", "track pack", "track piece" }));
        productTypePanel.add(productTypeComboBox);

        jPanel4.add(productTypePanel);

        eraDccPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        eraCodeLabel.setLabelFor(eraCodeTextField);
        eraCodeLabel.setText("Era Code:");
        eraDccPanel.add(eraCodeLabel);

        eraCodeTextField.setColumns(10);
        eraDccPanel.add(eraCodeTextField);

        dccCodeLabel.setLabelFor(dccCodeComboBox);
        dccCodeLabel.setText("Dcc Code:");
        eraDccPanel.add(dccCodeLabel);

        dccCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(Locomotive.DccCode.values()));
        eraDccPanel.add(dccCodeComboBox);

        digitalLabel.setLabelFor(digitalComboBox);
        digitalLabel.setText("Digital:");
        eraDccPanel.add(digitalLabel);

        digitalComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new Boolean[] { true, false }));
        eraDccPanel.add(digitalComboBox);

        trainSetPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        controllerLabel.setText("Controller:");
        controllerPanel.add(controllerLabel);

        controllerPanel.add(controllerComboBox);

        trainSetPanel.add(controllerPanel);

        jLabel2.setText("Locomotives:");
        locomotivePanel.add(jLabel2);

        locomotivePanel.add(locomotiveComboBox);

        addLocomotiveButton.setText("Add Locomotive:");
        locomotivePanel.add(addLocomotiveButton);

        locomotivePanel.add(allLocomotivesComboBox);

        trainSetPanel.add(locomotivePanel);

        jLabel5.setText("Rolling Stock:");
        rollingStockPanel.add(jLabel5);

        rollingStockPanel.add(rollingStockComboBox);

        addRollingStockButton.setText("Add Rolling Stock:");
        rollingStockPanel.add(addRollingStockButton);

        rollingStockPanel.add(allRollingStockComboBox);

        trainSetPanel.add(rollingStockPanel);

        trackPackPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        trackPieceLabel.setText("Track Pieces:");
        trackPackPanel.add(trackPieceLabel);

        trackPackPanel.add(trackPiecesComboBox);

        addTrackPieceButton.setText("Add Track Piece:");
        trackPackPanel.add(addTrackPieceButton);

        trackPackPanel.add(allTrackPiecesComboBox);

        quantityLabel.setText("Quantity:");
        trackPackPanel.add(quantityLabel);

        quantityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
                new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }));
        quantityComboBox.setEditable(true);
        trackPackPanel.add(quantityComboBox);

        removeTrackPieceButton.setText("Remove selected Track Piece");
        trackPackPanel.add(removeTrackPieceButton);

        initialiseCardLayout();
        jPanel4.add(cardPanel);

        errorAlertPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        errorLabel.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 0, 51));

        // javax.swing.GroupLayout errorAlertPanelLayout = new
        // javax.swing.GroupLayout(errorAlertPanel);
        // errorAlertPanel.setLayout(errorAlertPanelLayout);
        // errorAlertPanelLayout.setHorizontalGroup(
        // errorAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGap(0, 1054, Short.MAX_VALUE)
        // .addGroup(errorAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(errorAlertPanelLayout.createSequentialGroup()
        // .addGap(0, 0, Short.MAX_VALUE)
        // .addComponent(errorLabel)
        // .addGap(0, 0, Short.MAX_VALUE)))
        // );
        // errorAlertPanelLayout.setVerticalGroup(
        // errorAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGap(0, 68, Short.MAX_VALUE)
        // .addGroup(errorAlertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        // .addGroup(errorAlertPanelLayout.createSequentialGroup()
        // .addGap(0, 34, Short.MAX_VALUE)
        // .addComponent(errorLabel)
        // .addGap(0, 34, Short.MAX_VALUE)))
        // );
        errorAlertPanel.add(errorLabel);

        trackPackLabel.setText("Track Packs:");
        addTrackPackPanel.add(trackPackLabel);

        addTrackPackPanel.add(trackPackComboBox);

        addTrackPackButton.setText("Add Track Pack:");
        addTrackPackPanel.add(addTrackPackButton);

        addTrackPackPanel.add(allTrackPackComboBox);

        removeLocomotiveButton.setText("Remove selected Locomotive");
        addTrackPackPanel.add(removeLocomotiveButton);

        removeRollingStockButton.setText("Remove selected Rolling Stock");
        addTrackPackPanel.add(removeRollingStockButton);

        removeTrackPackButton.setText("Remove selected Track Pack");
        addTrackPackPanel.add(removeTrackPackButton);

        errorAlertPanel.add(addTrackPackPanel);

        addTrackPackPanel.setVisible(false);

        jPanel4.add(errorAlertPanel);

        bottomButtonPanel.setPreferredSize(new java.awt.Dimension(1000, 50));

        submitButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        submitButton.setText("Submit");
        bottomButtonPanel.add(submitButton);

        clearButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
        clearButton.setText("Clear");
        bottomButtonPanel.add(clearButton);

        deleteButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        deleteButton.setText("Delete");
        bottomButtonPanel.add(deleteButton);

        jPanel4.add(bottomButtonPanel);

        add(jPanel4, java.awt.BorderLayout.CENTER);
        eraDccPanel.setVisible(false);
        trainSetPanel.setVisible(false);
        trackPackPanel.setVisible(false);
    }

    /**
     * Initialises the <code>CardLayout</code> used to swap between
     * relevant input fields for different product
     * types.
     */
    private void initialiseCardLayout() {
        cardLayout = new CardLayout();
        cardPanel = new javax.swing.JPanel(cardLayout);
        emptyPanel = new javax.swing.JPanel();

        // Add the panels you want to switch between
        cardPanel.add(emptyPanel, EMPTY_PANEL);
        cardPanel.add(eraDccPanel, ERA_DCC_PANEL);
        cardPanel.add(trainSetPanel, TRAIN_SET_PANEL);
        cardPanel.add(trackPackPanel, TRACK_PACK_PANEL);
    }

    /**
     * Gets data for each combo box from the database and adds it to each combo box
     */
    private void populateComboBoxes() {
        try {
            ArrayList<String> manufacturers = DatabaseOperations.selectDistinctManufacturers();
            ArrayList<Product> controllers = DatabaseOperations.selectAllProductType(CONTROLLER);
            ArrayList<Product> locomotives = DatabaseOperations.selectAllProductType(LOCOMOTIVE);
            ArrayList<Product> rollingStockList = DatabaseOperations.selectAllProductType(ROLLING_STOCK);
            ArrayList<Product> trackPacks = DatabaseOperations.selectAllProductType(TRACK_PACK);
            ArrayList<Product> trackPieces = DatabaseOperations.selectAllProductType(TRACK_PIECE);

            for (String manufacturer : manufacturers) {
                manufacturerComboBox.addItem(manufacturer);
            }

            for (Product controller : controllers) {
                controllerComboBox.addItem(controller.getProductCode());
            }

            for (Product locomotive : locomotives) {
                allLocomotivesComboBox.addItem(locomotive.getProductCode());
            }

            for (Product rollingStock : rollingStockList) {
                allRollingStockComboBox.addItem("");
                allRollingStockComboBox.addItem(rollingStock.getProductCode());
            }

            for (Product trackPack : trackPacks) {
                allTrackPackComboBox.addItem(trackPack.getProductCode());
            }

            for (Product trackPiece : trackPieces) {
                allTrackPiecesComboBox.addItem(trackPiece.getProductCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows delete button to be shown/hidden as needed from parent staff page
     * panel
     *
     * @param visible
     */
    public void setEditMode(boolean editMode) {
        deleteButton.setVisible(editMode);
        productCodeTextField.setEditable(!editMode);
    }

    /**
     * Clears the current inputs in the input fields by
     */
    public void clearInputFields() {
        productCodeTextField.setText("");
        productNameTextField.setText("");
        manufacturerComboBox.setSelectedItem("");
        retailPriceTextField.setText("");
        stockComboBox.setSelectedItem(1);
        gaugeTextField.setText("");
        rollingStockComboBox.removeAllItems();
        locomotiveComboBox.removeAllItems();
        trackPackComboBox.removeAllItems();
        trackPiecesComboBox.removeAllItems();
    }

    /**
     * Loads details of the product to be edited into the relevant fields on the GUI
     *
     * @param product The product intended to be edited
     */
    public void loadProductDetails(String productCode) {
        try {
            Product product = DatabaseOperations.selectProductFromProductCode(productCode);
            productCodeTextField.setText(productCode);
            productNameTextField.setText(product.getProductName());
            manufacturerComboBox.setSelectedItem(product.getManufacturer());
            retailPriceTextField.setText(String.valueOf(product.getRetailPrice()));
            stockComboBox.setSelectedItem(product.getStock());
            gaugeTextField.setText(product.getGauge());
            productTypeComboBox.setSelectedItem(product.getProductType());

            if (product.getProductType().equals(TRAIN_SET)) {
                TrainSet trainSet = (TrainSet) product;
                controllerComboBox.setSelectedItem(trainSet.getController().getProductCode());

                for (RollingStock rollingStock : trainSet.getRollingStocks()) {
                    for (int i = 0; i < trainSet.getQuantities().get(rollingStock.getProductCode()); i++) {
                        rollingStockComboBox.addItem(rollingStock.getProductCode());
                    }
                }

                for (Locomotive locomotive : trainSet.getLocomotives()) {
                    for (int i = 0; i < trainSet.getQuantities().get(locomotive.getProductCode()); i++) {
                        locomotiveComboBox.addItem(locomotive.getProductCode());
                    }
                }

                for (TrackPack trackPack : trainSet.getTrackPacks()) {
                    for (int i = 0; i < trainSet.getQuantities().get(trackPack.getProductCode()); i++) {
                        trackPackComboBox.addItem(trackPack.getProductCode());
                    }
                }
            } else if (product.getProductType().equals(TRACK_PACK)) {
                TrackPack trackPack = (TrackPack) product;

                for (TrackPiece trackPiece : trackPack.getTrackPieces()) {
                    for (int i = 0; i < trackPack.getTrackPieceQuantities().get(trackPiece.getProductCode()); i++) {
                        trackPiecesComboBox.addItem(trackPiece.getProductCode());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retailPriceTextFieldKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
            errorLabel.setText("Error. Retail Price must be a numeric value.");
        }
    }

    /**
     * Performs the actions required when submit button is pressed
     */
    private void submitButtonActions() {
        // Gets inputted values from input fields
        String productCode = productCodeTextField.getText();
        String productName = productNameTextField.getText();
        String manufacturer = manufacturerComboBox.getSelectedItem().toString();
        BigDecimal retailPrice = BigDecimal.ZERO;
        int quantity = Integer.valueOf(stockComboBox.getSelectedItem().toString());
        try {
            retailPrice = BigDecimal.valueOf(Double.valueOf(retailPriceTextField.getText()));
        } catch (Exception exception) {
            exception.printStackTrace();
            errorLabel.setText("Error. Retail price must be a numerical double value.");
        }
        String gauge = gaugeTextField.getText();
        String productType = String.valueOf(productTypeComboBox.getSelectedItem());

        boolean valid = true;

        // checks for empty input fields or if retail price is not greater than zero
        if (productCode.isEmpty() || productName.isEmpty() || manufacturer.isEmpty() || gauge.isEmpty()) {
            valid = false;
            errorLabel.setText("Error. Fields cannot be left empty");
        } else if (retailPrice.compareTo(BigDecimal.valueOf(0)) != 1) {
            valid = false;
            errorLabel.setText("Error. Retail Price must be greater than zero");
        }

        // if input fields are valid, begins creating product objects with given inputs
        if (valid) {
            boolean success = false;

            if (productType.equals(LOCOMOTIVE)) {
                String eraCode = eraCodeTextField.getText();
                DccCode dccCode = (DccCode) dccCodeComboBox.getSelectedItem();

                Locomotive locomotive = new Locomotive(productCode, productName, manufacturer, retailPrice,
                        productType, gauge, eraCode, dccCode, quantity);

                try {
                    success = DatabaseOperations.insertUpdateProduct(locomotive);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            } else if (productType.equals(ROLLING_STOCK)) {
                String eraCode = eraCodeTextField.getText();

                RollingStock rollingStock = new RollingStock(productCode, productName, manufacturer,
                        retailPrice, productType, gauge, eraCode, quantity);

                try {
                    success = DatabaseOperations.insertUpdateProduct(rollingStock);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            } else if (productType.equals(CONTROLLER)) {
                boolean digital = (boolean) digitalComboBox.getSelectedItem();

                Controller controller = new Controller(productCode, productName, manufacturer, retailPrice,
                        productType, gauge, digital, quantity);

                try {
                    success = DatabaseOperations.insertUpdateProduct(controller);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            } else if (productType.equals(TRAIN_SET)) {
                String eraCode = eraCodeTextField2.getText();
                String controllerID = String.valueOf(controllerComboBox.getSelectedItem());
                ComboBoxModel<String> locomotiveModel = locomotiveComboBox.getModel();
                ArrayList<Locomotive> locomotives = new ArrayList<>();
                ComboBoxModel<String> rollingStockModel = rollingStockComboBox.getModel();
                ArrayList<RollingStock> rollingStocks = new ArrayList<>();
                ComboBoxModel<String> trackPackModel = trackPackComboBox.getModel();
                ArrayList<TrackPack> trackPacks = new ArrayList<>();
                Map<String, Integer> quantities = new HashMap<>();

                try {
                    Controller controller = (Controller) DatabaseOperations.selectProductFromProductCode(controllerID);

                    for (int i = 0; i < locomotiveModel.getSize(); i++) {
                        String locomotiveID = locomotiveModel.getElementAt(i);

                        if (quantities.containsKey(locomotiveID)) {
                            quantities.put(locomotiveID, quantities.get(locomotiveID) + 1);
                        } else {
                            locomotives.add((Locomotive) DatabaseOperations.selectProductFromProductCode(locomotiveID));
                            quantities.put(locomotiveID, 1);
                        }
                    }

                    for (int i = 0; i < rollingStockModel.getSize(); i++) {
                        String rollingStockID = rollingStockModel.getElementAt(i);
                        if (quantities.containsKey(rollingStockID)) {
                            quantities.put(rollingStockID, quantities.get(rollingStockID) + 1);
                        } else {
                            rollingStocks
                                    .add((RollingStock) DatabaseOperations
                                            .selectProductFromProductCode(rollingStockID));
                            quantities.put(rollingStockID, 1);
                        }
                    }

                    for (int i = 0; i < trackPackModel.getSize(); i++) {
                        String trackPackID = trackPackModel.getElementAt(i);
                        if (quantities.containsKey(trackPackID)) {
                            System.out.println("REACHES");
                            quantities.put(trackPackID, quantities.get(trackPackID) + 1);
                        } else {
                            trackPacks.add((TrackPack) DatabaseOperations.selectProductFromProductCode(trackPackID));
                            quantities.put(trackPackID, 1);
                        }
                    }

                    // check all products contained in the set are of the same gauge
                    ArrayList<Product> allProductsInSet = new ArrayList<>();
                    allProductsInSet.addAll(locomotives);
                    allProductsInSet.addAll(rollingStocks);
                    allProductsInSet.addAll(trackPacks);

                    for (Product product : allProductsInSet) {
                        if (!product.getGauge().equals(gauge)) {
                            valid = false;
                            errorLabel.setText("Error. Gauges of all products in a box set must be the same");
                        }
                        if (product instanceof Locomotive) {
                            Locomotive locomotive = (Locomotive) product;
                            if (controller.getDigital() && locomotive.getDccCode() == DccCode.ANALOGUE) {
                                valid = false;
                                errorLabel.setText("Error. Digital Controller cannot control analogue locomotives");
                            } else if (!controller.getDigital() && !(locomotive.getDccCode() == DccCode.ANALOGUE)) {
                                valid = false;
                                errorLabel.setText("Error. Analogue Controller cannot control digital locomotives");
                            }
                        }
                    }

                    if (valid) {
                        TrainSet trainSet = new TrainSet(productCode, productName, manufacturer, retailPrice,
                                productType, gauge, eraCode, controller, trackPacks, locomotives, rollingStocks,
                                quantities, quantity);

                        success = DatabaseOperations.insertUpdateProduct(trainSet);
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            } else if (productType.equals(TRACK_PACK)) {
                ComboBoxModel<String> trackPieceModel = trackPiecesComboBox.getModel();
                ArrayList<TrackPiece> trackPieces = new ArrayList<>();
                Map<String, Integer> quantities = new HashMap<>();

                try {
                    for (int i = 0; i < trackPieceModel.getSize(); i++) {
                        String trackPieceID = trackPieceModel.getElementAt(i);
                        if (quantities.containsKey(trackPieceID)) {
                            quantities.put(trackPieceID, quantities.get(trackPieceID) + 1);
                        } else {
                            trackPieces.add((TrackPiece) DatabaseOperations.selectProductFromProductCode(trackPieceID));
                            quantities.put(trackPieceID, 1);
                        }
                    }

                    for (TrackPiece trackPiece : trackPieces) {
                        if (!trackPiece.getGauge().equals(gauge)) {
                            valid = false;
                            errorLabel.setText("Error. Gauges of all products in a box set must be the same");
                        }
                    }

                    if (valid) {
                        TrackPack trackPack = new TrackPack(productCode, productName, manufacturer, retailPrice,
                                productType, gauge, trackPieces, quantities, quantity);

                        success = DatabaseOperations.insertUpdateProduct(trackPack);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (productType.equals(TRACK_PIECE)) {
                TrackPiece trackPiece = new TrackPiece(productCode, productName, manufacturer, retailPrice, productType,
                        gauge, quantity);

                try {
                    success = DatabaseOperations.insertUpdateProduct(trackPiece);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(null,
                        "Success, product has been added/updated in the database", "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
                clearInputFields();
                if (manager) {
                    managerPage.populateProductTable();
                    managerPage.openProductPanel();
                } else {
                    staffPage.populateProductTable();
                    staffPage.openProductPanel();
                }
            }
        }
    }

    /**
     * Adds action listeners to GUI components.
     */
    private void addActionListeners() {
        // changes shown input fields based on selected product type
        productTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productType = String.valueOf(productTypeComboBox.getSelectedItem());

                // Checks which type of product is selected and then displays relevant extra
                // input fields
                if (productType.equals(TRAIN_SET)) {
                    cardLayout.show(cardPanel, TRAIN_SET_PANEL);
                    eraCodeLabel2.setVisible(true);
                    eraCodeTextField2.setVisible(true);
                    addTrackPackPanel.setVisible(true);

                } else {
                    addTrackPackPanel.setVisible(false);
                    eraCodeLabel2.setVisible(false);
                    eraCodeTextField2.setVisible(false);
                }
                if (productType.equals(LOCOMOTIVE)) {
                    cardLayout.show(cardPanel, ERA_DCC_PANEL);
                    dccCodeLabel.setVisible(true);
                    dccCodeComboBox.setVisible(true);
                    eraCodeLabel.setVisible(true);
                    eraCodeTextField.setVisible(true);
                    digitalLabel.setVisible(false);
                    digitalComboBox.setVisible(false);
                } else if (productType.equals(ROLLING_STOCK)) {
                    cardLayout.show(cardPanel, ERA_DCC_PANEL);
                    eraCodeLabel.setVisible(true);
                    eraCodeTextField.setVisible(true);
                    dccCodeLabel.setVisible(false);
                    dccCodeComboBox.setVisible(false);
                    digitalLabel.setVisible(false);
                    digitalComboBox.setVisible(false);
                } else if (productType.equals(CONTROLLER)) {
                    cardLayout.show(cardPanel, ERA_DCC_PANEL);
                    digitalLabel.setVisible(true);
                    digitalComboBox.setVisible(true);
                    dccCodeLabel.setVisible(false);
                    dccCodeComboBox.setVisible(false);
                    eraCodeLabel.setVisible(false);
                    eraCodeTextField.setVisible(false);
                } else if (productType.equals(TRACK_PACK)) {
                    cardLayout.show(cardPanel, TRACK_PACK_PANEL);
                } else if (productType.equals(TRACK_PIECE)) {
                    cardLayout.show(cardPanel, EMPTY_PANEL);
                }
            }
        });

        // only allows numerical input for quantity box
        quantityComboBox.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });

        // only allows numerical input for stock box
        stockComboBox.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });

        // once submit button is pressed, checks input fields and then adds/updates in
        // the database
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButtonActions();
            }
        });

        // clears input fields when clear button is pressed
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
            }
        });

        // deletes product when delete button is pressed
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane confirmOptionPane = new JOptionPane();
                int result = JOptionPane.showConfirmDialog(confirmOptionPane,
                        "Are you sure you want to delete this product?",
                        "Confirm Deletion of Product", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION && !productCodeTextField.getText().equals("")) {
                    try {
                        boolean success = DatabaseOperations.deleteProduct(productCodeTextField.getText());

                        if (success) {
                            JOptionPane.showMessageDialog(null,
                                    "Success, product has been deleted from the database", "Success!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            clearInputFields();
                            if (manager) {
                                managerPage.populateProductTable();
                                managerPage.openProductPanel();
                            } else {
                                staffPage.populateProductTable();
                                staffPage.openProductPanel();
                            }
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                }
            }
        });

        // adds locomotive to list of locomotives to be added when button is pressed
        addLocomotiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                locomotiveComboBox.addItem(allLocomotivesComboBox.getSelectedItem().toString());
            }
        });

        // adds rolling stock to list of rolling stock to be added when button is
        // pressed
        addRollingStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollingStockComboBox.addItem(allRollingStockComboBox.getSelectedItem().toString());
            }
        });

        // adds track pack to list of track packs to be added when button is pressed
        addTrackPackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackPackComboBox.addItem(allTrackPackComboBox.getSelectedItem().toString());
            }
        });

        // adds quantity of track pieces when button is pressed
        addTrackPieceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < (int) quantityComboBox.getSelectedItem(); i++) {
                    trackPiecesComboBox.addItem(allTrackPiecesComboBox.getSelectedItem().toString());
                }
                quantityComboBox.setSelectedItem(1);
            }
        });

        // removes selected locomotive from combo box when button is pressed
        removeLocomotiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                locomotiveComboBox.removeItemAt(locomotiveComboBox.getSelectedIndex());
            }
        });

        // removes selected rolling stock from combo box when button is pressed
        removeRollingStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollingStockComboBox.removeItemAt(rollingStockComboBox.getSelectedIndex());
            }
        });

        // removes selected track pack from combo box when button is pressed
        removeTrackPackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackPackComboBox.removeItemAt(trackPackComboBox.getSelectedIndex());
            }
        });

        // removes selected track piece from combo box when button is pressed
        removeTrackPieceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trackPiecesComboBox.removeItemAt(trackPiecesComboBox.getSelectedIndex());
            }
        });
    }

    // Variables declaration - do not modify
    private StaffPage staffPage;
    private ManagerPage managerPage;
    private boolean manager;
    private javax.swing.JButton addLocomotiveButton;
    private javax.swing.JButton addRollingStockButton;
    private javax.swing.JPanel addTrackPackPanel;
    private javax.swing.JButton addTrackPieceButton;
    private javax.swing.JComboBox<String> allLocomotivesComboBox;
    private javax.swing.JComboBox<String> allRollingStockComboBox;
    private javax.swing.JComboBox<String> allTrackPackComboBox;
    private javax.swing.JComboBox<String> allTrackPiecesComboBox;
    private javax.swing.JPanel bottomButtonPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> controllerComboBox;
    private javax.swing.JLabel controllerLabel;
    private javax.swing.JPanel controllerPanel;
    private javax.swing.JLabel dccCodeLabel;
    private javax.swing.JComboBox<DccCode> dccCodeComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel digitalLabel;
    private javax.swing.JComboBox<Boolean> digitalComboBox;
    private javax.swing.JLabel eraCodeLabel;
    private javax.swing.JTextField eraCodeTextField;
    private javax.swing.JLabel eraCodeLabel2;
    private javax.swing.JTextField eraCodeTextField2;
    private javax.swing.JPanel eraDccPanel;
    private javax.swing.JPanel errorAlertPanel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel gaugeLabel;
    private javax.swing.JPanel gaugePanel;
    private javax.swing.JTextField gaugeTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox<String> locomotiveComboBox;
    private javax.swing.JPanel locomotivePanel;
    private javax.swing.JComboBox<String> manufacturerComboBox;
    private javax.swing.JLabel manufacturerLabel;
    private javax.swing.JPanel manufacturerPanel;
    private javax.swing.JLabel productCodeLabel;
    private javax.swing.JPanel productCodePanel;
    private javax.swing.JTextField productCodeTextField;
    private javax.swing.JLabel productNameLabel;
    private javax.swing.JPanel productNamePanel;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JComboBox<String> productTypeComboBox;
    private javax.swing.JLabel productTypeLabel;
    private javax.swing.JPanel productTypePanel;
    private javax.swing.JComboBox<Integer> quantityComboBox;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel retailPriceLabel;
    private javax.swing.JPanel retailPricePanel;
    private javax.swing.JTextField retailPriceTextField;
    private javax.swing.JButton removeLocomotiveButton;
    private javax.swing.JButton removeRollingStockButton;
    private javax.swing.JButton removeTrackPackButton;
    private javax.swing.JButton removeTrackPieceButton;
    private javax.swing.JComboBox<String> rollingStockComboBox;
    private javax.swing.JPanel rollingStockPanel;
    private javax.swing.JComboBox<Integer> stockComboBox;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton addTrackPackButton;
    private javax.swing.JComboBox<String> trackPackComboBox;
    private javax.swing.JLabel trackPackLabel;
    private javax.swing.JPanel trackPackPanel;
    private javax.swing.JLabel trackPieceLabel;
    private javax.swing.JComboBox<String> trackPiecesComboBox;
    private javax.swing.JPanel trainSetPanel;
    private CardLayout cardLayout;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel emptyPanel;
    private static final String EMPTY_PANEL = "emptyPanel";
    private static final String ERA_DCC_PANEL = "eraDccPanel";
    private static final String TRAIN_SET_PANEL = "trainSetPanel";
    private static final String TRACK_PACK_PANEL = "trackPackPanel";
    private static final String LOCOMOTIVE = "locomotive";
    private static final String ROLLING_STOCK = "rolling stock";
    private static final String CONTROLLER = "controller";
    private static final String TRAIN_SET = "train set";
    private static final String TRACK_PIECE = "track piece";
    private static final String TRACK_PACK = "track pack";
    // End of variables declaration
}
