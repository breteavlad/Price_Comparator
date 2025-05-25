package com.example.demo_PriceComparator.model;

import java.time.LocalDate;
import java.util.HashSet;

public class Product {

    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private double package_quantity;
    private String package_unit;
    private String storeName;
    private LocalDate dateOfRelease;
    public static HashSet<LocalDate> listOfReleaseDates= new HashSet<>();

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }





    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }



    public boolean isDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(boolean discounted) {
        isDiscounted = discounted;
    }

    private boolean isDiscounted;


    public Product(){

    }

    public Product(String productId, String productName, String productCategory, String brand, double package_quantity, String package_unit) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.brand = brand;
        this.package_quantity = package_quantity;
        this.package_unit = package_unit;
        this.isDiscounted = false;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPackage_quantity() {
        return package_quantity;
    }

    public void setPackage_quantity(double package_quantity) {
        this.package_quantity = package_quantity;
    }

    public String getPackage_unit() {
        return package_unit;
    }

    public void setPackage_unit(String package_unit) {
        this.package_unit = package_unit;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }








    public String toString(){
        return " This product has the id: "+ this.getProductId() + " the name: " + this.getProductName() +
                " it has the product category: " + productCategory + " , the brand: " + brand + " the package quantity: "
                + package_quantity + " the package unit: " + package_unit  + " from the store " + this.getStoreName() ;
    }


}
