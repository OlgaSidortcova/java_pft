package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().list().size() == 0) {
      app.contact().create();
      app.goTo().returnToHomePage();
    }
  }

  @Test// (enabled = false)
  public void testContactDelition() {

    Contacts before = app.contact().all();
    int index = before.size() - 1;
    NewContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().gotoHomePage();

    Contacts after = app.contact().all();

    Assert.assertEquals(before.size() - 1, after.size());

   // before.remove(deletedContact);
   // Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
