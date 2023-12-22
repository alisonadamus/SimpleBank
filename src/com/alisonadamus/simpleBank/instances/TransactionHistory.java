package com.alisonadamus.simpleBank.instances;

import com.alisonadamus.simpleBank.Display;
import com.alisonadamus.simpleBank.instances.Person.Account.BankCard;
import java.time.LocalDateTime;

public class TransactionHistory {

    public Person person;
    public BankCard bankCard;
    public Good good;
    public int quantity;
    public int sum;
    public LocalDateTime timeOfPurchase;
    public int remainder;

    public TransactionHistory(Person person, BankCard bankCard, Good good, int quantity, int sum,
        int remainder) {
        this.person = person;
        this.bankCard = bankCard;
        this.good = good;
        this.quantity = quantity;
        this.sum = sum;
        this.timeOfPurchase = LocalDateTime.now();
        this.remainder = remainder;
    }

    @Override
    public String toString() {
        return person + "[" + bankCard + "]" + " - купив/ла " + good.getName() + "(" + quantity
            + "штуки)" + " на суму " + sum + Display.currency + ". " + timeOfPurchase + "\n"
            + " Залишилось на балансі аккаунту:v" + remainder + Display.currency;
    }
}
