/*
 * Created by JFormDesigner on Sat Sep 11 22:54:35 CST 2021
 */

package ui.Admin;

import control.OrderManager;
import control.UserManager;
import model.BeanUser;
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
public class AdminOrderCheck extends JFrame {
    public AdminOrderCheck() {
        initComponents();
    }

    private void BackToMainActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void RemoveWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void AddWorkerActionPerformed(ActionEvent e) {
        // TODO add your code here
        try {
            Integer.parseInt(UserNumInput.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"编号不能为空！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Integer UserNum = Integer.parseInt(UserNumInput.getText());
            BeanUser SelectedUser = new BeanUser();
            SelectedUser.setUserNum(UserNum);
            BeanUser.currentLoginUser = SelectedUser;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
        OrderOfSelectedUser dlg = new OrderOfSelectedUser();
        dlg.setVisible(true);
    }

    private void TCouponListMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        //打开窗体时加载所有用户订单信息以及总订单数和总金额
        DefaultTableModel tblModel = (DefaultTableModel) TUserOrderList.getModel();
        tblModel.setRowCount(0);
        try {
            List<BeanUser> UserData= new UserManager().GetAllUserInfo();
            for(int i = 0;i < UserData.size();i ++){
                BeanUser OneOfData = UserData.get(i);
                Integer OrderCnt = new OrderManager().OrderCount(OneOfData.getUserNum());
                Double TotalMoneyCnt = new OrderManager().TotalMoneyCount(OneOfData.getUserNum());
                Vector v = new Vector();
                v.add(OneOfData.getUserNum());
                v.add(OneOfData.getUser_id());
                v.add(OneOfData.getSex());
                v.add(OrderCnt);
                v.add(TotalMoneyCnt);
                tblModel.addRow(v);
            }
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void TUserOrderListMouseClicked(MouseEvent e) {
        int row = TUserOrderList.getSelectedRow();
        UserNumInput.setText(String.valueOf(TUserOrderList.getValueAt(row,0)));
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
        UserNumInput = new JTextField();
        CheckDetail = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Center = new JPanel();
        LUserOrderList = new JLabel();
        SCPanel = new JScrollPane();
        TUserOrderList = new JTable();

        //======== this ========
        setTitle("OrderCheck");
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
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
                    panel6.add(UserNumInput);

                    //---- CheckDetail ----
                    CheckDetail.setText("\u67e5\u770b\u8be6\u7ec6\u4fe1\u606f");
                    CheckDetail.setIcon(new ImageIcon(getClass().getResource("/static/image/search.png")));
                    CheckDetail.addActionListener(e -> AddWorkerActionPerformed(e));
                    panel6.add(CheckDetail);
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

            //---- LUserOrderList ----
            LUserOrderList.setText("\u5ba2\u6237\u8ba2\u5355\u4fe1\u606f");
            LUserOrderList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(LUserOrderList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- TUserOrderList ----
                TUserOrderList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, "", null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u5ba2\u6237\u7f16\u53f7", "\u5ba2\u6237\u540d\u79f0", "\u6027\u522b", "\u8ba2\u5355\u6570", "\u603b\u91d1\u989d"
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
                TUserOrderList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        TUserOrderListMouseClicked(e);
                    }
                });
                SCPanel.setViewportView(TUserOrderList);
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
    private JTextField UserNumInput;
    private JButton CheckDetail;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel Center;
    private JLabel LUserOrderList;
    private JScrollPane SCPanel;
    private JTable TUserOrderList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
