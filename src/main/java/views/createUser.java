package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class createUser extends JFrame {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JTextField nameField;
    public JTextField lastnameField;
    public JTextField phoneField;
    public JTextField emailField;
    public JSpinner ageField;
    private JPanel mainPanel;
    public JButton aceptBtn;
    public JButton cancelBtn;
    private Point mouseClickPoint;

    public createUser (){
        setContentPane(mainPanel);
        setSize(380,500);
        setMinimumSize(new Dimension(380,500));
        setLocationRelativeTo(null);
        dragControl();
    }

    private void dragControl(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickPoint = e.getPoint();
            }

        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint = e.getLocationOnScreen();
                newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y);
                setLocation(newPoint);
            }
        });
    }
}
