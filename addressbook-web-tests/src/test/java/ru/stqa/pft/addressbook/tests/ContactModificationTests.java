package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();
    if (app.contact().list().size()==0) {
      app.contact().create();
      app.goTo().returnToHomePage();
    }
  }



  @Test
  public void testContactModification() {

    List<NewContactData> before = app.contact().list();
    int index = before.size() - 1;
    NewContactData contact =
            //new NewContactData("1FirstName", "2LastName",
           // "Company", "Address", "home", "mobile", "work", "fax",
           // "e-mail", null, before.get(index).getId());

    new NewContactData().withFirst_name("1FirstName").withLast_name( "2LastName").withCompany("Company").withAddress("Address").withId(before.get(index).getId());

    app.contact().modify(index, contact);
    app.goTo().returnToHomePage();

    List<NewContactData> after = app.contact().list();
    Assert.assertEquals(before.size(), after.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super NewContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }


}
