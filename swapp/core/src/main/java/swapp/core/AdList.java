package swapp.core;

import java.util.ArrayList;

public class AdList {
    /**
     * The AdList contains all active Ad-instances and communicates with the GUI.
     */
    private ArrayList<Ad> ads;
    public AdList(){
        this.ads = new ArrayList<Ad>();
        populateAds();
    }
    // Create an Ad
    public void createAndAdd(String title, String author, String textBody){
        this.ads.add(new Ad(title, author, textBody));
    }

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
    // Temporary test method.
    public void populateAds(){
        ads.add(new Ad("Hoppeslott byttes mot en trampo", "henrik", "nybrukt"));
        ads.add(new Ad("Hoppeslott byttes mot en ", "henrk", "nyrukt"));
        ads.add(new Ad("fisk mot krabbe", "rambo", "brukt"));
        ads.add(new Ad("Seilskip fra 1769", "lars", "Godt brukt"));
        ads.add(new Ad("løk", "henrik", "ubrukt"));
        ads.add(new Ad("krabbe", "bø", "brukt"));
        ads.add(new Ad("nepe", "lars", "Godt brukt"));
    }

    public void archiveAd(Ad ad){
        ads.remove(ad);
        // TODO: Give the ad somwhere to display transaction history
    }

}
