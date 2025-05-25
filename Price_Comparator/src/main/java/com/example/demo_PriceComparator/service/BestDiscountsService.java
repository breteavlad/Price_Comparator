package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.DiscountedProduct;
import com.example.demo_PriceComparator.model.StockOfDiscountedProducts;
import com.example.demo_PriceComparator.model.StoreDateKey;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BestDiscountsService {

    public ArrayList<DiscountedProduct> findBestDiscounts(StockOfDiscountedProducts stockOfDiscountedProducts) {
        Map<String, DiscountedProduct> bestDiscountsMap = new HashMap<>();

        for (Map.Entry<StoreDateKey, ArrayList<DiscountedProduct>> entry : stockOfDiscountedProducts.getStockWithStore().entrySet()) {
            for (DiscountedProduct product : entry.getValue()) {
                String name = product.getProductName();
                if (!bestDiscountsMap.containsKey(name)) {
                    bestDiscountsMap.put(name, product);
                } else {
                    DiscountedProduct currentBest = bestDiscountsMap.get(name);
                    if (product.getPercentage_of_discount() > currentBest.getPercentage_of_discount()) {
                        bestDiscountsMap.put(name, product);
                    }
                }
            }
        }

        return new ArrayList<>(bestDiscountsMap.values());
    }


}
