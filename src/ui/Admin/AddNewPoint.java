/*
 * Created by JFormDesigner on Sat Sep 11 21:37:20 CST 2021
 */

package ui.Admin;

import control.PointManager;
import control.WorkerManager;
import model.BeanPoint;
import model.BeanWorker;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Brainrain
 */
public class AddNewPoint extends JFrame {
    public AddNewPoint() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        PointInfoManager dlg = new PointInfoManager();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        BeanPoint NewPoint = new BeanPoint();
        NewPoint.setPointName(TPointName.getText());
        NewPoint.setPointCity(TCity.getText());
        NewPoint.setPointAddress(TAddress.getText());
        NewPoint.setPointPhoneNum(TPhoneNum.getText());

        try {
            new PointManager().AddNewPoint(NewPoint);
            JOptionPane.showMessageDialog(null,"Ìí¼Ó³É¹¦£¡");

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        PointInfoManager dlg = new PointInfoManager();
        dlg.setVisible(true);

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        Center = new JPanel();
        Title = new JLabel();
        panel5 = new JPanel();
        LPointName = new JLabel();
        TPointName = new JTextField();
        LCity = new JLabel();
        TCity = new JTextField();
        LAddress = new JLabel();
        TAddress = new JTextField();
        LPhoneNum = new JLabel();
        TPhoneNum = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("AddNewPoint");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(20, 20));

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout());
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        contentPane.add(panel2, BorderLayout.WEST);

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout());
        }
        contentPane.add(panel3, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setLayout(new GridLayout());
        }
        contentPane.add(panel4, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout(10, 10));

            //---- Title ----
            Title.setText("\u6dfb\u52a0\u65b0\u7f51\u70b9");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(5, 2, 20, 10));

                //---- LPointName ----
                LPointName.setText("\u7f51\u70b9\u540d\u79f0");
                LPointName.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPointName);
                panel5.add(TPointName);

                //---- LCity ----
                LCity.setText("\u7f51\u70b9\u6240\u5728\u57ce\u5e02");
                LCity.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LCity);
                panel5.add(TCity);

                //---- LAddress ----
                LAddress.setText("\u7f51\u70b9\u5730\u5740");
                LAddress.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LAddress);
                panel5.add(TAddress);

                //---- LPhoneNum ----
                LPhoneNum.setText("\u7535\u8bdd\u53f7\u7801");
                LPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPhoneNum);
                panel5.add(TPhoneNum);

                //---- BackButton ----
                BackButton.setText("\u8fd4\u56de");
                BackButton.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                BackButton.addActionListener(e -> BackButtonActionPerformed(e));
                panel5.add(BackButton);

                //---- AddConfirm ----
                AddConfirm.setText("\u786e\u8ba4\u6dfb\u52a0");
                AddConfirm.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                AddConfirm.addActionListener(e -> AddConfirmActionPerformed(e));
                panel5.add(AddConfirm);
            }
            Center.add(panel5, BorderLayout.CENTER);
        }
        contentPane.add(Center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel Center;
    private JLabel Title;
    private JPanel panel5;
    private JLabel LPointName;
    private JTextField TPointName;
    private JLabel LCity;
    private JTextField TCity;
    private JLabel LAddress;
    private JTextField TAddress;
    private JLabel LPhoneNum;
    private JTextField TPhoneNum;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
