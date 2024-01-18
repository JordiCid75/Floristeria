package App;

import java.io.IOException;
import java.util.*;

import Persistence.DataBase;
import entities.FlowerShop;
import entities.Reader;
import exceptions.ProductNotFoundException;

public class App {
    public static void main(String[] args) throws IOException {
        boolean quit = false;
        FlowerShop flowerShop = FlowerShop.getInstance();
        do {
            try {
                int option;
                option = Reader.askInt("0. Quit \n" +
                        "1. Add products to stock \n" +
                        "2. Remove product \n" +
                        "3. Print stock \n" +
                        "4. Print stock with quantities \n" +
                        "5. Print FlowerShop's total value \n" +
                        "6. Create tickets \n" +
                        "7. Show list of old purchases \n" +
                        "8. Show total gains \n");
                switch (option) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        flowerShop.addOrIncrementProductStock();
                        break;
                    case 2:
                        flowerShop.removeProductStock();
                        break;
                    case 3:
                        flowerShop.showCatalog();
                        break;
                    case 4:
                        flowerShop.showCatalogWithQuantities();
                        break;
                    case 5:
                        flowerShop.printTotalValue();
                        break;
                    case 6:
                        flowerShop.addNewTicket();
                        break;
                    case 7:
                        flowerShop.printTickets();
                        break;
                    case 8:
                        flowerShop.showTotalSells();
                        break;
                    default:
                        System.out.println("This is not a valid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("This is not a valid character");
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (!quit);
        flowerShop.saveInfoToBD();
    }
}
