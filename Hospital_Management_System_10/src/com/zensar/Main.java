package com.zensar;

import com.zensar.model.Appointment;
import com.zensar.model.Doctor;
import com.zensar.model.Patient;
import com.zensar.services.AppointmentServiceImpl;
import com.zensar.services.DoctorServiceImpl;
import com.zensar.services.PatientServiceImpl;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DoctorServiceImpl doctorService = new DoctorServiceImpl();
    private static PatientServiceImpl patientService = new PatientServiceImpl();
    private static AppointmentServiceImpl appointmentService = new AppointmentServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Hospital Management System!");
            System.out.println("Please select your role:");
            System.out.println("1. Doctor");
            System.out.println("2. Patient");
            System.out.println("3. Administrator");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            
            switch (choice) {
                case 1: // Doctor
                    doctorMenu();
                    break;
                case 2: // Patient
                    patientMenu();
                    break;
                case 3: // Administrator
                    administratorMenu();
                    break;
                case 4: // Exit
                    System.out.println("Exiting the application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Doctor menu
    private static void doctorMenu() {
        System.out.println("You are logged in as a Doctor.");
        System.out.println("1. View my appointments");
        System.out.println("2. View patient details");
        System.out.println("3. Logout");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        
        switch (choice) {
            case 1:
                viewDoctorAppointments();
                break;
            case 2:
                viewPatients();
                break;
            case 3:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewDoctorAppointments() {
        System.out.print("Enter your doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Appointment appointment : appointmentService.getDoctorAppointments(doctorId)) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Date and Time: " + appointment.getAppointmentDateTime());
            System.out.println("Reason: " + appointment.getReason());
            System.out.println("Status: " + appointment.getStatus());
            System.out.println("---------------");
        }
    }

    private static void viewPatients() {
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patient patient = patientService.getPatient(patientId);
        if (patient != null) {
            System.out.println("Patient ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Diagnosis: " + patient.getDiagnosis());
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Patient menu
    private static void patientMenu() {
        System.out.println("You are logged in as a Patient.");
        System.out.println("1. View my appointments");
        System.out.println("2. View available doctors");
        System.out.println("3. Book an appointment");
        System.out.println("4. Logout");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        
        switch (choice) {
            case 1:
                viewPatientAppointments();
                break;
            case 2:
                viewAvailableDoctors();
                break;
            case 3:
                bookAppointment();
                break;
            case 4:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewPatientAppointments() {
        System.out.print("Enter your patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Appointment appointment : appointmentService.getPatientAppointments(patientId)) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("Date and Time: " + appointment.getAppointmentDateTime());
            System.out.println("Reason: " + appointment.getReason());
            System.out.println("Status: " + appointment.getStatus());
            System.out.println("---------------");
        }
    }

    private static void viewAvailableDoctors() {
        for (Doctor doctor : doctorService.getAllDoctors()) {
            System.out.println("Doctor ID: " + doctor.getDoctorId());
            System.out.println("Name: " + doctor.getName());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println("---------------");
        }
    }

    private static void bookAppointment() {
        System.out.print("Enter your patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter appointment date and time (YYYY-MM-DD HH:MM:SS): ");
        String appointmentDateTime = scanner.nextLine();
        System.out.print("Enter the reason for the appointment: ");
        String reason = scanner.nextLine();
        
        Appointment appointment = new Appointment(0, doctorId, patientId, appointmentDateTime, reason, "Scheduled");
        if (appointmentService.addAppointment(appointment)) {
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Failed to book appointment.");
        }
    }

    // Administrator menu
    private static void administratorMenu() {
        System.out.println("You are logged in as an Administrator.");
        System.out.println("1. Add Patient");
        System.out.println("2. Update Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Add Doctor");
        System.out.println("5. Update Doctor");
        System.out.println("6. Delete Doctor");
        System.out.println("7. View Appointments");
        System.out.println("8. Logout");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        
        switch (choice) {
            case 1:
                addPatient();
                break;
            case 2:
                updatePatient();
                break;
            case 3:
                deletePatient();
                break;
            case 4:
                addDoctor();
                break;
            case 5:
                updateDoctor();
                break;
            case 6:
                deleteDoctor();
                break;
            case 7:
                viewAppointments();
                break;
            case 8:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = Integer.parseInt(scanner.nextLine()); // Read age as a string and then parse it to an integer
        System.out.print("Enter patient gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();
        
        Patient patient = new Patient(0, name, age, gender, diagnosis);
        if (patientService.addPatient(patient)) {
            System.out.println("Patient added successfully.");
        } else {
            System.out.println("Failed to add patient.");
        }
    }


    private static void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter updated patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter updated age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter updated gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter updated diagnosis: ");
        String diagnosis = scanner.nextLine();
        
        Patient patient = new Patient(patientId, name, age, gender, diagnosis);
        if (patientService.updatePatient(patient)) {
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Failed to update patient.");
        }
    }

    private static void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (patientService.deletePatient(patientId)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Failed to delete patient.");
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor specialization: ");
        String specialization = scanner.nextLine();
        
        Doctor doctor = new Doctor(0, name, specialization);
        if (doctorService.addDoctor(doctor)) {
            System.out.println("Doctor added successfully.");
        } else {
            System.out.println("Failed to add doctor.");
        }
    }


        private static void updateDoctor() {
            System.out.print("Enter doctor ID to update: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter updated doctor name: ");
            String name = scanner.nextLine();
            System.out.print("Enter updated specialization: ");
            String specialization = scanner.nextLine();

            Doctor doctor = new Doctor(doctorId, name, specialization);
            if (doctorService.updateDoctor(doctor)) {
                System.out.println("Doctor updated successfully.");
            } else {
                System.out.println("Failed to update doctor.");
            }
        }

        private static void deleteDoctor() {
            System.out.print("Enter doctor ID to delete: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (doctorService.deleteDoctor(doctorId)) {
                System.out.println("Doctor deleted successfully.");
            } else {
                System.out.println("Failed to delete doctor.");
            }
        }

        private static void viewAppointments() {
            for (Appointment appointment : appointmentService.getAllAppointments()) {
                System.out.println("Appointment ID: " + appointment.getAppointmentId());
                System.out.println("Doctor ID: " + appointment.getDoctorId());
                System.out.println("Patient ID: " + appointment.getPatientId());
                System.out.println("Date and Time: " + appointment.getAppointmentDateTime());
                System.out.println("Reason: " + appointment.getReason());
                System.out.println("Status: " + appointment.getStatus());
                System.out.println("---------------");
            }
        }
    }
