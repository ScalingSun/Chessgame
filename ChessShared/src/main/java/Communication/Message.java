package Communication;


import Enums.messageType;

public class Message {

    private messageType type;
    private String message;
    private int playerNr;

    public Message(messageType t, String m, int nr){
        type = t;
        message = m;
        playerNr = nr;
    }

    public String getMessage() {
        return message;
    }

    public int getPlayerNr() {
        return playerNr;
    }

    public messageType getType() {
        return type;
    }
}
