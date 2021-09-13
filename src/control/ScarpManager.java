package control;

import model.BeanScarppedCar;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class ScarpManager {
    //添加车辆报废信息
    public void AddScarppedCarInfo(BeanScarppedCar SelectedCar)throws DbException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_broken(worker_num,carinfo_num,broken_time,broken_describe) values(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,SelectedCar.getWokerNum());
            pst.setInt(2,SelectedCar.getCarInfoNum());
            pst.setDate(3,new java.sql.Date(SelectedCar.getScarppedTime().getTime()));
            pst.setString(4,SelectedCar.getDescription());
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
}
