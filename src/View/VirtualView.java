package View;

import View.View;
import model.Card;
import model.Table;

import java.io.IOException;
import java.util.ArrayList;


public class VirtualView implements View {


    @Override
    public void askNickname() {

    }

    @Override
    public void failedLogin() {

    }

    @Override
    public void succesLogin() throws IOException {

    }

    @Override
    public void askCreateorJoin() {

    }

    @Override
    public void askID() {

    }

    @Override
    public void askGameParameters(int id) {

    }

    @Override
    public void waitOtherPlayer() {

    }

    @Override
    public void succesfullJoin() {

    }

    @Override
    public void failedJoin() {

    }

    @Override
    public void startingGame() {

    }

    @Override
    public void showCurrentPlayer(String name) {

    }

    @Override
    public void showWin(String winner) {

    }

    @Override
    public void showDisconnection(String name) {

    }

    @Override
    public void updateTable(Table table) {

    }

    @Override
    public void askCard(ArrayList<Card> carte) {

    }

    @Override
    public void cardError(ArrayList<Card> carte) {

    }
}