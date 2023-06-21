package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Table {
    private Seed trunf;
    private Card ultimaCarta;
    private CartaGiocata cartaGiocata1, cartaGiocata2;
    private Giocatore g1, g2;
    private ArrayList<Card> mazzo;

    private ArrayList<Card> pozzetto;



    public Seed getTrunf() {
        return trunf;
    }

    public void setTrunf(Seed trunf) {
        this.trunf = trunf;
    }

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

    public ArrayList<Card> getPozzetto() {
        return pozzetto;
    }

    public Table(String nome1, String nome2){
        g1 = new Giocatore(nome1);
        g2 = new Giocatore(nome2);
        pozzetto = new ArrayList<Card>();

        mazzo = new ArrayList<Card>();
        instanziaMazzo();
        mischiaMazzo();
        mostraMazzo(mazzo);


        for(int i = 0; i < 10; i++){
            g1.daiCarta(mazzo.remove(0));
        }
        for(int i = 0; i < 10; i++){
            g2.daiCarta(mazzo.remove(0));
        }

        for(int i = 0; i < 10; i++){
            pozzetto.add(mazzo.remove(0));
        }

        setTrunf(mazzo.get(0).getSeed());
        setUltimaCarta(mazzo.get(5));

        System.out.println("Scale G1");
        g1.scale();

        System.out.println("Scale G2");
        g2.scale();

    }

    public void instanziaMazzo(){
        InputStream is = Card.class.getResourceAsStream("/resource/Carte.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Scanner myReader = new Scanner(reader);
        String data = myReader.nextLine();

        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<Card>>(){}.getType();
        mazzo = gson.fromJson(data, userListType);

        myReader.close();
    }

    public void mischiaMazzo(){
        Collections.shuffle(mazzo);
    }

    public void mostraMazzo(ArrayList<Card> m){
        for(Card c: m){
            c.mostraCarta();
        }
        System.out.print("\n");
    }


    public void puntiCusa(){
        g1.ifStuck(trunf);
        g2.ifStuck(trunf);

        if(g1.pokermax() != null && g2.pokermax() != null){
            if(!g1.pokermax().equals('J') && !g2.pokermax().equals('J')){
                if (g1.pokermax().compareTo(g2.pokermax())>0){
                    g1.puntiPoker();
                }
                else{
                    g2.puntiPoker();
                }
            }
            else if (g1.pokermax().equals('J')) {
                g1.puntiPoker();
            }
            else {
                g2.puntiPoker();
            }
        }
        else{
            if(g1.pokermax() == null)
                g2.puntiPoker();
            else
                g1.puntiPoker();
        }

        if(g1.scalamax(trunf) != null && g2.scalamax(trunf) != null){
            if(g1.scalamax(trunf).getNum() > g2.scalamax(trunf).getNum()){
                g1.puntiScale();
            }
            else if(g1.scalamax(trunf).getNum() < g2.scalamax(trunf).getNum()){
                g2.puntiScale();
            }
            else{
                if(g1.scalamax(trunf).getValue().compareTo(g2.scalamax(trunf).getValue()) > 0){
                    g1.puntiScale();
                }
                else if(g1.scalamax(trunf).getValue().compareTo(g2.scalamax(trunf).getValue()) < 0){
                    g2.puntiScale();
                }
                else{
                    if(g1.scalamax(trunf).getSeed().equals(trunf))
                        g1.puntiScale();
                    if(g2.scalamax(trunf).getSeed().equals(trunf))
                        g2.puntiScale();
                }
            }
        }
        else{
            if(g1.scalamax(trunf) == null)
                g2.puntiScale();
            else
                g1.puntiScale();
        }
    }






}
