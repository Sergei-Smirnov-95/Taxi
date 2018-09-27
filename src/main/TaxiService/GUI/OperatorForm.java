package GUI;

import BusinessLogic.Order;
import Exceptions.DBAccessException;
import Exceptions.DBConnectionException;
import Exceptions.HaveNotOrderEx;
import Exceptions.HaveNotUserEx;
import FACADE.Facade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperatorForm extends JFrame {

    private JTable Orders;
    private JButton AppointButton;
    private JButton declineButton;
    private JTable Drivers;
    private JButton exitButton;
    private JPanel rootPanel;
    private Facade facade;
    private String login;
    private int selectedOrders;
    private int selectedDrivers;

    public OperatorForm(String userLogin){

        login = userLogin;
        try {
            facade = Facade.getInstance();
        } catch (DBConnectionException | DBAccessException ex) {
            JOptionPane.showMessageDialog(new JFrame(),
                    ex.toString(),  "Allert",
                    JOptionPane.WARNING_MESSAGE);
        }

        this.setContentPane(rootPanel);
        this.setLocationRelativeTo(null);
        Dimension size = new Dimension(400, 200);
        this.setSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        Drivers.setModel(new DefaultTableModel());
        Orders.setModel(new DefaultTableModel());
        setVisible(true);
        initHandlers();
        populateTables();
    }

    void initHandlers(){
        OperatorForm thisFrame =  this;
        AppointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*try {
                    facade.acceptRequest(selectedRequest, login);
                    populateTables();
                    //for()
                    /*decline other, populate*/
                /*} catch ( DBConnectionException  e) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            e.toString(),  "Cannot connect to DB",
                            JOptionPane.WARNING_MESSAGE);
                }

*/
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thisFrame.dispose();
                Login login = new Login();
                login.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        Drivers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int row = Drivers.rowAtPoint(mouseEvent.getPoint());
                int tempSelectedDriver = (int) Drivers.getModel().getValueAt(row, 0);
                if(tempSelectedDriver>0){
                    selectedDrivers = tempSelectedDriver;
                    if(selectedOrders>0) {
                        AppointButton.setEnabled(true);
                    }
                } else {
                    AppointButton.setEnabled(false);
                }


            }
        });
        Orders.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int row = Orders.rowAtPoint(mouseEvent.getPoint());
                int tempSelectedOrders = (int) Orders.getModel().getValueAt(row, 0);
                if(tempSelectedOrders>0){
                    selectedOrders = tempSelectedOrders;
                    if(selectedDrivers>0) {
                        AppointButton.setEnabled(true);
                    }
                } else {
                    AppointButton.setEnabled(false);
                }
            }
        });


    }

    private void populateTables(){
        //table new orders
        DefaultTableModel model = (DefaultTableModel) NewOrders.getModel();
        model.setRowCount(0);
        model.setColumnCount(3);
        Object[] cols = new Object[]{"id", "SourceAddr", "DestAddr"};
        model.setColumnIdentifiers(cols);
        try {

            java.util.List<Order> orlist= facade.getAppointedOrdersByDriver(login);
            for(Order or : orlist) {
                model.addRow(new Object[]{or.getOrderId(),or.getSourceAddress(),or.getDestinationAddress()});
            }

        } catch (HaveNotOrderEx ex)
        {
            model.addRow(new Object[]{"-","-","-"});
        }
        catch (HaveNotUserEx | DBConnectionException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Havent user with this login or DB connection", "Error",
                    JOptionPane.ERROR_MESSAGE);
            Container frame = exitButton.getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();
        }
        NewOrders.setModel(model);
        NewOrders.repaint();
        //table old orders
        DefaultTableModel modeltab = (DefaultTableModel) Orders.getModel();
        modeltab.setRowCount(0);
        modeltab.setColumnCount(3);
        modeltab.setColumnIdentifiers(cols);
        try {

            java.util.List<Order> orlist= facade.getOrdersByDriver(login);
            for(Order or : orlist) {
                modeltab.addRow(new Object[]{or.getOrderId(),or.getSourceAddress(),or.getDestinationAddress()});
            }

        } catch (HaveNotOrderEx ex)
        {
            modeltab.addRow(new Object[]{"-","-","-"});
        }
        catch (HaveNotUserEx | DBConnectionException ex)
        {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Havent user with this login or DB connection", "Error",
                    JOptionPane.ERROR_MESSAGE);
            Container frame = exitButton.getParent();
            do
                frame = frame.getParent();
            while (!(frame instanceof JFrame));
            ((JFrame) frame).dispose();
        }
        Orders.setModel(modeltab);
        Orders.repaint();
    }


}
