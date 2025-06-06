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
class EditTableStatusFrame extends JFrame {
    public EditTableStatusFrame() {
        setTitle("Edit Table Status");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblTableNo = new JLabel("Table No:");
        JTextField txtTableNo = new JTextField();
        JLabel lblStatus = new JLabel("New Status (Available/Occupied/Reserved):");
        JTextField txtStatus = new JTextField();
        JButton btnUpdate = new JButton("Update");

        btnUpdate.addActionListener(e -> {
            String tableNo = txtTableNo.getText().trim();
            String status = txtStatus.getText().trim();
            if (!tableNo.isEmpty() && (status.equals("Available") || status.equals("Occupied") || status.equals("Reserved"))) {
                DatabaseHelper.updateTableStatus(tableNo, status);
                JOptionPane.showMessageDialog(this, "Table status updated successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid table number and status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(lblTableNo);
        add(txtTableNo);
        add(lblStatus);
        add(txtStatus);
        add(btnUpdate);
    }
}

