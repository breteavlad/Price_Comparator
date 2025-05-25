package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.DiscountedProduct;
import com.example.demo_PriceComparator.model.StockOfDiscountedProducts;
import com.example.demo_PriceComparator.model.StoreDateKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class NewDiscountsService {
    public ArrayList<DiscountedProduct> getAddedDiscountsLast24H(StockOfDiscountedProducts stockOfDiscountedProducts) {
        LocalDate usedLocalDate = LocalDate.of(2025,5,10);
        ArrayList<DiscountedProduct> lastDayDiscProducts = new ArrayList<>();
        for(Map.Entry<StoreDateKey , ArrayList<DiscountedProduct>> mapElement : stockOfDiscountedProducts.getStockWithStore().entrySet()){

            StoreDateKey storeDateKey = mapElement.getKey();
            ArrayList<DiscountedProduct> discountedProducts = mapElement.getValue();
            for(DiscountedProduct discountedProduct : discountedProducts){
                //System.out.println("From date is : " + discountedProduct.getFrom_date());
                if(discountedProduct.getFrom_date().equals(usedLocalDate.minusDays(1))
                        ){
                lastDayDiscProducts.add(discountedProduct);
                }
            }
        }
        return lastDayDiscProducts;
    }
}
