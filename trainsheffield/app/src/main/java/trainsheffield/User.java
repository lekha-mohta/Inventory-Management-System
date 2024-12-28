package trainsheffield;

import trainsheffield.Users.Roles;

public class User {
    private String userID;
    private String name;
    private String email;
    private Roles role;

    // Get methods

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Roles getRole() {
        return role;
    }

    // Set methods

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    // Constructor

    /**
     * Constructs a new User with the specified details.
     *
     * @param userID
     * @param name
     * @param email
     * @param role
     */
    public User(String userID, String name, String email, Roles role) {

        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
    }

}
