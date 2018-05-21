package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.db().contacts().size() == 0) {
      NewContactData contact = new NewContactData().
              withFirst_name("1FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactModification() {
    Contacts before = app.db().contacts();
    NewContactData modifiedContact = before.iterator().next();

    NewContactData contact = new NewContactData().
            withFirst_name("1FirstName").withLast_name("2LastName").withCompany("Company").withAddress("Address").withId(modifiedContact.getId());
    app.goTo().gotoHomePage();
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    assertThat(app.contact().count(), Matchers.equalTo(before.size()));

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    veryfaiGroupListInUi();
  }
}
