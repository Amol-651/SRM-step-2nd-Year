/**
 * Q6_3AP.java
 * Assignment 6: Employee Payroll and Attendance System
 * An integrated system showing complex interactions between Employee and
 * Department objects, handling payroll and attendance with different employee types.
 */
import java.util.ArrayList;
import java.util.List;

class Employee {
    String empId;
    String empName;
    String designation;
    String empType; // "Full-time", "Part-time", "Contract"
    double baseValue; // Per month for Full-time, per hour for Part-time, fixed for Contract
    boolean[] attendanceRecord;

    private static int empIdCounter = 0;

    public Employee(String empName, String designation, String empType, double baseValue) {
        this.empId = "E" + String.format("%04d", ++empIdCounter);
        this.empName = empName;
        this.designation = designation;
        this.empType = empType;
        this.baseValue = baseValue;
        this.attendanceRecord = new boolean[Q6_3AP.getWorkingDaysPerMonth()];
    }

    public String getEmpId() { return empId; }
    public String getEmpName() { return empName; }

    public void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= Q6_3AP.getWorkingDaysPerMonth()) {
            attendanceRecord[day - 1] = present;
        }
    }

    public int getDaysPresent() {
        int count = 0;
        for (boolean present : attendanceRecord) {
            if (present) count++;
        }
        return count;
    }

    public double calculateSalary(double performanceBonus, int hoursWorked) {
        double grossSalary = 0;
        switch (empType) {
            case "Full-time":
                grossSalary = (baseValue * getDaysPresent() / Q6_3AP.getWorkingDaysPerMonth()) + performanceBonus;
                break;
            case "Part-time":
                grossSalary = baseValue * hoursWorked;
                break;
            case "Contract":
                grossSalary = baseValue;
                break;
        }
        return grossSalary;
    }

    public void generatePaySlip(double grossSalary) {
        System.out.println("\n--- Pay Slip for " + empName + " (" + empId + ") ---");
        System.out.println("Designation: " + designation + " (" + empType + ")");
        System.out.printf("Gross Salary for the month: $%.2f\n", grossSalary);
        System.out.println("------------------------------------------");
    }
}

class Department {
    String deptId;
    String deptName;
    Employee manager;
    List<Employee> employees;
    double budget;

    public Department(String deptId, String deptName, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.budget = budget;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee emp) { employees.add(emp); }
    public void setManager(Employee mgr) { this.manager = mgr; }
    public String getDeptName() { return deptName; }
    public List<Employee> getEmployees() { return employees; }
}

public class Q6_3AP {
    private static String companyName = "Innovate Corp";
    private static int totalEmployees = 0;
    private static double totalSalaryExpense = 0;
    private static int workingDaysPerMonth = 22;

    private static List<Department> departments = new ArrayList<>();
    private static List<Employee> allEmployees = new ArrayList<>();

    // --- Static Getters ---
    public static String getCompanyName() { return companyName; }
    public static int getTotalEmployees() { return totalEmployees; }
    public static double getTotalSalaryExpense() { return totalSalaryExpense; }
    public static int getWorkingDaysPerMonth() { return workingDaysPerMonth; }

    public static void calculateCompanyPayroll() {
        totalSalaryExpense = 0;
        System.out.println("\n======= Calculating Payroll for " + getCompanyName() + " =======");
        for (Employee emp : allEmployees) {
            double bonus = emp.empType.equals("Full-time") ? 500 : 0;
            int hours = emp.empType.equals("Part-time") ? 80 : 0;

            double salary = emp.calculateSalary(bonus, hours);
            totalSalaryExpense += salary;
            emp.generatePaySlip(salary);
        }
        System.out.printf("\n>>> Total Salary Expense for the Month: $%.2f\n", getTotalSalaryExpense());
        System.out.println("======================================================");
    }

    public static void main(String[] args) {
        Department engineering = new Department("D01", "Engineering", 500000);
        departments.add(engineering);

        Employee alice = new Employee("Alice", "Senior Engineer", "Full-time", 8000);
        Employee bob = new Employee("Bob", "Intern", "Part-time", 25);
        Employee charlie = new Employee("Charlie", "Consultant", "Contract", 5000);

        allEmployees.add(alice);
        allEmployees.add(bob);
        allEmployees.add(charlie);
        totalEmployees = allEmployees.size();

        engineering.addEmployee(alice);
        engineering.addEmployee(bob);
        engineering.addEmployee(charlie);
        engineering.setManager(alice);

        for (int i = 1; i <= 22; i++) alice.markAttendance(i, true);
        for (int i = 1; i <= 15; i++) bob.markAttendance(i, true);

        System.out.println(alice.getEmpName() + " was present for " + alice.getDaysPresent() + " days.");

        calculateCompanyPayroll();
    }
}

