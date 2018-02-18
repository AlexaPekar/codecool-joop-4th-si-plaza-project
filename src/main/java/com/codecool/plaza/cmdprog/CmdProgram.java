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
            System.out.println("\n\nThere is no plaza created yet! Press...\n" +
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
            System.out.println("\n\n" + myPlaza + " Press...\n" +
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
        if (myPlaza.isOpen()) {
            try {
                if (myPlaza.getShops().size() > 0) {
                    System.out.println("These shops are in the " + myPlaza.getName() + " plaza:");
                    try {
                        for (Shop shop : myPlaza.getShops()) {
                            System.out.println(shop.getName());
                        }
                    } catch (PlazaIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("The plaza is empty!");
                }
            } catch (PlazaIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("The plaza is closed!");
        }
    }

    public void handleAddShop() {
        if (myPlaza.isOpen()) {
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
                System.out.println("Your shop is added.");
            } catch (PlazaIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("The plaza is closed!");
        }
    }

    public void handleRemoveShop() {
        if (myPlaza.isOpen()) {
            System.out.println("Please, enter the name of the shop you would like to remove!");
            String name = scanner.nextLine();
            try {
                try {
                    myPlaza.removeShop(myPlaza.findShopByName(name));
                } catch (NoSuchShopException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("This shop is removed.");
            } catch (PlazaIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("The plaza is closed!");
        }
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
        if (myPlaza.isOpen()) {
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
        } else {
            System.out.println("The plaza is closed!");
        }
    }

    public void handleEnterShop() {
        boolean inShopMenu = true;
        while (inShopMenu) {
            System.out.println("\n\nWelcome to the " + myShop.getName() + "! Press...\n" +
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
                    break;
                case "5":
                    handleCloseShop();
                    break;
                case "6":
                    handleAddNewProduct();
                    break;
                case "7":
                    handleAddProduct();
                    break;
                case "8":
                    handleBuyProduct();
                    break;
                case "9":
                    handleCheckPrice();
                    break;
                case "0":
                    System.out.println("See you soon!");
                    inShopMenu = false;
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    public void handleListProducts() {
        if (myShop.isOpen()) {
            try {
                if (myShop.getProducts().size() > 01
                        ) {
                    System.out.println("These products are in the shop:");
                    try {
                        for (Product product : myShop.getProducts()) {
                            System.out.println(product);
                        }
                    } catch (ShopIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("The shop is empty!");
                }
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("This shop is closed!");
        }
    }

    public void handleFindProducts() {
        if (myShop.isOpen()) {
            System.out.println("Please, enter the name of the product, you would like to find!");
            String name = scanner.nextLine();
            Product product = null;
            try {
                try {
                    product = myShop.findByName(name);
                } catch (NoSuchProductException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Your product is here:");
                System.out.println(product);
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("This shop is closed!");
        }
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

    public void handleAddNewProduct() {
        if (myShop.isOpen()) {
            Product product = null;
            System.out.println("Please, enter the type of the product!(makeup/clothing)");
            String type = scanner.nextLine();
            System.out.println("Please, enter the barcode of the product!");
            long barcode = scanner.nextLong();
            System.out.println("Please, enter the name of the product!");
            String name = scanner.nextLine();
            System.out.println("Please, enter the manufacturer of the product!");
            String manufacturer = scanner.nextLine();
            switch (type) {
                case "makeup":
                    System.out.println("Please, enter the amount of the product in grams!");
                    int amount = scanner.nextInt();
                    System.out.println("Is the product cruelty-free?(true/false)");
                    boolean crueltyFree = scanner.nextBoolean();
                    product = new MakeupProduct(barcode, name, manufacturer, amount, crueltyFree);
                case "clothing":
                    System.out.println("Please, enter the material of the product!");
                    String material = scanner.nextLine();
                    System.out.println("Please, enter the colour of the product!");
                    String colour = scanner.nextLine();
                    product = new ClothingProduct(barcode, name, manufacturer, material, colour);
                default:
                    System.out.println("Wrong input!");
            }
            System.out.println("Please, enter the quantity of the product!");
            int quantity = scanner.nextInt();
            System.out.println("Please, enter the price of the product!");
            float price = scanner.nextFloat();
            try {
                try {
                    myShop.addNewProduct(product, quantity, price);
                } catch (ProductAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Your product is added.");
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("This shop is closed!");
        }
    }

    public void handleAddProduct() {
        if (myShop.isOpen()) {
            System.out.println("Please, enter the barcode of the product!");
            long barcode = scanner.nextLong();
            System.out.println("Please, enter the quantity you would like to add!");
            int quantity = scanner.nextInt();
            try {
                try {
                    myShop.addProduct(barcode, quantity);
                } catch (NoSuchProductException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("The product(s) is/are added.");
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("This shop is closed!");
        }
    }

    public void handleBuyProduct() {
        if (myShop.isOpen()) {
            System.out.println("Please, enter the barcode of the product, you would like to buy!");
            long barcode = scanner.nextLong();
            System.out.println("Please enter the quantity of the product!");
            int quantity = scanner.nextInt();
            if (quantity > 1) {
                List<Product> products = null;
                try {
                    try {
                        try {
                            products = myShop.buyProducts(barcode, quantity);
                        } catch (NoSuchProductException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (OutOfStockException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (ShopIsClosedException e) {
                    System.out.println(e.getMessage());
                }
                assert products != null;
                for (int i = 0; i < products.size(); i++) {
                    cart.add(products.get(i));
                    try {
                        try {
                            prices.add(myShop.getPrice(barcode));
                        } catch (NoSuchProductException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (ShopIsClosedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                Product product = null;
                try {
                    try {
                        try {
                            product = myShop.buyProduct(barcode);
                        } catch (NoSuchProductException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch (OutOfStockException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (ShopIsClosedException e) {
                    System.out.println(e.getMessage());
                }
                cart.add(product);
                try {
                    try {
                        prices.add(myShop.getPrice(barcode));
                    } catch (NoSuchProductException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (ShopIsClosedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("You bought the product(s).");
            }
        } else {
            System.out.println("This shop is closed!");
        }
    }

    public void handleCheckPrice() {
        if (myShop.isOpen()) {
            System.out.println("Please, enter the barcode of the product!");
            long barcode = scanner.nextLong();
            System.out.print("The price of the product is ");
            try {
                try {
                    System.out.println(myShop.getPrice(barcode) + " HUF");
                } catch (NoSuchProductException e) {
                    System.out.println(e.getMessage());
                }
            } catch (ShopIsClosedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("This shop is closed!");
        }
    }
}
