package Network.Client;

import Network.GenericMessage;
import observer.Observable;

import java.io.IOException;

public abstract class Client extends Observable {

    private int matchID;
    private String nickname;

    public abstract void sendMessage(GenericMessage message) throws IOException;

    public abstract void readMessage() throws IOException;

    public abstract void disconnect() throws IOException;

    public abstract void startPing(boolean start) throws IOException;

    public void setMatchID(int id){
        matchID=id;
    }
    public int getMatchID(){
        return matchID;
    }
    public void setNickname(String name){
        nickname=name;
    }
    public String getNickname(){
        return nickname;
    }
}