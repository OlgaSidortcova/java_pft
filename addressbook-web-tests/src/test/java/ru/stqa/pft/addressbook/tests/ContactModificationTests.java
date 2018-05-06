package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
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
    app.getContactHelper().editContact(before.size() - 1);

    NewContactData contact = new NewContactData("1FirstName", "2LastName",
            "Company", "Address", "home", "mobile", "work", "fax",
            "e-mail", null, before.get(before.size() - 1).getId());

    app.getContactHelper().fillNewContact(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();

    List<NewContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super NewContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }
}
