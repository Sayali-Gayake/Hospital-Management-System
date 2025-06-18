package com.zensar.services;

import com.zensar.model.Doctor;
import com.zensar.dbconnection.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    public boolean addDoctor(Doctor doctor) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO doctors (name, specialization) VALUES (?, ?)");
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDoctor(Doctor doctor) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE doctors SET name = ?, specialization = ? WHERE doctorId = ?");
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setInt(3, doctor.getDoctorId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDoctor(int doctorId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM doctors WHERE doctorId = ?");
            stmt.setInt(1, doctorId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Doctor getDoctor(int doctorId) {
        try {
            Connection connection = DBConnection.getDBConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM doctors WHERE doctorId = ?");
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getInt("doctorId"), rs.getString("name"), rs.getString("specialization"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Doctor[] getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM doctors";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int doctorId = rs.getInt("doctorId");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");

                Doctor doctor = new Doctor(doctorId, name, specialization);
                doctorList.add(doctor);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Doctor[] doctorsArray = new Doctor[doctorList.size()];
        doctorList.toArray(doctorsArray);
        return doctorsArray;
    }
}
