public class Q3_3LP {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private static int empIdCounter = 0;

    // Full-time constructor
    public Q3_3LP(String empName, String department, double baseSalary) {
        this.empType = "Full-time";
        initialize(empName, department, baseSalary);
    }

    // Part-time constructor
    public Q3_3LP(String empName, String department) {
        this.empType = "Part-time";
        initialize(empName, department, 0);
    }

    // Contract constructor
    public Q3_3LP(String empName) {
        this.empType = "Contract";
        initialize(empName, "Contract Dept", 0);
    }

    private void initialize(String empName, String department, double baseSalary) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empId = generateEmpId();
        totalEmployees++;
    }

    private static String generateEmpId() {
        empIdCounter++;
        return "EMP" + String.format("%03d", empIdCounter);
    }

    // Overloaded calculateSalary methods
    // Full-time: base salary + bonus
    public double calculateSalary(double bonus) {
        if (!empType.equals("Full-time")) {
            System.out.println("Bonus only applicable for full-time employees.");
            return 0;
        }
        return baseSalary + bonus;
    }

    // Part-time: hourly rate * hours
    public double calculateSalary(int hoursWorked, double hourlyRate) {
        if (!empType.equals("Part-time")) {
            System.out.println("Hourly pay only applicable for part-time employees.");
            return 0;
        }
        return hoursWorked * hourlyRate;
    }

    // Contract: fixed amount
    public double calculateSalary(double fixedAmount, boolean isContract) {
        if (!empType.equals("Contract")) {
            System.out.println("Fixed amount only for contract employees.");
            return 0;
        }
        return fixedAmount;
    }

    // Method to calculate tax based on employee type
    public double calculateTax(double salary) {
        if (empType.equals("Full-time")) {
            return salary * 0.25; // 25% tax for full-time
        } else if (empType.equals("Part-time")) {
            return salary * 0.15; // 15% tax for part-time
        } else if (empType.equals("Contract")) {
            return salary * 0.10; // 10% tax for contract
        }
        return 0;
    }

    public void generatePaySlip(double salary, double tax) {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Employee Type: " + empType);
        System.out.printf("Salary: $%.2f\n", salary);
        System.out.printf("Tax: $%.2f\n", tax);
        System.out.printf("Net Pay: $%.2f\n", salary - tax);
        System.out.println("---------------------------");
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Employee Type: " + empType);
        System.out.println("---------------------------");
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    public static void main(String[] args) {
        Q3_3LP fullTime = new Q3_3LP("Alice", "Engineering", 70000);
        Q3_3LP partTime = new Q3_3LP("Bob", "Sales");
        Q3_3LP contract = new Q3_3LP("Charlie");

        System.out.println("--- Employee Information ---");
        fullTime.displayEmployeeInfo();
        partTime.displayEmployeeInfo();
        contract.displayEmployeeInfo();

        System.out.println("--- Payroll Processing ---");
        double ftSalary = fullTime.calculateSalary(5000); // base + bonus
        double ftTax = fullTime.calculateTax(ftSalary);
        fullTime.generatePaySlip(ftSalary, ftTax);

        double ptSalary = partTime.calculateSalary(120, 20); // hours * hourlyRate
        double ptTax = partTime.calculateTax(ptSalary);
        partTime.generatePaySlip(ptSalary, ptTax);

        double ctSalary = contract.calculateSalary(30000, true); // fixed amount
        double ctTax = contract.calculateTax(ctSalary);
        contract.generatePaySlip(ctSalary, ctTax);

        System.out.println("Total employees: " + Q3_3LP.getTotalEmployees());
    }
}
