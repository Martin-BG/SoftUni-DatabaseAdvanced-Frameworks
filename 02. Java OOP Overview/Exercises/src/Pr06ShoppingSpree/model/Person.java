package Pr06ShoppingSpree.model;

import Pr06ShoppingSpree.constants.Constants;
import Pr06ShoppingSpree.exceptions.EmptyNameException;
import Pr06ShoppingSpree.exceptions.NegativeMoneyException;

import java.util.List;
import java.util.stream.Collectors;

public class Person {

    private String name;
    private double money;
    private List<Product> bag;

    public Person(String name, double money, List<Product> bag) throws EmptyNameException, NegativeMoneyException {
        this.setName(name);
        this.setMoney(money);
        this.bag = bag;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) throws EmptyNameException {
        if (name == null || name.isEmpty()) {
            throw new EmptyNameException();
        }

        this.name = name;
    }

    public double getMoney() {
        return this.money;
    }

    private void setMoney(double money) throws NegativeMoneyException {
        if (money < Constants.MIN_MONEY_ALLOWED) {
            throw new NegativeMoneyException();
        }

        this.money = money;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder(this.getName()).append(Constants.NAME_PRODUCTS_SEPARATOR);
        if (this.bag.isEmpty()) {
            info.append(Constants.NOTHING_BOUGHT);
        } else {
            info.append(this.bag.stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(Constants.PRODUCTS_DELIMITER)));
        }
        return info.toString();
    }

    public boolean buyProduct(Product product) {
        if (this.getMoney() >= product.getCost()) {
            this.bag.add(product);
            this.money -= product.getCost();
            return true;
        }

        return false;
    }
}
