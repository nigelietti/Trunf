package model;

import java.util.ArrayList;

public class MazzoG {
    private ArrayList<Card> cards; //attributo di tipo ArrayList già presente

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean ifStuck(Seed trunf) {//metodo
        boolean trovato = false;
        for (Card k : cards) { //itera su tutte le carte(c) col metodo che eredita da mazzo

            if (k.getValue().equals('K') && k.getSeed().equals(trunf)) {
                for (Card q : cards) {
                    if (q.getValue().equals('Q') && q.getSeed().equals(trunf)) {
                        return true;
                    }
                }
            }
        }

    return false;}
    public Scala ifscala(){

    }

}