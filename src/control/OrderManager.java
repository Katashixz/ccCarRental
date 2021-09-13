package control;

import model.BeanCarInfo;
import model.BeanOrder;
import model.BeanPoint;
import model.BeanUser;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager {
    //���¶�����Ϣ
    public void updateOrderInfo(BeanOrder NewOrder)throws BaseException{
        Connection conn = null;
        try{
            int cnt = 1;
            conn = DBUtil.getConnection();
            String sql = "update user_order set order_return_point = ?,order_return_time = ?,order_total_time = ?,order_total_money = ?,order_state = ?";
            if(NewOrder.getCouponNum() != 0){
                sql = sql + ",coupon_num = ?";
            }if(NewOrder.getDiscountNum() != 0){
                sql = sql + ",discount_num = ?";
            }
            sql = sql + " where order_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(cnt,NewOrder.getRtnPointNum());
            pst.setDate(++cnt, new java.sql.Date(NewOrder.getReturnTime().getTime()));
            pst.setLong(++cnt,NewOrder.getTermdays());
            pst.setDouble(++cnt,NewOrder.getTotalMoney());
            pst.setString(++cnt,NewOrder.getState());
            if(NewOrder.getCouponNum() != 0){
                pst.setInt(++cnt,NewOrder.getCouponNum());
            }if(NewOrder.getDiscountNum() != 0){
                pst.setInt(++cnt,NewOrder.getDiscountNum());
            }
            pst.setInt(++cnt,NewOrder.getOrderNum());
            pst.execute();
            pst.close();
        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
    //�����������
    public long getTermDays(String Start,String Over){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long TermDays = 0;
        try{
            Date date1 = sdf.parse(Start);
            Date date2 = sdf.parse(Over);
            TermDays = (date2.getTime()-date1.getTime())/(24*60*60*1000);
        }catch (ParseException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null,"����������Ϣ�ٵ��ˢ�°�ť��","����",JOptionPane.ERROR_MESSAGE);

        }
        return TermDays;

    }
    //ͨ��������Ų�ѯ�˳�׬ȡ���ܽ��
    public Double SelectedCarTotalProfit(Integer CarInfoNum)throws BaseException{
        Connection conn = null;
        Double result = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select sum(order_total_money) from user_order where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarInfoNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }else{
                throw new BusinessException("������Ų����ڣ�");
            }
        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //ͨ��������Ų�ѯ���������
    public Integer RentCount(Integer CarInfoNum)throws BaseException{
        Connection conn = null;
        Integer result = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select count(carinfo_num) from user_order where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarInfoNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }else{
                throw new BusinessException("������Ų����ڣ�");
            }
        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //ͨ���û���Ų����ܽ��
    public Double TotalMoneyCount(Integer UserNum)throws BaseException{
        Connection conn = null;
        Double result = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select sum(order_total_money) from user_order where userinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,UserNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }

        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //ͨ���û���Ų��Ҷ�������
    public Integer OrderCount(Integer UserNum)throws BaseException{
        Connection conn = null;
        Integer result = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select count(userinfo_num) from user_order where userinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,UserNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    //ͨ���û���Ų��Ҷ���
    public List<BeanOrder> SearchOrderInfoByUserInfoNum(Integer UserInfoNum)throws BaseException {
        Connection conn = null;
        List<BeanOrder> result = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select order_num,carinfo_num,order_get_point,order_get_time,order_state from user_order "+
                    "where userinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,UserInfoNum);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanOrder temp = new BeanOrder();
                temp.setOrderNum(rs.getInt(1));
                temp.setCarinfoNum(rs.getInt(2));
                temp.setGetPointNum(rs.getInt(3));
                temp.setGetTime(rs.getDate(4));
//                System.out.println(rs.getTime(4));
                temp.setState(rs.getString(5));
                result.add(temp);
            }


        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
    //��������
    public void uploadOrderInfo(BeanOrder curOrder)throws BaseException {
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into user_order(userinfo_num,carinfo_num,order_get_point,order_get_time,order_state,order_origin_money)"
                    +" values(?,?,?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,curOrder.getUserinfoNum());
            pst.setInt(2,curOrder.getCarinfoNum());
            pst.setInt(3,curOrder.getGetPointNum());
            pst.setDate(4, new java.sql.Date(curOrder.getGetTime().getTime()));
            pst.setString(5,curOrder.getState());
            pst.setDouble(6,curOrder.getOriginMoney());
            pst.execute();
            pst.close();

        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //ͨ��������Ų��Ҷ�����Ϣ
    public BeanOrder SearchOrderByOrderNum(Integer OrderNum) throws BaseException {
        Connection conn = null;
        BeanOrder result = new BeanOrder();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from user_order where order_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,OrderNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setOrderNum(rs.getInt(1));
                result.setUserinfoNum(rs.getInt(2));
                result.setCouponNum(rs.getInt(3));
                result.setDiscountNum(rs.getInt(4));
                result.setCarinfoNum(rs.getInt(5));
                result.setGetPointNum(rs.getInt(6));
                result.setGetTime(rs.getDate(7));
                result.setRtnPointNum(rs.getInt(8));
                result.setReturnTime(rs.getDate(9));
                result.setTermdays(rs.getLong(10));
                result.setOriginMoney(rs.getDouble(11));
                result.setTotalMoney(rs.getDouble(12));
                result.setState(rs.getString(13));
            }

        }catch (SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /*public static void main(String[] args) {
        System.out.println(new OrderManager().getTermDays("2021-9-8","2021-9-10"));

    }*/
}
