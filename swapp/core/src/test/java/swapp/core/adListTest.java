package swapp.core;

import org.junit.jupiter.api.Test;

public class adListTest {
  AdList adList;

  @Test
  public void setUp() throws Exception{
    System.out.println("Test coverage report can be found in target/site/jacoco/index.html");

    this.adList = new AdList();
    createAd();
    removeAd();
  }

  @Test
  public void createAd() {
    try {
      int NumberofAds = adList.getNumberOfAds();
      adList.createAndAdd("nepe", "lars", "Godt brukt");
      adList.createAndAdd("koll", "ødipus", "brukt");
      adList.createAndAdd("kål", "Pante-per", "Pen");
      assert adList.getNumberOfAds() == NumberofAds+3;
      assert adList.getAd(NumberofAds).getAuthor().equals("lars");
      assert adList.getAd(NumberofAds).getTitle().equals("nepe");
      assert adList.getAd(NumberofAds).getTextBody().equals("Godt brukt");
      assert adList.getAd(NumberofAds+2).getAuthor().equals("Pante-per");
    }
    catch (Exception e) {
    }
  }

  @Test
  public void removeAd() {
    try {
      int NumberofAds = adList.getNumberOfAds();
      assert adList.getNumberOfAds() == NumberofAds;
      Ad ad = adList.getAd(0);
      adList.archiveAd(ad);
      assert adList.getNumberOfAds() == NumberofAds-1;

      //
    } catch (Exception e) {
    }
  }
}
