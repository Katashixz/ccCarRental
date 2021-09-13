package model;

import java.util.Date;

public class BeanCoupon {
    public Integer Num;
    String Description;
    Double Exemption;
    Date Start;
    Date Over;
    Integer PointNum;

    public static BeanCoupon currrentCoupon = null;

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        Num = num;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String discription) {
        Description = discription;
    }

    public Double getExemption() {
        return Exemption;
    }

    public void setExemption(Double exemption) {
        Exemption = exemption;
    }

    public Date getStart() {
        return Start;
    }

    public void setStart(Date start) {
        Start = start;
    }

    public Date getOver() {
        return Over;
    }

    public void setOver(Date over) {
        Over = over;
    }

    public Integer getPointNum() {
        return PointNum;
    }

    public void setPointNum(Integer pointNum) {
        PointNum = pointNum;
    }

    public static BeanCoupon getCurrrentCoupon() {
        return currrentCoupon;
    }

    public static void setCurrrentCoupon(BeanCoupon currrentCoupon) {
        BeanCoupon.currrentCoupon = currrentCoupon;
    }
}
