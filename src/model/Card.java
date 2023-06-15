package model;

public class Card {
    private Value value; // attributo è di nome value ed è di classe Value (classe Enum di prima )
    private Seed seed; // attributo è di nome seed ed è di classe Seed (classe Enum di prima )

    //bisogna accedere ai singoli semi e valori delle carte


    public Value getValue() {   //public un altro oggetto può chiamarlo e ti ritorna Value
        return value;
    }

    public Seed getSeed() {
        return seed;
    }
}
