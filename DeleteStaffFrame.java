package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class DeleteStaffFrame extends JFrame {
    public DeleteStaffFrame() {
        setTitle("Delete Staff");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 10, 10));

        JLabel lblName = new JLabel("Enter Staff Name:");
        JTextField txtName = new JTextField();
        JButton btnDelete = new JButton("Delete");
        JButton btnBack = new JButton("Back");

        add(lblName);
        add(txtName);
        add(btnDelete);
        add(btnBack);

        btnDelete.addActionListener(e -> {
            String staffName = txtName.getText().trim();
            if (!staffName.isEmpty()) {
                deleteStaff(staffName);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid staff name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnBack.addActionListener(e -> dispose());
    }

    private void deleteStaff(String staffName) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";
        String query = "DELETE FROM Staff WHERE staff_name = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                 PreparedStatement pstmt = con.prepareStatement(query)) {

                pstmt.setString(1, staffName);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Staff deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No staff found with that name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}

