import java.util.Random;
public class Q2_4AP {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor
    public Q2_4AP() {
        this.accountHolder = "Anonymous";
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // Constructor with name
    public Q2_4AP(String accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
    }

    // Constructor with name and initial balance
    public Q2_4AP(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = generateAccountNumber();
        if (initialBalance > 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    // Helper method to generate a random account number
    private int generateAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000); // Generates a 6-digitnumber
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited $%.2f. New balance: $%.2f\n",
                    amount, balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Withdrawal failed. Insufficient funds.");
        } else {
            balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f\n",
                    amount, balance);
        }
    }

    public void displayAccount() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Current Balance: $%.2f\n", balance);
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {
        Q2_4AP acc1 = new Q2_4AP("Alice");
        Q2_4AP acc2 = new Q2_4AP("Bob", 500.0);
        acc1.displayAccount();
        acc2.displayAccount();
        System.out.println("\n--- Performing Transactions for Alice ---");
        acc1.deposit(200.0);
        acc1.withdraw(50.0);
        System.out.println("\n--- Performing Transactions for Bob ---");
        acc2.withdraw(600.0); // Should fail
        acc2.withdraw(150.0);
        System.out.println("\n--- Final Account States ---");
        acc1.displayAccount();
        acc2.displayAccount();
    }
}