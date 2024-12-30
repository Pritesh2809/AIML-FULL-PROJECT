import java.util.ArrayList;

public class Admin extends Account { // Declaring Admin class that extends Account
    private Notes notes; // Declaring Notes object
    private ArrayList<Transaction> transactions; // List to store transactions

    public Admin(String id, String password) { // Constructor for Admin class
        super(id, password); // Calling constructor of the superclass Account
        this.notes = new Notes(); // Initializing the notes object
        this.transactions = new ArrayList<>(); // Initializing the transactions list
    }

    public Notes getNotes() { // Getter method for notes
        return notes; // Returning notes
    }

    public ArrayList<Transaction> getTransactions() { // Getter method for transactions
        return transactions; // Returning transactions
    }

    public void addTransaction(Transaction transaction) { // Method to add a transaction
        transactions.add(transaction); // Adding the transaction to the list
    }
}
