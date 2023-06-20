
import model.*;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Table table = new Table("Riccio", "Nige");

        System.out.println("Mazzo G1:");
        table.mostraMazzo(table.getG1().getMazzoGiocatore());
        System.out.println("Mazzo G2:");
        table.mostraMazzo(table.getG2().getMazzoGiocatore());
        System.out.println("Pozzetto:");
        table.mostraMazzo(table.getPozzetto());
        System.out.println("Trunf:" + table.getTrunf());
        System.out.print("Ultima carta mazzo:");
        table.getUltimaCarta().mostraCarta();
        System.out.println();
        Giocatore g3= new Giocatore("picca");
        ArrayList<Card> carte4 = new ArrayList<Card>();

        carte4.add(new Card(Value.SETTE,Seed.PICCHE));
        carte4.add(new Card(Value.OTTO,Seed.PICCHE));
        carte4.add(new Card(Value.NOVE,Seed.PICCHE));
        carte4.add(new Card(Value.DIECI,Seed.PICCHE));
        carte4.add(new Card(Value.J,Seed.PICCHE));
        carte4.add(new Card(Value.SEI,Seed.FIORI));
        carte4.add(new Card(Value.SETTE,Seed.FIORI));
        carte4.add(new Card(Value.OTTO,Seed.FIORI));
        carte4.add(new Card(Value.SETTE,Seed.QUADRI));
        carte4.add(new Card(Value.OTTO,Seed.QUADRI));
        g3.setMazzoGiocatore(carte4);
        g3.scale();

        g3.scalamax(table.getTrunf());
        g3.pokermax();




    }
}
