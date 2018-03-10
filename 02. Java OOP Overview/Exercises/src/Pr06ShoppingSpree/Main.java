package Pr06ShoppingSpree;

import Pr06ShoppingSpree.constants.Constants;
import Pr06ShoppingSpree.exceptions.EmptyNameException;
import Pr06ShoppingSpree.exceptions.NegativeMoneyException;
import Pr06ShoppingSpree.model.Person;
import Pr06ShoppingSpree.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, Person> persons = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] people = reader.readLine().split(Constants.DATA_DELIMITERS);
            for (int i = 0; i < people.length; i += 2) {
                persons.put(people[i], new Person(people[i].trim(), Double.parseDouble(people[i + 1].trim()), new ArrayList<>()));
            }

            String[] prod = reader.readLine().split(Constants.DATA_DELIMITERS);
            for (int i = 0; i < prod.length; i += 2) {
                products.put(prod[i], new Product(prod[i].trim(), Double.parseDouble(prod[i + 1].trim())));
            }

            String purchase;
            while (!Constants.END.equalsIgnoreCase(purchase = reader.readLine().trim())) {
                String[] tokens = purchase.split(Constants.ORDERS_DELIMITER);
                String personName = tokens[0];
                String productName = tokens[1];

                if (persons.get(personName).buyProduct(products.get(productName))) {
                    System.out.printf(Constants.PRODUCT_BOUGHT, personName, productName);
                } else {
                    System.out.printf(Constants.CANT_AFFORD_PRODUCT, personName, productName);
                }
            }

            persons.values().forEach(person -> System.out.println(person.getInfo()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NegativeMoneyException e) {
            System.out.println(Constants.MONEY_CANNOT_BE_NEGATIVE);
        } catch (EmptyNameException e) {
            System.out.println(Constants.NAME_CANNOT_BE_EMPTY);
        }
    }
}
