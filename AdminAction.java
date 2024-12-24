import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction {
    private Admin admin;
    private ArrayList<Customer> customers;

    public AdminAction(Admin admin, ArrayList<Customer> customers) {
        this.admin = admin;
        this.customers = customers;
    }

    public void createCustomer(Scanner scanner) {
        System.out.print("Enter new customer ID: ");
        String newCustomerId = scanner.nextLine();
        if (isCustomerIdExist(newCustomerId)) {
            System.out.println("Customer ID already exists. Returning to main menu.");
            return;
        }
        System.out.print("Enter new customer password: ");
        String newCustomerPassword = scanner.nextLine();
        customers.add(new Customer(newCustomerId, newCustomerPassword, 0.0));
        System.out.println("Customer account created successfully.");
    }

    public void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID to delete: ");
        String deleteCustomerId = scanner.nextLine();
        Customer customerToDelete = findCustomerById(deleteCustomerId);
        if (customerToDelete != null) {
            customers.remove(customerToDelete);
            System.out.println("Customer account deleted successfully.");
        } else {
            System.out.println("Customer account not found.");
        }
    }

    public void viewAllCustomers() {
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getId() + ", Balance: " + customer.getBalance());
        }
    }

    public void depositToATM(Scanner scanner) {
        System.out.print("Enter the total amount to deposit: ");
        String totalAmountStr = scanner.nextLine();
        double totalAmount = Double.parseDouble(totalAmountStr);

        if (totalAmount <= 0 || totalAmount % 100 != 0) {
            System.out.println("Invalid amount. Please enter a positive amount in multiples of 100.");
            return;
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

        double calculatedAmount = (notes2000 * 2000) + (notes500 * 500) + (notes200 * 200) + (notes100 * 100);

        if (calculatedAmount != totalAmount) {
            System.out.println("The total amount of notes does not match the total amount deposited. Please try again.");
            return;
        }

        admin.depositToATM(totalAmount, notes2000, notes500, notes200, notes100);
    }

    public void viewAllTransactions() {
        admin.viewAllTransactions(customers);
    }

    private boolean isCustomerIdExist(String customerId) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return true;
            }
        }
        return false;
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
