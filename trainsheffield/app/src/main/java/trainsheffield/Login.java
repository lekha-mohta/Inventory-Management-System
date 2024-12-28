package trainsheffield;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.List;

public class Login extends JPanel {
  public static String[] currentUser = new String[1];;
  private int loginAttempts = 0;
  private JButton jcomp1;
  private JButton jcomp2;
  private JLabel jcomp3;
  private JTextField jcomp4;
  private JLabel jcomp5;
  private JLabel jcomp6;
  private JTextField jcomp7;
  private JLabel jcomp8;
  private List<Product> products;

  public Login() {
    //construct components
    jcomp1 = new JButton ("Login");
    jcomp2 = new JButton ("Sign Up");
    jcomp3 = new JLabel ("Email");
    jcomp4 = new JTextField ();
    jcomp5 = new JLabel ("Welcome to Trains Sheffield");
    jcomp6 = new JLabel ("Password");
    jcomp7 = new JPasswordField ();
    jcomp8 = new JLabel ("Incorrect Email or Password. Please try again!");

    //adjust size and set layout
    setPreferredSize (new Dimension (414, 287));
    setLayout (null);

    //add components
    add (jcomp1);
    add (jcomp2);
    add (jcomp3);
    add (jcomp4);
    add (jcomp5);
    add (jcomp6);
    add (jcomp7);

    //set component bounds (only needed by Absolute Positioning)
    jcomp1.setBounds (150, 165, 115, 35);
    jcomp2.setBounds (150, 220, 115, 40);
    jcomp3.setBounds (10, 65, 100, 25);
    jcomp4.setBounds (75, 65, 330, 25);
    jcomp5.setBounds (130, 20, 165, 20);
    jcomp6.setBounds (10, 110, 100, 25);
    jcomp7.setBounds (75, 110, 330, 25);
    jcomp8.setBounds (85, 135, 270, 25);

    this.addListener();
  }

public static void main (String[] args) {
  JFrame frame = new JFrame ("Trains Sheffield");
  frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().add (new Login());
  frame.pack();
  frame.setVisible (true);
}

//login user
public void loginUser() {
  if (loginAttempts >= 5) {
    JOptionPane.showMessageDialog(this, "Too many failed login attempts. Please try again later.");
    System.exit(0);
    return;
  }
  Connection connection = DatabaseManager.getConnection();
  String email = jcomp4.getText();
  String password = jcomp7.getText();
  String hashedPass = Users.getMD5(password);
  String pass = getPassword(email);
  //System.out.println(pass);
  if (hashedPass.equals(pass)) {
    String userid = Users.getUserid(connection, email);
    currentUser[0] = userid;
    loginAttempts = 0;
    SwingUtilities.getWindowAncestor(Login.this).dispose();
    JFrame frame = new JFrame ("ShopPage");
    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().add (new ShopPage(products));
    frame.pack();
    frame.setVisible (true);
    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Login.this);
    if (loginFrame != null) {
      loginFrame.dispose();
    }
  } else {
    loginAttempts++;
    add (jcomp8);
    revalidate();
    repaint();
  }
}

  //displays the signup page upon login
  public void addListener() {
    jcomp1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
     loginUser();
    }
  });

  jcomp2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    //System.out.println("Loading signup screen");
    Signup sign = new Signup();
    JFrame signupFrame = new JFrame("Signup");
    signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the Signup frame, not the main frame
    signupFrame.getContentPane().add(sign);
    signupFrame.pack();
    signupFrame.setVisible(true);
    JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor(Login.this);
    if (loginFrame != null) {
      loginFrame.dispose();
    }
    }
  });
  }

  public String getPassword(String email) {
  try {
    Connection connection = DatabaseManager.getConnection();
    String sql = "SELECT password FROM Users WHERE email = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, email);
    ResultSet results = statement.executeQuery();
  if (results.next()) {
    return results.getString("password");
  }
  } catch (SQLException e) {
    e.printStackTrace();
  }
  return null; // Return null if no password is found or an error occurs.
  }

}
