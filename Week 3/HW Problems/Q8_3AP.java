/**
 * Q8_3AP.java
 * Assignment 8: Hospital Patient Management System
 * An advanced system showing complex object relationships between Patient,
 * Doctor, and Appointment classes to manage hospital operations.
 */
import java.util.ArrayList;
import java.util.List;

class Patient {
    String patientId;
    String patientName;
    int age;
    String gender;
    List<String> medicalHistory;

    private static int patientIdCounter = 0;

    public Patient(String name, int age, String gender) {
        this.patientId = "P" + String.format("%04d", ++patientIdCounter);
        this.patientName = name;
        this.age = age;
        this.gender = gender;
        this.medicalHistory = new ArrayList<>();
        Q8_3AP.incrementTotalPatients();
    }

    public void addMedicalHistory(String entry) {
        medicalHistory.add(entry);
    }

    public void display() {
        System.out.printf("ID: %s, Name: %s, Age: %d, Gender: %s\n", patientId, patientName, age, gender);
    }
}

class Doctor {
    String doctorId;
    String doctorName;
    String specialization;
    double consultationFee;
    List<String> availableSlots;

    private static int doctorIdCounter = 0;

    public Doctor(String name, String specialization, double fee) {
        this.doctorId = "D" + String.format("%03d", ++doctorIdCounter);
        this.doctorName = name;
        this.specialization = specialization;
        this.consultationFee = fee;
        this.availableSlots = new ArrayList<>();
        availableSlots.add("09:00 AM");
        availableSlots.add("10:00 AM");
        availableSlots.add("11:00 AM");
    }

    public boolean bookSlot(String time) {
        return availableSlots.remove(time);
    }

    public void display() {
        System.out.printf("ID: %s, Name: Dr. %s, Specialization: %s, Fee: $%.2f\n", doctorId, doctorName, specialization, consultationFee);
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate;
    String appointmentTime;
    String status;
    String type;
    double billAmount;

    private static int appointmentIdCounter = 0;

    public Appointment(Patient patient, Doctor doctor, String date, String time, String type) {
        this.appointmentId = "A" + String.format("%05d", ++appointmentIdCounter);
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.type = type;
        this.status = "Scheduled";
        Q8_3AP.incrementTotalAppointments();
    }

    public void generateBill() {
        double baseFee = doctor.consultationFee;
        switch(type) {
            case "Follow-up": this.billAmount = baseFee * 0.5; break;
            case "Emergency": this.billAmount = baseFee * 1.5; break;
            case "Consultation":
            default: this.billAmount = baseFee; break;
        }
        Q8_3AP.addRevenue(this.billAmount);
        System.out.printf("Bill generated for Appointment %s. Amount: $%.2f\n", appointmentId, billAmount);
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        Q8_3AP.decrementTotalAppointments();
        System.out.println("Appointment " + appointmentId + " has been cancelled.");
    }

    public void display() {
        System.out.printf("ID: %s | Date: %s at %s | Patient: %s | Doctor: %s | Status: %s | Type: %s\n",
                appointmentId, appointmentDate, appointmentTime, patient.patientName, doctor.doctorName, status, type);
    }
}

public class Q8_3AP {
    private static int totalPatients = 0;
    private static int totalAppointments = 0;
    private static String hospitalName = "OOP General Hospital";
    private static double totalRevenue = 0;

    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    // --- Static Getters and Modifiers for Encapsulation ---
    public static int getTotalPatients() { return totalPatients; }
    public static int getTotalAppointments() { return totalAppointments; }
    public static String getHospitalName() { return hospitalName; }
    public static double getTotalRevenue() { return totalRevenue; }

    public static void incrementTotalPatients() { totalPatients++; }
    public static void incrementTotalAppointments() { totalAppointments++; }
    public static void decrementTotalAppointments() { totalAppointments--; }
    public static void addRevenue(double amount) { totalRevenue += amount; }


    public static Appointment scheduleAppointment(Patient p, Doctor d, String date, String time, String type) {
        System.out.printf("\nScheduling a %s appointment for %s with Dr. %s...\n", type, p.patientName, d.doctorName);
        if (d.bookSlot(time)) {
            Appointment appt = new Appointment(p, d, date, time, type);
            appointments.add(appt);
            p.addMedicalHistory("Appointment " + appt.appointmentId + " scheduled with Dr. " + d.doctorName + " on " + date);
            System.out.println("Success! Appointment scheduled.");
            return appt;
        } else {
            System.out.println("Failed. Dr. " + d.doctorName + " is not available at " + time + ".");
            return null;
        }
    }

    public static void generateHospitalReport() {
        System.out.println("\n========== " + getHospitalName() + " Report ==========");
        System.out.println("Total Patients Registered: " + getTotalPatients());
        System.out.println("Active Appointments: " + getTotalAppointments());
        System.out.printf("Total Revenue Generated: $%.2f\n", getTotalRevenue());
        System.out.println("==============================================");
    }

    public static void main(String[] args) {
        Doctor drHouse = new Doctor("House", "Cardiology", 150.00);
        Doctor drWilson = new Doctor("Wilson", "Oncology", 120.00);
        doctors.add(drHouse);
        doctors.add(drWilson);

        Patient john = new Patient("John Doe", 45, "Male");
        Patient jane = new Patient("Jane Smith", 35, "Female");
        patients.add(john);
        patients.add(jane);

        generateHospitalReport();

        Appointment appt1 = scheduleAppointment(john, drHouse, "2025-09-07", "10:00 AM", "Consultation");
        Appointment appt2 = scheduleAppointment(jane, drWilson, "2025-09-07", "10:00 AM", "Follow-up");
        scheduleAppointment(john, drWilson, "2025-09-07", "10:00 AM", "Consultation");

        System.out.println("\n--- Current Appointments ---");
        appointments.forEach(Appointment::display);

        System.out.println("\n--- Processing Bills ---");
        if (appt1 != null) appt1.generateBill();
        if (appt2 != null) appt2.generateBill();

        generateHospitalReport();

        System.out.println("\n--- Patient Medical History for John ---");
        john.medicalHistory.forEach(System.out::println);
    }
}

