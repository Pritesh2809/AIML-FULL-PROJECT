import java.util.ArrayList;

public class Customer extends Account { // Declaring Customer class that extends Account
    private double balance; // Declaring private variable for account balance
    private final ArrayList<Transaction> transactions; // List to store transactions

    public Customer(String id, String password) { // Constructor for Customer class
        super(id, password); // Calling constructor of the superclass Account
        this.balance = 0.0; // Initializing account balance to zero
        this.transactions = new ArrayList<>(); // Initializing the transactions list
    }

    public ArrayList<Transaction> getTransactions() { // Getter method for transactions
        return transactions; // Returning the transactions list
    }

    public void addTransaction(Transaction transaction) { // Method to add a transaction
        transactions.add(transaction); // Adding the transaction to the list
    }

    public void checkBalance() { // Method to check the balance
        System.out.println("Your balance is: " + getBalance()); // Printing the current balance
    }

    public void deposit(double amount) { // Method to deposit money
        balance += amount; // Adding amount to account balance
        addTransaction(new Transaction("Deposit", amount, balance)); // Adding deposit transaction to the list
    }

    public void withdraw(double amount) { // Method to withdraw money
        if (balance >= amount) { // Checking if balance is sufficient
            balance -= amount; // Subtracting amount from balance
            addTransaction(new Transaction("Withdraw", amount, balance)); // Adding withdraw transaction to the list
        } else { // If balance is insufficient
            System.out.println("Insufficient balance."); // Printing insufficient balance message
        }
    }

    public double getBalance() { // Getter method for account balance
        return balance; // Returning account balance
    }

    public String getId() { // Getter method for customer ID
        return super.getId(); // Returning the ID from superclass Account
    }
}
