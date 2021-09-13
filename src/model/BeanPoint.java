package model;

public class BeanPoint {
    Integer PointNum;
    String PointName;
    String PointCity;
    String PointAddress;
    String PointPhoneNum;
    BeanPoint CurrentPoint = null;

    public BeanPoint getCurrentPoint() {
        return CurrentPoint;
    }

    public void setCurrentPoint(BeanPoint currentPoint) {
        CurrentPoint = currentPoint;
    }

    public Integer getPointNum() {
        return PointNum;
    }

    public void setPointNum(Integer pointNum) {
        PointNum = pointNum;
    }

    public String getPointName() {
        return PointName;
    }

    public void setPointName(String pointName) {
        PointName = pointName;
    }

    public String getPointCity() {
        return PointCity;
    }

    public void setPointCity(String pointCity) {
        PointCity = pointCity;
    }

    public String getPointAddress() {
        return PointAddress;
    }

    public void setPointAddress(String pointAddress) {
        PointAddress = pointAddress;
    }

    public String getPointPhoneNum() {
        return PointPhoneNum;
    }

    public void setPointPhoneNum(String pointPhoneNum) {
        PointPhoneNum = pointPhoneNum;
    }
}
