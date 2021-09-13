/*
 * Created by JFormDesigner on Wed Sep 08 22:15:40 CST 2021
 */

package ui.User;

import com.sun.org.apache.xpath.internal.operations.Or;
import control.*;
import model.*;
import util.BaseException;
import util.BusinessException;
import util.DbException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brainrain
 */
public class ReturnOrderGeneration extends JFrame {
    public ReturnOrderGeneration() {
        initComponents();
    }
    //���봰��ʱ�Զ����ú�������Ϣ
    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        BeanOrder curOrder = new BeanOrder().getCurrentOrder();
        //��ȡ�����еĳ�������Ϣ
        try {
            BeanCarInfo curCarInfo = new CarInfoManager().SearchCarInfoByCarNum(curOrder.getCarinfoNum());
            TLicense.setText(curCarInfo.getLicense());
            TLicense.setEditable(false);
            TDescription.setText(curCarInfo.getTypeDescription());
            TDescription.setEditable(false);
            TBrand_Model.setText(curCarInfo.getBrand()+"-"+curCarInfo.getModelName());
            TBrand_Model.setEditable(false);
            TOPrice.setText(String.valueOf(curCarInfo.getPrice())+"/��");
            TOPrice.setEditable(false);
            TCartype.setText(curCarInfo.getCarType());
            TCartype.setEditable(false);
        } catch (BaseException ex) {
            ex.printStackTrace();
        }
        //��ȡ�û���Ϣ
        BeanUser user = BeanUser.getCurrentLoginUser();
        TUserName.setText(user.getUser_id());
        TUserName.setEditable(false);
        //��ȡȡ��ʱ��
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String GetDate = sdf.format(curOrder.getGetTime());
        TGetTime.setText(GetDate);
        TGetTime.setEditable(false);
        //��ȡȡ������
        try {
            BeanPoint curPoint = new PointManager().SearchPointInfoByPointNum(curOrder.getGetPointNum());
            SGetPoint.setText(curPoint.getPointNum()+"-"+curPoint.getPointCity()+"-"+curPoint.getPointAddress());
            SGetPoint.setEditable(false);
        } catch (BaseException ex) {
            ex.printStackTrace();
        }

        TTotalPrice.setEditable(false);
        TDiscount.setEditable(false);
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
//        List<Integer> IndexCorrespondCity  = new ArrayList<>();
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
//                    IndexCorrespondCity.add(PointList.get(j).getPointNum());
                    SRtnPoint.addItem(PointList.get(j).getPointNum()+"-"+OneOfCities+"-"+Point);
                }

        }
        //�Ż�ȯ��Ϣ��ȡ����ӵ�ѡ������
        try {
            //��ʼ��
            TCouponSelect.removeAllItems();
            TCouponSelect.addItem("��ʹ���Ż�ȯ");
            //����ȡ�����������Ż�ȯѡ��
            List<BeanCoupon> CouponData = new CouponInfoManager().GetCouponInfoByPointNum(curOrder.getGetPointNum());
            for(int i = 0;i < CouponData.size();i ++){
                //�ж��Ż�ȯ�Ƿ�����Чʱ�䷶Χ��
                java.util.Date curDate = new Date(System.currentTimeMillis());
                java.util.Date Start = CouponData.get(i).getStart();
                java.util.Date End = CouponData.get(i).getOver();
                if(curDate.compareTo(Start) > 0 && curDate.compareTo(End) < 0){
                    TCouponSelect.addItem(CouponData.get(i).getDescription());
                }
            }
        } catch (DbException ex) {
            ex.printStackTrace();
        }
        //�ۿ���Ϣ��ѡ�����ᳵ���ˢ����ʾ

//        Integer CarNum = BeanSearchConditions.getCurrentConditions();


    }

    private void BackToCarInfoActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void ReloadOrderActionPerformed(ActionEvent e) {
        // TODO add your code here
        OrderConfirm.setVisible(true);
        BeanOrder curOrder = new BeanOrder().getCurrentOrder();
        Integer CarNum = curOrder.getCarinfoNum();
        String GetTime = String.valueOf(TGetTime.getText());
        String RtnTime = String.valueOf(TRtnTime.getText());

        String Coupon = this.TCouponSelect.getItemAt(this.TCouponSelect.getSelectedIndex());
        double Price = Double.valueOf(TOPrice.getText().substring(0,TOPrice.getText().indexOf("/")));
        //ʱ�䲻��һ��Ҳ��һ��
        long TermDays = new OrderManager().getTermDays(GetTime,RtnTime) + 1;
        if(TermDays <= 0){
            JOptionPane.showMessageDialog(null,"��������ȷ�Ļ���ʱ�䣡","����",JOptionPane.ERROR_MESSAGE);
            OrderConfirm.setVisible(false);
        }
        Double TotalPrice = Price * TermDays;
        curOrder.setTermdays(TermDays);
        //�ۿ���Ϣ��ʾ
//        BeanSearchConditions bsc = BeanSearchConditions.getCurrentConditions();
        String RentPoint = SGetPoint.getText();
        RentPoint = RentPoint.substring(0,RentPoint.indexOf('-'));
        try {
            BeanCarInfo curCarInfo = new CarInfoManager().SearchCarInfoByCarNum(CarNum);
            Integer ModelNum = curCarInfo.getModelNum();
            TDiscount.setText("���ۿ�");
            Double Discount = null;
            List<BeanDiscount> DiscountInfo = new DiscountManager().GetAllDiscountInfo();
            for(int i=0;i<DiscountInfo.size();i++){
                //�ж��ۿ���Ϣ�Ƿ�����Ч����
                java.util.Date curDate = new Date(System.currentTimeMillis());
                java.util.Date Start = DiscountInfo.get(i).getDiscntStart();
                java.util.Date End = DiscountInfo.get(i).getDiscntEnd();
                //�������ʱ�䲻����Ч���ھ�����
                if(!(curDate.compareTo(Start) > 0 && curDate.compareTo(End) < 0)){
                    continue;
                }
                if((DiscountInfo.get(i).getDiscntCarModelNum().equals(ModelNum)) && (String.valueOf(DiscountInfo.get(i).getDiscntPointNum()).equals(RentPoint))){
                    Discount = DiscountInfo.get(i).getDiscount();
                    TDiscount.setText(DiscountInfo.get(i).getDiscount()+"��");
                    curOrder.setDiscountNum(DiscountInfo.get(i).getDiscntNum());
                }
            }
            //�ۿ۽���
            if(Discount != null){
                TotalPrice = TotalPrice * Discount;
            }
            //�Ż�ȯ�ֿ۽���
            if(!Coupon.equals("��ʹ���Ż�ȯ")){
                int i = Coupon.indexOf("-");
                long Reduction = Long.valueOf(Coupon.substring(i+1));
                long DesignedAmount = Long.valueOf(Coupon.substring(1,i));
//            System.out.println(DesignedAmount+" "+Reduction);
                if(TotalPrice >= DesignedAmount){
                    TotalPrice = TotalPrice - Reduction;
                }
            }


            TTotalPrice.setText(String.valueOf(TotalPrice));
            BeanOrder.CurrentOrder = curOrder;
        } catch (BaseException ex) {
            JOptionPane.showMessageDialog(null,"����������Ϣ�����ˢ�°�ť��","����",JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    private void OrderConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        BeanOrder curOrder = new BeanOrder().getCurrentOrder();
        String SelectedCoupon = String.valueOf(this.TCouponSelect.getItemAt(this.TCouponSelect.getSelectedIndex()));
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String RtnPoint = String.valueOf(this.SRtnPoint.getItemAt(this.SRtnPoint.getSelectedIndex()));

        try{
            //�����Ż�ȯ��Ϣ
            List<BeanCoupon> AllCouponInfo = new CouponInfoManager().GetAllCouponInfo();
            //��ʼ������������Ϊnull����ֵΪ����ʹ���Ż�ȯ���͡����ۿ�"ʱ�ᵼ��ֵΪ0���ϴ����ݿ�ʱ���������ֵ��һ�¡�
            for(int i = 0;i < AllCouponInfo.size();i ++){
                if(AllCouponInfo.get(i).getDescription().equals(SelectedCoupon)){
                    curOrder.setCouponNum(AllCouponInfo.get(i).getNum());
                }
            }
            //�ۿ���Ϣ�����Զ�����ʱ����
            //���仹��ʱ����Ϣ
            try {
                java.util.Date RtnDate =fmt.parse(TRtnTime.getText());
//                System.out.println(TRtnTime.getText());
                curOrder.setReturnTime(RtnDate);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,"���ڸ�ʽ����","����",JOptionPane.ERROR_MESSAGE);
                return;
            }
//            System.out.println(curOrder.getReturnTime());
            //���仹��������Ϣ
            RtnPoint = RtnPoint.substring(0,RtnPoint.indexOf('-'));
            curOrder.setRtnPointNum(Integer.valueOf(RtnPoint));
            //���ʱ����Ϣ���ڼ����ܼ���Ϣʱ����
            //�����ۺ��ܼ���Ϣ
            try{
                curOrder.setTotalMoney(Double.valueOf(TTotalPrice.getText()));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null,"����ˢ�°�ť����۸�","����",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //�޸�״̬��Ϣ
            curOrder.setState("�����");
            //�ϴ���Ϣ(��������������Ϣ�����������Ϣ���������״̬���)
            new CarInfoManager().updateCarInfoState(curOrder.getCarinfoNum(),"����");
            try {
                new OrderManager().updateOrderInfo(curOrder);
                new CarInfoManager().updateCarPoint(curOrder.getRtnPointNum(),curOrder.getCarinfoNum());
            } catch (BaseException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
            }
        }catch(BaseException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null,"��������ɣ���лʹ��cc�⳵��");
        this.setVisible(false);


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        North = new JPanel();
        West = new JPanel();
        South = new JPanel();
        panel12 = new JPanel();
        BackToCarInfo = new JButton();
        ReloadOrder = new JButton();
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
        ORtnTime = new JLabel();
        TRtnTime = new JTextField();
        OGetPoint = new JLabel();
        SGetPoint = new JTextField();
        ORtnPoint = new JLabel();
        SRtnPoint = new JComboBox();
        OCartype = new JLabel();
        TCartype = new JTextField();
        ODescription = new JLabel();
        TDescription = new JTextField();
        OBrand_Model = new JLabel();
        TBrand_Model = new JTextField();
        OOPrice = new JLabel();
        TOPrice = new JTextField();
        ODiscount = new JLabel();
        TDiscount = new JTextField();
        OCoupon = new JLabel();
        TCouponSelect = new JComboBox<>();
        OTotalPrice = new JLabel();
        TTotalPrice = new JTextField();

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
            South.setLayout(new BorderLayout(40, 20));

            //======== panel12 ========
            {
                panel12.setLayout(new GridLayout(1, 3, 40, 0));

                //---- BackToCarInfo ----
                BackToCarInfo.setText("\u8fd4\u56de");
                BackToCarInfo.setIcon(new ImageIcon(getClass().getResource("/static/image/back_android.png")));
                BackToCarInfo.addActionListener(e -> BackToCarInfoActionPerformed(e));
                panel12.add(BackToCarInfo);

                //---- ReloadOrder ----
                ReloadOrder.setText("\u5237\u65b0");
                ReloadOrder.setIcon(new ImageIcon(getClass().getResource("/static/image/refresh.png")));
                ReloadOrder.addActionListener(e -> ReloadOrderActionPerformed(e));
                panel12.add(ReloadOrder);

                //---- OrderConfirm ----
                OrderConfirm.setText("\u5b8c\u6210\u8ba2\u5355");
                OrderConfirm.setIcon(new ImageIcon(getClass().getResource("/static/image/���.png")));
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
            Center.setLayout(new BorderLayout(20, 10));

            //---- label1 ----
            label1.setText("\u8fd8\u8f66\u8ba2\u5355");
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
                panel4.setLayout(new GridLayout(13, 2, 0, 10));

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

                //---- ORtnTime ----
                ORtnTime.setText("\u8fd8\u8f66\u65f6\u95f4");
                ORtnTime.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(ORtnTime);
                panel4.add(TRtnTime);

                //---- OGetPoint ----
                OGetPoint.setHorizontalAlignment(SwingConstants.CENTER);
                OGetPoint.setText("\u53d6\u8f66\u7f51\u70b9");
                panel4.add(OGetPoint);
                panel4.add(SGetPoint);

                //---- ORtnPoint ----
                ORtnPoint.setText("\u8fd8\u8f66\u7f51\u70b9");
                ORtnPoint.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(ORtnPoint);
                panel4.add(SRtnPoint);

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

                //---- ODiscount ----
                ODiscount.setText("\u6298\u6263");
                ODiscount.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(ODiscount);
                panel4.add(TDiscount);

                //---- OCoupon ----
                OCoupon.setText("\u4f18\u60e0\u5238");
                OCoupon.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OCoupon);

                //---- TCouponSelect ----
                TCouponSelect.setPreferredSize(new Dimension(84, 30));
                TCouponSelect.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u4e0d\u4f7f\u7528\u4f18\u60e0\u5238"
                }));
                panel4.add(TCouponSelect);

                //---- OTotalPrice ----
                OTotalPrice.setText("\u6298\u540e\u603b\u4ef7");
                OTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(OTotalPrice);
                panel4.add(TTotalPrice);
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
    private JButton ReloadOrder;
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
    private JLabel ORtnTime;
    private JTextField TRtnTime;
    private JLabel OGetPoint;
    private JTextField SGetPoint;
    private JLabel ORtnPoint;
    private JComboBox SRtnPoint;
    private JLabel OCartype;
    private JTextField TCartype;
    private JLabel ODescription;
    private JTextField TDescription;
    private JLabel OBrand_Model;
    private JTextField TBrand_Model;
    private JLabel OOPrice;
    private JTextField TOPrice;
    private JLabel ODiscount;
    private JTextField TDiscount;
    private JLabel OCoupon;
    private JComboBox<String> TCouponSelect;
    private JLabel OTotalPrice;
    private JTextField TTotalPrice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
