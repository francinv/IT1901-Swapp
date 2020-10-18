package swapp.core;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String email;
	private String password;
	private List<Ad> userAds;//

	public User(String name, String email, String password) {
		this.name = name.toLowerCase();
		this.email = email;
		this.password = password;
		this.userAds = new ArrayList<>();//
	}

	public void createAd(String title, String textBody){
		this.userAds.add(new Ad(title, this, textBody));//
	}
	public List<swapp.core.Ad> getUserAds(){
		return this.userAds;
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
	 * @param name the new name of the User
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for the User's email.
	 * @return the User's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets the email of the User.
	 * @param email the new email of the User
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getter for the User's password.
	 * @return the User's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the User's password.
	 * @param password the User's new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[NAME: " + name + ", " + "EMAIL: " + email + "]";
	}
}