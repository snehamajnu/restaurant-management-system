package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class AddUsersFrame extends JFrame {
    public AddUsersFrame() {
        setTitle("Add New User");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblUserName = new JLabel("User Name:");
        JTextField txtUserName = new JTextField();
        JLabel lblUserType = new JLabel("User Type:");
        JTextField txtUserType = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnAddUser = new JButton("Add User");

        btnAddUser.addActionListener(e -> {
            String userName = txtUserName.getText();
            String userType = txtUserType.getText();
            String password = new String(txtPassword.getPassword());
            
            addUserToDatabase(userName, userType, password);
        });

        add(lblUserName);
        add(txtUserName);
        add(lblUserType);
        add(txtUserType);
        add(lblPassword);
        add(txtPassword);
        add(btnAddUser);
    }

    private void addUserToDatabase(String userName, String userType, String password) {
        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";
        String query = "INSERT INTO Login (user_name, user_type, login_password) VALUES (?, ?, ?)";
        
        try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, userType);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User Added Successfully!");
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

