/*
 * Created by JFormDesigner on Sat Sep 11 20:33:35 CST 2021
 */

package ui.Admin;

import ui.Admin.WorkerInfoManager;
import ui.Login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class AdminMain extends JFrame {
    public AdminMain() {
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
    }

    private void WorkerManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        WorkerInfoManager WIMdlg = new WorkerInfoManager();
        WIMdlg.setVisible(true);
    }

    private void OrderCheckActionPerformed(ActionEvent e) {
        // TODO add your code here
        AdminOrderCheck dlg = new AdminOrderCheck();
        dlg.setVisible(true);
    }

    private void CarRentalInfoCheckActionPerformed(ActionEvent e) {
        // TODO add your code here
        AdminCarRentalInfoCheck dlg = new AdminCarRentalInfoCheck();
        dlg.setVisible(true);
    }

    private void PointInfoManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        PointInfoManager dlg = new PointInfoManager();
        dlg.setVisible(true);
    }

    private void CarInfoManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        CarInfoManager dlg = new CarInfoManager();
        dlg.setVisible(true);
    }

    private void CarTypeManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        ui.Admin.CarTypeManager dlg = new CarTypeManager();
        dlg.setVisible(true);
    }

    private void CarModelManagerActionPerformed(ActionEvent e) {
        // TODO add your code here
        CarModelManager dlg = new CarModelManager();
        dlg.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        menuBar1 = new JMenuBar();
        PersonalCenter = new JMenu();
        LogOut = new JMenuItem();
        Exit = new JMenuItem();
        WorkerCenter = new JMenu();
        WorkerManager = new JMenuItem();
        menu1 = new JMenu();
        PointInfoManager = new JMenuItem();
        UserCenter = new JMenu();
        OrderCheck = new JMenuItem();
        CarCenter = new JMenu();
        CarRentalInfoCheck = new JMenuItem();
        CarTypeManager = new JMenuItem();
        CarModelManager = new JMenuItem();
        CarInfoManager = new JMenuItem();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        Center = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setTitle("Admin");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== North ========
        {
            North.setLayout(new BorderLayout());

            //======== menuBar1 ========
            {

                //======== PersonalCenter ========
                {
                    PersonalCenter.setText("\u7ba1\u7406\u5458\u4e2d\u5fc3");

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

                //======== WorkerCenter ========
                {
                    WorkerCenter.setText("\u5458\u5de5\u4fe1\u606f\u4e2d\u5fc3");
                    WorkerCenter.addActionListener(e -> CouponManagerActionPerformed(e));

                    //---- WorkerManager ----
                    WorkerManager.setText("\u5458\u5de5\u4fe1\u606f\u7ba1\u7406");
                    WorkerManager.setIcon(new ImageIcon(getClass().getResource("/static/image/staff.png")));
                    WorkerManager.addActionListener(e -> WorkerManagerActionPerformed(e));
                    WorkerCenter.add(WorkerManager);
                }
                menuBar1.add(WorkerCenter);

                //======== menu1 ========
                {
                    menu1.setText("\u7f51\u70b9\u4e2d\u5fc3");

                    //---- PointInfoManager ----
                    PointInfoManager.setText("\u7ef4\u62a4\u7f51\u70b9\u4fe1\u606f");
                    PointInfoManager.setIcon(new ImageIcon(getClass().getResource("/static/image/point time line.png")));
                    PointInfoManager.addActionListener(e -> PointInfoManagerActionPerformed(e));
                    menu1.add(PointInfoManager);
                }
                menuBar1.add(menu1);

                //======== UserCenter ========
                {
                    UserCenter.setText("\u7528\u6237\u4e2d\u5fc3");

                    //---- OrderCheck ----
                    OrderCheck.setText("\u67e5\u770b\u7528\u6237\u8ba2\u5355");
                    OrderCheck.setIcon(new ImageIcon(getClass().getResource("/static/image/order-list.png")));
                    OrderCheck.addActionListener(e -> OrderCheckActionPerformed(e));
                    UserCenter.add(OrderCheck);
                }
                menuBar1.add(UserCenter);

                //======== CarCenter ========
                {
                    CarCenter.setText("\u8f66\u8f86\u4e2d\u5fc3");

                    //---- CarRentalInfoCheck ----
                    CarRentalInfoCheck.setText("\u67e5\u8be2\u8f66\u8f86\u79df\u7528\u4fe1\u606f");
                    CarRentalInfoCheck.setIcon(new ImageIcon(getClass().getResource("/static/image/rental reimbursement.png")));
                    CarRentalInfoCheck.addActionListener(e -> CarRentalInfoCheckActionPerformed(e));
                    CarCenter.add(CarRentalInfoCheck);

                    //---- CarTypeManager ----
                    CarTypeManager.setText("\u7ef4\u62a4\u6c7d\u8f66\u7c7b\u522b");
                    CarTypeManager.setIcon(new ImageIcon(getClass().getResource("/static/image/\u6273\u624b.png")));
                    CarTypeManager.addActionListener(e -> CarTypeManagerActionPerformed(e));
                    CarCenter.add(CarTypeManager);

                    //---- CarModelManager ----
                    CarModelManager.setText("\u7ef4\u62a4\u8f66\u578b\u4fe1\u606f");
                    CarModelManager.setIcon(new ImageIcon(getClass().getResource("/static/image/\u6273\u624b.png")));
                    CarModelManager.addActionListener(e -> CarModelManagerActionPerformed(e));
                    CarCenter.add(CarModelManager);

                    //---- CarInfoManager ----
                    CarInfoManager.setText("\u7ef4\u62a4\u8f66\u8f86\u4fe1\u606f");
                    CarInfoManager.setIcon(new ImageIcon(getClass().getResource("/static/image/\u6273\u624b.png")));
                    CarInfoManager.addActionListener(e -> CarInfoManagerActionPerformed(e));
                    CarCenter.add(CarInfoManager);
                }
                menuBar1.add(CarCenter);
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
    private JMenu WorkerCenter;
    private JMenuItem WorkerManager;
    private JMenu menu1;
    private JMenuItem PointInfoManager;
    private JMenu UserCenter;
    private JMenuItem OrderCheck;
    private JMenu CarCenter;
    private JMenuItem CarRentalInfoCheck;
    private JMenuItem CarTypeManager;
    private JMenuItem CarModelManager;
    private JMenuItem CarInfoManager;
    private JPanel West;
    private JPanel East;
    private JPanel South;
    private JPanel Center;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
