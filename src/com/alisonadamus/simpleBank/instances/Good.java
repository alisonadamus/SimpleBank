package com.alisonadamus.simpleBank.instances;

public class Good {

    public String name;
    public int price;

    public Good(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " Ціна: " + price;
    }
}
