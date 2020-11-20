package swapp.ui;

import java.util.List;
import swapp.core.*;

public interface SwappAccess {

  User getUser(String name);

  Ad getAd(String name, String title);

  boolean validUser(String name, String email, String password); //??? nødvendig?

  void createUser(String name, String email, String password);

  void createAd(String title, String textBody, Ad.Category category);


  UserValidation getUserValidation();

  User getUserLogin(String email, String password);

  void setCurrentUser(User user);

  User getCurrentUser();

  List<User> getAccounts();

  AdList getAdList();

  void populateAdList();

  void createTransaction(Ad ad, User requester);

  Boolean changeAdStatus(Ad ad, Ad.Status status);

  Boolean setTransactionStatus(Transaction transaction);
}
