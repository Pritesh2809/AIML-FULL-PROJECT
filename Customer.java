// Class declaration that extends the Account class.
public class Customer extends Account {
    // Instance variable to store the balance of the customer.
    double balance;

    // Constructor to initialize the Customer object with an ID and password.
    public Customer(String id, String password) {
        // Calling the constructor of the superclass Account to initialize ID and password.
        super(id, password);
        // Initializing the balance to 0.0 when a new Customer object is created.
        this.balance = 0.0;
    }

    // Getter method to retrieve the current balance of the customer.
    public double getBalance() {
        // Returning the current balance.
        return balance;
    }

    // Method to check and display the current balance of the customer.
    public void checkBalance() {
        // Printing the current balance to the console.
        System.out.println("Your balance is: " + getBalance());
    }

    // Method to deposit a specified amount into the customer's account.
    public void deposit(double amount) {
        // Adding the specified amount to the current balance.
        balance += amount;
        // Logging the deposit transaction by adding it to the transaction list.
        addTransaction(new Transaction("Deposit", amount, balance));
    }
}
