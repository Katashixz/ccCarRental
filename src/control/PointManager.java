package control;

import model.BeanPoint;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointManager {
    //���������Ϣ
    public void AddNewPoint(BeanPoint NewPoint)throws BaseException{
        Connection conn = null;
        if(NewPoint.getPointName().equals("")){
            throw new BusinessException("�������Ʋ���Ϊ�գ�");
        }if(NewPoint.getPointCity().equals("")){
            throw new BusinessException("���в���Ϊ�գ�");
        }if(NewPoint.getPointAddress().equals("")){
            throw new BusinessException("��ַ����Ϊ�գ�");
        }if(NewPoint.getPointPhoneNum().equals("")){
            throw new BusinessException("�绰���벻��Ϊ�գ�");
        }
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_point(point_name,point_city,point_address,point_phonenum)"
                    +" values(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,NewPoint.getPointName());
            pst.setString(2,NewPoint.getPointCity());
            pst.setString(3,NewPoint.getPointAddress());
            pst.setString(4,NewPoint.getPointPhoneNum());
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
    //ɾ��ѡ��������Ϣ
    public void RemoveSelectedPoint(Integer PointNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from car_point where point_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
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
    //��ȡ����������Ϣ
    public List<BeanPoint> GetAllPointInfo()throws BaseException{
        Connection conn = null;
        List<BeanPoint> result = new ArrayList<>();

        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_point";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                BeanPoint temp = new BeanPoint();
                temp.setPointNum(rs.getInt(1));
                temp.setPointName(rs.getString(2));
                temp.setPointCity(rs.getString(3));
                temp.setPointAddress(rs.getString(4));
                temp.setPointPhoneNum(rs.getString(5));
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
//        System.out.println(result.getPointAddress());
        return result;
    }
    //ͨ������������������Ϣ
    public BeanPoint SearchPointInfoByPointNum(Integer PointNum)throws BaseException {
        Connection conn = null;
        BeanPoint result = new BeanPoint();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_point where point_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setPointNum(PointNum);
                result.setPointName(rs.getString(2));
                result.setPointCity(rs.getString(3));
                result.setPointAddress(rs.getString(4));
                result.setPointPhoneNum(rs.getString(5));
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
//        System.out.println(result.getPointAddress());
        return result;
    }
    /*public static void main(String[] args) {
        try {
            new PointManager().SearchPointInfoByPointNum(1001);

        } catch (BaseException e) {
            e.printStackTrace();
        }
    }*/
}
