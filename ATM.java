import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static ArrayList<Customer> customers = new ArrayList<>(); // A static list to store customer information, shared by all instances because there's a single set of customers for the ATM system
    private static ArrayList<Admin> admins; // A static list to store admin information, shared by all instances because there's a single set of admins managing the ATM system
    private static double atmBalance = 0.0; // A static variable to keep track of the ATM's balance, shared by all instances to ensure a single, consistent balance across the system

    // Constructor for the ATM class
    public ATM() {
        admins = new ArrayList<>(); // Initialize the admins list, which is shared by all instances
        // Add a default admin to the list
        admins.add(new Admin("", "")); // Add a default admin with ID "admin" and password "admin123"
    }

    // Method to display the main menu and handle user input for navigating the ATM
    // This method is static because it needs to be called without creating an instance of the ATM class, allowing users to access the menu directly
    public static void mainMenu() {
        new ATM(); // Ensure the constructor is called to initialize the admins

        Scanner scanner = new Scanner(System.in);

        while (true) { // Infinite loop to continuously display the main menu until the user decides to exit
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine()); // Get the user's choice

            switch (choice) { // Perform actions based on the user's choice
                case 1:
                    Customer customer = CustomerAction.customerLogin(scanner, customers); // Allow the customer to log in
                    if (customer != null) { // If the customer is valid, proceed to the customer menu
                        customerMenu(scanner, customer); // Show the customer menu
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 2:
                    Admin admin = AdminAction.adminLogin(scanner, admins); // Allow the admin to log in
                    if (admin != null) { // If the admin is valid, proceed to the admin menu
                        adminMenu(scanner, admin, customers); // Show the admin menu
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!"); // Exit message
                    return; // Exit the main menu
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid choices
            }
        }
    }

    // Method to display the admin menu and handle admin operations
    // This method is static because it does not depend on instance-specific data and can be shared across all instances, ensuring consistency in admin actions
    public static void adminMenu(Scanner scanner, Admin admin, ArrayList<Customer> customers) {
        while (true) { // Infinite loop to continuously display the admin menu until the admin decides to log out
            System.out.println("1. Create a new customer account");
            System.out.println("2. Delete customer account");
            System.out.println("3. View all customer accounts");
            System.out.println("4. Deposit money to ATM");
            System.out.println("5. View ATM balance");
            System.out.println("6. View all transactions");
            System.out.println("7. View all notes");
            System.out.println("8. Logout");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) { // Perform actions based on the admin's choice
                case 1:
                    String createResult = AdminAction.createCustomer(scanner, customers); // Create a new customer account
                    System.out.println(createResult);
                    break;
                case 2:
                    String deleteResult = AdminAction.deleteCustomer(scanner, customers); // Delete a customer account
                    System.out.println(deleteResult);
                    break;
                case 3:
                    ArrayList<String> customerDetails = AdminAction.viewAllCustomers(customers); // View all customer accounts
                    for (String detail : customerDetails) { // Loop through each customer's details and print them
                        System.out.println(detail);
                    }
                    break;
                case 4:
                    String depositResult = AdminAction.depositToATM(scanner, admin); // Deposit money to the ATM
                    System.out.println(depositResult);
                    break;
                case 5:
                    String atmBalance = AdminAction.viewAtmBalance(); // View the ATM balance
                    System.out.println(atmBalance);
                    break;
                case 6:
                    ArrayList<String> transactionsList = AdminAction.viewAllTransactions(customers, admins); // View all transactions
                    for (String transaction : transactionsList) { // Loop through each transaction and print it
                        System.out.println(transaction);
                    }
                    break;
                case 7:
                    String notes = AdminAction.viewAllNotes(admin); // View all notes in the ATM
                    System.out.println(notes);
                    break;
                case 8:
                    return; // Logout and return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid choices
            }
        }
    }

    // Method to display the customer menu and handle customer operations
    // This method is static because it does not depend on instance-specific data and can be shared across all instances, ensuring consistent customer actions
    public static void customerMenu(Scanner scanner, Customer customer) {
        while (true) { // Infinite loop to continuously display the customer menu until the customer decides to log out
            System.out.println("1. Check balance");
            System.out.println("2. Deposit cash");
            System.out.println("3. Withdraw cash");
            System.out.println("4. Change password");
            System.out.println("5. View transaction history");
            System.out.println("6. Logout");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) { // Perform actions based on the customer's choice
                case 1:
                    customer.checkBalance(); // Check the customer's balance
                    break;
                case 2:
                    CustomerAction.depositCash(scanner, customer, admins.get(0)); // Deposit cash without admin login
                    break;
                case 3:
                    CustomerAction.withdrawCash(scanner, customer, admins.get(0)); // Withdraw cash without admin login
                    break;
                case 4:
                    CustomerAction.changeCustomerPassword(scanner, customer); // Change the customer's password
                    break;
                case 5:
                    CustomerAction.viewTransactions(customer); // View the customer's transaction history
                    break;
                case 6:
                    return; // Logout and return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again."); // Handle invalid choices
            }
        }
    }

    // Method to get the current ATM balance
    // This method is static because it accesses the static atmBalance variable, ensuring the balance is consistent across the system
    public static double getAtmBalance() {
        return atmBalance;
    }

    // Method to deposit money into the ATM
    // This method is static because it modifies the static atmBalance variable, ensuring all deposits affect the shared ATM balance
    public static void depositToAtm(double amount) {
        atmBalance += amount; // Increase the ATM balance by the specified amount
    }

    // Method to withdraw money from the ATM
    // This method is static because it modifies the static atmBalance variable, ensuring all withdrawals affect the shared ATM balance
    public static void withdrawFromAtm(double amount) {
        if (atmBalance >= amount) { // Check if the ATM has sufficient balance
            atmBalance -= amount; // Decrease the ATM balance by the specified amount
        } else {
            System.out.println("Insufficient ATM balance."); // Print message if ATM balance is insufficient
        }
    }
}
