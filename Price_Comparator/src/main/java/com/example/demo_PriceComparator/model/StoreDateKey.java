package com.example.demo_PriceComparator.model;

import java.time.LocalDate;
import java.util.Date;

public class StoreDateKey {
    private String storeName;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }



    public StoreDateKey(String storeName, LocalDate date) {
        this.storeName = storeName;
        this.date = date;
    }
}
