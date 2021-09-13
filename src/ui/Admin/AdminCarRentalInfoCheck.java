/*
 * Created by JFormDesigner on Tue Sep 07 10:13:09 CST 2021
 */

package ui.Admin;

import control.CarInfoManager;
import control.OrderManager;
import model.BeanCarInfo;
import model.BeanPoint;
import ui.User.OrderGeneration;
import util.BaseException;
import util.BusinessException;
import util.DbException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Brainrain
 */
public class AdminCarRentalInfoCheck extends JFrame {
    public AdminCarRentalInfoCheck() {
        initComponents();
    }

    private void CitySelectActionPerformed(ActionEvent e){
        // TODO add your code here
        //根据城市名，网点的选择会发生变化
        //获取现在框中选择的城市
        String CurrentCity = this.CitySelect.getItemAt(this.CitySelect.getSelectedIndex());
        String Point = null;
        //获取网点信息中所有的城市
        List<String> CityList = new ArrayList<>();
        try {
            CityList = new CarInfoManager().GetAllCity();
        } catch (DbException ex) {
            ex.printStackTrace();
        }
        String OneOfCities = null;
        //初始化
        PointSelect.removeAllItems();
        PointSelect.addItem("全部");
        //遍历所有城市
        for(int i = 0;i < CityList.size();i ++){
            OneOfCities = CityList.get(i);
            //找到这个城市的所有网点
            if(CurrentCity.equals(OneOfCities)){
                List<BeanPoint> PointList = new ArrayList<>();
                try {
                    PointList = new CarInfoManager().SearchPointByCity(OneOfCities);
                } catch (DbException ex) {
                    ex.printStackTrace();
                }
                for(int j = 0;j < PointList.size();j ++){
                    Point = PointList.get(j).getPointAddress();
                    PointSelect.addItem(Point);
                }
            }

        }

    }
    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        //初始化
        BrandSelect.removeAllItems();
        BrandSelect.addItem("全部");
        TypeSelect.removeAllItems();
        TypeSelect.addItem("全部");
        //不初始化取车城市是因为取车网点和城市相关联，如果有removeAllItem存在则会导致存在一个空指针，报错
/*        CitySelect.removeAllItems();
        CitySelect.addItem("全部");*/
        List<String> Data = null;
        String OneOfData;
        //获取所有商标并加入到栏中
        try {
            Data = new CarInfoManager().GetAllBrand();
            for(int i = 0;i < Data.size();i ++){
                OneOfData = Data.get(i);
                BrandSelect.addItem(OneOfData);
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }
        //获取所有车辆类别并加入到栏中
        try {
            Data = new CarInfoManager().GetAllCarType();
            for(int i = 0;i < Data.size();i ++){
                OneOfData = Data.get(i);
                TypeSelect.addItem(OneOfData);
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }
        //获取所有取车城市并加入到栏中
        try {
            Data = new CarInfoManager().GetAllCity();
            for(int i = 0;i < Data.size();i ++){
                OneOfData = Data.get(i);
                CitySelect.addItem(OneOfData);
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }


    }
    private void SearchButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        //查询按钮按下后显示所有符合条件的车辆
        String Brand = this.BrandSelect.getItemAt(this.BrandSelect.getSelectedIndex());
        String CarType = this.TypeSelect.getItemAt(this.TypeSelect.getSelectedIndex());
        String CarCondition = this.StateSelect.getItemAt(this.StateSelect.getSelectedIndex());
        String RentalPoint = this.PointSelect.getItemAt(this.PointSelect.getSelectedIndex());
        String RentalCity = this.CitySelect.getItemAt(this.CitySelect.getSelectedIndex());
        DefaultTableModel tableModel =(DefaultTableModel) TBCarInfo.getModel();
        tableModel.setRowCount(0);

        try {
            List<BeanCarInfo> AllInfo = (new CarInfoManager()).GetAllCarInfo(Brand,CarType,CarCondition,RentalPoint,RentalCity);
            for(int i=0;i<AllInfo.size();i++){
                Integer RentalCount = new OrderManager().RentCount(AllInfo.get(i).getCarInfoNum());
                Double TotalProfit = new OrderManager().SelectedCarTotalProfit(AllInfo.get(i).getCarInfoNum());
                /* if(AllInfo.get(i).getCarCondition().equals("报废")){
                    continue;
                }*/
                Vector v = new Vector();
                v.add(AllInfo.get(i).getCarInfoNum());
                v.add(AllInfo.get(i).getLicense());
                v.add(AllInfo.get(i).getCarType());
                v.add(AllInfo.get(i).getBrand());
                v.add(AllInfo.get(i).getModelName());

               /* v.add(AllInfo.get(i).getDisplacement());
                v.add(AllInfo.get(i).getGears());
                v.add(AllInfo.get(i).getSeats());*/
                v.add(AllInfo.get(i).getPrice());
                v.add(RentalCount);
                v.add(TotalProfit);
                v.add(AllInfo.get(i).getCarCondition());
                tableModel.addRow(v);
            }

        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }

    }
    //返回按钮，关闭租车界面
    private void BackToMainActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void TBCarInfoMouseClicked(MouseEvent e) {
        // TODO add your code here
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        East = new JPanel();
        South = new JPanel();
        panel22 = new JPanel();
        panel23 = new JPanel();
        panel24 = new JPanel();
        panel25 = new JPanel();
        panel26 = new JPanel();
        BackToMain = new JButton();
        Center = new JPanel();
        CarInfoSearch = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        panel3 = new JPanel();
        Brand = new JLabel();
        BrandSelect = new JComboBox<>();
        City = new JLabel();
        CitySelect = new JComboBox<>();
        Type = new JLabel();
        TypeSelect = new JComboBox<>();
        Point = new JLabel();
        PointSelect = new JComboBox<>();
        State = new JLabel();
        StateSelect = new JComboBox<>();
        panel8 = new JPanel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        panel5 = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        SearchButton = new JButton();
        panel4 = new JPanel();
        panel9 = new JPanel();
        SCBar = new JScrollPane();
        TBCarInfo = new JTable();

        //======== this ========
        setMinimumSize(new Dimension(700, 600));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/admin.png")).getImage());
        setTitle("CarRentalInfoCheck");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(20, 10));

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
            South.setLayout(new BorderLayout(150, 10));

            //======== panel22 ========
            {
                panel22.setLayout(new GridLayout());
            }
            South.add(panel22, BorderLayout.WEST);

            //======== panel23 ========
            {
                panel23.setLayout(new GridLayout());
            }
            South.add(panel23, BorderLayout.EAST);

            //======== panel24 ========
            {
                panel24.setLayout(new GridLayout());
            }
            South.add(panel24, BorderLayout.SOUTH);

            //======== panel25 ========
            {
                panel25.setLayout(new GridLayout());
            }
            South.add(panel25, BorderLayout.NORTH);

            //======== panel26 ========
            {
                panel26.setLayout(new GridLayout(1, 2, 50, 0));

                //---- BackToMain ----
                BackToMain.setText("\u8fd4\u56de");
                BackToMain.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                BackToMain.addActionListener(e -> BackToMainActionPerformed(e));
                panel26.add(BackToMain);
            }
            South.add(panel26, BorderLayout.CENTER);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout());

            //======== CarInfoSearch ========
            {
                CarInfoSearch.setLayout(new BorderLayout());

                //---- label1 ----
                label1.setText("\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u67e5\u8be2\u6761\u4ef6\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");
                CarInfoSearch.add(label1, BorderLayout.NORTH);

                //---- label2 ----
                label2.setText("  \u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");
                CarInfoSearch.add(label2, BorderLayout.SOUTH);

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(3, 4, 0, 5));

                    //---- Brand ----
                    Brand.setText("\u54c1\u724c");
                    Brand.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(Brand);

                    //---- BrandSelect ----
                    BrandSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u5168\u90e8"
                    }));
                    panel3.add(BrandSelect);

                    //---- City ----
                    City.setText("\u53d6\u8f66\u57ce\u5e02");
                    City.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(City);

                    //---- CitySelect ----
                    CitySelect.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u5168\u90e8"
                    }));
                    CitySelect.addActionListener(e -> CitySelectActionPerformed(e));
                    panel3.add(CitySelect);

                    //---- Type ----
                    Type.setText("\u8f66\u8f86\u7c7b\u522b");
                    Type.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(Type);

                    //---- TypeSelect ----
                    TypeSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u5168\u90e8"
                    }));
                    panel3.add(TypeSelect);

                    //---- Point ----
                    Point.setText("\u53d6\u8f66\u7f51\u70b9");
                    Point.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(Point);

                    //---- PointSelect ----
                    PointSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u5168\u90e8"
                    }));
                    panel3.add(PointSelect);

                    //---- State ----
                    State.setText("\u8f66\u8f86\u72b6\u6001");
                    State.setHorizontalAlignment(SwingConstants.CENTER);
                    panel3.add(State);

                    //---- StateSelect ----
                    StateSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\u5168\u90e8",
                        "\u7a7a\u95f2",
                        "\u5728\u9014"
                    }));
                    panel3.add(StateSelect);

                    //======== panel8 ========
                    {
                        panel8.setLayout(new BorderLayout(20, 0));

                        //======== panel10 ========
                        {
                            panel10.setLayout(new GridLayout());
                        }
                        panel8.add(panel10, BorderLayout.NORTH);

                        //======== panel11 ========
                        {
                            panel11.setLayout(new GridLayout());
                        }
                        panel8.add(panel11, BorderLayout.WEST);

                        //======== panel12 ========
                        {
                            panel12.setLayout(new GridLayout());
                        }
                        panel8.add(panel12, BorderLayout.EAST);

                        //======== panel13 ========
                        {
                            panel13.setLayout(new GridLayout());
                        }
                        panel8.add(panel13, BorderLayout.SOUTH);
                    }
                    panel3.add(panel8);

                    //======== panel5 ========
                    {
                        panel5.setLayout(new BorderLayout(20, 0));

                        //======== panel1 ========
                        {
                            panel1.setLayout(new BorderLayout());
                        }
                        panel5.add(panel1, BorderLayout.WEST);

                        //======== panel2 ========
                        {
                            panel2.setLayout(new BorderLayout());
                        }
                        panel5.add(panel2, BorderLayout.NORTH);

                        //======== panel6 ========
                        {
                            panel6.setLayout(new BorderLayout());
                        }
                        panel5.add(panel6, BorderLayout.EAST);

                        //======== panel7 ========
                        {
                            panel7.setLayout(new BorderLayout());
                        }
                        panel5.add(panel7, BorderLayout.SOUTH);

                        //---- SearchButton ----
                        SearchButton.setText("\u67e5\u8be2");
                        SearchButton.setIcon(new ImageIcon(getClass().getResource("/static/image/search.png")));
                        SearchButton.addActionListener(e -> SearchButtonActionPerformed(e));
                        panel5.add(SearchButton, BorderLayout.CENTER);
                    }
                    panel3.add(panel5);
                }
                CarInfoSearch.add(panel3, BorderLayout.CENTER);
            }
            Center.add(CarInfoSearch, BorderLayout.NORTH);

            //======== panel4 ========
            {
                panel4.setLayout(new BorderLayout());

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout());
                }
                panel4.add(panel9, BorderLayout.SOUTH);

                //======== SCBar ========
                {

                    //---- TBCarInfo ----
                    TBCarInfo.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null, null, null, null, null, null},
                        },
                        new String[] {
                            "\u7f16\u53f7", "\u724c\u7167", "\u8f66\u8f86\u7c7b\u522b", "\u54c1\u724c", "\u578b\u53f7\u540d\u79f0", "\u4ef7\u683c", "\u88ab\u79df\u6b21\u6570", "\u83b7\u53d6\u5229\u6da6", "\u72b6\u6001"
                        }
                    ) {
                        boolean[] columnEditable = new boolean[] {
                            false, true, true, true, true, true, false, true, true
                        };
                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return columnEditable[columnIndex];
                        }
                    });
                    TBCarInfo.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            TBCarInfoMouseClicked(e);
                        }
                    });
                    SCBar.setViewportView(TBCarInfo);
                }
                panel4.add(SCBar, BorderLayout.CENTER);
            }
            Center.add(panel4, BorderLayout.CENTER);
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
    private JPanel panel22;
    private JPanel panel23;
    private JPanel panel24;
    private JPanel panel25;
    private JPanel panel26;
    private JButton BackToMain;
    private JPanel Center;
    private JPanel CarInfoSearch;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel3;
    private JLabel Brand;
    private JComboBox<String> BrandSelect;
    private JLabel City;
    private JComboBox<String> CitySelect;
    private JLabel Type;
    private JComboBox<String> TypeSelect;
    private JLabel Point;
    private JComboBox<String> PointSelect;
    private JLabel State;
    private JComboBox<String> StateSelect;
    private JPanel panel8;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JPanel panel5;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel6;
    private JPanel panel7;
    private JButton SearchButton;
    private JPanel panel4;
    private JPanel panel9;
    private JScrollPane SCBar;
    private JTable TBCarInfo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
