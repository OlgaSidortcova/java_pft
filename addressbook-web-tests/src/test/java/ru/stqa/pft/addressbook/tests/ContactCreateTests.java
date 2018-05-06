package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreateTests extends TestBase {




  @Test(enabled = false)
  public void testNewContact1() {

    app.contact().initNewContactCreation();
    app.contact().fillNewContact(new NewContactData().withFirst_name("test1").withLast_name( "test2").withCompany("test3").withAddress("test4"), true);
    app.contact().submitNewContactCreation();
    app.goTo().addNextContact();

    app.contact().fillNewContact(new NewContactData().withFirst_name("test123").withLast_name( "test2").withCompany("test3").withAddress("test4"), true);
    app.contact().submitNewContactCreation();
    app.goTo().returnToHomePage();
  }

  @Test
  public void testNewContact2() {
    app.goTo().gotoHomePage();
    List<NewContactData> before = app.contact().list();

    app.contact().initNewContactCreation();
    NewContactData contact = new NewContactData().withFirst_name("1").withLast_name( "2").withCompany("3").withAddress("4");

    app.contact().fillNewContact(contact, true);

    app.contact().submitNewContactCreation();
    app.goTo().returnToHomePage();


    List<NewContactData> after = app.contact().list();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);

    Comparator<? super NewContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(ById);
    after.sort(ById);

    Assert.assertEquals(before, after);

  }
}


