import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static ArrayList<Customer> customers = new ArrayList<>(); // Array list for the Customer
    private static ArrayList<Admin> admins = new ArrayList<>(); // Array list for the Admins
    private static double atmBalance = 0.0; // for ATM balance

    static { // Static initializer block to add a default admin
        admins.add(new Admin("", "")); // Adding default admin
    }

    public static void mainMenu() { // Main menu method
        Scanner scanner = new Scanner(System.in);

        while (true) { // Loop for main menu
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            int choice = scanner.nextInt(); // Input for operation
            scanner.nextLine(); // Consume next line

            switch (choice) { // Switch for choosing the main menu action
                case 1:
                    Customer customer = CustomerAction.customerLogin(scanner, customers); // Method calling
                    if (customer != null) { // Checking if customer is valid
                        customerMenu(scanner, customer); // Customer main menu execution
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 2:
                    Admin admin = AdminAction.adminLogin(scanner, admins); // Calling adminLogin method
                    if (admin != null) { // Checking if admin is valid
                        adminMenu(scanner, admin, customers); // Display admin menu
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

    public static void adminMenu(Scanner scanner, Admin admin, ArrayList<Customer> customers) { // Method to display the admin menu
        while (true) {
            System.out.println("1. Create a new customer account");
            System.out.println("2. Delete customer account");
            System.out.println("3. View all customer accounts");
            System.out.println("4. Deposit money to ATM");
            System.out.println("5. View ATM balance");
            System.out.println("6. View all transactions");
            System.out.println("7. View all notes");
            System.out.println("8. Exit to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) { // Switch for admin menu actions
                case 1:
                    String createResult = AdminAction.createCustomer(scanner, customers); // Calling createCustomer method
                    System.out.println(createResult);
                    break;
                case 2:
                    String deleteResult = AdminAction.deleteCustomer(scanner, customers); // Calling deleteCustomer method
                    System.out.println(deleteResult);
                    break;
                case 3:
                    ArrayList<String> customerDetails = AdminAction.viewAllCustomers(customers); // Calling viewAllCustomers method
                    for (String detail : customerDetails) { // Using enhanced for loop
                        System.out.println(detail);
                    }
                    break;
                case 4:
                    String depositResult = AdminAction.depositToATM(scanner, admin); // Calling depositToATM method
                    System.out.println(depositResult);
                    break;
                case 5:
                    String atmBalance = AdminAction.viewAtmBalance(); // Calling viewAtmBalance method
                    System.out.println(atmBalance);
                    break;
                case 6:
                    ArrayList<String> transactionsList = AdminAction.viewAllTransactions(customers, admins); // Calling viewAllTransactions method
                    for (String transaction : transactionsList) { // Using enhanced for loop
                        System.out.println(transaction);
                    }
                    break;
                case 7:
                    String notes = AdminAction.viewAllNotes(admin); // Calling viewAllNotes method
                    System.out.println(notes);
                    break;
                case 8:
                    return; // Exit to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void customerMenu(Scanner scanner, Customer customer) { // Method to display the customer menu
        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Deposit cash");
            System.out.println("3. Withdraw cash");
            System.out.println("4. Change password");
            System.out.println("5. View transaction history");
            System.out.println("6. Exit to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) { // Loop for customer menu
                case 1:
                    customer.checkBalance(); // Check balance
                    break;
                case 2:
                    Admin admin = AdminAction.adminLogin(scanner, admins); // Prompting for admin login
                    if (admin != null) {
                        CustomerAction.depositCash(scanner, customer, admin); // Method for deposit cash
                    }
                    break;
                case 3:
                    Admin adminForWithdrawal = AdminAction.adminLogin(scanner, admins); // Prompting for admin login
                    if (adminForWithdrawal != null) {
                        CustomerAction.withdrawCash(scanner, customer, adminForWithdrawal); // Method for withdraw cash
                    }
                    break;
                case 4:
                    CustomerAction.changeCustomerPassword(scanner, customer); // Method for change password
                    break;
                case 5:
                    CustomerAction.viewTransactions(customer); // Method for view transactions
                    break;
                case 6:
                    return; // Exit to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static double getAtmBalance() { // Method for ATM balance
        return atmBalance;
    }

    public static void depositToAtm(double amount) { // Method to deposit money to the ATM
        atmBalance += amount;
    }

    public static void withdrawFromAtm(double amount) { // Method to withdraw money from the ATM
        if (atmBalance >= amount) {
            atmBalance -= amount;
        } else {
            System.out.println("Insufficient ATM balance.");
        }
    }
}
