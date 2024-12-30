import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static ArrayList<Customer> customers = new ArrayList<>(); // List to hold customer information
    private static ArrayList<Admin> admins = new ArrayList<>(); // List to hold admin information
    private static double atmBalance = 0.0; // To keep track of the ATM's balance

    static { // Adding a default admin when the program starts
        admins.add(new Admin("", "")); // Adding default admin
    }

    public static void mainMenu() { // Displaying the main menu
        Scanner scanner = new Scanner(System.in);

        while (true) { // Keep showing the main menu until the user decides to exit
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            int choice = scanner.nextInt(); // Getting the user's choice
            scanner.nextLine(); // Consume newline character

            switch (choice) { // Perform actions based on the user's choice
                case 1:
                    Customer customer = CustomerAction.customerLogin(scanner, customers); // Let the customer log in
                    if (customer != null) { // If the customer is valid, proceed to customer menu
                        customerMenu(scanner, customer); // Show the customer menu
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 2:
                    Admin admin = AdminAction.adminLogin(scanner, admins); // Let the admin log in
                    if (admin != null) { // If the admin is valid, proceed to admin menu
                        adminMenu(scanner, admin, customers); // Show the admin menu
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return; // Exit the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void adminMenu(Scanner scanner, Admin admin, ArrayList<Customer> customers) { // Displaying the admin menu
        while (true) {
            System.out.println("1. Create a new customer account");
            System.out.println("2. Delete customer account");
            System.out.println("3. View all customer accounts");
            System.out.println("4. Deposit money to ATM");
            System.out.println("5. View ATM balance");
            System.out.println("6. View all transactions");
            System.out.println("7. View all notes");
            System.out.println("8. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

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
                    for (String detail : customerDetails) { // Print each customer detail
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
                    for (String transaction : transactionsList) { // Print each transaction
                        System.out.println(transaction);
                    }
                    break;
                case 7:
                    String notes = AdminAction.viewAllNotes(admin); // View all notes in the ATM
                    System.out.println(notes);
                    break;
                case 8:
                    return; // Logout and go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void customerMenu(Scanner scanner, Customer customer) { // Displaying the customer menu
        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Deposit cash");
            System.out.println("3. Withdraw cash");
            System.out.println("4. Change password");
            System.out.println("5. View transaction history");
            System.out.println("6. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

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
                    return; // Logout and go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static double getAtmBalance() { // Get the ATM balance
        return atmBalance;
    }

    public static void depositToAtm(double amount) { // Deposit money to the ATM
        atmBalance += amount;
    }

    public static void withdrawFromAtm(double amount) { // Withdraw money from the ATM
        if (atmBalance >= amount) {
            atmBalance -= amount;
        } else {
            System.out.println("Insufficient ATM balance.");
        }
    }
}