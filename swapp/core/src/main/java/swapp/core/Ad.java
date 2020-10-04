package swapp.core;

public class Ad {
    private String title;
    private String author;
    private String textBody;
    //Todo: Tilstand, transaksjonstype, Bilder

    /**
     *
     * @param title
     * @param author
     * @param textBody
     */
    public Ad(String title, String author, String textBody){
        // todo: check that name exists
        this.title = title;
        this.author = author;
        this.textBody = textBody;

    }

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

    @Override
    public String toString() {
        String str = this.title+" (annonsert av "+this.author+")";
        System.out.println(str);
        return str;
    }

    public String getAgrip() {
        String str = this.title+" (annonsert av "+this.author+")";
        System.out.println(str);
        return str;
    }
}
