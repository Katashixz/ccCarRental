/*
 * Created by JFormDesigner on Sat Sep 11 20:56:07 CST 2021
 */

package ui.Admin;

import control.CarInfoManager;
import model.BeanCarInfo;
import model.BeanWorker;
import util.BaseException;
import util.BusinessException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

/**
 * @author Brainrain
 */
public class CarTypeManager extends JFrame {
    public CarTypeManager() {
        initComponents();
    }

    private void BackToMainActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void RemoveCouponActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void AddCouponActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void TCouponListMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row = TCarInfoList.getSelectedRow();
        CarTypeNumInput.setText(String.valueOf(TCarInfoList.getValueAt(row,0)));
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载所有车辆类别信息
        DefaultTableModel tblModel = (DefaultTableModel) TCarInfoList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanCarInfo> CarInfoData = new control.CarInfoManager().GetAllCarTypeRTNBean();
            for(int i = 0;i < CarInfoData.size();i ++){
                BeanCarInfo OneOfData = CarInfoData.get(i);
                Vector v = new Vector();
                v.add(OneOfData.getCarTypeNum());
                v.add(OneOfData.getCarType());
                v.add(OneOfData.getTypeDescription());
                tblModel.addRow(v);
            }

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void RemoveActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer CarTypeNum;
        try{
            CarTypeNum = Integer.valueOf(CarTypeNumInput.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确的类型编号！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            //删除汽车类别的时候，如果有相关车型存在，就要给出提示，并禁止删除
            if(new CarInfoManager().isExistModel(CarTypeNum)){
                throw new BusinessException("车类别已有相关车型！无法删除！");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //删除车型
            new control.CarInfoManager().RemoveCarType(CarTypeNum);
            JOptionPane.showMessageDialog(null,"删除成功！");
            this.setVisible(false);
            CarTypeManager dlg = new CarTypeManager();
            dlg.setVisible(true);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void AddTypeActionPerformed(ActionEvent e) {
        // TODO add your code here
        AddNewCarType dlg = new AddNewCarType();
        dlg.setVisible(true);
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel3 = new JPanel();
        panel6 = new JPanel();
        BackToMain = new JButton();
        CarTypeNumInput = new JTextField();
        RemoveCarTye = new JButton();
        AddCarType = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LCarInfoList = new JLabel();
        SCPanel = new JScrollPane();
        TCarInfoList = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("CarTypeManager");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));

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
            South.setLayout(new BorderLayout(10, 10));

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout());
            }
            South.add(panel4, BorderLayout.SOUTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout());
            }
            South.add(panel5, BorderLayout.EAST);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(1, 0, 10, 0));

                //======== panel6 ========
                {
                    panel6.setLayout(new GridLayout(1, 4, 10, 0));

                    //---- BackToMain ----
                    BackToMain.setText("\u8fd4\u56de");
                    BackToMain.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                    BackToMain.addActionListener(e -> BackToMainActionPerformed(e));
                    panel6.add(BackToMain);
                    panel6.add(CarTypeNumInput);

                    //---- RemoveCarTye ----
                    RemoveCarTye.setText("\u5220\u9664");
                    RemoveCarTye.setIcon(new ImageIcon(getClass().getResource("/static/image/Remo2ve.png")));
                    RemoveCarTye.addActionListener(e -> RemoveActionPerformed(e));
                    panel6.add(RemoveCarTye);

                    //---- AddCarType ----
                    AddCarType.setText("\u6dfb\u52a0\u65b0\u8f66\u7c7b\u4fe1\u606f");
                    AddCarType.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                    AddCarType.addActionListener(e -> AddTypeActionPerformed(e));
                    panel6.add(AddCarType);
                }
                panel3.add(panel6);
            }
            South.add(panel3, BorderLayout.CENTER);

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout());
            }
            South.add(panel1, BorderLayout.WEST);

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());
            }
            South.add(panel2, BorderLayout.NORTH);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //---- LCarInfoList ----
            LCarInfoList.setText("\u8f66\u8f86\u7c7b\u522b\u5217\u8868");
            LCarInfoList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LCarInfoList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TCarInfoList ----
                TCarInfoList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null},
                        {null, null, null},
                    },
                    new String[] {
                        "\u7c7b\u522b\u7f16\u53f7", "\u7c7b\u522b\u540d\u79f0", "\u7c7b\u522b\u63cf\u8ff0"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                TCarInfoList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TCouponListMouseClicked(e);
                    }
                });
                SCPanel.setViewportView(TCarInfoList);
            }
            Center.add(SCPanel, BorderLayout.CENTER);
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
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel3;
    private JPanel panel6;
    private JButton BackToMain;
    private JTextField CarTypeNumInput;
    private JButton RemoveCarTye;
    private JButton AddCarType;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LCarInfoList;
    private JScrollPane SCPanel;
    private JTable TCarInfoList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
