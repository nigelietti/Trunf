package model;

public class Giocatore {
    private String nome;
    private MazzoG mazzoGiocatore;
    private Mazzo cartePrese;

    public Giocatore(String nome){
        mazzoGiocatore = new MazzoG();
        this.nome = nome;
    }

    public MazzoG getMazzoGiocatore() {
        return mazzoGiocatore;
    }

    public void setMazzoGiocatore(MazzoG mazzoGiocatore) {
        this.mazzoGiocatore = mazzoGiocatore;
    }


}
