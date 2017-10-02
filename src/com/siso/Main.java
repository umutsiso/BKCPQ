package com.siso;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /**
         * Creating new Quote that has new Customer and itemList
         * Also populating Item List with 3 Items
         * Then calling calculate all method that calculates all the item details and stores it in an inner class that each item has
         * Then generates quotes ! (This could have been done using IO.
         * */
        List<LineItem> itemList = new ArrayList<>();
        Quote quote = new Quote(new Customer("Jake SISO", "Purdue"), itemList);
        LineItem item = new LineItem("10003-SHD", "Shield", 299.3559, 179.0034, "Bronze", 5, LineItem.UOM.Each);
        quote.addItem(item);
        item = new LineItem("20005-SWD", "Sword", 1015.293, 999.00, "Steel", 10, LineItem.UOM.Each);
        quote.addItem(item);
        item = new LineItem("30001-CHN", "Chain", 3.99, 0.99, "Iron", 5000, LineItem.UOM.LinearFeet);
        quote.addItem(item);

        /**
         * */
        quote.calculateAll(); // Calculating the item details (customer price, discount etc..)
        quote.generateQuote(); //Creating 2 files to send to Customer and Accounting !

    }
}
