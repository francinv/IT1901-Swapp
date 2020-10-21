package swapp.core;

import java.util.Date;

public class Ad {


    private final long time;
    /**
     * Instances of Ad are stored in AdList and displayed on the main page. AdList handles creation and communication
     * with the GUI.
     *
     */
    private String title;
    private User author; // For testing purposes author is currently a string, should be User author;
    private String textBody;
    private String category; //  "borrow"|"gift"|"switch"

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    private String status; // "completed" | "deleted" | "active"
    //Todo: Tilstand, transaksjonstype

    /**
     *
     * @param title
     * @param author
     * @param textBody
     *
     */
    public Ad(String title, User author, String textBody){
        // todo: check that name exists
        this.title = title;
        this.author = author;
        this.textBody = textBody;
        this.category = "borrow";  // temporary, should be passed as argument "borrow"|"gift"|"switch"
        this.status = "active"; // "completed" | "deleted" | "active"
        this.time =  new Date().getTime();
    }
    // Getters and setters
    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public String getTextBody() {
        return textBody;
    }
    public long getTime() {
        return time;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    /**
     * This method overrides how the Ad object is displayed in the UI.
     *
     */
    @Override
    public String toString() {
        String str = this.title+" (annonsert av "+this.author.getName()+")";
        return str;
    }

}
