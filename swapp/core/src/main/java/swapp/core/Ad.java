package swapp.core;

public class Ad {
    /**
     * Instances of Ad are stored in AdList and displayed on the main page. AdList handles creation and communication
     * with the GUI.
     *
     */
    private String title;
    private String author; // For testing purposes author is currently a string, should be User author;
    private String textBody;
    // private String status; // "completed" | "deleted" | "active"
    //Todo: Tilstand, transaksjonstype

    /**
     *
     * @param title
     * @param author
     * @param textBody
     *
     */
    public Ad(String title, String author, String textBody){
        // todo: check that name exists
        this.title = title;
        this.author = author;
        this.textBody = textBody;

    }
    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTextBody() {
        return textBody;
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
        String str = this.title+" (annonsert av "+this.author+")";
        return str;
    }

}
