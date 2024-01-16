package entities;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Reader {

    private static Scanner scanner = new Scanner(System.in);

    public static String askString(String message)
    {
        System.out.println(message);

        String input = scanner.nextLine();

        return input;
    }

    public static int askInt(String message) throws InputMismatchException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        int input = scanner.nextInt();

        return input;

    }

    public static float askFloat(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        float input = scanner.nextFloat();

        return input;

    }
}
