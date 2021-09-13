/*
 * Created by JFormDesigner on Sat Sep 11 21:37:20 CST 2021
 */

package ui.Admin;

import control.CarInfoManager;
import control.PointManager;
import model.BeanCarInfo;
import model.BeanPoint;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Brainrain
 */
public class AddNewCarInfo extends JFrame {
    public AddNewCarInfo() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        ui.Admin.CarInfoManager dlg = new ui.Admin.CarInfoManager();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer PointNum;
        Integer ModelNum;
        try{
            PointNum = Integer.valueOf(TPointNum.getText());
            ModelNum = Integer.valueOf(TCarModelNum.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确编号！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        BeanCarInfo NewCarInfo = new BeanCarInfo();
        NewCarInfo.setAtPoint(PointNum);
        NewCarInfo.setModelNum(ModelNum);
        NewCarInfo.setLicense(TLicense.getText());
        NewCarInfo.setCarCondition(TState.getText());
        try {
            new CarInfoManager().AddNewCarInfo(NewCarInfo);
            JOptionPane.showMessageDialog(null,"添加成功！");

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        ui.Admin.CarInfoManager dlg = new ui.Admin.CarInfoManager();
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
        LPointNum = new JLabel();
        TPointNum = new JTextField();
        LCarModelNum = new JLabel();
        TCarModelNum = new JTextField();
        LLicense = new JLabel();
        TLicense = new JTextField();
        LState = new JLabel();
        TState = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("AddNewCarType");
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
            Title.setText("\u6dfb\u52a0\u65b0\u8f66\u8f86\u4fe1\u606f");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(5, 2, 20, 10));

                //---- LPointNum ----
                LPointNum.setText("\u6240\u5c5e\u7f51\u70b9");
                LPointNum.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPointNum);
                panel5.add(TPointNum);

                //---- LCarModelNum ----
                LCarModelNum.setText("\u6240\u5c5e\u8f66\u578b");
                LCarModelNum.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LCarModelNum);
                panel5.add(TCarModelNum);

                //---- LLicense ----
                LLicense.setText("\u8f66\u8f86\u724c\u7167");
                LLicense.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LLicense);
                panel5.add(TLicense);

                //---- LState ----
                LState.setText("\u8f66\u8f86\u72b6\u6001");
                LState.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LState);
                panel5.add(TState);

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
    private JLabel LPointNum;
    private JTextField TPointNum;
    private JLabel LCarModelNum;
    private JTextField TCarModelNum;
    private JLabel LLicense;
    private JTextField TLicense;
    private JLabel LState;
    private JTextField TState;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
