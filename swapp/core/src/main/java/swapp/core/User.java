package swapp.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The User object is the underlying logic for clients to interact with the App. Ads created by the
 * user and Transactions created by someone else requesting the user's Ad is stored here.
 */
public class User {
  private String name;
  private String email;
  private String password;
  private List<Ad> userAds;
  private List<Transaction> userTransactions;

  /**
   * @param name String
   * @param email String
   * @param password String
   */
  public User(String name, String email, String password) {
    this.name = name.toLowerCase();
    this.email = email;
    this.password = password;
    this.userAds = new ArrayList<>();
    this.userTransactions = new ArrayList<>();
  }

  public void createAd(String title, String textBody, Ad.Category category) {
    this.userAds.add(new Ad(title, this, textBody, category));
  }

	public Ad getAd(String title) {
	  for (Ad ad : userAds) {
	    if (ad.getTitle().equals(title)) {
	      return ad;
        }
      }
	  return null;
    }

  public void createTransaction(Ad ad, User requester) {
    this.userTransactions.add(new Transaction(ad, requester));
    System.out.println(userTransactions);
  }

  public List<swapp.core.Ad> getUserAds() {
    return this.userAds;
  }

  public List<swapp.core.Transaction> getUserTransactions() {
    return this.userTransactions;
  }

  /**
   * getter for the User's name.
   * @return the name of the User
   */
  public String getName() {
    return name;
  }

  /**
   * sets the name of the User.
   *
   * @param name the new name of the User
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * getter for the User's email.
   *
   * @return the User's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * sets the email of the User.
   *
   * @param email the new email of the User
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * getter for the User's password.
   *
   * @return the User's password
   */
  public String getPassword() {
    return password;
  }

  /**
   * sets the User's password.
   *
   * @param password the User's new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Overloaded for easier debugging
   *
   * @return
   */
  @Override
  public String toString() {
    return "[NAME: " + name + ", " + "EMAIL: " + email + "]";
  }

  /**
   * Override equals method to ensure matching is predictable.
   *
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return name.equals(user.name)
            && email.equals(user.email)
            && password.equals(user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password);
  }
}