package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@EnableScheduling
public class PriceAlertService {
    private final List<PriceAlert> alerts = new ArrayList<>();
    private final StockOfProducts stockOfProducts;


    public PriceAlertService(StockOfProducts stockOfProducts) {
        this.stockOfProducts = stockOfProducts;
    }
    public void addAlert(PriceAlert alert) {
        alerts.add(alert);
    }

    @Scheduled(fixedRate = 5000)
    public void alertTargetPrice(){
        System.out.println("Inside alertTargetPrice");
        for(Map.Entry<StoreDateKey, ArrayList<RegularProduct>> mapElement : stockOfProducts.getStockWithStore().entrySet()){
            StoreDateKey key = mapElement.getKey();
            ArrayList<RegularProduct> products = mapElement.getValue();

            for (RegularProduct product : products) {
                for (PriceAlert alert : alerts) {
                   // System.out.println("Product to be alerted: " + alert.getProductName() + "with the price: " + alert.getTargetPrice());
                if(product.getProductName().equals(alert.getProductName()) && product.getPrice()<= alert.getTargetPrice()){
                    System.out.println("Attention " + " there was found a  product with name : " + product.getProductName() + " and with price of: " + product.getPrice());
                    alert.setAlerted(true);
                }
                }
            }
        }
    }
public boolean verifyAllAlerts(){
        for(PriceAlert alert : alerts){
            if(!alert.isAlerted()){
                return false;
            }
        }
        return true;
}
    public List<PriceAlert> getAlerts() {
        return alerts;
    }
}
