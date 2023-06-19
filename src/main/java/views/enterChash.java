package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class enterChash extends JFrame{
    private JPanel mainPanel;
    private JButton btn100;
    private JButton btn500;
    private JButton btn200;
    private JButton btn1000;
    private JButton btn1500;
    public JButton aceptBtn;
    private JButton cancelBtn;
    private JButton btn300;
    public JSpinner cashField;
    private Point mouseClickPoint;

    public enterChash(){
        setContentPane(mainPanel);
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        int width = 400;
        int height = 200;
        setSize(width,height);
        setMinimumSize(new Dimension(width,height));
        setLocationRelativeTo(null);
        dragControl();

        cancelBtn.addActionListener(e -> {
            this.dispose();
        });

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,5000,100);
        cashField.setModel(spinnerModel);
        setActionButtons(btn100,100);
        setActionButtons(btn200,200);
        setActionButtons(btn300,300);
        setActionButtons(btn500,500);
        setActionButtons(btn1000,1000);
        setActionButtons(btn1500,1500);

    }

    private void setActionButtons(JButton button, int value){
        button.addActionListener(e -> {
            cashField.setValue(value);
        });
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
