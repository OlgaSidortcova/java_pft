package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.goTo().returnToHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {

    Set<NewContactData> before = app.contact().all();
    NewContactData modifiedContact = before.iterator().next();

    NewContactData contact = new NewContactData().
            withFirst_name("1FirstName").withLast_name("2LastName").withCompany("Company").withAddress("Address").withId(modifiedContact.getId());

    app.contact().modify(contact);
    app.goTo().returnToHomePage();

    Set<NewContactData> after = app.contact().all();
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedContact);
    before.add(contact);

    Assert.assertEquals(before, after);
  }


}
