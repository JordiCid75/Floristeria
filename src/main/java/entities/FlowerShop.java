package entities;

import exceptions.ProductNotFoundException;

import java.util.*;

public class FlowerShop {
    private String name;
    private static FlowerShop instance;
    private Stock stock;
    private TicketHistory ticketHistory;

    private FlowerShop(String name) {
        this.name = name;
        this.stock = new Stock();
        this.ticketHistory = new TicketHistory();
    }

    public static FlowerShop getInstance() {
        if (instance == null) {
            String name = Reader.askString("Introduce the name of the flower shop");
            instance = new FlowerShop(name);
            instance.initializeStock();
        }
        return instance;
    }

    public void showCatalog() {
        List<Product> productList = new ArrayList<>(stock.getProductList().keySet());
        productList.sort(Comparator.comparingInt(Product::getId));
        productList.forEach(System.out::println);
    }

    public void showCatalogWithQuantities() {
        stock.getProductList().forEach((k, v) -> {
            System.out.println(k + "Quantity: " + v + "\n");
        });
    }

    private void addNewProductStock() {
        String name = Reader.askString("Introduce its name");
        float price = Reader.askFloat("Introduce its price per unit");
        String type = Reader.askString("Introduce its type (Flower, Tree, Decoration)").toUpperCase();
        Product newProduct = null;
        switch (type) {
            case "FLOWER":
                String colour = Reader.askString("Introduce its colour");
                newProduct = new Flower(name, price, colour);
                break;
            case "TREE":
                float height = Reader.askFloat("Introduce its height");
                newProduct = new Tree(name, price, height);
                break;
            case "DECORATION":
                String materialString = Reader.askString("Introduce its material").toUpperCase();
                Material material = Enum.valueOf(Material.class, materialString);
                newProduct = new Decoration(name, price, material);
                break;
            default:
                System.out.println("This is not a valid type");
        }
        int quantity = Reader.askInt("Introduce its quantity");
        stock.addProduct(newProduct, quantity);
    }

    public void addProductStock() {
        String name = Reader.askString("Introduce its name");
        float price = Reader.askFloat("Introduce its price per unit");
        String type = Reader.askString("Introduce its type (Flower, Tree, Decoration)").toUpperCase();
        Product newProduct = null;
        switch (type) {
            case "FLOWER":
                String colour = Reader.askString("Introduce its colour");
                newProduct = new Flower(name, price, colour);
                break;
            case "TREE":
                float height = Reader.askFloat("Introduce its height");
                newProduct = new Tree(name, price, height);
                break;
            case "DECORATION":
                String materialString = Reader.askString("Introduce its material").toUpperCase();
                Material material = Enum.valueOf(Material.class, materialString);
                newProduct = new Decoration(name, price, material);
                break;
            default:
                System.out.println("This is not a valid type");
        }
        int quantity = Reader.askInt("Introduce its quantity");
        Command addCommand = new AddProductInStockCommand(stock, newProduct, quantity);
        stock.executeCommand(addCommand);
    }

    private void incrementProductStock() throws ProductNotFoundException {
        showCatalog();
        int id = Reader.askInt("Introduce id of product you want to increase its quantity");
        Product product = stock.findProductById(id);
        int quantity = Reader.askInt("Introduce the quantity you want to add");
        stock.increaseProductQuantity(product, quantity);
    }

    public void incrementProductStock2() throws ProductNotFoundException {
        showCatalog();
        int id = Reader.askInt("Introduce id of product you want to increase its quantity");
        Product product = stock.findProductById(id);
        int quantity = Reader.askInt("Introduce the quantity you want to add");
        Command increaseCommand = new IncreaseProductQuantityCommand(stock, product, quantity);
        stock.executeCommand(increaseCommand);
    }

    public void addOrIncrementProductStock() throws ProductNotFoundException {
        int option = Reader.askInt("Choose an option: \n" +
                "1. Increment quantity of already existing product \n" +
                "2. Add new product to stock \n");
        switch (option) {
            case 1:
                incrementProductStock();
                break;
            case 2:
                addNewProductStock();
                break;
            default:
                System.out.println("this is not a valid option");
        }
    }

    public void removeProductStock() throws ProductNotFoundException {
        showCatalog();
        int id = Reader.askInt("Introduce id of product you want to remove");
        Product product = stock.findProductById(id);
        stock.removeProduct(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printTotalValue() {
        System.out.println(stock.getTotalValue() + "€");
    }

    public void initializeStock() {
        stock.addProduct(new Flower("Daisy", 0.05F, "White"), 50);
//        stock.addProduct(new Flower("Rose", 0.02F, "Red"), 85);
//        stock.addProduct(new Flower("Sunflower", 0.01F, "Yellow"), 100);
//        stock.addProduct(new Tree("Aleppo pine", 50, 20), 30);
//        stock.addProduct(new Tree("Pedunculate oak", 60, 40), 15);
//        stock.addProduct(new Tree("European beech", 80, 50), 10);
//        stock.addProduct(new Decoration("Table", 100, Material.WOOD), 30);
//        stock.addProduct(new Decoration("Statue", 60, Material.PLASTIC), 70);
//        stock.addProduct(new Decoration("Painting", 40, Material.WOOD), 50);
    }

    public void addNewTicket() throws ProductNotFoundException {
        Ticket newTicket = new Ticket();
        showCatalogWithQuantities();
        int id = Reader.askInt("Introduce id of product you want to buy");
        Product product = stock.findProductById(id);
        int qtyBuy = Reader.askInt("Introduce amount");
        int qtyNow = stock.getProductQuantity(product);
        if (qtyBuy > qtyNow) {
            System.out.println("Only " + qtyNow + " available.");
        } else {
            stock.decreaseProductQuantity(product, qtyBuy);
        }
        newTicket.addProductInTicket(product, qtyBuy);
        newTicket.printTicket();
    }

    public Stock getStock() {
        return stock;
    }
}
