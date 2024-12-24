import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAction {
    private ArrayList<Customer> customers;
    private Admin admin;

    public CustomerAction(ArrayList<Customer> customers, Admin admin) {
        this.customers = customers;
        this.admin = admin;
    }

    public void customerMenu(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Deposit cash");
            System.out.println("3. Withdraw cash");
            System.out.println("4. Change password");
            System.out.println("5. View transaction history");
            System.out.println("6. Exit to main menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customer.checkBalance();
                    break;
                case 2:
                    depositCash(scanner, customer);
                    break;
                case 3:
                    withdrawCash(scanner, customer);
                    break;
                case 4:
                    changePassword(scanner, customer);
                    break;
                case 5:
                    customer.viewTransactions();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void depositCash(Scanner scanner, Customer customer) {
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();

        if (depositAmount <= 0 || depositAmount % 100 != 0) {
            System.out.println("Invalid amount. Please enter a positive amount in multiples of 100.");
            return;
        }

        System.out.print("Enter number of 2000 Rs notes: ");
        int notes2000 = scanner.nextInt();
        System.out.print("Enter number of 500 Rs notes: ");
        int notes500 = scanner.nextInt();
        System.out.print("Enter number of 200 Rs notes: ");
        int notes200 = scanner.nextInt();
        System.out.print("Enter number of 100 Rs notes: ");
        int notes100 = scanner.nextInt();
        scanner.nextLine();

        double calculatedAmount = (notes2000 * 2000) + (notes500 * 500) + (notes200 * 200) + (notes100 * 100);

        if (calculatedAmount != depositAmount) {
            System.out.println("The total amount of notes does not match the total amount deposited. Please try again.");
            return;
        }

        customer.deposit(depositAmount);
        admin.depositToATM(depositAmount, notes2000, notes500, notes200, notes100);
    }

    private void withdrawCash(Scanner scanner, Customer customer) {
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        scanner.nextLine();

        if (customer.getBalance() >= withdrawAmount) {
            if (admin.withdrawFromATM(withdrawAmount)) {
                customer.withdraw(withdrawAmount);
            } else {
                System.out.println("Unable to dispense the requested amount with available notes.");
            }
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void changePassword(Scanner scanner, Customer customer) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        customer.changePin(newPassword);
    }
}
