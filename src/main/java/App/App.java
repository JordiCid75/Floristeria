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
                        "2. Print stock \n" +
                        "3. Remove product \n" +
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
                        flowerShop.showCatalog();
                        break;
                    case 3:
                        flowerShop.removeProductStock();
                        break;
                    case 4:
                        flowerShop.showCatalogWithQuantities();
                        break;
                    case 5:
                        flowerShop.printTotalValue();
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        System.out.println("This is not a valid option");
                }
            } catch (NoSuchElementException e) {
                System.out.println("No such element");
            } catch (ProductNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (!quit);
        flowerShop.finalize();
    }
}
