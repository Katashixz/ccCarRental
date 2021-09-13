package control;

import model.BeanUser;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    //ȡ�����пͻ���Ϣ
    public List<BeanUser> GetAllUserInfo()throws BaseException{
        List<BeanUser> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from user_info";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanUser temp = new BeanUser();
                temp.setUserNum(rs.getInt(1));
                temp.setUser_id(rs.getString(2));
                temp.setSex(rs.getString(3));
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
    //ע�����
    public BeanUser reg(String userid, String pwd, String pwd2, String sex, String phonenum, String email, Object city) throws BaseException {
        // TODO Auto-generated method stub
        if(userid == null || "".equals(userid)) throw new BusinessException(("�˺Ų���Ϊ��"));
        if(pwd == null || "".equals(pwd)) throw new BusinessException(("���벻��Ϊ��"));
       /*��������
        System.out.println(sex);
        System.out.println(city);*/
        if(!pwd.equals(pwd2)) throw new BusinessException(("�����������벻һ��"));
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            //����û��Ƿ��Ѿ�����
            String sql = "select userinfo_name from user_info where userinfo_name = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                rs.close();
                pst.close();
                throw new BusinessException("�û��Ѿ�����");
            }
            rs.close();
            pst.close();
            String sql2 = "insert into user_info(userinfo_name,userinfo_sex,userinfo_password,userinfo_phonenum,userinfo_email,userinfo_city,userinfo_registertime) values(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql2);
            pst.setString(1,userid);
            pst.setString(2,sex);
            pwd = new PasswordEncoding().encode(pwd);
            pst.setString(3,pwd);
            pst.setString(4,phonenum);
            pst.setString(5,email);
            pst.setString(6, String.valueOf(city));
            pst.setTimestamp(7,new java.sql.Timestamp(System.currentTimeMillis()));
            pst.execute();
            BeanUser user = new BeanUser();
            user.setRegister_time(new java.sql.Timestamp(System.currentTimeMillis()));
            user.setUser_id(userid);
            return user;

        }catch(SQLException ex){
            throw new DbException(ex);
        }finally {
            if(conn!=null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    //�޸Ĳ���
    public  BeanUser Update(String UserName,String Pwd,String NewPwd,String Sex,String PhoneNum,String Email,String City) throws BusinessException, DbException {
        Pwd = new PasswordEncoding().encode(Pwd);
        NewPwd = new PasswordEncoding().encode(NewPwd);

        Connection conn = null;
        BeanUser user = BeanUser.getCurrentLoginUser();
        if(UserName == null){
            throw new BusinessException("�û�������Ϊ�գ�");
        }
        if(Pwd == null || NewPwd == null) {
            throw new BusinessException("���벻��Ϊ�գ�");
        }
        if(PhoneNum == null){
            throw new BusinessException("�绰���벻��Ϊ�գ�");
        }
        if(Email == null){
            throw new BusinessException("���䲻��Ϊ�գ�");
        }
        if(Pwd.equals(NewPwd)){
            throw new BusinessException("�������ԭ���벻��һ�£�");
        }
        if(!Pwd.equals(user.getPassword())){
            throw new BusinessException("ԭ�������");
        }

        try{
            conn = DBUtil.getConnection();
            String sql = "update user_info " +
                    "set userinfo_name = ?,userinfo_sex = ?,userinfo_password = ?,userinfo_phonenum = ?,userinfo_email = ?,userinfo_city = ? " +
                    "where userinfo_email = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,UserName);
            pst.setString(2,Sex);
            pst.setString(3,NewPwd);
            pst.setString(4,PhoneNum);
            pst.setString(5,Email);
            pst.setString(6,City);
            pst.setString(7,user.getEmail());
            pst.execute();
            user.setUser_id(UserName);
            user.setPassword(NewPwd);
            user.setSex(Sex);
            user.setEmail(Email);
            user.setPhonenum(PhoneNum);
            user.setCity(City);
            return user;
        } catch (SQLException ex) {
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
    //��½����
    public BeanUser login(String userid,String pwd)throws BaseException{
        Connection conn = null;
        BeanUser user = null;
        if(pwd == null){
            throw new BusinessException("���벻��Ϊ��");
        }
        try{
            conn = DBUtil.getConnection();
            String sql = " select * "
                    + " from user_info where userinfo_name = ? and userinfo_password = ? ";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,userid);
            pst.setString(2,pwd);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = new BeanUser();
                user.setUser_id(userid);
                user.setUserNum(rs.getInt(1));
                user.setPassword(rs.getString(4));
                user.setSex(rs.getString(3));
                user.setPhonenum(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setCity(rs.getString(7));
                user.setRegister_time(rs.getTimestamp(8));
                return user;
            }else{
                throw new BusinessException("�û������ڻ��������");
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
