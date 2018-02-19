package com.codecool.plaza.api;

public class MakeupProduct extends Product {

    private int amountInGrams;
    private boolean isCrueltyFree;

    public MakeupProduct(long barcode, String name, String manufacturer, int amountInGrams, boolean isCrueltyFree) {
        super(barcode, name, manufacturer);
        this.amountInGrams = amountInGrams;
        this.isCrueltyFree = isCrueltyFree;
    }

    public boolean getCrueltyFree() {
        return isCrueltyFree;
    }

    public int getAmountInGrams() {
        return amountInGrams;
    }

    public String toString() {
        return "Barcode: " + getBarcode() + "  Name: " + getName() + "  Manufacturer: " + getManufacturer() + "  Amount: " + getAmountInGrams() + "g" + "  Cruelty-free: " + getCrueltyFree();
    }
}
