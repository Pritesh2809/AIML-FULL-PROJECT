// Class declaration for representing a transaction.
public class Transaction {
    // Private instance variables to store the transaction type, amount, and balance after the transaction.
    private String type;
    private double amount;
    private double balanceAfterTransaction;

    // Constructor to initialize a Transaction object with type, amount, and balance after the transaction.
    public Transaction(String type, double amount, double balanceAfterTransaction) {
        this.type = type; // Initializing the type of transaction (e.g., "Deposit", "Withdraw").
        this.amount = amount; // Initializing the amount involved in the transaction.
        this.balanceAfterTransaction = balanceAfterTransaction; // Initializing the balance after the transaction is completed.
    }

    // Overriding the toString method to provide a custom string representation of the Transaction object.
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' + // Including the type of transaction in the string representation.
                ", amount=" + amount + // Including the amount involved in the transaction in the string representation.
                ", balanceAfterTransaction=" + balanceAfterTransaction + // Including the balance after the transaction in the string representation.
                '}'; // Closing the string representation.
    }
}
