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
public class AddNewCarModel extends JFrame {
    public AddNewCarModel() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        CarModelManager dlg = new CarModelManager();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here

        Integer TypeNum;
        Double Displacement;
        Integer Seats;
        Double Price;
        try{
            TypeNum = Integer.valueOf(TTypeNum.getText());
            Seats = Integer.valueOf(TSeats.getText());
            Price = Double.valueOf(TPrice.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入数字！","错误",JOptionPane.ERROR_MESSAGE);
            return;

        }
        try{
            Displacement = Double.valueOf(TDisplacement.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确排量！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        BeanCarInfo NewCarInfo = new BeanCarInfo();
        NewCarInfo.setCarTypeNum(TypeNum);
        NewCarInfo.setModelName(TModelName.getText());
        NewCarInfo.setBrand(TBrand.getText());
        NewCarInfo.setDisplacement(Displacement);
        NewCarInfo.setGears(TGears.getText());
        NewCarInfo.setSeats(Seats);
        NewCarInfo.setPrice(Price);
        try {
            new CarInfoManager().AddModel(NewCarInfo);
            JOptionPane.showMessageDialog(null,"添加成功！");

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setVisible(false);
        CarModelManager dlg = new CarModelManager();
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
        LTypeNum = new JLabel();
        TTypeNum = new JTextField();
        LModelName = new JLabel();
        TModelName = new JTextField();
        LBrand = new JLabel();
        TBrand = new JTextField();
        LDisplacement = new JLabel();
        TDisplacement = new JTextField();
        LGears = new JLabel();
        TGears = new JTextField();
        LSeats = new JLabel();
        TSeats = new JTextField();
        LPrice = new JLabel();
        TPrice = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("AddNewCarInfo");
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
            Title.setText("\u6dfb\u52a0\u65b0\u8f66\u8f86\u7c7b\u578b");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(8, 2, 20, 10));

                //---- LTypeNum ----
                LTypeNum.setText("\u7c7b\u522b\u7f16\u53f7");
                LTypeNum.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LTypeNum);
                panel5.add(TTypeNum);

                //---- LModelName ----
                LModelName.setText("\u8f66\u578b\u540d\u79f0");
                LModelName.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LModelName);
                panel5.add(TModelName);

                //---- LBrand ----
                LBrand.setText("\u54c1\u724c");
                LBrand.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LBrand);
                panel5.add(TBrand);

                //---- LDisplacement ----
                LDisplacement.setText("\u6392\u91cf");
                LDisplacement.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LDisplacement);
                panel5.add(TDisplacement);

                //---- LGears ----
                LGears.setText("\u6392\u6321");
                LGears.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LGears);
                panel5.add(TGears);

                //---- LSeats ----
                LSeats.setText("\u5ea7\u4f4d\u6570");
                LSeats.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LSeats);
                panel5.add(TSeats);

                //---- LPrice ----
                LPrice.setText("\u5355\u65e5\u4ef7\u683c");
                LPrice.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LPrice);
                panel5.add(TPrice);

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
    private JLabel LTypeNum;
    private JTextField TTypeNum;
    private JLabel LModelName;
    private JTextField TModelName;
    private JLabel LBrand;
    private JTextField TBrand;
    private JLabel LDisplacement;
    private JTextField TDisplacement;
    private JLabel LGears;
    private JTextField TGears;
    private JLabel LSeats;
    private JTextField TSeats;
    private JLabel LPrice;
    private JTextField TPrice;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
