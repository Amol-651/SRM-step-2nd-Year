public class Q3_3PP {
    // Static members
    static String bankName;
    static int totalAccounts = 0;
    static double interestRate;

    // Instance members
    String accountNumber;
    String accountHolder;
    double balance;

    // Constructor
    public Q3_3PP(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        totalAccounts++; // Increment static counter for each new account
    }

    // Static methods
    public static void setBankName(String name) {
        bankName = name;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static void displayBankInfo() {
        System.out.println("Bank: " + bankName + ", Interest Rate: " + interestRate + "%, Total Accounts: " + totalAccounts);
    }

    // Instance methods
    public void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolder + " deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew $" + amount);
        } else {
            System.out.println("Insufficient funds for " + accountHolder);
        }
    }

    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        System.out.println("Interest for " + accountHolder + ": $" + interest);
    }

    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolder + ", Account #: " + accountNumber + ", Balance: $" + balance);
    }

    public static void main(String[] args) {
        // Set static bank-wide properties
        Q3_3PP.setBankName("Global Trust Bank");
        Q3_3PP.setInterestRate(3.5);

        // Create instances of the class
        Q3_3PP acc1 = new Q3_3PP("ACC001", "Alice", 1000);
        Q3_3PP acc2 = new Q3_3PP("ACC002", "Bob", 2000);

        // Perform instance-specific operations
        acc1.deposit(500);
        acc2.withdraw(300);

        // Calculate and display interest for each account
        acc1.calculateInterest();
        acc2.calculateInterest();

        // Display individual account info
        acc1.displayAccountInfo();
        acc2.displayAccountInfo();

        // Display static bank-wide info
        Q3_3PP.displayBankInfo();
        System.out.println("Total accounts (called from main): " + Q3_3PP.getTotalAccounts());
    }
}