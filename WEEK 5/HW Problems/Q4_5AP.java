import java.util.*;

/**
 * Q4_5AP - Employee Payroll Demo (single-file)
 */
public class Q4_5AP {
    public static void main(String[] args) {
        System.out.println("=== Q4_5AP: Employee Payroll Demo ===");
        Employee e1 = new Employee("E-001", "Neha", Role.SALARIED, 60000);
        Employee e2 = new Employee("E-002", "Vikram", Role.HOURLY, 120); // hourly-rate

        Payroll payroll = new Payroll();
        payroll.addEmployee(e1);
        payroll.addEmployee(e2);

        System.out.println("Pay for Neha (monthly): " + payroll.computePay("E-001", 1)); // months/hours depending role
        System.out.println("Pay for Vikram (40 hours): " + payroll.computePay("E-002", 40));
        System.out.println("Payroll total expense: " + payroll.totalPayroll());
    }

    enum Role { SALARIED, HOURLY }

    static final class Employee {
        private final String id;
        private String name;
        private final Role role;
        private double compensation; // monthly salary OR hourly rate depending on role

        public Employee(String id, String name, Role role, double compensation) {
            if (id == null || id.isEmpty()) throw new IllegalArgumentException("Bad id");
            this.id = id;
            this.name = name;
            this.role = role;
            this.compensation = compensation;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public Role getRole() { return role; }
        public double getCompensation() { return compensation; }

        public void setName(String name) { this.name = name; }
        public void setCompensation(double compensation) { this.compensation = compensation; }

        @Override
        public String toString() { return id + ":" + name + "(" + role + ")"; }
    }

    static class Payroll {
        private final Map<String, Employee> employees = new HashMap<>();

        public void addEmployee(Employee e) {
            employees.put(e.getId(), e);
        }

        // computePay: for SALARIED expects months (1 => monthly pay), for HOURLY expects hours
        public double computePay(String employeeId, double periodAmount) {
            Employee e = employees.get(employeeId);
            if (e == null) throw new IllegalArgumentException("No such employee");
            if (e.getRole() == Role.SALARIED) {
                // periodAmount interpreted as months
                return Math.round(e.getCompensation() * periodAmount * 100.0) / 100.0;
            } else {
                // hourly worker: periodAmount interpreted as hours worked
                double gross = e.getCompensation() * periodAmount;
                double tax = gross * 0.12; // simple tax
                return Math.round((gross - tax) * 100.0) / 100.0;
            }
        }

        public double totalPayroll() {
            double total = 0;
            for (Employee e : employees.values()) {
                if (e.getRole() == Role.SALARIED) total += e.getCompensation();
                else total += e.getCompensation() * 160; // assume 160 hours/month baseline
            }
            return Math.round(total * 100.0) / 100.0;
        }
    }
}
