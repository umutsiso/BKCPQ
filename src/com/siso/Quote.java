package com.siso;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quote {
    Customer customer = new Customer();
    private List<LineItem> itemList = new ArrayList<>();
    double quoteTotal = 0;

    {
        calculateAll();
    }

    public Quote(Customer customer, List<LineItem> itemList) {
        this.customer = customer;
        this.itemList = itemList;
    }


    public void calculateAll() {
        double discount, customerPrice, marginPercent, itemTotal;
        for (LineItem item : itemList) {
            discount = calcDiscount(item);
            customerPrice = calcCustomerPrice(item, discount);
            itemTotal = calcItemTotal(item, customerPrice);
            marginPercent = marginPercent(item, customerPrice);
            item.setDiscount(discount);
            item.setLineItemTotal(itemTotal);
            item.setCustomerPrice(customerPrice);
            item.setMarginPercent(marginPercent);
            quoteTotal = quoteTotal + itemTotal;
        }
        quoteTotal = Math.round((quoteTotal + 0.005) * 100) / 100.00;
        customer.setQuoteTotal(quoteTotal);

    }


    public List<LineItem> getItemList() {
        List<LineItem> newList = itemList;
        return newList;
    }


    public void addItem(LineItem item) {
        try {
            if (!itemList.contains(item)) {
                itemList.add(item);
            } else {
                System.out.println(item.getName() + " is Duplicate!");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("You cannot add null Item!");
        }
    }

    public void removeItem(LineItem item) {
        try {
            if (itemList.contains(item)) {
                itemList.remove(item);
                System.out.println(item.getName() + "Has been removed !");
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            System.out.println("You cannot add null Item!");
        }
    }

    public void generateQuote() {
        if (itemList.isEmpty()) {
            System.out.println("There are no items in the List to be Quoted!");
            return;
        }
        sendQuoteToCustomer();
        sendQuoteToAccounting();
    }

    private void sendQuoteToCustomer() {
        String fullName = customer.getFullName();
        String school = customer.getSchool();
        int number = 1;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("customerQuote.txt"))) {
            bufferedWriter.write("Quote details are generated below." + "\n");
            bufferedWriter.write("Customer Details : " + fullName + ", " + school + " || QuoteTotal : " + quoteTotal + "\n" + "\n");
            for (LineItem item : itemList) {
                String material = item.getItemMaterial();
                String desc = item.getItemDesc();
                double price = item.getItemPrice();
                int quantity = item.getItemQuantity();
                String UOM = item.getUOM();
                double lineTotal = item.getLineItemTotal();
                bufferedWriter.write(number++ + ") " + "Details for item " + item.getItemNo() + "\n");
                bufferedWriter.write("Item : " + material + "," + desc + "\n");
                bufferedWriter.write("Item Price : " + String.format("%.2f", price) + "\n");
                bufferedWriter.write("Item Quantity : " + quantity + "\n");
                bufferedWriter.write("Type : " + UOM + "\n");
                bufferedWriter.write("Line Total : " + String.format("%.2f", lineTotal) + "\n" + "\n");
            }
        } catch(IOException e){
        System.out.println("IOException" + e.getMessage());
    }
}

    private void sendQuoteToAccounting() {
        String fullName = customer.getFullName();
        String school = customer.getSchool();
        int number = 1;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("AccountingQuote.txt"))) {
            bufferedWriter.write("Quote details are generated below." + "\n");
            bufferedWriter.write("Customer Details : " + fullName + ", " + school + " || QuoteTotal : " + quoteTotal + "\n" + "\n");
            for (LineItem item : itemList) {
                String itemNo = item.getItemNo();
                int quantity = item.getItemQuantity();
                double cost = item.getItemCost();
                double customerPrice = item.getCustomerPrice();
                double margin = item.getMarginPercent();
                bufferedWriter.write(number++ + ") " + "Details for item " + itemNo + "\n");
                bufferedWriter.write("Item Quantity : " + quantity + "\n");
                bufferedWriter.write("Item Cost : " + String.format("%.1f", cost) + "\n");
                bufferedWriter.write("Customer Price : " + String.format("%.2f",customerPrice) + "\n");
                bufferedWriter.write("Margin : " + margin + "\n" + "\n");
            }
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }


    private double calcDiscount(LineItem item){
        double discount = 0;
        int quantity = item.getItemQuantity();
        String material = item.getItemMaterial();
        double cost = item.getItemCost();
        double price = item.getItemPrice();
        if(quantity > 100 && material == "Iron"){
            discount += 50;
        }
        if(cost > 100 && (material == "Bronze" || material =="Steel")){
            discount += 15;
        }
        if(price>100 && (material == "Bronze" || material =="Steel")){
            discount += 3;
        }
        return discount;
    }


    private double calcCustomerPrice(LineItem item, double discount){
        return (item.getItemPrice()*(1-(discount/100)));
    }

    private double calcItemTotal(LineItem item, double customerPrice){
        double total = item.getItemQuantity()*customerPrice;
        total = Math.round((total+0.005)*100)/100.00;
        return total;
    }

    private double marginPercent(LineItem item, double customerPrice){
        double margin = ((customerPrice - item.getItemCost())/customerPrice)*100;
        margin = Math.round((margin+0.05)*10)/10.0;
        return margin;
    }

}
