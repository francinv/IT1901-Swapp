package swapp.core;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class adListTest {

  AdList adList;
  User user1;
  User user2;
  //Test coverage report can be found in target/site/jacoco/index.html
  @BeforeEach
  public void setUp() throws Exception{

    user1 = new User("tester", "gabb@gmail.com", "Password123");
    user2 = new User("name", "birk@gmail.com", "Password123");
    this.adList = new AdList();

    int numberOfAds = adList.getNumberOfAds();
    adList.add(new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW));
    TimeUnit.MILLISECONDS.sleep(1);
    adList.add(new Ad("kål", user2, "Pen", Ad.Category.BORROW));
    TimeUnit.MILLISECONDS.sleep(2);
    adList.add(new Ad("ergonomisk stol", user2, "brukt", Ad.Category.GIFT));
    TimeUnit.MILLISECONDS.sleep(1);
    adList.add(new Ad("ubåt til låns", user2, "brukt", Ad.Category.BORROW));
    TimeUnit.MILLISECONDS.sleep(2);
    adList.add(new Ad("gir ski", user1, "brukt", Ad.Category.TRADE));
    TimeUnit.MILLISECONDS.sleep(4);
    adList.add(new Ad("aaaaaaa", user1, "brukt", Ad.Category.GIFT));

    assert adList.getNumberOfAds() == numberOfAds + 6;
  }

  @Test
  public void testCreateAd() {
    assert adList.getAd(0).getAuthor().equals(user1);
    assert adList.getAd(0).getTitle().equals("nepe");
    assert adList.getAd(0).getTextBody().equals("Godt brukt");
    assert adList.getAd(2).getAuthor().equals(user2);

    assert adList.getAd(2).getStatus().equals(Ad.Status.ACTIVE);
    assert adList.getAd(2).getCategory().equals(Ad.Category.GIFT);
  }

  @Test
  public void testSorting(){
    adList.sortBy("title");
    assert adList.getAd(0).getTitle().equals("aaaaaaa");
    assert adList.getAd(adList.getNumberOfAds()-1).getTitle().equals("ubåt til låns");

    adList.sortBy("author");
    assert adList.getAd(0).getAuthor().equals(user2);
    assert adList.getAd(adList.getNumberOfAds()-1).getAuthor().equals(user1);


    adList.sortBy("new");
    assert adList.getAd(0).getTime()>adList.getAd(adList.getNumberOfAds()-1).getTime();
    assert adList.getAd(0).getTime()>adList.getAd(1).getTime();

    adList.sortBy("old");
    assert adList.getAd(0).getTime()<adList.getAd(adList.getNumberOfAds()-1).getTime();
    assert adList.getAd(0).getTime()<adList.getAd(1).getTime();

  }

  @Test
  public void testFilteringByBorrow(){
    assert adList.getNumberOfAds() == 6;
    adList.filterByCategory(Ad.Category.BORROW);
    assert adList.getNumberOfAds() == 3;
    assert adList.getAd(0).getCategory()==Ad.Category.BORROW;
  }

  @Test
  public void testFilteringByGift(){
    assert adList.getNumberOfAds() == 6;
    adList.filterByCategory(Ad.Category.GIFT);
    assert adList.getNumberOfAds() == 2;
    assert adList.getAd(0).getCategory()==Ad.Category.GIFT;
  }

  @Test
  public void testFilteringByTrade(){
    assert adList.getNumberOfAds() == 6;
    adList.filterByCategory(Ad.Category.TRADE);
    assert adList.getNumberOfAds() == 1;
  }



}
