package App;

import java.util.*;

import entities.FlowerShop;
import entities.Reader;
import exceptions.ProductNotFoundException;

public class App {
    public static void main(String[] args) {
        boolean quit = false;
        FlowerShop flowerShop = FlowerShop.getInstance();
        do {
        	flowerShop.saveInfoToBD();
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
                        "8. Show total gains \n" +
                        "9. Show list of old products \n");
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
                        flowerShop.printOldPurchases();
                        break;
                    case 8:
                        flowerShop.printTotalGains();
                        break;
                    case 9:
                        flowerShop.showOldCatalogWithQuantities();
                        break;
                    default:
                        System.out.println("This is not a valid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("This is not a valid character");
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            } catch(IllegalArgumentException e)
            {
                System.out.println("This type of product does not exist");
            }
        } while (!quit);
        flowerShop.saveInfoToBD();
    }
}
