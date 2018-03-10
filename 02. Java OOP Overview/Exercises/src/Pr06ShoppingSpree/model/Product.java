package Pr06ShoppingSpree.model;

import Pr06ShoppingSpree.constants.Constants;
import Pr06ShoppingSpree.exceptions.EmptyNameException;
import Pr06ShoppingSpree.exceptions.NegativeMoneyException;

public class Product {

    private String name;
    private double cost;

    public Product(String name, double cost) throws EmptyNameException, NegativeMoneyException {
        this.setName(name);
        this.setCost(cost);
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

    public double getCost() {
        return this.cost;
    }

    private void setCost(double cost) throws NegativeMoneyException {
        if (cost < Constants.MIN_MONEY_ALLOWED) {
            throw new NegativeMoneyException();
        }

        this.cost = cost;
    }
}
