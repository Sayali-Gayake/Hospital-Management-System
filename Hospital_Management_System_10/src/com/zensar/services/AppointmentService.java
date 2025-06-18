package com.zensar.services;

import com.zensar.model.Appointment;

public interface AppointmentService {
    boolean addAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);
    Appointment getAppointment(int appointmentId);
    public Appointment[] getDoctorAppointments(int doctorId);
}