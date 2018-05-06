package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().list().size() == 0) {
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

    Set<NewContactData> before = app.contact().all();
    int index = before.size() - 1;
    NewContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHomePage();

    Set<NewContactData> after = app.contact().all();

    Assert.assertEquals(before.size() - 1, after.size());


    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
