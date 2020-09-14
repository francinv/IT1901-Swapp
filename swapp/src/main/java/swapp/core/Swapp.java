package swapp.core;

import java.util.ArrayList;
import java.util.List;

public class Swapp {
	private List<User> accounts;
	
	public Swapp() {
		this.accounts = new ArrayList<>();
	}
	
	public void add(User user) {
		if (!accounts.contains(user)) {
			accounts.add(user);
		}
	}
	public void remove(User user) {
		if (accounts.contains(user)) {
			accounts.remove(user);
		}
	}
}