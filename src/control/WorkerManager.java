package control;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.BeanUser;
import model.BeanWorker;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerManager {
    //查看此网点是否有员工存在
    public boolean isExistWorker(Integer PointNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from admin_worker where point_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
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
    }
    //新增员工信息
    public void addWorker(BeanWorker NewWorker)throws BaseException{
        Connection conn = null;
        if(NewWorker.getWorkerName().equals("")){
            throw new BusinessException("名称不能为空！");
        }
        if(NewWorker.getWorkerPwd().equals("")){
            throw new BusinessException("密码不能为空！");
        }
        try{
            conn = DBUtil.getConnection();
            String sql ;

            if(NewWorker.getPointNum() != 0){
                sql = "insert into admin_worker(point_num,worker_name,worker_password)"
                        +" values(?,?,?)";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1,NewWorker.getPointNum());
                pst.setString(2,NewWorker.getWorkerName());
                pst.setString(3, NewWorker.getWorkerPwd());
                try{
                    pst.execute();
                }catch (SQLException e){
                    throw new BusinessException("网点信息不存在！");
                }

                pst.close();
            }else {
                sql = "insert into admin_worker(worker_name,worker_password)"
                        +" values(?,?)";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1,NewWorker.getWorkerName());
                pst.setString(2, NewWorker.getWorkerPwd());
                pst.execute();
                pst.close();
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
    }
    //删除指定员工信息
    public void removeWorker(Integer WorkerNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from admin_worker where worker_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,WorkerNum);
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
    //获取员工列表
    public List<BeanWorker> GetAllWorker()throws BaseException{
        Connection conn = null;
        List<BeanWorker> result = new ArrayList<>();
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from admin_worker";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanWorker temp = new BeanWorker();
                if(rs.getInt(2) == 0){
                    continue;
                }
                temp.setWorkerNum(rs.getInt(1));
                temp.setPointNum(rs.getInt(2));
                temp.setWorkerName(rs.getString(3));
                temp.setWorkerPwd(rs.getString(4));
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
    //管理员登陆操作
    public BeanWorker adminlogin(String WorkerName, String Pwd)throws BaseException {
        Connection conn = null;
        BeanWorker worker = null;
        if(Pwd == null){
            throw new BusinessException("密码不能为空");
        }
        try{
            conn = DBUtil.getConnection();
            String sql = " select * "
                    + " from admin_worker where worker_name = ? and worker_password = ? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,WorkerName);
            pst.setString(2,Pwd);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                worker = new BeanWorker();
                worker.setWorkerNum(rs.getInt(1));
                worker.setPointNum(rs.getInt(2));
                if(!worker.getPointNum().equals(0)){
                    throw new BusinessException("此用户为员工，请选择对应选项！");
                }
                worker.setWorkerName(rs.getString(3));
                worker.setWorkerPwd(rs.getString(4));
                return worker;
            }else{
                throw new BusinessException("用户不存在或密码错误");
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

    }
    //员工登陆操作
    public BeanWorker login(String WorkerName, String Pwd)throws BaseException {
        Connection conn = null;
        BeanWorker worker = null;
        if(Pwd == null){
            throw new BusinessException("密码不能为空");
        }
        try{
            conn = DBUtil.getConnection();
            String sql = " select * "
                    + " from admin_worker where worker_name = ? and worker_password = ? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,WorkerName);
            pst.setString(2,Pwd);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                worker = new BeanWorker();
                worker.setWorkerNum(rs.getInt(1));
                worker.setPointNum(rs.getInt(2));
                if(worker.getPointNum().equals(0)){
                    throw new BusinessException("此用户为管理员，请选择对应选项！");
                }
                worker.setWorkerName(rs.getString(3));
                worker.setWorkerPwd(rs.getString(4));
                return worker;
            }else{
                throw new BusinessException("用户不存在或密码错误");
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

    }
}
