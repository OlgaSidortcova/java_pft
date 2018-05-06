package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreateTests extends TestBase {


  @Test(enabled = false)
  public void testNewContact1() {

    app.getContactHelper().initNewContactCreation();
    app.getContactHelper().fillNewContact(new NewContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", null), true);
    app.getContactHelper().submitNewContactCreation();
    app.goTo().addNextContact();

    app.getContactHelper().fillNewContact(new NewContactData("test1111111", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "text132"), true);
    app.getContactHelper().submitNewContactCreation();
    app.goTo().returnToHomePage();
  }

  @Test(enabled = false)
  public void testNewContact2() {
    app.goTo().gotoHomePage();
    List<NewContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initNewContactCreation();
    NewContactData contact = new NewContactData("1", "2", "3", "4", "5", "6", "7", "8", "9", null);

    app.getContactHelper().fillNewContact(contact, true);

    app.getContactHelper().submitNewContactCreation();
    app.goTo().returnToHomePage();


    List<NewContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(contact);

    Comparator<? super NewContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(ById);
    after.sort(ById);

    Assert.assertEquals(before, after);

  }
}


