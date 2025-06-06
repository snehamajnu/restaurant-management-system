package restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

// Database Helper Class
class DatabaseHelper {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USERNAME = "system";
    private static final String DB_PASSWORD = "msc";
    
    public static void fetchData(String query, int columnCount, DefaultTableModel model) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    row[j] = rs.getObject(j + 1);
                }
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void insertStaff(String staffId, String name, String work) {
        String query = "INSERT INTO Staff (staff_id, staff_name, work) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, staffId);
            pstmt.setString(2, name);
            pstmt.setString(3, work);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void insertTable(String tableId, String tableNo, String capacity, String status) {
        String query = "INSERT INTO Tables (table_id, table_no, capacity, t_status) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, tableId);
            pstmt.setString(2, tableNo);
            pstmt.setString(3, capacity);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void deleteStaff(String staffName) {
        String query = "DELETE FROM Staff WHERE staff_name = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, staffName);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void insertSmartAppliance(String applianceId, String name, String type, String status, String lastMaintenance) {
        String query = "INSERT INTO SmartAppliances (appliance_id, appliance_name, type, app_status, last_maintenance_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, applianceId);
            pstmt.setString(2, name);
            pstmt.setString(3, type);
            pstmt.setString(4, status);
            pstmt.setString(5, lastMaintenance);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void updateTableStatus(String tableNo, String status) {
        String query = "UPDATE Tables SET t_status = ? WHERE table_no = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setString(2, tableNo);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

