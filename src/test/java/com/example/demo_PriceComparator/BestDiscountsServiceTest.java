package com.example.demo_PriceComparator;


import com.example.demo_PriceComparator.model.*;
import com.example.demo_PriceComparator.service.BestDiscountsService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BestDiscountsServiceTest {

    @Test
    public void testFindBestDiscounts() {
        // Create DiscountedProduct with proper constructor
        DiscountedProduct apple10 = new DiscountedProduct(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(5),
                10,
                "P001", "Apple", "Fruit", "BrandA", 1.0, "kg"
        );
        apple10.setOriginalAndDiscountedPrice(5.0);
        apple10.setCurrency(CURRENCY.RON);

        DiscountedProduct apple25 = new DiscountedProduct(
                LocalDate.now().minusDays(2),
                LocalDate.now().plusDays(3),
                25,
                "P002", "Apple", "Fruit", "BrandB", 1.0, "kg"
        );
        apple25.setOriginalAndDiscountedPrice(5.0);
        apple25.setCurrency(CURRENCY.RON);

        DiscountedProduct banana15 = new DiscountedProduct(
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                15,
                "P003", "Banana", "Fruit", "BrandA", 1.0, "kg"
        );
        banana15.setOriginalAndDiscountedPrice(4.0);
        banana15.setCurrency(CURRENCY.RON);

        // Create stock
        StockOfDiscountedProducts stock = new StockOfDiscountedProducts();
        StoreDateKey key1 = new StoreDateKey("StoreX", LocalDate.now());
        StoreDateKey key2 = new StoreDateKey("StoreY", LocalDate.now());

        stock.getStockWithStore().put(key1, new ArrayList<>(java.util.List.of(apple10, banana15)));
        stock.getStockWithStore().put(key2, new ArrayList<>(java.util.List.of(apple25)));

        // Invoke service
        BestDiscountsService service = new BestDiscountsService();
        ArrayList<DiscountedProduct> bestDiscounts = service.findBestDiscounts(stock);

        // Assert
        assertEquals(2, bestDiscounts.size());

        Map<String, DiscountedProduct> byName = bestDiscounts.stream()
                .collect(java.util.stream.Collectors.toMap(
                        DiscountedProduct::getProductName,
                        p -> p
                ));

        assertEquals(25, byName.get("Apple").getPercentage_of_discount());
        assertEquals(15, byName.get("Banana").getPercentage_of_discount());
        assertEquals(3.75, byName.get("Apple").getDiscountPrice(), 0.01); // 25% off 5.0
        assertEquals(3.4, byName.get("Banana").getDiscountPrice(), 0.01); // 15% off 4.0
    }
}
