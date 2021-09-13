package model;

public class BeanWorker {
    public static BeanWorker currentLoginWorker = null;
    Integer WorkerNum;
    Integer PointNum;
    String WorkerName;
    String WorkerPwd;

    public static BeanWorker getCurrentLoginWorker() {
        return currentLoginWorker;
    }

    public static void setCurrentLoginWorker(BeanWorker currentLoginWorker) {
        BeanWorker.currentLoginWorker = currentLoginWorker;
    }

    public Integer getWorkerNum() {
        return WorkerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        WorkerNum = workerNum;
    }

    public Integer getPointNum() {
        return PointNum;
    }

    public void setPointNum(Integer pointNum) {
        PointNum = pointNum;
    }

    public String getWorkerName() {
        return WorkerName;
    }

    public void setWorkerName(String workerName) {
        WorkerName = workerName;
    }

    public String getWorkerPwd() {
        return WorkerPwd;
    }

    public void setWorkerPwd(String workerPwd) {
        WorkerPwd = workerPwd;
    }
}
