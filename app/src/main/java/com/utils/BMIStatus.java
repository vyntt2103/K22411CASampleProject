package com.utils;

public class BMIStatus {
    private double BMI; //double là kiểu dữ liệu số thực (có dấu phẩy)
    private String description;

    public BMIStatus() {
    }

    public BMIStatus(double BMI, String description) {
        this.BMI = BMI;
        this.description = description;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
