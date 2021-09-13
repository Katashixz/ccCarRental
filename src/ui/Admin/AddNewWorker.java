/*
 * Created by JFormDesigner on Sat Sep 11 21:37:20 CST 2021
 */

package ui.Admin;

import control.PasswordEncoding;
import control.WorkerManager;
import model.BeanWorker;
import ui.Admin.WorkerInfoManager;
import util.BaseException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class AddNewWorker extends JFrame {
    public AddNewWorker() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        WorkerInfoManager dlg = new WorkerInfoManager();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        BeanWorker NewWorker = new BeanWorker();
        NewWorker.setWorkerName(TWorkerName.getText());
        //密码需要加密
        NewWorker.setWorkerPwd(new PasswordEncoding().encode(TPwd.getText()));

        try{
            NewWorker.setPointNum(Integer.valueOf(TPoint.getText()));
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确的网点信息！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            new WorkerManager().addWorker(NewWorker);
            JOptionPane.showMessageDialog(null,"添加成功！");

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        WorkerInfoManager dlg = new WorkerInfoManager();
        dlg.setVisible(true);

    }

    private void TPointActionPerformed(ActionEvent e) {
        // TODO add your code here
        TPoint.setText("");
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
        LPoint = new JLabel();
        TPoint = new JTextField();
        LWorkerName = new JLabel();
        TWorkerName = new JTextField();
        LPwd = new JLabel();
        TPwd = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setTitle("AddNewWorker");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
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
            Title.setText("\u6dfb\u52a0\u65b0\u5458\u5de5");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(4, 2, 20, 10));

                //---- LPoint ----
                LPoint.setText("\u6240\u5c5e\u7f51\u70b9");
                LPoint.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPoint);

                //---- TPoint ----
                TPoint.setText("\u8bbe\u7f6e\u4e3a0\u4ee3\u8868\u7ba1\u7406\u5458");
                TPoint.addActionListener(e -> TPointActionPerformed(e));
                panel5.add(TPoint);

                //---- LWorkerName ----
                LWorkerName.setText("\u8d26\u53f7\u540d\u79f0");
                LWorkerName.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LWorkerName);
                panel5.add(TWorkerName);

                //---- LPwd ----
                LPwd.setText("\u5bc6\u7801");
                LPwd.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPwd);
                panel5.add(TPwd);

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
    private JLabel LPoint;
    private JTextField TPoint;
    private JLabel LWorkerName;
    private JTextField TWorkerName;
    private JLabel LPwd;
    private JTextField TPwd;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
