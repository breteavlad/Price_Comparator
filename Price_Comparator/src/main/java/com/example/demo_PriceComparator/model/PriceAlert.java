package com.example.demo_PriceComparator.model;

public class PriceAlert {
    private String productName;
    private double targetPrice;

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public boolean isAlerted() {
        return alerted;
    }

    public void setAlerted(boolean alerted) {
        this.alerted = alerted;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private boolean alerted=false;

    public PriceAlert(String productName, double targetPrice){
        this.productName = productName;
        this.targetPrice = targetPrice;
    }

    public String getProductName(){
        return productName;
    }
    public double getTargetPrice(){
        return targetPrice;
    }
}
