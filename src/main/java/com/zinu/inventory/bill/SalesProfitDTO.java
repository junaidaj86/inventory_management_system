package com.zinu.inventory.bill;

public class SalesProfitDTO {

    private double totalSales;
    private double totalProfit;

    public SalesProfitDTO(double totalSales, double totalProfit) {
        this.totalSales = totalSales;
        this.totalProfit = totalProfit;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }
}

