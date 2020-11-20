package swapp.core;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserValidation {

  public final Pattern emailRegex = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
  public final Pattern usernameRegex = Pattern.compile("^([a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*){3,}$");
  public final Pattern passwordRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
  private final List<User> users;


  public UserValidation(List<User> users) {
    this.users = users;
  }

  /**
   * Checks if the user fields are valid
   *
   * @param name the username string to validate
   * @param email the email string to validate
   * @param pwd the password string to validate
   * @return {@code boolean} true if the user fields have been validated, false otherwise
   */
  public boolean validateUser(String name, String email, String pwd) {
    return (validUsername(name) && !nameInUse(name)) && (validEmail(email)
            && !emailInUse(email)) && validPassword(pwd);
  }

  /**
   * Checks if the specified name already exist
   * @param name the username string to validate
   * @return {@code boolean} true if a user with this name is found, false otherwise
   */
  public boolean nameInUse(String name) {
    return inUse(e -> e.getName().equalsIgnoreCase(name));
  }

  /**
   * Checks if the specified email is already in use
   * @param email the email string to validate
   * @return {@code boolean} true if the given email is in use
   */
  public boolean emailInUse(String email) {
    return inUse(e -> e.getEmail().equalsIgnoreCase(email));
  }

  /**
   * Check if a users attribute is already in use by use of a {@code Predicate<User>}
   * @param predicate predicate used for filtering results
   * @return {@code boolean} true if a users attribute matches the given predicate, false otherwise
   */
  public boolean inUse(Predicate<User> predicate) {
    return users.stream().filter(predicate).
            collect(Collectors.reducing((a,b) -> null)).isPresent();
  }

  /**
   * Checks if the given string is a valid username based on a regex pattern
   * @param name
   * @return true if the string matches the regex
   */
  public boolean validUsername(String name) {
    return validRegex(usernameRegex, name);
  }

  /**
   * Checks if the given string is a valid email based on a regex pattern
   * @param email
   * @return true if the string mathces the regex
   */
  public boolean validEmail(String email) {
    return validRegex(emailRegex, email);
  }

  /**
   * Checks if the given string is a valid password based on a regex pattern
   * @param pwd
   * @return ture if the string matches the regex
   */
  public boolean validPassword(String pwd) {
    return validRegex(passwordRegex, pwd);
  }

  /**
   * Helper function for matching a regex pattern with a string
   * @param pattern
   * @param str
   * @return true if the given string matches the given pattern
   */
  public boolean validRegex(Pattern pattern, String str) {
    return pattern.matcher(str).matches();
  }

}
