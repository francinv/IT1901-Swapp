package swapp.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class adListTest {
  AdList adList;
  Swapp swapp;
  User user1;
  User user2;

  @BeforeEach
  public void setUp() throws Exception{
    System.out.println("Test coverage report can be found in target/site/jacoco/index.html");
    user1 = new User("tester", "gabb@gmail.com", "Password123");
    user2 = new User("name", "birk@gmail.com", "Password123");
    this.adList = new AdList();

    int numberOfAds = adList.getNumberOfAds();
    adList.add(new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW));
    adList.add(new Ad("kål", user2, "Pen", Ad.Category.BORROW));
    adList.add(new Ad("ergonomisk stol", user2, "brukt", Ad.Category.GIFT));
    adList.add(new Ad("ubåt til låns", user2, "brukt", Ad.Category.BORROW));
    adList.add(new Ad("gir ski", user1, "brukt", Ad.Category.GIFT));
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
    //assert adList.getAd(0).getTime()>adList.getAd(adList.getNumberOfAds()-1).getTime();

  }

      /*



      assert adList.getAd(numberOfAds).getAuthor().equals("lars");
      assert adList.getAd(numberOfAds).getTitle().equals("nepe");
      assert adList.getAd(numberOfAds).getTextBody().equals("Godt brukt");
      assert adList.getAd(numberOfAds+2).getAuthor().equals("Pante-per");

  }

  @Test
  public void testRemoveAd() {

      int NumberofAds = adList.getNumberOfAds();
      assert adList.getNumberOfAds() == NumberofAds;
      Ad ad = adList.getAd(0);
      adList.archiveAd(ad);
      assert adList.getNumberOfAds() == NumberofAds-1;

  */


}
