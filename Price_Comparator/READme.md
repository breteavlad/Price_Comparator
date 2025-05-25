📘 README
1. Brief Overview of the Project Structure
The project was structured with three main distinct packages: model, service, and controller.

The model package is used for storage of different types of data, for example:

DiscountedProduct gets its attributes from the discount CSVs.

PriceAlert gets the data from the options selected by the user (the target price and the name of the product).

The service package contains classes that represent the logic behind the entire application:

BasketService finds the products with the smallest price from all the stores.

BestDiscountService returns the best discounts for all the products considering all the stores.

NewDiscountService returns the discounts that appeared in the last 24h.

GraphFilterService is used to find the PriceHistoryData (in model, it has 2 parameters: price and LocalDate) using the following parameters: productName (mandatory), product category, store name, and brand (optional). This data will be used for the frontend to eventually construct a graph.

PriceAlertService is used for the scheduler task. It will allow the user to enter a product and a price, and they will be announced when the product has fallen below or to the target price.

ProductRecommendationService recommends products based on the price/quantity, the unit used being also normalized to L/kg instead of mL/g.

SmartWeeklyPlanService is the additional task that allows a more interactive experience. The user introduces a budget, a store, and the date, then gets to choose the products that they want to buy by typing its ID from the list that they can see above. As they add products, the bar keeps getting greener so the user knows their budget is getting to an end.

There are also some auxiliary files in the service package:

UtilService – used for string operations and other kinds of auxiliary functions.

FileFormatService – used for reading the CSV files and extracting the relevant information.

When it comes to the data structures used in the project:

I used ArrayLists, HashSets, and HashMaps.

I mainly used ArrayLists for simple applications because they are very convenient and have dynamic allocation property.

When I needed an array with only unique elements, I chose a HashSet.

For example, in BestDiscountsService, I used a HashMap for its property of unique keys, although I returned an ArrayList.

2. Instructions on How to Build and Run the Application
The application is cloned via:

git clone <repo_html/ssh_link>
Then, it is opened in an IDE (for example, IntelliJ) and run.

3. Assumptions Made / Simplifications
I assumed that the points for the graph would have the attributes of price and data, and the data would correspond to the data written as title for the CSV.

4. How to Use Implemented Features (API Endpoints)
The only endpoint is in /history/prices and it can be accessed via Postman, for example, using a link similar to:

localhost:<your_access_port>/history/prices?productName=<product_name>&store=<store_name>