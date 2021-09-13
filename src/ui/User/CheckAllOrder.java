/*
 * Created by JFormDesigner on Fri Sep 10 10:51:47 CST 2021
 */

package ui.User;

import javax.swing.table.*;
import control.CarInfoManager;
import control.OrderManager;
import control.PointManager;
import model.BeanCarInfo;
import model.BeanOrder;
import model.BeanPoint;
import model.BeanUser;
import util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;

/**
 * @author Brainrain
 */
public class CheckAllOrder extends JFrame {
    public CheckAllOrder() {
        initComponents();
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanUser user = BeanUser.getCurrentLoginUser();
        DefaultTableModel tblModel = (DefaultTableModel) OrderList.getModel();
        tblModel.setRowCount(0);
        //获取当前用户的所有订单
        try {
            List<BeanOrder> OrderListByUserNum = new OrderManager().SearchOrderInfoByUserInfoNum(user.getUserNum());
            //获取订单的所有信息
            for(int i = 0;i < OrderListByUserNum.size();i ++){
                //订单信息之一
                BeanOrder OneOfOrder = new OrderManager().SearchOrderByOrderNum(OrderListByUserNum.get(i).getOrderNum());
                //根据订单信息里的网点编号获取网点信息
                BeanPoint RtnPointGetByOrder = null;
                BeanPoint GetPointGetByOrder = new PointManager().SearchPointInfoByPointNum(OneOfOrder.getGetPointNum());
                if(OneOfOrder.getRtnPointNum() != null){
                    RtnPointGetByOrder = new PointManager().SearchPointInfoByPointNum(OneOfOrder.getRtnPointNum());
                }
                //根据订单信息里的车辆编号获取车辆信息
                BeanCarInfo CarInfoGetByOrder = new CarInfoManager().SearchCarInfoByCarNum(OneOfOrder.getCarinfoNum());
                Vector v = new Vector();
                v.add(OneOfOrder.getOrderNum());
                v.add(CarInfoGetByOrder.getLicense());
                v.add(CarInfoGetByOrder.getCarType());
                v.add(CarInfoGetByOrder.getBrand());
                v.add(CarInfoGetByOrder.getModelName());
                v.add(GetPointGetByOrder.getPointName()+GetPointGetByOrder.getPointAddress());

                if(OneOfOrder.getRtnPointNum() != 0){
                    v.add(RtnPointGetByOrder.getPointName()+RtnPointGetByOrder.getPointAddress());
                }else{
                    v.add(" ");
                }
                v.add(OneOfOrder.getGetTime());
                v.add(OneOfOrder.getReturnTime());
                v.add(OneOfOrder.getTermdays());
                v.add(OneOfOrder.getTotalMoney());
                v.add(OneOfOrder.getState());
//                System.out.println(OneOfOrder.getState());
                tblModel.addRow(v);
            }
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }



    }

    private void BackToMainButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }


    private void ReturnButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
       /* try {
            Integer.parseInt(TOrderNum.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,"编号不能为空！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Integer OrderNum = Integer.valueOf(TOrderNum.getText());
            BeanOrder.CurrentOrder = new OrderManager().SearchOrderByOrderNum(OrderNum);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        ReturnOrderGeneration dlg = new ReturnOrderGeneration();
        dlg.setVisible(true);
        this.setVisible(false);*/
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        panel1 = new JPanel();
        East = new JPanel();
        panel2 = new JPanel();
        South = new JPanel();
        panel7 = new JPanel();
        panel6 = new JPanel();
        panel5 = new JPanel();
        panel4 = new JPanel();
        panel8 = new JPanel();
        panel10 = new JPanel();
        BackToMainButton = new JButton();
        panel9 = new JPanel();
        panel3 = new JPanel();
        Center = new JPanel();
        TOrderList = new JLabel();
        SCPanel = new JScrollPane();
        OrderList = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(1200, 500));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        setTitle("OrderList");
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

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout());
            }
            West.add(panel1, BorderLayout.WEST);
        }
        contentPane.add(West, BorderLayout.WEST);

        //======== East ========
        {
            East.setLayout(new BorderLayout());

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());
            }
            East.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(East, BorderLayout.EAST);

        //======== South ========
        {
            South.setLayout(new BorderLayout(20, 10));

            //======== panel7 ========
            {
                panel7.setLayout(new GridLayout());
            }
            South.add(panel7, BorderLayout.EAST);

            //======== panel6 ========
            {
                panel6.setLayout(new GridLayout());
            }
            South.add(panel6, BorderLayout.WEST);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout());
            }
            South.add(panel5, BorderLayout.NORTH);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(1, 0, 80, 0));

                //======== panel8 ========
                {
                    panel8.setLayout(new GridLayout(1, 3, 30, 0));

                    //======== panel10 ========
                    {
                        panel10.setLayout(new GridLayout());
                    }
                    panel8.add(panel10);

                    //---- BackToMainButton ----
                    BackToMainButton.setText("\u8fd4\u56de");
                    BackToMainButton.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                    BackToMainButton.addActionListener(e -> BackToMainButtonActionPerformed(e));
                    panel8.add(BackToMainButton);

                    //======== panel9 ========
                    {
                        panel9.setLayout(new GridLayout());
                    }
                    panel8.add(panel9);
                }
                panel4.add(panel8);
            }
            South.add(panel4, BorderLayout.CENTER);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout());
            }
            South.add(panel3, BorderLayout.SOUTH);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout(0, 10));

            //---- TOrderList ----
            TOrderList.setText("\u8ba2\u5355\u5217\u8868");
            TOrderList.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(TOrderList, BorderLayout.NORTH);

            //======== SCPanel ========
            {

                //---- OrderList ----
                OrderList.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u8ba2\u5355\u7f16\u53f7", "\u8f66\u8f86\u724c\u7167", "\u8f66\u8f86\u7c7b\u522b", "\u8f66\u8f86\u54c1\u724c", "\u578b\u53f7\u540d\u79f0", "\u53d6\u8f66\u7f51\u70b9", "\u8fd8\u8f66\u7f51\u70b9", "\u53d6\u8f66\u65e5\u671f", "\u8fd8\u8f66\u65e5\u671f", "\u79df\u8f66\u65f6\u957f", "\u5b9e\u4ed8\u6b3e", "\u8ba2\u5355\u72b6\u6001"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false, false, false, false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = OrderList.getColumnModel();
                    cm.getColumn(1).setMinWidth(60);
                    cm.getColumn(1).setPreferredWidth(60);
                    cm.getColumn(5).setMinWidth(180);
                    cm.getColumn(5).setPreferredWidth(180);
                    cm.getColumn(6).setMinWidth(180);
                    cm.getColumn(6).setPreferredWidth(180);
                    cm.getColumn(7).setMinWidth(60);
                    cm.getColumn(7).setPreferredWidth(60);
                    cm.getColumn(8).setMinWidth(60);
                    cm.getColumn(8).setPreferredWidth(60);
                }
                SCPanel.setViewportView(OrderList);
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
    private JPanel panel1;
    private JPanel East;
    private JPanel panel2;
    private JPanel South;
    private JPanel panel7;
    private JPanel panel6;
    private JPanel panel5;
    private JPanel panel4;
    private JPanel panel8;
    private JPanel panel10;
    private JButton BackToMainButton;
    private JPanel panel9;
    private JPanel panel3;
    private JPanel Center;
    private JLabel TOrderList;
    private JScrollPane SCPanel;
    private JTable OrderList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
