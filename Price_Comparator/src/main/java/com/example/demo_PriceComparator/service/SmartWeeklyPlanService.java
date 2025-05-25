package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

//THIS IS THE ADDITIONAL TASK : Basically
public class SmartWeeklyPlanService {
    private StockOfProducts s1;
    private StockOfDiscountedProducts s2;

    public SmartWeeklyPlanService(StockOfProducts s1, StockOfDiscountedProducts s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public static String getBudgetProgressBar(double totalSpent, double budget) {
        int totalBars = 10;
        double ratio = totalSpent / budget;
        int filledBars = (int) Math.min(totalBars, Math.round(ratio * totalBars));

        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < filledBars; i++) {
            bar.append("🟩");
        }
        for (int i = filledBars; i < totalBars; i++) {
            bar.append("⬜");
        }

        return bar.toString();
    }


    public void startPlanner() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<RegularProduct> boughtProducts = new ArrayList<>();

        System.out.println("What is your budget for this shopping trip? Ex:200 RON");
        double budget = 0;
        CURRENCY currency = null;
        while (true) {
            try {
                String budgetString = scanner.nextLine();
                budget = Double.parseDouble(budgetString.substring(0, budgetString.indexOf(" ")));
                currency = CURRENCY.valueOf(budgetString.substring(budgetString.indexOf(" "), budgetString.length()).trim());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid budget: Ex:200 RON");
                e.printStackTrace();
            }
        }
        System.out.println("Budget of : " + budget + " " + currency);
        String storeName;
        System.out.println("Enter your preferred store : ");
        storeName = scanner.nextLine();
        System.out.println("Choose your date . You would have to choose between:  " + Product.listOfReleaseDates.toString());
        LocalDate dateOfRelease = LocalDate.parse(scanner.nextLine());

        for (int i = 0; i < s1.getProductList().size(); i++) {
            if (s1.getProductList().get(i).getStoreName().equalsIgnoreCase(storeName) && s1.getProductList().get(i).getDateOfRelease().equals(dateOfRelease)) {
                System.out.println("Please choose the id of the product you would like to buy: " + (i + 1) + s1.getProductList().get(i));

            }

        }
        System.out.println("EXAMPLE:Enter P001 for lapte zuzu");
        double totalBought = 0;
        do {
            System.out.println("Please enter your product id. Press 'q' if you want to exit ");

            String choice = scanner.nextLine();
            Double priceOfBoughtProduct = s1.findPriceByIdStoreDate(choice, storeName, dateOfRelease);
            totalBought = totalBought + s1.findPriceByIdStoreDate(choice, storeName, dateOfRelease);


            if (totalBought > budget) {
                System.out.println("OOOps... Your budget exceeds the limit of " + budget);
                break;

            }
            boughtProducts.add(s1.findProductById(choice));
            System.out.println("You have chosen to buy: " + s1.findProductNameById(choice));
            System.out.printf("💸 Total: %.2f / %.2f %s %s%n", totalBought, budget, currency, getBudgetProgressBar(totalBought, budget));

            if (choice.equals("q")) {
                break;
            }
        } while (true);
        System.out.println("You have just bought : " + boughtProducts.toString() + " .Thank you for using SmartWeeklyPlanService");


    }

    public void findPricesDiscountedProducts( String storeFilter) {
        // find the discounted products from a store
        ArrayList<DiscountedProduct> discountedProducts = new ArrayList<>();

        for (Map.Entry<StoreDateKey, ArrayList<DiscountedProduct>> entry : s2.getStockWithStore().entrySet()) {
            ArrayList<DiscountedProduct> products = entry.getValue();
            for (DiscountedProduct product : products) {
                if (product.getStoreName().equalsIgnoreCase(storeFilter)) {
                    discountedProducts.add(product);
                }
            }
        }
        System.out.println("Discounted products : " + discountedProducts.toString());
    }
}



