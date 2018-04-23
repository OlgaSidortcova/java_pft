package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().editContact();

    app.getContactHelper().fillNewContact(new NewContactData("1FirstName", "2LastName", "Company", "Address", "home", "mobile", "work", "fax", "e-mail", null), false);


    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
