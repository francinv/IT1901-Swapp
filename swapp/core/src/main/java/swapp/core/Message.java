package swapp.core;

import java.util.ArrayList;

public class Message {
    private User adOwner; //owns the ad
    private Ad ad;
    private User messageSender;  // the one sending the message to the adOwner
    private ArrayList<String> listOfStringMessages;

    public Message(Ad ad, User messageSender, String firstMessage){
        this.ad = ad;
        //this.adOwner = ad.getAuthor();
        this.messageSender = messageSender;
        this.listOfStringMessages = new ArrayList<>();
        this.listOfStringMessages.add(firstMessage);
    }

    public void sendMessage(User messageFrom, String message){
        assert messageFrom == adOwner || messageFrom==messageSender;
        this.listOfStringMessages.add(message);
    }

    public ArrayList<String> getListOfStringMessages() {
        return listOfStringMessages;
    }
}
