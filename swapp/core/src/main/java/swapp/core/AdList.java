package swapp.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdList implements Cloneable{
  /**
   * The AdList contains all active Ad-instances and communicates with the GUI.
   */
  private ArrayList<Ad> ads;
  public AdList(){
    this.ads = new ArrayList<Ad>();
  }
  // Create an Ad
    /*
    public void createAndAdd(String title, String author, String textBody){
        this.ads.add(new Ad(title, author, textBody));
    }*/
  public void add(Ad ad){
    this.ads.add(ad);
  }
  // TODO: Get users
  /**
   *
   * @return size of adList/number of active Ads.
   */
  public int getNumberOfAds(){
    return this.ads.size();
  }
  public Ad getAd(int i){
    return ads.get(i);
  }

  public List<Ad> getList() {
    return new ArrayList<>(ads);
  }

  public void archiveAd(Ad ad){
    ads.remove(ad);
    // TODO: Give the ad somwhere to display transaction history
  }


  public ArrayList<Ad> reverse(){ // tested, works
    ArrayList<Ad> arrList = this.ads;
    Collections.reverse(arrList);
    return arrList;
  }

  public List<Ad> filterByCategory(Enum s){ // s = "borrow" | "switch" | "gift"
    this.ads = (ArrayList<Ad>) this.ads.stream().filter(c -> c.getCategory().equals(s) ).collect(Collectors.toList());
    return ads;
  }

  public ArrayList<Ad> sortBy(String mode){ // mode = "title" | "author"
    ArrayList<Ad> arrList = this.ads;
    if (mode.equals("title")){
      arrList.sort((o1, o2) -> o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase()));
    }
    else if (mode.equals("author")){
      arrList.sort((o1, o2) -> o1.getAuthor().getName().toLowerCase().
              compareTo(o2.getAuthor().getName().toLowerCase()));
    }
    else if (mode.equals("old")) arrList.sort(Comparator.comparingLong(Ad::getTime));
    else if (mode.equals("new")) {
      arrList.sort(Comparator.comparingLong(Ad::getTime));
      this.reverse();
    }

    return arrList;
  }

  // Dette er svært ineffektivt men en enkel løsning for når alle annonser skal vises, tidligere ville adlist-
  // objektet knyttet til Swapp bli hentet ut direkte for å så bli endret på, selv om den ikke skal endres bortsett
  // fra når noen legger til en ny annonse. Da var det å lage en kopi av objektet en enkel måte å gjøre det på for da
  // får man de objektene som en trenger og vil da ha muligheten til å endre på listen slik som en selv vil uten
  // å være redd for å endre på den underliggende listen
  @Override
  protected AdList clone() {
    AdList clone = null;
    try {
      clone = (AdList) super.clone();
      clone.ads = new ArrayList<>(ads); // deep copy slik at vi ikke endrer på det opprinnelige objektet
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return clone;
  }

}
