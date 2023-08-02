package View;

import model.Card;
import model.Table;

import java.io.IOException;
import java.util.ArrayList;

public interface View {

    void askNickname();

    void failedLogin();

    void succesLogin() throws IOException;

    void askCreateorJoin();

    void askID();

    void askGameParameters(int id);

    void waitOtherPlayer();

    void succesfullJoin();

    void failedJoin();

    void startingGame();

    void showCurrentPlayer(String name);

    void showWin(String winner);

    void showDisconnection(String name);

    void updateTable(Table table);

    void askCard(ArrayList<Card> carte);

    void cardError(ArrayList<Card> carte);



}