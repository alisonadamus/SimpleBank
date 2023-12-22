package com.alisonadamus.simpleBank.instances;

public class Person {

    private String surname;
    private String name;
    private String middleName;

    public Person(String surname, String name, String middleName) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public class Account {

        private int balance;
        private String password;
        private Person owner;

        public Account(String password, Person owner) {
            this.balance = 0;
            this.password = password;
            this.owner = owner;
        }

        public int getBalance() {
            return balance;
        }

        public String getPassword() {
            return password;
        }

        public Person getOwner() {
            return owner;
        }

        public class BankCard {

            private int id;
            private int cardBalance;
            private Person cardOwner;

            public BankCard(int id, int cardBalance, Person cardOwner) {
                this.id = id;
                this.cardBalance = cardBalance;
                this.cardOwner = cardOwner;
            }

            public int getCardBalance() {
                return cardBalance;
            }

            public void setCardBalance(int cardBalance) {
                this.cardBalance = cardBalance;
            }

            public Person getCardOwner() {
                return cardOwner;
            }

            @Override
            public String toString() {
                return "<" + id + "> " + "Баланс: " + cardBalance;
            }

            public void setAccountBalance() {
                balance = balance + cardBalance;
            }
        }
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + middleName;
    }
}
