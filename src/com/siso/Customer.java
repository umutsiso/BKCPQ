package com.siso;

public class Customer {
    private String fullName;
    private String school;
    private double quoteTotal;

    public Customer(){

    }
    public Customer(String fullName, String school){
        this.fullName = fullName;
        this.school = school;
        this.quoteTotal = 0;
    }

    public void setQuoteTotal(double quoteTotal) {
        this.quoteTotal = quoteTotal;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSchool() {
        return school;
    }

    public Double getQuoteTotal() {
        return quoteTotal;
    }
}
