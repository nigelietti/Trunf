package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Mazzo {
    private ArrayList<Card> cards = new ArrayList<Card>(); //attributo di tipo ArrayList già presente
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void instanziaMazzo(){
        InputStream is = Mazzo.class.getResourceAsStream("/resource/Carte.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Scanner myReader = new Scanner(reader);
        String data = myReader.nextLine();

        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<Card>>(){}.getType();
        cards = gson.fromJson(data, userListType);

        myReader.close();
    }

    public void mischia(){
        Collections.shuffle(cards);
    }

    public void mostraMazzo(){
        for(Card c: cards){
            c.mostraCarta();
        }
        System.out.print("\n");
    }

}
