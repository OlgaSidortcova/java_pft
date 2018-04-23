package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initNewContactCreation();
      app.getContactHelper().fillNewContact(
              new NewContactData("test1", "test2",
                      "test3", "test4", "test5",
                      "test6", "test7", "test8", "test9", "text132"), true);


      app.getContactHelper().submitNewContactCreation();
      app.getNavigationHelper().returnToHomePage();
    }


    app.getContactHelper().selectContact();
   app.getContactHelper().editContact();

    app.getContactHelper().fillNewContact(new NewContactData("1FirstName", "2LastName", "Company", "Address", "home", "mobile", "work", "fax", "e-mail", null), false);

    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }
}
