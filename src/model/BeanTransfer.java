package model;

import java.util.Date;

public class BeanTransfer {
    public static  BeanTransfer CurrentTrans = null;
    Integer TransNum;
    Integer CarInfoNum;
    Date TransTime;
    Integer TransOutNum;
    Integer TransInNum;

    public static BeanTransfer getCurrentTrans() {
        return CurrentTrans;
    }

    public static void setCurrentTrans(BeanTransfer currentTrans) {
        CurrentTrans = currentTrans;
    }

    public Integer getTransNum() {
        return TransNum;
    }

    public void setTransNum(Integer transNum) {
        TransNum = transNum;
    }

    public Integer getCarInfoNum() {
        return CarInfoNum;
    }

    public void setCarInfoNum(Integer carInfoNum) {
        CarInfoNum = carInfoNum;
    }

    public Date getTransTime() {
        return TransTime;
    }

    public void setTransTime(Date transTime) {
        TransTime = transTime;
    }

    public Integer getTransOutNum() {
        return TransOutNum;
    }

    public void setTransOutNum(Integer transOutNum) {
        TransOutNum = transOutNum;
    }

    public Integer getTransInNum() {
        return TransInNum;
    }

    public void setTransInNum(Integer transInNum) {
        TransInNum = transInNum;
    }
}
