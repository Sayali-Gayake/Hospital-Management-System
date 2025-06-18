

package com.zensar.services;

import com.zensar.model.Patient;
import com.zensar.dbconnection.DBConnection;

import java.sql.*;

public class PatientServiceImpl implements PatientService {

    public boolean addPatient(Patient patient) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO patients (name, age, gender, diagnosis) VALUES (?, ?, ?, ?)");
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getDiagnosis());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePatient(Patient patient) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE patients SET name = ?, age = ?, gender = ?, diagnosis = ? WHERE patientId = ?");
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getDiagnosis());
            stmt.setInt(5, patient.getPatientId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deletePatient(int patientId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt1 = connection.prepareStatement("DELETE FROM appointments WHERE patientId = ?");
            stmt1.setInt(1, patientId);
            stmt1.executeUpdate();
            
            PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM patients WHERE patientId = ?");
            stmt2.setInt(1, patientId);
            return stmt2.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Patient getPatient(int patientId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM patients WHERE patientId = ?");
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("patientId"), rs.getString("name"), rs.getInt("age"), rs.getString("gender"), rs.getString("diagnosis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

