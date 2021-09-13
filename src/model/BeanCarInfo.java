package model;

public class BeanCarInfo {
    public Integer CarInfoNum;
    public Integer AtPoint;
    public String License;
    public String Brand;
    public String CarType;
    public String ModelName;
    public Integer ModelNum;
    public Double Displacement;
    public String Gears;
    public Integer Seats;
    public double Price;
    public String CarCondition;
    public String TypeDescription;
    public Integer CarTypeNum;

    public Integer getCarTypeNum() {
        return CarTypeNum;
    }

    public void setCarTypeNum(Integer carTypeNum) {
        CarTypeNum = carTypeNum;
    }

    public Integer getAtPoint() {
        return AtPoint;
    }

    public void setAtPoint(Integer atPoint) {
        AtPoint = atPoint;
    }

    public String getTypeDescription() {
        return TypeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        TypeDescription = typeDescription;
    }

    public Integer getModelNum() {
        return ModelNum;
    }

    public void setModelNum(Integer modelNum) {
        ModelNum = modelNum;
    }

    public static BeanCarInfo currentConditions = null;

    public static BeanCarInfo getCurrentConditions() {
        return currentConditions;
    }

    public static void setCurrentConditions(BeanCarInfo currentConditions) {
        BeanCarInfo.currentConditions = currentConditions;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public void setDisplacement(Double displacement) {
        Displacement = displacement;
    }


    public Integer getCarInfoNum() {
        return CarInfoNum;
    }

    public void setCarInfoNum(Integer carInfoNum) {
        CarInfoNum = carInfoNum;
    }

    public Double getDisplacement() {
        return Displacement;
    }

    public Integer getSeats() {
        return Seats;
    }

    public void setSeats(Integer seats) {
        Seats = seats;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }


    public String getGears() {
        return Gears;
    }

    public void setGears(String gears) {
        Gears = gears;
    }

    public String getCarCondition() {
        return CarCondition;
    }

    public void setCarCondition(String carCondition) {
        CarCondition = carCondition;
    }
}
