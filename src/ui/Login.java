/*
 * Created by JFormDesigner on Thu Sep 02 22:42:31 CST 2021
 */

package ui;

import java.awt.event.*;

import control.PasswordEncoding;
import control.UserManager;
import control.WorkerManager;
import model.BeanUser;
import model.BeanWorker;
import ui.Admin.AdminMain;
import ui.User.UserMain;
import ui.Worker.WorkerMain;
import util.BaseException;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class Login extends JFrame {
    public Login() {
        initComponents();
    }

    private void LoginButtonMouseClicked(MouseEvent e) {
        // TODO add your code here

    }


    private void ExitButtonMouseClicked(MouseEvent e) {
        System.exit(0);
    }

    private void RegisterButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        Register dlg=new Register();
        dlg.setVisible(true);
        this.setVisible(false);
    }

    private void LoginButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        String userid = this.LoginField.getText();
        String pwd = new String(this.passwordField1.getPassword());
        pwd = new PasswordEncoding().encode(pwd);

        //判断登陆的用户类型，调用不同的函数
        if(this.TypeSelect.getItemAt(this.TypeSelect.getSelectedIndex()).equals("客户")){
            try{
                BeanUser.currentLoginUser = new UserManager().login(userid,pwd);
            }catch (BaseException e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            UserMain dlgUserMain = new UserMain();
            dlgUserMain.setVisible(true);
        }else if(this.TypeSelect.getItemAt(this.TypeSelect.getSelectedIndex()).equals("员工")){
            try{
                BeanWorker.currentLoginWorker = new WorkerManager().login(userid,pwd);
            }catch (BaseException e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            WorkerMain dlgWorkerMain = new WorkerMain();
            dlgWorkerMain.setVisible(true);
        }else if(this.TypeSelect.getItemAt(this.TypeSelect.getSelectedIndex()).equals("管理员")){
            try {
                BeanWorker.currentLoginWorker = new WorkerManager().adminlogin(userid,pwd);
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            AdminMain dlgAdminMain = new AdminMain();
            dlgAdminMain.setVisible(true);

        }
        this.setVisible(false);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label7 = new JLabel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        center = new JPanel();
        LoginLable = new JLabel();
        panel5 = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        label1 = new JLabel();
        panel6 = new JPanel();
        LoginField = new JTextField();
        passwordField1 = new JPasswordField();
        TypeSelect = new JComboBox<>();
        panel7 = new JPanel();
        RegisterButton = new JButton();
        LoginButton = new JButton();
        ExitButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 300));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        setTitle("Login");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(30, 20));

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout());
            panel1.add(label7, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());
        }
        contentPane.add(panel2, BorderLayout.WEST);

        //======== panel3 ========
        {
            panel3.setLayout(new BorderLayout());
        }
        contentPane.add(panel3, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());
        }
        contentPane.add(panel4, BorderLayout.SOUTH);

        //======== center ========
        {
            center.setLayout(new BorderLayout(20, 20));

            //---- LoginLable ----
            LoginLable.setText("\u767b\u5f55");
            LoginLable.setHorizontalAlignment(SwingConstants.CENTER);
            center.add(LoginLable, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(3, 1, 0, 30));

                //---- label5 ----
                label5.setText("\u7528\u6237\u540d");
                label5.setHorizontalAlignment(SwingConstants.LEFT);
                label5.setIcon(new ImageIcon(getClass().getResource("/static/image/user.png")));
                panel5.add(label5);

                //---- label6 ----
                label6.setText("\u5bc6\u7801");
                label6.setHorizontalAlignment(SwingConstants.LEFT);
                label6.setIcon(new ImageIcon(getClass().getResource("/static/image/password2.png")));
                panel5.add(label6);

                //---- label1 ----
                label1.setText("\u7528\u6237\u7c7b\u578b");
                label1.setHorizontalAlignment(SwingConstants.LEFT);
                label1.setIcon(new ImageIcon(getClass().getResource("/static/image/user-group.png")));
                panel5.add(label1);
            }
            center.add(panel5, BorderLayout.WEST);

            //======== panel6 ========
            {
                panel6.setLayout(new GridLayout(3, 1, 30, 30));
                panel6.add(LoginField);
                panel6.add(passwordField1);

                //---- TypeSelect ----
                TypeSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u5ba2\u6237",
                    "\u5458\u5de5",
                    "\u7ba1\u7406\u5458"
                }));
                panel6.add(TypeSelect);
            }
            center.add(panel6, BorderLayout.CENTER);

            //======== panel7 ========
            {
                panel7.setLayout(new GridLayout(1, 2, 30, 0));

                //---- RegisterButton ----
                RegisterButton.setText("\u7528\u6237\u6ce8\u518c");
                RegisterButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        RegisterButtonMouseClicked(e);
                    }
                });
                panel7.add(RegisterButton);

                //---- LoginButton ----
                LoginButton.setText("\u767b\u5f55");
                LoginButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        LoginButtonMouseClicked(e);
                    }
                });
                LoginButton.addActionListener(e -> LoginButtonActionPerformed(e));
                panel7.add(LoginButton);

                //---- ExitButton ----
                ExitButton.setText("\u9000\u51fa");
                ExitButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ExitButtonMouseClicked(e);
                    }
                });
                panel7.add(ExitButton);
            }
            center.add(panel7, BorderLayout.SOUTH);
        }
        contentPane.add(center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label7;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel center;
    private JLabel LoginLable;
    private JPanel panel5;
    private JLabel label5;
    private JLabel label6;
    private JLabel label1;
    private JPanel panel6;
    private JTextField LoginField;
    private JPasswordField passwordField1;
    private JComboBox<String> TypeSelect;
    private JPanel panel7;
    private JButton RegisterButton;
    private JButton LoginButton;
    private JButton ExitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
