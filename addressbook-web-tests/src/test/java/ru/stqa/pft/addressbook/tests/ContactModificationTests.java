package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().all().size()==0) {
      app.contact().create();
      app.goTo().returnToHomePage();
    }
  }



  @Test
  public void testContactModification() {

    Contacts before = app.contact().all();
   // int index = before.size() - 1;
    NewContactData modifiedContact = before.iterator().next();

    NewContactData contact =
            //new NewContactData("1FirstName", "2LastName",
           // "Company", "Address", "home", "mobile", "work", "fax",
           // "e-mail", null, before.get(index).getId());

    new NewContactData().withFirst_name("1FirstName").withLast_name( "2LastName").withCompany("Company").withAddress("Address").withId(modifiedContact.getId());

    app.contact().modify( contact);
    app.goTo().returnToHomePage();

    Contacts after = app.contact().all();
    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedContact);
    before.add(contact);

   // Comparator<? super NewContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
   // before.sort(ById);
   // after.sort(ById);
   // Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }


}
