import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction { // Handling admin actions

    // Admin login
    public static Admin adminLogin(Scanner scanner, ArrayList<Admin> admins) {
        System.out.print("Enter admin ID: ");
        String adminId = scanner.nextLine();

        Admin admin = findAccountById(adminId, admins);
        if (admin == null) {
            System.out.println("Admin ID not found. Returning to main menu.");
            return null;
        }

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print("Enter password (Attempts remaining: " + attempts + "): ");
            String password = scanner.nextLine();

            if (admin.login(adminId, password)) {
                return admin;
            } else {
                System.out.println("Invalid password.");
            }
        }

        System.out.println("Too many failed attempts. Returning to main menu.");
        return null;
    }

    // Create a new customer
    public static String createCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.print("Enter new customer ID: ");
        String newCustomerId = scanner.nextLine();
        if (isCustomerIdExist(newCustomerId, customers)) {
            return "Customer ID already exists. Returning to main menu.";
        }
        System.out.print("Enter new customer password: ");
        String newCustomerPassword = scanner.nextLine();
        customers.add(new Customer(newCustomerId, newCustomerPassword));
        return "Customer account created successfully.";
    }

    // Delete a customer
    public static String deleteCustomer(Scanner scanner, ArrayList<Customer> customers) {
        System.out.print("Enter customer ID to delete: ");
        String deleteCustomerId = scanner.nextLine();
        Customer customerToDelete = findCustomerById(deleteCustomerId, customers);
        if (customerToDelete == null) {
            return "Customer account not found.";
        } else {
            customers.remove(customerToDelete);
            return "Customer account deleted successfully.";
        }
    }

    // View all customers
    public static ArrayList<String> viewAllCustomers(ArrayList<Customer> customers) {
        ArrayList<String> customerDetails = new ArrayList<>();
        for (Customer customer : customers) {
            customerDetails.add("Customer ID: " + customer.getId() + ", Balance: " + customer.getBalance());
        }
        return customerDetails;
    }

    // Deposit money to the ATM
    public static String depositToATM(Scanner scanner, Admin admin) {
        System.out.print("Enter the total amount to deposit: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();

        if (totalAmount <= 0 || totalAmount % 100 != 0) {
            return "Invalid amount. Please enter a positive amount in multiples of 100.";
        }

        System.out.print("Enter number of 2000 Rs notes: ");
        int notes2000 = scanner.nextInt();
        System.out.print("Enter number of 500 Rs notes: ");
        int notes500 = scanner.nextInt();
        System.out.print("Enter number of 200 Rs notes: ");
        int notes200 = scanner.nextInt();
        System.out.print("Enter number of 100 Rs notes: ");
        int notes100 = scanner.nextInt();
        scanner.nextLine();

        NotesAction.addNotes(admin.getNotes(), notes2000, notes500, notes200, notes100);
        ATM.depositToAtm(totalAmount);
        admin.addTransaction(new Transaction("Admin Deposit", totalAmount, ATM.getAtmBalance()));
        return "Deposit successful. New ATM balance: " + ATM.getAtmBalance();
    }

    // View ATM balance
    public static String viewAtmBalance() {
        return "ATM balance is: " + ATM.getAtmBalance();
    }

    // View all transactions
    public static ArrayList<String> viewAllTransactions(ArrayList<Customer> customers, ArrayList<Admin> admins) {
        ArrayList<String> transactionsList = new ArrayList<>();
        for (Customer customer : customers) {
            transactionsList.add("Customer ID: " + customer.getId());
            for (Transaction transaction : customer.getTransactions()) {
                transactionsList.add(transaction.toString());
            }
        }
        for (Admin admin : admins) {
            transactionsList.add("Admin ID: " + admin.getId());
            for (Transaction transaction : admin.getTransactions()) {
                transactionsList.add(transaction.toString());
            }
        }
        return transactionsList;
    }

    // View all notes in the ATM
    public static String viewAllNotes(Admin admin) {
        return "Notes: \n" + NotesAction.notesToString(admin.getNotes());
    }

    // Helper methods
    private static boolean isCustomerIdExist(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    private static Customer findCustomerById(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private static Admin findAccountById(String adminId, ArrayList<Admin> admins) {
        for (Admin admin : admins) {
            if (admin.getId().equals(adminId)) {
                return admin;
            }
        }
        return null;
    }
}