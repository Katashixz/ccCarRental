/*
 * Created by JFormDesigner on Sat Sep 11 20:56:07 CST 2021
 */

package ui.Admin;

import control.WorkerManager;
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
public class CarInfoManager extends JFrame {
    public CarInfoManager() {
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
        CarInfoNumInput.setText(String.valueOf(TCarInfoList.getValueAt(row,0)));
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载所有车辆信息
        DefaultTableModel tblModel = (DefaultTableModel) TCarInfoList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanCarInfo> CarInfoData = new control.CarInfoManager().GetAllCarInfoWithoutCondition();
            for(int i = 0;i < CarInfoData.size();i ++){
                BeanCarInfo OneOfData = CarInfoData.get(i);
                Vector v = new Vector();
                v.add(OneOfData.getCarInfoNum());
                v.add(OneOfData.getAtPoint());
                v.add(OneOfData.getModelNum());
                v.add(OneOfData.getLicense());
                v.add(OneOfData.getCarCondition());
                tblModel.addRow(v);
            }

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void RemoveActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer CarInfoNum;
        try{
            CarInfoNum = Integer.valueOf(CarInfoNumInput.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确的车辆信息编号！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            String State = new control.CarInfoManager().CheckCarState(CarInfoNum);
            if(State.equals("在途")){
                throw new BusinessException("车辆正在被使用！无法删除！");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            new control.CarInfoManager().RemoveCarInfo(CarInfoNum);
            JOptionPane.showMessageDialog(null,"删除成功！");
            this.setVisible(false);
            CarInfoManager dlg = new CarInfoManager();
            dlg.setVisible(true);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void AddWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        AddNewCarInfo dlg = new AddNewCarInfo();
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
        CarInfoNumInput = new JTextField();
        RemoveCarInfo = new JButton();
        AddCarInfo = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LCarInfoList = new JLabel();
        SCPanel = new JScrollPane();
        TCarInfoList = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("CarInfoManager");
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
                    panel6.add(CarInfoNumInput);

                    //---- RemoveCarInfo ----
                    RemoveCarInfo.setText("\u5220\u9664");
                    RemoveCarInfo.setIcon(new ImageIcon(getClass().getResource("/static/image/Remo2ve.png")));
                    RemoveCarInfo.addActionListener(e -> RemoveActionPerformed(e));
                    panel6.add(RemoveCarInfo);

                    //---- AddCarInfo ----
                    AddCarInfo.setText("\u6dfb\u52a0\u65b0\u8f66\u8f86\u4fe1\u606f");
                    AddCarInfo.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                    AddCarInfo.addActionListener(e -> AddWorkerActionPerformed(e));
                    panel6.add(AddCarInfo);
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
            LCarInfoList.setText("\u8f66\u8f86\u4fe1\u606f\u5217\u8868");
            LCarInfoList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LCarInfoList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TCarInfoList ----
                TCarInfoList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u8f66\u8f86\u4fe1\u606f\u7f16\u53f7", "\u6240\u5c5e\u7f51\u70b9", "\u6240\u5c5e\u8f66\u578b", "\u724c\u7167\u53f7", "\u8f66\u8f86\u72b6\u6001"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, true
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
    private JTextField CarInfoNumInput;
    private JButton RemoveCarInfo;
    private JButton AddCarInfo;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LCarInfoList;
    private JScrollPane SCPanel;
    private JTable TCarInfoList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
