import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAction { // CustomerAction class for customer operations

    // Method for customer login
    public static Customer customerLogin(Scanner scanner, ArrayList<Customer> customers) {
        System.out.println("Enter Customer ID: ");
        String id = scanner.nextLine(); // Get customer ID from input
        System.out.println("Enter Password: ");
        String password = scanner.nextLine(); // Get customer password from input

        // Loop through the list of customers to verify login credentials
        for (Customer customer : customers) {
            if (customer.login(id, password)) { // Check if customer ID and password match
                System.out.println("Login successful."); // Print success message
                return customer; // Return the logged-in customer
            }
        }

        System.out.println("Invalid Customer ID or Password."); // Print error message if credentials are incorrect
        return null; // Return null if login fails
    }

    // Method for customer to deposit cash
    public static void depositCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.println("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Get deposit amount from input

        customer.deposit(amount); // Deposit amount into customer's account
        ATM.depositToAtm(amount); // Deposit amount into ATM balance
        admin.addTransaction(new Transaction("Customer Deposit", amount, ATM.getAtmBalance())); // Add transaction to admin's transaction list

        System.out.println("Deposit successful. Your new balance is: " + customer.getBalance()); // Print success message with new balance
    }

    // Method for customer to withdraw cash
    public static void withdrawCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.println("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Get withdrawal amount from input

        // Check if the ATM has sufficient balance and can dispense the requested amount
        if (ATM.getAtmBalance() < amount) {
            System.out.println("Insufficient ATM balance."); // Print error message if ATM balance is insufficient
        } else {
            // Attempt to withdraw the notes from the ATM
            if (NotesAction.withdrawNotes(admin.getNotes(), amount)) {
                customer.withdraw(amount); // Withdraw amount from customer's account
                ATM.withdrawFromAtm(amount); // Withdraw amount from ATM balance
                admin.addTransaction(new Transaction("Customer Withdraw", amount, ATM.getAtmBalance())); // Add transaction to admin's transaction list

                System.out.println("Withdrawal successful. Your new balance is: " + customer.getBalance()); // Print success message with new balance
            } else {
                System.out.println("ATM cannot dispense the requested amount. Please try a different amount."); // Print error message if ATM cannot dispense the requested amount
            }
        }
    }

    // Method for customer to change their password
    public static void changeCustomerPassword(Scanner scanner, Customer customer) {
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine(); // Get new password from input
        customer.changePassword(newPassword); // Change customer's password

        System.out.println("Password changed successfully."); // Print success message
    }

    // Method to view customer's transaction history
    public static void viewTransactions(Customer customer) {
        ArrayList<Transaction> transactions = customer.getTransactions(); // Get customer's transaction list

        System.out.println("Transaction History: "); // Print header for transaction history
        // Loop through each transaction and print the details
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString()); // Print transaction details
        }
    }
}
