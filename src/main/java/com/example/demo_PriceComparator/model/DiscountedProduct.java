package com.example.demo_PriceComparator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiscountedProduct extends Product{

    //these 2 are for the additional task 
    private double originalPrice;
    private double discountPrice;
    private CURRENCY currency;


    public CURRENCY getCurrency() {
        return currency;
    }

    public void setCurrency(CURRENCY currency) {
        this.currency = currency;
    }


    private LocalDate from_date;
    private LocalDate to_date;
    private int percentage_of_discount;

    public double getOriginalPrice() {
        return originalPrice;
    }


    public void setOriginalAndDiscountedPrice(double originalPrice) {
        this.originalPrice = originalPrice;
        this.discountPrice = originalPrice * (1-(percentage_of_discount/100.0));
    }

    public double getDiscountPrice() {
        return discountPrice;
    }





    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public int getPercentage_of_discount() {
        return percentage_of_discount;
    }

    public void setPercentage_of_discount(int percentage_of_discount) {
        this.percentage_of_discount = percentage_of_discount;
    }



    public DiscountedProduct(LocalDate from_date, LocalDate to_date, int percentage_of_discount,String productId, String productName, String productCategory, String brand, double package_quantity, String package_unit) {
        super(productId, productName, productCategory, brand, package_quantity,  package_unit);
        this.from_date = from_date;
        this.to_date = to_date;
        this.percentage_of_discount = percentage_of_discount;
    }
    public String toString(){
        return super.toString() + " , the percentage of discount: " + percentage_of_discount + " from date: " + from_date + " to date: " + to_date + "\n";
    }

}
