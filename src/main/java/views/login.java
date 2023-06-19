package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame{
    private JPanel mainPanel;
    public JButton cancelBtn;
    public JButton cardBtn;
    public JButton enterBtn;
    public JPasswordField passwordField;
    public JTextField usernameField;
    private JLabel labelIcon;
    public JButton createBtn;
    private Point mouseClickPoint;

    public login(){

        setContentPane(mainPanel);
        setSize(380,500);
        setMinimumSize(new Dimension(300,225));
        setLocationRelativeTo(null);
        setUndecorated(true);

        dragControl();
        ImageIcon image = new ImageIcon("src/main/java/views/logo.png");
        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(275/2,320/2, Image.SCALE_DEFAULT)
        );
        labelIcon.setIcon(icon);
        labelIcon.repaint();
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
