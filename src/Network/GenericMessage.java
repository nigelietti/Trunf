package Network;

public class GenericMessage {
    private String nickname;
    private MessageType messageType;

    public GenericMessage(String nn) {
        nickname = nn;
    }

    public void setMessageType(MessageType mt) {
        messageType = mt;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getNickname() {
        return nickname;
    }

}