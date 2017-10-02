package com.siso;

public class LineItem {
    private String itemNo;
    private String itemDesc;
    private String itemName = IDGenerator.generateID();
    private double itemPrice;
    private double itemCost;
    private String itemMaterial;
    private int itemQuantity;
    private UOM type;
    ItemDetail itemDetail = new ItemDetail(this.itemNo);

    public enum UOM{
        Each,
        LinearFeet
    };

    public LineItem(String itemNo, String itemDesc, double itemPrice, double itemCost, String itemMaterial, int itemQuantity, UOM type) {
        this.itemNo = itemNo;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.itemCost = itemCost;
        this.itemMaterial = itemMaterial;
        this.itemQuantity = itemQuantity;
        this.type = type;
    }

    public String getUOM(){
        return type.toString();
    }

    public void setLineItemTotal(double lineItemTotal) {
        itemDetail.setLineItemTotal(lineItemTotal);
    }

    public void setCustomerPrice(double customerPrice) {
        itemDetail.setCustomerPrice(customerPrice);
    }

    public void setMarginPercent(double marginPercent) {
        itemDetail.setMarginPercent(marginPercent);
    }

    public void setDiscount(double discount) {
        itemDetail.setDiscount(discount);
    }

    public double getLineItemTotal(){
        return itemDetail.getLineItemTotal();
    }

    public double getCustomerPrice() {
        return itemDetail.getCustomerPrice();
    }

    public double getMarginPercent() {
        return itemDetail.getMarginPercent();
    }

    public double getDiscount() {
        return itemDetail.getDiscount();
    }


    public String getName(){
        return this.itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public String getItemMaterial() {
        return itemMaterial;
    }

    public void setItemMaterial(String itemMaterial) {
        this.itemMaterial = itemMaterial;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    private class ItemDetail {
        private String itemNo;
        private double lineItemTotal;
        private double customerPrice;
        private double marginPercent;
        private double discount;

        private ItemDetail(String itemNo){
            this.itemNo = itemNo;
        }

        private void setLineItemTotal(double lineItemTotal) {
            this.lineItemTotal = lineItemTotal;
        }

        private void setCustomerPrice(double customerPrice) {
            this.customerPrice = customerPrice;
        }

        private void setMarginPercent(double marginPercent) {
            this.marginPercent = marginPercent;
        }

        private void setDiscount(double discount) {
            this.discount = discount;
        }

        private double getLineItemTotal(){
            return this.lineItemTotal;
        }

        private double getCustomerPrice() {
            return customerPrice;
        }

        private double getMarginPercent() {
            return marginPercent;
        }

        private double getDiscount() {
            return discount;
        }
    }

}
