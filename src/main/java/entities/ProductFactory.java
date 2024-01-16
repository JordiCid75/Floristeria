package entities;

import Factory.*;

public class ProductFactory {


    public static Product create(String name, float price, String colour)
    {
       return new Flower(name, price, colour);

    }

    public static Product create(String name, float price, float height)
    {

        return new Tree(name, price, height);


    }

    public static Product create(String name, float price, Material material)
    {

        return new Decoration(name, price, material);


    }




}
