import java.util.LinkedList;
import java.util.Scanner;
public class Main {
    private static LinkedList<User> users = new LinkedList<>();

    public static void main(String[] args) {
        // Create users
        users.add(new User("Kapil Desai", "1234", 1000)); // User with PIN 1234, initial balance $1000, and name John Doe
        users.add(new User("Emma Stone", "5678", 1500)); // User with PIN 5678, initial balance $1500, and name Jane Smith

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        User currentUser = authenticateUser(enteredPin);

        if (currentUser != null) {
            showMenu(currentUser);
        } else {
            System.out.println("Invalid PIN. Exiting...");
        }

        scanner.close();
    }

    private static User authenticateUser(String enteredPin) {
        for (User user : users) {
            if (user.pin.equals(enteredPin)) {
                return user;
            }
        }
        return null;
    }

    private static void showMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Menu for " + user.name + ":");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    performWithdrawal(user);
                    break;
                case 2:
                    performDeposit(user);
                    break;
                case 3:
                    checkBalance(user);
                    break;
                case 4:
                    showTransactionHistory(user);
                    break;
                case 5:
                    System.out.println("Exiting ATM. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void performWithdrawal(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter withdrawal amount: Rs");
        int amount = scanner.nextInt();

        if (amount > 0 && amount <= user.balance) {
            user.balance -= amount;
            Transaction withdrawal = new Transaction("Withdrawal", amount);
            user.transactionHistory.add(withdrawal);
            System.out.println("Withdrawal successful. Remaining balance: Rs" + user.balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    private static void performDeposit(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter deposit amount: Rs");
        int amount = scanner.nextInt();

        if (amount > 0) {
            user.balance += amount;
            Transaction deposit = new Transaction("Deposit", amount);
            user.transactionHistory.add(deposit);
            System.out.println("Deposit successful. Current balance: Rs" + user.balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private static void checkBalance(User user) {
        System.out.println("Your current balance is: Rs" + user.balance);
    }

    private static void showTransactionHistory(User user) {
        System.out.println("\nTransaction History for " + user.name + ":");
        for (Transaction transaction : user.transactionHistory) {
            System.out.println(transaction.type + ": Rs" + transaction.amount);
        }
    }
}



