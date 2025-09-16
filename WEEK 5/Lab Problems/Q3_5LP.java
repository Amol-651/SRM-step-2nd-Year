import java.util.UUID;
import java.time.Instant;

public class Q3_5LP {
    public static void main(String[] args) {
        System.out.println("=== Q3: Bank Account Encapsulation Demo ===");
        AccountType savings = new AccountType("Savings", 5000);
        BankAccount a = new BankAccount("Rajat", savings);
        System.out.println(a);
        a.deposit(2000);
        a.withdraw(1000);
        try {
            a.withdraw(7000); // should fail (overdraft)
        } catch (IllegalArgumentException ex) {
            System.out.println("Expected fail: " + ex.getMessage());
        }
        a.setOwnerName("Rajat Kumar");
        System.out.println("Final: " + a);
    }

    // Small immutable account type
    static final class AccountType {
        private final String name;
        private final double minBalance;

        public AccountType(String name, double minBalance) {
            if (name == null || name.isEmpty()) throw new IllegalArgumentException("Bad type");
            if (minBalance < 0) throw new IllegalArgumentException("minBalance negative");
            this.name = name;
            this.minBalance = minBalance;
        }
        public String getName() { return name; }
        public double getMinBalance() { return minBalance; }
    }

    // Encapsulated bank account
    static class BankAccount {
        private final String accountId;
        private String ownerName;
        private final AccountType type;
        private double balance;
        private final long createdAt;

        private static final double MAX_DEPOSIT_SINGLE = 1_000_000.0;

        public BankAccount(String ownerName, AccountType type) {
            if (ownerName == null || ownerName.isEmpty()) throw new IllegalArgumentException("Owner required");
            if (type == null) throw new IllegalArgumentException("Type required");
            this.accountId = UUID.randomUUID().toString();
            this.ownerName = ownerName;
            this.type = type;
            this.balance = type.getMinBalance();
            this.createdAt = Instant.now().toEpochMilli();
        }

        public String getAccountId() { return accountId; }
        public String getOwnerName() { return ownerName; }
        public void setOwnerName(String ownerName) {
            if (ownerName == null || ownerName.isEmpty()) throw new IllegalArgumentException("Owner name invalid");
            this.ownerName = ownerName;
        }
        public AccountType getType() { return type; }
        public double getBalance() { return balance; }
        public long getCreatedAt() { return createdAt; }

        public void deposit(double amount) {
            if (amount <= 0 || amount > MAX_DEPOSIT_SINGLE) throw new IllegalArgumentException("Invalid deposit");
            balance += amount;
            System.out.println("Deposited " + amount + " => balance: " + balance);
        }

        public void withdraw(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Invalid withdraw");
            if (balance - amount < type.getMinBalance())
                throw new IllegalArgumentException("Would drop below min balance");
            balance -= amount;
            System.out.println("Withdrew " + amount + " => balance: " + balance);
        }

        @Override
        public String toString() {
            return "BankAccount{" +
                    "id='" + accountId + '\'' +
                    ", owner='" + ownerName + '\'' +
                    ", type=" + type.getName() +
                    ", balance=" + balance +
                    '}';
        }
    }
}
