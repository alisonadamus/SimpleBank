package com.alisonadamus.simpleBank;

import com.alisonadamus.simpleBank.instances.Good;
import com.alisonadamus.simpleBank.instances.Person;
import com.alisonadamus.simpleBank.instances.Person.Account;
import com.alisonadamus.simpleBank.instances.Person.Account.BankCard;
import com.alisonadamus.simpleBank.instances.TransactionHistory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Display {

    static Scanner scanner = new Scanner(System.in);
    public static String currency = null;
    public static ArrayList<Person> people = new ArrayList<>();
    public static HashMap<Person, Person.Account> accounts = new HashMap<>();
    public static HashMap<Person.Account.BankCard, Person.Account> bankCards = new HashMap<>();
    public static ArrayList<Good> goods = new ArrayList<>();
    static ArrayList<TransactionHistory> transactionHistoryList = new ArrayList<>();

    public static void creatingInstances() {
        InstancesCreator.createPersons();
        InstancesCreator.createGoods();
    }

    public static void chooseCurrency() {
        System.out.println("\u001B[33mВиберіть валюту, якою хочете оперувати\u001B[0m");
        System.out.println("[1] Гривні UAH");
        System.out.println("[2] Долари $");
        System.out.println("[3] Євро €");

        switch (scanner.nextLine()) {
            case "1":
                currency = "UAH";
                break;
            case "2":
                currency = "$";
                break;
            case "3":
                currency = "€";
                break;
            default:
                System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                chooseCurrency();
        }
        choosePerson();
    }

    public static void choosePerson() {
        System.out.println("\u001B[33mВиберіть ПІБ зі списку\u001B[0m");

        IntStream.range(0, people.size()).mapToObj(i -> (i + 1) + ": " + people.get(i))
            .forEach(System.out::println);

        int check = scanner.nextInt();
        boolean userFounded = false;
        for (int i = 0; i < people.size(); i++) {
            if (check == i + 1) {
                userFounded = true;

                System.out.println(
                    "Введіть пароль(" + accounts.get(people.get(i)).getPassword() + "):");
                scanner.nextLine();
                String password = scanner.nextLine();

                if (password.equals(accounts.get(people.get(i)).getPassword())) {
                    System.out.println("\u001B[32mВітаємо, " + people.get(i) + "!");
                    accountsSystem(people.get(i), accounts.get(people.get(i)));
                } else {
                    System.out.println("\u001B[31mНевірний пароль!\u001B[0m");
                    choosePerson();
                }
                break;
            }
        }
        if (!userFounded) {
            System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
            choosePerson();
        }
    }

    public static void accountsSystem(Person person, Account account) {
        if (person.equals(account.getOwner())) {
            System.out.println("Ви є власником аккаунту");
        } else {
            System.out.println("Ви є довіреною особою користувача " + person);
        }
        System.out.println("Баланс: " + account.getBalance() + currency + "\u001B[0m");

        mainMenu(person, account);
    }

    public static void mainMenu(Person person, Account account) {
        System.out.println("\u001B[33mВиберіть дію\u001B[0m");
        System.out.println("[1] Переглянути інформацію про аккаунт");
        System.out.println("[2] Переглянути банківські карти");
        System.out.println("[3] Додати банківську карту");
        System.out.println("[4] Поповнити баланс");
        System.out.println("[5] Купити товар");
        System.out.println("[6] Вийти з аккаунту та показати історію транзакцій");

        switch (scanner.nextLine()) {
            case "1":
                accountsSystem(person, account);
            case "2":
                bankCards.entrySet().stream()
                    .filter(card -> card.getValue() == account)
                    .forEach(card -> System.out.println(
                        card.getKey() + currency + "Власник: " + card.getKey().getCardOwner()));
                mainMenu(person, account);
                break;
            case "3":
                addCard(person, account);
                break;
            case "4":
                addBalance(person, account);
                break;
            case "5":
                buyGoods(person, account);
                break;
            case "6":
                System.out.println("\u001B[33mІсторія транзакцій:\u001B[0m");
                System.out.println(transactionHistoryList);
                choosePerson();
                break;
            default:
                System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                mainMenu(person, account);
        }
    }

    public static void addCard(Person person, Account account) {
        if (person.equals(account.getOwner())) {
            System.out.println("Введіть id (до 10 цифр):");
            int id = scanner.nextInt();
            System.out.println("Введіть суму:");
            int balance = scanner.nextInt();

            Person.Account.BankCard newCard = account.new BankCard(id, balance, person);
            newCard.setAccountBalance();
            bankCards.put(newCard, account);
            System.out.println("\u001B[32mКарту успішно додано!\u001B[0m");
            mainMenu(person, account);
        } else {
            System.out.println("\u001B[31mУ вас нема прав власника аккаунту\u001B[0m");
            mainMenu(person, account);
        }
    }

    public static void addBalance(Person person, Account account) {
        ArrayList<BankCard> cards = bankCards.entrySet().stream()
            .filter(card -> card.getValue() == account && card.getKey().getCardOwner() == person)
            .map(Map.Entry::getKey)
            .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("\u001B[33mВиберіть карту, яку хочете поповнити\u001B[0m");

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + cards.get(i) + currency);
        }

        int check = scanner.nextInt();
        boolean checked = false;
        for (int i = 0; i < cards.size(); i++) {
            if (check == i + 1) {
                checked = true;

                System.out.println("Введіть суму:");
                int newBalance = scanner.nextInt() + cards.get(i).getCardBalance();
                cards.get(i).setCardBalance(newBalance);
                cards.get(i).setAccountBalance();

                System.out.println("\u001B[32mКарту поповнено!\u001B[0m");
                mainMenu(person, account);
                break;
            }
        }
        if (!checked) {
            System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
            addBalance(person, account);
        }

    }

    public static void buyGoods(Person person, Account account) {
        System.out.println("\u001B[33mВиберіть товар зі списку\u001B[0m");

        IntStream.range(0, goods.size()).mapToObj(i -> (i + 1) + ": " + goods.get(i))
            .forEach(System.out::println);


        int checkGood = scanner.nextInt();
        boolean checkedGood = false;
        for (int i = 0; i < goods.size(); i++) {
            if (checkGood == i + 1) {
                checkedGood = true;

                System.out.println("Введіть кількість товару, яку хочете придбати");
                int quantity = scanner.nextInt();
                int total = goods.get(i).getPrice() * quantity;

                System.out.println("Введіть карту:");
                ArrayList<BankCard> cards = bankCards.entrySet().stream()
                    .filter(card -> card.getValue() == account
                        && card.getKey().getCardOwner() == person)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(ArrayList::new));
                for (int j = 0; j < cards.size(); j++) {
                    System.out.println("[" + (j + 1) + "] " + cards.get(j) + currency);
                }

                int checkCard = scanner.nextInt();
                boolean checkedCard = false;
                for (int j = 0; j < cards.size(); j++) {
                    if (checkCard == j + 1) {
                        checkedCard = true;
                        if (cards.get(j).getCardBalance() >= total) {
                            cards.get(j).setCardBalance(cards.get(j).getCardBalance() - total);
                            cards.get(j).setAccountBalance();

                            TransactionHistory transactionHistory = new TransactionHistory(person,
                                cards.get(j), goods.get(i),quantity,total,account.getBalance());
                            transactionHistoryList.add(transactionHistory);
                            System.out.println("\u001B[32mТовар придбано\u001B[0m");
                        } else {
                            System.out.println("\u001B[31mНедостатньо грошей на карті\u001B[0m");
                        }
                        mainMenu(person, account);
                        break;
                    }
                }
                if (!checkedCard) {
                    System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
                    buyGoods(person, account);
                }
                break;
            }
        }
        if (!checkedGood) {
            System.out.println("\u001B[31mНатисніть відповідну кнопку\u001B[0m");
            buyGoods(person, account);
        }
    }
}
