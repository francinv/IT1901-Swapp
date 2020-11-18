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
}
