package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class home extends JFrame{
    public JButton depositBtn;
    public JButton withdrawalBtn;
    private JPanel mainPanel;
    public JLabel usernameLabel;
    public JLabel contentLabel;
    public JLabel nameLabel;
    public JLabel lastnameLabel;
    public JLabel phoneLabel;
    public JLabel emailLabel;
    public JLabel ageLabel;
    private JLabel labelIcon;
    public JButton exitBtn;
    private Point mouseClickPoint;


    public home(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int width = 700;
        int height = 400;
        setSize(width,height);
        setMinimumSize(new Dimension(width,height));
        setLocationRelativeTo(null);
        dragControl();
        ImageIcon image = new ImageIcon("src/main/resources/names.png");
        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(320/2,427/2, Image.SCALE_DEFAULT)
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
