package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    boolean gr = false;
    GroupData group = new GroupData();

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      group.withName("test1").withFooter("test3");
      app.group().create(group);
      gr = true;
      app.goTo().gotoHomePage();
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      NewContactData contact = new NewContactData().
              withFirst_name("1FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      if (gr) {
        contact.inGroup(group);
      }
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactDeleteGroup() {

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    boolean done = false;
    NewContactData contact = new NewContactData();
    Groups begoreGroups = new Groups();
    GroupData group = new GroupData();

    for (NewContactData currentContact : contacts) {
      begoreGroups = currentContact.getGroups();

      if (!begoreGroups.isEmpty()) {
        group = begoreGroups.iterator().next();
        contact = currentContact;
        done = true;
        break;
      }
    }
    if (!done) {
      contact = contacts.iterator().next();
      group = groups.iterator().next();

      app.goTo().gotoHomePage();
      app.contact().selectContactById(contact.getId());
      app.contact().selectGroupForAdd(group);
      app.contact().addToGroup();
      contact.inGroup(group);
      begoreGroups = contact.getGroups();
    }

    app.goTo().gotoHomePage();
    app.contact().selectGroupForDelete(group);
    app.contact().selectContactById(contact.getId());

    app.contact().dellFromGroup();
    app.goTo().gotoHomePage();
    contact.outGroup(group);
    assertThat(contact.getGroups(), equalTo(begoreGroups.without(group)));
  }
}


