package Controller;

import Network.GenericMessage;

import static Network.MessageType.*;

public class ClientController implements ViewObserver, Observer {
    private final View view;
    private Client client;
    private String nickname;
    private final ExecutorService taskQueue;
    private final ExecutorService lastTask;

    /**
     * constructor of ClientController, it initializes the thread executors
     * @param view it can be the Cli or the Gui
     */
    public ClientController(View view) {
        this.view = view;
        taskQueue = Executors.newSingleThreadExecutor();
        lastTask = Executors.newSingleThreadExecutor();
    }

    /**
     * it instances the client after receiving the server address and server port
     * @param serverSettings a map that contains the server address and the server port
     */
    @Override
    public void onUpdateServerSettings(Map<String, String> serverSettings) {
        String address = serverSettings.get("address");
        String port = serverSettings.get("port");
        int intport = Integer.parseInt(port);
        try {
            client = new SocketClient(address,intport);
            client.addObserver(this);
            client.readMessage(); // Starts an asynchronous reading from the server.
            client.startPing(true);
            taskQueue.execute(view::askNickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method send to the server a LoginRequest message with the nickname chosen by the client
     * @param nickname the username chosen by the client
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onUpdateNickname(String nickname) throws IOException {
        this.nickname = nickname;
        client.sendMessage(new LoginRequest(this.nickname));
    }

    /**
     * this method send to the server a NewGameRequest message if the client wants to create a new match or
     * proceeds by asking the id if the client wants to join in a match
     * @param choose the string that represents the choice of the client, it can be "create" or "join"
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onCreateorJoin(String choose) throws IOException {
        if(choose.equals("create")){
            client.sendMessage(new NewGameRequest(nickname));
        }
        else{
            view.askID();
        }
    }

    /**
     * this method send to the server a JoinGameRequest message
     * @param id the id inserted by the client
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onJoinRequest(int id) throws IOException {
        client.sendMessage(new JoinGameRequest(nickname, id));
    }

    /**
     * this method send to the server a CreateGame message
     * @param playersNumber the maximum number of the player that will be accepted in the match
     * @param isExpert the game mode chosen by the client for the new match
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onCreateGame(int playersNumber, boolean isExpert) throws IOException {
        client.sendMessage(new CreateGame(nickname, client.getMatchID(), playersNumber, isExpert));
    }

    /**
     * this method send to the server a Planning message
     * @param assistantCard it represents the AssistantCard chosen by the client
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskAssistantCard(AssistantCard assistantCard) throws IOException {
        client.sendMessage(new Planning(nickname, assistantCard));
    }

    /**
     * this method send to the server a MoveStudent message
     * @param s a string that represent the students end their new location on the board
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskMoveStudent(ArrayList<String> s) throws IOException {
        client.sendMessage(new MoveStudent(nickname, s));
    }

    /**
     * this method send to the server a MoveMN message
     * @param steps the number of steps that mother nature will take through the islands
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskMoveMN(int steps) throws IOException {
        client.sendMessage(new MoveMN(nickname, steps));
    }

    /**
     * this method send to the server a ChooseCloud message
     * @param cloud the index that represents the cloud chosen by the client
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskCloud(int cloud) throws IOException {
        client.sendMessage(new ChooseCloud(nickname, cloud));
    }

    /**
     * this method send to the server a ChooseCard message
     * @param card it represents the card chosen by the client
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskCharacterCard(int card) throws IOException {
        client.sendMessage(new ChooseCard(nickname, card));
    }

    /**
     * this method send to the server a ChooseEffect message
     * @param effect the strings that represents the effect chosen by the client
     * @param characterCard the character card that that links with the effect
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onAskEffect(ArrayList<String> effect, int characterCard) throws IOException {
        client.sendMessage(new ChooseEffect(nickname, effect, characterCard));
    }

    /**
     * this method send to the server a EndMatch message
     * @throws IOException it is needed for the sendMessage method
     */
    @Override
    public void onWin() throws IOException {
        client.sendMessage(new EndMatch(nickname));
    }

    /**
     * this method manage the messages that can arrive from the server
     * for each message correspond a different behavior for the evolution of the game
     * @param message the message received
     */
    @Override
    public void update(GenericMessage message) {
        switch(message.getMessageType()){
            case LOGIN_RESPONSE:
                LoginResponse logres = (LoginResponse) message;
                if(logres.getSuccess()) taskQueue.execute(view::askCreateorJoin);
                if(!logres.getSuccess()) taskQueue.execute(view::failedLogin);
                break;
            case NEW_GAME_RESPONSE:
                NewGameResponse newGameResponse = (NewGameResponse) message;
                client.setMatchID(newGameResponse.getMatchID());
                taskQueue.execute(() -> view.askGameParameters(client.getMatchID()));
                break;
            case WAIT_PLAYERS:
                taskQueue.execute(view::waitOthersPlayer);
                break;
            case JOIN_GAME_RESPONSE:
                JoinGameResponse joinGameResponse = (JoinGameResponse) message;
                if(joinGameResponse.getSuccess()) taskQueue.execute(view::succesfullJoin);
                if(!joinGameResponse.getSuccess()) taskQueue.execute(view::failedJoin);
                break;
            case STARTING_GAME:
                StartingGame startingGame = (StartingGame) message;
                taskQueue.execute(view::startingGame);
                break;
            case CURRENT_PLAYER:
                CurrentPlayerMessage currentPlayerMessage = (CurrentPlayerMessage) message;
                taskQueue.execute(() -> view.showCurrentPlayer(currentPlayerMessage.getName()));
                break;
            case WIN_MESSAGE:
                WinMessage winMessage = (WinMessage) message;
                taskQueue.execute(() -> view.showWin(winMessage.getWinner()));
                break;
            case DISCONNECTION:
                DisconnectionMessage disconnectionMessage = (DisconnectionMessage) message;
                taskQueue.execute(() -> view.showDisconnection(disconnectionMessage.getName()));
                break;
            case MATCH_PHASE:
                GenericMatchPhase genericMatchPhase = (GenericMatchPhase) message;
                switch(genericMatchPhase.getMessagePhase()) {
                    case UPDATE:
                        UpdateBoard updateBoard = (UpdateBoard) genericMatchPhase;
                        taskQueue.execute(() -> view.updateBoard(updateBoard.getGm()));
                        break;
                    case ASK_ASSISTANT_CARD:
                        AskAssitantCard askAssitantCard = (AskAssitantCard) genericMatchPhase;
                        taskQueue.execute(() -> view.askAssistantCard(askAssitantCard.getAssistantCards()));
                        break;
                }

}