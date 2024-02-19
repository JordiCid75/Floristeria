package entities;

import Persistence.*;
import Factory.*;
import exceptions.ProductNotFoundException;

import java.util.*;

public class FlowerShop {
    private String name;
    private static FlowerShop instance;
    private Stock stock;
    private Stock oldStock;
    private TicketHistory ticketHistory;

    private FlowerShop(String name) {
        this.name = name;
        this.stock = new Stock();
        this.ticketHistory = new TicketHistory();
        this.oldStock = new Stock();
    }

    public static FlowerShop getInstance() {
        if (instance == null) {
            String name;
            name = getNameFromBD();
            if (name == null) {
                name = Reader.askString("Introduce the name of the flower shop");
            }
            instance = new FlowerShop(name);
            saveNameToBD();
            instance.initializeStock();
        }
        return instance;
    }

    private static void saveNameToBD() {
        try {
            FlowerShopBD fsBD = new FlowerShopBD();
            fsBD.write(instance);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private static String getNameFromBD() {
        try {
            FlowerShopBD fsBD = new FlowerShopBD();
            return fsBD.getShopName();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void showCatalog() {
        List<Product> productList = new ArrayList<>(stock.getProductList().keySet());
        productList.sort(Comparator.comparingInt(Product::getId));
        productList.forEach(System.out::println);
    }

    public void showCatalogWithQuantities() {
        List<Product> productList = new ArrayList<>(stock.getProductList().keySet());
        productList.sort(Comparator.comparingInt(Product::getId));
        productList.forEach(
                (product) -> System.out.println(product + "Quantity: " + stock.getProductList().get(product) + "\n"));
    }

    public void showOldCatalogWithQuantities() {
        List<Product> productList = new ArrayList<>(oldStock.getProductList().keySet());
        productList.sort(Comparator.comparingInt(Product::getId));
        productList.forEach(
                (product) -> System.out.println(product + "Quantity: " + oldStock.getProductList().get(product) + "\n"));
    }

    private void addNewProductStock() {
        String productTypeString = Reader.askString("Introduce its type (Flower, Tree, Decoration)").toUpperCase();
        ProductType productType = Enum.valueOf(ProductType.class, productTypeString);
        String name = Reader.askString("Introduce its name");
        float price = Reader.askFloat("Introduce its price per unit");
        ProductFactory productFactory = new ProductFactory();
        Product product = null;
        switch (productType) {
            case FLOWER: {
                String colour = Reader.askString("Introduce its colour");
                product = productFactory.create(name, price, colour);
                break;
            }
            case TREE: {
                float height = Reader.askFloat("Introduce its height");
                product = productFactory.create(name, price, height);
                break;
            }
            case DECORATION: {
                String materialString = Reader.askString("Introduce its material (WOOD,PLASTIC)").toUpperCase();
                Material material = Enum.valueOf(Material.class, materialString);
                product = productFactory.create(name, price, material);
                break;
            }
            default: {
                System.out.println("This is not a valid type");
            }
        }
        int quantity = Reader.askInt("Introduce its quantity");
        Command addCommand = new AddProductInStockCommand(stock, product, quantity);
        stock.executeCommand(addCommand);
    }

    private void incrementProductStock() throws ProductNotFoundException {
        showCatalogWithQuantities();
        int id = Reader.askInt("Introduce id of product you want to increase its quantity");
        Product product = stock.findProductById(id);
        int quantity = Reader.askInt("Introduce the quantity you want to add");
        Command increaseCommand = new IncreaseProductQuantityCommand(stock, product, quantity);
        stock.executeCommand(increaseCommand);
    }

    public void addOrIncrementProductStock() throws ProductNotFoundException {
        int option = Reader.askInt("Choose an option: \n" + "1. Increment quantity of already existing product \n"
                + "2. Add new product to stock \n");
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
        showCatalogWithQuantities();
        int id = Reader.askInt("Introduce id of product you want to remove");
        Product product = stock.findProductById(id);
        if (ticketHistory.productInTickets(product)) {
            // afegir a la llista de deprecated
            oldStock.addProduct(product, 0);
        }
        stock.removeProduct(product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printTotalValue() {
        System.out.printf("%.2f€\n", stock.getTotalValue());
    }

    public void initializeStock() {
        StockBD stBD = new StockBD();
        OldStockBD oldStockBD = new OldStockBD();
        stBD.readListStockBD(stock);
        oldStockBD.readListStockBD(oldStock);
        TicketHistoryBD thBD = new TicketHistoryBD();

        thBD.readListTicketHistoryBD(ticketHistory, stock, oldStock);
    }

    public void showTotalSells() {
        System.out.println(ticketHistory.totalSells());
    }

    public void addNewTicket() throws ProductNotFoundException {
        ListOfCommands listOfCommands = new ListOfCommands();
        Product product = null;
        Ticket newTicket = null;
        newTicket = new Ticket();
        boolean salir = false;
        do {
            int option = Reader.askInt("Choose an option: \n" + "1. Add new product to ticket \n" + "2. Print ticket \n"
                    + "3. Cancel ticket\n");
            switch (option) {
                case 1:
                    int qtyBuy = 0;
                    showCatalogWithQuantities();
                    int id = Reader.askInt("Introduce id of product you want to buy");
                    product = stock.findProductById(id);
                    if (stock.getProductQuantity(product) > 0) {
                        qtyBuy = Reader.askInt("Introduce amount");
                        int qtyNow = stock.getProductQuantity(product);
                        if (qtyBuy > qtyNow) {
                            System.out.println("Only " + qtyNow + " available.");
                        } else {
                            Command decreaseCommand = new DecreaseProductQuantityCommand(stock, product, qtyBuy);
                            Command addInTicket = new AddProductInTicketCommand(newTicket, product, qtyBuy);
                            Command addInStockCommand = new IncreaseProductQuantityCommand(stock, product, qtyBuy);
                            decreaseCommand.execute();
                            addInTicket.execute();
                            listOfCommands.addCommand(addInStockCommand);
                            newTicket.printTicket();
                        }
                    } else {
                        System.out.println("Only " + stock.getProductQuantity(product) + " available.");
                    }
                    break;
                case 2:
                    if (newTicket != null && newTicket.getProductsInTicket().size() > 0) {
                        newTicket.printTicket();
                        ticketHistory.addTicketToHistory(newTicket, newTicket.getTotalPrice());
                        salir = true;
                    } else {
                        System.out.println("ticket is empty");
                    }
                    break;
                case 3:
                    listOfCommands.executeCommands();
                    newTicket.cancelTicket();
                    newTicket = null;
                    salir = true;
                    break;
                default:
                    System.out.println("this is not a valid option");
            }
        } while (!salir);
    }

    public void printTotalGains() {
        System.out.printf("%.2f€\n", ticketHistory.totalSells());
    }

    public void printOldPurchases() {
        ticketHistory.printAllTickets();
    }

    public Stock getStock() {
        return stock;
    }

    public void printTickets() {
        ticketHistory.printAllTickets();
    }

    private void saveTicketHistoryToBD() {
        TicketHistoryBD thBD = new TicketHistoryBD();
        thBD.write(ticketHistory);
    }

    private void saveStockToBD() {
        StockBD stBD = new StockBD();
        stBD.write(stock);
    }

    private void saveOldStockToBD() {
        OldStockBD stBD = new OldStockBD();
        stBD.write(oldStock);
    }

    public void saveInfoToBD() {
        saveNameToBD();
        saveStockToBD();
        saveOldStockToBD();
        saveTicketHistoryToBD();
    }
}
