/*
 * Created by JFormDesigner on Sat Sep 04 22:33:12 CST 2021
 */

package ui.User;

import java.awt.event.*;

import control.UserManager;
import model.BeanUser;
import util.BaseException;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class UserInfoChange extends JFrame {
    public UserInfoChange() {
        initComponents();
    }


    private void BackButtonMouseClicked(MouseEvent e) {
        this.setVisible(false);
    }

    private void SummitButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        String cUserName = this.CUserName.getText();
        String cPwd = this.CPassword.getText();
        String cSex = this.CSex.getItemAt(this.CSex.getSelectedIndex());
        String cNewPwd = this.CNewPassword.getText();
        String cPhoneNum = this.CPhoneNum.getText();
        String cEmail = this.CEmail.getText();
        String cCtiy = this.CCtiy.getItemAt(this.CCtiy.getSelectedIndex());
        try{
            new UserManager().Update(cUserName,cPwd,cNewPwd,cSex,cPhoneNum,cEmail,cCtiy);
            UserInfoChange dlg = new UserInfoChange();
            JOptionPane.showMessageDialog(null,"更新成功！");
            this.setVisible(false);

        }catch(BaseException e1){
            JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanUser user = BeanUser.getCurrentLoginUser();
        CUserName.setText(user.getUser_id());
        CEmail.setText(user.getEmail());
        CPhoneNum.setText(user.getPhonenum());
    }
    BeanUser user = BeanUser.getCurrentLoginUser();
    private void initComponents() {
        BeanUser user = BeanUser.getCurrentLoginUser();
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        BackButton = new JButton();
        SummitButton = new JButton();
        Center = new JPanel();
        panel1 = new JPanel();
        lbUserName = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();
        lbSex = new JLabel();
        lbPhoneNum = new JLabel();
        lbEmail = new JLabel();
        lbCity = new JLabel();
        panel3 = new JPanel();
        CUserName = new JTextField();
        CPassword = new JTextField();
        CNewPassword = new JTextField();
        CSex = new JComboBox<>();
        CPhoneNum = new JTextField();
        CEmail = new JTextField();
        CCtiy = new JComboBox<>();

        //======== this ========
        setMinimumSize(new Dimension(400, 280));
        setTitle("UserInfo");
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

                //======== panel7 ========
                {
                    panel7.setLayout(new GridLayout(0, 2, 20, 0));

                    //---- BackButton ----
                    BackButton.setText("\u8fd4\u56de");
                    BackButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            BackButtonMouseClicked(e);
                        }
                    });
                    panel7.add(BackButton);

                    //---- SummitButton ----
                    SummitButton.setText("\u63d0\u4ea4");
                    SummitButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            SummitButtonMouseClicked(e);
                        }
                    });
                    panel7.add(SummitButton);
                }
                panel6.add(panel7);
            }
            South.add(panel6, BorderLayout.CENTER);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout(7, 1));

                //---- lbUserName ----
                lbUserName.setText("\u7528\u6237\u540d");
                lbUserName.setHorizontalAlignment(SwingConstants.LEFT);
                lbUserName.setIcon(new ImageIcon(getClass().getResource("/static/image/user.png")));
                panel1.add(lbUserName);

                //---- label2 ----
                label2.setText("\u539f\u5bc6\u7801");
                label2.setIcon(new ImageIcon(getClass().getResource("/static/image/password2.png")));
                panel1.add(label2);

                //---- label1 ----
                label1.setText("\u65b0\u5bc6\u7801");
                label1.setIcon(new ImageIcon(getClass().getResource("/static/image/password.png")));
                panel1.add(label1);

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
            }
            Center.add(panel1, BorderLayout.WEST);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(7, 0));
                panel3.add(CUserName);
                panel3.add(CPassword);
                panel3.add(CNewPassword);

                //---- CSex ----
                CSex.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u7537",
                    "\u5973"
                }));
                panel3.add(CSex);
                panel3.add(CPhoneNum);
                panel3.add(CEmail);

                //---- CCtiy ----
                CCtiy.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u4e0a\u6d77",
                    "\u6e29\u5dde",
                    "\u676d\u5dde"
                }));
                panel3.add(CCtiy);
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
    private JPanel panel7;
    private JButton BackButton;
    private JButton SummitButton;
    private JPanel Center;
    private JPanel panel1;
    private JLabel lbUserName;
    private JLabel label2;
    private JLabel label1;
    private JLabel lbSex;
    private JLabel lbPhoneNum;
    private JLabel lbEmail;
    private JLabel lbCity;
    private JPanel panel3;
    private JTextField CUserName;
    private JTextField CPassword;
    private JTextField CNewPassword;
    private JComboBox<String> CSex;
    private JTextField CPhoneNum;
    private JTextField CEmail;
    private JComboBox<String> CCtiy;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
