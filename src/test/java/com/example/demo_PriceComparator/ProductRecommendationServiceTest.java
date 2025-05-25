package com.example.demo_PriceComparator;


import com.example.demo_PriceComparator.model.*;
import com.example.demo_PriceComparator.service.ProductRecommendationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRecommendationServiceTest {

    private ProductRecommendationsService recommendationsService;
    private StockOfProducts stock;

    @BeforeEach
    public void setUp() {
        recommendationsService = new ProductRecommendationsService();
        stock = new StockOfProducts();

        StoreDateKey key1 = new StoreDateKey("Lidl", LocalDate.of(2024, 5, 10));
        StoreDateKey key2 = new StoreDateKey("Mega", LocalDate.of(2024, 5, 10));

        // Create sample products
        RegularProduct milkCheap = new RegularProduct("P001", "Milk", "Dairy", "Zuzu", 1.0, "L", 5.0, CURRENCY.RON);      // 5.0 RON/L
        RegularProduct milkExpensive = new RegularProduct("P002", "Milk", "Dairy", "Zuzu", 1.0, "L", 7.0, CURRENCY.RON); // 7.0 RON/L

        RegularProduct bread1 = new RegularProduct("P003", "Bread", "Bakery", "Panemar", 0.5, "kg", 3.0, CURRENCY.RON);  // 6.0 RON/kg
        RegularProduct bread2 = new RegularProduct("P004", "Bread", "Bakery", "Panemar", 1.0, "kg", 5.0, CURRENCY.RON);  // 5.0 RON/kg

        stock.getStockWithStore().put(key1, new ArrayList<>(List.of(milkExpensive, bread1)));
        stock.getStockWithStore().put(key2, new ArrayList<>(List.of(milkCheap, bread2)));
    }

    @Test
    public void testGetValuePerUnit_ReturnsBestValueProducts() {
        List<RegularProduct> bestProducts = recommendationsService.getValuePerUnit(stock);

        assertEquals(2, bestProducts.size());

        Optional<RegularProduct> bestMilk = bestProducts.stream()
                .filter(p -> p.getProductName().equals("Milk"))
                .findFirst();

        Optional<RegularProduct> bestBread = bestProducts.stream()
                .filter(p -> p.getProductName().equals("Bread"))
                .findFirst();

        assertTrue(bestMilk.isPresent());
        assertTrue(bestBread.isPresent());

        assertEquals(5.0, bestMilk.get().getPrice(), 0.001);
        assertEquals(5.0, bestBread.get().getPrice(), 0.001);
    }
}
