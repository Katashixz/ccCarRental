/*
 * Created by JFormDesigner on Sat Sep 11 21:37:20 CST 2021
 */

package ui.Admin;

import control.CarInfoManager;
import model.BeanCarInfo;
import util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Brainrain
 */
public class AddNewCarType extends JFrame {
    public AddNewCarType() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        CarTypeManager dlg = new CarTypeManager();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here

        BeanCarInfo NewCarInfo = new BeanCarInfo();
        NewCarInfo.setCarType(TTypeName.getText());
        NewCarInfo.setTypeDescription(TCarTypeDes.getText());
        try {
            new CarInfoManager().AddCarType(NewCarInfo);
            JOptionPane.showMessageDialog(null,"Ìí¼Ó³É¹¦£¡");

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        ui.Admin.CarTypeManager dlg = new ui.Admin.CarTypeManager();
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
        LTypeName = new JLabel();
        TTypeName = new JTextField();
        LCarTypeDes = new JLabel();
        TCarTypeDes = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("AddNewCarModel");
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
            Title.setText("\u6dfb\u52a0\u65b0\u8f66\u8f86\u7c7b\u522b");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(3, 2, 20, 10));

                //---- LTypeName ----
                LTypeName.setText("\u7c7b\u522b\u540d\u79f0");
                LTypeName.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LTypeName);
                panel5.add(TTypeName);

                //---- LCarTypeDes ----
                LCarTypeDes.setText("\u7c7b\u522b\u63cf\u8ff0");
                LCarTypeDes.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LCarTypeDes);
                panel5.add(TCarTypeDes);

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
    private JLabel LTypeName;
    private JTextField TTypeName;
    private JLabel LCarTypeDes;
    private JTextField TCarTypeDes;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
