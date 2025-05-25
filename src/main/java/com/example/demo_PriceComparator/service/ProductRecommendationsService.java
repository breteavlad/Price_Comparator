package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.Product;
import com.example.demo_PriceComparator.model.RegularProduct;
import com.example.demo_PriceComparator.model.StockOfProducts;
import com.example.demo_PriceComparator.model.StoreDateKey;
import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductRecommendationsService {
    //TODO: Make it return the products with best value/unit
public ArrayList<RegularProduct> getValuePerUnit(StockOfProducts stockOfProducts) {
    UtilService utilService = new UtilService();
    Map<String,RegularProduct> bestProductMap = new HashMap<>();

    for(Map.Entry<StoreDateKey,ArrayList<RegularProduct>> mapElement : stockOfProducts.getStockWithStore().entrySet()) {
        ArrayList<RegularProduct> products = mapElement.getValue();

        for(RegularProduct product : products) {
            String productName = product.getProductName();
            RegularProduct bestProduct = bestProductMap.get(productName);
            if(bestProduct == null || utilService.getValuePerUnit(product) < utilService.getValuePerUnit(bestProduct)) {
                bestProductMap.put(productName, product);
            }
        }
    }



return new ArrayList<>(bestProductMap.values());
}
}
