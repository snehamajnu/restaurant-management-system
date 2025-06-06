package restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Restaurant Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(93, 62, 80, 14);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(93, 114, 80, 14);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(200, 59, 130, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 111, 130, 20);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        btnLogin.setBounds(160, 176, 89, 23);
        contentPane.add(btnLogin);
    }

    private void authenticateUser() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";

        // Query to check username, password, and fetch user_type
        String query = "SELECT login_password, user_type FROM login WHERE user_name = ? AND login_password = ?";

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, username);
                pst.setString(2, password);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String userType = rs.getString("user_type").trim();

                        JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Navigate to different panels based on user_type
                        if ("manager".equalsIgnoreCase(userType)) {
                            ManagerPanel managerPanel = new ManagerPanel();
                            managerPanel.setVisible(true);
                        } else {
                            EmployeePanel employeePanel = new EmployeePanel();
                            employeePanel.setVisible(true);
                        }

                        this.dispose(); // Close login window
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + sqle.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(this, "Oracle JDBC Driver not found: " + cnfe.getMessage(), "Driver Error", JOptionPane.ERROR_MESSAGE);
            cnfe.printStackTrace();
        }
    }
}
