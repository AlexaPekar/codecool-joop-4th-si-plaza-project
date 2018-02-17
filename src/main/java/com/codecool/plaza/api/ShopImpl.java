package com.codecool.plaza.api;

import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {

    private String name;
    private String owner;
    private Map<Long, ShopEntryImpl> products;
    private boolean isOpen;

    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }


    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public void close() {
        isOpen = false;
    }

    public List<Product> getProducts() throws ShopIsClosedException {
        return null;
    }

    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        return null;
    }

    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        return 0;
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        return false;
    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {

    }

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {

    }

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        return null;
    }

    private class ShopEntryImpl {

        private Product product;
        private int quantity;
        private float price;

        private ShopEntryImpl(Product product, int quantity, float price){
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct(){
            return product;
        }

        public void setProduct(Product product) {

        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {

        }

        public void increaseQuantity(int amount) {

        }

        public void decreaseQuantity(int amount) {

        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {

        }

        public String toString() {
            return null;
        }
    }
}
