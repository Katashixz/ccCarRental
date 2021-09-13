/*
 * Created by JFormDesigner on Wed Sep 08 22:15:40 CST 2021
 */

package ui.User;

import control.*;
import model.*;
import util.BaseException;
import util.BusinessException;
import util.DbException;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class OrderGeneration extends JFrame {
    public OrderGeneration() {
        initComponents();
    }
    //���봰��ʱ�Զ����ú�������Ϣ
    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanCarInfo bsc = BeanCarInfo.getCurrentConditions();
        BeanUser user = BeanUser.getCurrentLoginUser();
        TUserName.setText(user.getUser_id());
        TUserName.setEditable(false);
        TLicense.setText(bsc.getLicense());
        TLicense.setEditable(false);
        TDescription.setText(bsc.getTypeDescription());
        TDescription.setEditable(false);
        TBrand_Model.setText(bsc.getBrand()+"-"+bsc.getModelName());
        TBrand_Model.setEditable(false);
        TOPrice.setText(String.valueOf(bsc.getPrice())+"/��");
        TOPrice.setEditable(false);
        TCartype.setText(bsc.getCarType());
        TCartype.setEditable(false);
//        TTotalPrice.setEditable(false);
        TGetTime.setText("ʾ����2021-9-9");
//        TDiscount.setEditable(false);
        //������Ϣ��ȡ����ӵ�ѡ������
        String Point = null;
        //��ȡ������Ϣ�����еĳ���
        List<String> CityList = null;
        try {
            CityList = new CarInfoManager().GetAllCity();
        } catch (DbException ex) {
            ex.printStackTrace();
        }
        String OneOfCities = null;
        //�������г���
        for(int i = 0;i < CityList.size();i ++){
            OneOfCities = CityList.get(i);
            //�ҵ�������е���������
                List<BeanPoint> PointList = new ArrayList<>();
                try {
                    PointList = new CarInfoManager().SearchPointByCity(OneOfCities);
                } catch (DbException ex) {
                    ex.printStackTrace();
                }
                for(int j = 0;j < PointList.size();j ++){
                    Point = PointList.get(j).getPointAddress();
                    SGetPoint.addItem(PointList.get(j).getPointNum()+"-"+OneOfCities+"-"+Point);
                }

        }
        //�Ż�ȯ��Ϣ��ȡ����ӵ�ѡ������
       /* try {
            //��ʼ��
            TCouponSelect.removeAllItems();
            TCouponSelect.addItem("��ʹ���Ż�ȯ");
            List<BeanCoupon> CouponData = new CouponInfoManager().GetAllCouponInfo();
            for(int i = 0;i < CouponData.size();i ++){
                TCouponSelect.addItem(CouponData.get(i).getDiscription());
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }*/
        //�ۿ���Ϣ��ѡ�����ᳵ���ˢ����ʾ

//        Integer CarNum = BeanSearchConditions.getCurrentConditions();


    }

    private void BackToCarInfoActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void OrderConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        BeanUser curUser = BeanUser.getCurrentLoginUser();
        BeanCarInfo curCarInfo = BeanCarInfo.getCurrentConditions();
        String RentPoint = String.valueOf(this.SGetPoint.getItemAt(this.SGetPoint.getSelectedIndex()));
        RentPoint = RentPoint.substring(0,RentPoint.indexOf('-'));
        BeanOrder curOrderInfo = new BeanOrder();
        //�������е�order��Ϣ
        curOrderInfo.setUserinfoNum(curUser.getUserNum());
        curOrderInfo.setCarinfoNum(curCarInfo.getCarInfoNum());
        curOrderInfo.setGetPointNum(Integer.valueOf(RentPoint));
        //��ȡԭ�ۣ���Ϊ��ȡ���ַ�����ʽ��xxxx/�죬��Ҫȥ���ַ��������ֲ���
        curOrderInfo.setOriginMoney(Double.valueOf(TOPrice.getText().substring(0,TOPrice.getText().indexOf("/"))));
//        System.out.println(curOrderInfo.getOriginMoney());
        //�ַ���ת��Ϊdate
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date GetDate =fmt.parse(TGetTime.getText());
            //����ʱ�������ʱ��Աȣ��������ʱ����������ʱ�䱨��
            java.util.Date curDate = new Date(System.currentTimeMillis());
            //����ʱ����Ϊ����Сʱ�����������ͬһ�죬����ʱ��϶���������ʱ�䣬����Ҫ������ʱ����ǰ��һ��
            Calendar cal = Calendar.getInstance();
            cal.setTime(curDate);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            curDate = cal.getTime();
            if(GetDate.compareTo(curDate) < 0){
                throw new BusinessException("ȡ��ʱ�䲻����������ʱ�䣡");
            }
            curOrderInfo.setGetTime(GetDate);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"\n���ڸ�ʽ����"+ex.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        //�������ȹ��ܣ������ж����е�ȡ�������복������������жԱ�
        if(!curOrderInfo.getGetPointNum().equals(curCarInfo.getAtPoint())){
            BeanTransfer NewInfo = new BeanTransfer();
            NewInfo.setCarInfoNum(curOrderInfo.getCarinfoNum());
            NewInfo.setTransOutNum(curCarInfo.getAtPoint());
            NewInfo.setTransInNum(curOrderInfo.getGetPointNum());
            NewInfo.setTransTime(curOrderInfo.getGetTime());
            try {
                new TransferManager().InsertTransferInfo(NewInfo);
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage()+"\n���ɳ���������Ϣʧ�ܣ�","����",JOptionPane.ERROR_MESSAGE);

            }
            //����������Ҫʱ�䣬Ĭ��2�졣����ȡ��ʱ��Ҫ���޸�
            Calendar cal = Calendar.getInstance();
            cal.setTime(curOrderInfo.getGetTime());
            cal.add(Calendar.DAY_OF_MONTH,2);
            curOrderInfo.setGetTime(cal.getTime());
            JOptionPane.showMessageDialog(null,"���ڴ����������賵����������Ҫ������ȡ��ʱ���ݻ�����");

        }
        try {
            curOrderInfo.setState("������");
            new OrderManager().uploadOrderInfo(curOrderInfo);
            JOptionPane.showMessageDialog(null,"�µ��ɹ���");
            new CarInfoManager().updateCarInfoState(curOrderInfo.getCarinfoNum(),"��;");
            this.setVisible(false);
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"����ʧ��",JOptionPane.ERROR_MESSAGE);

        }
//        System.out.println(Date.valueOf(TGetTime.getText()));

    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        South = new JPanel();
        panel12 = new JPanel();
        BackToCarInfo = new JButton();
        OrderConfirm = new JButton();
        panel9 = new JPanel();
        panel10 = new JPanel();
        panel11 = new JPanel();
        East = new JPanel();
        Center = new JPanel();
        label1 = new JLabel();
        panel1 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        OUserName = new JLabel();
        TUserName = new JTextField();
        OLicense = new JLabel();
        TLicense = new JTextField();
        OGetTime = new JLabel();
        TGetTime = new JTextField();
        OGetPoint = new JLabel();
        SGetPoint = new JComboBox();
        OCartype = new JLabel();
        TCartype = new JTextField();
        ODescription = new JLabel();
        TDescription = new JTextField();
        OBrand_Model = new JLabel();
        TBrand_Model = new JTextField();
        OOPrice = new JLabel();
        TOPrice = new JTextField();

        //======== this ========
        setMinimumSize(new Dimension(600, 400));
        setIconImage(new ImageIcon(getClass().getResource("/static/image/car2.png")).getImage());
        setTitle("Order");
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

        //======== South ========
        {
            South.setLayout(new BorderLayout(80, 20));

            //======== panel12 ========
            {
                panel12.setLayout(new GridLayout(1, 3, 40, 0));

                //---- BackToCarInfo ----
                BackToCarInfo.setText("\u8fd4\u56de");
                BackToCarInfo.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                BackToCarInfo.addActionListener(e -> BackToCarInfoActionPerformed(e));
                panel12.add(BackToCarInfo);

                //---- OrderConfirm ----
                OrderConfirm.setText("\u786e\u8ba4\u8ba2\u5355");
                OrderConfirm.setIcon(new ImageIcon(getClass().getResource("/static/image/����_ȷ��_manifest_confirm.png")));
                OrderConfirm.addActionListener(e -> OrderConfirmActionPerformed(e));
                panel12.add(OrderConfirm);
            }
            South.add(panel12, BorderLayout.CENTER);

            //======== panel9 ========
            {
                panel9.setLayout(new GridLayout());
            }
            South.add(panel9, BorderLayout.WEST);

            //======== panel10 ========
            {
                panel10.setLayout(new GridLayout());
            }
            South.add(panel10, BorderLayout.EAST);

            //======== panel11 ========
            {
                panel11.setLayout(new GridLayout());
            }
            South.add(panel11, BorderLayout.SOUTH);
        }
        contentPane.add(South, BorderLayout.SOUTH);

        //======== East ========
        {
            East.setLayout(new BorderLayout());
        }
        contentPane.add(East, BorderLayout.EAST);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout(40, 10));

            //---- label1 ----
            label1.setText("\u79df\u8f66\u8ba2\u5355");
            label1.setHorizontalTextPosition(SwingConstants.LEFT);
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(label1, BorderLayout.NORTH);

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout());

                //======== panel5 ========
                {
                    panel5.setLayout(new BorderLayout());

                    //======== panel6 ========
                    {
                        panel6.setLayout(new GridLayout());
                    }
                    panel5.add(panel6, BorderLayout.WEST);

                    //======== panel7 ========
                    {
                        panel7.setLayout(new GridLayout());
                    }
                    panel5.add(panel7, BorderLayout.NORTH);
                }
                panel1.add(panel5);
            }
            Center.add(panel1, BorderLayout.SOUTH);

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());
            }
            Center.add(panel2, BorderLayout.WEST);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout());
            }
            Center.add(panel3, BorderLayout.EAST);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(8, 2, 0, 10));

                //---- OUserName ----
                OUserName.setText("\u7528\u6237\u540d");
                OUserName.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OUserName);
                panel4.add(TUserName);

                //---- OLicense ----
                OLicense.setText("\u8f66\u8f86\u724c\u7167");
                OLicense.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OLicense);
                panel4.add(TLicense);

                //---- OGetTime ----
                OGetTime.setText("\u53d6\u8f66\u65f6\u95f4");
                OGetTime.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OGetTime);
                panel4.add(TGetTime);

                //---- OGetPoint ----
                OGetPoint.setHorizontalAlignment(SwingConstants.CENTER);
                OGetPoint.setText("\u53d6\u8f66\u7f51\u70b9");
                panel4.add(OGetPoint);
                panel4.add(SGetPoint);

                //---- OCartype ----
                OCartype.setText("\u8f66\u8f86\u7c7b\u522b");
                OCartype.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OCartype);
                panel4.add(TCartype);

                //---- ODescription ----
                ODescription.setText("\u7c7b\u578b\u7b80\u4ecb");
                ODescription.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(ODescription);
                panel4.add(TDescription);

                //---- OBrand_Model ----
                OBrand_Model.setText("\u54c1\u724c-\u578b\u53f7");
                OBrand_Model.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OBrand_Model);
                panel4.add(TBrand_Model);

                //---- OOPrice ----
                OOPrice.setText("\u539f\u4ef7");
                OOPrice.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OOPrice);
                panel4.add(TOPrice);
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
    private JPanel South;
    private JPanel panel12;
    private JButton BackToCarInfo;
    private JButton OrderConfirm;
    private JPanel panel9;
    private JPanel panel10;
    private JPanel panel11;
    private JPanel East;
    private JPanel Center;
    private JLabel label1;
    private JPanel panel1;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel OUserName;
    private JTextField TUserName;
    private JLabel OLicense;
    private JTextField TLicense;
    private JLabel OGetTime;
    private JTextField TGetTime;
    private JLabel OGetPoint;
    private JComboBox SGetPoint;
    private JLabel OCartype;
    private JTextField TCartype;
    private JLabel ODescription;
    private JTextField TDescription;
    private JLabel OBrand_Model;
    private JTextField TBrand_Model;
    private JLabel OOPrice;
    private JTextField TOPrice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
