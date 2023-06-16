
import com.google.gson.Gson;
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
        table.mostraMazzo(table.getG1().getMazzoGiocatore());
        System.out.println("Pozzetto:");
        table.mostraMazzo(table.getPozzetto());
        System.out.println("Trunf:" + table.getTrunf());
        System.out.print("Ultima carta mazzo:");
        table.getUltimaCarta().mostraCarta();



    }
}
