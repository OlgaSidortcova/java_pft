package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().list().size()==0) {
     /* app.contact().initNewContactCreation();
      app.contact().fillNewContact(
              new NewContactData("test1", "test2",
                      "test3", "test4", "test5",
                      "test6", "test7", "test8", "test9", "text132"), true);

      app.contact().submitNewContactCreation();
      */
      app.contact().create();
      app.goTo().returnToHomePage();
    }
  }
  @Test
  public void testContactDelition() {

    List<NewContactData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().delete(index);
    app.goTo().gotoHomePage();

    List<NewContactData> after = app.contact().list();

    Assert.assertEquals(before.size()-1, after.size());

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
