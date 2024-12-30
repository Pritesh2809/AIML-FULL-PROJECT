public abstract class Account { // Declaring abstract class Account

    private final String id; // Declaring private final variable for account ID
    private String password; // Declaring private variable for account password

    public Account(String id, String password) { // Constructor for Account class
        this.id = id; // Initializing account ID
        this.password = password; // Initializing account password
    }

    public String getId() { // Getter method for account ID
        return id; // Returning account ID
    }

    public boolean login(String id, String password) { // Method for login
        return this.id.equals(id) && this.password.equals(password); // Checking if ID and password match
    }

    public void changePassword(String newPassword) { // Method for changing password
        this.password = newPassword; // Updating password
        System.out.println("Password changed successfully."); // Printing password change success message
    }
}
