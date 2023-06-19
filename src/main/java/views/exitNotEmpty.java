package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exitNotEmpty extends JDialog {
    private JPanel contentPane;
    public JButton buttonOK;
    public JButton buttonCancel;
    public JLabel msjLabel;
    private Point mouseClickPoint;

    public exitNotEmpty() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setSize(400,225);
        setMinimumSize(new Dimension(400,225));
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
