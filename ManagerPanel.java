package restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Manager Panel
class ManagerPanel extends JFrame {
    public ManagerPanel() {
        setTitle("Manager Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        JButton btnShowOrders = new JButton("Show Orders");
        btnShowOrders.addActionListener(e -> new ShowOrdersFrame().setVisible(true));
        add(btnShowOrders);

        JButton btnShowStaffs = new JButton("Show Staffs");
        btnShowStaffs.addActionListener(e -> new ShowStaffFrame().setVisible(true));
        add(btnShowStaffs);
        
        JButton btnSmartAppliances = new JButton("Smart Appliances");
        btnSmartAppliances.addActionListener(e -> new ShowSmartAppliancesFrame().setVisible(true));
        add(btnSmartAppliances);
        
        
        JButton btnShowTableStatus = new JButton("Show Table Status");
        btnShowTableStatus.addActionListener(e -> new ShowTableStatusFrame().setVisible(true));
        add(btnShowTableStatus);
        
        JButton btnAddStaff = new JButton("Add Staffs");
        btnAddStaff.addActionListener(e -> new AddStaffFrame().setVisible(true));
        add(btnAddStaff);
        
        JButton btnAddTables = new JButton("Add Tables");
        btnAddTables.addActionListener(e -> new AddTableFrame().setVisible(true));
        add(btnAddTables);
        
        JButton btnDeleteStaff = new JButton("Delete Staff");
        btnDeleteStaff.addActionListener(e -> new DeleteStaffFrame().setVisible(true));
        add(btnDeleteStaff);

        
        JButton btnUpStatta=new JButton("Edit Table Status");
        btnUpStatta.addActionListener(e-> new EditTableStatusFrame().setVisible(true));
        add(btnUpStatta);
        
        JButton btnloginad=new JButton("Add New User");
        btnloginad.addActionListener(e-> new AddUsersFrame().setVisible(true));
        add(btnloginad);
        
        JButton btnSmartAp=new JButton("Add Smart Appliances");
        btnSmartAp.addActionListener(e-> new AddSmartAppliancesFrame().setVisible(true));
        add(btnSmartAp);
        
        JButton btnAddDish = new JButton("Add Dishes");
        btnAddDish.addActionListener(e -> new AddDishFrame().setVisible(true));
        add(btnAddDish);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });
        add(btnBack);
    }
        
        
}
