package model;

import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private ArrayList<Card> mazzoGiocatore;
    private ArrayList<Card> cartePrese;

    public Giocatore(String nome){
        mazzoGiocatore = new ArrayList<Card>();
        cartePrese = new ArrayList<Card>();
        this.nome = nome;
    }

    public ArrayList<Card> getMazzoGiocatore() {
        return mazzoGiocatore;
    }

    public void setMazzoGiocatore(ArrayList<Card> mazzoGiocatore) {
        this.mazzoGiocatore = mazzoGiocatore;
    }

    public boolean ifStuck(Seed trunf) {//metodo
        boolean trovato = false;
        for (Card k : getMazzoGiocatore()) { //itera su tutte le carte(c) col metodo che eredita da mazzo

            if (k.getValue().equals('K') && k.getSeed().equals(trunf)) {
                for (Card q : getMazzoGiocatore()) {
                    if (q.getValue().equals('Q') && q.getSeed().equals(trunf)) {
                        return true;
                    }
                }
            }
        }

        return false;}
    public Value ifpoker(){
        Value valore = null;
        int contatore;
        for (Value v: Value.values()){
            contatore = 0;
            for (Card k : getMazzoGiocatore()) {
                if (k.getValue().compareTo(v) == 0)
                    contatore++;

            }
            if (contatore==4) {
                valore=v;}
        }
        return valore;
    }


    public Scala ifscala(){
        Scala scala=new Scala();
        Scala scalaprima = new Scala();
        Value valore= null;
        for (int i=0; i<10; i++)  {
            valore=getMazzoGiocatore().get(i).getValue();
            for (int j=0; j<4; j++){
                if (getMazzoGiocatore().get(i+j+1).getSeed().compareTo(getMazzoGiocatore().get(i+j).getSeed())==0 && getMazzoGiocatore().get(i+j+1).getValue().compareTo(mazzoGiocatore.get(i+j).getValue())==0){
                    scala.setNum(j+2);
                    scala.setValue(getMazzoGiocatore().get(i).getValue());
                }
                else{
                    break;
                }
                if (scala.getNum()<3){
                    scala.setNum(0);
                    scala.setValue(null);
                    break;
                }
            }
            scalaprima.setValue(scala.getValue());
            scalaprima.setNum(scala.getNum());
        }

        return null;
    }

    public void inserisciCarta(Card card) {
        if (getMazzoGiocatore().isEmpty()) {
            getMazzoGiocatore().add(card);
            return;
        }

        for (Card c : getMazzoGiocatore()) {
            if (card.getSeed().compareTo(c.getSeed()) < 0) {
                getMazzoGiocatore().add(c.getSeed().ordinal(), card);
                return;
            } else {
                if (card.getSeed().compareTo(c.getSeed()) == 0) {
                    if (card.getValue().compareTo(c.getValue()) < 0) {
                        getMazzoGiocatore().add(c.getSeed().ordinal(), card);
                        return;
                    }
                }
            }

        }

    }

}
