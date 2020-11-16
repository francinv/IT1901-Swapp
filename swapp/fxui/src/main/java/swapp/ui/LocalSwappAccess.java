package swapp.ui;

import swapp.core.*;
import swapp.json.SwappStorage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class LocalSwappAccess implements SwappAccess, IObserver<Swapp> {

    private final Swapp swapp;
    private SwappStorage storage;
    private String pathToSwapp;

    public LocalSwappAccess(Swapp swapp) {
        this.swapp = swapp;
        this.swapp.addObserver(this);
    }

    @Override
    public User getUser(String name) {
        return swapp.getUser(name);
    }

    @Override
    public Ad getAd(String name, String title) {
        //return swapp.getUser(name).getAd(title);
        return null;
    }

    @Override
    public boolean validUser(String name, String email, String password) {
        return swapp.getUserValidation().validateUser(name, email, password);
    }

    @Override
    public void addUser(String name, String email, String password) {
        swapp.createUser(name, email, password);
    }

    @Override
    public void addAd(String title, String author, String textBody, Ad.Category category) {
        swapp.getUser(author).createAd(title, textBody, category);
    }

    @Override
    public void changeAdStatus(Ad ad, Ad.Status status) {
        //swapp.getAdList().getAd(ad).setStatus(status);
    }

    @Override
    public UserValidation getUserValidation() {
        return swapp.getUserValidation();
    }

    @Override
    public User getUserLogin(String email, String password) {
        return swapp.getUserLogin(email, password);
    }

    @Override
    public void setCurrentUser(User user) {
        swapp.setCurrentUser(user);
    }

    @Override
    public List<User> getAccounts() {
        return swapp.getAccounts();
    }

    @Override
    public AdList getAdList() {
        return swapp.getAdList();
    }


    private void saveSwapp() {
        storage = new SwappStorage();
        try (FileWriter writer = new FileWriter(Paths.get(System.getProperty("user.dir"), pathToSwapp).toFile())) {
            storage.write(swapp, writer);
        } catch (IOException e) {
            System.err.println("Unable to save SwApp instance to file");
        }
    }

    public void setPath(String path) {
        this.pathToSwapp = path;
    }

    @Override
    public void notify(Swapp obj) {
        saveSwapp();
    }
}
