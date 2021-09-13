/*
 * Created by JFormDesigner on Sat Sep 11 20:56:07 CST 2021
 */

package ui.Admin;

import control.WorkerManager;
import model.BeanWorker;
import util.BaseException;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Brainrain
 */
public class WorkerInfoManager extends JFrame {
    public WorkerInfoManager() {
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
        int row = TWorkerList.getSelectedRow();
        WorkerNumInput.setText(String.valueOf(TWorkerList.getValueAt(row,0)));
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载所有员工信息
        DefaultTableModel tblModel = (DefaultTableModel) TWorkerList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanWorker> WorkerData = new WorkerManager().GetAllWorker();
            for(int i = 0;i < WorkerData.size();i ++){
                BeanWorker OneOfWorker = WorkerData.get(i);
                Vector v = new Vector();
                v.add(OneOfWorker.getWorkerNum());
                v.add(OneOfWorker.getPointNum());
                v.add(OneOfWorker.getWorkerName());
                v.add(OneOfWorker.getWorkerPwd());
                tblModel.addRow(v);
            }

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    private void RemoveWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        Integer WorkerNum;
        try{
             WorkerNum = Integer.valueOf(WorkerNumInput.getText());
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"请输入正确的员工编号！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            new WorkerManager().removeWorker(Integer.valueOf(WorkerNum));
            JOptionPane.showMessageDialog(null,"删除成功！");
            this.setVisible(false);
            WorkerInfoManager dlg = new WorkerInfoManager();
            dlg.setVisible(true);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void AddWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        AddNewWorker dlg = new AddNewWorker();
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
        WorkerNumInput = new JTextField();
        RemoveWorker = new JButton();
        AddWorker = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LWorkerList = new JLabel();
        SCPanel = new JScrollPane();
        TWorkerList = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("WorkerManager");
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
                    panel6.add(WorkerNumInput);

                    //---- RemoveWorker ----
                    RemoveWorker.setText("\u5220\u9664");
                    RemoveWorker.setIcon(new ImageIcon(getClass().getResource("/static/image/Remo2ve.png")));
                    RemoveWorker.addActionListener(e -> RemoveWorkerActionPerformed(e));
                    panel6.add(RemoveWorker);

                    //---- AddWorker ----
                    AddWorker.setText("\u6dfb\u52a0\u65b0\u5458\u5de5");
                    AddWorker.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                    AddWorker.addActionListener(e -> AddWorkerActionPerformed(e));
                    panel6.add(AddWorker);
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

            //---- LWorkerList ----
            LWorkerList.setText("\u5458\u5de5\u5217\u8868");
            LWorkerList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LWorkerList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TWorkerList ----
                TWorkerList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "\u5458\u5de5\u7f16\u53f7", "\u6240\u5c5e\u7f51\u70b9", "\u5458\u5de5\u540d\u79f0", "\u5458\u5de5\u5bc6\u7801"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                TWorkerList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TCouponListMouseClicked(e);
                    }
                });
                SCPanel.setViewportView(TWorkerList);
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
    private JTextField WorkerNumInput;
    private JButton RemoveWorker;
    private JButton AddWorker;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LWorkerList;
    private JScrollPane SCPanel;
    private JTable TWorkerList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
