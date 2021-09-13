/*
 * Created by JFormDesigner on Sat Sep 04 22:33:12 CST 2021
 */

package ui.User;

import java.awt.event.*;

import control.UserManager;
import model.BeanUser;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class UserInfoCheck extends JFrame {
    public UserInfoCheck() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        //窗体加载就获得当前登陆的用户信息，此前已经从数据库中取出
       BeanUser user = BeanUser.getCurrentLoginUser();
        txtUserName.setText(user.getUser_id());
        txtCity.setText(user.getCity());
        txtEmail.setText(user.getEmail());
        txtSex.setText(user.getSex());
        txtPhoneNum.setText(user.getPhonenum());
        String RegTime = String.valueOf(user.getRegister_time());
        RegTime = RegTime.substring(0,RegTime.length()-2);
        txtRegisterTime.setText(RegTime);
    }
    BeanUser user = BeanUser.getCurrentLoginUser();
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        Back = new JButton();
        Center = new JPanel();
        panel1 = new JPanel();
        lbUserName = new JLabel();
        lbSex = new JLabel();
        lbPhoneNum = new JLabel();
        lbEmail = new JLabel();
        lbCity = new JLabel();
        lbRegisterTime = new JLabel();
        panel3 = new JPanel();
        txtUserName = new JLabel();
        txtSex = new JLabel();
        txtPhoneNum = new JLabel();
        txtEmail = new JLabel();
        txtCity = new JLabel();
        txtRegisterTime = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(400, 280));
        setTitle("UserInfoCheck");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(60, 20));

        //======== North ========
        {
            North.setLayout(new BorderLayout());
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
            South.setLayout(new BorderLayout(80, 20));

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());
            }
            South.add(panel2, BorderLayout.WEST);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout());
            }
            South.add(panel4, BorderLayout.EAST);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout());
            }
            South.add(panel5, BorderLayout.SOUTH);

            //======== panel6 ========
            {
                panel6.setLayout(new GridLayout());

                //---- Back ----
                Back.setText("\u8fd4\u56de");
                Back.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panel6.add(Back);
            }
            South.add(panel6, BorderLayout.CENTER);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout(6, 1));

                //---- lbUserName ----
                lbUserName.setText("\u7528\u6237\u540d");
                lbUserName.setHorizontalAlignment(SwingConstants.LEFT);
                lbUserName.setIcon(new ImageIcon(getClass().getResource("/static/image/user.png")));
                panel1.add(lbUserName);

                //---- lbSex ----
                lbSex.setText("\u6027\u522b");
                lbSex.setHorizontalAlignment(SwingConstants.LEFT);
                lbSex.setIcon(new ImageIcon(getClass().getResource("/static/image/sex.png")));
                panel1.add(lbSex);

                //---- lbPhoneNum ----
                lbPhoneNum.setText("\u7535\u8bdd\u53f7\u7801");
                lbPhoneNum.setHorizontalAlignment(SwingConstants.LEFT);
                lbPhoneNum.setIcon(new ImageIcon(getClass().getResource("/static/image/phone-number.png")));
                panel1.add(lbPhoneNum);

                //---- lbEmail ----
                lbEmail.setText("\u90ae\u7bb1");
                lbEmail.setHorizontalAlignment(SwingConstants.LEFT);
                lbEmail.setIcon(new ImageIcon(getClass().getResource("/static/image/185078 - email mail streamline.png")));
                panel1.add(lbEmail);

                //---- lbCity ----
                lbCity.setText("\u6240\u5728\u57ce\u5e02");
                lbCity.setHorizontalAlignment(SwingConstants.LEFT);
                lbCity.setIcon(new ImageIcon(getClass().getResource("/static/image/city.png")));
                panel1.add(lbCity);

                //---- lbRegisterTime ----
                lbRegisterTime.setText("\u6ce8\u518c\u65f6\u95f4");
                lbRegisterTime.setHorizontalAlignment(SwingConstants.LEFT);
                lbRegisterTime.setIcon(new ImageIcon(getClass().getResource("/static/image/time.png")));
                panel1.add(lbRegisterTime);
            }
            Center.add(panel1, BorderLayout.WEST);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(6, 0));

                //---- txtUserName ----
                txtUserName.setText("text");
                txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtUserName);

                //---- txtSex ----
                txtSex.setText("text");
                txtSex.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtSex);

                //---- txtPhoneNum ----
                txtPhoneNum.setText("text");
                txtPhoneNum.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtPhoneNum);

                //---- txtEmail ----
                txtEmail.setText("text");
                txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtEmail);

                //---- txtCity ----
                txtCity.setText("text");
                txtCity.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtCity);

                //---- txtRegisterTime ----
                txtRegisterTime.setText("text");
                txtRegisterTime.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(txtRegisterTime);
            }
            Center.add(panel3, BorderLayout.CENTER);
        }
        contentPane.add(Center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel North;
    private JPanel West;
    private JPanel East;
    private JPanel South;
    private JPanel panel2;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JButton Back;
    private JPanel Center;
    private JPanel panel1;
    private JLabel lbUserName;
    private JLabel lbSex;
    private JLabel lbPhoneNum;
    private JLabel lbEmail;
    private JLabel lbCity;
    private JLabel lbRegisterTime;
    private JPanel panel3;
    private JLabel txtUserName;
    private JLabel txtSex;
    private JLabel txtPhoneNum;
    private JLabel txtEmail;
    private JLabel txtCity;
    private JLabel txtRegisterTime;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
