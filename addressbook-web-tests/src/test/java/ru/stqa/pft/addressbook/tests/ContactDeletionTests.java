package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().list().size() == 0) {
      NewContactData contact = new NewContactData().
              withFirst_name("1FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactDelition() {

    Contacts before = app.contact().all();

    NewContactData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);
    app.goTo().gotoHomePage();

    assertThat(app.contact().count(), Matchers.equalTo(before.size() - 1));
    Contacts after = app.contact().all();

    //  Assert.assertEquals(before.size() - 1, after.size());

    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
