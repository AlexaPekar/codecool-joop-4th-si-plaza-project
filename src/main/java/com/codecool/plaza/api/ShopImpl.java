package com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.HashMap;
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
        products = new HashMap<>();
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
        List<Product> productList = new ArrayList<Product>();
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                productList.add(entry.getValue().getProduct());
            }
            return productList;
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getValue().getProduct().getName().equals(name)) {
                    return entry.getValue().getProduct();
                }
            }
            throw new NoSuchProductException("There is no product like this!");
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public float getPrice(long barcode) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    return entry.getValue().getPrice();
                }
            }
            throw new NoSuchProductException("There is no product like this!");
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    return true;
                }
            }
            return false;
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public void addNewProduct(Product product, int quantity, float price) throws ProductAlreadyExistsException, ShopIsClosedException {
        if (isOpen()) {
            if (!(hasProduct(product.getBarcode()))) {
                ShopEntryImpl shopEntry = new ShopEntryImpl(product, quantity, price);
                products.put(product.getBarcode(), shopEntry);
            } else {
                throw new ProductAlreadyExistsException("There is already a product like this!");
            }
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public void addProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    entry.getValue().increaseQuantity(quantity);
                }
            }
            throw new NoSuchProductException("There is no product like this!");
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public Product buyProduct(long barcode) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    if (entry.getValue().getQuantity() > 0) {
                        entry.getValue().decreaseQuantity(1);
                        return entry.getValue().getProduct();
                    } else {
                        throw new OutOfStockException("This product is out of stock!");
                    }
                }
            }
            throw new NoSuchProductException("There is no product like this!");
        } else {
            throw new ShopIsClosedException("This shop is closed!");
        }
    }

    public List<Product> buyProducts(long barcode, int quantity) throws NoSuchProductException, OutOfStockException, ShopIsClosedException {
        List<Product> productList = new ArrayList<Product>();
        if (isOpen()) {
            for (Map.Entry<Long, ShopEntryImpl> entry : products.entrySet()) {
                if (entry.getKey() == barcode) {
                    if (entry.getValue().getQuantity() >= quantity) {
                        entry.getValue().decreaseQuantity(quantity);
                        for (int i = 0; i < quantity; i++) {
                            productList.add(entry.getValue().getProduct());
                        }
                        return productList;
                    } else {
                        throw new OutOfStockException("This product is out of stock!");
                    }
                }
            }
            throw new NoSuchProductException("There is no product like this!");
        } else {
            throw new ShopIsClosedException("This shop is closed");
        }
    }


    private class ShopEntryImpl {

        private Product product;
        private int quantity;
        private float price;

        private ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product argProduct) {
            product = argProduct;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int argQuantity) {
            quantity = argQuantity;
        }

        public void increaseQuantity(int amount) {
            quantity += amount;
        }

        public void decreaseQuantity(int amount) {
            quantity -= amount;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int argPrice) {
            price = argPrice;
        }

        public String toString() {
            return "Product(name): " + getProduct().getName() + "  Quantity: " + getQuantity() + "  Price: " + getPrice() + "HUF";
        }
    }
}
