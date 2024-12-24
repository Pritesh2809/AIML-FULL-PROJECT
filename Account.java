public class Account {
    private String id;
    private String password;
    private double balance;

    public Account(String id, String password, double balance) {
        this.id = id;
        this.password = password;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void changePin(String newPassword) {
        this.password = newPassword;
        System.out.println("PIN changed successfully.");
    }
}
