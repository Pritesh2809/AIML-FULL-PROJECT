import java.util.ArrayList;

public class Customer extends Account { // Declaring Customer class that extends Account
    private double balance; // Declaring private variable for account balance, specific to each customer
    private final ArrayList<Transaction> transactions; // List to store transactions, specific to each customer

    // Constructor for the Customer class
    public Customer(String id, String password) {
        super(id, password); // Calling constructor of the superclass Account to initialize id and password
        this.balance = 0.0; // Initializing account balance to zero
        this.transactions = new ArrayList<>(); // Initializing the transactions list to keep track of customer's transactions
    }

    // Getter method for transactions
    public ArrayList<Transaction> getTransactions() {
        return transactions; // Returning the transactions list
    }

    // Method to add a transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction); // Adding the transaction to the list
    }

    // Method to check the balance
    public void checkBalance() {
        System.out.println("Your balance is: " + getBalance()); // Printing the current balance
    }

    // Method to deposit money
    public void deposit(double amount) {
        balance += amount; // Adding amount to account balance
        addTransaction(new Transaction("Deposit", amount, balance)); // Adding deposit transaction to the list
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (balance >= amount) { // Checking if balance is sufficient
            balance -= amount; // Subtracting amount from balance
            addTransaction(new Transaction("Withdraw", amount, balance)); // Adding withdraw transaction to the list
        } else { // If balance is insufficient
            System.out.println("Insufficient balance."); // Printing insuffisient balance message
        }
    }

    // Getter method for account balance
    public double getBalance() {
        return balance; // Returning account balance
    }

    // Getter method for customer ID
    public String getId() {
        return super.getId(); // Returning the ID from superclass Account
    }
}
