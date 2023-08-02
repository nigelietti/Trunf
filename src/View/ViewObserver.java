package View;

import model.Card;

import java.io.IOException;
import java.util.Map;

public interface ViewObserver {

    void onUpdateServerSettings(Map<String, String> serverSettings);

    void onUpdateNickname(String nickname) throws IOException;

    void onCreateorJoin(String s) throws IOException;

    void onJoinRequest(int id) throws IOException;

    void onCreateGame(int playersNumber, boolean isExpert) throws IOException;

    void askCard(Card card) throws IOException;

    void onWin() throws IOException;
}