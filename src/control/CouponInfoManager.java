package control;

import model.BeanCoupon;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponInfoManager {
    //删除优惠券
    public void RemoveCoupon(Integer CouponNum) throws DbException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from coupon where coupon_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CouponNum);
            pst.execute();
            pst.close();
        } catch (SQLException ex){
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
    //根据优惠券编号找优惠券
    public BeanCoupon SeachCouponInfoByCouponNum(Integer CouponNum)throws DbException{
        Connection conn = null;
        BeanCoupon Coupon = new BeanCoupon();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from coupon where coupon_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CouponNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                Coupon.setNum(rs.getInt(1));
                Coupon.setDescription(rs.getString(2));
                Coupon.setExemption(rs.getDouble(3));
                Coupon.setStart(rs.getDate(4));
                Coupon.setOver(rs.getDate(5));
                Coupon.setPointNum(rs.getInt(6));
            }
        } catch (SQLException ex){
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
        return Coupon;

    }
    //添加新优惠券
    public void AddNewCoupon(BeanCoupon NewCoupon)throws DbException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into coupon(coupon_describe,coupon_exemption,coupon_start,coupon_over,coupon_point)"
                    +" values(?,?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,NewCoupon.getDescription());
            pst.setDouble(2,NewCoupon.getExemption());
            pst.setDate(3,new java.sql.Date(NewCoupon.getStart().getTime()));
            pst.setDate(4,new java.sql.Date(NewCoupon.getOver().getTime()));
            pst.setInt(5,NewCoupon.getPointNum());
            pst.execute();
            pst.close();
            } catch (SQLException ex){
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
    //根据网点找优惠信息
    public List<BeanCoupon> GetCouponInfoByPointNum(Integer PointNum)throws DbException{
        List<BeanCoupon> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from coupon where coupon_point = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCoupon temp = new BeanCoupon();
                temp.setNum(rs.getInt(1));
                temp.setDescription(rs.getString(2));
                temp.setExemption(rs.getDouble(3));
                temp.setStart(rs.getDate(4));
                temp.setOver(rs.getDate(5));
                temp.setPointNum(rs.getInt(6));
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
    //找所有优惠信息
    public List<BeanCoupon> GetAllCouponInfo()throws DbException{
        List<BeanCoupon> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from coupon";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                BeanCoupon temp = new BeanCoupon();
                temp.setNum(rs.getInt(1));
                temp.setDescription(rs.getString(2));
                temp.setExemption(rs.getDouble(3));
                temp.setStart(rs.getDate(4));
                temp.setOver(rs.getDate(5));
                temp.setPointNum(rs.getInt(6));

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

}
