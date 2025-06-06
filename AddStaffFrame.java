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

class AddStaffFrame extends JFrame {
    public AddStaffFrame() {
        setTitle("Add Staff");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));
        
        JLabel lblId = new JLabel("Staff ID:");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblWork = new JLabel("Work:");
        JTextField txtWork = new JTextField();
        JButton btnAdd = new JButton("Add Staff");
        
        btnAdd.addActionListener(e -> {
            String id = txtId.getText();
            String name = txtName.getText();
            String work = txtWork.getText();
            DatabaseHelper.insertStaff(id, name, work);
            JOptionPane.showMessageDialog(this, "Staff Added Successfully!");
            dispose();
        });
        
        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblWork);
        add(txtWork);
        add(btnAdd);
    }
}

