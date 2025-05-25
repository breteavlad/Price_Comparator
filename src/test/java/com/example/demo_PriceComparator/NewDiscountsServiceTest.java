package com.example.demo_PriceComparator;


import com.example.demo_PriceComparator.model.*;
import com.example.demo_PriceComparator.service.NewDiscountsService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NewDiscountsServiceTest {

    @Test
    public void testGetAddedDiscountsLast24H() {
        // Reference date in service: 2025-05-10
        LocalDate referenceDate = LocalDate.of(2025, 5, 10);
        LocalDate addedYesterday = referenceDate.minusDays(1); // 2025-05-09
        LocalDate addedToday = referenceDate;
        LocalDate addedTwoDaysAgo = referenceDate.minusDays(2);

        // Create products
        DiscountedProduct productYesterday = new DiscountedProduct(
                addedYesterday,
                referenceDate.plusDays(3),
                15,
                "P001", "Milk", "Dairy", "BrandA", 1.0, "L"
        );
        productYesterday.setOriginalAndDiscountedPrice(7.0);
        productYesterday.setCurrency(CURRENCY.RON);

        DiscountedProduct productToday = new DiscountedProduct(
                addedToday,
                referenceDate.plusDays(3),
                20,
                "P002", "Cheese", "Dairy", "BrandB", 0.5, "kg"
        );
        productToday.setOriginalAndDiscountedPrice(10.0);
        productToday.setCurrency(CURRENCY.RON);

        DiscountedProduct productOld = new DiscountedProduct(
                addedTwoDaysAgo,
                referenceDate.plusDays(5),
                10,
                "P003", "Bread", "Bakery", "BrandC", 0.3, "kg"
        );
        productOld.setOriginalAndDiscountedPrice(2.0);
        productOld.setCurrency(CURRENCY.RON);

        // Add to stock
        StockOfDiscountedProducts stock = new StockOfDiscountedProducts();
        StoreDateKey storeKey = new StoreDateKey("MegaImage", referenceDate);
        ArrayList<DiscountedProduct> products = new ArrayList<>();
        products.add(productYesterday);
        products.add(productToday);
        products.add(productOld);
        stock.getStockWithStore().put(storeKey, products);

        // Invoke service
        NewDiscountsService service = new NewDiscountsService();
        ArrayList<DiscountedProduct> result = service.getAddedDiscountsLast24H(stock);

        // Assert only one product is returned
        assertEquals(1, result.size());
        assertEquals("Milk", result.get(0).getProductName());
        assertEquals(LocalDate.of(2025, 5, 9), result.get(0).getFrom_date());
    }
}
