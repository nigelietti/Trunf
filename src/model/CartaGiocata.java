package model;

public class CartaGiocata extends Card{

    private Giocatore giocatore;

    public CartaGiocata(Value value, Seed seed) {
        super(value, seed);
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }
}
