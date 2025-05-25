package com.example.demo_PriceComparator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockOfDiscountedProducts {
    private ArrayList<DiscountedProduct> discountedProductArrayList;
    private ArrayList<DiscountedProduct> stockOfProducts;
    private HashMap<StoreDateKey,ArrayList<DiscountedProduct>> stockWithStore;

    public HashMap<StoreDateKey, ArrayList<DiscountedProduct>> getStockWithStore() {
        return stockWithStore;
    }


    public StockOfDiscountedProducts() {
        stockOfProducts = new ArrayList<>();
        stockWithStore= new HashMap<>();
    }
    public void addToStock(DiscountedProduct p1){

        stockOfProducts.add(p1);
    }
    public DiscountedProduct findProductById(String prodId){
        for(DiscountedProduct p: stockOfProducts){
            if(p.getProductId().equals(prodId)){
                return p;
            }
        }
        return null;
    }
    public String stockEnumeration() {
        String s="Stock Enumeration: \n";
//        for (Product p : stockWithStore) {
//            s= s + ("Product: " + p + "\n");
//        }
        for(Map.Entry<StoreDateKey,ArrayList<DiscountedProduct>> mapElement : stockWithStore.entrySet()){
            StoreDateKey storeAndDate=mapElement.getKey();
            ArrayList<DiscountedProduct> products=mapElement.getValue();
            String storeName=storeAndDate.getStoreName();
            LocalDate date= storeAndDate.getDate();
            ArrayList<DiscountedProduct> prodList= mapElement.getValue();
            s=s+ " the shop is : " + storeName + " the stock is on the date of: "+ date.toString() ;
            s=s+ " Product: " + prodList + "\n";


        }
        return s;
    }
    public void addToStoreStock(DiscountedProduct p1, StoreDateKey storeDateKey ){
        ArrayList<DiscountedProduct> products = stockWithStore.getOrDefault(storeDateKey, new ArrayList<>());

        products.add(p1);

        stockWithStore.put(storeDateKey, products);
        stockOfProducts.add(p1);
    }

    public ArrayList<DiscountedProduct> getProductList(){
        return stockOfProducts;
    }

    public String findProductNameById(String prodID){
        for(DiscountedProduct p : stockOfProducts){
            if(p.getProductId().equals(prodID)){
                return p.getProductName();
            }
        }
        return null;
    }

    public double findPriceById(String prodID){
        for(DiscountedProduct p : stockOfProducts){
            if(p.getProductId().equals(prodID)){
                return p.getOriginalPrice();
            }
        }
        return 0;
    }

}
