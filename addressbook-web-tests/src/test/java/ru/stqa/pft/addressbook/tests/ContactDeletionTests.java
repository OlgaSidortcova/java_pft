package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDelition() {
    app.getNavigationHelper().gotoHomePage();

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().initNewContactCreation();
      app.getContactHelper().fillNewContact(
              new NewContactData("test1", "test2",
                      "test3", "test4", "test5",
                      "test6", "test7", "test8", "test9", "text132"), true);

      app.getContactHelper().submitNewContactCreation();
      app.getNavigationHelper().returnToHomePage();
    }

    List<NewContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);

    app.getContactHelper().deleteSelectedContacts();

    app.getContactHelper().submitDeletionContact();

    app.getNavigationHelper().gotoHomePage();

    List<NewContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);


  }
}
