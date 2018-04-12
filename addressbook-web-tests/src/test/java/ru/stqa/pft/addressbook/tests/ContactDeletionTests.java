package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDelition() {
    app.getNavigationHelper().gotoHomePage();

    app.getContactHelper().selectContact();

    app.getContactHelper().deleteSelectedContacts();

    app.getContactHelper().submitDeletionContact();

    app.getNavigationHelper().gotoHomePage();

  }
}
