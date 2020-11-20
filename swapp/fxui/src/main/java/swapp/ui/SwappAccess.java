package swapp.ui;

import swapp.core.*;

public interface SwappAccess {

  User getUser(String name);

  void createUser(String name, String email, String password);

  void createAd(String title, String textBody, Ad.Category category);

  UserValidation getUserValidation();

  boolean loginUser(String email, String password);

  void setCurrentUser(User user);

  User getCurrentUser();

  AdList getAdList();

  void populateAdList();

  void createTransaction(Ad ad, User requester);

  Boolean changeAdStatus(Ad ad, Ad.Status status);

  Boolean setTransactionStatus(Transaction transaction);
}
