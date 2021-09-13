package model;

import java.util.Date;

public class BeanScarppedCar {
    Integer ScarppedNum;
    Integer WokerNum;
    Integer CarInfoNum;
    Date ScarppedTime;
    String Description;
    public static BeanScarppedCar CurrentScarppedCar = null;

    public Integer getScarppedNum() {
        return ScarppedNum;
    }

    public void setScarppedNum(Integer scarppedNum) {
        ScarppedNum = scarppedNum;
    }

    public Integer getWokerNum() {
        return WokerNum;
    }

    public void setWokerNum(Integer wokerNum) {
        WokerNum = wokerNum;
    }

    public Integer getCarInfoNum() {
        return CarInfoNum;
    }

    public void setCarInfoNum(Integer carInfoNum) {
        CarInfoNum = carInfoNum;
    }

    public Date getScarppedTime() {
        return ScarppedTime;
    }

    public void setScarppedTime(Date scarppedTime) {
        ScarppedTime = scarppedTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static BeanScarppedCar getCurrentScarppedCar() {
        return CurrentScarppedCar;
    }

    public static void setCurrentScarppedCar(BeanScarppedCar currentScarppedCar) {
        CurrentScarppedCar = currentScarppedCar;
    }
}
