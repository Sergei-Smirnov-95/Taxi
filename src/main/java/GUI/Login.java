package GUI;

import Exceptions.DBAccessException;
import Exceptions.DBConnectionException;
import FACADE.Facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField login;
    private JPasswordField password;
    private JButton exitButton;
    private JButton signInButton;
    private JPanel loginPanel;

    private Facade facade;
    public Login() {
        /*
        setContentPane(loginPanel);
        Dimension size = new Dimension(200,50);
        setSize(size);
        setMinimumSize(size);
        setLocationRelativeTo(null);
        setMaximumSize(size);*/
        setListeners();
        //setVisible(true);

    }

    private void setListeners(){
        Login thisFrame = this;
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    facade = Facade.getInstance();
                } catch (DBAccessException | DBConnectionException e) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Cant connect with DB", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String userLogin = login.getText();
                String userPass  = new String (password.getPassword());
                String userType;
                if(userLogin.isEmpty() || userPass.isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Please, enter your login and password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                /*try {
                    userType = facade.authenticate(userLogin, userPass);
                    switch (userType){
                        case "Regular":
                            thisFrame.dispose();
                            RegularUser ruForm = new RegularUser(userLogin);
                            ruForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            break;
                        case "Operator":
                            thisFrame.dispose();
                            OperatorForm operatorForm = new OperatorForm(userLogin);
                            operatorForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            break;
                        case "Medic":
                            thisFrame.dispose();
                            MedicForm medicForm = new MedicForm(userLogin);
                            medicForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            break;
                        default:
                            Main.allertDialog("Incorrect user type");
                    }
                } catch (DBConnectionException e) {
                    Main.allertDialog(e.toString());
                } catch (AuthentificationException e) {
                    Main.allertDialog(e.toString());
                }*/
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /*Container frame = exitButton.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();*/
            }
        });
    }


}
