import java.time.LocalDate;
import java.util.*;

/**
 * Q1_5AP - Hospital Management System (single-file)
 * Drop into src as Q1_5AP.java and run.
 */
public class Q1_5AP {
    public static void main(String[] args) {
        System.out.println("=== Q1_5AP: Hospital Management System Demo ===");

        MedicalRecord mr = new MedicalRecord(
                "REC-001",
                "ATCG-XXXX",
                new String[]{"Peanuts", "Penicillin"},
                new String[]{"Asthma"},
                LocalDate.of(1990, 5, 12),
                "O+"
        );

        Patient p = new Patient(UUID.randomUUID().toString(), mr, "Ritika Sharma", "9999999999", "InsureX", 101, "Dr. Singh");
        Doctor d = new Doctor("LIC-12345", "Cardiology", new HashSet<>(Arrays.asList("Cardio-Board")));
        Nurse n = new Nurse("NUR-01", "Night", Arrays.asList("Pediatric Certification"));
        Administrator a = new Administrator("ADM-1", Arrays.asList("ALL"));

        HospitalSystem hs = new HospitalSystem();
        boolean admitted = hs.admitPatient(p, d);
        System.out.println("Admit attempt (doctor): " + admitted);
        System.out.println("Patient public info: " + p.getPublicInfo());
        System.out.println("Patient allergy to peanuts? " + (mr.isAllergicTo("Peanuts") ? "Yes" : "No"));
    }

    // Immutable medical record
    static final class MedicalRecord {
        private final String recordId;
        private final String patientDNA;
        private final String[] allergies;
        private final String[] medicalHistory;
        private final LocalDate birthDate;
        private final String bloodType;

        public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory, LocalDate birthDate, String bloodType) {
            this.recordId = recordId;
            this.patientDNA = patientDNA;
            this.allergies = Arrays.copyOf(allergies, allergies.length);
            this.medicalHistory = Arrays.copyOf(medicalHistory, medicalHistory.length);
            this.birthDate = birthDate;
            this.bloodType = bloodType;
        }

        public String getRecordId() { return recordId; }
        public String getPatientDNA() { return patientDNA; }
        public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
        public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }
        public LocalDate getBirthDate() { return birthDate; }
        public String getBloodType() { return bloodType; }

        public final boolean isAllergicTo(String substance) {
            for (String allergy : allergies) {
                if (allergy.equalsIgnoreCase(substance)) return true;
            }
            return false;
        }
    }

    // Patient with controlled mutators
    static class Patient {
        private final String patientId;
        private final MedicalRecord medicalRecord;
        private String currentName;
        private String emergencyContact;
        private String insuranceInfo;
        private int roomNumber;
        private String attendingPhysician;

        Patient(String patientId, MedicalRecord medicalRecord) {
            this.patientId = patientId;
            this.medicalRecord = medicalRecord;
        }

        Patient(String currentName, String emergencyContact) {
            this.patientId = UUID.randomUUID().toString();
            this.medicalRecord = null;
            this.currentName = currentName;
            this.emergencyContact = emergencyContact;
        }

        Patient(String patientId, MedicalRecord medicalRecord, String currentName, String emergencyContact, String insuranceInfo, int roomNumber, String attendingPhysician) {
            this.patientId = patientId;
            this.medicalRecord = medicalRecord;
            this.currentName = currentName;
            this.emergencyContact = emergencyContact;
            this.insuranceInfo = insuranceInfo;
            this.roomNumber = roomNumber;
            this.attendingPhysician = attendingPhysician;
        }

        String getBasicInfo() { return currentName + ", Room: " + roomNumber; }
        public String getPublicInfo() { return currentName + ", Room: " + roomNumber; }

        public String getPatientId() { return patientId; }
        public MedicalRecord getMedicalRecord() { return medicalRecord; }
        public String getCurrentName() { return currentName; }
        public String getEmergencyContact() { return emergencyContact; }
        public String getInsuranceInfo() { return insuranceInfo; }
        public int getRoomNumber() { return roomNumber; }
        public String getAttendingPhysician() { return attendingPhysician; }

        public void setCurrentName(String name) { this.currentName = name; }
        public void setEmergencyContact(String contact) { this.emergencyContact = contact; }
        public void setInsuranceInfo(String info) { this.insuranceInfo = info; }
        public void setRoomNumber(int room) { this.roomNumber = room; }
        public void setAttendingPhysician(String physician) { this.attendingPhysician = physician; }

        @Override
        public String toString() {
            return "Patient: " + patientId + ", Name: " + currentName + ", Room: " + roomNumber;
        }
    }

    static class Doctor {
        private final String licenseNumber;
        private final String specialty;
        private final Set<String> certifications;

        Doctor(String licenseNumber, String specialty, Set<String> certifications) {
            this.licenseNumber = licenseNumber;
            this.specialty = specialty;
            this.certifications = new HashSet<>(certifications);
        }

        public String getLicenseNumber() { return licenseNumber; }
        public String getSpecialty() { return specialty; }
        public Set<String> getCertifications() { return new HashSet<>(certifications); }
    }

    static class Nurse {
        private final String nurseId;
        private final String shift;
        private final List<String> qualifications;

        Nurse(String nurseId, String shift, List<String> qualifications) {
            this.nurseId = nurseId;
            this.shift = shift;
            this.qualifications = new ArrayList<>(qualifications);
        }

        public String getNurseId() { return nurseId; }
        public String getShift() { return shift; }
        public List<String> getQualifications() { return new ArrayList<>(qualifications); }
    }

    static class Administrator {
        private final String adminId;
        private final List<String> accessPermissions;

        Administrator(String adminId, List<String> accessPermissions) {
            this.adminId = adminId;
            this.accessPermissions = new ArrayList<>(accessPermissions);
        }

        public String getAdminId() { return adminId; }
        public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }
    }

    static class HospitalSystem {
        private final Map<String, Object> patientRegistry = new HashMap<>();
        public static final String POLICY_PRIVACY = "STRICT_PRIVACY";
        public static final String RULES_HIPAA = "HIPAA_COMPLIANT";

        public boolean admitPatient(Object patient, Object staff) {
            if (validateStaffAccess(staff, patient)) {
                patientRegistry.put(UUID.randomUUID().toString(), patient);
                return true;
            }
            return false;
        }

        private boolean validateStaffAccess(Object staff, Object patient) {
            return (staff instanceof Doctor || staff instanceof Nurse || staff instanceof Administrator);
        }

        void internalMethod() { /* internal functionality */ }
    }
}
