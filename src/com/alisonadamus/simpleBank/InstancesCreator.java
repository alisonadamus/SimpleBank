package com.alisonadamus.simpleBank;

import com.alisonadamus.simpleBank.instances.Good;
import com.alisonadamus.simpleBank.instances.Person;
import java.util.ArrayList;
import java.util.List;

public class InstancesCreator {

    public static void createPersons() {

        Person ivan = new Person("Іванов", "Іван", "Іванович");
        Display.people.add(ivan);
        Person.Account ivanAccount = ivan.new Account("ivan1234", ivan);
        Display.accounts.put(ivan, ivanAccount);
        Person.Account.BankCard ivanCard = ivanAccount.new BankCard(90873423, 11232, ivan);
        ivanCard.setAccountBalance();
        Display.bankCards.put(ivanCard, ivanAccount);

        Person klara = new Person("Іванова", "Клара", "Василівна");
        Display.people.add(klara);
        Display.accounts.put(klara, ivanAccount);
        Person.Account.BankCard klaraCard = ivanAccount.new BankCard(44044223, 20245, klara);
        klaraCard.setAccountBalance();
        Display.bankCards.put(klaraCard, ivanAccount);

        Person vasyl = new Person("Кравченко", "Василь", "Михайлович");
        Display.people.add(vasyl);
        Person.Account vasylAccount = vasyl.new Account("password", vasyl);
        Display.accounts.put(vasyl, vasylAccount);
        Person.Account.BankCard vasylCard = vasylAccount.new BankCard(5465543, 434, vasyl);
        vasylCard.setAccountBalance();
        Display.bankCards.put(vasylCard, vasylAccount);

        Person maria = new Person("Мельник", "Марія", "Степанівна");
        Display.people.add(maria);
        Person.Account mariaAccount = maria.new Account("dcba4321", maria);
        Display.accounts.put(maria, mariaAccount);
        Person.Account.BankCard mariaCard = mariaAccount.new BankCard(3324452, 982, maria);
        mariaCard.setAccountBalance();
        Display.bankCards.put(mariaCard, mariaAccount);

        Person viktoria = new Person("Степанюк", "Вікторія", "Володимірівна");
        Display.people.add(viktoria);
        Display.accounts.put(viktoria, mariaAccount);
        Person.Account.BankCard viktoriaCard = mariaAccount.new BankCard(4255429, 982, viktoria);
        viktoriaCard.setAccountBalance();
        Display.bankCards.put(viktoriaCard, mariaAccount);
    }

    public static void createGoods() {
        Display.goods = new ArrayList<>(List.of(
            new Good("Апельсини", 365),
            new Good("Печеньки", 228),
            new Good("Мандаринки", 374),
            new Good("Яблука", 386),
            new Good("Шоколадні цукерки", 112),
            new Good("Банани", 309),
            new Good("Ківі", 498),
            new Good("Кексики", 622),
            new Good("Грейпфрут", 518),
            new Good("Тортик", 780)));
    }
}
