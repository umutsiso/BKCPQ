package com.siso;

public class IDGenerator {
    private static String id= "Line Item ";
    private static int count = 0;

    public static String generateID(){
        return id + ++count;
    }
}
