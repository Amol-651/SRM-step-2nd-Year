import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;

public class Q4_5PP implements Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    public Q4_5PP() {}

    public Q4_5PP(String id, String first, String last, double sal, String dept, Date hire, boolean active) {
        employeeId = id;
        firstName = first;
        lastName = last;
        salary = sal;
        department = dept;
        hireDate = hire;
        isActive = active;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String id) { employeeId = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String first) { firstName = first; }

    public String getLastName() { return lastName; }
    public void setLastName(String last) { lastName = last; }

    public double getSalary() { return salary; }
    public void setSalary(double sal) { if (sal > 0) salary = sal; }

    public String getDepartment() { return department; }
    public void setDepartment(String dept) { department = dept; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date date) { hireDate = date; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getFullName() { return firstName + " " + lastName; }

    public int getYearsOfService() {
        Calendar hireCal = Calendar.getInstance();
        hireCal.setTime(hireDate);
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) - hireCal.get(Calendar.YEAR);
    }

    public String getFormattedSalary() { return "$" + salary; }

    public void setFullName(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length >= 2) {
            firstName = parts[0];
            lastName = parts[1];
        }
    }

    public String toString() {
        return employeeId + ": " + getFullName() + ", Dept: " + department + ", Salary: " + salary;
    }

    public boolean equals(Object o) {
        if (o instanceof Q4_5PP) return employeeId.equals(((Q4_5PP)o).employeeId);
        return false;
    }

    public int hashCode() { return employeeId.hashCode(); }

    public static void main(String[] args) {
        Q4_5PP e1 = new Q4_5PP();
        e1.setEmployeeId("E100");
        e1.setFullName("John Doe");
        e1.setSalary(50000);
        e1.setDepartment("IT");
        e1.setActive(true);
        e1.setHireDate(new Date());

        Q4_5PP e2 = new Q4_5PP("E101", "Jane", "Smith", 60000, "HR", new Date(), true);

        System.out.println(e1.getFullName());
        System.out.println(e1.getYearsOfService());
        System.out.println(e2.getFormattedSalary());
    }
}
