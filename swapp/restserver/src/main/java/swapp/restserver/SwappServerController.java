package swapp.restserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import swapp.core.Ad;
import swapp.core.Swapp;
import swapp.core.User;

@RestController
@RequestMapping(SwappServerController.SWAPP_SERVER_SERVICE_PATH)
public class SwappServerController {

  public static final String SWAPP_SERVER_SERVICE_PATH = "swapp";

  @Autowired
  private SwappService swappService;

  @GetMapping
  public Swapp getSwapp() {
    return swappService.getSwapp();
  }

  private void checkUser(User user, String name) {
    if (user == null) {
      throw new IllegalArgumentException("No user with the name: " + name);
    }
  }

  private void checkAd(String title) {
    if (getSwapp().getCurrentUser().getAd(title) == null) {
      throw new IllegalArgumentException("No ad exists with title: " + title);
    }
  }

  @PutMapping(value = "/login", consumes = "application/json", produces = "application/json")
  public boolean loginUser(@RequestBody String details) {
    String[] loginArray = details.split("-");
    User user = getSwapp().getUserLogin(loginArray[0], loginArray[1]);
    if (user != null) {
      getSwapp().setCurrentUser(user);
      return true;
    }
    return false;
  }

  @GetMapping(path = "/user/{name}")
  public User getUser(@PathVariable("name") String name) {
    User user = getSwapp().getUser(name);
    checkUser(user, name);
    return user;
  }

  @PutMapping(path = "/status/{ad}/{status}")
  public boolean changeAdStatus(@PathVariable("ad") Ad ad, @PathVariable("status") String status) {
    return getSwapp().setAdStatus(ad, Ad.Status.valueOf(status));
  }

  @PostMapping(value = "/adduser", consumes = "application/json", produces = "application/json")
  public boolean createUser(@RequestBody User user) {
    return getSwapp().add(user);
  }

  @PostMapping(value = "/createad", consumes = "application/json",
               produces = "application/json")
  public boolean createAd(@RequestBody String ad) {
    String[] adArgs = ad.split("-");
    getSwapp().createAd(adArgs[0], adArgs[1], Ad.Category.valueOf(adArgs[2]));
    checkAd(adArgs[0]);
    return true;
  }

}
