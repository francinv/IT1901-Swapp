package swapp.core;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserValidation {

    public final Pattern emailRegex = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])");
    public final Pattern usernameRegex = Pattern.compile("^([a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*){3,}$");
    public final Pattern passwordRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    private final List<User> users;
//    private static final String NAME_IN_USE = "Name already in use!";
//    private static final String EMAIL_IN_USE = "Email already in use!";
//    private static final String INVALID_USERNAME = "Username must contain at least 3 characters and must end and start with an alphanumerical character";
//    private static final String INVALID_EMAIL = "Email is not valid, must be of format: name-part@domain, e.g. example@example.com";
//    private static final String INVALID_PASSWORD = "Password is not valid, must be at least 8 characters and contain at least 1 upper, and lowercase letter and at least 1 digit";
//    private final HashMap<String, Boolean> errors = new HashMap<>();

    public UserValidation(List<User> users) {
        this.users = users;
//        errors.put(NAME_IN_USE, null);
//        errors.put(EMAIL_IN_USE, null);
//        errors.put(INVALID_USERNAME, null);
//        errors.put(INVALID_EMAIL, null);
//        errors.put(INVALID_PASSWORD, null);
    }

//    /**
//     * Checks if the user fields are valid
//     * @param name the username string to validate
//     * @param email the email string to validate
//     * @param pwd the password string to validate
//     * @return {@code boolean} true if the user fields have been validated, false otherwise
//     */
//    public boolean validateUser(String name, String email, String pwd) {
//        if (validateUsername(name) && validateEmail(email) && validatePwd(pwd)) {
//            return true;
//        } return false;
//    }
//
//    /**
//     * Checks if the given username is valid and not in use
//     * @param name the username string to validate
//     * @return {@code boolean} true if username is valid and not in use
//     */
//    public boolean validateUsername(String name) {
//        if (!nameInUse(name)) {
//            if (validRegex(usernameRegex, name, INVALID_USERNAME)) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * Checks if the given email is valid and not in use
//     * @param email the email string to validate
//     * @return {@code boolean} true if email is not in use and matches regex
//     */
//    public boolean validateEmail(String email) {
//        if (!emailInUse(email)) {
//            if (validRegex(emailRegex, email, INVALID_EMAIL)) {
//                return true;
//            } return false;
//        } return false;
//    }
//
//    /**
//     * Checks if the given password is valid by using a predefined regex
//     * @param pwd the password string to validate
//     * @return {@code boolean} true if the password matches the regex
//     */
//    public boolean validatePwd(String pwd) {
//        if (validRegex(passwordRegex, pwd, INVALID_PASSWORD)) {
//            return true;
//        } return false;
//    }
//
//    /**
//     * Checks if the specified name already exist
//     * @param name the username string to validate
//     * @return {@code boolean} true if a user with this name is found, false otherwise
//     */
//    public boolean nameInUse(String name) {
//        boolean nameInUse = inUse((e) -> e.getName().equalsIgnoreCase(name));
//        if (nameInUse) {
//            return setTruthValue(NAME_IN_USE, true);
//        }
//        return setTruthValue(NAME_IN_USE, false);
//    }
//
//    /**
//     * Checks if the specified email is already in use
//     * @param email the email string to validate
//     * @return {@code boolean} true if the given email is in use
//     */
//    public boolean emailInUse(String email) {
//        boolean emailInUse = inUse((e) -> e.getEmail().equalsIgnoreCase(email));
//        if (emailInUse) {
//            return setTruthValue(EMAIL_IN_USE, true);
//        }
//        return setTruthValue(EMAIL_IN_USE, false);
//    }

    /**
     * Checks if the user fields are valid
     * @param name the username string to validate
     * @param email the email string to validate
     * @param pwd the password string to validate
     * @return {@code boolean} true if the user fields have been validated, false otherwise
     */
    public boolean validateUser(String name, String email, String pwd) {
        if ((validUsername(name) && !nameInUse(name)) && (validEmail(email) && !emailInUse(email)) && validPassword(pwd)) {
            return true;
        } return false;
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
        return users.stream().filter(predicate).collect(Collectors.reducing((a,b) -> null)).isPresent();
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

//    /**
//     * Checks if the given string matches the pattern and sets the given errors truth value to the return of this match
//     * @param pattern
//     * @param str
//     * @param error
//     * @return true if the given string matches pattern
//     */
//    private boolean validRegex(Pattern pattern, String str, String error) {
//        boolean patternMatch = pattern.matcher(str).matches();
//        return setTruthValue(error, patternMatch);
//    }
//
//    /**
//     * Sets the error strings corresponding truth value
//     * @param error
//     * @param bool
//     * @return bool
//     */
//    private boolean setTruthValue(final String error, boolean bool) {
//        errors.replace(error, bool);
//        return bool;
//    }
}
