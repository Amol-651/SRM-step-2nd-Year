/**
 * Q1_3AP.java
 * Assignment 1: Personal Finance Manager
 * This program demonstrates basic class creation, object usage, and the difference
 * between static and instance members in a personal finance context.
 */

// Represents a single transaction record.
class Transaction {
    String description;
    double amount;
    String type; // "Income" or "Expense"

    public Transaction(String description, double amount, String type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }
}

class PersonalAccount {
    // --- Static Members (Shared across all accounts) ---
    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";
    private static int accountCounter = 0;

    // --- Instance Members (Unique to each account object) ---
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;
    // To keep a history of transactions
    private Transaction[] transactions;
    private int transactionCount;


    // --- Constructor ---
    public PersonalAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.currentBalance = initialBalance;
        this.totalIncome = initialBalance > 0 ? initialBalance : 0;
        this.totalExpenses = 0;
        this.accountNumber = generateAccountNumber();

        // Initialize transaction history
        this.transactions = new Transaction[100]; // Max 100 transactions
        this.transactionCount = 0;
        if (initialBalance > 0) {
            addTransaction("Initial Deposit", initialBalance, "Income");
        }

        totalAccounts++; // Increment the static counter for every new account
    }

    // --- Static Methods ---
    public static void setBankName(String name) {
        bankName = name;
    }

    public static String getBankName() {
        return bankName;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    // Generates a unique account number like ACC001, ACC002, etc.
    private static String generateAccountNumber() {
        accountCounter++;
        return "ACC" + String.format("%03d", accountCounter);
    }

    // --- Instance Methods ---

    // Helper to add a transaction to the history
    private void addTransaction(String description, double amount, String type) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = new Transaction(description, amount, type);
        }
    }

    public void addIncome(double amount, String description) {
        if (amount > 0) {
            currentBalance += amount;
            totalIncome += amount;
            addTransaction(description, amount, "Income");
            System.out.println("Income added: $" + amount + " (" + description + ")");
        } else {
            System.out.println("Income amount must be positive.");
        }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
            addTransaction(description, amount, "Expense");
            System.out.println("Expense added: $" + amount + " (" + description + ")");
        } else if (amount > currentBalance) {
            System.out.println("Expense failed: Insufficient funds.");
        } else {
            System.out.println("Expense amount must be positive.");
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n--- Account Summary for " + accountHolderName + " ---");
        System.out.println("Bank: " + bankName); // Accessing static variable is fine inside the class
        System.out.println("Account Number: " + accountNumber);
        System.out.printf("Current Balance: $%.2f\n", currentBalance);
        System.out.printf("Total Income: $%.2f\n", totalIncome);
        System.out.printf("Total Expenses: $%.2f\n", totalExpenses);
        System.out.printf("Net Savings: $%.2f\n", calculateSavings());
        System.out.println("----------------------------------------");
    }
}

public class Q1_3AP {
    public static void main(String[] args) {
        // Set the static bank name, shared by all accounts
        PersonalAccount.setBankName("Global Finance Bank");

        System.out.println("--- Creating Personal Accounts ---");
        PersonalAccount acc1 = new PersonalAccount("Alice", 500.00);
        PersonalAccount acc2 = new PersonalAccount("Bob", 1200.50);
        PersonalAccount acc3 = new PersonalAccount("Charlie", 250.75);

        System.out.println("\nTotal accounts created: " + PersonalAccount.getTotalAccounts());

        // Display initial summaries
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Perform transactions on Alice's account
        System.out.println("\n--- Processing Transactions for Alice ---");
        acc1.addIncome(250.00, "Monthly Salary");
        acc1.addExpense(50.00, "Groceries");
        acc1.addExpense(75.00, "Utility Bill");

        // Perform transactions on Bob's account
        System.out.println("\n--- Processing Transactions for Bob ---");
        acc2.addExpense(200.00, "Rent");
        acc2.addExpense(1500.00, "Car Repair"); // This should fail

        // Display updated summaries
        System.out.println("\n--- Final Account Summaries ---");
        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        // Demonstrate static variable is shared by using the public getter method
        System.out.println("\nAll accounts operate under the same bank: " + PersonalAccount.getBankName());
    }
}

