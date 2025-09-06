public class Q2_3PP {
    // Private attributes
    private String studentId;
    private String name;
    private double grade;
    private String course;

    // Default (no-argument) constructor
    public Q2_3PP() {}

    // Parameterized constructor
    public Q2_3PP(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    // Method to calculate the letter grade
    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    // Method to display student details
    public void displayStudent() {
        System.out.println("ID: " + studentId + ", Name: " + name + ", Course: " + course + ", " + "Grade: " + grade + ", Letter: " + calculateLetterGrade());
    }

    public static void main(String[] args) {
        // Create an object using the default constructor and setters
        Q2_3PP s1 = new Q2_3PP();
        s1.setStudentId("S001");
        s1.setName("Alice");
        s1.setGrade(87);
        s1.setCourse("Math");

        // Create an object using the parameterized constructor
        Q2_3PP s2 = new Q2_3PP("S002", "Bob", 92.5, "Physics");

        // Display initial details
        s1.displayStudent();
        s2.displayStudent();

        // Test getter and setter
        System.out.println("Bob's Grade (getter): " + s2.getGrade());
        s2.setGrade(85);
        System.out.println("Updated Grade: " + s2.getGrade());
        s2.displayStudent(); // Display again to show the updated letter grade
    }
}