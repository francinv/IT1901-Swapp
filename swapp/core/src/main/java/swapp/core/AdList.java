package swapp.core;

import java.util.ArrayList;

public class AdList {
    private ArrayList<Ad> ads;
    public AdList(){
        System.out.println("jaja");
        this.ads = new ArrayList<Ad>();
        populateAds();
        System.out.println(this.getAd(0));
    }
    public void createAndAdd(String title, String author, String textBody){
        this.ads.add(new Ad(title, author, textBody));
    }
    public int getNumberOfAds(){
        return this.ads.size();
    }
    public Ad getAd(int i){
        System.out.println("heh "+ads.get(i));
        return ads.get(i);
        /*
        if (this.ads.size()> i || i<0){
            System.out.println("Out of range");
            return null;
        }
        else{
            return ads.get(i);
        }*/
    }

    public void populateAds(){
        ads.add(new Ad("Hoppeslott byttes mot en trampo", "henrik", "nybrukt"));
        ads.add(new Ad("Hoppeslott byttes mot en ", "henrk", "nyrukt"));
        ads.add(new Ad("fisk mot krabbe", "rambo", "brukt"));
        ads.add(new Ad("Seilskip fra 1769", "lars", "Godt brukt"));
        ads.add(new Ad("løk", "henrik", "ubrukt"));
        ads.add(new Ad("krabbe", "bø", "brukt"));
        ads.add(new Ad("nepe", "lars", "Godt brukt"));
    }

}
