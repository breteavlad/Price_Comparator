package com.example.demo_PriceComparator.model;

public class RegularProduct extends Product{
    private CURRENCY currency;
    private double price;

    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public RegularProduct(String productId, String productName, String productCategory, String brand, double package_quantity, String package_unit,double price,  CURRENCY currency) {
        super(productId, productName, productCategory, brand, package_quantity,  package_unit);
        this.price = price;
        this.currency = currency;
    }

    public String toString(){
        return super.toString()  + " the price is : " + price + " the currency is : " + currency + "\n";
    }
}
