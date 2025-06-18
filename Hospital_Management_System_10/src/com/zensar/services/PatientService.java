package com.zensar.services;

import com.zensar.model.Patient;

public interface PatientService {
    boolean addPatient(Patient patient);
    boolean updatePatient(Patient patient);
    boolean deletePatient(int patientId);
    Patient getPatient(int patientId);
}
