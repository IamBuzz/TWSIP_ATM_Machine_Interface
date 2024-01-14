import java.util.LinkedList;

class User {
    String name;
    String pin;
    int balance;
    LinkedList<Transaction> transactionHistory;

    public User(String name, String pin, int balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new LinkedList<>();
    }

}