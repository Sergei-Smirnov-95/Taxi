package GUI;

import BusinessLogic.Passenger;
import Exceptions.DBAccessException;
import Exceptions.DBConnectionException;
import Exceptions.HaveNotUserEx;
import FACADE.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.time.LocalDate;

public class PassengerForm extends JFrame {
    private JButton newOrderButton;
    private JTextField sourceAddresTextField;
    private JTextField destinationAddresTextField;
    private JButton exitButton;
    private JPanel rootPanel;
    private Facade facade;

    public PassengerForm(String userLogin) {

        setContentPane(rootPanel);
        Dimension size = new Dimension(400,300);
        setSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(null);
        setMaximumSize(size);
        setListeners(userLogin);
        setVisible(true);


    }
    public void setListeners(String userLogin){

        JFrame thisFrame = this;
        try {
            facade = Facade.getInstance();
        } catch (DBAccessException | DBConnectionException e) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Cant connect with DB", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sourceAddr = sourceAddresTextField.getText();
                String destAddr = destinationAddresTextField.getText();
                if(sourceAddr.isEmpty() || destAddr.isEmpty() ||
                        sourceAddr.equals("Source Addres") || destAddr.equals("Destination Addres")){
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Please, enter data about order", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    facade.addNewOrder(sourceAddr,destAddr, userLogin, LocalDate.now());
                } catch (DBConnectionException e) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Cant connect with DB", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (HaveNotUserEx haveNotUserEx) {

                        Container frame = exitButton.getParent();
                        do
                            frame = frame.getParent();
                        while (!(frame instanceof JFrame));
                        ((JFrame) frame).dispose();

                }
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
    }

}
