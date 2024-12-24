import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Admin admin = new Admin("", "");

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        AdminAction adminAction = new AdminAction(admin, customers);
        CustomerAction customerAction = new CustomerAction(customers, admin);

        while (true) {
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customerLogin(scanner, customerAction);
                    break;
                case 2:
                    adminLogin(scanner, adminAction);
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void customerLogin(Scanner scanner, CustomerAction customerAction) {
        if (customers.isEmpty()) {
            System.out.println("No account found. Please contact admin to create an account.");
            return;
        }

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer != null && customer.login(customerId, password)) {
            customerAction.customerMenu(scanner, customer);
        } else {
            System.out.println("Invalid credentials. Returning to main menu.");
        }
    }

    private void adminLogin(Scanner scanner, AdminAction adminAction) {
        System.out.print("Enter admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.login(adminId, password)) {
            while (true) {
                System.out.println("1. Create a new customer account");
                System.out.println("2. Delete customer account");
                System.out.println("3. View all customer accounts");
                System.out.println("4. Deposit money to ATM");
                System.out.println("5. View ATM balance");
                System.out.println("6. View all transactions");
                System.out.println("7. Exit to main menu");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline

                switch (choice) {
                    case 1:
                        adminAction.createCustomer(scanner);
                        break;
                    case 2:
                        adminAction.deleteCustomer(scanner);
                        break;
                    case 3:
                        adminAction.viewAllCustomers();
                        break;
                    case 4:
                        adminAction.depositToATM(scanner);
                        break;
                    case 5:
                        admin.viewAtmBalance();
                        break;
                    case 6:
                        adminAction.viewAllTransactions();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid admin credentials. Returning to main menu.");
        }
    }

    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}
