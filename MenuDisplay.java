package restaurant;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MenuDisplay extends JFrame {
    public MenuDisplay() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextArea menuTextArea = new JTextArea();
        menuTextArea.setEditable(false);
        add(new JScrollPane(menuTextArea), BorderLayout.CENTER);

        String query = "SELECT * FROM menu";
        try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "msc");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                menuTextArea.append(rs.getInt("dish_id") + " - " + rs.getString("dish_name") + " - ₹" + rs.getDouble("dish_price") + "\n");
            }
        } catch (SQLException ex) {
            menuTextArea.setText("Error loading menu: " + ex.getMessage());
        }
    }
}

