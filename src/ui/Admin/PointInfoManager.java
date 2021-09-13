/*
 * Created by JFormDesigner on Sat Sep 11 20:56:07 CST 2021
 */

package ui.Admin;

import control.CarInfoManager;
import control.PointManager;
import control.WorkerManager;
import model.BeanPoint;
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
public class PointInfoManager extends JFrame {
    public PointInfoManager() {
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
        int row = TPointList.getSelectedRow();
        PointNumInput.setText(String.valueOf(TPointList.getValueAt(row,0)));
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载所有网点信息
        DefaultTableModel tblModel = (DefaultTableModel) TPointList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanPoint> PointData = new PointManager().GetAllPointInfo();
            for(int i = 0;i < PointData.size();i ++){
                BeanPoint OneOfPoint = PointData.get(i);
                Vector v = new Vector();
                v.add(OneOfPoint.getPointNum());
                v.add(OneOfPoint.getPointName());
                v.add(OneOfPoint.getPointCity());
                v.add(OneOfPoint.getPointAddress());
                v.add(OneOfPoint.getPointPhoneNum());
                tblModel.addRow(v);
            }

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void RemoveWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer PointNum;
        try{
            PointNum = Integer.valueOf(PointNumInput.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确的编号！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            if(new WorkerManager().isExistWorker(PointNum)){
                throw new BusinessException("网点有员工存在，无法删除！");
            }
            if(new CarInfoManager().isExistCarInPoint(PointNum)){
                throw new BusinessException("网点有车辆存在，无法删除！");

            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            new PointManager().RemoveSelectedPoint(PointNum);
            JOptionPane.showMessageDialog(null,"删除成功！");
            this.setVisible(false);
            PointInfoManager dlg = new PointInfoManager();
            dlg.setVisible(true);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void AddWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        AddNewPoint dlg = new AddNewPoint();
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
        PointNumInput = new JTextField();
        RemovePoint = new JButton();
        AddPoint = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LPointList = new JLabel();
        SCPanel = new JScrollPane();
        TPointList = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("PointManager");
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
                    panel6.add(PointNumInput);

                    //---- RemovePoint ----
                    RemovePoint.setText("\u5220\u9664");
                    RemovePoint.setIcon(new ImageIcon(getClass().getResource("/static/image/Remo2ve.png")));
                    RemovePoint.addActionListener(e -> RemoveWorkerActionPerformed(e));
                    panel6.add(RemovePoint);

                    //---- AddPoint ----
                    AddPoint.setText("\u6dfb\u52a0\u65b0\u7f51\u70b9");
                    AddPoint.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                    AddPoint.addActionListener(e -> AddWorkerActionPerformed(e));
                    panel6.add(AddPoint);
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

            //---- LPointList ----
            LPointList.setText("\u7f51\u70b9\u5217\u8868");
            LPointList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LPointList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TPointList ----
                TPointList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u7f51\u70b9\u7f16\u53f7", "\u7f51\u70b9\u540d\u79f0", "\u7f51\u70b9\u6240\u5728\u57ce\u5e02", "\u7f51\u70b9\u8be6\u7ec6\u5730\u5740", "\u7f51\u70b9\u7535\u8bdd"
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
                TPointList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TCouponListMouseClicked(e);
                    }
                });
                SCPanel.setViewportView(TPointList);
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
    private JTextField PointNumInput;
    private JButton RemovePoint;
    private JButton AddPoint;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LPointList;
    private JScrollPane SCPanel;
    private JTable TPointList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
