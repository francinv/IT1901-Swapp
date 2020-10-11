package swapp.core;

import java.util.ArrayList;
import java.util.List;

public class Swapp {

	private List<User> accounts;
	private User currentUser;
	private UserValidation userValidation;

	public Swapp() {
		this.accounts = new ArrayList<>();
		this.userValidation = new UserValidation(this.accounts);
	}

	/**
	 * Adds a User to the list of users.
	 * @param user the User to add
	 */
	public boolean add(User user) {
		if (!accounts.contains(user)) {
			return accounts.add(user);
		}
		return false;
	}

	/**
	 * Removes a User from the list of users.
	 * @param user the User that is to be removed
	 */
	public boolean remove(User user) {
		return accounts.remove(user);
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

	public UserValidation getUserValidation() {
		return this.userValidation;
	}

	public boolean createUser(String name, String email, String password) {
		if (this.userValidation.validateUser(name, email, password)) {
			User user = new User(name, email, password);
			return this.add(user);
		}
		return false;
	}

	public User getUserLogin(String email, String password) {
		for (User user : accounts) {
			if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}