/*
 * Created by JFormDesigner on Sat Sep 11 10:11:25 CST 2021
 */

package ui.Worker;

import ui.Login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class WorkerMain extends JFrame {
    public WorkerMain() {
        initComponents();
    }


    private void LogOutActionPerformed(ActionEvent e) {
        // TODO add your code here
        Login dlg = new Login();
        dlg.setVisible(true);
        this.setVisible(false);
    }

    private void ExitActionPerformed(ActionEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void CouponManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        CouponInfoCheck dlg = new CouponInfoCheck();
        dlg.setVisible(true);

    }

    private void CarManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        ScarppedCar dlg = new ScarppedCar();
        dlg.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        menuBar1 = new JMenuBar();
        PersonalCenter = new JMenu();
        LogOut = new JMenuItem();
        Exit = new JMenuItem();
        CouponCenter = new JMenu();
        CouponManager = new JMenuItem();
        CarInfoMCenter = new JMenu();
        CarManager = new JMenuItem();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        Center = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setTitle("WorkerMain");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/office-worker.png")).getImage());
        setMinimumSize(new Dimension(600, 400));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== North ========
        {
            North.setLayout(new BorderLayout());

            //======== menuBar1 ========
            {

                //======== PersonalCenter ========
                {
                    PersonalCenter.setText("\u5458\u5de5\u4e2d\u5fc3");

                    //---- LogOut ----
                    LogOut.setText("\u6ce8\u9500");
                    LogOut.setIcon(new ImageIcon(getClass().getResource("/static/image/log-out.png")));
                    LogOut.addActionListener(e -> LogOutActionPerformed(e));
                    PersonalCenter.add(LogOut);

                    //---- Exit ----
                    Exit.setText("\u9000\u51fa");
                    Exit.setIcon(new ImageIcon(getClass().getResource("/static/image/Exit.png")));
                    Exit.addActionListener(e -> ExitActionPerformed(e));
                    PersonalCenter.add(Exit);
                }
                menuBar1.add(PersonalCenter);

                //======== CouponCenter ========
                {
                    CouponCenter.setText("\u4f18\u60e0\u5238\u4e2d\u5fc3");
                    CouponCenter.addActionListener(e -> CouponManagerActionPerformed(e));

                    //---- CouponManager ----
                    CouponManager.setText("\u4f18\u60e0\u5238\u7ba1\u7406");
                    CouponManager.setIcon(new ImageIcon(getClass().getResource("/static/image/coupon (1).png")));
                    CouponManager.addActionListener(e -> CouponManagerActionPerformed(e));
                    CouponCenter.add(CouponManager);
                }
                menuBar1.add(CouponCenter);

                //======== CarInfoMCenter ========
                {
                    CarInfoMCenter.setText("\u8f66\u8f86\u4e2d\u5fc3");

                    //---- CarManager ----
                    CarManager.setText("\u62a5\u5e9f\u8f66\u8f86");
                    CarManager.setIcon(new ImageIcon(getClass().getResource("/static/image/rubbish bin.png")));
                    CarManager.addActionListener(e -> CarManagerActionPerformed(e));
                    CarInfoMCenter.add(CarManager);
                }
                menuBar1.add(CarInfoMCenter);
            }
            North.add(menuBar1, BorderLayout.WEST);
        }
        contentPane.add(North, BorderLayout.NORTH);

        //======== West ========
        {
            West.setLayout(new BorderLayout());
        }
        contentPane.add(West, BorderLayout.WEST);

        //======== East ========
        {
            East.setLayout(new BorderLayout());
        }
        contentPane.add(East, BorderLayout.EAST);

        //======== South ========
        {
            South.setLayout(new BorderLayout());
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //---- label1 ----
            label1.setIcon(new ImageIcon("F:\\\u5927\u5b66\\\u77ed\u5b66\u671f\\2021\\\u8d44\u6e90\\car.png"));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(label1, BorderLayout.CENTER);
        }
        contentPane.add(Center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel North;
    private JMenuBar menuBar1;
    private JMenu PersonalCenter;
    private JMenuItem LogOut;
    private JMenuItem Exit;
    private JMenu CouponCenter;
    private JMenuItem CouponManager;
    private JMenu CarInfoMCenter;
    private JMenuItem CarManager;
    private JPanel West;
    private JPanel East;
    private JPanel South;
    private JPanel Center;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
