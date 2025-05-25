package com.example.demo_PriceComparator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StockOfProducts {
    private ArrayList<RegularProduct> stockOfProducts;
    private HashMap<StoreDateKey,ArrayList<RegularProduct>> stockWithStore;

    public HashMap<StoreDateKey, ArrayList<RegularProduct>> getStockWithStore() {
        return stockWithStore;
    }


    public StockOfProducts() {
    stockOfProducts = new ArrayList<>();
    stockWithStore= new HashMap<>();
    }
    public void addToStock(RegularProduct p1){

        stockOfProducts.add(p1);
    }
    public String stockEnumeration() {
        String s="Stock Enumeration: \n";
//        for (Product p : stockWithStore) {
//            s= s + ("Product: " + p + "\n");
//        }
        for(Map.Entry<StoreDateKey,ArrayList<RegularProduct>> mapElement : stockWithStore.entrySet()){
        StoreDateKey storeAndDate=mapElement.getKey();
        ArrayList<RegularProduct> products=mapElement.getValue();
        String storeName=storeAndDate.getStoreName();
        LocalDate date= storeAndDate.getDate();
        ArrayList<RegularProduct> prodList= mapElement.getValue();
            s=s+ " the shop is : " + storeName + " the stock is on the date of: "+ date.toString() ;
            s=s+ " Product: " + prodList + "\n";


        }
        return s;
    }
    public void addToStoreStock(RegularProduct p1, StoreDateKey storeDateKey) {

        ArrayList<RegularProduct> products = stockWithStore.getOrDefault(storeDateKey, new ArrayList<>());

        products.add(p1);

        stockWithStore.put(storeDateKey, products);
        stockOfProducts.add(p1);
    }

    public ArrayList<RegularProduct> getProductList(){
        return stockOfProducts;
    }

    public String findProductNameById(String prodID){
        for(RegularProduct p : stockOfProducts){
            if(p.getProductId().equals(prodID)){
                return p.getProductName();
            }
        }
        return null;
    }

    public double findPriceById(String prodID){
        for(RegularProduct p : stockOfProducts){
            if(p.getProductId().equals(prodID)){
                return p.getPrice();
            }
        }
        return 0;
    }

    public RegularProduct findProductById(String prodId){
        for(RegularProduct p: stockOfProducts){
            if(p.getProductId().equals(prodId)){
                return p;
            }
        }
        return null;
    }
    public double findPriceByIdStoreDate(String prodId,String storeName, LocalDate date){
        for(RegularProduct p : stockOfProducts){
            if(p.getProductId().equals(prodId) && p.getStoreName().equals(storeName) && p.getDateOfRelease().equals(date)){
                return p.getPrice();
            }
        }
        System.out.println("There is no product with id " + prodId + " in store " + storeName + " on the date of " + date);
        return 0;
    }

    public ArrayList<String> getListProductNames(){
        return (ArrayList<String>) stockOfProducts.stream().map(RegularProduct::getProductName).collect(Collectors.toList());
    }

}
