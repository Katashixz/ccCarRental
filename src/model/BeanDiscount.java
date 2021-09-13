package model;

import java.util.Date;

public class BeanDiscount {
    public Integer getDiscntNum() {
        return DiscntNum;
    }

    public void setDiscntNum(Integer discntNum) {
        DiscntNum = discntNum;
    }

    public Integer getDiscntPointNum() {
        return DiscntPointNum;
    }

    public void setDiscntPointNum(Integer discntPointNum) {
        DiscntPointNum = discntPointNum;
    }

    public Integer getDiscntCarModelNum() {
        return DiscntCarModelNum;
    }

    public void setDiscntCarModelNum(Integer discntCarTypeNum) {
        DiscntCarModelNum = discntCarTypeNum;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public Double getDiscntCarAmount() {
        return DiscntCarAmount;
    }

    public void setDiscntCarAmount(Double discntCarAmount) {
        DiscntCarAmount = discntCarAmount;
    }

    public Date getDiscntStart() {
        return DiscntStart;
    }

    public void setDiscntStart(Date discntStart) {
        DiscntStart = discntStart;
    }

    public Date getDiscntEnd() {
        return DiscntEnd;
    }

    public void setDiscntEnd(Date discntEnd) {
        DiscntEnd = discntEnd;
    }

    Integer DiscntNum;
    Integer DiscntPointNum;
    Integer DiscntCarModelNum;
    Double Discount;
    Double DiscntCarAmount;
    Date DiscntStart;
    Date DiscntEnd;
    BeanDiscount CurrentDiscount;

    public BeanDiscount getCurrentDiscount() {
        return CurrentDiscount;
    }

    public void setCurrentDiscount(BeanDiscount currentDiscount) {
        CurrentDiscount = currentDiscount;
    }
}
