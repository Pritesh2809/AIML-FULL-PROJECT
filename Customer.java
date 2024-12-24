import java.util.ArrayList;

public class Customer extends Account {
    private ArrayList<Transaction> transactions;

    public Customer(String id, String password, double balance) {
        super(id, password, balance);
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        transactions.add(new Transaction( "Deposit", amount, getBalance()));
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        transactions.add(new Transaction( "Withdraw", amount, getBalance()));
    }

    public void viewTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public boolean login(String customerId, String password) {
        return getId().equals(customerId) && getPassword().equals(password);
    }
}
