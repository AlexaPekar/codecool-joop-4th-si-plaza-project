package com.codecool.plaza.api;

public class ClothingProduct  extends Product {

    private String material;
    private String colour;

    protected ClothingProduct(long barcode, String name, String manufacturer, String material, String colour) {
        super(barcode, name, manufacturer);
        this.material = material;
        this.colour = colour;
    }

    public String getMaterial() {
        return material;
    }

    public String getColour() {
        return colour;
    }

    public String toString() {
        return "Barcode: " + getBarcode() + "  Name: " + getName() + "  Manufacturer: " + getManufacturer() + "  Material: " + getMaterial() + "  Colour: " + getColour();
    }
}
