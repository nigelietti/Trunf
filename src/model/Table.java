package model;

public class Table {
    private Seed trunf;
    private Card ultimaCarta;
    private CartaGiocata cartaGiocata1, cartaGiocata2;
    private Giocatore g1, g2;
    private Mazzo mazzo;

    private MazzoG pozzetto;



    public Seed getTrunf() {
        return trunf;
    }

    public void setTrunf(Seed trunf) {
        this.trunf = trunf;
    } //parametro che gli gli dai

    public Card getUltimaCarta() {
        return ultimaCarta;
    }

    public void setUltimaCarta(Card ultimaCarta) {
        this.ultimaCarta = ultimaCarta;
    }

    public CartaGiocata getCartaGiocata1() {
        return cartaGiocata1;
    }

    public CartaGiocata getCartaGiocata2() {
        return cartaGiocata2;
    }

    public void setCartaGiocata1(CartaGiocata cartaGiocata1) {
        this.cartaGiocata1 = cartaGiocata1;
    }

    public void setCartaGiocata2(CartaGiocata cartaGiocata2) {
        this.cartaGiocata2 = cartaGiocata2;
    }

    public Giocatore getG1() {
        return g1;
    }

    public Giocatore getG2() {
        return g2;
    }

    public MazzoG getPozzetto() {
        return pozzetto;
    }

    public Table(String nome1, String nome2){
        g1 = new Giocatore(nome1);
        g2 = new Giocatore(nome2);
        pozzetto = new MazzoG();

        mazzo = new Mazzo();
        mazzo.instanziaMazzo();
        mazzo.mischia();
        mazzo.mostraMazzo();


        for(int i = 0; i < 10; i++){
            g1.getMazzoGiocatore().addCard(mazzo.getCards().remove(0));
        }
        for(int i = 0; i < 10; i++){
            g2.getMazzoGiocatore().addCard(mazzo.getCards().remove(0));
        }

        for(int i = 0; i < 10; i++){
            pozzetto.addCard(mazzo.getCards().remove(0));
        }

        setTrunf(mazzo.getCards().get(0).getSeed());
        setUltimaCarta(mazzo.getCards().get(5));

    }


}
