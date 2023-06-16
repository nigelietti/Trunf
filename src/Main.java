
import com.google.gson.Gson;
import model.*;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Table table = new Table("Riccio", "Nige");

        System.out.println("Mazzo G1:");
        table.getG1().getMazzoGiocatore().mostraMazzo();
        System.out.println("Mazzo G2:");
        table.getG2().getMazzoGiocatore().mostraMazzo();
        System.out.println("Pozzetto:");
        table.getPozzetto().mostraMazzo();
        System.out.println("Trunf:" + table.getTrunf());
        System.out.print("Ultima carta mazzo:");
        table.getUltimaCarta().mostraCarta();



    }
}
