package App;

import java.util.ArrayList;
import java.util.List;


public class Stock {

    List<Product> productList;

    public Stock()
    {
        productList = new ArrayList<>();
    }

    public void showCatalog()
    {
        productList.forEach(System.out::println);
    }

    public void addProduct(Product product)
    {

        productList.add(product);

    }

    public void increaseProductQuantity(int id, int quantity)
    {
        productList.get(id-1).quantityByProduct += quantity;

    }

    public void DecreaseProductQuantity(int id, int quantity)
    {
        productList.get(id-1).quantityByProduct -= quantity;

    }

    public void removeProduct(int id)
    {

        productList.remove(id);

    }

    public void printTotalValue()
    {
        float totalValue = (float) productList.stream()
                .mapToDouble(product -> product.calculateTotalPrice())
                .sum();

        System.out.println("Total value is: " + totalValue);
    }


}
