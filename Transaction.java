public class Transaction {
    private String description;
    private double amount;
    private double balance;

    public Transaction(String description, double amount, double balance) {
        this.description = description;
        this.amount = amount;
        this.balance = balance;
    }


    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return  " Description: " + description + ", Amount: " + amount + ", Balance: " + balance;
    }
}
