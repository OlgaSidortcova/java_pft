package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

public class NewContactCreateTests extends TestBase{


    @Test
    public void testNewContact1() {

      app.getContactHelper().initNewContactCreation();
      app.getContactHelper().fillNewContact(new NewContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"));
      app.getContactHelper().submitNewContactCreation();
      app.getNavigationHelper().addNextContact();

      app.getContactHelper().fillNewContact(new NewContactData("test1111111", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"));
      app.getContactHelper().submitNewContactCreation();

      app.getNavigationHelper().returnToHomePage();

    }


    @Test
    public void testNewContact2() {

      app.getContactHelper().initNewContactCreation();
      app.getContactHelper().fillNewContact(new NewContactData("FirstName", "LastName", "Company", "Address", "home", "mobile", "work", "fax", "e-mail"));

      app.getContactHelper().submitNewContactCreation();
      app.getNavigationHelper().returnToHomePage();

    }


   
  }


