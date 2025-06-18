package com.zensar.services;

import com.zensar.model.Doctor;
import com.zensar.model.Patient;
import com.zensar.model.Appointment;

public interface DoctorService {
    boolean addDoctor(Doctor doctor);
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(int doctorId);
    Doctor getDoctor(int doctorId);
}



