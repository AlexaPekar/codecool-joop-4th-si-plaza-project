package com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {

    private List<Shop> shops;
    private boolean isOpen;

    public PlazaImpl() {
        shops = new ArrayList<Shop>();
        isOpen = false;
    }


    public List<Shop> getShops() throws PlazaIsClosedException {
        if (isOpen()) {
            return shops;
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }

    }

    public void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException {
        if (isOpen()) {
            if (hasShop(shop) == false) {
                shops.add(shop);
            } else {
                throw new ShopAlreadyExistsException("There is already a shop like this!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }

    public void removeShop(Shop shop) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()) {
            if (hasShop(shop)) {
                shops.remove(shop);
            } else {
                throw new NoSuchShopException("There is no shop like this!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }

    public Shop findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException {
        if (isOpen()) {
            for (Shop s : shops) {
                if (s.getName().equals(name)) {
                    return s;
                } else {
                    throw new NoSuchShopException("There is no shop like this!");
                }
            }
        }
        throw new PlazaIsClosedException("The plaza is closed!");
    }

    public boolean hasShop(Shop shop) {
        for (Shop s : shops) {
            if (s.getName().equals(shop.getName())) {
                return true;
            }
        }
        return false;
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
