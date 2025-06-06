package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class AddDishFrame extends JFrame {
    private JTextField txtDishId, txtDishName, txtDishPrice;

    public AddDishFrame() {
        setTitle("Add Dish");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Dish ID:"));
        txtDishId = new JTextField();
        add(txtDishId);

        add(new JLabel("Dish Name:"));
        txtDishName = new JTextField();
        add(txtDishName);

        add(new JLabel("Dish Price:"));
        txtDishPrice = new JTextField();
        add(txtDishPrice);

        JButton btnSubmit = new JButton("Add Dish");
        btnSubmit.addActionListener(e -> insertDish());
        add(btnSubmit);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);
    }

    private void insertDish() {
        String dishId = txtDishId.getText().trim();
        String dishName = txtDishName.getText().trim();
        String dishPrice = txtDishPrice.getText().trim();

        if (dishId.isEmpty() || dishName.isEmpty() || dishPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";
        String query = "INSERT INTO menu (dish_id, dish_name, dish_price) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, Integer.parseInt(dishId));
            pst.setString(2, dishName);
            pst.setDouble(3, Double.parseDouble(dishPrice));
            
            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Dish added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
