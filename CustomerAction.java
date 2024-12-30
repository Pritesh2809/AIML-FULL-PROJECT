import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAction { // Declaring CustomerAction class

    // Method for customer login
    public static Customer customerLogin(Scanner scanner, ArrayList<Customer> customers) {
        if (customers.isEmpty()) { // Checking if customers list is empty
            System.out.println("No account found. Please contact admin to create an account."); // Printing message if no account found
            return new Customer("invalid", "invalid"); // Returning invalid customer
        }

        System.out.print("Enter customer ID: "); // Prompting user to enter customer ID
        String customerId = scanner.nextLine(); // Reading customer ID
        Customer customer = findAccountById(customerId, customers); // Finding customer by ID
        if (customer.getId().equals("invalid")) { // Checking if customer ID is invalid
            System.out.println("Customer ID not found. Returning to main menu."); // Printing message if ID not found
            return new Customer("invalid", "invalid"); // Returning invalid customer
        }

        System.out.print("Enter password: "); // Prompting user to enter password
        String password = scanner.nextLine(); // Reading password

        if (customer.login(customerId, password)) { // Checking if login is successful
            return customer; // Returning customer
        } else { // If login is unsuccessful
            System.out.println("Invalid credentials. Returning to main menu."); // Printing invalid credentials message
            return new Customer("invalid", "invalid"); // Returning invalid customer
        }
    }

    // Method for depositing cash
    public static void depositCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.print("Enter amount to deposit: "); // Prompting user to enter amount to deposit
        double depositAmount = scanner.nextDouble(); // Reading deposit amount
        scanner.nextLine(); // Consuming newline character

        if (depositAmount <= 0 || depositAmount % 100 != 0) { // Checking if deposit amount is valid
            System.out.println("Invalid amount. Please enter a positive amount in multiples of 100."); // Printing invalid amount message
            return; // Returning if amount is invalid
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

        double calculatedAmount = (notes2000 * 2000) + (notes500 * 500) + (notes200 * 200) + (notes100 * 100); // Calculating total amount of notes

        if (calculatedAmount != depositAmount) { // Checking if calculated amount matches deposit amount
            System.out.println("The total amount of notes does not match the total amount deposited. Please try again."); // Printing mismatch amount message
            return; // Returning if amounts do not match
        }

        customer.deposit(depositAmount); // Depositing amount to customer account
        ATM.depositToAtm(depositAmount); // Updating ATM balance
        NotesAction.addNotes(admin.getNotes(), notes2000, notes500, notes200, notes100); // Adding notes to admin's record
        customer.addTransaction(new Transaction("Deposit", depositAmount, customer.getBalance())); // Adding deposit transaction to customer
        System.out.println("Deposit successful. New balance: " + customer.getBalance()); // Printing deposit success message with new balance
    }

    // Method for withdrawing cash
    public static void withdrawCash(Scanner scanner, Customer customer, Admin admin) {
        System.out.print("Enter amount to withdraw: "); // Prompting user to enter amount to withdraw
        double withdrawAmount = scanner.nextDouble(); // Reading withdraw amount
        scanner.nextLine(); // Consuming newline character

        if (customer.getBalance() >= withdrawAmount) { // Checking if customer has sufficient balance
            if (NotesAction.withdrawNotes(admin.getNotes(), withdrawAmount)) { // Checking if admin can dispense the requested amount
                customer.withdraw(withdrawAmount); // Withdrawing amount from customer account
                ATM.withdrawFromAtm(withdrawAmount); // Updating ATM balance
                customer.addTransaction(new Transaction("Withdraw", withdrawAmount, customer.getBalance())); // Adding withdraw transaction to customer
                System.out.println("Withdrawal successful. New balance: " + customer.getBalance()); // Printing withdrawal success message with new balance
            } else { // If admin cannot dispense the requested amount
                System.out.println("Unable to dispense the requested amount with available notes."); // Printing unable to dispense message
            }
        } else { // If customer has insufficient balance
            System.out.println("Insufficient balance."); // Printing insufficient balance message
        }
    }

    // Method for changing customer password
    public static void changeCustomerPassword(Scanner scanner, Customer customer) {
        System.out.print("Enter new password: "); // Prompting user to enter new password
        String newPassword = scanner.nextLine(); // Reading new password
        customer.changePassword(newPassword); // Changing customer password
    }

    // Method for viewing customer transactions
    public static void viewTransactions(Customer customer) {
        for (Transaction transaction : customer.getTransactions()) { // Iterating through transactions
            System.out.println(transaction); // Printing transaction
        }
    }

    // Method for finding customer by ID
    private static Customer findAccountById(String customerId, ArrayList<Customer> customers) {
        for (Customer customer : customers) { // Iterating through customers
            if (customer.getId().equals(customerId)) { // Checking if customer ID matches
                return customer; // Returning customer
            }
        }
        return new Customer("invalid", "invalid"); // Returning invalid customer if ID not found
    }
}
