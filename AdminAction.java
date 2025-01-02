import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction { // AdminAction class for admin operations

    // Method for admin login
    public static Admin adminLogin(Scanner scanner, ArrayList<Admin> admins) {
        System.out.println("Enter Admin ID: ");
        String id = scanner.nextLine(); // Get admin ID from input
        System.out.println("Enter Password: ");
        String password = scanner.nextLine(); // Get admin password from input

        // Loop through the list of admins to verify login credentials
        for (Admin admin : admins) {
            if (admin.login(id, password)) { // Check if admin ID and password match
                System.out.println("Login successful."); // Print success message
                return admin; // Return the logged-in admin
            }
        }

        System.out.println("Invalid Admin ID or Password."); // Print error message if credentials are incorrect
        return null; // Return null if login fails
    }

    // Method to create a new customer account
    public static String createCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.println("Enter Customer ID: ");
        String customerId = scanner.nextLine(); // Get customer ID from input
        System.out.println("Enter Password: ");
        String password = scanner.nextLine(); // Get customer password from input

        // Check if customer ID already exists
        if (Account.isCustomerIdExist(customerId, customers)) {
            return "Customer ID already exists."; // Return error message if ID exists
        }

        customers.add(new Customer(customerId, password)); // Add new customer to the list
        return "Customer account created successfully."; // Return success message
    }

    // Method to delete a customer account
    public static String deleteCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.println("Enter Customer ID to delete: ");
        String customerId = scanner.nextLine(); // Get customer ID from input

        // Find the customer by ID
        Customer customer = Account.findCustomerById(customerId, customers);
        if (customer == null) {
            return "Customer ID not found."; // Return error message if customer ID does not exist
        }

        customers.remove(customer); // Remove the customer from the list
        return "Customer account deleted successfully."; // Return success message
    }

    // Method to view all customer accounts
    public static ArrayList<String> viewAllCustomers(ArrayList<Customer> customers) {
        ArrayList<String> customerDetails = new ArrayList<>(); // List to store customer details
        // Loop through each customer in the list
        for (Customer customer : customers) {
            customerDetails.add("Customer ID: " + customer.getId() + ", Balance: " + customer.getBalance()); // Add customer details to the list
        }
        return customerDetails; // Return the list of customer details
    }

    // Method to deposit money to the ATM
    public static String depositToATM(Scanner scanner, Admin admin) {
        System.out.println("Enter number of 2000 Rs notes: ");
        int num2000 = Integer.parseInt(scanner.nextLine()); // Get number of 2000 Rs notes from input
        System.out.println("Enter number of 500 Rs notes: ");
        int num500 = Integer.parseInt(scanner.nextLine()); // Get number of 500 Rs notes from input
        System.out.println("Enter number of 200 Rs notes: ");
        int num200 = Integer.parseInt(scanner.nextLine()); // Get number of 200 Rs notes from input
        System.out.println("Enter number of 100 Rs notes: ");
        int num100 = Integer.parseInt(scanner.nextLine()); // Get number of 100 Rs notes from input

        // Add notes to the ATM's notes object
        NotesAction.addNotes(admin.getNotes(), num2000, num500, num200, num100);
        double totalAmount = num2000 * 2000 + num500 * 500 + num200 * 200 + num100 * 100; // Calculate total amount deposited

        ATM.depositToAtm(totalAmount); // Deposit the total amount to the ATM balance
        admin.addTransaction(new Transaction("Deposit to ATM", totalAmount, ATM.getAtmBalance())); // Add transaction to admin's transaction list

        return "Money deposited to ATM successfully."; // Return success message
    }

    // Method to view the ATM balance
    public static String viewAtmBalance() {
        return "ATM Balance: " + ATM.getAtmBalance(); // Return the ATM balance
    }

    // Method to view all transactions
    public static ArrayList<String> viewAllTransactions(ArrayList<Customer> customers, ArrayList<Admin> admins) {
        ArrayList<String> transactionsList = new ArrayList<>(); // List to store all transactions

        // Loop through each customer and add their transactions to the list
        for (Customer customer : customers) {
            for (Transaction transaction : customer.getTransactions()) {
                transactionsList.add("Customer ID: " + customer.getId() + ", " + transaction.toString());
            }
        }

        // Loop through each admin and add their transactions to the list
        for (Admin admin : admins) {
            for (Transaction transaction : admin.getTransactions()) {
                transactionsList.add("Admin ID: " + admin.getId() + ", " + transaction.toString());
            }
        }

        return transactionsList; // Return the list of all transactions
    }

    // Method to view all notes in the ATM
    public static String viewAllNotes(Admin admin) {
        return NotesAction.notesToString(admin.getNotes()); // Return the string representation of all notes in the ATM
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
