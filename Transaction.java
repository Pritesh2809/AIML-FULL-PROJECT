// Transaction class to represent a banking transaction
public class Transaction {
    private String type; // Variable for transaction type
    private double amount; // Variable for transaction amount
    private double balanceAfter; // Variable for balance after transaction

    // Constructor for Transaction class
    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type; // Initializing transaction type (e.g., "Deposit" or "Withdraw")
        this.amount = amount; // Initializing transaction amount
        this.balanceAfter = balanceAfter; // Initializing balance after transaction
    }

    // Getter method for transaction type
    public String getType() {
        return type; // Returning transaction type
    }

    // Getter method for transaction amount
    public double getAmount() {
        return amount; // Returning transaction amount
    }

    // Getter method for balance after transaction
    public double getBalanceAfter() {
        return balanceAfter; // Returning balance after transaction
    }

    // Method to convert Transaction object to String representation

    public String toString() {
        return "Transaction Type: " + type + ", Amount: " + amount + ", Balance After Transaction: " + balanceAfter; // Returning string representation of the transaction
    }
}
