package swapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {
  Message message;
  @BeforeEach
  public void setUp() throws Exception{
    message = new Message(new Ad("title","henrik","textbody")
            , new User("name", "email", "password"), "Lyst til å bytte?");
  }

  @Test
  public void testCreateMessage() {
    assert message.getListOfStringMessages().get(0).equals( "Lyst til å bytte?");
    message.sendMessage(null, "Kult");/**/
    assert message.getListOfStringMessages().get(1).equals( "Kult");
  }

}
