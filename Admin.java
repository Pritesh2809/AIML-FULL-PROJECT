import java.util.ArrayList;

public class Admin extends Account { // Declaring Admin class that extends Account
    private Notes notes; // Declaring Notes object to manage currency notes
    private ArrayList<Transaction> transactions; // List to store transactions

    // Constructor for the Admin class
    public Admin(String id, String password) {
        super(id, password); // Calling constructor of the superclass Account to initialize id and password
        this.notes = new Notes(); // Initializing the notes object to manage currency notes
        this.transactions = new ArrayList<>(); // Initializing the transactions list to keep track of admin's transactions
    }

    // Getter method for notes
    public Notes getNotes() {
        return notes; // Returning notes
    }

    // Getter method for transactions
    public ArrayList<Transaction> getTransactions() {
        return transactions; // Returning transactions
    }

    // Method to add a transaction
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction); // Adding the transaction to the list
    }
}
