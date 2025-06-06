package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class OrderEntryPanel extends JFrame {
    private JTextField txtOrderID, txtTableID, txtOrderTime, txtStatus, txtTotalPrice;

    public OrderEntryPanel() {
        setTitle("Insert Order");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Order ID:"));
        txtOrderID = new JTextField();
        add(txtOrderID);

        add(new JLabel("Table ID:"));
        txtTableID = new JTextField();
        add(txtTableID);

        

        add(new JLabel("Order Status:"));
        txtStatus = new JTextField();
        add(txtStatus);

        add(new JLabel("Total Price:"));
        txtTotalPrice = new JTextField();
        add(txtTotalPrice);

        JButton btnSubmit = new JButton("Submit Order");
        btnSubmit.addActionListener(e -> insertOrder());
        add(btnSubmit);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> dispose());
        add(btnBack);
    }

    private void insertOrder() {
        String orderID = txtOrderID.getText().trim();
        String tableID = txtTableID.getText().trim();
       
        String status = txtStatus.getText().trim();
        String totalPrice = txtTotalPrice.getText().trim();

        String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String dbUsername = "system";
        String dbPassword = "msc";

      
        String query = "INSERT INTO Orders(order_id, table_id, o_status, total_price) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, orderID);
                pst.setString(2, tableID);
                pst.setString(3, status);
                pst.setString(4, totalPrice);

                int rowsInserted = pst.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Order Inserted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }
}

