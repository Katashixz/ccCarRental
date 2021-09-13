package control;

import model.BeanTransfer;
import util.BaseException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransferManager {
    //新增调度信息
    public void InsertTransferInfo(BeanTransfer NewInfo)throws BaseException {
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_transfer(carinfo_num,trans_time,trans_out_num,trans_in_num) values(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,NewInfo.getCarInfoNum());
            pst.setDate(2,new java.sql.Date(NewInfo.getTransTime().getTime()));
            pst.setInt(3,NewInfo.getTransOutNum());
            pst.setInt(4,NewInfo.getTransInNum());
            pst.execute();
            pst.close();
            new CarInfoManager().updateCarPoint(NewInfo.getTransInNum(),NewInfo.getCarInfoNum());
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
}
