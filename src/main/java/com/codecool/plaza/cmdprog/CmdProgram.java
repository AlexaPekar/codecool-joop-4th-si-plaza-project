package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CmdProgram {

    private List<Product> cart;
    private List<Float> prices;
    Scanner scanner = new Scanner(System.in);
    PlazaImpl myPlaza;
    ShopImpl myShop;

    public CmdProgram(String[] args) {
        cart = new ArrayList<Product>();
        prices = new ArrayList<Float>();
    }

    public void run() {
        while (myPlaza == null) {
            System.out.println("There are no plaza created yet! Press\n" +
                    "1) to create a new plaza.\n" +
                    "0) to exit.");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Please, enter the plaza's name:");
                    String name = scanner.nextLine();
                    myPlaza = new PlazaImpl(name);
                    break;
                case "0":
                    System.out.println("See you soon!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }

        while (true) {
            System.out.println(myPlaza + " Press...\n" +
                    "1) to list all shops.\n" +
                    "2) to add a new shop.\n" +
                    "3) to remove an existing shop.\n" +
                    "4) enter a shop by name.\n" +
                    "5) to open the plaza.\n" +
                    "6) to close the plaza.\n" +
                    "7) to check if the plaza is open or not.\n" +
                    "0) leave plaza.");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    handleListShops();
                    break;
                case "2":
                    handleAddShop();
                    break;
                case "3":
                    handleRemoveShop();
                    break;
                case "4":
                    enterShop();
                    if (myShop != null) {
                        handleEnterShop();
                    }
                    break;
                case "5":
                    handleOpen();
                    break;
                case "6":
                    handleClose();
                    break;
                case "7":
                    handleCheckPlazaIsOpen();
                    break;
                case "0":
                    handleExit();
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public void handleListShops() {
        System.out.println("These shops are in the " + myPlaza.getName() + " plaza:");
        try {
            for (Shop shop : myPlaza.getShops()) {
                System.out.println(shop.getName());
            }
        } catch (PlazaIsClosedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleAddShop() {
        System.out.println("Please, enter the name of the new shop!");
        String name = scanner.nextLine();
        System.out.println("Please, enter the owner of the new shop!");
        String owner = scanner.nextLine();
        ShopImpl newShop = new ShopImpl(name, owner);
        try {
            try {
                myPlaza.addShop(newShop);
            } catch (ShopAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        } catch (PlazaIsClosedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Your shop is added.");
    }

    public void handleRemoveShop() {
        System.out.println("Please, enter the name of the shop you would like to remove!");
        String name = scanner.nextLine();
        try {
            try {
                myPlaza.removeShop(myPlaza.findShopByName(name));
            } catch (NoSuchShopException e) {
                System.out.println(e.getMessage());
            }
        } catch (PlazaIsClosedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("This shop is removed.");
    }

    public void handleOpen() {
        myPlaza.open();
        System.out.println("The plaza is opened.");
    }

    public void handleClose() {
        myPlaza.close();
        System.out.println("The plaza is closed.");
    }

    public void handleExit() {
        System.out.println("See you soon!");
        System.exit(0);
    }

    public void handleCheckPlazaIsOpen() {
        if (myPlaza.isOpen()) {
            System.out.println("The plaza is open.");
        } else {
            System.out.println("The plaza is closed.");
        }
    }

    public void enterShop() {
        System.out.println("Please, enter the name of the shop you would like to enter!");
        String name = scanner.nextLine();
        try {
            try {
                myShop = (ShopImpl) myPlaza.findShopByName(name);
            } catch (NoSuchShopException e) {
                System.out.println(e.getMessage());
            }
        } catch (PlazaIsClosedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleEnterShop() {
        boolean shopMenu = true;
        while (shopMenu) {
            System.out.println("Welcome to the " + myShop.getName() + "! Press...\n" +
                    "1) to list available products.\n" +
                    "2) to find products by name.\n" +
                    "3) to display the shop's owner.\n" +
                    "4) to open the shop.\n" +
                    "5) to close the shop.\n" +
                    "6) to add new product to the shop.\n" +
                    "7) to add existing products to the shop.\n" +
                    "8) to buy a product by barcode.\n" +
                    "9) check price by barcode.\n" +
                    "0) go back to plaza.");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    handleListProducts();
                    break;
                case "2":
                    handleFindProducts();
                    break;
                case "3":
                    handleDisplayOwner();
                    break;
                case "4":
                    handleOpenShop();
                case "5":
                    handleCloseShop();
                case "6":

                case "7":

                case "8":

                case "9":

                case "0":
                    System.out.println("See you soon!");
                    shopMenu = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public void handleListProducts() {
        System.out.println("These products are in the shop:");
        try {
            for (Product product : myShop.getProducts()) {
                System.out.println(product);
            }
        } catch (ShopIsClosedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleFindProducts() {
        System.out.println("Please, enter the name of the product, you would like to find!");
        String name = scanner.nextLine();
        Product product = null;
        try {
            try {
                product = myShop.findByName(name);
            } catch (NoSuchProductException e) {
                System.out.println(e.getMessage());
            }
        } catch (ShopIsClosedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Your product is here:");
        System.out.println(product);
    }

    public void handleDisplayOwner() {
        System.out.println("The owner of this shop is: " + myShop.getOwner());
    }

    public void handleOpenShop() {
        myShop.open();
        System.out.println("The shop is opened.");
    }

    public void handleCloseShop() {
        myShop.close();
        System.out.println("The shop is closed.");
    }

    public void handleAddProduct() {
        Product product;
        System.out.println("Please, enter the type of the product!(MAKEUP/CLOTHING)");
        ProductType type = scanner.nextLine();

    }
}
