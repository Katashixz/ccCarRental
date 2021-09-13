package model;

import java.sql.Timestamp;
import java.util.Date;

public class BeanOrder {
    public  static BeanOrder CurrentOrder = null;
    Integer UserinfoNum;
    Integer CouponNum;
    Integer DiscountNum;
    Integer CarinfoNum;
    Integer OrderNum;
    Integer GetPointNum;
    Date GetTime;
    Integer RtnPointNum;
    Date ReturnTime;
    Long Termdays;
    Double OriginMoney;
    Double TotalMoney;
    String State;

    public BeanOrder getCurrentOrder() {
        return CurrentOrder;
    }

    public void setCurrentOrder(BeanOrder currentOrder) {
        CurrentOrder = currentOrder;
    }

    public Integer getUserinfoNum() {
        return UserinfoNum;
    }

    public void setUserinfoNum(Integer userinfoNum) {
        UserinfoNum = userinfoNum;
    }

    public Integer getCouponNum() {
        return CouponNum;
    }

    public void setCouponNum(Integer couponNum) {
        CouponNum = couponNum;
    }

    public Integer getDiscountNum() {
        return DiscountNum;
    }

    public void setDiscountNum(Integer discountNum) {
        DiscountNum = discountNum;
    }

    public Integer getCarinfoNum() {
        return CarinfoNum;
    }

    public void setCarinfoNum(Integer carinfoNum) {
        CarinfoNum = carinfoNum;
    }

    public Integer getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(Integer orderNum) {
        OrderNum = orderNum;
    }

    public Integer getGetPointNum() {
        return GetPointNum;
    }

    public void setGetPointNum(Integer getPointNum) {
        GetPointNum = getPointNum;
    }

    public Date getGetTime() {
        return GetTime;
    }

    public void setGetTime(Date getTime) {
        GetTime = getTime;
    }

    public Integer getRtnPointNum() {
        return RtnPointNum;
    }

    public void setRtnPointNum(Integer rtnPointNum) {
        RtnPointNum = rtnPointNum;
    }

    public Date getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(Date returnTime) {
        ReturnTime = returnTime;
    }

    public Long getTermdays() {
        return Termdays;
    }

    public void setTermdays(Long termdays) {
        Termdays = termdays;
    }

    public Double getOriginMoney() {
        return OriginMoney;
    }

    public void setOriginMoney(Double originMoney) {
        OriginMoney = originMoney;
    }

    public Double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        TotalMoney = totalMoney;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
