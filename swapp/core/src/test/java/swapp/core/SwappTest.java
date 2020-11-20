package swapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SwappTest {

    Swapp swapp;
    User user = new User("name", "ggg@gmail.com", "Password123");

    public void printInfo() {
        System.out.println("Test coverage report can be found in target/site/jacoco/index.html");
        System.out.println("These tests check that Swapp objects and their methods work as intented");
    }

    @BeforeEach
    public void setUpSwapp() {

        this.swapp = new Swapp();

    }

    @Test
    public void testAddandRemove() {
        assertTrue(this.swapp.add(user));
        assertFalse(this.swapp.add(user));
        assertTrue(this.swapp.remove(user));
        assertFalse(this.swapp.remove(user));
    }
    @Test
    public void testCreateUser() {
        assertFalse(this.swapp.createUser("name", "XD", "Password123"));
        assertTrue(this.swapp.createUser("name", "ggg@gmail.com", "Password123"));
    }
    @Test
    public void testGetUserLogin() {
        this.swapp.add(user);
        assertEquals(user, this.swapp.getUserLogin(user.getEmail(), user.getPassword()));

    }

    @Test
    public void getUser(){
        this.swapp.add(user);
        assert swapp.getUser("name").equals(user);
        assert swapp.getUser("ggg@gmail.com").equals(user);
        assert swapp.getUser("doesntExist") == (null);

        System.out.println(swapp.getUserLogin("ggg@gmail.com", "Password123"));
        assert swapp.getUserLogin("ggg@gmail.com", "Password123") == user;
    }

    @Test
    public void testAdListAndAccountList() {

        assert this.swapp.getAccounts().size() == 0;
        this.swapp.add(user);
        assert this.swapp.getAccounts().size() == 1;
        assert swapp.getAccounts().size() == swapp.getUserAmount();

        System.out.println(this.swapp.getAdList().getNumberOfAds());
        assert this.swapp.getAdList().getNumberOfAds() == 0;
        this.swapp.setCurrentUser(user);
        swapp.getCurrentUser().createAd("nepe", "Godt brukt", Ad.Category.BORROW);
        assert this.swapp.getAdList().getNumberOfAds() == 0;
        swapp.populateAdList();
        assert this.swapp.getAdList().getNumberOfAds() == 1;

        swapp.getCurrentUser().createAd("kål", "Pen", Ad.Category.GIFT);
        swapp.populateAdList();
        System.out.println(this.swapp.getAdList().getNumberOfAds());
        assert this.swapp.getAdList().getNumberOfAds() == 2;  //Uncomment later when adlist works
        swapp.getCurrentUser().getUserAds().get(1).setStatus(Ad.Status.COMPLETED);
        swapp.populateAdList();
        assert this.swapp.getAdList().getNumberOfAds() == 1;  // uncomment when adList works

    }
    @Test
    public void testTransactionAndAd(){
        this.swapp.createUser("name", "ggg@gmail.com", "Password123");
        swapp.getAccounts().get(0).createAd("tittel","body,", Ad.Category.BORROW);
        user.createTransaction(swapp.getAccounts().get(0).getUserAds().get(0),user);
        swapp.setCurrentUser(swapp.getAccounts().get(0));
        swapp.setTransactionStatus(new Transaction(swapp.getAccounts().get(0).getUserAds().get(0), user));
        swapp.setAdStatus(new Ad("tittel", swapp.getAccounts().get(0),
                "body,", Ad.Category.BORROW), Ad.Status.COMPLETED);
    }
}