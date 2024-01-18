package entities;

import Persistence.*;
import Factory.*;
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


	public void showCatalogWithQuantities()
	{
		List<Product> productList = new ArrayList<>(stock.getProductList().keySet());
		productList.sort(Comparator.comparingInt(Product::getId));

		productList.forEach(product -> System.out.println(product + "Quantity: " + stock.getProductList().get(product) + "\n"));

	}
    private void addNewProductStock() throws IllegalArgumentException {

		String productTypeString = Reader.askString("Introduce its type (Flower, Tree, Decoration)").toUpperCase();


		ProductType productType = Enum.valueOf(ProductType.class, productTypeString);

		String name = Reader.askString("Introduce its name");
		float price = Reader.askFloat("Introduce its price per unit");


		ProductFactory productFactory = new ProductFactory();
		Product product = null;

		switch(productType)
		{
			case FLOWER:

				String colour = Reader.askString("Introduce its colour");

				product = productFactory.create(name, price, colour);

				break;

			case TREE:

				float height = Reader.askFloat("Introduce its height");

				product = productFactory.create(name, price, height);

				break;

			case DECORATION:

				String materialString = Reader.askString("Introduce its material").toUpperCase();
				Material material = Enum.valueOf(Material.class, materialString);

				product = productFactory.create(name, price, material);

				break;

		}

    public void showCatalogWithQuantities() {
        List<Product> productList = new ArrayList<>(stock.getProductList().keySet());
        productList.sort(Comparator.comparingInt(Product::getId));
        productList.forEach(product -> System.out.println(product + "Quantity: " + stock.getProductList().get(product) + "\n"));
    }

    private void addNewProductStock() {
        String productTypeString = Reader.askString("Introduce its type (Flower, Tree, Decoration)").toUpperCase();
        ProductType productType = Enum.valueOf(ProductType.class, productTypeString);
        String name = Reader.askString("Introduce its name");
        float price = Reader.askFloat("Introduce its price per unit");
        ProductFactory productFactory = new ProductFactory();
        Product product = null;
        switch (productType) {
            case FLOWER:
                String colour = Reader.askString("Introduce its colour");
                product = productFactory.create(name, price, colour);
                break;
            case TREE:
                float height = Reader.askFloat("Introduce its height");
                product = productFactory.create(name, price, height);
                break;
            case DECORATION:
                String materialString = Reader.askString("Introduce its material").toUpperCase();
                Material material = Enum.valueOf(Material.class, materialString);
                product = productFactory.create(name, price, material);
                break;
            default:
                System.out.println("This is not a valid type");
        }
        int quantity = Reader.askInt("Introduce its quantity");
        Command addCommand = new AddProductInStockCommand(stock, product, quantity);
        stock.executeCommand(addCommand);
    }

    private void incrementProductStock() throws ProductNotFoundException {
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
        System.out.printf("%.2f€\n", stock.getTotalValue());
    }

    public void initializeStock() {
        StockBD stBD = new StockBD();
        stBD.readListStockBD(stock);
        TicketHistoryBD thBD = new TicketHistoryBD();
        thBD.readListTicketHistoryBD(ticketHistory, stock);
//		stock.addProduct(new Flower("Daisy", 0.05F, "White"), 50);
//		stock.addProduct(new Flower("Rose", 0.02F, "Red"), 85);
//		stock.addProduct(new Flower("Sunflower", 0.01F, "Yellow"), 100);
//
//		stock.addProduct(new Tree("Aleppo pine", 50, 20), 30);
//		stock.addProduct(new Tree("Pedunculate oak", 60, 40), 15);
//		stock.addProduct(new Tree("European beech", 80, 50), 10);
//
//		stock.addProduct(new Decoration("Table", 100, Material.WOOD), 30);
//		stock.addProduct(new Decoration("Statue", 60, Material.PLASTIC), 70);
//		stock.addProduct(new Decoration("Painting", 40, Material.WOOD), 50);
    }
 public void showTotalSells(){
     System.out.println(ticketHistory.totalSells());
 }
    public void addNewTicket() throws ProductNotFoundException {
        ListOfCommands listOfCommands = new ListOfCommands();
        Product product = null;
        Ticket newTicket = null;
        newTicket = new Ticket();
        boolean salir = false;
        do {

            int option = Reader.askInt("Choose an option: \n" +
                    "1. Add new product to ticket \n" +
                    "2. Print ticket \n" +
                    "3. Cancel ticket\n");
            switch (option) {
                case 1:
                    int qtyBuy = 0;
                    showCatalogWithQuantities();
                    int id = Reader.askInt("Introduce id of product you want to buy");
                    product = stock.findProductById(id);
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
                    break;
                case 2:
                    if (newTicket != null && newTicket.getProductsInTicket().size()>0) {
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

	public void printTotalGains()
	{
		System.out.printf("%.2f€\n", ticketHistory.totalSells());
	}

	public void printOldPurchases()
	{
		ticketHistory.printAllTickets();
	}

    public Stock getStock() {
        return stock;
    }

    public void printTickets() {
        ticketHistory.printAllTickets();
    }

    public void saveInfoToBD() {
        saveNameToBD();
        saveStockToBD();
        saveTicketHistoryToBD();
    }

    private void saveTicketHistoryToBD() {
        TicketHistoryBD thBD = new TicketHistoryBD();
        thBD.write(ticketHistory);
    }

    private void saveStockToBD() {
        StockBD stBD = new StockBD();
        stBD.write(stock);
    }
}
