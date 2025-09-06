public class Q1_3LP {
    // Private instance variables
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Static variables
    private static int totalAccounts = 0;
    private static int accountNumberCounter = 0;

    // Constructor
    public Q1_3LP(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Setting balance to 0.");
            this.balance = 0;
        } else {
            this.balance = initialDeposit;
        }
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    // Static method to generate unique account numbers like ACC001, ACC002
    public static String generateAccountNumber() {
        accountNumberCounter++;
        return String.format("ACC%03d", accountNumberCounter);
    }

    // Instance methods
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
        } else {
            balance += amount;
            System.out.println("Deposited $" + amount + " to " + accountNumber);
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal.");
        } else {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from " + accountNumber);
        }
    }

    public double checkBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Balance: $%.2f\n", balance);
        System.out.println("---------------------------");
    }

    // Static method to get total accounts created
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Main method to test
    public static void main(String[] args) {
        // Create an array of Q1_3LP objects (fixed size 3 for demo)
        Q1_3LP[] accounts = new Q1_3LP[3];

        // Create accounts
        accounts[0] = new Q1_3LP("Alice Johnson", 500);
        accounts[1] = new Q1_3LP("Bob Smith", 1000);
        accounts[2] = new Q1_3LP("Charlie Brown", -50); // Invalid initial deposit

        // Display all accounts info
        for (Q1_3LP acc : accounts) {
            acc.displayAccountInfo();
        }

        // Perform transactions
        accounts[0].deposit(200);
        accounts[1].withdraw(1500); // Should fail due to insufficient funds
        accounts[1].withdraw(200);
        accounts[2].deposit(100);
        accounts[2].withdraw(30);

        // Check balances after transactions
        System.out.println("\n--- Balances after transactions ---");
        for (Q1_3LP acc : accounts) {
            System.out.println(acc.accountHolderName + "'s balance: $" + acc.checkBalance());
        }

        // Show total accounts (static variable)
        System.out.println("\nTotal accounts created: " + Q1_3LP.getTotalAccounts());
    }
}
