/*
 * Created by JFormDesigner on Sat Sep 11 10:32:55 CST 2021
 */

package ui.Worker;

import control.CouponInfoManager;
import model.BeanCoupon;
import model.BeanWorker;
import util.DbException;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author Brainrain
 */
public class CouponInfoCheck extends JFrame {
    public CouponInfoCheck() {
        initComponents();
    }

    private void BackToMainActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        //窗体打开时加载此网点的所有优惠券信息
        DefaultTableModel tblModel = (DefaultTableModel) TCouponList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanCoupon> CouponInthisPoint = new CouponInfoManager().GetCouponInfoByPointNum(curWorker.getPointNum());
            for(int i = 0;i < CouponInthisPoint.size();i ++){
                BeanCoupon OneOfCoupon = CouponInthisPoint.get(i);
                Vector v = new Vector();
                v.add(OneOfCoupon.getNum());
                v.add(OneOfCoupon.getDescription());
                v.add(OneOfCoupon.getExemption());
                v.add(OneOfCoupon.getStart());
                v.add(OneOfCoupon.getOver());
                tblModel.addRow(v);
            }
        } catch (DbException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void TCouponListMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row = TCouponList.getSelectedRow();
        CouponNumInput.setText(String.valueOf(TCouponList.getValueAt(row,0)));
    }

    private void AddCouponActionPerformed(ActionEvent e) {
        // TODO add your code here
        AddNewCoupon dlg = new AddNewCoupon();
        dlg.setVisible(true);
        this.setVisible(false);
    }

    private void RemoveCouponActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            new CouponInfoManager().RemoveCoupon(Integer.valueOf(CouponNumInput.getText()));
            JOptionPane.showMessageDialog(null,"删除成功！");
            this.setVisible(false);
            CouponInfoCheck dlg = new CouponInfoCheck();
            dlg.setVisible(true);
        } catch (DbException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
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
        CouponNumInput = new JTextField();
        RemoveCoupon = new JButton();
        AddCoupon = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LCouponList = new JLabel();
        SCPanel = new JScrollPane();
        TCouponList = new JTable();

        //======== this ========
        setTitle("CouponInfo");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/office-worker.png")).getImage());
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
                    panel6.add(CouponNumInput);

                    //---- RemoveCoupon ----
                    RemoveCoupon.setText("\u5220\u9664");
                    RemoveCoupon.setIcon(new ImageIcon(getClass().getResource("/static/image/Remo2ve.png")));
                    RemoveCoupon.addActionListener(e -> RemoveCouponActionPerformed(e));
                    panel6.add(RemoveCoupon);

                    //---- AddCoupon ----
                    AddCoupon.setText("\u6dfb\u52a0\u65b0\u4f18\u60e0\u5238");
                    AddCoupon.setIcon(new ImageIcon(getClass().getResource("/static/image/add.png")));
                    AddCoupon.addActionListener(e -> AddCouponActionPerformed(e));
                    panel6.add(AddCoupon);
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

            //---- LCouponList ----
            LCouponList.setText("\u4f18\u60e0\u5238\u5217\u8868");
            LCouponList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LCouponList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TCouponList ----
                TCouponList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u4f18\u60e0\u5238\u7f16\u53f7", "\u4f18\u60e0\u5238\u63cf\u8ff0", "\u51cf\u514d\u91d1\u989d", "\u5f00\u59cb\u65f6\u95f4", "\u7ed3\u675f\u65f6\u95f4"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                TCouponList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TCouponListMouseClicked(e);
                    }
                });
                SCPanel.setViewportView(TCouponList);
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
    private JTextField CouponNumInput;
    private JButton RemoveCoupon;
    private JButton AddCoupon;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LCouponList;
    private JScrollPane SCPanel;
    private JTable TCouponList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
