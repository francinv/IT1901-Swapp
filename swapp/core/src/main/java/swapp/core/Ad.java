package swapp.core;

import java.util.Date;
import java.util.Objects;

/**
 * The Ad can be created by users and then requested by other users to create a transaction object.
 * The User that creates the Ad has a one-to-many relation with the Ad. Only Status.ACTIVE Ads will be shown
 * in the GUI.
 */
public class Ad {

  public enum Status {
    ACTIVE,
    COMPLETED,
    DELETED // for potential future features, not currently used
  }

  public enum Category {
    BORROW,
    GIFT,
    TRADE
  }

  /**
   * Instances of Ad are stored in AdList and displayed on the main page. AdList handles creation and communication
   * with the GUI.
   *
   */
  private String title;
  private User author;
  private String textBody;
  private Category category;
  private final long time;
  private Status status;

  /**
   *
   * @param title
   * @param author
   * @param textBody
   * @param category
   *
   */
  public Ad(String title, User author, String textBody, Category category){
    this.title = title;
    this.author = author;
    this.textBody = textBody;
    this.category = category;
    this.status = Status.ACTIVE;
    this.time =  new Date().getTime();
  }

  /**
   * For use after deserializing from Json to get original time.
   * @param title
   * @param author
   * @param textBody
   * @param category
   * @param status
   * @param time
   */
  public Ad(String title, User author, String textBody, Category category, Status status, long time) {
    this.title = title;
    this.author = author;
    this.textBody = textBody;
    this.category = category;
    this.status = status;
    this.time = time;
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

  public Category getCategory() {
    return category;
  }

  public Status getStatus() {
    return status;
  }

  public long getTime() {
    return time;
  }

  public void setTextBody(String textBody) {
    this.textBody = textBody;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * We use the toString method to display Ad-objects in the listView.
   * @return
   */
  @Override
  public String toString() {
    return this.title + " (annonsert av " + this.author.getName() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Ad ad = (Ad) o;
    return time == ad.time &&
            title.equals(ad.title) &&
            author.equals(ad.author) &&
            textBody.equals(ad.textBody) &&
            category == ad.category &&
            status == ad.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, textBody, category, time, status);
  }
}
