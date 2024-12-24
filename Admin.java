import java.util.ArrayList;

public class Admin extends Account {
    private double atmBalance;
    private Notes notes;
    private ArrayList<Transaction> atmTransactions;

    public Admin(String id, String password) {
        super(id, password, 0.0);
        this.atmBalance = 0.0;
        this.notes = new Notes();
        this.atmTransactions = new ArrayList<>();
    }

    public void depositToATM(double totalAmount, int notes2000, int notes500, int notes200, int notes100) {
        notes.addNotes(notes2000, notes500, notes200, notes100);
        atmBalance += totalAmount;

        atmTransactions.add(new Transaction("ATM Deposit", totalAmount, atmBalance));
        System.out.println("Deposited " + totalAmount + " Rs to the ATM.");
        System.out.println("New ATM balance updated.");
    }

    public boolean withdrawFromATM(double amount) {
        if (notes.withdrawNotes(amount)) {
            atmBalance -= amount;
            atmTransactions.add(new Transaction("ATM Withdraw", -amount, atmBalance));
            return true;
        } else {
            System.out.println("Unable to dispense the requested amount with available notes.");
            return false;
        }
    }

    public void viewAtmBalance() {
        System.out.println("ATM balance: " + atmBalance + " Rs.");
    }

    public void viewAllTransactions(ArrayList<Customer> customers) {
        System.out.println("ATM Transactions:");
        for (Transaction transaction : atmTransactions) {
            System.out.println(transaction);
        }

        System.out.println("\nCustomer Transactions:");
        for (Customer customer : customers) {
            System.out.println("Customer ID: " + customer.getId());
            customer.viewTransactions();
        }
    }

    public boolean login(String adminId, String password) {
        return getId().equals(adminId) && getPassword().equals(password);
    }
}
