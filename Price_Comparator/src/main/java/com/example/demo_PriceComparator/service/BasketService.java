package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.Product;
import com.example.demo_PriceComparator.model.RegularProduct;
import com.example.demo_PriceComparator.model.StockOfProducts;
import com.example.demo_PriceComparator.model.StoreDateKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class BasketService {
    StockOfProducts stockOfProducts = new StockOfProducts();

    public BasketService(StockOfProducts s1) {
        this.stockOfProducts = s1;
    }

    public HashSet<RegularProduct> getBestPrices(ArrayList<String> productNames) {



        HashSet<RegularProduct> bestPrices = new HashSet<>();
        RegularProduct bestProduct = null;

//    for(String productName : productNames) {
//        System.out.println("The selected products are: " +productName);
//    }

        for (String productName : productNames) {

            for (Map.Entry<StoreDateKey, ArrayList<RegularProduct>> mapElement : stockOfProducts.getStockWithStore().entrySet()) {

                ArrayList<RegularProduct> products = mapElement.getValue();
                StoreDateKey storeNameAndDate = mapElement.getKey();
                String storeName = storeNameAndDate.getStoreName();
                LocalDate date = storeNameAndDate.getDate();


                for (RegularProduct product : products) {
                    if (!product.isDiscounted()) {
                        if (product.getProductName().equals(productName)) {
                            if (bestProduct == null) {
                                bestProduct = product;
                                bestProduct.setStoreName(product.getStoreName());
                            } else {
                                if (product.getPrice() < bestProduct.getPrice() && product.getPrice() > 0) {
                                    bestProduct = product;
                                    bestProduct.setStoreName(product.getStoreName());
                                }
                            }

                        }
                    }

                }

            }
            bestPrices.add(bestProduct);
            bestProduct = null;
        }
        return bestPrices;
    }

}
