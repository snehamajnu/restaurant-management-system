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
class AddSmartAppliancesFrame extends JFrame {
    public AddSmartAppliancesFrame() {
        setTitle("Add Smart Appliance");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));
        
        JLabel lblId = new JLabel("Appliance ID:");
        JTextField txtId = new JTextField();
        JLabel lblName = new JLabel("Appliance Name:");
        JTextField txtName = new JTextField();
        JLabel lblType = new JLabel("Type:");
        JTextField txtType = new JTextField();
        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField();
        JLabel lblMaintenance = new JLabel("Last Maintenance Date:");
        JTextField txtMaintenance = new JTextField();
        
        JButton btnAdd = new JButton("Add Appliance");
        btnAdd.addActionListener(e -> {
            DatabaseHelper.insertSmartAppliance(txtId.getText(), txtName.getText(), txtType.getText(), txtStatus.getText(), txtMaintenance.getText());
            JOptionPane.showMessageDialog(this, "Smart Appliance Added Successfully!");
            dispose();
        });
        
        add(lblId);
        add(txtId);
        add(lblName);
        add(txtName);
        add(lblType);
        add(txtType);
        add(lblStatus);
        add(txtStatus);
        add(lblMaintenance);
        add(txtMaintenance);
        add(btnAdd);
    }
}
