package trainsheffield;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Signup extends JPanel {
    private JButton jcomp1;
    private JLabel jcomp2;
    private JTextField jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;
    private JLabel jcomp7;
    private JTextField jcomp8;
    private JLabel jcomp9;
    private JTextField jcomp10;
    private JTextField jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JLabel jcomp14;
    private JLabel jcomp15;
    private JTextField jcomp16;
    private JTextField jcomp17;
    private JTextField jcomp18;
    private JLabel jcomp19;

    public Signup() {
        //construct components
        jcomp1 = new JButton ("Sign Up");
        jcomp2 = new JLabel ("First Name");
        jcomp3 = new JTextField ();
        jcomp4 = new JLabel ("Welcome to Trains Sheffield");
        jcomp5 = new JLabel ("Last Name");
        jcomp6 = new JTextField ();
        jcomp7 = new JLabel ("Email");
        jcomp8 = new JTextField ();
        jcomp9 = new JLabel ("Password");
        jcomp10 = new JTextField ();
        jcomp11 = new JTextField ();
        jcomp12 = new JLabel ("House Number");
        jcomp13 = new JLabel ("Road Name");
        jcomp14 = new JLabel ("City");
        jcomp15 = new JLabel ("Post Code");
        jcomp16 = new JTextField ();
        jcomp17 = new JTextField ();
        jcomp18 = new JTextField ();
        jcomp19 = new JLabel("A User with this email already exists.");

        //adjust size and set layout
        setPreferredSize (new Dimension (718, 324));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
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

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (585, 260, 115, 40);
        jcomp2.setBounds (10, 65, 100, 25);
        jcomp3.setBounds (85, 65, 210, 25);
        jcomp4.setBounds (275, 10, 165, 20);
        jcomp5.setBounds (10, 110, 100, 25);
        jcomp6.setBounds (85, 110, 210, 25);
        jcomp7.setBounds (10, 155, 100, 25);
        jcomp8.setBounds (85, 155, 210, 25);
        jcomp9.setBounds (10, 200, 100, 25);
        jcomp10.setBounds (85, 200, 210, 25);
        jcomp11.setBounds (495, 65, 210, 25);
        jcomp12.setBounds (395, 65, 100, 25);
        jcomp13.setBounds (395, 110, 100, 25);
        jcomp14.setBounds (395, 155, 100, 25);
        jcomp15.setBounds (395, 200, 100, 25);
        jcomp16.setBounds (495, 110, 210, 25);
        jcomp17.setBounds (495, 155, 210, 25);
        jcomp18.setBounds (495, 200, 210, 25);
        jcomp19.setBounds (10, 265, 250, 25);
        
        this.addListener();
    }

    

    public void signupUser() {
        String firstName = jcomp3.getText();
        String surname = jcomp6.getText();
        String email = jcomp8.getText();
        String password = Users.getMD5(jcomp10.getText());
        String userId =  Users.generateUserid(DatabaseManager.getConnection());
        Integer role = 0;
        String houseNumber = jcomp11.getText();
        String roadName = jcomp16.getText();
        String city = jcomp17.getText();
        String postcode = jcomp18.getText();
        if (Users.checkUserExists(DatabaseManager.getConnection(), email)) {
            add(jcomp19);
            revalidate();
            repaint();
            JOptionPane.showMessageDialog(this, "A user with this email already exists!");
        } else {
            try (Connection connection = DatabaseManager.getConnection()) {
                String sql = "INSERT INTO Users (user_id, first_name, last_name, email, user_role, password) VALUES (?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, userId);
                statement.setString(2, firstName);
                statement.setString(3, surname);
                statement.setString(4, email);
                statement.setInt(5, role);
                statement.setString(6, password);
                statement.executeUpdate(); 
                //System.out.println("Saved user to db");
                String sql1 = "INSERT INTO Address (house_number, road_name, city, postcode, user_address) VALUES (?,?,?,?,?)";
                PreparedStatement statement1 = connection.prepareStatement(sql1);
                statement1.setString(1, houseNumber);
                statement1.setString(2, roadName);
                statement1.setString(3, city);
                statement1.setString(4, postcode);
                statement1.setString(5, userId);
                statement1.executeUpdate(); 
                //System.out.println("Saved address to db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    
    }

    public void addListener() {
        jcomp1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupUser();
                JFrame frame = new JFrame ("Trains Sheffield");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add (new Login());
                frame.pack();
                frame.setVisible (true);
                JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Signup.this);
                if (loginFrame != null) {
                loginFrame.dispose();
                }
            }
        });
    }













}



