package com.example.demo_PriceComparator.model;

import java.time.LocalDate;

public class PriceHistoryData {
    private LocalDate date;
    private double price;

    public PriceHistoryData(LocalDate date, double price) {
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
