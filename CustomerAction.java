// Importing necessary classes from the Notes package.
import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;

// Class declaration for handling various customer actions.
public class CustomerAction {

    // Method for customer login.
    public static Customer customerLogin(Scanner scanner, ArrayList<Customer> customers) {
        // Prompting the user to enter Customer ID.
        System.out.println("Enter Customer ID: ");
        String id = scanner.nextLine(); // Reading the Customer ID input.

        // Prompting the user to enter Password.
        System.out.println("Enter Password: ");
        String password = scanner.nextLine(); // Reading the Password input.

        // Loop through the list of customers to verify login credentials.
        for (Customer customer : customers) {
            // Check if the entered ID and password match any customer.
            if (customer.login(id, password)) {
                System.out.println("Login successful."); // Print success message if credentials match.
                return customer; // Return the logged-in customer.
            }
        }

        // Print error message if credentials do not match any customer.
        System.out.println("Invalid Customer ID or Password.");
        return null; // Return null if login fails.
    }

    // Method for customer to deposit cash.
    public static void depositCash(Scanner scanner, Customer customer, Admin admin) {
        // Prompting the user to enter the total amount to deposit.
        System.out.println("Enter total amount to deposit: ");
        double totalAmount = Double.parseDouble(scanner.nextLine()); // Reading the amount input.

        // Check if the total amount is divisible by 100.
        if (totalAmount % 100 != 0) {
            System.out.println("The total amount must be divisible by 100. Please try again."); // Print error message if not divisible by 100.
            return; // Exit the method.
        }

        // Prompting the user to enter the number of 2000 Rs notes.
        System.out.println("Enter number of 2000 Rs notes: ");
        int num2000 = Integer.parseInt(scanner.nextLine()); // Reading the number of 2000 Rs notes.

        // Prompting the user to enter the number of 500 Rs notes.
        System.out.println("Enter number of 500 Rs notes: ");
        int num500 = Integer.parseInt(scanner.nextLine()); // Reading the number of 500 Rs notes.

        // Prompting the user to enter the number of 200 Rs notes.
        System.out.println("Enter number of 200 Rs notes: ");
        int num200 = Integer.parseInt(scanner.nextLine()); // Reading the number of 200 Rs notes.

        // Prompting the user to enter the number of 100 Rs notes.
        System.out.println("Enter number of 100 Rs notes: ");
        int num100 = Integer.parseInt(scanner.nextLine()); // Reading the number of 100 Rs notes.

        // Calculate the total value of the notes provided.
        double calculatedAmount = (num2000 * 2000) + (num500 * 500) + (num200 * 200) + (num100 * 100);

        // Check if the total amount matches the calculated amount.
        if (totalAmount != calculatedAmount) {
            System.out.println("The total amount does not match the sum of the notes provided. Please try again."); // Print error message if amounts do not match.
            return; // Exit the method.
        }

        // Deposit the specified amount into the customer's account.
        customer.deposit(totalAmount);

        // Deposit the specified amount into the ATM balance.
        ATM.depositToAtm(totalAmount);

        // Add the provided notes to the ATM's notes history.
        NotesAction.addNotes(admin.getNotes(), num2000, num500, num200, num100);

        // Log the deposit transaction to the admin's transaction list.
        admin.addTransaction(new Transaction("Customer Deposit", totalAmount, ATM.getAtmBalance()));

        // Print success message with the new balance.
        System.out.println("Deposit successful. Your new balance is: " + customer.getBalance());
    }

    // Method for customer to withdraw cash.
    public static void withdrawCash(Scanner scanner, Customer customer, Admin admin) {
        // Prompting the user to enter the amount to withdraw.
        System.out.println("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Reading the amount input.

        // Check if the ATM has sufficient balance.
        if (ATM.getAtmBalance() < amount) {
            System.out.println("Insufficient ATM balance."); // Print error message if ATM balance is insufficient.
        } else {
            // Attempt to withdraw the notes from the ATM.
            if (NotesAction.withdrawNotes(admin.getNotes(), amount)) {
                // Call the withdraw method to deduct the amount from the customer's balance.
                withdraw(customer, amount);

                // Deduct the amount from the ATM balance.
                ATM.withdrawFromAtm(amount);

                // Log the withdrawal transaction to the admin's transaction list.
                admin.addTransaction(new Transaction("Customer Withdraw", amount, ATM.getAtmBalance()));

                // Print success message with the new balance.
                System.out.println("Withdrawal successful. Your new balance is: " + customer.getBalance());
            } else {
                // Print error message if the ATM cannot dispense the requested amount.
                System.out.println("ATM cannot dispense the requested amount. Please try a different amount.");
            }
        }
    }

    // Method to withdraw a specified amount from the customer's account.
    public static void withdraw(Customer customer, double amount) {
        // Check if the customer has sufficient balance.
        if (customer.getBalance() >= amount) {
            customer.balance -= amount; // Deduct the specified amount from the balance.
            // Log the withdrawal transaction to the customer's transaction list.
            customer.addTransaction(new Transaction("Withdraw", amount, customer.getBalance()));
        } else {
            // Print error message if the balance is insufficient.
            System.out.println("Insufficient balance.");
        }
    }

    // Method for customer to change their password.
    public static void changeCustomerPassword(Scanner scanner, Customer customer) {
        // Prompting the user to enter the new password.
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine(); // Reading the new password input.

        // Setting the new password for the customer.
        customer.setPassword(newPassword);

        // Print success message.
        System.out.println("Password changed successfully.");
    }

    // Method to view the customer's transaction history.
    public static void viewTransactions(Customer customer) {
        // Retrieving the customer's transaction list.
        ArrayList<Transaction> transactions = customer.getTransactions();

        // Printing the header for the transaction history.
        System.out.println("Transaction History:");

        // Loop through each transaction and print its details.
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    // Method to find a customer by their ID.
    public static Customer findCustomerById(String customerId, ArrayList<Customer> customers) {
        // Loop through each customer in the list.
        for (Customer customer : customers) {
            // Check if the customer ID matches the provided ID.
            if (customer.getId().equals(customerId)) {
                return customer; // Return the customer if the ID matches.
            }
        }
        return null; // Return null if no customer matches the provided ID.
    }

    // Method to check if a customer ID exists in the list of customers.
    public static boolean isCustomerIdExist(String customerId, ArrayList<Customer> customers) {
        // Loop through each customer in the list.
        for (Customer customer : customers) {
            // Check if the customer ID matches the provided ID.
            if (customer.getId().equals(customerId)) {
                return true; // Return true if the ID exists.
            }
        }
        return false; // Return false if the ID does not exist.
    }
}
