package com.zensar.services;

import com.zensar.model.Appointment;
import com.zensar.dbconnection.DBConnection;

import java.sql.*;

public class AppointmentServiceImpl implements AppointmentService {

    public boolean addAppointment(Appointment appointment) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO appointments (doctorId, patientId, appointmentDateTime, reason, status) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, appointment.getDoctorId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setString(3, appointment.getAppointmentDateTime());
            stmt.setString(4, appointment.getReason());
            stmt.setString(5, appointment.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAppointment(Appointment appointment) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE appointments SET doctorId = ?, patientId = ?, appointmentDateTime = ?, reason = ?, status = ? WHERE appointmentId = ?");
            stmt.setInt(1, appointment.getDoctorId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setString(3, appointment.getAppointmentDateTime());
            stmt.setString(4, appointment.getReason());
            stmt.setString(5, appointment.getStatus());
            stmt.setInt(6, appointment.getAppointmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAppointment(int appointmentId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM appointments WHERE appointmentId = ?");
            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Appointment getAppointment(int appointmentId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM appointments WHERE appointmentId = ?");
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int doctorId = rs.getInt("doctorId");
                int patientId = rs.getInt("patientId");
                String appointmentDateTime = rs.getString("appointmentDateTime");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                return new Appointment(appointmentId, doctorId, patientId, appointmentDateTime, reason, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Appointment[] getDoctorAppointments(int doctorId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getDBConnection();
            stmt = connection.prepareStatement("SELECT * FROM appointments WHERE doctorId = ?");
            stmt.setInt(1, doctorId);
            rs = stmt.executeQuery();
            
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            Appointment[] appointments = new Appointment[size];
            int index = 0;
            
            while (rs.next()) {
                int appointmentId = rs.getInt("appointmentId");
                int patientId = rs.getInt("patientId");
                String appointmentDateTime = rs.getString("appointmentDateTime");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                
                appointments[index++] = new Appointment(appointmentId, doctorId, patientId, appointmentDateTime, reason, status);
            }
            
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Appointment[0];
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Appointment[] getAllAppointments() {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getDBConnection();
            stmt = connection.prepareStatement("SELECT * FROM appointments");
            rs = stmt.executeQuery();
            
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            Appointment[] appointments = new Appointment[size];
            int index = 0;
            
            while (rs.next()) {
                int appointmentId = rs.getInt("appointmentId");
                int doctorId = rs.getInt("doctorId");
                int patientId = rs.getInt("patientId");
                String appointmentDateTime = rs.getString("appointmentDateTime");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                
                appointments[index++] = new Appointment(appointmentId, doctorId, patientId, appointmentDateTime, reason, status);
            }
            
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Appointment[0];
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Appointment[] getPatientAppointments(int patientId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getDBConnection();
            stmt = connection.prepareStatement("SELECT * FROM appointments WHERE patientId = ?");
            stmt.setInt(1, patientId);
            rs = stmt.executeQuery();
            
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            Appointment[] appointments = new Appointment[size];
            int index = 0;
            
            while (rs.next()) {
                int appointmentId = rs.getInt("appointmentId");
                int doctorId = rs.getInt("doctorId");
                String appointmentDateTime = rs.getString("appointmentDateTime");
                String reason = rs.getString("reason");
                String status = rs.getString("status");
                
                appointments[index++] = new Appointment(appointmentId, doctorId, patientId, appointmentDateTime, reason, status);
            }
            
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Appointment[0];
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
