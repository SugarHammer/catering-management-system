package com.zunke.shopmanager.dto.reportsForDMY;

/**
 * @author Yiming
 * @version 1.0
 * @since 2021/9/17 18:58
 */

public class ReportsForDMY {

    private String bywhen;
    private Integer ordersNum;
    private Double ordersSum;

    public String getBywhen() {
        return bywhen;
    }

    public void setBywhen(String bywhen) {
        this.bywhen = bywhen;
    }

    public Integer getOrdersNum() {
        return ordersNum;
    }

    public void setOrdersNum(Integer ordersNum) {
        this.ordersNum = ordersNum;
    }

    public Double getOrdersSum() {
        return ordersSum;
    }

    public void setOrdersSum(Double ordersSum) {
        this.ordersSum = ordersSum;
    }

    @Override
    public String toString() {
        return "ReportsForDay{" +
                "bywhen=" + bywhen +
                ", ordersNum=" + ordersNum +
                ", ordersSum=" + ordersSum +
                '}';
    }
}
