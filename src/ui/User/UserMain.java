/*
 * Created by JFormDesigner on Fri Sep 03 15:05:57 CST 2021
 */

package ui.User;

import java.awt.event.*;

import ui.Login;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class UserMain extends JFrame {
    public UserMain() {
        initComponents();
    }


    private void InfoCheckActionPerformed(ActionEvent e) {
        UserInfoCheck dlgUserInfoCheck = new UserInfoCheck();
        dlgUserInfoCheck.setVisible(true);
        /*BeanUser user = BeanUser.getCurrentLoginUser();
        System.out.println(user.User_id);
        System.out.println(1);*/
    }

    private void InfoReviceActionPerformed(ActionEvent e) {
        // TODO add your code here
        UserInfoChange dlgUserInfoChange = new UserInfoChange();
        dlgUserInfoChange.setVisible(true);
    }

    private void LogOutActionPerformed(ActionEvent e) {
        // TODO add your code here
        Login dlg = new Login();
        dlg.setVisible(true);
        this.setVisible(false);
    }

    private void CarRentActionPerformed(ActionEvent e) {
        // TODO add your code here
        ui.User.CarRent CarRentdlg = new CarRent();
        CarRentdlg.setVisible(true);
    }

    private void ExitActionPerformed(ActionEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void CarReturnActionPerformed(ActionEvent e) {
        // TODO add your code here
        OrderList OrderListdlg = new OrderList();
        OrderListdlg.setVisible(true);
    }

    private void OrderCheckActionPerformed(ActionEvent e) {
        // TODO add your code here
        CheckAllOrder dlg = new CheckAllOrder();
        dlg.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        menuBar1 = new JMenuBar();
        PersonalCenter = new JMenu();
        InfoCheck = new JMenuItem();
        InfoRevice = new JMenuItem();
        OrderCheck = new JMenuItem();
        LogOut = new JMenuItem();
        Exit = new JMenuItem();
        Rent_Return_Management = new JMenu();
        CarRent = new JMenuItem();
        CarReturn = new JMenuItem();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        Center = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        setTitle("Main");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== North ========
        {
            North.setLayout(new BorderLayout());

            //======== menuBar1 ========
            {

                //======== PersonalCenter ========
                {
                    PersonalCenter.setText("\u4e2a\u4eba\u4e2d\u5fc3");

                    //---- InfoCheck ----
                    InfoCheck.setText("\u67e5\u770b\u4e2a\u4eba\u4fe1\u606f");
                    InfoCheck.setIcon(new ImageIcon(getClass().getResource("/static/image/info-circle.png")));
                    InfoCheck.addActionListener(e -> InfoCheckActionPerformed(e));
                    PersonalCenter.add(InfoCheck);

                    //---- InfoRevice ----
                    InfoRevice.setText("\u4fee\u6539\u4e2a\u4eba\u4fe1\u606f");
                    InfoRevice.setIcon(new ImageIcon(getClass().getResource("/static/image/update (1).png")));
                    InfoRevice.addActionListener(e -> InfoReviceActionPerformed(e));
                    PersonalCenter.add(InfoRevice);

                    //---- OrderCheck ----
                    OrderCheck.setText("\u67e5\u770b\u8ba2\u5355");
                    OrderCheck.setIcon(new ImageIcon(getClass().getResource("/static/image/order.png")));
                    OrderCheck.addActionListener(e -> OrderCheckActionPerformed(e));
                    PersonalCenter.add(OrderCheck);

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

                //======== Rent_Return_Management ========
                {
                    Rent_Return_Management.setText("\u79df\u501f\u7ba1\u7406");

                    //---- CarRent ----
                    CarRent.setText("\u79df\u8f66");
                    CarRent.setIcon(new ImageIcon(getClass().getResource("/static/image/car-rental.png")));
                    CarRent.addActionListener(e -> CarRentActionPerformed(e));
                    Rent_Return_Management.add(CarRent);

                    //---- CarReturn ----
                    CarReturn.setText("\u8fd8\u8f66");
                    CarReturn.setIcon(new ImageIcon(getClass().getResource("/static/image/car_return.png")));
                    CarReturn.addActionListener(e -> CarReturnActionPerformed(e));
                    Rent_Return_Management.add(CarReturn);
                }
                menuBar1.add(Rent_Return_Management);
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
    private JMenuItem InfoCheck;
    private JMenuItem InfoRevice;
    private JMenuItem OrderCheck;
    private JMenuItem LogOut;
    private JMenuItem Exit;
    private JMenu Rent_Return_Management;
    private JMenuItem CarRent;
    private JMenuItem CarReturn;
    private JPanel West;
    private JPanel East;
    private JPanel South;
    private JPanel Center;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
