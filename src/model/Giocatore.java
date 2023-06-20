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


    public ArrayList<Scala> scale(){
        Value valorePiuAlto = mazzoGiocatore.get(9).getValue();
        Value valorePrecedente = mazzoGiocatore.get(9).getValue();
        Seed seme = mazzoGiocatore.get(9).getSeed();;
        int carteInFila = 1;

        ArrayList<Scala> scale = new ArrayList<Scala>();

        for(int i = 8; i >= 0; i--){
            if(mazzoGiocatore.get(i).getSeed().equals(seme) && mazzoGiocatore.get(i).getValue().compareTo(valorePrecedente) == -1){

                if(carteInFila < 5){
                    carteInFila++;
                    valorePrecedente = mazzoGiocatore.get(i).getValue();
                }
                else{
                    scale.add(new Scala(carteInFila, valorePiuAlto, seme));
                    carteInFila = 1;
                    valorePiuAlto = mazzoGiocatore.get(i).getValue();
                    valorePrecedente = valorePiuAlto;
                }

            }
            else{
                if(carteInFila > 2){
                    scale.add(new Scala(carteInFila, valorePiuAlto, seme));
                    carteInFila = 1;
                    valorePiuAlto = mazzoGiocatore.get(i).getValue();
                    valorePrecedente = valorePiuAlto;
                }
            }

        }

        for(Scala s: scale){
            System.out.println("Scala di " + s.getNum() + " carte di " + s.getSeed() + " con carta più alta " + s.getValue());
        }

        return scale;
    }


    public void daiCarta(Card card) {
        if (mazzoGiocatore.isEmpty()) {
            mazzoGiocatore.add(card);
            return;
        }

        for (Card c : mazzoGiocatore) {
            if (card.getSeed().compareTo(c.getSeed()) < 0) {
                mazzoGiocatore.add(mazzoGiocatore.indexOf(c), card);
                return;
            } else {
                if (card.getSeed().compareTo(c.getSeed()) == 0) {
                    if (card.getValue().compareTo(c.getValue()) < 0) {
                        mazzoGiocatore.add(mazzoGiocatore.indexOf(c), card);
                        return;
                    }
                }
            }

        }

        mazzoGiocatore.add(card);
        return;

    }

}
