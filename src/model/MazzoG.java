package model;

import java.util.ArrayList;

public class MazzoG extends Mazzo {

    public boolean ifStuck(Seed trunf) {//metodo
        boolean trovato = false;
        for (Card k : super.getCards()) { //itera su tutte le carte(c) col metodo che eredita da mazzo

            if (k.getValue().equals('K') && k.getSeed().equals(trunf)) {
                for (Card q : super.getCards()) {
                    if (q.getValue().equals('Q') && q.getSeed().equals(trunf)) {
                        return true;
                    }
                }
            }
        }

    return false;}

    public void inserisciCarta(Card card){
        if(super.getCards().isEmpty()) {
            super.getCards().add(card);
            return;
        }

        for(Card c : super.getCards()){
            if(card.getSeed().compareTo(c.getSeed()) < 0){
                super.getCards().add(c.getSeed().ordinal(), card);
                return;
            }
            else{
                if()
            }
        }
    }
    public Scala ifscala(){

    }



}