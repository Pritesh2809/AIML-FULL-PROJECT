public class Transaction { // Transaction class
    private final String type; // Variable for transaction type
    private final double amount; // Variable for transaction amount
    private final double balanceAfter; // Variable for balance after transaction

    public Transaction(String type, double amount, double balanceAfter) { // Constructor for Transaction class
        this.type = type; // Initializing transaction type
        this.amount = amount; // Initializing transaction amount
        this.balanceAfter = balanceAfter; // Initializing balance after transaction
    }

    public String toString() { // Method to convert Transaction to String
        return "Transaction Type: " + type + ", Amount: " + amount + ", Balance After Transaction: " + balanceAfter;
    }
}
