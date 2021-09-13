package control;

import model.BeanDiscount;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountManager {
    //获得所有折扣信息
    public List<BeanDiscount> GetAllDiscountInfo()throws DbException{
        List<BeanDiscount> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from discount";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                BeanDiscount temp = new BeanDiscount();
                temp.setDiscntNum(rs.getInt(1));
                temp.setDiscntPointNum(rs.getInt(2));
                temp.setDiscntCarModelNum(rs.getInt(3));
                temp.setDiscount(rs.getDouble(4));
                temp.setDiscntCarAmount(rs.getDouble(5));
                temp.setDiscntStart(rs.getDate(6));
                temp.setDiscntEnd(rs.getDate(7));
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
    //判断车型是否在打折
    public boolean IsOnDiscount(int Num,String SelectedPoint) throws DbException{
        List<BeanDiscount> DiscountInfo = new DiscountManager().GetAllDiscountInfo();
        boolean flag = false;
        List<Integer> DiscountPointNum = null;
        //判断选择车型是否属于打折车型,同一个打折的车型可能在多个网点打折，所以要用数组储存
        for(int i = 0;i < DiscountInfo.size();i ++){
           if(DiscountInfo.get(i).getDiscntCarModelNum() == Num){
               flag = true;
               //获取网点
               DiscountPointNum.add(DiscountInfo.get(i).getDiscntPointNum());
           }
        }
        //现在要对比选择的网点是否有打折活动，因为在Order页面选择的网点做过改动，所以从已有的网点开始从外向里寻找数据会容易一点
        Connection conn = null;
        try{
            for(int i = 0;i < DiscountPointNum.size();i ++){
                conn = DBUtil.getConnection();
                String sql = "select point_city,point_address from car_point where point_num = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,DiscountPointNum.get(i));
                java.sql.ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    String comp = rs.getString(1)+"-"+rs.getString(2);
                    if(comp.equals(SelectedPoint)){

                        return flag;
                    }
                }

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
        return false;
    }

}
