package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class EmployeePanel extends JFrame {
    public EmployeePanel() {
        setTitle("Employee Panel");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnInsertOrder = new JButton("Insert Orders");
        btnInsertOrder.addActionListener((ActionEvent e) -> new OrderEntryPanel().setVisible(true));
        add(btnInsertOrder);
        
        JButton btnViewMenu = new JButton("Show Menu");
        btnViewMenu.setBounds(90, 50, 120, 30);
        btnViewMenu.addActionListener((ActionEvent e) -> new MenuDisplay().setVisible(true));
        add(btnViewMenu);
        
        JButton btnShowOrders = new JButton("Show Orders");
        btnShowOrders.addActionListener((ActionEvent e) -> new ShowOrdersPanel().setVisible(true));
        add(btnShowOrders);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
        add(btnBack);
    }
}