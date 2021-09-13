/*
 * Created by JFormDesigner on Fri Sep 03 08:56:53 CST 2021
 */

package ui;

import control.CarInfoManager;
import control.UserManager;
import util.BaseException;
import util.DbException;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class Register extends JFrame {
    public Register() {
        initComponents();
    }

    private void button7MouseClicked(MouseEvent e) {
        // TODO add your code here
        Login dlg=new Login();
        dlg.setVisible(true);
        this.setVisible(false);
    }
    //点击提交按钮后应该在数据库新建数据
    private void SumitButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String userid = this.UserID.getText();
        String pwd = String.valueOf(this.UserPwd.getPassword());
        String Confirm_pwd = String.valueOf(this.UserConfPwd.getPassword());
        String sex = this.SexSelect.getItemAt(this.SexSelect.getSelectedIndex());
        String email = this.UserEmail.getText();
        String phoneNum = this.UserPhoneNum.getText();
        String city = this.UserCity.getItemAt(this.UserCity.getSelectedIndex());
        try{
            new UserManager().reg(userid,pwd,Confirm_pwd,sex,phoneNum,email,city);
            Login dlg=new Login();
            JOptionPane.showMessageDialog(null,"注册成功！");
            this.setVisible(false);
            dlg.setVisible(true);
        } catch (BaseException e1) {
            JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }


    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        try {
            UserCity.removeAllItems();
            List<String> CityData = new CarInfoManager().GetAllCity();
            for(int i = 0;i < CityData.size();i ++){
                UserCity.addItem(CityData.get(i));
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        panel13 = new JPanel();
        panel14 = new JPanel();
        panel16 = new JPanel();
        panel18 = new JPanel();
        BackButton = new JButton();
        SumitButton = new JButton();
        Center = new JPanel();
        Title = new JPanel();
        RegiTitle = new JLabel();
        panel9 = new JPanel();
        RUserId = new JLabel();
        RPwd = new JLabel();
        RConfirmPwd = new JLabel();
        RSex = new JLabel();
        REmail = new JLabel();
        RPhone = new JLabel();
        Rcity = new JLabel();
        panel10 = new JPanel();
        UserID = new JTextField();
        UserPwd = new JPasswordField();
        UserConfPwd = new JPasswordField();
        SexSelect = new JComboBox<>();
        UserEmail = new JTextField();
        UserPhoneNum = new JTextField();
        UserCity = new JComboBox<>();

        //======== this ========
        setMinimumSize(new Dimension(440, 330));
        setTitle("Register");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(60, 10));

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
            South.setLayout(new BorderLayout(40, 10));

            //======== panel13 ========
            {
                panel13.setLayout(new GridLayout());
            }
            South.add(panel13, BorderLayout.WEST);

            //======== panel14 ========
            {
                panel14.setLayout(new GridLayout());
            }
            South.add(panel14, BorderLayout.EAST);

            //======== panel16 ========
            {
                panel16.setLayout(new GridLayout());
            }
            South.add(panel16, BorderLayout.SOUTH);

            //======== panel18 ========
            {
                panel18.setLayout(new GridLayout(0, 2, 40, 0));

                //---- BackButton ----
                BackButton.setText("\u8fd4\u56de");
                BackButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button7MouseClicked(e);
                    }
                });
                panel18.add(BackButton);

                //---- SumitButton ----
                SumitButton.setText("\u63d0\u4ea4");
                SumitButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        SumitButtonMouseClicked(e);
                    }
                });
                panel18.add(SumitButton);
            }
            South.add(panel18, BorderLayout.CENTER);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //======== Title ========
            {
                Title.setLayout(new GridLayout());

                //---- RegiTitle ----
                RegiTitle.setText("\u7528\u6237\u6ce8\u518c");
                RegiTitle.setHorizontalAlignment(SwingConstants.CENTER);
                Title.add(RegiTitle);
            }
            Center.add(Title, BorderLayout.NORTH);

            //======== panel9 ========
            {
                panel9.setLayout(new GridLayout(7, 0));

                //---- RUserId ----
                RUserId.setText("\u7528\u6237\u540d");
                RUserId.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(RUserId);

                //---- RPwd ----
                RPwd.setText("\u5bc6\u7801");
                RPwd.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(RPwd);

                //---- RConfirmPwd ----
                RConfirmPwd.setText("\u786e\u8ba4\u5bc6\u7801");
                RConfirmPwd.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(RConfirmPwd);

                //---- RSex ----
                RSex.setText("\u6027\u522b");
                RSex.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(RSex);

                //---- REmail ----
                REmail.setText("\u7535\u5b50\u90ae\u4ef6");
                REmail.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(REmail);

                //---- RPhone ----
                RPhone.setText("\u7535\u8bdd\u53f7\u7801");
                RPhone.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(RPhone);

                //---- Rcity ----
                Rcity.setText("\u57ce\u5e02");
                Rcity.setHorizontalAlignment(SwingConstants.CENTER);
                panel9.add(Rcity);
            }
            Center.add(panel9, BorderLayout.WEST);

            //======== panel10 ========
            {
                panel10.setLayout(new GridLayout(7, 0, 0, 5));
                panel10.add(UserID);
                panel10.add(UserPwd);
                panel10.add(UserConfPwd);

                //---- SexSelect ----
                SexSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                panel10.add(SexSelect);
                panel10.add(UserEmail);
                panel10.add(UserPhoneNum);

                //---- UserCity ----
                UserCity.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u6e29\u5dde"
                }));
                panel10.add(UserCity);
            }
            Center.add(panel10, BorderLayout.CENTER);
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
    private JPanel panel13;
    private JPanel panel14;
    private JPanel panel16;
    private JPanel panel18;
    private JButton BackButton;
    private JButton SumitButton;
    private JPanel Center;
    private JPanel Title;
    private JLabel RegiTitle;
    private JPanel panel9;
    private JLabel RUserId;
    private JLabel RPwd;
    private JLabel RConfirmPwd;
    private JLabel RSex;
    private JLabel REmail;
    private JLabel RPhone;
    private JLabel Rcity;
    private JPanel panel10;
    private JTextField UserID;
    private JPasswordField UserPwd;
    private JPasswordField UserConfPwd;
    private JComboBox<String> SexSelect;
    private JTextField UserEmail;
    private JTextField UserPhoneNum;
    private JComboBox<String> UserCity;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
