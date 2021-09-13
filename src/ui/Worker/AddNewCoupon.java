/*
 * Created by JFormDesigner on Sat Sep 11 12:59:00 CST 2021
 */

package ui.Worker;

import control.CouponInfoManager;
import model.BeanCoupon;
import model.BeanWorker;
import util.BusinessException;
import util.DbException;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class AddNewCoupon extends JFrame {
    public AddNewCoupon() {
        initComponents();
    }

    private void BackButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
        CouponInfoCheck dlg = new CouponInfoCheck();
        dlg.setVisible(true);
    }

    private void AddConfirmActionPerformed(ActionEvent e) {
        // TODO add your code here
        //���Ż�ȯ��Ϣ�ռ���¼��
        BeanWorker curWorker = BeanWorker.getCurrentLoginWorker();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        BeanCoupon NewCoupon = new BeanCoupon();
        NewCoupon.setDescription(TDescription.getText());
        NewCoupon.setExemption(Double.valueOf(TExemption.getText()));
        NewCoupon.setPointNum(curWorker.getPointNum());
        try {
            java.util.Date GetStartDate =fmt.parse(TStartTime.getText());
            java.util.Date GetEndDate =fmt.parse(TEndTime.getText());
            //����ʱ�������ʱ��Աȣ��������ʱ����������ʱ�䱨��
            java.util.Date curDate = new Date(System.currentTimeMillis());
            //����ʱ����Ϊ����Сʱ�����������ͬһ�죬����ʱ��϶���������ʱ�䣬����Ҫ������ʱ����ǰ��һ��
            Calendar cal = Calendar.getInstance();
            cal.setTime(curDate);
            cal.add(Calendar.DAY_OF_MONTH,-1);
            curDate = cal.getTime();
            if(curDate.compareTo(GetStartDate) >= 0){
                throw new BusinessException("�Ż�ȯʱ�䲻����������ʱ�䣡");
            }
            if(GetEndDate.compareTo(GetStartDate) < 0){
                throw new BusinessException("�Ż�ȯ����ʱ�䲻�����ڿ�ʼʱ�䣡");
            }
            NewCoupon.setStart(GetStartDate);
            NewCoupon.setOver(GetEndDate);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"\n���ڸ�ʽ����"+ex.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            new CouponInfoManager().AddNewCoupon(NewCoupon);
            JOptionPane.showMessageDialog(null,"��ӳɹ���");
            CouponInfoCheck dlg = new CouponInfoCheck();
            dlg.setVisible(true);
        } catch (DbException ex) {
            JOptionPane.showMessageDialog(null,"\n�Ż�ȯ���ʧ�ܣ�"+ex.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
            return;
        }


    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
        TStartTime.setText("ʾ��:2021-9-13");

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        Center = new JPanel();
        Title = new JLabel();
        panel5 = new JPanel();
        label1 = new JLabel();
        TDescription = new JTextField();
        label2 = new JLabel();
        TExemption = new JTextField();
        LStartTime = new JLabel();
        TStartTime = new JTextField();
        LEndTime = new JLabel();
        TEndTime = new JTextField();
        BackButton = new JButton();
        AddConfirm = new JButton();

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/static/image/office-worker.png")).getImage());
        setTitle("AddNewCoupon");
        setMinimumSize(new Dimension(400, 200));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(20, 20));

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout());
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new GridLayout());
        }
        contentPane.add(panel2, BorderLayout.WEST);

        //======== panel3 ========
        {
            panel3.setLayout(new GridLayout());
        }
        contentPane.add(panel3, BorderLayout.EAST);

        //======== panel4 ========
        {
            panel4.setLayout(new GridLayout());
        }
        contentPane.add(panel4, BorderLayout.SOUTH);

        //======== Center ========
        {
            Center.setLayout(new BorderLayout(10, 10));

            //---- Title ----
            Title.setText("\u6dfb\u52a0\u65b0\u4f18\u60e0\u5238");
            Title.setHorizontalAlignment(SwingConstants.CENTER);
            Center.add(Title, BorderLayout.NORTH);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(5, 2, 20, 10));

                //---- label1 ----
                label1.setText("\u4f18\u60e0\u5238\u63cf\u8ff0");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label1);
                panel5.add(TDescription);

                //---- label2 ----
                label2.setText("\u51cf\u514d\u91d1\u989d");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label2);
                panel5.add(TExemption);

                //---- LStartTime ----
                LStartTime.setText("\u5f00\u59cb\u65f6\u95f4");
                LStartTime.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LStartTime);
                panel5.add(TStartTime);

                //---- LEndTime ----
                LEndTime.setText("\u7ed3\u675f\u65f6\u95f4");
                LEndTime.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(LEndTime);
                panel5.add(TEndTime);

                //---- BackButton ----
                BackButton.setText("\u8fd4\u56de");
                BackButton.addActionListener(e -> BackButtonActionPerformed(e));
                panel5.add(BackButton);

                //---- AddConfirm ----
                AddConfirm.setText("\u786e\u8ba4\u6dfb\u52a0");
                AddConfirm.addActionListener(e -> AddConfirmActionPerformed(e));
                panel5.add(AddConfirm);
            }
            Center.add(panel5, BorderLayout.CENTER);
        }
        contentPane.add(Center, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel Center;
    private JLabel Title;
    private JPanel panel5;
    private JLabel label1;
    private JTextField TDescription;
    private JLabel label2;
    private JTextField TExemption;
    private JLabel LStartTime;
    private JTextField TStartTime;
    private JLabel LEndTime;
    private JTextField TEndTime;
    private JButton BackButton;
    private JButton AddConfirm;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
