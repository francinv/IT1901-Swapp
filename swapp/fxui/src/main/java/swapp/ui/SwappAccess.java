package swapp.ui;

import swapp.core.Ad;
import swapp.core.AdList;
import swapp.core.User;
import swapp.core.UserValidation;
import java.util.List;

public interface SwappAccess {

    User getUser(String name);

    Ad getAd(String name, String title);

    boolean validUser(String name, String email, String password); //??? nødvendig?

    void addUser(String name, String email, String password);

    void addAd(String title, String author, String textBody, Ad.Category category);

    void changeAdStatus(Ad ad, Ad.Status status);

    UserValidation getUserValidation();

    User getUserLogin(String email, String password);

    void setCurrentUser(User user);

    List<User> getAccounts();

    AdList getAdList();
}
