package com.example.demo_PriceComparator;


import com.example.demo_PriceComparator.model.*;
import com.example.demo_PriceComparator.service.SmartWeeklyPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class SmartWeeklyPlanServiceTest {

    private StockOfDiscountedProducts discountedStock;
    private SmartWeeklyPlanService service;

    @BeforeEach
    public void setup() {
        discountedStock = new StockOfDiscountedProducts();

        DiscountedProduct p1 = new DiscountedProduct(
                LocalDate.of(2025, 5, 10),
                LocalDate.of(2025, 5, 15),
                30,
                "D001", "Juice", "Beverages", "BrandA", 1.0, "L"
        );
        p1.setStoreName("Lidl");
        p1.setCurrency(CURRENCY.RON);
        p1.setOriginalAndDiscountedPrice(10.0);

        DiscountedProduct p2 = new DiscountedProduct(
                LocalDate.of(2025, 5, 9),
                LocalDate.of(2025, 5, 20),
                20,
                "D002", "Yogurt", "Dairy", "BrandB", 0.5, "kg"
        );
        p2.setStoreName("Kaufland");
        p2.setCurrency(CURRENCY.RON);
        p2.setOriginalAndDiscountedPrice(5.0);

        DiscountedProduct p3 = new DiscountedProduct(
                LocalDate.of(2025, 5, 8),
                LocalDate.of(2025, 5, 18),
                25,
                "D003", "Bread", "Bakery", "BrandC", 0.4, "kg"
        );
        p3.setStoreName("Profi");
        p3.setCurrency(CURRENCY.RON);
        p3.setOriginalAndDiscountedPrice(3.0);

        ArrayList<DiscountedProduct> lidlProducts = new ArrayList<>();
        lidlProducts.add(p1);

        ArrayList<DiscountedProduct> kauflandProducts = new ArrayList<>();
        kauflandProducts.add(p2);

        ArrayList<DiscountedProduct> profiProducts = new ArrayList<>();
        profiProducts.add(p3);

        discountedStock.getStockWithStore().put(new StoreDateKey("Lidl", LocalDate.of(2025, 5, 10)), lidlProducts);
        discountedStock.getStockWithStore().put(new StoreDateKey("Kaufland", LocalDate.of(2025, 5, 9)), kauflandProducts);
        discountedStock.getStockWithStore().put(new StoreDateKey("Profi", LocalDate.of(2025, 5, 8)), profiProducts);

        // StockOfProducts can be empty for this test
        service = new SmartWeeklyPlanService(new StockOfProducts(), discountedStock);
    }

    @Test
    public void testFindPricesDiscountedProducts_Lidl() {
        System.out.println("=== TEST OUTPUT: Lidl ===");
        service.findPricesDiscountedProducts("Lidl");
    }

    @Test
    public void testFindPricesDiscountedProducts_Kaufland() {
        System.out.println("=== TEST OUTPUT: Kaufland ===");
        service.findPricesDiscountedProducts("Kaufland");
    }

    @Test
    public void testFindPricesDiscountedProducts_Profi() {
        System.out.println("=== TEST OUTPUT: Profi ===");
        service.findPricesDiscountedProducts("Profi");
    }

    @Test
    public void testFindPricesDiscountedProducts_Unknown() {
        System.out.println("=== TEST OUTPUT: UnknownStore ===");
        service.findPricesDiscountedProducts("UnknownStore");
    }
}
