package swapp.core;

import java.util.Objects;

/**
 * A transaction object is created when someone requests an Ad. It is stored in the receiver's UserTransactionList
 * and will then show up on the profile of the receiver, but only if it has Status.ONGOING
 * page of the receiver
 */
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
    // Getters
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

    /**
     * Makes the Transaction not show up on profile page.
     */
    public void accepted() {
        this.status = Status.ACCEPTED;
    }
    /**
     * used to display the object in listView
     */
    public String toString() {
        return this.ad.getTitle() + "(requested by " + this.getRequester() + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return requester.equals(transaction.requester) &&
                receiver.equals(transaction.receiver) &&
                ad.equals(transaction.ad);
    }
    @Override
    public int hashCode() {
        return Objects.hash(receiver, requester, ad);
    }
}