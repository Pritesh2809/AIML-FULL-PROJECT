import java.util.ArrayList;

public class Account { // Declaring POJO Account class

    private String id; // Declaring private variable for account ID
    private String password; // Declaring private variable for account password
    private ArrayList<Transaction> transactions; // List to store transactions

    // Parameterized constructor
    public Account(String id, String password) {
        this.id = id; // Initializing account ID
        this.password = password; // Initializing account password
        this.transactions = new ArrayList<>(); // Initializing the transactions list to keep track of account's transactions
    }

    // Getter method for account ID
    public String getId() {
        return id; // Returning account ID
    }

    // Setter method for account ID
    public void setId(String id) {
        this.id = id; // Setting account ID
    }

    // Getter method for account password
    public String getPassword() {
        return password; // Returning account password
    }

    // Setter method for account password
    public void setPassword(String password) {
        this.password = password; // Setting account password
    }

    // Getter method for transactions
    public ArrayList<Transaction> getTransactions() {
        return transactions; // Returning the transactions list
    }

    // Method to add a transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction); // Adding the transaction to the list
    }

    // Method for login
    public boolean login(String id, String password) {
        // Checking if the provided ID and password match the account's ID and password
        return this.id.equals(id) && this.password.equals(password);
    }

    // Method to display transactions
    public void displayTransactions() {
        System.out.println("Transaction History: "); // Print header for transaction history
        // Loop through each transaction and print the details
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString()); // Print transaction details
        }
    }
}
