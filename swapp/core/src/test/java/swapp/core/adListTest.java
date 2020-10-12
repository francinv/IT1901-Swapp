package swapp.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class adListTest {
  AdList adList;

  @BeforeEach
  public void setUp() throws Exception{
    System.out.println("Test coverage report can be found in target/site/jacoco/index.html");

    this.adList = new AdList();
  }

  @Test
  public void testCreateAd() {
      System.out.println("HEHEHE ");
      int numberOfAds = adList.getNumberOfAds();

      adList.createAndAdd("nepe", "lars", "Godt brukt");
      adList.createAndAdd("koll", "ødipus", "brukt");
      adList.createAndAdd("kål", "Pante-per", "Pen");
      assert adList.getNumberOfAds() == numberOfAds+3;

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

  }
}
