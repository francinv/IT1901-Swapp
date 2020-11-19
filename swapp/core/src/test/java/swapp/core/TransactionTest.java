package swapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TransactionTest {
  Ad ad1;
  Ad ad2;
  User user1;
  User user2;
  Transaction tran1;
  Transaction tran2;
  @BeforeEach
  public void setUp() throws Exception{
    user1 = new User("tester", "gabb@gmail.com", "Password123");
    user2 = new User("name", "birk@gmail.com", "Password123");
    ad1 = new Ad("nepe", user1, "Godt brukt", Ad.Category.BORROW);
    ad2 = new Ad("kål", user2, "Pen", Ad.Category.GIFT);
    tran1= new Transaction(ad1,user2);
    tran2 = new Transaction(ad2,user1);
  }

  @Test
  public void testGetters() {
    assert tran1.getReceiver().equals(user1);
    assert tran1.getRequester().equals(user2);
    assert tran1.getStatus().equals(Transaction.Status.ONGOING);
  }
  @Test
  public void testToString(){
    assert tran1.toString().equals("nepe(requested by [NAME: name, EMAIL: birk@gmail.com])");
  }

  @Test
  public void equality(){
    assert ! tran1.equals(tran2);
    assert  tran1.equals(tran1);
    assert tran1.equals(new Transaction(ad1,user2));
  }
}
