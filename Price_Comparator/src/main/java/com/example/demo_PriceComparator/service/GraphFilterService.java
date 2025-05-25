package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class GraphFilterService {


    public List<PriceHistoryData> getPriceHistory(String productName, String store, String productCategory, String brand) {
        StockOfProducts stockOfProducts = new StockOfProducts();
        StockOfDiscountedProducts stockOfDiscountedProducts = new StockOfDiscountedProducts();
        FileFormatService fileFormatService = new FileFormatService();
        fileFormatService.readAllCSVs(stockOfProducts, stockOfDiscountedProducts);

        Set<PriceHistoryData> dataList = new HashSet<>();
        for (Map.Entry<StoreDateKey, ArrayList<RegularProduct>> mapElement : stockOfProducts.getStockWithStore().entrySet()) {
            List<RegularProduct> regularProducts = mapElement.getValue().stream().toList();
            List<PriceHistoryData> filteredData = new ArrayList<>();
            //System.out.println("The values inside getPriceHistory: " + regularProducts);
            filteredData = regularProducts.stream().distinct()
                    .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                    .filter(product -> productCategory == null || product.getProductCategory().equalsIgnoreCase(productCategory))
                    .filter(product -> brand == null || product.getBrand().equalsIgnoreCase(brand))
                    .filter(product -> store == null || product.getStoreName().equalsIgnoreCase(store))
                    .map(product -> new PriceHistoryData(product.getDateOfRelease(), product.getPrice()))
                    .collect(Collectors.toList());
            ;
            dataList.addAll(filteredData);
        }
        return new ArrayList<>(dataList);
    }

}
