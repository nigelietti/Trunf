package Network.Client;

import Network.GenericMessage;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SocketClient extends Client {

    private final Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Gson gson;
    private final ExecutorService readQueue;
    private ScheduledExecutorService ping;

    private static final int SOCKET_TIMEOUT = 10000;
    public SocketClient(String address, int port) throws IOException {
        this.socket = new Socket();
        try{
        this.socket.connect(new InetSocketAddress(address, port), SOCKET_TIMEOUT);
        }
        catch (SocketTimeoutException e){
        System.out.println("Error");
        System.exit(0);
        }
        catch(ConnectException e){
        System.out.println("Server not available");
        }
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        gson = new Gson();
        readQueue = Executors.newSingleThreadExecutor();
        this.ping = Executors.newSingleThreadScheduledExecutor();
        }

/**
 * this method reads the message received from the server and notify the observer
 */
@Override
public void readMessage() throws IOException {
        readQueue.execute(() -> {
        while(!readQueue.isShutdown()){
        String msg;
        GenericMessage message;
        try {
        msg = in.readUTF();
        message = gson.fromJson(msg, GenericMessage.class);
        switch(message.getMessageType()){
        case LOGIN_RESPONSE:
        LoginResponse loginResponse = gson.fromJson(msg, LoginResponse.class);
        notifyObserver(loginResponse);
        break;
        case NEW_GAME_RESPONSE:
        NewGameResponse newGameResponse = gson.fromJson(msg, NewGameResponse.class);
        notifyObserver(newGameResponse);
        break;
        case WAIT_PLAYERS:
        WaitPlayers waitPlayers = gson.fromJson(msg, WaitPlayers.class);
        notifyObserver(waitPlayers);
        break;
        case JOIN_GAME_RESPONSE:
        JoinGameResponse joinGameResponse = gson.fromJson(msg, JoinGameResponse.class);
        notifyObserver(joinGameResponse);
        break;
        case STARTING_GAME:
        StartingGame startingGame = gson.fromJson(msg,StartingGame.class);
        notifyObserver(startingGame);
        break;
        case CURRENT_PLAYER:
        CurrentPlayerMessage currentPlayerMessage = gson.fromJson(msg, CurrentPlayerMessage.class);
        notifyObserver(currentPlayerMessage);
        break;
        case WIN_MESSAGE:
        WinMessage winMessage = gson.fromJson(msg, WinMessage.class);
        notifyObserver(winMessage);
        disconnect();
        break;
        case DISCONNECTION:
        DisconnectionMessage disconnectionMessage = gson.fromJson(msg, DisconnectionMessage.class);
        notifyObserver(disconnectionMessage);
        break;
        case MATCH_PHASE:
        GenericMatchPhase genericMatchPhase = gson.fromJson(msg, GenericMatchPhase.class);
        switch(genericMatchPhase.getMessagePhase()) {
            case UPDATE:
                UpdateTable updateBoard = gson.fromJson(msg, UpdateTable.class);
                notifyObserver(updateBoard);
                break;
            case ASK_CARD:
                AskAssitantCard askAssitantCard = gson.fromJson(msg, AskAssitantCard.class);
                notifyObserver(askAssitantCard);
                break;
            default:
                break;
         }
        }



        } catch (IOException e) {
        try {
        System.out.println("\nConnection lost with the server");
        disconnect();
        //called when the server stops
        } catch (IOException ex) {
        ex.printStackTrace();
        }
        readQueue.shutdownNow();
        }
        }
        });
        }

/**
 * this method send messages to the server using json
 * @param message
 * @throws IOException
 */
@Override
public void sendMessage(GenericMessage message) throws IOException {
        try{
        String msg = gson.toJson(message);
        try{
        out.writeUTF(msg);
        }
        catch(SocketException se){
        disconnect();
        out.flush();
        }
        out.flush();
        }
        catch(IOException e){
        e.printStackTrace();
        disconnect();
        }
        }

/**
 * this method handle the disconnection of a client
 * @throws IOException
 */
@Override
public void disconnect() throws IOException {
        if(!socket.isClosed()){
        readQueue.shutdownNow();
        startPing(false);
        socket.close();
        }
        }

/**
 * this method define the ping system
 * @param start a boolean setted to true when the method is called for the first time
 */
public void startPing(boolean start) throws IOException {
        if(start){
        ping.scheduleAtFixedRate(() -> {
        try {
        sendMessage(new PingMessage());
        } catch (IOException e) {
        e.printStackTrace();
        }
        }, SOCKET_TIMEOUT/2, SOCKET_TIMEOUT/2, TimeUnit.MILLISECONDS);
        }
        else{
        ping.shutdownNow();
        }
        }
        }