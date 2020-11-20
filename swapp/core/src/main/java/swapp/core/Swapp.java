package swapp.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Swapp handles a List of users, who is currently logged in, user creation and validation, which ads
 * to display (adList) etc.
 * Interaction with GUI-module happens through Swapp.
 */
public class Swapp implements IObservable<Swapp> {

  private List<User> accounts;
  private User currentUser;
  private UserValidation userValidation;
  private AdList adList;
  private List<Transaction> transactionList;
  private List<IObserver<Swapp>> observers = new ArrayList<>();


  public Swapp() {
    this.accounts = new ArrayList<>();
    this.userValidation = new UserValidation(this.accounts);
    this.adList = new AdList();
    this.transactionList= new ArrayList<>();
  }

  //TODO Implementer notifyObserver() i de riktige metodene

  /**
   * Adds a User to the list of users.
   * @param user the User to add
   */
  public boolean add(User user) {
    if (!accounts.contains(user)) {
      accounts.add(user);
      notifyObservers(this);
      return true;
    }
    return false;
  }

  /**
   * Removes a User from the list of users.
   * @param user the User that is to be removed
   */
  public boolean remove(User user) {
    boolean bool = accounts.remove(user);
    notifyObservers(this);
    return bool;
  }

  /**
   * Sets the Swapp object's currentUser. A way of logging in a User object.
   * @param user the User which is to be the current user
   */
  public void setCurrentUser(User user) {
    this.currentUser = user;
  }

  /**
   * Getter for currentUser.
   * @return the Swapp object's current logged in user
   */
  public User getCurrentUser() {
    return this.currentUser;
  }

  /**
   * Getter for the list of users.
   * @return the list of users
   */
  public List<User> getAccounts() {
    return accounts;
  }

  /**
   * Returns the number of users registered.
   * @return the size of the list of users
   */
  public int getUserAmount() {
    return accounts.size();
  }

  /**
   * Returns an instance of UserValidation
   * @return UserValidation
   */
  public UserValidation getUserValidation() {
    return this.userValidation;
  }

  /**
   * Verifies that a user with the specified name, email and password can be created
   * @param name
   * @param email
   * @param password
   * @return true if the user is successfully validated and added to this swapp, false otherwise
   */
  public boolean createUser(String name, String email, String password) {
    if (this.userValidation.validateUser(name, email, password)) {
      User user = new User(name, email, password);
      this.add(user);
      notifyObservers(this);
      return true;
    }
    return false;
  }

  /**
   * Creates a new Ad with Author as the currently logged in user.
   * @param title
   * @param textbody
   * @param category
   */
  public void createAd(String title, String textbody, Ad.Category category) {
    System.out.print("hei  ");
    System.out.println(getCurrentUser());
    getCurrentUser().createAd(title, textbody, category);
    populateAdList();
    notifyObservers(this);
  }

  public void createTransaction(Ad ad, User requester){
    User user = ad.getAuthor();
    this.getUser(user.getName()).createTransaction(ad, requester);
    //notifyObservers(this);
  }

  /**
   * Get the current logged in user
   * @param email
   * @param password
   * @return the logged in user with the specified login details, if no such user is found null is returned
   */
  public User getUserLogin(String email, String password) {
    for (User user : accounts) {
      if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Get a user by either username or email
   * @param string
   * @return user with the matching username or email, null if no such user is found
   */
  public User getUser(String string) {
    Optional<User> optionalUser = this.accounts.stream().filter(getUserPredicate(string)).collect(Collectors.reducing((a, b) -> null));
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    } else {
      return null;
    }
  }

  /**
   * This method is used to remove ads from displaying in the GUI by changig the status.
   * @param ad
   * @param status
   * @return true if the method finds the correct Ad
   */
  public Boolean setAdStatus(Ad ad, Ad.Status status){
    User user = this.getUser(ad.getAuthor().getName());
    for (Ad swappAd: user.getUserAds()){
      if (swappAd.equals(ad)){
        swappAd.setStatus(status);
        notifyObservers(this);
        return true;
      }
    }
    System.out.println("this shouldnt happen");
    return false;
  }

  /**
   * Getter for adList
   * @return returns adList
   */
  public AdList getAdList() {
    return adList;
  }

  public List<Transaction> getTransactionList(){
    return transactionList;
  }

  public Boolean setTransactionStatus(Transaction transaction){
    User user = this.getUser(transaction.getReceiver().getName());
    for (Transaction swappTransaction: user.getUserTransactions()){
      if (swappTransaction.equals(transaction)){
        swappTransaction.accepted();
        return true;
      }
    }
    System.out.println("this shouldnt happen");
    return false;
  }

  /**
   * Empties the adList and then gets all ads from each user to AdList. adList now contains all ads that
   * should be displayed. This must be called when changes happen that warrant an updated listView of ads.
   */
  public void populateAdList(){ // gets all ads from all users from swapp and append to Adlist

    this.adList = new AdList();
    List<User> accounts = this.getAccounts();
    for (User user: accounts){
      for (Ad ad: user.getUserAds()){
        if (ad.getStatus().equals(Ad.Status.ACTIVE)){
          adList.add(ad);
        }
        else{
          System.out.println(ad.getStatus());
        }
      }
    }

  }
  // DELETEXX
  public void populatetransactionList(){

    this.transactionList = new ArrayList<>();
    List<User> accounts = this.getAccounts();
    for (User user: accounts){
      for (Transaction transaction: user.getUserTransactions()){
        if (transaction.getStatus().equals(Transaction.Status.ONGOING)){
          transactionList.add(transaction);
        }
        else{
          System.out.println(transaction.getStatus());
        }
      }
    }

  }

  /**
   * A predicate for deciding whether or not the given string is an email or username
   * @param string
   * @return Predicate<User> that contains a predicate based on either a users name or email
   */
  private Predicate<User> getUserPredicate(String string)	{
    if (userValidation.validUsername(string)) {
      return user -> user.getName().equalsIgnoreCase(string);
    }
    if (userValidation.validEmail(string)) {
      return user -> user.getEmail().equalsIgnoreCase(string);
    }
    return null;
  }

  @Override
  public void addObserver(IObserver<Swapp> observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(IObserver<Swapp> observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers(Swapp swapp) {
    observers.forEach(o -> o.notify(swapp));
  }
}