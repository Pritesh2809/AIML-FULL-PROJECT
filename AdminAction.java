import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction { // Declaring AdminAction class

    // Method for admin login
    public static Admin adminLogin(Scanner scanner, ArrayList<Admin> admins) {
        System.out.print("Enter admin ID: "); // Prompting user to enter admin ID
        String adminId = scanner.nextLine(); // Reading admin ID

        Admin admin = findAccountById(adminId, admins); // Finding admin by ID
        if (admin.getId().equals("invalid")) { // Checking if admin ID is invalid
            System.out.println("Admin ID not found. Returning to main menu."); // Printing message if ID not found
            return new Admin("invalid", "invalid"); // Returning invalid admin
        }

        for (int attempts = 3; attempts > 0; attempts--) { // Loop for password attempts
            System.out.print("Enter password (Attempts remaining: " + attempts + "): "); // Prompting user to enter password
            String password = scanner.nextLine(); // Reading password

            if (admin.login(adminId, password)) { // Checking if login is successful
                return admin; // Returning admin if login is successful
            } else { // If login is unsuccessful
                System.out.println("Invalid password."); // Printing invalid password message
            }
        }

        System.out.println("Too many failed attempts. Returning to main menu."); // Printing message if too many failed attempts
        return new Admin("invalid", "invalid"); // Returning invalid admin if login fails
    }

    // Method for creating a new customer
    public static String createCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.print("Enter new customer ID: "); // Prompting user to enter new customer ID
        String newCustomerId = scanner.nextLine(); // Reading new customer ID
        if (isCustomerIdExist(newCustomerId, customers)) { // Checking if customer ID already exists
            return "Customer ID already exists. Returning to main menu."; // Returning message if ID already exists
        }
        System.out.print("Enter new customer password: "); // Prompting user to enter new customer password
        String newCustomerPassword = scanner.nextLine(); // Reading new customer password
        customers.add(new Customer(newCustomerId, newCustomerPassword)); // Adding new customer to the list
        return "Customer account created successfully."; // Returning success message
    }

    // Method for deleting a customer
    public static String deleteCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.print("Enter customer ID to delete: "); // Prompting user to enter customer ID to delete
        String deleteCustomerId = scanner.nextLine(); // Reading customer ID to delete
        Customer customerToDelete = findCustomerById(deleteCustomerId, customers); // Finding customer by ID
        if (customerToDelete.getId().equals("invalid")) { // Checking if customer ID is invalid
            return "Customer account not found."; // Returning message if ID not found
        } else { // If customer ID is found
            customers.remove(customerToDelete); // Removing customer from the list
            return "Customer account deleted successfully."; // Returning success message
        }
    }

    // Method for viewing all customers
    public static ArrayList<String> viewAllCustomers(ArrayList<Customer> customers) {
        ArrayList<String> customerDetails = new ArrayList<>(); // Creating list for customer details
        for (Customer customer : customers) { // Iterating through customers
            customerDetails.add("Customer ID: " + customer.getId() + ", Balance: " + customer.getBalance()); // Adding customer details to the list
        }
        return customerDetails; // Returning customer details list
    }

    // Method for depositing money to ATM
    public static String depositToATM(Scanner scanner, Admin admin) {
        System.out.print("Enter the total amount to deposit: "); // Prompting user to enter total amount to deposit
        double totalAmount = scanner.nextDouble(); // Reading total amount to deposit
        scanner.nextLine(); // Consuming newline character

        if (totalAmount <= 0 || totalAmount % 100 != 0) { // Checking if total amount is valid
            return "Invalid amount. Please enter a positive amount in multiples of 100."; // Returning invalid amount message
        }

        System.out.print("Enter number of 2000 Rs notes: "); // Prompting user to enter number of 2000 Rs notes
        int notes2000 = scanner.nextInt(); // Reading number of 2000 Rs notes
        System.out.print("Enter number of 500 Rs notes: "); // Prompting user to enter number of 500 Rs notes
        int notes500 = scanner.nextInt(); // Reading number of 500 Rs notes
        System.out.print("Enter number of 200 Rs notes: "); // Prompting user to enter number of 200 Rs notes
        int notes200 = scanner.nextInt(); // Reading number of 200 Rs notes
        System.out.print("Enter number of 100 Rs notes: "); // Prompting user to enter number of 100 Rs notes
        int notes100 = scanner.nextInt(); // Reading number of 100 Rs notes
        scanner.nextLine(); // Consuming newline character

        NotesAction.addNotes(admin.getNotes(), notes2000, notes500, notes200, notes100); // Adding notes to admin's record
        ATM.depositToAtm(totalAmount); // Updating ATM balance
        admin.addTransaction(new Transaction("Admin Deposit", totalAmount, ATM.getAtmBalance())); // Adding deposit transaction to admin
        return "Deposit successful. New ATM balance: " + ATM.getAtmBalance(); // Returning success message with new ATM balance
    }

    // Method for viewing ATM balance
    public static String viewAtmBalance() {
        return "ATM balance is: " + ATM.getAtmBalance(); // Returning ATM balance
    }

    // Method for viewing all transactions
    public static ArrayList<String> viewAllTransactions(ArrayList<Customer> customers, ArrayList<Admin> admins) {
        ArrayList<String> transactionsList = new ArrayList<>(); // Creating list for transactions
        for (Customer customer : customers) { // Iterating through customers
            transactionsList.add("Customer ID: " + customer.getId()); // Adding customer ID to the list
            for (Transaction transaction : customer.getTransactions()) { // Iterating through transactions
                transactionsList.add(transaction.toString()); // Adding transaction to the list
            }
        }
        for (Admin admin : admins) { // Iterating through admins
            transactionsList.add("Admin ID: " + admin.getId()); // Adding admin ID to the list
            for (Transaction transaction : admin.getTransactions()) { // Iterating through transactions
                transactionsList.add(transaction.toString()); // Adding transaction to the list
            }
        }
        return transactionsList; // Returning transactions list
    }

    // Method for viewing all notes
    public static String viewAllNotes(Admin admin) {
        return "Notes: \n" + NotesAction.notesToString(admin.getNotes()); // Returning notes
    }

    // Method for checking if customer ID exists
    private static boolean isCustomerIdExist(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) { // Iterating through customers
            if (customer.getId().equals(customerId)) { // Checking if customer ID matches
                return true; // Returning true if ID exists
            }
        }
        return false; // Returning false if ID does not exist
    }

    // Method for finding customer by ID
    private static Customer findCustomerById(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) { // Iterating through customers
            if (customer.getId().equals(customerId)) { // Checking if customer ID matches
                return customer; // Returning customer
            }
        }
        return new Customer("invalid", "invalid"); // Returning invalid customer if ID not found
    }

    // Method for finding admin by ID
    private static Admin findAccountById(String adminId, ArrayList<Admin> admins) {
        for (Admin admin : admins) { // Iterating through admins
            if (admin.getId().equals(adminId)) { // Checking if admin ID matches
                return admin; // Returning admin
            }
        }
        return new Admin("invalid", "invalid"); // Returning invalid admin if ID not found
    }
}
