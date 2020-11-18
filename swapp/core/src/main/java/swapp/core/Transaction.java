package swapp.core;

public class Transaction {

    public enum Status {
        ONGOING, 
        ACCEPTED;
    }

    private Ad ad;
    private User requester;
    private User receiver;
    private Status status;

    public Transaction(Ad ad, User requester) {
        this.ad = ad;
        this.requester = requester;
        this.receiver = ad.getAuthor();
        this.status = Status.ONGOING;
    }

    public User getRequester() {
        return this.requester;
    }

    public User getReceiver() {
        return this.receiver;
    }
    public Ad getAd() {
        return this.ad;
    }
    public Status getStatus() {
        return this.status;
    }
    public void accepted() {
        this.status = Status.ACCEPTED;
    }
    public String toString() {
        return this.ad.getTitle() + "(requested by " + this.getRequester() + ")";
    }
}