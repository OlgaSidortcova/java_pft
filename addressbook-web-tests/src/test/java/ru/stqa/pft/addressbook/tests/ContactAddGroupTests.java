package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      NewContactData contact = new NewContactData().
              withFirst_name("111FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test3"));
      app.goTo().gotoHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactAddGroup() {
    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    boolean done = false;
    NewContactData contact = new NewContactData();
    Groups beforeGroups = new Groups();
    GroupData group = new GroupData();

    for (NewContactData currentContact : allContacts) {
      Groups vacantGroups = new Groups(allGroups);
      vacantGroups.removeAll(currentContact.getGroups());

      if (!vacantGroups.isEmpty()) {
        contact = currentContact;
        beforeGroups = contact.getGroups();
        group = vacantGroups.iterator().next();
        done = true;
        break;
      }
    }
    if (!done) {
      app.goTo().groupPage();
      group = group.withName("newtest1").withFooter("test3");
      app.group().create(group);
      contact = allContacts.iterator().next();
      beforeGroups = contact.getGroups();
    }
    app.goTo().gotoHomePage();
    app.contact().selectContactById(contact.getId());
    app.contact().selectGroupForAdd(group);
    app.contact().addToGroup();

    allContacts = app.db().contacts();
    int searchId = contact.getId();
    contact = allContacts.findContactById(searchId);
    assertThat(contact.getGroups(), equalTo(beforeGroups.withAdded(group)));
  }
}


