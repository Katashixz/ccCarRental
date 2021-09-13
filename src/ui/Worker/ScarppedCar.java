/*
 * Created by JFormDesigner on Sat Sep 11 14:38:50 CST 2021
 */

package ui.Worker;

import control.CarInfoManager;
import control.ScarpManager;
import model.BeanCarInfo;
import model.BeanScarppedCar;
import model.BeanWorker;
import util.BaseException;
import util.BusinessException;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Brainrain
 */
public class ScarppedCar extends JFrame {
    public ScarppedCar() {
        initComponents();
    }


    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载此网点的所有车辆信息
        DefaultTableModel tblModel = (DefaultTableModel) TCarInfoList.getModel();
        tblModel.setRowCount(0);
        try {
            //一口气获取了和车辆有关的所有信息
            List<BeanCarInfo> CarInfoInThisPoint = new CarInfoManager().SeachCarInfoByPointNum(curWorker.getPointNum());
            for(int i = 0;i < CarInfoInThisPoint.size();i ++){
                BeanCarInfo OneOfCarInfo = CarInfoInThisPoint.get(i);
                if(OneOfCarInfo.CarCondition.equals("报废")){
                    continue;
                }
                Vector v = new Vector();
                v.add(OneOfCarInfo.getCarInfoNum());
                v.add(OneOfCarInfo.getLicense());
                v.add(OneOfCarInfo.getBrand());
                v.add(OneOfCarInfo.getModelName());
                v.add(OneOfCarInfo.getPrice());
                v.add(OneOfCarInfo.getCarCondition());
                tblModel.addRow(v);
            }
        } catch (BaseException ex) {
            ex.printStackTrace();
        }

    }

    private void TCarInfoListMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row = TCarInfoList.getSelectedRow();
        CarInfoNumInput.setText(String.valueOf(TCarInfoList.getValueAt(row,0)));
    }

    private void BackToMainActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void ScarppedButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer InputCarInfoNum = Integer.valueOf(CarInfoNumInput.getText());
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        try {

            //修改车辆状态为“报废”
            BeanCarInfo SelectedCarInfo = new CarInfoManager().SearchCarInfoByCarNum(InputCarInfoNum);
            if(SelectedCarInfo.getCarCondition().equals("在途")){
                throw new BusinessException("车辆正在使用！无法报废！");
            }
            new CarInfoManager().ScarppedCar(SelectedCarInfo);
            //上传报废信息
            BeanScarppedCar ScCar = new BeanScarppedCar();
            ScCar.setDescription(Description.getText());
            ScCar.setCarInfoNum(SelectedCarInfo.getCarInfoNum());
            ScCar.setWokerNum(curWorker.getWorkerNum());
            ScCar.setScarppedTime(new Date(System.currentTimeMillis()));
            new ScarpManager().AddScarppedCarInfo(ScCar);
            JOptionPane.showMessageDialog(null,"报废成功！");
            this.setVisible(false);
            ScarppedCar dlg = new ScarppedCar();
            dlg.setVisible(true);

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void DescriptionMouseClicked(MouseEvent e) {
        // TODO add your code here
        Description.setText("");
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
        Description = new JTextField();
        ScarppedButton = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LCarInfoList = new JLabel();
        SCPanel = new JScrollPane();
        TCarInfoList = new JTable();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/static/image/office-worker.png")).getImage());
        setTitle("Scrapped Vehicle  ");
        setMinimumSize(new Dimension(600, 400));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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
            South.setLayout(new BorderLayout(80, 30));

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

                    //---- Description ----
                    Description.setText("\u8bf7\u586b\u5199\u62a5\u5e9f\u8bf4\u660e");
                    Description.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            DescriptionMouseClicked(e);
                        }
                    });
                    panel6.add(Description);

                    //---- ScarppedButton ----
                    ScarppedButton.setText("\u62a5\u5e9f");
                    ScarppedButton.setIcon(new ImageIcon(getClass().getResource("/static/image/rem3ove.png")));
                    ScarppedButton.addActionListener(e -> ScarppedButtonActionPerformed(e));
                    panel6.add(ScarppedButton);
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
            LCarInfoList.setText("\u7f51\u70b9\u8f66\u8f86\u5217\u8868");
            LCarInfoList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LCarInfoList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TCarInfoList ----
                TCarInfoList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u8f66\u8f86\u4fe1\u606f\u7f16\u53f7", "\u724c\u7167", "\u54c1\u724c", "\u8f66\u578b\u540d\u79f0", "\u8f66\u8f86\u4ef7\u683c", "\u72b6\u6001"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                TCarInfoList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TCarInfoListMouseClicked(e);
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
    private JTextField Description;
    private JButton ScarppedButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LCarInfoList;
    private JScrollPane SCPanel;
    private JTable TCarInfoList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
