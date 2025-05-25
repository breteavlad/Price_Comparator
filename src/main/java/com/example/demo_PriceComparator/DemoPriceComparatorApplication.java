package com.example.demo_PriceComparator;

import com.example.demo_PriceComparator.model.*;
import com.example.demo_PriceComparator.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoPriceComparatorApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoPriceComparatorApplication.class, args);
        //add the data into the hashmaps
        StockOfProducts s1 = new StockOfProducts();
        StockOfDiscountedProducts s2 = new StockOfDiscountedProducts();

        FileFormatService.readAllCSVs(s1, s2);


        while (true) {
            System.out.println("Please select an option.You have 1 -> optimize basket \n 2-> show best discounts \n" +
                    " 3-> show new discounts \n 4-> products recommendations \n 5-> custom price alert \n 6-> (ADDITIONAL TASK)" +
                    "Smart Weekly Plan \n YOU CAN ALSO ACCESS " +
                    "the price history graph via the API /history/prices?productName={enterNameOfProduct}" +
                    "&storeName={enterStoreName}");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            System.out.println("Press q to exit from application");
            switch (option) {
                case "1":
                    BasketService basketService = new BasketService(s1);

                    HashSet<RegularProduct> bestProducts = new HashSet<>();
                    HashSet<RegularProduct> allBestProducts = new HashSet<>();
                    ArrayList<String> productNames = new ArrayList<>();
                    allBestProducts = basketService.getBestPrices(s1.getListProductNames());

                    System.out.println("The best products are: " + allBestProducts);
                    while (true) {


                        String theStore = null;


                        System.out.println("Please enter your shopping list. Write 'q' when finished writing your shopping list ");
                        String input = scanner.nextLine();
                        System.out.println(">" + input);
                        productNames.add(input);


                        if (input.equals("q")) {
                            break;
                        }
                    }

                    bestProducts = basketService.getBestPrices(productNames);
                    System.out.println("Best products are: " + bestProducts);
                    break;
                case "2":
                    //TODO: <Optional> Make the list more aesthetic
                    BestDiscountsService bestDiscountsService = new BestDiscountsService();
                    System.out.println("The best discounts are: " + bestDiscountsService.findBestDiscounts(s2));

                    break;
                case "3":
                    //TODO: New Discounts
                    //List discounts that have been newly added (e.g., within the last 24 hours).
                    NewDiscountsService newDiscountsService = new NewDiscountsService();
                    System.out.println("The newly discounted products in the last 24h are: " + newDiscountsService.getAddedDiscountsLast24H(s2));

                    break;
                case "4":
                    //TODO: Product Recommendations
                    ProductRecommendationsService productRecommendationsService = new ProductRecommendationsService();

                    System.out.println("The best value/unit products are: " + productRecommendationsService.getValuePerUnit(s1));
                    break;
                case "5":
                    //TODO: Custom Price Alert
                    PriceAlertService priceAlertService = new PriceAlertService(s1);
                    System.out.println("Select the name of you target product: ");
                    String targetProductName = scanner.nextLine();

                    boolean isValid = false;
                    double targetPrice = 0;
                    while (!isValid) {
                        System.out.println("Select the target price below or at which the alert will be triggered: ");
                        try {
                            targetPrice = scanner.nextDouble();
                            isValid = true;
                        } catch (InputMismatchException e) {
                            e.printStackTrace();
                            scanner.nextLine();
                        }

                    }
                    PriceAlert priceAlert = new PriceAlert(targetProductName, targetPrice);
                    priceAlertService.addAlert(priceAlert);
                    while (true) {
                        System.out.println("Alerting system started.. the system will wait for the products with their target prices to be" +
                                "added. If you want to stop the process, press 'q' ");
                        //Decided to update here the state of the stockOfProductWithStore, that contains the array of products, storename and date
                        //because I want the application to mimique an alert system which will wait for data to be fetched from the storage(csv,database,excel)
                        FileFormatService.readAllCSVs(s1, s2);
                        priceAlertService.alertTargetPrice();
                        if (priceAlertService.verifyAllAlerts())
                            break;
                        String stop = scanner.nextLine();
                        if (stop.equals("q")) {
                            break;
                        }
                    }
                    break;
                case "6":
                    //TODO: Smart Weekly Planner
                    SmartWeeklyPlanService smartWeeklyPlanService = new SmartWeeklyPlanService(s1, s2);
                    smartWeeklyPlanService.startPlanner();


                case "q":
                    System.exit(0);

            }
        }


    }

}
