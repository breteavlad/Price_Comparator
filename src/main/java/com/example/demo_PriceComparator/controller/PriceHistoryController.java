package com.example.demo_PriceComparator.controller;

import com.example.demo_PriceComparator.model.PriceHistoryData;
import com.example.demo_PriceComparator.service.GraphFilterService;
import com.example.demo_PriceComparator.service.PriceAlertService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class PriceHistoryController {
GraphFilterService graphFilterService = new GraphFilterService();

    @GetMapping("/prices")
    public List<PriceHistoryData> getHistory(@RequestParam String productName,
                                             @RequestParam(required=false) String store,
                                              @RequestParam(required=false) String category,
                                             @RequestParam(required=false) String brand){
        return graphFilterService.getPriceHistory(productName, store, category, brand);

    }

}
