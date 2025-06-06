package restaurant;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
class ShowSmartAppliancesFrame extends JFrame {
    public ShowSmartAppliancesFrame() {
        setTitle("Smart Appliances");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"appliance_id", "appliance_name", "type", "app_status", "last_maintenance_date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> dispose());
        add(btnBack, BorderLayout.SOUTH);
        
        loadSmartAppliances(model);
    }

    private void loadSmartAppliances(DefaultTableModel model) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";
        String query = "SELECT * FROM SmartAppliances";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("appliance_id"),
                        rs.getString("appliance_name"),
                        rs.getString("type"),
                        rs.getString("app_status"),
                        rs.getString("last_maintenance_date")
                    });
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}

