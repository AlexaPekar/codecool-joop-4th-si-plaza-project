package com.codecool.plaza.api;

import java.util.List;

public class PlazaImpl implements Plaza {

    private List<Shop> shops;
    private boolean isOpen;

    public PlazaImpl() {

    }


    public List<Shop> getShops() throws PlazaIsClosedException {
        return shops;
    }

    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {

    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {

    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        return null;
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
}
