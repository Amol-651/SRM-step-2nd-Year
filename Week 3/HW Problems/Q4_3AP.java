/**
 * Q4_3AP.java
 * Assignment 4: Student Grade Management System
 * This program demonstrates data processing with static and instance members
 * to manage student grades, subjects, and school-wide reporting.
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    // --- Instance Members ---
    private String studentId;
    private String studentName;
    private String className;
    private Subject[] subjects;
    private double[][] marks; // 2D array: [subject_index][0] = mark
    private double gpa;

    // --- Static Members ---
    private static int totalStudents = 0;
    private static String schoolName = "Global Tech Academy";
    private static String[] gradingScale = {"A", "B", "C", "D", "F"};
    private static double[] gradeCutoffs = {90, 80, 70, 60, 0};
    private static double passPercentage = 60.0;

    private static int studentIdCounter = 0;

    // --- Constructor ---
    public Student(String studentName, String className, Subject[] subjects) {
        this.studentName = studentName;
        this.className = className;
        this.studentId = "S" + String.format("%03d", ++studentIdCounter);
        this.subjects = subjects;
        this.marks = new double[subjects.length][1];
        for (int i = 0; i < subjects.length; i++) {
            this.marks[i][0] = -1.0; // -1 indicates mark not yet added
        }
        totalStudents++;
    }

    // --- Getters ---
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }
    public double getGpa() { return gpa; }

    // --- Instance Methods ---
    public void addMarks(String subjectCode, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].subjectCode.equals(subjectCode)) {
                if (mark >= 0 && mark <= 100) {
                    marks[i][0] = mark;
                    System.out.printf("Mark %.1f added for %s for student %s.\n", mark, subjects[i].subjectName, studentName);
                } else {
                    System.out.println("Invalid mark for " + studentName + ". Must be between 0 and 100.");
                }
                return;
            }
        }
        System.out.println("Subject code " + subjectCode + " not found for " + studentName);
    }

    public String getGradeFromMark(double mark) {
        for (int i = 0; i < gradeCutoffs.length; i++) {
            if (mark >= gradeCutoffs[i]) {
                return gradingScale[i];
            }
        }
        return "F";
    }

    public void calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        boolean allMarksEntered = true;
        for(int i = 0; i < subjects.length; i++) {
            if(marks[i][0] == -1.0) {
                allMarksEntered = false;
                break;
            }
            double gradePoint = Math.max(0, (marks[i][0] / 10.0) - 5);
            if (marks[i][0] == 100) gradePoint = 5.0;

            totalPoints += gradePoint * subjects[i].credits;
            totalCredits += subjects[i].credits;
        }

        if (!allMarksEntered) {
            this.gpa = 0.0;
            return;
        }

        this.gpa = (totalCredits > 0) ? totalPoints / totalCredits : 0.0;
    }

    public void generateReportCard() {
        calculateGPA();
        System.out.println("\n--- Report Card for " + studentName + " (" + studentId + ") ---");
        System.out.println("Class: " + className);
        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-6s | %-6s\n", "Sub Code", "Subject Name", "Mark", "Grade");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-10s | %-20s | %-6s | %-6s\n",
                    subjects[i].subjectCode, subjects[i].subjectName,
                    (marks[i][0] == -1.0 ? "N/A" : String.format("%.1f", marks[i][0])),
                    (marks[i][0] == -1.0 ? "N/A" : getGradeFromMark(marks[i][0])));
        }
        System.out.println("-------------------------------------------------");
        System.out.printf("GPA: %.2f\n", this.gpa);
        System.out.println("Promotion Status: " + (checkPromotionEligibility() ? "Promoted" : "Not Promoted"));
    }

    public boolean checkPromotionEligibility() {
        for(double[] markArr : marks) {
            if (markArr[0] < passPercentage && markArr[0] != -1.0) return false;
        }
        return true;
    }

    // --- Static Methods ---
    public static void setSchoolName(String name) { schoolName = name; }
    public static String getSchoolName() { return schoolName; }
    public static int getTotalStudents() { return totalStudents; }
    public static void setGradingScale(String[] scale, double[] cutoffs) {
        gradingScale = scale;
        gradeCutoffs = cutoffs;
    }
}

public class Q4_3AP {
    // --- Static Reporting/Utility Methods for the School ---
    public static double calculateClassAverage(Student[] students, String className) {
        double totalGPA = 0;
        int studentCount = 0;
        for (Student s : students) {
            if (s != null && s.getClassName().equals(className)) {
                s.calculateGPA();
                if(s.getGpa() > 0) {
                    totalGPA += s.getGpa();
                    studentCount++;
                }
            }
        }
        return (studentCount > 0) ? totalGPA / studentCount : 0.0;
    }

    public static List<Student> getTopPerformers(Student[] students, int count) {
        List<Student> studentList = new ArrayList<>(Arrays.asList(students));
        studentList.removeIf(s -> s == null);

        studentList.forEach(Student::calculateGPA);

        studentList.sort(Comparator.comparingDouble(Student::getGpa).reversed());

        return studentList.subList(0, Math.min(count, studentList.size()));
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n====== " + Student.getSchoolName() + " School Report ======");
        System.out.println("Total Students: " + Student.getTotalStudents());
        System.out.printf("Class 10A Average GPA: %.2f\n", calculateClassAverage(students, "10A"));
        System.out.printf("Class 10B Average GPA: %.2f\n", calculateClassAverage(students, "10B"));

        System.out.println("\n--- Top 2 Performers Overall ---");
        List<Student> top = getTopPerformers(students, 2);
        for(Student s : top) {
            System.out.printf("- %s (GPA: %.2f)\n", s.getStudentName(), s.getGpa());
        }
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        Subject math = new Subject("M101", "Mathematics", 4, "Mr. Turing");
        Subject science = new Subject("S101", "Science", 4, "Mrs. Curie");
        Subject history = new Subject("H101", "History", 3, "Mr. Herodotus");
        Subject[] allSubjects = {math, science, history};

        Student[] students = new Student[3];
        students[0] = new Student("Alice", "10A", allSubjects);
        students[1] = new Student("Bob", "10A", allSubjects);
        students[2] = new Student("Charlie", "10B", allSubjects);

        System.out.println("--- Adding Marks ---");
        students[0].addMarks("M101", 95);
        students[0].addMarks("S101", 88);
        students[0].addMarks("H101", 92);

        students[1].addMarks("M101", 75);
        students[1].addMarks("S101", 65);
        students[1].addMarks("H101", 58);

        students[2].addMarks("M101", 89);
        students[2].addMarks("S101", 91);
        students[2].addMarks("H101", 85);

        students[0].generateReportCard();
        students[1].generateReportCard();
        students[2].generateReportCard();

        generateSchoolReport(students);
    }
}

