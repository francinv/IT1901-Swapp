package swapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.TimeUnit;


public class AdTest {
  Ad ad1;
  Ad ad2;
  User user1;
  User user2;
  //Test coverage report can be found in target/site/jacoco/index.html
  @BeforeEach
  public void setUp() throws Exception{

    user1 = new User("tester", "gabb@gmail.com", "Password123");
    user2 = new User("name", "birk@gmail.com", "Password123");
    ad1 = new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW);
    ad2 = new Ad("kål", user2, "Pen", Ad.Category.GIFT);
    /*
    new Ad("ergonomisk stol", user2, "brukt", Ad.Category.GIFT));
    new Ad("ubåt til låns", user2, "brukt", Ad.Category.BORROW));
    new Ad("gir ski", user1, "brukt", Ad.Category.TRADE));
    new Ad("aaaaaaa", user1, "brukt", Ad.Category.GIFT));*/
  }

  @Test
  public void testGetters() {
    assert ad1.getAuthor().equals(user1);
    assert ad1.getTitle().equals("nepe");
    assert ad1.getTextBody().equals("Godt brukt");

    assert ad2.getAuthor().equals(user2);
    assert ad2.getStatus().equals(Ad.Status.ACTIVE);
    assert ad2.getCategory().equals(Ad.Category.GIFT);
  }
  @Test
  public void testToString(){
    assert ad1.toString().equals("nepe (annonsert av tester)");
    assert ad2.toString().equals("kål (annonsert av name)");
    assert ! ad1.toString().equals("kål (annonsert av name)");
  }

  @Test
  public void equality() throws InterruptedException {
    assert ! ad1.equals(ad2);
    assert  ad1.equals(ad1);
    Ad ad1Similar = new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW, Ad.Status.ACTIVE, ad1.getTime());
    assert ad1Similar.equals(ad1);
    TimeUnit.MILLISECONDS.sleep(1);
    Ad ad1SimilarButNewTime = new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW);
    assert ! ad1SimilarButNewTime.equals(ad1);

    assert ad1.hashCode() != ad2.hashCode();
    assert ad1.hashCode() == ad1.hashCode();
    assert ad1.hashCode() == ad1Similar.hashCode();
    assert ad1.hashCode() != ad1SimilarButNewTime.hashCode();

  }



}