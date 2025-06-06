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
class AddTableFrame extends JFrame {
    public AddTableFrame() {
        setTitle("Add Table");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        
        JLabel lblId = new JLabel("Table ID:");
        JTextField txtId = new JTextField();
        JLabel lblNo = new JLabel("Table No:");
        JTextField txtNo = new JTextField();
        JLabel lblCapacity = new JLabel("Capacity:");
        JTextField txtCapacity = new JTextField();
        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField();
        JButton btnAdd = new JButton("Add Table");
        
        btnAdd.addActionListener(e -> {
            String id = txtId.getText();
            String no = txtNo.getText();
            String capacity = txtCapacity.getText();
            String status = txtStatus.getText();
            DatabaseHelper.insertTable(id, no, capacity, status);
            JOptionPane.showMessageDialog(this, "Table Added Successfully!");
            dispose();
        });
        
        add(lblId);
        add(txtId);
        add(lblNo);
        add(txtNo);
        add(lblCapacity);
        add(txtCapacity);
        add(lblStatus);
        add(txtStatus);
        add(btnAdd);
    }
}
