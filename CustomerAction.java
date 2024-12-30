import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAction { // Handling customer actions

    // Customer login
    public static Customer customerLogin(Scanner scanner, ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("No account found. Please contact admin to create an account.");
            return null;
        }

        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        Customer customer = findAccountById(customerId, customers);
        if (customer == null) {
            System.out.println("Customer ID not found. Returning to main menu.");
            return null;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (customer.login(customerId, password)) {
            return customer;
        } else {
            System.out.println("Invalid credentials. Returning to main menu.");
            return null;
        }
    }

    // Deposit cash
    public static void depositCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();

        if (depositAmount <= 0 || depositAmount % 100 != 0) {
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

        if (calculatedAmount != depositAmount) {
            System.out.println("The total amount of notes does not match the total amount deposited. Please try again.");
            return;
        }

        customer.deposit(depositAmount);
        ATM.depositToAtm(depositAmount);
        NotesAction.addNotes(admin.getNotes(), notes2000, notes500, notes200, notes100);
        customer.addTransaction(new Transaction("Deposit", depositAmount, customer.getBalance()));
        System.out.println("Deposit successful. New balance: " + customer.getBalance());
    }

    // Withdraw cash
    public static void withdrawCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        scanner.nextLine();

        if (customer.getBalance() >= withdrawAmount) {
            if (NotesAction.withdrawNotes(admin.getNotes(), withdrawAmount)) {
                customer.withdraw(withdrawAmount);
                ATM.withdrawFromAtm(withdrawAmount);
                customer.addTransaction(new Transaction("Withdraw", withdrawAmount, customer.getBalance()));
                System.out.println("Withdrawal successful. New balance: " + customer.getBalance());
            } else {
                System.out.println("Unable to dispense the requested amount with available notes.");
            }
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Change customer password
    public static void changeCustomerPassword(Scanner scanner, Customer customer) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        customer.changePassword(newPassword);
    }

    // View customer transactions
    public static void viewTransactions(Customer customer) {
        for (Transaction transaction : customer.getTransactions()) {
            System.out.println(transaction);
        }
    }

    // Helper methods
    private static Customer findAccountById(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}