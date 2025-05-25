package com.example.demo_PriceComparator.service;

import com.example.demo_PriceComparator.model.*;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class FileFormatService {


    public static void readRegularCSV(String file, StockOfProducts s1, StoreDateKey storeDateKey, boolean isDiscount) {
        String storeName = storeDateKey.getStoreName();
        LocalDate date = storeDateKey.getDate();

        Product.listOfReleaseDates.add(date);

        try {
            FileReader fileReader = new FileReader(file);
            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();
            String[] nextLine;

            int skipCnt = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].trim();
                }
                if (skipCnt < 1) {
                    skipCnt++;
                    continue;
                }
                if (!isDiscount) {
                    try {
                        String productId = nextLine[0];
                        String prodName = nextLine[1];
                        String prodCategory = nextLine[2];
                        String brand = nextLine[3];
                        double package_quantity = 0;
                        try {
                            package_quantity = Double.parseDouble(nextLine[4]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        String package_unit = nextLine[5];
                        double price = Double.parseDouble(nextLine[6]);
                        CURRENCY currency = CURRENCY.valueOf(nextLine[7].toUpperCase().trim());

                        RegularProduct p1 = new RegularProduct(productId, prodName, prodCategory, brand, package_quantity, package_unit, price, currency);
                        p1.setStoreName(storeName);
                        p1.setDateOfRelease(date);
                        StockOfProducts stockOfProducts1 = new StockOfProducts();


                        //s1.addToStock(p1);
                        s1.addToStoreStock(p1, storeDateKey);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        System.out.println("The file that caused the problem is: " + file);
                    }

                    // System.out.println("store is : " + storeName.substring(0,1).toUpperCase() + storeName.substring(1,storeName.length()) + " productId is : " + productId + " Product name: "+ prodName + " product category: " + prodCategory +
                    //        " brand: " + brand + " package_quantity: " + package_quantity + "package_unit: " + package_unit + " price: " + price + "currency: " + currency);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDiscountedCSV(String file, StockOfDiscountedProducts s1, StockOfProducts s2, StoreDateKey storeDateKey, boolean isDiscount) {
        String storeName = storeDateKey.getStoreName();
        LocalDate date = storeDateKey.getDate();

        try {
            FileReader fileReader = new FileReader(file);
            CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(csvParser).build();
            String[] nextLine;

            int skipCnt = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].trim();
                }
                if (skipCnt < 1) {
                    skipCnt++;
                    continue;
                }
                if (nextLine.length != 9) {
                    System.out.println("Skipped malformed line: " + Arrays.toString(nextLine) + " from file " + file);
                    continue;
                }


                for (int i = 0; i < nextLine.length; i++) {
                    nextLine[i] = nextLine[i].trim();
                }
                try {
                    String productId = nextLine[0];
                    String prodName = nextLine[1];
                    String brand = nextLine[2];
                    double package_quantity = 0;
                    try {
                        package_quantity = Double.parseDouble(nextLine[3]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    String package_unit = nextLine[4];
                    String prodCategory = nextLine[5];
                    LocalDate from_date = LocalDate.parse(nextLine[6]);
                    LocalDate to_date = LocalDate.parse(nextLine[7]);
                    int percentage_of_discount = Integer.parseInt(nextLine[8].trim());
                    DiscountedProduct p1 = new DiscountedProduct(from_date, to_date, percentage_of_discount, productId, prodName, prodCategory, brand, package_quantity, package_unit);
                    p1.setStoreName(storeName);
                    p1.setDateOfRelease(date);
//                    System.out.println("Discount store is : " + storeName.substring(0, 1).toUpperCase() + storeName.substring(1, storeName.length()) + " productId is : " + productId + " Product name: " + prodName + " product category: " + prodCategory +
//                            " brand: " + brand + " package_quantity: " + package_quantity + "package_unit: " + package_unit + " percentage of discount: " + percentage_of_discount + "from date: " + from_date + " to date: " + to_date);

                    //s1.addToStock(p1);
                    s1.addToStoreStock(p1, storeDateKey);

                    findAndSetAccordingDiscPrice(s2, s1);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("The file that caused the problem is: " + file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readAllCSVs(StockOfProducts s1, StockOfDiscountedProducts s2) {
        s1.getStockWithStore().clear();
        s2.getStockWithStore().clear();
        s1.getProductList().clear();
        s2.getProductList().clear();
        String directoryPath = ".";
        UtilService utilService = new UtilService();
        String[] fileNames = {"kaufland_2025-05-01.csv",
                "kaufland_2025-05-08.csv",
                "profi_2025-05-01.csv",
                "profi_2025-05-08.csv",
                "lidl_2025-05-01.csv",
                "lidl_2025-05-08.csv",
                "kaufland_discounts_2025-05-01.csv",
                "kaufland_discounts_2025-05-08.csv",
                "profi_discounts_2025-05-01.csv",
                "profi_discounts_2025-05-08.csv",
                "lidl_discounts_2025-05-01.csv",
                "lidl_discounts_2025-05-08.csv"};

        File[] files = null;
        File directory = new File(directoryPath);
        files = directory.listFiles();
        if (files != null) {

            for (String fileName : fileNames) {
                StoreDateKey storeDateKey = utilService.extractStoreDateKey(fileName);
                boolean isDiscounted = utilService.isDiscounted(fileName);
                if (isDiscounted) {
                    readDiscountedCSV("files/" + fileName, s2, s1, storeDateKey, isDiscounted);
                    //System.out.println("Product list: " + s2.getProductList());
                } else {
                    readRegularCSV("files/" + fileName, s1, storeDateKey, isDiscounted);
                }
                //fileFormatService.readCSVfile("files/" + fileName,s1,storeDateKey,isDiscounted);
            }

        }
    }

    public static void findAndSetAccordingDiscPrice(StockOfProducts s1, StockOfDiscountedProducts s2) {
        for (DiscountedProduct dP : s2.getProductList()) {
            for (RegularProduct p : s1.getProductList()) {
                if (dP.getProductId().equals(p.getProductId()) && dP.getStoreName().equals(p.getStoreName())) {
                    dP.setOriginalAndDiscountedPrice(p.getPrice());
                    dP.setCurrency(p.getCurrency());
                }
            }
        }

    }


}

