package swapp.core;

public class User {
	private String name;
	private String email;
	private String password;
	
	
	public User(String name, String email, String password) {
        validName(name);
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
        validName(name);
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
    }

    private void validName(String name) {
        if (name.length() > 128) {
            throw new IllegalArgumentException("name too long");
        }
        char[] characters = name.toCharArray();
        for (char c : characters) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("name can only contain letters");
            }
        }
    }
    
    @Override
	public String toString() {
    	return "[NAME: " + name + ", " + "EMAIL: " + email + "]";
    }
}
	