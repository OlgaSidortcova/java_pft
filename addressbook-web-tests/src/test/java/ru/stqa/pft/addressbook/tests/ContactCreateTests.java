package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Set;

public class ContactCreateTests extends TestBase {

  @Test(enabled = false)
  public void testNewContact1() {

    app.contact().initNewContactCreation();
    app.contact().fillNewContact(new NewContactData().withFirst_name("test1").withLast_name("test2").withCompany("test3").withAddress("test4"), true);
    app.contact().submitNewContactCreation();
    app.goTo().addNextContact();

    app.contact().fillNewContact(new NewContactData().withFirst_name("test123").withLast_name("test2").withCompany("test3").withAddress("test4"), true);
    app.contact().submitNewContactCreation();
    app.goTo().returnToHomePage();
  }

  @Test //(enabled = false)
  public void testNewContact2() {
    app.goTo().gotoHomePage();
    Set<NewContactData> before = app.contact().all();

    app.contact().initNewContactCreation();
    NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").withAddress("4");

    app.contact().fillNewContact(contact, true);

    app.contact().submitNewContactCreation();
    app.goTo().returnToHomePage();


    Set<NewContactData> after = app.contact().all();
    Assert.assertEquals(before.size() + 1, after.size());

    contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt());

    before.add(contact);
    Assert.assertEquals(before, after);

  }
}


