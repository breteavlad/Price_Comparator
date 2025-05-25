package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.RegularProduct;
import com.example.demo_PriceComparator.model.StoreDateKey;

import java.time.LocalDate;

public class UtilService {
    public String extractStoreName(String dataFileName) {
        //ex: kaufland_discounts_2025-05-01.csv -> kaufland; kaufland_2025-05-01.csv -> kaufland
        return dataFileName.substring(0, dataFileName.lastIndexOf("_"));

    }

    public LocalDate extractDate(String dateFileName) {
        //kaufland_discounts_2025-05-01.csv -> 2025-05-01;
        String dateString = dateFileName.substring(dateFileName.lastIndexOf("_") + 1, dateFileName.indexOf("."));
        return LocalDate.parse(dateString);
    }

    public StoreDateKey extractStoreDateKey(String dateFileName) {
        String storeName = extractStoreName(dateFileName);
        LocalDate date = extractDate(dateFileName);
        StoreDateKey storeDateKey = new StoreDateKey(storeName, date);
        return storeDateKey;
    }

    public boolean isDiscounted(String dateFileName) {
        return dateFileName.contains("discounts");
    }

    public double normalizeQuantity(String unit, double quantity) {
        return switch (unit.toLowerCase()) {
            case "g" -> quantity / 1000;
            case "ml" -> quantity / 1000;
            default -> quantity;
        };

    }

    public double getValuePerUnit(RegularProduct regularProduct) {
        double normalizedQuantity = normalizeQuantity(regularProduct.getPackage_unit(), regularProduct.getPackage_quantity());
        return regularProduct.getPrice() /normalizedQuantity;
    }


}
