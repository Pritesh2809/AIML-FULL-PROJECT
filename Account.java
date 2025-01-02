import java.util.ArrayList;

public abstract class Account { // Declaring abstract class Account

    private final String id; // Declaring private final variable for account ID, ensuring it's immutable after initialization
    private String password; // Declaring private variable for account password, allowing it to be changed

    // Constructor for the Account class
    public Account(String id, String password) {
        this.id = id; // Initializing account ID, which should remain constant
        this.password = password; // Initializing account password
    }

    // Getter method for account ID
    public String getId() {
        return id; // Returning account ID
    }

    // Method for login
    public boolean login(String id, String password) {
        // Checking if the provided ID and password match the account's ID and password
        return this.id.equals(id) && this.password.equals(password);
    }

    // Method for changing password
    public void changePassword(String newPassword) {
        this.password = newPassword; // Updating the account password
        System.out.println("Password changed successfully."); // Printing password change success message
    }

    // Method to check if a customer ID exists in the list of customers
    public static boolean isCustomerIdExist(String customerId, ArrayList<Customer> customers) {
        // Loop through each customer in the list
        for (Customer customer : customers) {
            // Check if the customer ID matches
            if (customer.getId().equals(customerId)) {
                return true; // Return true if the ID exists
            }
        }
        return false; // Return false if the ID does not exist
    }

    // Method to find a customer by ID
    public static Customer findCustomerById(String customerId, ArrayList<Customer> customers) {
        // Loop through each customer in the list
        for (Customer customer : customers) {
            // Check if the customer ID matches
            if (customer.getId().equals(customerId)) {
                return customer; // Return the customer if the ID matches
            }
        }
        return null; // Return null if no customer matches the ID
    }

    // Method to find an admin by ID
    public static Admin findAdminById(String adminId, ArrayList<Admin> admins) {
        // Loop through each admin in the list
        for (Admin admin : admins) {
            // Check if the admin ID matches
            if (admin.getId().equals(adminId)) {
                return admin; // Return the admin if the ID matches
            }
        }
        return null; // Return null if no admin matches the ID
    }
}
