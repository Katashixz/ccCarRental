package control;

import model.BeanOrder;
import model.BeanPoint;
import model.BeanCarInfo;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarInfoManager {
    //增加车型信息
    public void AddModel(BeanCarInfo NewModel)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_model(type_num,model_name,model_brand,model_displacement,model_gears,model_seats,model_price)"
                    +" values(?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            try{
                pst.setInt(1,NewModel.getCarTypeNum());
                pst.setString(2,NewModel.getModelName());
                pst.setString(3,NewModel.getBrand());
                pst.setDouble(4,NewModel.getDisplacement());
                pst.setString(5,NewModel.getGears());
                pst.setDouble(6,NewModel.getSeats());
                pst.setDouble(7,NewModel.getPrice());
                pst.execute();
                pst.close();
            }catch (Exception ex){
                throw new BusinessException("不存在此车型编号！");
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
    //删除车型信息
    public void RemoveModel(Integer ModelNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from car_model where model_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,ModelNum);
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
    //判断车型是否有相关车辆信息
    public boolean isExistCarInfo(Integer ModelNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_info where model_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,ModelNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
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
    //获得所有车型信息
    public List<BeanCarInfo> GetAllModel()throws BaseException{
        List<BeanCarInfo> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_model";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCarInfo temp = new BeanCarInfo();
                temp.setModelNum(rs.getInt(1));
                temp.setCarTypeNum(rs.getInt(2));
                temp.setModelName(rs.getString(3));
                temp.setBrand(rs.getString(4));
                temp.setDisplacement(rs.getDouble(5));
                temp.setGears(rs.getString(6));
                temp.setSeats(rs.getInt(7));
                temp.setPrice(rs.getDouble(8));
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
    //添加车类别信息
    public void AddCarType(BeanCarInfo NewType)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_type(type_name,type_discribe) values(?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,NewType.getCarType());
            pst.setString(2,NewType.getTypeDescription());
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
    //删除车类别信息
    public void RemoveCarType(Integer CarTypeNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from car_type where type_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarTypeNum);
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
    //判断类别有无相关车型存在
    public boolean isExistModel(Integer CarTypeNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_model where type_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarTypeNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
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
    //添加车辆信息
    public void AddNewCarInfo(BeanCarInfo NewCarInfo)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "insert into car_info(point_num,model_num,carinfo_license,carinfo_state)"
                    +"values(?,?,?,?)";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,NewCarInfo.getAtPoint());
            pst.setInt(2,NewCarInfo.getModelNum());
            pst.setString(3,NewCarInfo.getLicense());
            pst.setString(4,NewCarInfo.getCarCondition());
            try {
                pst.execute();
            }catch (Exception ex){
                throw new BusinessException("网点编号或车型编号有误！");
            }
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
    //查看车辆状态
    public String CheckCarState(Integer CarInfoNum)throws BaseException{
        Connection conn = null;
        String State = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select carinfo_state from car_info where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarInfoNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                State = rs.getString(1);
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
        return State;
    }
    //删除车辆信息
    public void RemoveCarInfo(Integer CarInfoNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "delete from car_info where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarInfoNum);
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
    //获取所有车辆信息
    public List<BeanCarInfo> GetAllCarInfoWithoutCondition()throws BaseException{
        List<BeanCarInfo> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_info";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCarInfo temp = new BeanCarInfo();
                temp.setCarInfoNum(rs.getInt(1));
                temp.setAtPoint(rs.getInt(2));
                temp.setModelNum(rs.getInt(3));
                temp.setLicense(rs.getString(4));
                temp.setCarCondition(rs.getString(5));
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
    //报废车辆
    public void ScarppedCar(BeanCarInfo SelectedCarInfo)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "update car_info set carinfo_state = ? where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,"报废");
            pst.setInt(2,SelectedCarInfo.getCarInfoNum());
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
    //判断车辆信息是否存在
    public boolean isExistCarInPoint(Integer PointNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_info where point_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
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
    //车辆归还时的所在网点更新
    public void updateCarPoint(Integer NewPointNum,Integer CarInfoNum)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "update car_info set point_num = ? where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,NewPointNum);
            pst.setInt(2,CarInfoNum);
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
    //更新被租用车辆的状态信息
    public void updateCarInfoState(Integer CarInfoNum,String CarState)throws BaseException{
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "update car_info set carinfo_state = ? where carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,CarState);
            pst.setInt(2,CarInfoNum);
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
    //获取所有品牌
    public List<String> GetAllBrand() throws DbException{
        List<String> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String Brand;
            String sql = "select DISTINCT model_brand from car_model";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Brand = rs.getString(1);
                result.add(Brand);
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
    //获取所有车辆类别的实体信息
    public List<BeanCarInfo> GetAllCarTypeRTNBean()throws BaseException{
        List<BeanCarInfo> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_type";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                BeanCarInfo temp = new BeanCarInfo();
                temp.setCarTypeNum(rs.getInt(1));
                temp.setCarType(rs.getString(2));
                temp.setTypeDescription(rs.getString(3));
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
    //获取所有车辆类别名称
    public List<String> GetAllCarType() throws DbException{
        List<String> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select DISTINCT type_name from car_type";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String CarType;
                CarType = rs.getString(1);
                result.add(CarType);
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
    //获取符合条件的车辆
    public List<BeanCarInfo> GetAllCarInfo(String Brand, String Cartype, String CarCondition, String RentalPoint, String RentalCity) throws DbException {
        Connection conn = null;
        List<BeanCarInfo> result = new ArrayList<BeanCarInfo>();
        try {
            conn = DBUtil.getConnection();

            //sql语句默认筛选出全部
            String sql = "SELECT info.carinfo_num,info.carinfo_license,model.model_brand,type.type_name,model.model_num,model.model_name,model.model_displacement,model.model_gears,model.model_seats,model.model_price,info.carinfo_state\n" +
                    "FROM car_info info,car_model model,car_point point,car_type type\n" +
                    "WHERE info.point_num = point.point_num and info.model_num = model.model_num and model.type_num = type.type_num";
            //如果选择项不是全部就在sql语句后补充条件
            if(!Brand.equals("全部")){
                sql = sql + " and model.model_brand like '" + Brand + "'";
            }
            if(!Cartype.equals("全部")){
                sql = sql + " and type.type_name like '" + Cartype + "'";
            }
            if(!CarCondition.equals("全部")){
                sql = sql + " and info.carinfo_state like '" + CarCondition + "'";
            }
            if(!RentalCity.equals("全部")){
                sql = sql + " and point.point_city like '" + RentalCity + "'";
            }
            if(!RentalPoint.equals("全部")){
                sql = sql + " and point.point_address like '" + RentalPoint + "'";
            }
            //System.out.println(sql);
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCarInfo temp = new BeanCarInfo();
                temp.setCarInfoNum(rs.getInt(1));
                temp.setLicense(rs.getString(2));
                temp.setBrand(rs.getString(3));
                temp.setCarType(rs.getString(4));
                temp.setModelNum(rs.getInt(5));
                temp.setModelName(rs.getString(6));
                temp.setDisplacement(rs.getDouble(7));
                temp.setGears(rs.getString(8));
                temp.setSeats(rs.getInt(9));
                temp.setPrice(rs.getDouble(10));
                temp.setCarCondition(rs.getString(11));
//                System.out.println(temp.getLicense());
                result.add(temp);
            }

            //返回一个数组结果，数组里的每一个值是一个BeanSearchConditions对象

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
    //获取所有的城市
    public List<String> GetAllCity() throws DbException{
        List<String> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String city = null;
            String sql = "select DISTINCT point_city from car_point";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                city = rs.getString(1);
                result.add(city);
            }
            return result;
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
    //根据网点找到这个网点的车
    public List<BeanCarInfo> SeachCarInfoByPointNum(Integer PointNum) throws BaseException{
        List<BeanCarInfo> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "SELECT info.carinfo_num,info.carinfo_license,model.model_brand,type.type_name,model.model_num,model.model_name,model.model_displacement,model.model_gears,model.model_seats,model.model_price,info.carinfo_state,type.type_discribe,info.point_num\n" +
                    "FROM car_info info,car_model model,car_point point,car_type type\n" +
                    "WHERE info.point_num = point.point_num and info.model_num = model.model_num and model.type_num = type.type_num and info.point_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,PointNum);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanCarInfo temp = new BeanCarInfo();
                temp.setCarInfoNum(rs.getInt(1));
                temp.setLicense(rs.getString(2));
                temp.setBrand(rs.getString(3));
                temp.setCarType(rs.getString(4));
                temp.setModelNum(rs.getInt(5));
                temp.setModelName(rs.getString(6));
                temp.setDisplacement(rs.getDouble(7));
                temp.setGears(rs.getString(8));
                temp.setSeats(rs.getInt(9));
                temp.setPrice(rs.getDouble(10));
                temp.setCarCondition(rs.getString(11));
                temp.setTypeDescription(rs.getString(12));
                temp.setAtPoint(rs.getInt(13));
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
    //根据车辆编号获取这辆车的所有信息
    public BeanCarInfo SearchCarInfoByCarNum(Integer CarNum) throws BaseException {
        BeanCarInfo temp = null;
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "SELECT info.carinfo_num,info.carinfo_license,model.model_brand,type.type_name,model.model_num,model.model_name,model.model_displacement,model.model_gears,model.model_seats,model.model_price,info.carinfo_state,type.type_discribe,info.point_num\n" +
                    "FROM car_info info,car_model model,car_point point,car_type type\n" +
                    "WHERE info.point_num = point.point_num and info.model_num = model.model_num and model.type_num = type.type_num and info.carinfo_num = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,CarNum);
            java.sql.ResultSet rs = pst.executeQuery();
            if(rs.next()){
                temp = new BeanCarInfo();
                temp.setCarInfoNum(rs.getInt(1));
                temp.setLicense(rs.getString(2));
                temp.setBrand(rs.getString(3));
                temp.setCarType(rs.getString(4));
                temp.setModelNum(rs.getInt(5));
                temp.setModelName(rs.getString(6));
                temp.setDisplacement(rs.getDouble(7));
                temp.setGears(rs.getString(8));
                temp.setSeats(rs.getInt(9));
                temp.setPrice(rs.getDouble(10));
                temp.setCarCondition(rs.getString(11));
                temp.setTypeDescription(rs.getString(12));
                temp.setAtPoint(rs.getInt(13));
                return temp;
            }else{
                throw new BusinessException("车辆编号不存在！");
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
    //根据传入的城市名，从数据库中获取所有网点地址
    public List<BeanPoint> SearchPointByCity(String City) throws DbException {
        List<BeanPoint> result = new ArrayList<>();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from car_point where point_city = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,City);
            java.sql.ResultSet rs = pst.executeQuery();
            while(rs.next()){
                BeanPoint temp = new BeanPoint();
                temp.setPointNum(rs.getInt(1));
                temp.setPointName(rs.getString(2));
                temp.setPointCity(rs.getString(3));
                temp.setPointAddress(rs.getString(4));
                temp.setPointPhoneNum(rs.getString(5));
                result.add(temp);
            }
            return result;
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
